package gameshop.advance.interfaces.remote.factory;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaccia remota per l'esportazione delle classi factory che la implementano tramite
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
    
    /** Ritorna il controller necessario all'inventario
     *
     * @return IInventarioControllerRemote
     * @throws RemoteException
     */
    IInventarioControllerRemote getGestisciInventarioController()  throws RemoteException;
    
    /** Ritorna il controller necessario alla prenotazione
     *
     * @return IPrenotaProdottoRemote
     * @throws RemoteException
     */
    IPrenotaProdottoRemote getPrenotaProdottoController() throws RemoteException;
    
    IFornitureControllerRemote getAnalizzaFornitureController() throws RemoteException;
    
    IProdottiRemote getProdottiFacade() throws RemoteException;
}
