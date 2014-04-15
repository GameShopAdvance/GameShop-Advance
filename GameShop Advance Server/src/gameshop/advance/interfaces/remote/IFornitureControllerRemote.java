/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces.remote;

import gameshop.advance.exceptions.QuantityException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author matteog
 */
public interface IFornitureControllerRemote extends Remote{
    
    IIteratorWrapperRemote<IInformazioniProdottoRemote> getDatiForniture() throws RemoteException, QuantityException;
    
    void addListener(IRemoteObserver obs) throws RemoteException;
    
    void addDeleteListener(IRemoteObserver obs) throws RemoteException;
}
