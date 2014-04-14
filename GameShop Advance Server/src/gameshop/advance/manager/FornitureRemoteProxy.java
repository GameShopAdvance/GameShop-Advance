/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.manager;

import gameshop.advance.interfaces.remote.IFornitureManagerRemote;
import gameshop.advance.interfaces.remote.IInformazioniProdottoRemote;
import gameshop.advance.interfaces.remote.IIteratorWrapperRemote;
import gameshop.advance.utility.IteratorWrapper;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
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

    FornitureRemoteProxy(ManagerFornitureSingleton manager) throws RemoteException
    {
        this.manager = manager;
    }

    @Override
    public IIteratorWrapperRemote<IInformazioniProdottoRemote> getInformazioni() throws RemoteException
    {
        if(this.manager == null)
            return new IteratorWrapper<>(ManagerFornitureSingleton.getInstance().getInformazioni());
        else
            return new IteratorWrapper<>(this.manager.getInformazioni());
    }
    
}
