package gameshop.advance.interfaces.remote.factory;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.exceptions.QuantityException;
import gameshop.advance.exceptions.products.ProdottoNotFoundException;
import gameshop.advance.exceptions.products.QuantityNotInStockException;
import gameshop.advance.exceptions.sales.AlreadyPayedException;
import gameshop.advance.exceptions.sales.ClientNotFoundException;
import gameshop.advance.exceptions.sales.InvalidSaleState;
import gameshop.advance.interfaces.remote.utility.IRemoteObserver;
import gameshop.advance.utility.IDProdotto;
import gameshop.advance.utility.Money;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaccia remota per esportare cassa sui client tramite Java RMI.
 * 
 * @author Lorenzo Di Giuseppe
 */
public interface ICassaRemote extends Remote {
    /**
     *
     * @throws RemoteException
     */
    void avviaNuovaVendita() throws RemoteException;
    /**
     *
     * @throws RemoteException
     */
    void concludiVendita() throws RemoteException;

    /**
     *
     * @param ammontare
     * @throws RemoteException
     * @throws InvalidMoneyException
     * @throws gameshop.advance.exceptions.sales.InvalidSaleState
     * @throws gameshop.advance.exceptions.sales.AlreadyPayedException
     */
    void gestisciPagamento(Money ammontare) throws RemoteException, InvalidMoneyException, InvalidSaleState, AlreadyPayedException;

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
     * @throws java.rmi.RemoteException
     * @throws gameshop.advance.exceptions.products.ProdottoNotFoundException
     * @throws gameshop.advance.exceptions.QuantityException
     * @throws gameshop.advance.exceptions.products.QuantityNotInStockException
     */
    void inserisciProdotto(IDProdotto codiceProdotto, Integer quantity) throws RemoteException, ProdottoNotFoundException, QuantityException, QuantityNotInStockException;
    /**
     * @param codiceTessera
     * @throws RemoteException
     * @throws gameshop.advance.exceptions.sales.ClientNotFoundException
     */
    void inserisciTesseraCliente(int codiceTessera) throws RemoteException, ClientNotFoundException;
    /**
     * @throws RemoteException
     */
    void annullaVendita() throws RemoteException;
}
