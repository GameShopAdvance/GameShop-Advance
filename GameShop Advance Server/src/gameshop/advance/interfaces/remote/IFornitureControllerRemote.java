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
    
    public IIteratorWrapperRemote<IInformazioniProdottoRemote> getDatiForniture() throws RemoteException, QuantityException;
    
    public void addListener(IRemoteObserver obs) throws RemoteException;
    
}
