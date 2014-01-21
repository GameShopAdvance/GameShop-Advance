package gameshop.advance.remote.interfaces;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.exceptions.ProdottoNotFoundException;
import gameshop.advance.exceptions.QuantityException;
import gameshop.advance.utility.IDProdotto;
import gameshop.advance.utility.Money;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaccia remota per esportare cassa sui client tramite Java RMI.
 * @author loki
 */
public interface ICassaRemote extends Remote {

    void avviaNuovaVendita() throws RemoteException;

    void concludiVendita() throws RemoteException;

    /**
     *
     * @param ammontare
     * @throws RemoteException
     * @throws InvalidMoneyException
     */
    void gestisciPagamento(Money ammontare) throws RemoteException, InvalidMoneyException;

    /**
     *
     * @param obs
     * @throws RemoteException
     */
    void aggiungiListener(IRemoteObserver obs) throws RemoteException;
    
    /**
     *
     * @param obs
     * @throws RemoteException
     */
    void rimuoviListener(IRemoteObserver obs) throws RemoteException;
    
    /**
     *
     * @param codiceProdotto
     * @param quantity
     */
    void inserisciProdotto(IDProdotto codiceProdotto, Integer quantity) throws RemoteException, ProdottoNotFoundException, QuantityException;
    
}
