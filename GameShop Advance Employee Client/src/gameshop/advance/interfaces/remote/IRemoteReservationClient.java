/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaccia remota per esportare ReservationClient sui client tramite Java RMI.
 * @author Matteo Gentile
 */
public interface IRemoteReservationClient extends Remote {
    
    /**
     * @param id
     * @throws RemoteException
     */
    void aggiornaIdPrenotazione(int id) throws RemoteException;

}
