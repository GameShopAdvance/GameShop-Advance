/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.controller;

import gameshop.advance.interfaces.remote.IDescrizioneProdottoRemote;
import gameshop.advance.interfaces.remote.IFornitureControllerRemote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 *
 * @author matteog
 */
public class AnalizzaFornitureController extends UnicastRemoteObject implements IFornitureControllerRemote {
    public AnalizzaFornitureController() throws RemoteException
    {
        
    }
    @Override
    public List<IDescrizioneProdottoRemote> getDatiForniture() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
