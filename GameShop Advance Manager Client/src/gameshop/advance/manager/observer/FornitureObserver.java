/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.manager.observer;

import gameshop.advance.controller.IRemoteFornitureClient;
import gameshop.advance.interfaces.remote.IFornitureManagerRemote;
import gameshop.advance.interfaces.remote.IRemoteObserver;
import java.rmi.RemoteException;

/**
 *
 * @author Lorenzo Di Giuseppe
 */
public class FornitureObserver implements IRemoteObserver
{

    private IRemoteFornitureClient client;
    
    public FornitureObserver(IRemoteFornitureClient client)
    {
        this.client = client;
    }
    
    @Override
    public void notifica(Object o) throws RemoteException
    {
        IFornitureManagerRemote manager = (IFornitureManagerRemote) o;
        this.client.setInformazioniProdotto(manager.getInformazioni());
    }
    
}
