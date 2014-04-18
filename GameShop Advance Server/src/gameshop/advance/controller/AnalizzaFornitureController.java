/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.controller;

import gameshop.advance.exceptions.QuantityException;
import gameshop.advance.interfaces.remote.factory.IFornitureControllerRemote;
import gameshop.advance.interfaces.remote.forniture.IInformazioniProdottoRemote;
import gameshop.advance.interfaces.remote.utility.IIteratorWrapperRemote;
import gameshop.advance.interfaces.remote.utility.IRemoteObserver;
import gameshop.advance.manager.ManagerFornitureSingleton;
import gameshop.advance.utility.IteratorWrapper;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/** 
 * Controller che implementa le operazioni di sistema necessarie al Manager per poter analizzare le forniture del negozio
 *
 * @author Matteo Gentile
 */
public class AnalizzaFornitureController extends UnicastRemoteObject implements IFornitureControllerRemote {
   /** 
     * @throws java.rmi.RemoteException
    */
    public AnalizzaFornitureController() throws RemoteException
    {
    }

    /** 
     *
     * @return L'iteratore collegato alla HashMap delle informazioni su prenotazioni e quantità dei prodotti 
     * @throws RemoteException
     * @throws QuantityException
     */
    @Override
    public IIteratorWrapperRemote<IInformazioniProdottoRemote> getDatiForniture() throws RemoteException, QuantityException {
        return new IteratorWrapper<>(ManagerFornitureSingleton.getInstance().getInformazioni());
    }
    /** 
     *
     * @param obs
     * @throws java.rmi.RemoteException
     */
    @Override
    public void addListener(IRemoteObserver obs) throws RemoteException
    {
        ManagerFornitureSingleton.getInstance().addListener(obs);
    }
    /** 
     *
     * @param obs
     * @throws java.rmi.RemoteException
     */
    @Override
    public void addDeleteListener(IRemoteObserver obs) throws RemoteException
    {
        ManagerFornitureSingleton.getInstance().addRemoveListener(obs);
    }
}
