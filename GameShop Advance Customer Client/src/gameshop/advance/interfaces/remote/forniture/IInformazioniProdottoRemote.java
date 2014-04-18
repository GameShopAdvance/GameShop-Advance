/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces.remote.forniture;

import gameshop.advance.interfaces.remote.IDescrizioneProdottoRemote;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaccia remota per l'esportazione RMI della classe informazioni prodotto.
 * @author Lorenzo Di Giuseppe
 */
public interface IInformazioniProdottoRemote extends Remote {

    /**
     * @return
     * @throws RemoteException
     */
    IDescrizioneProdottoRemote getDescrizione() throws RemoteException;

    /**
     * @return
     * @throws RemoteException
     */
    int getOrdinati() throws RemoteException;

    /**
     * @return
     * @throws RemoteException
     */
    int getPrenotati() throws RemoteException;
    
}
