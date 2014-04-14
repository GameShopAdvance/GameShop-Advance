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

/**
 *
 * @author Lorenzo Di Giuseppe
 */
class FornitureRemoteProxy implements IFornitureManagerRemote 
{   
    private ManagerFornitureSingleton manager;
    public FornitureRemoteProxy()
    {
        this.manager = null;
    }

    FornitureRemoteProxy(ManagerFornitureSingleton manager)
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
