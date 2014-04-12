/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces.remote;

import gameshop.advance.utility.Money;
import java.rmi.RemoteException;

/**
 * Interfaccia remota per esportare RemoteBookClient sui client tramite Java RMI.
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public interface IRemoteBookClient extends IRemoteClient {
    
    /**
     * @param ammontare
     * @throws RemoteException
     */
    void aggiornaAcconto(Money ammontare) throws RemoteException;

}
