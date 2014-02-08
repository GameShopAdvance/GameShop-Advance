/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.observer;

import gameshop.advance.interfaces.remote.IInventarioControllerRemote;
import gameshop.advance.interfaces.remote.IRemoteDescriptionClient;
import gameshop.advance.interfaces.remote.IRemoteObserver;
import java.rmi.RemoteException;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class DescriptionsObserver implements IRemoteObserver {
    
    private IRemoteDescriptionClient client;
    
    public DescriptionsObserver(IRemoteDescriptionClient client) throws RemoteException {
        this.client = client;
    }
    
    @Override
    public void notifica(Object o) throws RemoteException{
        IInventarioControllerRemote inventory = (IInventarioControllerRemote) o;
        System.err.println("Observer delle descrizioni, client: "+this.client);
        this.client.addDescription(inventory.getLastDescription());
        
    }
}
