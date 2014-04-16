/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.remote;

import gameshop.advance.interfaces.remote.IRemoteImage;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.ImageIcon;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class ImageProxy extends UnicastRemoteObject implements IRemoteImage {
    
    private static String imageDir = "./img/";
    private final String imagePath;
    
    public ImageProxy(String path) throws RemoteException {
        this.imagePath = path;
    }

    @Override
    public ImageIcon getIcon() throws RemoteException {
        String fullPath = imageDir.concat("small/").concat(this.imagePath);
        return new ImageIcon(fullPath);
    }

    @Override
    public ImageIcon getImage() throws RemoteException {
        String fullPath = imageDir.concat("regular/").concat(this.imagePath);
        return new ImageIcon(fullPath);
    }
}
