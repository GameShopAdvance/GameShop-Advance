/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.utility;

import gameshop.advance.interfaces.remote.utility.IIteratorWrapperRemote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;

/**
 * Classe che implementa gli IteratorWrappe e definisce tutte le operazioni applicabili.
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 * @param <T1>
 */
public class IteratorWrapper<T1> extends UnicastRemoteObject implements IIteratorWrapperRemote<T1>, Iterator<T1> {
    
    private Iterator<T1> iter;
    
    /**
     * @param iter
     * @throws RemoteException
     */
    public IteratorWrapper(Iterator<T1> iter) throws RemoteException
    {
        this.iter = iter;
    }
    
    /**
     * @return
     */
    @Override
    public boolean hasNext()
    {
        return iter.hasNext();
    }
    
    /**
     * @return
     */
    @Override
    public T1 next()
    {
        return iter.next();
    }
    
    @Override
    public void remove()
    {
        iter.remove();
    }
}
