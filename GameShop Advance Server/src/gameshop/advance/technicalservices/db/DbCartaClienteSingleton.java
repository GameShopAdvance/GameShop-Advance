/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.technicalservices.db;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;
import gameshop.advance.exceptions.ObjectAlreadyExistsDbException;
import gameshop.advance.model.vendita.CartaCliente;

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
        System.err.println("PreQuery");
        CartaCliente example = new CartaCliente(carta.getId());
        int exist = client.queryByExample(example).size();
        if(exist > 0)
            throw new ObjectAlreadyExistsDbException();
        System.err.println("Salvataggio in corso!");
        client.store(carta);
        DbManagerSingleton.getInstance().printObjects(CartaCliente.class);
        client.close();
    }
    
    public CartaCliente read(int id)
    {
        ObjectContainer client = DbManagerSingleton.getInstance().getClient();
        Query query=client.query();
        query.constrain(CartaCliente.class);
        System.err.println("Setting costrain on Cliente");
        query.descend("codice").constrain(id);
        System.err.println("Setting descend on Cliente");
        CartaCliente cliente = (CartaCliente) query.execute().get(0);
        System.err.println("Cliente recuperato: "+cliente.getId()+" "+cliente.getTipo().getNome());
        return cliente;
    }
    
}
