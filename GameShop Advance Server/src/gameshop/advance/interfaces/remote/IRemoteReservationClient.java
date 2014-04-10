/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Iterator;

/** Interfaccia remota per l'esportazione delle classi che la implementano tramite
 * Java RMI.I RemoteReservationClient si preoccuperà di aggiornare l'id di una prenotazione.
 *
 * @author Matteo Gentile
 */
public interface IRemoteReservationClient extends Remote {
    
    /** Metodo che consente di aggiornare l'identificativo di prenotazione effettuata dal cliente
     *
     * @param id
     * @throws RemoteException
     */
    
    void aggiornaIdPrenotazione(int id) throws RemoteException;

    void aggiornaListaProdotti(Iterator<IRigaDiTransazioneRemote> iter) throws RemoteException;
    
}
