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
import gameshop.advance.model.transazione.proxies.TransazioneSmartProxy;
import java.rmi.RemoteException;
import java.util.List;

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
    
    public void create(ITransazione sale) throws ObjectAlreadyExistsDbException
    {
        ObjectContainer client = DbManagerSingleton.getInstance().getClient();
        int exist = client.queryByExample(sale).size();
        if(exist > 0)
            throw new ObjectAlreadyExistsDbException();
        if(sale.getClass() != TransazioneSmartProxy.class)
            client.store(new TransazioneSmartProxy(sale));
        else
            client.store(sale);
        client.commit();
    }
    
    public ITransazione read(final Integer id)
    {
        ObjectContainer client = DbManagerSingleton.getInstance().getClient();
        List<TransazioneSmartProxy> result = client.query(new Predicate<TransazioneSmartProxy>() {
            @Override
            public boolean match(TransazioneSmartProxy candidate) {
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
        ITransazione trans = result.get(0);
        return trans;
    }
    
    public Integer count()
    {
        ObjectContainer client = DbManagerSingleton.getInstance().getClient();
        return client.queryByExample(TransazioneSmartProxy.class).size();
    }
}