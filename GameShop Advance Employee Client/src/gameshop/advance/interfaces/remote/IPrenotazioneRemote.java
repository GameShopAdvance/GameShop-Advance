/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces.remote;

import gameshop.advance.utility.Money;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Salx
 */
public interface IPrenotazioneRemote extends Remote {
    
    Money getAcconto() throws RemoteException;
    Money getRestoAcconto() throws RemoteException;
    
}
