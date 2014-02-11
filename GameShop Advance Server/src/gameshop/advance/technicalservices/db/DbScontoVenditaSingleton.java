package gameshop.advance.technicalservices.db;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;
import gameshop.advance.exceptions.ObjectAlreadyExistsDbException;
import gameshop.advance.interfaces.IScontoVenditaStrategy;
import gameshop.advance.interfaces.ITransazione;
import java.util.Iterator;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class DbScontoVenditaSingleton {
    
    private static DbScontoVenditaSingleton instance;
    
    private DbScontoVenditaSingleton()
    {
        
    }
    
    public static DbScontoVenditaSingleton getInstance()
    {
        if(DbScontoVenditaSingleton.instance == null)
            DbScontoVenditaSingleton.instance = new DbScontoVenditaSingleton();
        return DbScontoVenditaSingleton.instance;
    }
    
    public void create(IScontoVenditaStrategy sconto) throws ObjectAlreadyExistsDbException
    {
        ObjectContainer client = DbManagerSingleton.getInstance().getClient();
        int exist = client.queryByExample(sconto).size();
        if(exist > 0)
            throw new ObjectAlreadyExistsDbException();
        client.store(sconto);
        client.commit();
    }
    
    public ITransazione read(Integer id)
    {
        ObjectContainer client = DbManagerSingleton.getInstance().getClient();
        Query query=client.query();
        query.constrain(ITransazione.class);
        query.descend("id").constrain(id);
        return (ITransazione) query.execute().get(0);
    }
    
    public Iterator<Object> read()
    {
        ObjectContainer client = DbManagerSingleton.getInstance().getClient();
        Query query=client.query();
        query.constrain(IScontoVenditaStrategy.class);
        return query.execute().iterator();
    }
}
