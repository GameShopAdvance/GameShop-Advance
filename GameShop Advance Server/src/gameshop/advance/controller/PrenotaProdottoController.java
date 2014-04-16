package gameshop.advance.controller;

import gameshop.advance.exceptions.AlredyPayedException;
import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.exceptions.InvalidSaleState;
import gameshop.advance.exceptions.ProdottoNotFoundException;
import gameshop.advance.exceptions.QuantityException;
import gameshop.advance.exceptions.QuantityNotInStockException;
import gameshop.advance.interfaces.IDescrizioneProdotto;
import gameshop.advance.interfaces.IPrenotazione;
import gameshop.advance.interfaces.remote.IPrenotaProdottoRemote;
import gameshop.advance.interfaces.remote.IRemoteObserver;
import gameshop.advance.model.CatalogoProdottiSingleton;
import gameshop.advance.model.NegozioSingleton;
import gameshop.advance.model.transazione.Prenotazione;
import gameshop.advance.utility.IDProdotto;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class PrenotaProdottoController extends UnicastRemoteObject implements IPrenotaProdottoRemote {
    
    private IPrenotazione prenotazione;
    
    //OPERAZIONI DI SISTEMA LATO CLIENTE
    
    public PrenotaProdottoController() throws RemoteException
    {
        
    }
    
    /*
    @Override
    public IteratorWrapper<IDescrizioneProdottoRemote> getDescriptions() throws RemoteException
    {
        Iterator<Object> iter = CatalogoProdottiSingleton.getInstance().getDescrizioni();
        LinkedList<IDescrizioneProdottoRemote> list = new LinkedList<>();
        while(iter.hasNext())
        {
            list.add(new DescrizioneRemoteProxy((IDescrizioneProdottoRemote) iter.next()));
        }
        return new IteratorWrapper<> (list.iterator());
    }*/
    
    @Override
    public void avviaPrenotazione() throws RemoteException
    {
        this.prenotazione = new Prenotazione(NegozioSingleton.getInstance().getPercentualeAcconto());
        System.err.println("Prenotazione creata");
    }
    
    @Override
    public void prenotaProdotto(IDProdotto codiceProdotto, int quantity) throws RemoteException, ProdottoNotFoundException
    {
        IDescrizioneProdotto desc;
        desc = CatalogoProdottiSingleton.getInstance().getDescrizioneProdotto(codiceProdotto);
        System.err.println("Descrizione: "+desc);
        try {
            this.prenotazione.inserisciProdotto(desc, quantity);
        } catch (QuantityNotInStockException ex) {
            throw new RemoteException(ex.getMessage());
        }
    }
    
    @Override
    public void terminaPrenotazione() throws RemoteException
    {
        this.prenotazione.setId(NegozioSingleton.getInstance().getNextBookId());
        this.prenotazione.rimuoviListener(null);
        try {
            NegozioSingleton.getInstance().registraPrenotazione(this.prenotazione);
        } catch (QuantityException ex) {
            Logger.getLogger(PrenotaProdottoController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }
    
    //OPERAZIONI DI SISTEMA LATO COMMESSO
    
    @Override
    public void recuperaPrenotazione(Integer id) throws RemoteException
    {
        this.prenotazione = (IPrenotazione) NegozioSingleton.getInstance().riprendiPrenotazione(id);
    }
    
    @Override
    public void completaPrenotazione() throws RemoteException
    {
        this.prenotazione.completaTransazione();
    }
    
    @Override
    public void pagaAcconto(Money ammontare) throws RemoteException, InvalidMoneyException, InvalidSaleState, AlredyPayedException
    {
        this.prenotazione.pagaAcconto(ammontare);
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
    
    /**
     *
     * @param amount
     * @throws RemoteException
     * @throws InvalidMoneyException
     */
    @Override
    public void gestisciPagamento(Money amount) throws RemoteException, InvalidMoneyException, InvalidSaleState, AlredyPayedException
    {
        this.prenotazione.gestisciPagamento(amount);
        try {
            NegozioSingleton.getInstance().registraPrenotazione(this.prenotazione);
        } catch (QuantityException ex) {
            Logger.getLogger(PrenotaProdottoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void cancellaPrenotazione() {
        this.prenotazione = null;
    }
    
}
