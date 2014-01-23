package gameshop.advance.model.vendita;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.interfaces.remote.IRemoteClient;
import gameshop.advance.interfaces.remote.IRemoteObserver;
import gameshop.advance.interfaces.remote.IVenditaRemote;
import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * Il SaleObserver osserva i cambi di stato della vendita ed in particolare
 * le variazioni del totale di vendita.Invia poi queste informazioni ad un client
 * che si occuperà di farle visualizzare.
 * @author Salx
 */
public class SaleObserver implements IRemoteObserver, Serializable {
    

    private IRemoteClient client;
    
    /**
     * Il Costruttore utilizza il riferimento a IRemoteClient ricevuto in ingresso
     * per salvare il client al quale l'osservatore invierà informazioni riguardanti
     * la vendita a cui è associato.
     * @param client
     * @throws RemoteException
     */
    public SaleObserver(IRemoteClient client) throws RemoteException {
        this.client = client;
    }
    
    /**
     * Invia al client il totale della vendita ogni volta che la vendita notifica
     * all'osservatore che il suo stato è cambiato.
     * @param o
     * @throws RemoteException
     */
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
