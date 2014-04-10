/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author matteog
 */
public interface IFornitureControllerRemote extends Remote{
    
    public List<IDescrizioneProdottoRemote> getDatiForniture() throws RemoteException;
    
}
