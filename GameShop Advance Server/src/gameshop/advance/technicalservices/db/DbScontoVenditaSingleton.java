/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.technicalservices.db;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;
import gameshop.advance.exceptions.ObjectAlreadyExistsDbException;
import gameshop.advance.interfaces.IScontoVenditaStrategy;
import gameshop.advance.interfaces.IVendita;

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
        System.err.println("PreQuery");
        int exist = client.queryByExample(sconto).size();
        System.err.println("Salvataggio in corso!");
        if(exist > 0)
            throw new ObjectAlreadyExistsDbException();
        client.store(sconto);
        DbManagerSingleton.getInstance().printObjects(IScontoVenditaStrategy.class);
        client.close();
    }
    
    public IVendita read(Integer id)
    {
        ObjectContainer client = DbManagerSingleton.getInstance().getClient();
        Query query=client.query();
        query.constrain(IVendita.class);
        query.descend("id").constrain(id);
        return (IVendita) query.execute().get(0);
    }
    
}
