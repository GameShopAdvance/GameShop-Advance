/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.observer;

import gameshop.advance.interfaces.remote.sales.ITransazioneRemote;
import gameshop.advance.interfaces.remote.sales.reservations.IRemoteReservationClient;
import gameshop.advance.interfaces.remote.utility.IRemoteObserver;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Observer della transazione.
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class TransactionObserver extends UnicastRemoteObject implements IRemoteObserver{
    
    private final IRemoteReservationClient client;

    /**
     * @param client
     * @throws RemoteException
     */
    public TransactionObserver(IRemoteReservationClient client) throws RemoteException {
        this.client = client;
    }
    
    /**
     * @param o
     * @throws RemoteException
     */
    @Override
    public void notifica(Object o) throws RemoteException {
        ITransazioneRemote trans = (ITransazioneRemote) o;
        this.client.aggiornaListaProdotti(trans.getRigheDiVendita());
    }

}
