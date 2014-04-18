/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces.remote.utility;

import java.rmi.Remote;
import java.rmi.RemoteException;
import javax.swing.ImageIcon;

/**
 * Interfaccia remota per l'esportazione RMI della classe che gestisce le immagini.
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public interface IRemoteImage extends Remote {
    
    /**
     * @return
     * @throws RemoteException
     */
    ImageIcon getIcon() throws RemoteException;
    
    /**
     * @return
     * @throws RemoteException
     */
    ImageIcon getImage() throws RemoteException;
}
