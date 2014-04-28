/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.manager;

import gameshop.advance.interfaces.remote.forniture.IFornitureManagerRemote;
import gameshop.advance.interfaces.remote.forniture.IInformazioniProdottoRemote;
import gameshop.advance.interfaces.remote.utility.IIteratorWrapperRemote;
import gameshop.advance.utility.IteratorWrapper;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *  Proxy delle forniture consente di al manager si ottenere informazioni sui prodotti nel negozio
 *
 * @author Lorenzo Di Giuseppe
 */
class FornitureRemoteProxy extends UnicastRemoteObject implements IFornitureManagerRemote 
{   
    private final ManagerFornitureSingleton manager;
    
    public FornitureRemoteProxy() throws RemoteException
    {
        this.manager = null;
    }
    /**
     * @throws RemoteException
     */
    public FornitureRemoteProxy(ManagerFornitureSingleton manager) throws RemoteException
    {
        this.manager = manager;
    }
    /**
     * Permette di osservare i prodotti e le informazioni su di essi per 
     * al fine di analizzarle
     * 
     * @throws RemoteException
     * @return Iteratore sulle informazioniProdotto
     */
    @Override
    public IIteratorWrapperRemote<IInformazioniProdottoRemote> getInformazioni() throws RemoteException
    {
        if(this.manager == null)
            return new IteratorWrapper<>(ManagerFornitureSingleton.getInstance().getInformazioni());
        else
            return new IteratorWrapper<>(this.manager.getInformazioni());
    }
    
}
