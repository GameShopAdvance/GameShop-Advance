package gameshop.advance.observer;

import gameshop.advance.interfaces.remote.IRemoteClient;
import gameshop.advance.interfaces.remote.IRemoteObserver;
import gameshop.advance.interfaces.remote.ITransazioneRemote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Osservatore del totale da pagare di una transazione.
 * @author Salx
 */
public class TotalObserver extends UnicastRemoteObject implements IRemoteObserver {
    

    private IRemoteClient client;
    
    /**
     * @param client
     * @throws RemoteException
     */
    public TotalObserver(IRemoteClient client) throws RemoteException {
        this.client = client;
    }
    
    /**
     * @param o
     * @throws RemoteException
     */
    @Override
    public void notifica(Object o) throws RemoteException{
        ITransazioneRemote vendita = (ITransazioneRemote) o;
        System.err.println("Observer del totale, client: "+this.client);
        this.client.aggiornaTotale(vendita.getTotal());
    }

}
