/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.technicalservices.db;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import gameshop.advance.exceptions.ObjectAlreadyExistsDbException;
import gameshop.advance.interfaces.IPrenotazione;
import gameshop.advance.model.transazione.proxies.PrenotazioneSmartProxy;
import java.rmi.RemoteException;

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
}
