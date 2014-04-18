/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces.remote.sales.reservations;

import gameshop.advance.interfaces.remote.sales.ITransazioneRemote;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;


/** 
 * Interfaccia remota per l'esportazione delle classi che la implementano tramite
 * Java RMI.
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public interface IPrenotazioneRemote extends ITransazioneRemote {

    /**
     * @return
     * @throws RemoteException
     */
    Money getAcconto() throws RemoteException;

    /**
     * @return
     * @throws RemoteException
     */
    Money getRestoAcconto() throws RemoteException;
}
