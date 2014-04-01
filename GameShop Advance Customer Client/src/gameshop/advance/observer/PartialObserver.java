/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.observer;

import gameshop.advance.interfaces.remote.IPrenotazioneRemote;
import gameshop.advance.interfaces.remote.IRemoteBookClient;
import gameshop.advance.interfaces.remote.IRemoteObserver;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Osservatore dell'acconto di una prenotazione.
 * @author Salx
 */
public class PartialObserver extends UnicastRemoteObject implements IRemoteObserver {
    
    private IRemoteBookClient client;
    
    /**
     * @param client
     * @throws RemoteException
     */
    public PartialObserver(IRemoteBookClient client) throws RemoteException {
        this.client = client;
    }
    
    /**
     * @param o
     * @throws RemoteException
     */
    @Override
    public void notifica(Object o) throws RemoteException{
        IPrenotazioneRemote prenotazione = (IPrenotazioneRemote) o;
        System.err.println("Observer del totale, client: "+this.client);
        this.client.aggiornaAcconto(prenotazione.getAcconto());
    }
    
}
