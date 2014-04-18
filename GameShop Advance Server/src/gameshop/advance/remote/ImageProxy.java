/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.remote;

import gameshop.advance.interfaces.remote.utility.IRemoteImage;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.ImageIcon;

/** Proxy delle immagini collegate alle descrizioni prodotto consente anche la trasmissione remota delle stesse tramite RMI
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class ImageProxy extends UnicastRemoteObject implements IRemoteImage {
    
    private static final String imageDir = "./img/";
    private final String imagePath;
    
    public ImageProxy(String path) throws RemoteException {
        this.imagePath = path;
    }
    /**
     *
     * @return L'icona
     * @throws RemoteException
     */
    @Override
    public ImageIcon getIcon() throws RemoteException {
        String fullPath = imageDir+"small/"+this.imagePath;
        ImageIcon icon = new ImageIcon(fullPath);
        return icon;
    }
    /**
     *
     * @return L'immagine
     * @throws RemoteException
     */
    @Override
    public ImageIcon getImage() throws RemoteException {
        String fullPath = imageDir+"regular/"+this.imagePath;
        ImageIcon icon = new ImageIcon(fullPath);
        return icon;
    }
}
