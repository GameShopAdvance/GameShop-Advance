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

/** Interfaccia remota per l'esportazione delle classi che la implementano tramite
 * Java RMI IL metodo che si occupa di esportare si preoccuperà di aggiornare la sista dei prodotti presente in una prenotazione
 *
 * @author Matteo Gentile
 */
public interface IRemoteReservationClient extends Remote {
  /** 
    * 
    * @param iter
    * @throws java.rmi.RemoteException
    */
    void aggiornaListaProdotti(IIteratorWrapperRemote<IRigaDiTransazioneRemote> iter) throws RemoteException;
    
}
