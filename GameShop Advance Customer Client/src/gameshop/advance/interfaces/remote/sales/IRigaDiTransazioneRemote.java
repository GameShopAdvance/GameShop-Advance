/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces.remote.sales;

import gameshop.advance.interfaces.remote.IDescrizioneProdottoRemote;
import gameshop.advance.utility.Money;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaccia remota per l'esportazione RMI delle righe di transazione.
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public interface IRigaDiTransazioneRemote extends Remote{

    /**
     * @return
     * @throws RemoteException
     */
    IDescrizioneProdottoRemote getDescrizione() throws RemoteException;

    /**
     * @return
     * @throws RemoteException
     */
    int getQuantity() throws RemoteException;
    
    /**
     * @return
     * @throws RemoteException
     */
    Money getSubTotal() throws RemoteException;
}
