/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces.remote.factory;

import gameshop.advance.interfaces.remote.IDescrizioneProdottoRemote;
import gameshop.advance.interfaces.remote.utility.IIteratorWrapperRemote;
import java.rmi.Remote;
import java.rmi.RemoteException;

/** 
 * Interfaccia remota per l'esportazione dei metodi utili ad ottenere i prodotti presenti in catalogo 
 * 
 * @author Salx
 */
public interface IProdottiRemote extends Remote {
/** 
 * @return 
 * @throws java.rmi.RemoteException
 */
    public IIteratorWrapperRemote<IDescrizioneProdottoRemote> getDescrizioni()throws RemoteException;
    
}
