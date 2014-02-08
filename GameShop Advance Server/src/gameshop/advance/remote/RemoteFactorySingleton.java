package gameshop.advance.remote;


import gameshop.advance.controller.GestisciInventarioController;
import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.interfaces.remote.ICassaRemote;
import gameshop.advance.interfaces.remote.IInventarioControllerRemote;
import gameshop.advance.interfaces.remote.IRemoteFactory;
import gameshop.advance.model.Cassa;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * La Factory è utilizzata per la creazione delle casse:impedisce che una stessa cassa venga
 * instanziata più volte.Dato che tale Factory deve essere unica, è stata implementata come
 * Singleton.
 * @author Salx
 */
public class RemoteFactorySingleton extends UnicastRemoteObject implements IRemoteFactory {

    private static RemoteFactorySingleton factory;

    /**
     * Il Costruttore della classe.Non implementa nulla.
     */
    private RemoteFactorySingleton() throws RemoteException
    {}

    /**
     * Impedisce la creazione di più oggetti RemoteFactorySIngleton, dato che tale classe
     * è stata implementata come Singleton.
     * @return
     * @throws RemoteException
     */
    public static synchronized RemoteFactorySingleton getIstance() throws RemoteException {
        if(RemoteFactorySingleton.factory == null)
        {
            RemoteFactorySingleton.factory = new RemoteFactorySingleton();
        }
        System.err.println("Get this factory!");
        return RemoteFactorySingleton.factory;
    }

    /**
     * Crea un'istanza di un'oggetto cassa remoto.Inizialmente chiede a negozio di 
     * restituire la cassa il cui id sia quello ricevuto in input:se il negozio non 
     * restituisce nessuna cassa allora la Factory ne crea una con l'id ricevuto in 
     * input e aggiunge tale cassa all'elenco delle casse del negozio;se il negozio 
     * invece restituisce la casa, allora la Factory ritorna l'oggetto restituitogli
     * da negozio.Impedisce quindi di creare una stessa cassa più volte.
     * @param numeroCassa
     * @return
     * @throws RemoteException
     */
    @Override
    public ICassaRemote creaCassa(int numeroCassa) throws RemoteException {
        try {
            RemoteTerminalSingleton terminals;
            terminals = RemoteTerminalSingleton.getInstance();

            Cassa cassa = terminals.getCassa(numeroCassa);
            if (cassa == null){
                cassa = new Cassa(numeroCassa);
                terminals.addCassa(cassa);
            }
            System.err.println("Cassa: "+cassa);
            return cassa;
        } catch (InvalidMoneyException ex) {
            throw new RemoteException(ex.getMessage());
        }
    }
    
    @Override
    public IInventarioControllerRemote getGestisciInventarioController() throws RemoteException{
        System.err.println("Factory's creating controller of inventory");
        IInventarioControllerRemote inventario = new GestisciInventarioController();
        return inventario;
    }
}
