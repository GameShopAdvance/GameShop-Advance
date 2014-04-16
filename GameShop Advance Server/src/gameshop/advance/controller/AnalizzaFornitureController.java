/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.controller;

import gameshop.advance.exceptions.QuantityException;
import gameshop.advance.interfaces.remote.IFornitureControllerRemote;
import gameshop.advance.interfaces.remote.IInformazioniProdottoRemote;
import gameshop.advance.interfaces.remote.IIteratorWrapperRemote;
import gameshop.advance.interfaces.remote.IRemoteObserver;
import gameshop.advance.manager.ManagerFornitureSingleton;
import gameshop.advance.utility.IteratorWrapper;
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

    /**
     *
     * @return
     * @throws RemoteException
     * @throws QuantityException
     */
    @Override
    public IIteratorWrapperRemote<IInformazioniProdottoRemote> getDatiForniture() throws RemoteException, QuantityException {
        return new IteratorWrapper<>(ManagerFornitureSingleton.getInstance().getInformazioni());
    }
    
    @Override
    public void addListener(IRemoteObserver obs) throws RemoteException
    {
        ManagerFornitureSingleton.getInstance().addListener(obs);
    }
    
    public void addDeleteListener(IRemoteObserver obs) throws RemoteException
    {
        ManagerFornitureSingleton.getInstance().addRemoveListener(obs);
    }
}
