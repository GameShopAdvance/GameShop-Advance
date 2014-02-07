/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.config;


import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

/**
 *
 * @author Salx
 */
public class DbConfigurationSingleton {
    
    private static DbConfigurationSingleton instance;
    
    private ObjectContainer db;
    
    private final String DbName = "./configuration.db";
    
    private DbConfigurationSingleton(){
        this.db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), this.DbName);
    } 
    
    public synchronized static DbConfigurationSingleton getInstance(){
        
        if (instance == null){
            
            instance = new DbConfigurationSingleton();
        }
        
        return instance;
    }
    
   
    public void store (Configuration config) {
        System.err.println("Salvataggio in corso!");
        db.store(config);
        System.out.print("Object Stored: "+ config);
        db.commit();
    }
    
    public Configuration read(){
        Query query = this.db.query();
        query.constrain(Configuration.class);
        ObjectSet<Object> config = query.execute();
        Configuration result;
        if(config.isEmpty())
            result = null;
        else
            result = (Configuration) config.get(0);
        return result;
    }

}
