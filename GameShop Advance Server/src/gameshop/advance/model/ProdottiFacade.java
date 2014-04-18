/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model;

import gameshop.advance.interfaces.remote.IDescrizioneProdottoRemote;
import gameshop.advance.interfaces.remote.factory.IProdottiRemote;
import gameshop.advance.interfaces.remote.utility.IIteratorWrapperRemote;
import gameshop.advance.remote.DescrizioneRemoteProxy;
import gameshop.advance.utility.IteratorWrapper;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;
import java.util.LinkedList;

/** Facade che rende disponibili le descrizioni a chiunque implementi l'interfaccia remota di IProdottiRemote
 *  
 * @author Salx
 */
public class ProdottiFacade extends UnicastRemoteObject implements IProdottiRemote {
    
    public ProdottiFacade() throws RemoteException{
    }
    /** 
    * @return L'iterator sulle descrizioni
    * @throws java.rmi.RemoteException
    */
    @Override
    public IIteratorWrapperRemote<IDescrizioneProdottoRemote> getDescrizioni() throws RemoteException {
        
        Iterator<Object> iter = CatalogoProdottiSingleton.getInstance().getDescrizioni();
        LinkedList<IDescrizioneProdottoRemote> list = new LinkedList<>();
        while(iter.hasNext())
        {
            list.add(new DescrizioneRemoteProxy((IDescrizioneProdottoRemote) iter.next()));
        }
        return new IteratorWrapper<> (list.iterator());
    }
    
}
