/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces.remote.utility;

import java.rmi.Remote;
import java.rmi.RemoteException;

/** 
 * Interfaccia remota per l'esportazione delle classi che la implementano tramite
 * Java RMI.GLi IteratorWrapper si occuperanno di wrappare gli iteratori necessari per recuperare 
 * le descrizioni prodotto.
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 * @param <T1>
 */
public interface IIteratorWrapperRemote<T1> extends Remote {

    /**
     * @return
     * @throws RemoteException
     */
    boolean hasNext() throws RemoteException;

    /**
     * @return
     * @throws RemoteException
     */
    T1 next() throws RemoteException;

    /**
     * @throws RemoteException
     */
    void remove() throws RemoteException;
    
}
