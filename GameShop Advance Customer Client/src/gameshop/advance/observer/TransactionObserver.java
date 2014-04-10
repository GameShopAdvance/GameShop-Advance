/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.observer;

import gameshop.advance.interfaces.remote.IRemoteObserver;
import gameshop.advance.interfaces.remote.IRemoteReservationClient;
import gameshop.advance.interfaces.remote.ITransazioneRemote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class TransactionObserver extends UnicastRemoteObject implements IRemoteObserver{
    
    private final IRemoteReservationClient client;

    public TransactionObserver(IRemoteReservationClient client) throws RemoteException {
        this.client = client;
    }
    
    @Override
    public void notifica(Object o) throws RemoteException {
        ITransazioneRemote trans = (ITransazioneRemote) o;
        System.err.println("Transazione "+trans);
        this.client.aggiornaListaProdotti(trans.getRigheDiVendita());
    }

}
