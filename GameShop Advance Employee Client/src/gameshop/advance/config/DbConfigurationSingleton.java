package gameshop.advance.config;


import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

/**
 * Classe che si occupa della persistenza degli oggetti configuration.
 * @author Salx
 */
public class DbConfigurationSingleton {
    
    private static DbConfigurationSingleton instance;
    
    private ObjectContainer db;
    
    private final String DbName = "./configuration.db";
    
    /**
     * Inizializza il db.
     */
    private DbConfigurationSingleton(){
        this.db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), this.DbName);
    }

    /**
     * @return istanza di DbConfigurationSingleton
     */
    public synchronized static DbConfigurationSingleton getInstance(){
        
        if (instance == null){
            
            instance = new DbConfigurationSingleton();
        }
        
        return instance;
    }

    /**
     * Salva configuration sul db.
     * @param config
     */
    public void store (Configuration config) {
        db.store(config);
        db.commit();
    }
    
    /**
     * @return configuration dal db
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
