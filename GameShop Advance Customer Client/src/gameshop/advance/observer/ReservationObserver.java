/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.observer;

import gameshop.advance.interfaces.remote.IRemoteBookClient;
import gameshop.advance.interfaces.remote.IRemoteObserver;
import gameshop.advance.interfaces.remote.ITransazioneRemote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Observer della prenotazione
 *
 * @author Matteo Gentile
 */
public class ReservationObserver extends UnicastRemoteObject implements IRemoteObserver {
    
    private IRemoteBookClient client;
    
    /** 
     *
     * @param client
     * @throws RemoteException
     */
    public ReservationObserver(IRemoteBookClient client) throws RemoteException {
        this.client = client;
    }
    
    @Override
    public void notifica(Object o) throws RemoteException{
        ITransazioneRemote reservation = (ITransazioneRemote ) o;
        System.err.println("Observer delle Prenotazioni, server: "+o);
        try{
            this.client.aggiornaIdPrenotazione(reservation.getId());
        }catch(RemoteException e)
        {
            Logger.getLogger(ReservationObserver.class.getName()).log(Level.SEVERE, null, e);
            throw e;
        }
    }
}

