/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.technicalservices.db;

import com.db4o.ObjectContainer;
import gameshop.advance.exceptions.ObjectAlreadyExistsDbException;
import gameshop.advance.model.DescrizioneProdotto;

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
        
    public void create(DescrizioneProdotto desc) throws ObjectAlreadyExistsDbException{
            ObjectContainer client = DbManagerSingleton.getInstance().getClient();
            int result = client.queryByExample(desc).size();
            if(result > 0)
                throw new ObjectAlreadyExistsDbException();
            client.store(desc);
            client.commit();
            client.close();
            DbManagerSingleton.getInstance().printObjects(DescrizioneProdotto.class);
    }
    
    
    public void read() throws ObjectAlreadyExistsDbException {
            ObjectContainer client = DbManagerSingleton.getInstance().getClient();
            
    }
}

