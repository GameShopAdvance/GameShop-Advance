/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces.remote.forniture;

import gameshop.advance.interfaces.remote.utility.IIteratorWrapperRemote;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Lorenzo Di Giuseppe
 */
public interface IFornitureManagerRemote extends Remote
{

    IIteratorWrapperRemote<IInformazioniProdottoRemote> getInformazioni() throws RemoteException;
    
}