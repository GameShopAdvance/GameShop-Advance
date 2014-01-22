/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.technicalservices.db;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;
import gameshop.advance.exceptions.ObjectAlreadyExistsDbException;
import gameshop.advance.model.vendita.Vendita;
import gameshop.advance.remote.interfaces.IVenditaRemote;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class DbVenditaSingleton {
    
    private static DbVenditaSingleton instance;
    
    private DbVenditaSingleton()
    {
        
    }
    
    public static DbVenditaSingleton getInstance()
    {
        if(DbVenditaSingleton.instance == null)
            DbVenditaSingleton.instance = new DbVenditaSingleton();
        return DbVenditaSingleton.instance;
    }
    
    public void create(IVenditaRemote sale) throws ObjectAlreadyExistsDbException
    {
        ObjectContainer client = DbManagerSingleton.getInstance().getClient();
        System.err.println("PreQuery");
        int exist = client.queryByExample(sale).size();
        System.err.println("Salvataggio in corso!");
        if(exist > 0)
            throw new ObjectAlreadyExistsDbException();
        client.store(sale);
        DbManagerSingleton.getInstance().printObjects(Vendita.class);
        client.close();
    }
    
    public IVenditaRemote read(Integer id)
    {
        ObjectContainer client = DbManagerSingleton.getInstance().getClient();
        Query query=client.query();
        query.constrain(IVenditaRemote.class);
        query.descend("id").constrain(id);
        return (IVenditaRemote) query.execute().get(0);
    }
    
}
