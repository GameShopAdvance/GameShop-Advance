/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces.remote.sales.reservations;

import gameshop.advance.interfaces.remote.sales.IRigaDiTransazioneRemote;
import gameshop.advance.interfaces.remote.utility.IIteratorWrapperRemote;
import java.rmi.Remote;
import java.rmi.RemoteException;

/** 
 * Interfaccia remota per l'esportazione delle classi che la implementano tramite
 * Java RMI.I RemoteReservationClient si preoccuperà di aggiornare l'id di una prenotazione.
 * @author Matteo Gentile
 */
public interface IRemoteReservationClient extends Remote {

    /**
     * @param iter
     * @throws RemoteException
     */
    void aggiornaListaProdotti(IIteratorWrapperRemote<IRigaDiTransazioneRemote> iter) throws RemoteException;
    
}
