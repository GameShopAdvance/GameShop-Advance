/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public interface IIteratorWrapperRemote<T1> extends Remote {

    boolean hasNext() throws RemoteException;

    T1 next() throws RemoteException;

    void remove() throws RemoteException;
    
}
