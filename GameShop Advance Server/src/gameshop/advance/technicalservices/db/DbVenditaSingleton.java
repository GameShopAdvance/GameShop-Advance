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
        client.store(SmartProxyFactorySingleton.getIstance().creaProxyVendita(sale));
        client.commit();
    }
    
    public ITransazione read(final Integer id)
    {
        ObjectContainer client = DbManagerSingleton.getInstance().getClient();
        List<ITransazione> result = client.query(new Predicate<ITransazione>() {
            @Override
            public boolean match(ITransazione candidate) {
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
        client.activate(trans, 11);
        return trans;
    }
    
    public Integer count()
    {
        ObjectContainer client = DbManagerSingleton.getInstance().getClient();
        ITransazione trans = SmartProxyFactorySingleton.getIstance().creaProxyVendita(null);
        return client.queryByExample(trans.getClass()).size();
    }
}
