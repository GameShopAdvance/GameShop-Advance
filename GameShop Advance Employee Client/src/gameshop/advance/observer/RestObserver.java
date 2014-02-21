/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.observer;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.interfaces.remote.IRemoteClient;
import gameshop.advance.interfaces.remote.IRemoteObserver;
import gameshop.advance.interfaces.remote.ITransazioneRemote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Osservatore del resto di una transazione.
 * @author Salx
 */
public class RestObserver extends UnicastRemoteObject implements IRemoteObserver {
    private IRemoteClient client;

    /**
     * @param client
     * @throws RemoteException
     */
    public RestObserver(IRemoteClient client) throws RemoteException {
        this.client = client;
    }
    
    /**
     * @param o
     * @throws RemoteException
     */
    @Override
    public void notifica(Object o) throws RemoteException {
        ITransazioneRemote sale = (ITransazioneRemote) o;
        try {        
            this.client.aggiornaResto(sale.getResto());
        } catch (InvalidMoneyException ex) {
             throw new RemoteException(ex.getMessage());
        }
    }

    
}
