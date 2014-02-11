/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.technicalservices.db;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;
import gameshop.advance.exceptions.ObjectAlreadyExistsDbException;
import gameshop.advance.interfaces.ITransazione;
import gameshop.advance.model.transazione.decorator.VenditaTransazioneDecorator;

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
    
    public void create(VenditaTransazioneDecorator sale) throws ObjectAlreadyExistsDbException
    {
        ObjectContainer client = DbManagerSingleton.getInstance().getClient();
        int exist = client.queryByExample(sale).size();
        if(exist > 0)
            throw new ObjectAlreadyExistsDbException();
        client.store(sale);
        client.commit();
    }
    
    public ITransazione read(Integer id)
    {
        ObjectContainer client = DbManagerSingleton.getInstance().getClient();
        Query query=client.query();
        query.constrain(VenditaTransazioneDecorator.class);
        query.descend("id").constrain(id);
        return (VenditaTransazioneDecorator) query.execute().get(0);
    }
    
    public Integer count()
    {
        ObjectContainer client = DbManagerSingleton.getInstance().getClient();
        return client.queryByExample(VenditaTransazioneDecorator.class).size();
    }
}
