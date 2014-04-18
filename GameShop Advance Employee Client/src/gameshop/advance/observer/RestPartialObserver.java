/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.observer;

import gameshop.advance.interfaces.remote.sales.reservations.IPrenotazioneRemote;
import gameshop.advance.interfaces.remote.sales.reservations.IRemoteBookClient;
import gameshop.advance.interfaces.remote.utility.IRemoteObserver;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Osservatore del resto dell'acconto di una prenotazione.
 * @author Salx
 */
public class RestPartialObserver extends UnicastRemoteObject implements IRemoteObserver {
    
    private IRemoteBookClient client;
    
    /**
     * @param client
     * @throws RemoteException
     */
    public RestPartialObserver(IRemoteBookClient client) throws RemoteException {
        System.err.println("Rest partial observer creato");
        this.client = client;
    }
    
    /**
     * @param o
     * @throws RemoteException
     */
    @Override
    public void notifica(Object o) throws RemoteException{
        IPrenotazioneRemote prenotazione = (IPrenotazioneRemote) o;
        this.client.aggiornaResto(prenotazione.getRestoAcconto());
    }
    
}
