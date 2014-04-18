package gameshop.advance.interfaces.remote.factory;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaccia remota per l'esportazione della classe factory che la implementano tramite
 * Java RMI, Consente di esportare i metodi che a loro volta sonon necessari ad ottenere un controller associato a un caso d'uso. 
 * 
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
    /** Ritorna il controller necessario all'analisi delle forniture
     *
     * @return IFornitureControllerRemote
     * @throws RemoteException
     */
    IFornitureControllerRemote getAnalizzaFornitureController() throws RemoteException;
    /** Ritorna il controller necessario a chiunque servano le descrizioni
     *
     * @return IProdottoRemote
     * @throws RemoteException
     */
    IProdottiRemote getProdottiFacade() throws RemoteException;
}
