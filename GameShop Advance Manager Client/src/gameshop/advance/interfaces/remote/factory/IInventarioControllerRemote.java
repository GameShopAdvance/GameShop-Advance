/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces.remote.factory;

import gameshop.advance.exceptions.QuantityException;
import gameshop.advance.exceptions.products.ProdottoNotFoundException;
import gameshop.advance.interfaces.remote.IDescrizioneProdottoRemote;
import gameshop.advance.interfaces.remote.utility.IRemoteObserver;
import gameshop.advance.utility.IDProdotto;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaccia remota per l'esportazione delle classi che la implementano tramite
 * Java RMI.I InventarioControllerRemote renderanno invocabili da remoto tutti i metodi necessari a effettuare l'inventario
 * del negozio.Esporta GestisciInventarioController.
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public interface IInventarioControllerRemote extends Remote{
    
    /**
     * @param code
     * @param quantity
     * @throws RemoteException
     * @throws QuantityException
     * @throws ProdottoNotFoundException
     */
    public void inserisciProdotto(IDProdotto code, int quantity) throws RemoteException, QuantityException, ProdottoNotFoundException;
    
    /**
     * @throws RemoteException
     */
    public void terminaInventario() throws RemoteException;
    
    /**
     * @return
     * @throws RemoteException
     */
    public IDescrizioneProdottoRemote getLastDescription() throws RemoteException;

    /**
     * @throws RemoteException
     */
    public void cancel() throws RemoteException;

    /**
     * @throws RemoteException
     */
    public void avviaInventario() throws RemoteException;
    
    /**
     * @param obs
     * @throws RemoteException
     */
    void aggiungiListener(IRemoteObserver obs)  throws RemoteException;
    
    /**
     * @param obs
     * @throws RemoteException
     */
    void rimuoviListener(IRemoteObserver obs)  throws RemoteException;
    
}
