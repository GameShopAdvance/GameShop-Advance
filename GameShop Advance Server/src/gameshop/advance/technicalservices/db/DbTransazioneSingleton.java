/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.technicalservices.db;

import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;
import gameshop.advance.exceptions.ObjectAlreadyExistsDbException;
import gameshop.advance.interfaces.ITransazione;
import gameshop.advance.model.transazione.decorator.TransazioneDecorator;
import java.util.List;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class DbTransazioneSingleton {
    
    private static DbTransazioneSingleton instance;
    
    private DbTransazioneSingleton()
    {
        
    }
    
    public static DbTransazioneSingleton getInstance()
    {
        if(DbTransazioneSingleton.instance == null)
            DbTransazioneSingleton.instance = new DbTransazioneSingleton();
        return DbTransazioneSingleton.instance;
    }
    
    public void create(TransazioneDecorator sale) throws ObjectAlreadyExistsDbException
    {
        ObjectContainer client = DbManagerSingleton.getInstance().getClient();
        int exist = client.queryByExample(sale).size();
        if(exist > 0)
            throw new ObjectAlreadyExistsDbException();
        client.store(sale);
        client.commit();
    }
    
    public TransazioneDecorator read(final Integer id)
    {
        ObjectContainer client = DbManagerSingleton.getInstance().getClient();
        List<TransazioneDecorator> result = client.query(new Predicate<TransazioneDecorator>() {

            @Override
            public boolean match(TransazioneDecorator candidate) {
                ITransazione trans = candidate.unwrap();
                if(trans.getId().intValue() == id.intValue())
                    return true;
                else
                    return false;
            }
        });
        if(result.isEmpty())
            return null;
        return result.get(0);
    }
    
    public Integer count()
    {
        ObjectContainer client = DbManagerSingleton.getInstance().getClient();
        return client.queryByExample(TransazioneDecorator.class).size();
    }
}
