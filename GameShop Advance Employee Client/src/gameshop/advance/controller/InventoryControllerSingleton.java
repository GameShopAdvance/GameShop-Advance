
package gameshop.advance.controller;

import gameshop.advance.config.ConfigurationControllerSingleton;
import gameshop.advance.controller.valueData.AggiuntaProdotti;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.exceptions.QuantityException;
import gameshop.advance.exceptions.products.ProdottoNotFoundException;
import gameshop.advance.interfaces.remote.IDescrizioneProdottoRemote;
import gameshop.advance.interfaces.remote.IRemoteDescriptionClient;
import gameshop.advance.interfaces.remote.factory.IInventarioControllerRemote;
import gameshop.advance.interfaces.remote.factory.IRemoteFactory;
import gameshop.advance.interfaces.remote.utility.IRemoteObserver;
import gameshop.advance.observer.DescriptionsObserver;
import gameshop.advance.ui.swing.UIWindowSingleton;
import gameshop.advance.ui.swing.employee.EmployeeMenuPanel;
import gameshop.advance.ui.swing.employee.InventoryPanel;
import gameshop.advance.utility.IDProdotto;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.HashMap;
import javax.swing.JComponent;

/**
 * Controller lato client delle operazioni di gestione dell'inventario.
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class InventoryControllerSingleton extends UnicastRemoteObject implements IRemoteDescriptionClient {
    
    private static InventoryControllerSingleton instance;
    private IInventarioControllerRemote controller;
    private HashMap<String, AggiuntaProdotti> addedItems;
    private IRemoteObserver observer;
    
    private InventoryControllerSingleton() throws RemoteException
    {
        this.addedItems = new HashMap<>();
        this.observer = new DescriptionsObserver(this);
        System.err.println("observer: "+this.observer);
    }
    
    /**
     * @return istanza di InventoryControllerSingleton
     * @throws RemoteException
     * @throws ConfigurationException
     * @throws NotBoundException
     */
    public static InventoryControllerSingleton getInstance() throws RemoteException, ConfigurationException, NotBoundException
    {
        if(instance == null)
        {
            try {
                instance = new InventoryControllerSingleton();
                instance.configure();
            } catch (RemoteException | NotBoundException ex) {
                instance = null;
                throw new RemoteException("Ci sono problemi di comunicazione con il server.");
                //Instanziare un logger per tener traccia delle eccezioni e consentire la loro analisi
            }
        }
        
        return instance;
    }
    
    /**
     * @throws ConfigurationException
     * @throws RemoteException
     * @throws NotBoundException
     */
    private void configure() throws ConfigurationException, RemoteException, NotBoundException
    {
        ConfigurationControllerSingleton controllerConfig = ConfigurationControllerSingleton.getInstance();
        Registry reg = LocateRegistry.getRegistry(controllerConfig.getServerAddress(), controllerConfig.getServerPort());
        IRemoteFactory factory = (IRemoteFactory) reg.lookup("RemoteFactory");
        this.controller = factory.getGestisciInventarioController();
        System.err.println("Controller: " +this.controller);
    }
    
    /**
     * @throws ConfigurationException
     * @throws RemoteException
     * @throws NotBoundException
     */
    public void avviaInventario() throws ConfigurationException, RemoteException, NotBoundException
    {
        this.controller.avviaInventario();
        this.controller.aggiungiListener(this.observer);
        aggiornaWindow(new InventoryPanel());
    }
    
    /**
     * @param codiceProdotto
     * @param quantity
     * @throws RemoteException
     * @throws QuantityException
     * @throws ProdottoNotFoundException
     */
    public void inserisciProdotto(String codiceProdotto, int quantity) throws RemoteException, QuantityException, ProdottoNotFoundException
    {
        IDProdotto id = new IDProdotto(codiceProdotto);
        try{
            System.err.println("Pre aggiunta hash :"+this.addedItems.size());
            this.addedItems.put(id.getCodice(), new AggiuntaProdotti(null, quantity));
            System.err.println("Post aggiunta hash :"+this.addedItems.size());
            this.controller.inserisciProdotto(id, quantity);
        }catch(ProdottoNotFoundException | QuantityException e)
        {
            System.err.println("Err aggiunta hash");
            this.addedItems.remove(id.getCodice());
            throw e;
        }
        
    }
    
    /**
     * @throws RemoteException
     */
    public void terminaInventario() throws RemoteException
    {
        this.controller.rimuoviListener(this.observer);
        this.controller.terminaInventario();
        this.aggiornaWindow(new EmployeeMenuPanel());
    }

    /**
     * @param desc
     * @throws RemoteException
     */
    @Override
    public void addDescription(IDescrizioneProdottoRemote desc) throws RemoteException{
        System.err.println("OBSERVER CALL");
        AggiuntaProdotti aggiunta = this.addedItems.get(desc.getCodiceProdotto().getCodice());
        if(aggiunta != null)
        {
            aggiunta.setDescription(desc);
            this.addedItems.put(desc.getCodiceProdotto().getCodice(), aggiunta);
        }
        else
            this.addedItems.put(desc.getCodiceProdotto().getCodice(), new AggiuntaProdotti(desc, 0));
    }

    /**
     * @return valori degli oggetti appena inseriti
     * @throws RemoteException
     */
    public Collection<AggiuntaProdotti> getDescriptionList() throws RemoteException {
        return this.addedItems.values();
    }

    /**
     * @throws RemoteException
     */
    public void cancel() throws RemoteException {
        this.controller.cancel();
        this.aggiornaWindow(new EmployeeMenuPanel());
    }
     
    private void aggiornaWindow(JComponent panel) {
        UIWindowSingleton.getInstance().setPanel(panel);
        UIWindowSingleton.getInstance().refreshContent();
    }
}

