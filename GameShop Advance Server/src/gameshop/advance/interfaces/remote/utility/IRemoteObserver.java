/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces.remote.utility;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaccia remota per l'esportazione delle classi observer che la implementano tramite
 * Java RMI, Consente di implementare agli observer il metodo notifica che serve ad aggiornare gli oggetti interessati.
 * 
 * @author Salx
 */
public interface IRemoteObserver extends Remote {

    /**
     *
     * @param o
     * @throws RemoteException
     */
    void notifica(Object o) throws RemoteException;

}
