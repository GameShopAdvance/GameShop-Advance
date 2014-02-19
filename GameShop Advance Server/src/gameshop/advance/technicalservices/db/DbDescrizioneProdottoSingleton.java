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
import gameshop.advance.interfaces.IDescrizioneProdotto;
import gameshop.advance.model.DescrizioneProdotto;
import gameshop.advance.utility.IDProdotto;
import java.util.Iterator;

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
            client.store(SmartProxyFactorySingleton.getIstance().creaProxyDescrizioneProdotto(desc));
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
        IDescrizioneProdotto desc = SmartProxyFactorySingleton.getIstance().creaProxyDescrizioneProdotto(null);
        return client.queryByExample(desc.getClass()).iterator();
    }
    
    public IDescrizioneProdotto read(IDProdotto code) {
        ObjectContainer client = DbManagerSingleton.getInstance().getClient();
        Query query=client.query();
        IDescrizioneProdotto desc = SmartProxyFactorySingleton.getIstance().creaProxyDescrizioneProdotto(null);
        query.constrain(desc.getClass());
        query.descend("codiceProdotto").constrain(code);
        ObjectSet results = query.execute();
        if(results.isEmpty())
            return null;
        return (DescrizioneProdotto) results.get(0);
    }
}

