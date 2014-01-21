/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.technicalservices.db;

import com.db4o.ObjectContainer;
import com.db4o.ObjectServer;
import com.db4o.cs.Db4oClientServer;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class DbManagerSingleton {
    
    private static DbManagerSingleton instance;
    
    private final String dbName = "./src/gameshop/advance/tecnicalservices/db/GSA.db";
    
    private ObjectServer server;
    
    
    
    private DbManagerSingleton()
    {
        this.server = Db4oClientServer.openServer(Db4oClientServer.newServerConfiguration(), this.dbName, 0);
    }
    
    public synchronized static DbManagerSingleton getInstance()
    {
        if(instance == null)
            instance = new DbManagerSingleton();
        
        return instance;
    }
    
    public ObjectContainer getClient()
    {
        return this.server.openClient();
    }
}
