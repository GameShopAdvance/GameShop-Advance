
package gameshop.advance.technicalservices.db;

import com.db4o.ObjectContainer;
import com.db4o.ObjectServer;
import com.db4o.ObjectSet;
import com.db4o.cs.Db4oClientServer;
import com.db4o.cs.config.ServerConfiguration;
import com.db4o.query.Query;
import com.db4o.ta.TransparentActivationSupport;
import gameshop.advance.interfaces.IScontoProdottoStrategy;
import gameshop.advance.model.DescrizioneProdotto;
import gameshop.advance.model.DescrizioneProdottoSmartProxy;
import gameshop.advance.model.transazione.proxies.TransazioneSmartProxy;
import java.util.Iterator;
import java.util.List;
import org.joda.time.DateTime;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class DbManagerSingleton {
    
    private static DbManagerSingleton instance;
    
    private final String dbName = "./GSA.db";
    
    private final ObjectServer server;
    
    
    private ObjectContainer client;
    
    
    private DbManagerSingleton()
    {
        ServerConfiguration configuration = Db4oClientServer.newServerConfiguration();
        configuration.common().add(new TransparentActivationSupport());
        configuration.common().objectClass(DescrizioneProdottoSmartProxy.class).cascadeOnUpdate(true);
        configuration.common().objectClass(TransazioneSmartProxy.class).cascadeOnUpdate(true);

        this.server = Db4oClientServer.openServer(configuration, this.dbName, 0);

        this.client = this.server.openClient();
    }
    
    public synchronized static DbManagerSingleton getInstance()
    {
        if(instance == null)
            instance = new DbManagerSingleton();
        
        return instance;
    }
    
    public ObjectContainer getClient()
    {
        if(this.client == null)
            this.client = this.server.openClient();
        return this.client;
    }
    
    public void close()
    {
        this.client.close();
        this.client = null;
    }
    
    public void printObjects(Class c)
    {
        ObjectContainer client = this.server.openClient();
        Query query = client.query();
        query.constrain(DescrizioneProdotto.class);
        ObjectSet list = query.execute();
        Iterator iter = list.iterator();
        int i = 1;
        while(iter.hasNext())
        {
            DescrizioneProdotto desc = (DescrizioneProdotto) iter.next();
            List<IScontoProdottoStrategy> sconti = desc.getTuttiSconti();
            for(IScontoProdottoStrategy sconto:sconti)
            {
                System.out.println("Sconto: "+sconto.getClass()+" valid: "+sconto.isValid(DateTime.now()));
            }
            i++;
        }
        client.close();
    }
}

