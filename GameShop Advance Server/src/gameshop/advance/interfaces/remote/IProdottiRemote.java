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
 * @author Salx
 */
public interface IProdottiRemote extends Remote {
    
    public IIteratorWrapperRemote<IDescrizioneProdottoRemote> getDescrizioni()throws RemoteException;
    
}
