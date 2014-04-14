/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model;

import gameshop.advance.interfaces.remote.IDescrizioneProdottoRemote;
import gameshop.advance.interfaces.remote.IIteratorWrapperRemote;
import gameshop.advance.interfaces.remote.IProdottiRemote;
import gameshop.advance.remote.DescrizioneRemoteProxy;
import gameshop.advance.utility.IteratorWrapper;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Salx
 */
public class ProdottiFacade extends UnicastRemoteObject implements IProdottiRemote {
    
    public ProdottiFacade() throws RemoteException{
    }

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
