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
import com.db4o.query.QueryComparator;
import gameshop.advance.exceptions.db.ObjectAlreadyExistsDbException;
import gameshop.advance.interfaces.IPrenotazione;
import gameshop.advance.model.transazione.proxies.PrenotazioneSmartProxy;
import java.rmi.RemoteException;
import java.util.LinkedList;
import org.joda.time.DateTime;

/**
 *
 * @author Salx
 */
public class DbPrenotazioneSingleton {
    
    private static DbPrenotazioneSingleton instance;
    
    private DbPrenotazioneSingleton(){
        
    }
    
    public static DbPrenotazioneSingleton getInstance(){
        
        if(DbPrenotazioneSingleton.instance == null)
            DbPrenotazioneSingleton.instance = new DbPrenotazioneSingleton();
        return DbPrenotazioneSingleton.instance;
    }
    
    public void create(IPrenotazione book) throws ObjectAlreadyExistsDbException
    {
        ObjectContainer client = DbManagerSingleton.getInstance().getClient();
        int exist = client.queryByExample(book).size();
        if(exist > 0)
            throw new ObjectAlreadyExistsDbException();
        if(book.getClass() != PrenotazioneSmartProxy.class)
            client.store(new PrenotazioneSmartProxy(book));
        else
            client.store(book);
        client.commit();
    }
    
     public IPrenotazione read(final Integer id)
     {
        ObjectContainer client = DbManagerSingleton.getInstance().getClient();
        ObjectSet<PrenotazioneSmartProxy> result = client.query(new Predicate<PrenotazioneSmartProxy>() {
            @Override
            public boolean match(PrenotazioneSmartProxy candidate) {
                Integer idTrans;
                try {
                    idTrans = candidate.getId();
                }
                catch (RemoteException ex) {
                    return false;
                }
                if(idTrans.intValue() == id.intValue())
                    return true;
                else
                    return false;
            }
        });
        if(result.isEmpty())
            return null;
        return (IPrenotazione) result.get(0);
    }
     
     public Integer count(){
        ObjectContainer client = DbManagerSingleton.getInstance().getClient();
        return client.queryByExample(PrenotazioneSmartProxy.class).size();
    }
     
     public LinkedList<IPrenotazione> readNotProcessed()
     {
         ObjectContainer client = DbManagerSingleton.getInstance().getClient();
         ObjectSet<IPrenotazione> result = client.query(
                 new Predicate<IPrenotazione>() 
                 {
                    @Override
                    public boolean match(IPrenotazione candidate) {
                        if(candidate.getClass() != PrenotazioneSmartProxy.class)
                            return false;
                        else
                           return !candidate.getEvasa();
                    }
                },
                new QueryComparator()
                {
                   public int compare(Object o1, Object o2)
                   {
                       IPrenotazione p1 = (IPrenotazione)o1;
                       DateTime date1 = p1.getDate();
                       IPrenotazione p2 = (IPrenotazione)o2;
                       DateTime date2 = p2.getDate();
                       return (date2.compareTo(date1));
                   }
                }
         );
         if(result.isEmpty())
            return new ActivatableLinkedList<>();
         else{
             LinkedList<IPrenotazione> list = new ActivatableLinkedList<>(result);
             return list;
         }
     }
}
