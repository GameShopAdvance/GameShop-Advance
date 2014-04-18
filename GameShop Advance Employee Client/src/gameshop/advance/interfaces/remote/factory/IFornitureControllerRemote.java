/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces.remote.factory;

import gameshop.advance.exceptions.QuantityException;
import gameshop.advance.interfaces.remote.forniture.IInformazioniProdottoRemote;
import gameshop.advance.interfaces.remote.utility.IIteratorWrapperRemote;
import gameshop.advance.interfaces.remote.utility.IRemoteObserver;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaccia remota per il controller delle forniture.
 * @author matteog
 */
public interface IFornitureControllerRemote extends Remote{
    
    /**
     * @return
     * @throws RemoteException
     * @throws QuantityException
     */
    IIteratorWrapperRemote<IInformazioniProdottoRemote> getDatiForniture() throws RemoteException, QuantityException;
    
    /**
     * @param obs
     * @throws RemoteException
     */
    void addListener(IRemoteObserver obs) throws RemoteException;
    
    /**
     * @param obs
     * @throws RemoteException
     */
    void addDeleteListener(IRemoteObserver obs) throws RemoteException;
}
