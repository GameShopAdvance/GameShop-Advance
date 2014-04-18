package gameshop.advance.remote;


import gameshop.advance.controller.AnalizzaFornitureController;
import gameshop.advance.controller.GestisciInventarioController;
import gameshop.advance.controller.PrenotaProdottoController;
import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.interfaces.remote.factory.ICassaRemote;
import gameshop.advance.interfaces.remote.factory.IFornitureControllerRemote;
import gameshop.advance.interfaces.remote.factory.IInventarioControllerRemote;
import gameshop.advance.interfaces.remote.factory.IProdottiRemote;
import gameshop.advance.interfaces.remote.factory.IRemoteFactory;
import gameshop.advance.model.Cassa;
import gameshop.advance.model.ProdottiFacade;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

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
     /**
     * Crea un'istanza di un'oggetto remoto per gestire l'inventario.
     * @return GestisciInventarioController
     * @throws RemoteException
     */
    @Override
    public IInventarioControllerRemote getGestisciInventarioController() throws RemoteException{
        System.err.println("Factory's creating controller of inventory");
        try{
            IInventarioControllerRemote inventario = new GestisciInventarioController();
            return inventario;
        } catch (RemoteException ex) {
            Logger.getLogger(RemoteFactorySingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     /**
     * Crea un'istanza di un'oggetto remoto necessario a gestire le prenotazioni
     * 
     * @return renotaProdottoController
     * @throws RemoteException
     */
    @Override
    public PrenotaProdottoController getPrenotaProdottoController() throws RemoteException{
        System.err.println("Creazione controller della prenotazione");
        try{
            PrenotaProdottoController prenota = new PrenotaProdottoController();
            return prenota;
        } catch (RemoteException ex) {
            Logger.getLogger(RemoteFactorySingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    /**
     * Crea un'istanza di un'oggetto remoto necessario al manager ad analizzare le forniture del negozio
     * 
     * @return AnalizzaFornitureController
     * @throws RemoteException
     */
    @Override
    public IFornitureControllerRemote getAnalizzaFornitureController() throws RemoteException {
        return new AnalizzaFornitureController();
    }
    /**
     * Crea un'istanza di un'oggetto remoto che serve ad ottenere le descrizioni prodotto presenti nel catalogo del negozio
     * 
     * @return ProdottiFacade
     * @throws RemoteException
     */
    @Override
    public IProdottiRemote getProdottiFacade() throws RemoteException {
        
        return new ProdottiFacade();
    }
}
