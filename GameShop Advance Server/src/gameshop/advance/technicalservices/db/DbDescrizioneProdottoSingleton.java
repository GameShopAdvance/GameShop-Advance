/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.technicalservices.db;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.collections.ActivatableLinkedList;
import com.db4o.query.Predicate;
import gameshop.advance.exceptions.ObjectAlreadyExistsDbException;
import gameshop.advance.interfaces.IDescrizioneProdotto;
import gameshop.advance.model.DescrizioneProdottoSmartProxy;
import gameshop.advance.utility.IDProdotto;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author matteog
 */
public class DbDescrizioneProdottoSingleton {
    
    private static DbDescrizioneProdottoSingleton instance;
    
    private DbDescrizioneProdottoSingleton(){};
        
    public static DbDescrizioneProdottoSingleton getInstance()
    {
        if( instance == null)
            instance = new DbDescrizioneProdottoSingleton();

         return instance;
    }
        
    public void create(IDescrizioneProdotto desc) throws ObjectAlreadyExistsDbException{
        ObjectContainer client = DbManagerSingleton.getInstance().getClient();
        int result = client.queryByExample(desc).size();
        if(result > 0)
            throw new ObjectAlreadyExistsDbException();
        if(desc.getClass() != DescrizioneProdottoSmartProxy.class)
            client.store(new DescrizioneProdottoSmartProxy(desc));
        else
            client.store(desc);
        client.commit();
    }
    
    //metodo provvisorio
    public void update(IDescrizioneProdotto desc) throws ObjectAlreadyExistsDbException{
        ObjectContainer client = DbManagerSingleton.getInstance().getClient();
        client.store(desc);
        client.commit();
    }
    
    public Iterator<Object> read()
    {
        ObjectContainer client = DbManagerSingleton.getInstance().getClient();
        return client.queryByExample(DescrizioneProdottoSmartProxy.class).iterator();
    }
    
    public IDescrizioneProdotto read(final IDProdotto code) {
        ObjectContainer client = DbManagerSingleton.getInstance().getClient();
        List<DescrizioneProdottoSmartProxy> result = client.query(new Predicate<DescrizioneProdottoSmartProxy>() {
            @Override
            public boolean match(DescrizioneProdottoSmartProxy candidate) {
               IDProdotto codice;
               try {
                   codice = candidate.getCodiceProdotto();
               }
               catch (RemoteException ex) {
                   return false;
               }
               if(codice.getCodice().equals(code.getCodice()))
                   return true;
               else
                   return false;
           }
       });
       if(result.isEmpty())
           return null;
       IDescrizioneProdotto desc = result.get(0);
       client.ext().refresh(desc, 10);
       return desc;  
   }
    
    public LinkedList<IDescrizioneProdotto> readBelowThreshold(){
        ObjectContainer client = DbManagerSingleton.getInstance().getClient();
        ObjectSet<IDescrizioneProdotto> result = client.query(new Predicate<IDescrizioneProdotto>() {

            @Override
            public boolean match(IDescrizioneProdotto candidate) {
                if(candidate.getClass() != DescrizioneProdottoSmartProxy.class)
                    return false;
                else
                   return !candidate.sottoSoglia();
            }
        });
        if(result.isEmpty())
           return null;
        else{
            LinkedList<IDescrizioneProdotto> list = new ActivatableLinkedList<>(result);
            client.ext().refresh(list, 15);
            return list;
        }
    }
}

       