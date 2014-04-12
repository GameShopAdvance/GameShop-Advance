/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.controller;

import gameshop.advance.interfaces.remote.IFornitureControllerRemote;
import gameshop.advance.interfaces.remote.IInformazioniProdottoRemote;
import gameshop.advance.interfaces.remote.IIteratorWrapperRemote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author matteog
 */
public class AnalizzaFornitureController extends UnicastRemoteObject implements IFornitureControllerRemote {
    public AnalizzaFornitureController() throws RemoteException
    {
        
    }
    @Override
    public IIteratorWrapperRemote<IInformazioniProdottoRemote> getDatiForniture() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
