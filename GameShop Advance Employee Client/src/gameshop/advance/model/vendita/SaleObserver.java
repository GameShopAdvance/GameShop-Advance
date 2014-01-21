package gameshop.advance.model.vendita;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.remote.interfaces.IRemoteClient;
import gameshop.advance.remote.interfaces.IRemoteObserver;
import gameshop.advance.remote.interfaces.IVenditaRemote;
import java.io.Serializable;
import java.rmi.RemoteException;

/**
 *
 * @author Salx
 */
public class SaleObserver implements IRemoteObserver, Serializable {
    

    private IRemoteClient client;
    
    public SaleObserver(IRemoteClient client) throws RemoteException {
        this.client = client;
    }
    
    @Override
    public void notifica(Object o)throws RemoteException{
        IVenditaRemote vendita = (IVenditaRemote) o;
        System.err.println("Observer del totale, client: "+this.client);
        try{
            this.client.aggiornaTotale(vendita.getTotal());
        }
        catch(InvalidMoneyException e)
        {
            throw new RemoteException(e.getMessage());
        }
    }

}
