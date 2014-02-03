/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.technicalservices.db;

import com.db4o.ObjectContainer;
import com.db4o.ObjectServer;
import com.db4o.ObjectSet;
import com.db4o.cs.Db4oClientServer;
import com.db4o.query.Query;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class DbManagerSingleton {
    
    private static DbManagerSingleton instance;
    
    private final String dbName = "src/gameshop/advance/technicalservices/db/GSA.db";
    
    private final ObjectServer server;
    
    private HashMap<Long, ObjectContainer>  clients;
    
    
    private DbManagerSingleton()
    {
        System.err.println("SERVER DB OPENING");
        this.server = Db4oClientServer.openServer(Db4oClientServer.newServerConfiguration(), this.dbName, 0);
        System.err.println("SERVER DB: "+this.server);
        this.clients = new HashMap<>();
        Thread t = Thread.currentThread();
        
        System.out.println("Thread id: "+t.getId());
    }
    
    public synchronized static DbManagerSingleton getInstance()
    {
        if(instance == null)
            instance = new DbManagerSingleton();
        
        return instance;
    }
    
    public ObjectContainer getClient()
    {
        Thread t = Thread.currentThread();
        ObjectContainer client = this.clients.get(t.getId());
        if(client == null)
        {
            client = this.server.openClient();
            this.clients.put(t.getId(), client);
        }
        System.err.println("Thread di esecuzione client: "+t.getId());
        return client;
    }
    
    public void printObjects(Class c)
    {
        ObjectContainer client = this.server.openClient();
        Query query = client.query();
        query.constrain(c);
        ObjectSet list = query.execute();
        Iterator iter = list.iterator();
        System.out.println("PRINTING OBJECTS IN DB");
        while(iter.hasNext())
        {
            System.err.println(c.getName()+": "+iter.next());
        }
        client.close();
    }
}
