/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.controller;

import gameshop.advance.config.ConfigurationControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.interfaces.remote.IDescrizioneProdottoRemote;
import gameshop.advance.interfaces.remote.IIteratorWrapperRemote;
import gameshop.advance.interfaces.remote.IPrenotaProdottoRemote;
import gameshop.advance.interfaces.remote.IRemoteFactory;
import gameshop.advance.ui.swing.UIWindowSingleton;
import gameshop.advance.ui.swing.customer.ReservationPanel;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.HashSet;
import javax.swing.JComponent;

/**
 *
 * @author matteog
 */
public class PrenotaProdottoController extends UnicastRemoteObject implements Serializable{

    private static PrenotaProdottoController instance;
    private IPrenotaProdottoRemote controller;
    private IIteratorWrapperRemote<IDescrizioneProdottoRemote> iter;
   

    public PrenotaProdottoController() throws RemoteException {
    }
    
     private void configure() throws ConfigurationException, RemoteException, NotBoundException {
          
        ConfigurationControllerSingleton controllerConfig = ConfigurationControllerSingleton.getInstance();
        Registry reg = LocateRegistry.getRegistry(controllerConfig.getServerAddress(), controllerConfig.getServerPort());
        IRemoteFactory factory = (IRemoteFactory) reg.lookup("RemoteFactory");
        this.controller = factory.getPrenotaProdottoController();
        System.err.println("Controller: " +this.controller);
        
    }
    
    public static PrenotaProdottoController getInstance() throws NullPointerException, RemoteException, ConfigurationException
    {
        if(instance==null)
        {
            try {
                instance = new PrenotaProdottoController();
                System.err.println("Instance:"+instance);
                instance.configure();
            } catch (RemoteException | NotBoundException ex) {
                instance = null;
                throw new RemoteException("Ci sono problemi di comunicazione con il server.");
            }
        }
        
        return instance;
    }
    
    
    private void aggiornaWindow(JComponent panel) {
        UIWindowSingleton.getInstance().setPanel(panel);
        UIWindowSingleton.getInstance().refreshContent();
    }

    
    public void avviaPrenotazione() throws RemoteException {
       this.controller.avviaPrenotazione();
       System.err.println("Avvia Prenotazione");
       aggiornaWindow(new ReservationPanel());
    }
    
    public Collection<IDescrizioneProdottoRemote> getDescriptionList() throws RemoteException {
         iter = this.controller.getDescriptions();
         Collection<IDescrizioneProdottoRemote> result = new HashSet<IDescrizioneProdottoRemote>();
        while(iter.hasNext()){
            result.add((IDescrizioneProdottoRemote) iter.next());
        }
        return result;
    }   
    
}
