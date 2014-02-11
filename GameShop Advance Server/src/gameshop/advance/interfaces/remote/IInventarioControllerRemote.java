/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces.remote;

import gameshop.advance.exceptions.ProdottoNotFoundException;
import gameshop.advance.exceptions.QuantityException;
import gameshop.advance.utility.IDProdotto;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public interface IInventarioControllerRemote extends Remote{
    
    public void inserisciProdotto(IDProdotto code, int quantity) throws RemoteException, QuantityException, ProdottoNotFoundException;
    
    public void terminaInventario() throws RemoteException;
    
    public IDescrizioneProdottoRemote getLastDescription() throws RemoteException;

    public void cancel() throws RemoteException;

    public void avviaInventario() throws RemoteException;
    
    void aggiungiListener(IRemoteObserver obs)  throws RemoteException;
    
    void rimuoviListener(IRemoteObserver obs)  throws RemoteException;
    
}
