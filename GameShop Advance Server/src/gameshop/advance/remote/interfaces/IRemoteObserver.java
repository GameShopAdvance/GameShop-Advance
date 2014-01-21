/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.remote.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaccia remota per l'esportazione delle classiobserver che la implementano tramite
 * Java RMI.
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
