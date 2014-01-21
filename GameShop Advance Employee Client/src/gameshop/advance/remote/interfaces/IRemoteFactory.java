package gameshop.advance.remote.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaccia remota per l'esportazione delle classi che la implementano tramite
 * Java RMI.
 * @author Salx
 */
public interface IRemoteFactory extends Remote{

    /**
     *
     * @param numeroCassa
     * @return
     * @throws RemoteException
     */
    ICassaRemote creaCassa(int numeroCassa) throws RemoteException;
    
    
}
