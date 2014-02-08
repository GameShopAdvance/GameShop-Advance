
package gameshop.advance.controller;

import gameshop.advance.config.ConfigurationControllerSingleton;
import gameshop.advance.controller.valueData.AggiuntaProdotti;
import gameshop.advance.exceptions.ConfigurationException;
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
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class InventoryControllerSingleton implements IRemoteDescriptionClient {
    
    private static InventoryControllerSingleton instance;
    private IInventarioControllerRemote controller;
    private HashMap<String, AggiuntaProdotti> addedItems;
    
    private InventoryControllerSingleton()
    {
        this.addedItems = new HashMap<>();
    }
    
    public static InventoryControllerSingleton getInstance()
    {
        if(instance == null)
            instance = new InventoryControllerSingleton();
        return instance;
    }
    
    public void avviaInventario() throws ConfigurationException, RemoteException, NotBoundException
    {
        ConfigurationControllerSingleton controllerConfig = ConfigurationControllerSingleton.getInstance();
        Registry reg = LocateRegistry.getRegistry(controllerConfig.getServerAddress(), controllerConfig.getServerPort());
        IRemoteFactory factory = (IRemoteFactory) reg.lookup("RemoteFactory");
        this.controller = factory.getGestisciInventarioController();
        UIWindowSingleton.getInstance().setPanel(new InventoryPanel());
    }
    
    public void inserisciProdotto(String codiceProdotto, int quantity) throws RemoteException
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

    public void cancel() {
        this.controller.cancel();
        UIWindowSingleton.getInstance().setPanel(new EmployeeMenuPanel());
    }
    
    public void endInventory() throws RemoteException {
        this.controller.terminaInventario();
        UIWindowSingleton.getInstance().setPanel(new EmployeeMenuPanel());
    }
}

