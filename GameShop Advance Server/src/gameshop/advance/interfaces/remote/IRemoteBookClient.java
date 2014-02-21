/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces.remote;

import gameshop.advance.utility.Money;
import java.rmi.RemoteException;

/** Interfaccia remota che consente esportazione di metodi per la prenotazione del cliente tramite
 *  Java RMI. I RemoteBookClient si preoccuperà di aggiornare l'acconto dovuto relativo a una prenotazione.
 *
 *  @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public interface IRemoteBookClient extends IRemoteClient {

    /**
     *
     * @param ammontare
     * @throws RemoteException
     */
    void aggiornaAcconto(Money ammontare) throws RemoteException;
}
