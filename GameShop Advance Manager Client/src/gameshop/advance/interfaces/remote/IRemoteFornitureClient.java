/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces.remote;

import gameshop.advance.interfaces.remote.IInformazioniProdottoRemote;
import gameshop.advance.interfaces.remote.IIteratorWrapperRemote;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Lorenzo Di Giuseppe
 */
public interface IRemoteFornitureClient extends Remote
{

    void setInformazioniProdotto(IIteratorWrapperRemote<IInformazioniProdottoRemote> iter) throws RemoteException;
    
}
