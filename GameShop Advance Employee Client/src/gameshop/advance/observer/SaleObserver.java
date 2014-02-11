package gameshop.advance.observer;

import gameshop.advance.interfaces.remote.IRemoteClient;
import gameshop.advance.interfaces.remote.IRemoteObserver;
import gameshop.advance.interfaces.remote.ITransazioneRemote;
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
    public void notifica(Object o) throws RemoteException{
        ITransazioneRemote vendita = (ITransazioneRemote) o;
        System.err.println("Observer del totale, client: "+this.client);
        this.client.aggiornaTotale(vendita.getTotal());
    }

}
