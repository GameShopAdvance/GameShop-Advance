
package gameshop.advance.ui.swing.customer;

import gameshop.advance.config.ConfigurationControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.ui.swing.UIWindowSingleton;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.JComponent;

/**
 *
 * @author Matteo Gentile
 */
public class SearchControllerSingleton extends UnicastRemoteObject {
    
    private static SearchControllerSingleton instance;

    
    private SearchControllerSingleton() throws RemoteException
    {
        
    }

    private void configure() throws ConfigurationException, RemoteException, NotBoundException {
          
        ConfigurationControllerSingleton controllerConfig = ConfigurationControllerSingleton.getInstance();

        Registry reg = LocateRegistry.getRegistry(controllerConfig.getServerAddress(), controllerConfig.getServerPort());
        
    }
    
    public static SearchControllerSingleton getInstance() throws NullPointerException, RemoteException, ConfigurationException
    {
        if(instance==null)
        {
            try {
                instance = new SearchControllerSingleton();
                instance.configure();
            } catch (RemoteException | NotBoundException ex) {
                instance = null;
                throw new RemoteException("Ci sono problemi di comunicazione con il server.");
            }
        }
        
        return instance;
    }
    
    public void nuovaRicerca() throws RemoteException
    {   
       // this.pippo.avviaNuovaRicerca();
        aggiornaWindow(new SearchPanel());
    }
    
  
    
    private void aggiornaWindow(JComponent panel) {
        UIWindowSingleton.getInstance().setPanel(panel);
        UIWindowSingleton.getInstance().refreshContent();
    }
    
    
}
