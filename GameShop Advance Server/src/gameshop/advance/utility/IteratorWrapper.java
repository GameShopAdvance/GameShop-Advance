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

/** Classe che consente di Wrappare un iteratore
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 * @param <T1>
 */
public class IteratorWrapper<T1> extends UnicastRemoteObject implements IIteratorWrapperRemote<T1>, Iterator<T1> {
    private Iterator<T1> iter;
    
    public IteratorWrapper(Iterator<T1> iter) throws RemoteException
    {
        this.iter = iter;
    }
    
    @Override
    public boolean hasNext()
    {
        return iter.hasNext();
    }
    
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
