/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces.remote.factory;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.exceptions.products.ProdottoNotFoundException;
import gameshop.advance.exceptions.sales.AlredyPayedException;
import gameshop.advance.exceptions.sales.InvalidSaleState;
import gameshop.advance.interfaces.remote.utility.IRemoteObserver;
import gameshop.advance.utility.IDProdotto;
import gameshop.advance.utility.Money;
import java.rmi.Remote;
import java.rmi.RemoteException;

/** 
 * Interfaccia remota per l'esportazione delle classi che la implementano tramite
 * Java RMI, IPrenotaProdottoRemote renderà invocabili da remoto tutti i metodi necessari a effettuare una prenotazione sia esso lato cliente o lato commesso
 * Esporta PrenotaProdotto Controller.
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public interface IPrenotaProdottoRemote extends Remote{

    /**
     *
     * @param obs
     * @throws RemoteException
     */
    void addListener(IRemoteObserver obs) throws RemoteException;
    
    /**********************************

    OPERAZIONI DI SISTEMA LATO CLIENTE

    **********************************/
    
    /**
     *
     * @throws RemoteException
     */
    void avviaPrenotazione() throws RemoteException;

    /**
     *
     * @param codiceProdotto
     * @param quantity
     * @throws RemoteException
     * @throws ProdottoNotFoundException
     */
    void prenotaProdotto(IDProdotto codiceProdotto, int quantity) throws RemoteException, ProdottoNotFoundException;
    
    /**
     *
     * @throws RemoteException
     */
    void terminaPrenotazione() throws RemoteException;
    
    /**********************************

    OPERAZIONI DI SISTEMA LATO COMMESSO

    **********************************/

    /**
     *
     * @throws RemoteException
     */
    void completaPrenotazione() throws RemoteException;
    
    /**
     *
     * @param id
     * @throws RemoteException
     */
    void recuperaPrenotazione(Integer id) throws RemoteException;
    
    /**
     *
     * @param amount
     * @throws RemoteException
     * @throws InvalidMoneyException
     * @throws gameshop.advance.exceptions.sales.InvalidSaleState
     * @throws gameshop.advance.exceptions.sales.AlredyPayedException
     */
    void gestisciPagamento(Money amount) throws RemoteException, InvalidMoneyException, InvalidSaleState, AlredyPayedException;
    
    //metodo per la richiesta di pagamento in acconto

    /**
     *
     * @param ammontare
     * @throws RemoteException
     * @throws InvalidMoneyException
     * @throws gameshop.advance.exceptions.sales.InvalidSaleState
     * @throws gameshop.advance.exceptions.sales.AlredyPayedException
     */
    void pagaAcconto(Money ammontare) throws RemoteException, InvalidMoneyException, InvalidSaleState, AlredyPayedException;
    
    /**
     *
     * @throws RemoteException
     */
    public void cancellaPrenotazione() throws RemoteException;
    /**
     *
     * @param code
     * @throws RemoteException
     */
    public void inserisciCartaCliente(Integer code) throws RemoteException;
    
}
