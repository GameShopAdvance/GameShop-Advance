package gameshop.advance.interfaces.remote.factory;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.exceptions.QuantityException;
import gameshop.advance.exceptions.products.ProdottoNotFoundException;
import gameshop.advance.exceptions.products.QuantityNotInStockException;
import gameshop.advance.exceptions.sales.AlredyPayedException;
import gameshop.advance.exceptions.sales.InvalidSaleState;
import gameshop.advance.interfaces.remote.utility.IRemoteObserver;
import gameshop.advance.utility.IDProdotto;
import gameshop.advance.utility.Money;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaccia remota per esportare cassa sui client tramite Java RMI.
 * @author loki
 */
public interface ICassaRemote extends Remote {

    /**
     * @throws RemoteException
     */
    void avviaNuovaVendita() throws RemoteException;

    /**
     * @throws RemoteException
     */
    void concludiVendita() throws RemoteException;

    /**
     * @param ammontare
     * @throws RemoteException
     * @throws InvalidMoneyException
     */
    void gestisciPagamento(Money ammontare) throws RemoteException, InvalidMoneyException, InvalidSaleState, AlredyPayedException;

    /**
     * @param obs
     * @throws RemoteException
     */
    void aggiungiListener(IRemoteObserver obs) throws RemoteException;
    
    /**
     * @param obs
     * @throws RemoteException
     */
    void rimuoviListener(IRemoteObserver obs) throws RemoteException;
    
    /**
     * @param codiceProdotto
     * @param quantity
     * @throws RemoteException
     * @throws ProdottoNotFoundException
     * @throws QuantityException
     * @throws QuantityNotInStockException
     */
    void inserisciProdotto(IDProdotto codiceProdotto, Integer quantity) throws RemoteException, ProdottoNotFoundException, QuantityException, QuantityNotInStockException;
    
    /**
     * @param codiceTessera
     * @throws RemoteException
     */
    void inserisciTesseraCliente(int codiceTessera) throws RemoteException;
    
    /**
     * @throws RemoteException
     */
    void annullaVendita() throws RemoteException;
}
