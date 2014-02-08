
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
import gameshop.advance.ui.swing.UIWindowSingleton;
import gameshop.advance.ui.swing.employee.EmployeeMenuPanel;
import gameshop.advance.ui.swing.employee.InventoryPanel;
import gameshop.advance.utility.IDProdotto;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.LinkedList;
import javax.swing.JComponent;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class InventoryControllerSingleton extends UnicastRemoteObject implements IRemoteDescriptionClient {
    
    private static InventoryControllerSingleton instance;
    private IInventarioControllerRemote controller;
    private HashMap<String, AggiuntaProdotti> addedItems;
    
    private InventoryControllerSingleton() throws RemoteException
    {
        this.addedItems = new HashMap<>();
    }
    
    public static InventoryControllerSingleton getInstance() throws RemoteException, ConfigurationException, NotBoundException
    {
        try {
                instance = new InventoryControllerSingleton();
                instance.configure();
            } catch (RemoteException | NotBoundException ex) {
                instance = null;
                throw new RemoteException("Ci sono problemi di comunicazione con il server.");
                //Instanziare un logger per tener traccia delle eccezioni e consentire la loro analisi
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
        aggiornaWindow(new InventoryPanel());
    }
    
    public void inserisciProdotto(String codiceProdotto, int quantity) throws RemoteException, QuantityException, ProdottoNotFoundException
    {
        IDProdotto id = new IDProdotto(codiceProdotto);
        this.controller.inserisciProdotto(id, quantity);
        this.addedItems.put(id.getCodice(), new AggiuntaProdotti(null, quantity));
    }
    
    public void terminaInventario() throws RemoteException
    {
        this.controller.terminaInventario();
    }

    @Override
    public void addDescription(IDescrizioneProdottoRemote desc) throws RemoteException{
        AggiuntaProdotti aggiunta = this.addedItems.get(desc.getCodiceProdotto().getCodice());
        if(aggiunta != null)
        {
            aggiunta.setDescription(desc);
            this.addedItems.put(desc.getCodiceProdotto().getCodice(), aggiunta);
        }
        else
            this.addedItems.put(desc.getCodiceProdotto().getCodice(), new AggiuntaProdotti(desc, 0));
    }

    public LinkedList<AggiuntaProdotti> getDescriptions() throws RemoteException {
        return (LinkedList<AggiuntaProdotti>) this.addedItems.values();
    }

    public void cancel() throws RemoteException {
        this.controller.cancel();
        UIWindowSingleton.getInstance().setPanel(new EmployeeMenuPanel());
    }
    
    public void endInventory() throws RemoteException {
        this.controller.terminaInventario();
        UIWindowSingleton.getInstance().setPanel(new EmployeeMenuPanel());
    }
    
    private void aggiornaWindow(JComponent panel) {
        UIWindowSingleton.getInstance().setPanel(panel);
        UIWindowSingleton.getInstance().refreshContent();
    }
}

