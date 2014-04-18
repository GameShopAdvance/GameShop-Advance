/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces.remote.factory;

import gameshop.advance.exceptions.QuantityException;
import gameshop.advance.interfaces.remote.forniture.IInformazioniProdottoRemote;
import gameshop.advance.interfaces.remote.utility.IIteratorWrapperRemote;
import gameshop.advance.interfaces.remote.utility.IRemoteObserver;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaccia remota per l'esportazione dei metodi necessari ad ottenere i dati relativi alle forniture
 *
 * @author Matteo Gentile
 */
public interface IFornitureControllerRemote extends Remote{
    /**
     * @return L'iteratore collegato alla HashMap delle informazioni su prenotazioni e quantità dei prodotti 
     * @throws java.rmi.RemoteException
     * @throws gameshop.advance.exceptions.QuantityException
    */
    IIteratorWrapperRemote<IInformazioniProdottoRemote> getDatiForniture() throws RemoteException, QuantityException;
    /**
     * @param obs
     * @throws java.rmi.RemoteException
    */
    void addListener(IRemoteObserver obs) throws RemoteException;
    /**
     * @param obs
     * @throws java.rmi.RemoteException
    */
    void addDeleteListener(IRemoteObserver obs) throws RemoteException;
}
