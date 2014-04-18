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
 *  Interfaccia remota per l'esportazione delle classi che la implementano tramite
 * Java RMI.GLi IRemoteImage rendono disponibili i metodi per ottenere icone e immagini dei prodotti
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public interface IRemoteImage extends Remote {
    /**
     *
     * @return L'icona
     * @throws RemoteException
     */
    ImageIcon getIcon() throws RemoteException;
    /**
     *
     * @return L'immagine
     * @throws RemoteException
     */
    ImageIcon getImage() throws RemoteException;
}
