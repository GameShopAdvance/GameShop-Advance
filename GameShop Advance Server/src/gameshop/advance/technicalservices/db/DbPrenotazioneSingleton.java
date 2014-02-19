/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.technicalservices.db;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import gameshop.advance.exceptions.ObjectAlreadyExistsDbException;
import gameshop.advance.interfaces.IPrenotazione;

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
        client.store(SmartProxyFactorySingleton.getIstance().creaProxyPrenotazione(book));
        client.commit();
    }
    
     public IPrenotazione read(Integer id){
        ObjectContainer client = DbManagerSingleton.getInstance().getClient();
        Query query=client.query();
        query.constrain(IPrenotazione.class);
        query.descend("codiceProdotto").constrain(id);
        ObjectSet results = query.execute();
        if(results.isEmpty())
            return null;
        return (IPrenotazione) results.get(0);
    }
     
     public Integer count(){
        ObjectContainer client = DbManagerSingleton.getInstance().getClient();
        IPrenotazione pren = SmartProxyFactorySingleton.getIstance().creaProxyPrenotazione(null);
        return client.queryByExample(pren.getClass()).size();
    }
}
