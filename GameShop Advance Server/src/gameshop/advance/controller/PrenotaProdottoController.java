package gameshop.advance.controller;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.exceptions.QuantityException;
import gameshop.advance.exceptions.db.ReservationNotFoundDbException;
import gameshop.advance.exceptions.products.ProdottoNotFoundException;
import gameshop.advance.exceptions.products.QuantityNotInStockException;
import gameshop.advance.exceptions.sales.AlreadyPartialPayedException;
import gameshop.advance.exceptions.sales.AlreadyPayedException;
import gameshop.advance.exceptions.sales.ClientNotFoundException;
import gameshop.advance.exceptions.sales.InvalidSaleState;
import gameshop.advance.interfaces.IDescrizioneProdotto;
import gameshop.advance.interfaces.IPrenotazione;
import gameshop.advance.interfaces.remote.factory.IPrenotaProdottoRemote;
import gameshop.advance.interfaces.remote.utility.IRemoteObserver;
import gameshop.advance.model.CatalogoProdottiSingleton;
import gameshop.advance.model.NegozioSingleton;
import gameshop.advance.model.transazione.CartaCliente;
import gameshop.advance.model.transazione.Prenotazione;
import gameshop.advance.technicalservices.LoggerSingleton;
import gameshop.advance.utility.IDProdotto;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controller che consente la gestione delle prenotazioni lato cliente e commesso
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class PrenotaProdottoController extends UnicastRemoteObject implements IPrenotaProdottoRemote {
    
    private IPrenotazione prenotazione;
    
    //OPERAZIONI DI SISTEMA LATO CLIENTE
    
    /**
     * @throws java.rmi.RemoteException   
    */
    public PrenotaProdottoController() throws RemoteException
    {
        
    }
   /**
     * @throws java.rmi.RemoteException   
    */
    @Override
    public void avviaPrenotazione() throws RemoteException
    {
        this.prenotazione = new Prenotazione(NegozioSingleton.getInstance().getPercentualeAcconto());
    }
    /** Consente al cliente di prenotare un prodotto non disponibile in negozio
     * @param quantity 
     * @param codiceProdotto 
     * @throws java.rmi.RemoteException   
     * @throws gameshop.advance.exceptions.products.ProdottoNotFoundException   
    */
    @Override
    public void prenotaProdotto(IDProdotto codiceProdotto, int quantity) throws RemoteException, ProdottoNotFoundException
    {
        IDescrizioneProdotto desc;
        desc = CatalogoProdottiSingleton.getInstance().getDescrizioneProdotto(codiceProdotto);
        try {
            this.prenotazione.inserisciProdotto(desc, quantity);
        } catch (QuantityNotInStockException ex) {
            throw new RemoteException(ex.getMessage());
        }
    }
    /**
     * @throws java.rmi.RemoteException   
    */
    @Override
    public void terminaPrenotazione() throws RemoteException
    {
        this.prenotazione.setId(NegozioSingleton.getInstance().getNextBookId());
        this.prenotazione.rimuoviListener(null);
        try {
            NegozioSingleton.getInstance().registraPrenotazione(this.prenotazione);
        } catch (QuantityException ex) {
            LoggerSingleton.getInstance().log(ex);
            //Questa eccezione non dovrebbe propagarsi, è una situazione che con una prenotazione
            //non dovrebbe verificarsi mai
        }
           
    }
    
    //OPERAZIONI DI SISTEMA LATO COMMESSO
    
    /** Consente al commesso di recuperare una prenotazione avviata da un cliente e successivamente completarla
     * 
     * @param id
     * @throws java.rmi.RemoteException   
     * @throws gameshop.advance.exceptions.db.ReservationNotFoundDbException   
    */
    @Override
    public void recuperaPrenotazione(Integer id) throws RemoteException, ReservationNotFoundDbException
    {
        this.prenotazione = (IPrenotazione) NegozioSingleton.getInstance().riprendiPrenotazione(id);
        this.prenotazione.rimuoviListener(null);
    }
    /** 
     * @throws java.rmi.RemoteException   
    */
    @Override
    public void completaPrenotazione() throws RemoteException
    {
        this.prenotazione.completaTransazione();
    }
    /** Permette di pagare l'acconto relativo a una prenotazione avviata da un cliente 
     * 
     * @param ammontare 
     * @throws java.rmi.RemoteException   
     * @throws gameshop.advance.exceptions.InvalidMoneyException   
     * @throws gameshop.advance.exceptions.sales.InvalidSaleState   
     * @throws gameshop.advance.exceptions.sales.AlreadyPartialPayedException   
     * @throws gameshop.advance.exceptions.sales.AlreadyPayedException   
    */
    @Override
    public void pagaAcconto(Money ammontare) throws RemoteException, InvalidMoneyException, InvalidSaleState, AlreadyPartialPayedException
    {
        this.prenotazione.pagaAcconto(ammontare);
        this.prenotazione.rimuoviListener(null);
        try {
            NegozioSingleton.getInstance().registraPrenotazione(this.prenotazione);
        } catch (QuantityException ex) {
            Logger.getLogger(PrenotaProdottoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *
     * @param obs
     * @throws RemoteException
     */
    @Override
    public void addListener(IRemoteObserver obs) throws RemoteException
    {
        this.prenotazione.aggiungiListener(obs);
    }
    
    public void rimuoviListener(IRemoteObserver obs) throws RemoteException
    {
        this.prenotazione.rimuoviListener(obs);
    }
    
    /** Permette al commesso di pagare il totale della prenotazione al momento del ritiro del prodotto
     *
     * @param amount
     * @throws RemoteException
     * @throws InvalidMoneyException
     * @throws gameshop.advance.exceptions.sales.InvalidSaleState
     * @throws gameshop.advance.exceptions.sales.AlreadyPayedException
     */
    @Override
    public void gestisciPagamento(Money amount) throws RemoteException, InvalidMoneyException, InvalidSaleState, AlreadyPayedException
    {
        this.prenotazione.gestisciPagamento(amount);
        this.prenotazione.rimuoviListener(null);
        try {
            NegozioSingleton.getInstance().registraPrenotazione(this.prenotazione);
        } catch (QuantityException ex) {
            Logger.getLogger(PrenotaProdottoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /** Annulla una prenotazione in corso 
     *
     * @throws java.rmi.RemoteException
     */
    @Override
    public void cancellaPrenotazione() throws RemoteException{
        this.prenotazione = null;
    }
    /** Consente di aggiungere alla prenotazione una carta cliente la quale può dar luogo a sconti per il possessore
     *
     * @param code
     * @throws java.rmi.RemoteException
     */
    @Override
    public void inserisciCartaCliente(Integer code) throws RemoteException, ClientNotFoundException {
        CartaCliente carta = NegozioSingleton.getInstance().getCliente(code);
        if(carta!=null)
        {
            this.prenotazione.setCliente(carta);
        }
    }
    
}
