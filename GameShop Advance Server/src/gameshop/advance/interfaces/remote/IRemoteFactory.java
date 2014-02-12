package gameshop.advance.interfaces.remote;

import gameshop.advance.controller.PrenotaProdottoController;
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
    
    IInventarioControllerRemote getGestisciInventarioController()  throws RemoteException;
    
    PrenotaProdottoController getPrenotaProdottoController() throws RemoteException;
}
