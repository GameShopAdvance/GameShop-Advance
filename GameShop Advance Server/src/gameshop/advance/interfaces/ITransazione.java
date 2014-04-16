/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces;

import gameshop.advance.exceptions.AlredyPayedException;
import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.exceptions.InvalidSaleState;
import gameshop.advance.exceptions.QuantityNotInStockException;
import gameshop.advance.interfaces.remote.IRemoteObserver;
import gameshop.advance.interfaces.remote.ITransazioneRemote;
import gameshop.advance.model.transazione.CartaCliente;
import gameshop.advance.model.transazione.sconto.vendita.ScontoVenditaStrategyComposite;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;
import java.util.LinkedList;
import org.joda.time.DateTime;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public interface ITransazione extends ITransazioneRemote {

    /**
     * Imposta lo stato della vendita come completata e invia una notifica agli
     * osservatori in ascolto.
     */
    void completaTransazione() throws RemoteException;

    /**
     * Utilizza i parametri ricevuti in ingresso (descrizione prodotto e quantità) per
     * creare una riga di vendita.Aggiunge poi questa riga di vendita all'elenco di righe della
     * vendita corrente.Notifica tale aggiunta agli osservatori in ascolto per far
     * aggiornare l'output.
     * @param desc
     * @param quantity
     * @throws java.rmi.RemoteException
     */
    void inserisciProdotto(IDescrizioneProdotto desc, int quantity) throws RemoteException, QuantityNotInStockException;

    /**
     * Crea un nuovo oggetto pagamento utilizzando l'ammontare della vendita corrente.Aggiunge
     * tale pagamento alla vendita e invia una notifica gli osservatori in ascolto.
     * @param ammontare
     * @throws gameshop.advance.exceptions.InvalidMoneyException
     * @throws java.rmi.RemoteException
     */
    void gestisciPagamento(Money ammontare) throws InvalidMoneyException, RemoteException, InvalidSaleState, AlredyPayedException;

    CartaCliente getCliente();

    void setCliente(CartaCliente c) throws RemoteException;

    void setId(Integer idVendita) throws RemoteException;
    
    Money getPagamento();

    void addSconti(LinkedList<IScontoVenditaStrategy> scontiAttuali) throws RemoteException;
    
    void setStrategiaSconto(ScontoVenditaStrategyComposite sconto);
    
    boolean isCompleted();
    
    void aggiungiListener(IRemoteObserver obs);
    
    void rimuoviListener(IRemoteObserver obs);
        
    DateTime getDate();
    
    void annulla() throws RemoteException;
}
