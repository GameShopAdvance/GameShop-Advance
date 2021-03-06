/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.technicalservices.db;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import gameshop.advance.exceptions.db.ObjectAlreadyExistsDbException;
import gameshop.advance.model.transazione.CartaCliente;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class DbCartaClienteSingleton {

    private static DbCartaClienteSingleton instance;
    
    private DbCartaClienteSingleton()
    {
        
    }
    
    public static DbCartaClienteSingleton getInstance()
    {
        if(instance==null)
            instance = new DbCartaClienteSingleton();
        return instance;
    }
    
    public void create(CartaCliente carta) throws ObjectAlreadyExistsDbException
    {
        ObjectContainer client = DbManagerSingleton.getInstance().getClient();
        CartaCliente example = new CartaCliente(carta.getId());
        int exist = client.queryByExample(example).size();
        System.err.println("Clienti n."+exist);
        if(exist > 0)
            throw new ObjectAlreadyExistsDbException();
        client.store(carta);
        client.commit();
    }
    
    public CartaCliente read(int id)
    {
        ObjectContainer client = DbManagerSingleton.getInstance().getClient();
        Query query=client.query();
        query.constrain(CartaCliente.class);
        query.descend("codice").constrain(id);

        ObjectSet<CartaCliente> cliente = query.execute();
        if(cliente.isEmpty())
            return null;
        return cliente.get(0);
    }
    
}
