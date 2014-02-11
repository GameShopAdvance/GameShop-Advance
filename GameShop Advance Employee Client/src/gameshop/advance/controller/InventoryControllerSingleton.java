
package gameshop.advance.controller;

import gameshop.advance.config.ConfigurationControllerSingleton;
import gameshop.advance.controller.valueData.AggiuntaProdotti;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.exceptions.ProdottoNotFoundException;
import gameshop.advance.exceptions.QuantityException;
import gameshop.advance.interfaces.remote.IDescrizioneProdottoRemote;
import gameshop.advance.interfaces.remote.IInventarioControllerRemote;
import gameshop.advance.interfaces.remote.IRemoteDescriptionClient;
import gameshop.advance.interfaces.remote.IRemoteFactory;
import gameshop.advance.interfaces.remote.IRemoteObserver;
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
 *
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
    
    private void configure() throws ConfigurationException, RemoteException, NotBoundException
    {
        ConfigurationControllerSingleton controllerConfig = ConfigurationControllerSingleton.getInstance();
        Registry reg = LocateRegistry.getRegistry(controllerConfig.getServerAddress(), controllerConfig.getServerPort());
        IRemoteFactory factory = (IRemoteFactory) reg.lookup("RemoteFactory");
        this.controller = factory.getGestisciInventarioController();
        System.err.println("Controller: " +this.controller);
    }
    
    public void avviaInventario() throws ConfigurationException, RemoteException, NotBoundException
    {
        this.controller.avviaInventario();
        this.controller.aggiungiListener(this.observer);
        aggiornaWindow(new InventoryPanel());
    }
    
    public void inserisciProdotto(String codiceProdotto, int quantity) throws RemoteException, QuantityException, ProdottoNotFoundException
    {
        IDProdotto id = new IDProdotto(codiceProdotto);
        try{
            if(!this.addedItems.containsKey(id.getCodice()))
                this.addedItems.put(id.getCodice(), new AggiuntaProdotti(null, quantity));
            else
            {
                AggiuntaProdotti ap = this.addedItems.get(id.getCodice());
                ap.setAddedQuantity(ap.getAddedQuantity()+quantity);
            }
            this.controller.inserisciProdotto(id, quantity);
        }catch(ProdottoNotFoundException | QuantityException e)
        {
            System.err.println("Err aggiunta hash");
            this.addedItems.remove(id.getCodice());
            throw e;
        }
        
    }
    
    public void terminaInventario() throws RemoteException
    {
        this.controller.rimuoviListener(this.observer);
        this.controller.terminaInventario();
        this.aggiornaWindow(new EmployeeMenuPanel());
    }

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

    public Collection<AggiuntaProdotti> getDescriptionList() throws RemoteException {
        return this.addedItems.values();
    }

    public void cancel() throws RemoteException {
        this.controller.cancel();
        this.aggiornaWindow(new EmployeeMenuPanel());
    }
     
    private void aggiornaWindow(JComponent panel) {
        UIWindowSingleton.getInstance().setPanel(panel);
        UIWindowSingleton.getInstance().refreshContent();
    }
}

