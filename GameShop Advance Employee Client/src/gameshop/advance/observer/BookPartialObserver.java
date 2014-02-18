/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.observer;

import gameshop.advance.interfaces.remote.IPrenotazioneRemote;
import gameshop.advance.interfaces.remote.IRemoteClient;
import gameshop.advance.interfaces.remote.IRemoteObserver;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Salx
 */
public class BookPartialObserver extends UnicastRemoteObject implements IRemoteObserver {
    
    private IRemoteClient client;
    
    public BookPartialObserver(IRemoteClient client) throws RemoteException {
        this.client = client;
    }
    
    @Override
    public void notifica(Object o) throws RemoteException{
        IPrenotazioneRemote prenotazione = (IPrenotazioneRemote) o;
        System.err.println("Observer del totale, client: "+this.client);
        this.client.aggiornaTotale(prenotazione.getAcconto());
    }
    
}
