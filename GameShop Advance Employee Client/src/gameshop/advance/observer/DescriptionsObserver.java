/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.observer;

import gameshop.advance.interfaces.remote.IRemoteDescriptionClient;
import gameshop.advance.interfaces.remote.factory.IInventarioControllerRemote;
import gameshop.advance.interfaces.remote.utility.IRemoteObserver;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Osservatore delle descrizioni dei prodotti inseriti.
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class DescriptionsObserver extends UnicastRemoteObject implements IRemoteObserver {
    
    private IRemoteDescriptionClient client;
    
    /**
     * @param client
     * @throws RemoteException
     */
    public DescriptionsObserver(IRemoteDescriptionClient client) throws RemoteException {
        this.client = client;
    }
    
    /**
     * @param o
     * @throws RemoteException
     */
    @Override
    public void notifica(Object o) throws RemoteException{
        System.err.println("Notifica Observer");
        IInventarioControllerRemote inventory = (IInventarioControllerRemote) o;
        System.err.println("Observer delle descrizioni, server: "+o);
        System.err.println("Observer delle descrizioni, client: "+this.client);
        try{
            this.client.addDescription(inventory.getLastDescription());
        }catch(RemoteException e)
        {
            Logger.getLogger(DescriptionsObserver.class.getName()).log(Level.SEVERE, null, e);
            throw e;
        }
    }
}
