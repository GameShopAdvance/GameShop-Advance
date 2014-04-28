/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.manager.observer;

import gameshop.advance.interfaces.remote.forniture.IInformazioniProdottoRemote;
import gameshop.advance.interfaces.remote.forniture.IRemoteFornitureClient;
import gameshop.advance.interfaces.remote.utility.IRemoteObserver;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Osservatore delle forniture.Si occupa di eliminare un prodotto dall'elenco di prodotti da ordinare.
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class FornitureRemoveObserver extends UnicastRemoteObject implements IRemoteObserver {
    private IRemoteFornitureClient client;
    
    /**
     * @param client
     * @throws RemoteException
     */
    public FornitureRemoveObserver(IRemoteFornitureClient client) throws RemoteException
    {
        this.client = client;
    }
    
    /**
     * @param o
     * @throws RemoteException
     */
    @Override
    public void notifica(Object o) throws RemoteException {
        IInformazioniProdottoRemote info = (IInformazioniProdottoRemote) o;
        this.client.rimuoviInformazioneProdotto(info);
    }
    
    
}
