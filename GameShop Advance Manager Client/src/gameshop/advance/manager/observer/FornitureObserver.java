/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.manager.observer;

import gameshop.advance.interfaces.remote.forniture.IFornitureManagerRemote;
import gameshop.advance.interfaces.remote.forniture.IRemoteFornitureClient;
import gameshop.advance.interfaces.remote.utility.IRemoteObserver;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Lorenzo Di Giuseppe
 */
public class FornitureObserver extends UnicastRemoteObject implements IRemoteObserver
{

    private IRemoteFornitureClient client;
    
    public FornitureObserver(IRemoteFornitureClient client) throws RemoteException
    {
        this.client = client;
    }
    
    @Override
    public void notifica(Object o) throws RemoteException
    {
        IFornitureManagerRemote manager = (IFornitureManagerRemote) o;
        System.err.println("Notifica Forniture");
        this.client.setInformazioniProdotto(manager.getInformazioni());
    }
    
}
