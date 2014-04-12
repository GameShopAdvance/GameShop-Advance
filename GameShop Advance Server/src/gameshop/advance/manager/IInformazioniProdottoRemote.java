/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.manager;

import gameshop.advance.interfaces.IDescrizioneProdotto;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Lorenzo Di Giuseppe
 */
public interface IInformazioniProdottoRemote extends Remote {

    IDescrizioneProdotto getDescrizione() throws RemoteException;

    int getOrdinati() throws RemoteException;

    int getPrenotati() throws RemoteException;
    
}
