package gameshop.advance.model.vendita;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.interfaces.remote.IRemoteClient;
import gameshop.advance.interfaces.remote.IRemoteObserver;
import gameshop.advance.interfaces.remote.IVenditaRemoteDecorator;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Salx
 */
public class SaleObserver extends UnicastRemoteObject implements IRemoteObserver {
    

    private IRemoteClient client;
    
    public SaleObserver(IRemoteClient client) throws RemoteException {
        this.client = client;
    }
    
    @Override
    public void notifica(IVenditaRemoteDecorator o)throws RemoteException{
        IVenditaRemoteDecorator vendita = (IVenditaRemoteDecorator) o;
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
