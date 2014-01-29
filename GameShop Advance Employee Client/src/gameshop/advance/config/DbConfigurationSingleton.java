/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.config;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.query.Query;
import gameshop.advance.employee.Configuration;

/**
 *
 * @author Salx
 */
public class DbConfigurationSingleton {
    
    private static DbConfigurationSingleton instance;
    private final String DbName = "src/gameshop/advance/config/configuration.db";
    
    private DbConfigurationSingleton(){
    } 
    
    public synchronized static DbConfigurationSingleton getInstance(){
        
        if (instance == null){
            
            instance = new DbConfigurationSingleton();
        }
        
        return instance;
    }
    
   
    public void create (Configuration config) {
   
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), this.DbName);
        System.err.println("Salvataggio in corso!");
        db.store(config);
        System.out.print("Object Stored: "+ config);
        db.close();
    }
    
    public Configuration read(){
        
        Configuration config;
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), this.DbName);
        Query query = db.query();
        query.constrain(Configuration.class);
        config = (Configuration) query.execute().get(0);
        return config;
    }
    
    public void delete() {
    
        Configuration config;
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), this.DbName);
        Query query = db.query();
        config = (Configuration) query.execute().get(0);
        db.delete(config);
        db.close();
        
    }
    
    
}
