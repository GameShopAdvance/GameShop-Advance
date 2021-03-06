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
 * Classe che si occupa della persistenza della configurazione del client.Salva e recupera dal db
 * l'oggetto Configuration.
 * @author Salx
 */
public class DbConfigurationSingleton {
    
    private static DbConfigurationSingleton instance;
    
    private ObjectContainer db;
    
    private final String DbName = "./configuration.db";
    
    private DbConfigurationSingleton(){
        this.db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), this.DbName);
    }

    /**
     * Metodo che ritorna l'istanza della classe Singleton.
     * @return instance
     */
    public synchronized static DbConfigurationSingleton getInstance(){
        
        if (instance == null){
            
            instance = new DbConfigurationSingleton();
        }
        
        return instance;
    }

    /**
     * Salva l'oggetto Configuration sul db.
     * @param config la configurazione da salvare
     */
    public void store (Configuration config) {
        db.store(config);
        db.commit();
    }
    
    /**
     * Recupera dal db l'oggetto Configuration.
     * @return la configurazione salvata 
     */
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
