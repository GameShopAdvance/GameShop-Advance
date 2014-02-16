/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.controller;

import gameshop.advance.config.ConfigurationControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.exceptions.ProdottoNotFoundException;
import gameshop.advance.interfaces.remote.IDescrizioneProdottoRemote;
import gameshop.advance.interfaces.remote.IIteratorWrapperRemote;
import gameshop.advance.interfaces.remote.IPrenotaProdottoRemote;
import gameshop.advance.interfaces.remote.IRemoteFactory;
import gameshop.advance.ui.swing.UIWindowSingleton;
import gameshop.advance.ui.swing.customer.CustomerPanel;
import gameshop.advance.ui.swing.customer.ReservationPanel;
import gameshop.advance.utility.IDProdotto;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;

/**
 *
 * @author Matteo Gentile
 */
public class PrenotaProdottoController extends UnicastRemoteObject implements Serializable{

    private static PrenotaProdottoController instance;
    private IPrenotaProdottoRemote controller;
    private HashMap<String, Integer> reserved_items;
   

    public PrenotaProdottoController() throws RemoteException {
        this.reserved_items = new HashMap<>();
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
       aggiornaWindow(new ReservationPanel());
    }
    
    public IIteratorWrapperRemote<IDescrizioneProdottoRemote> getDescriptionList() throws RemoteException {
         return this.controller.getDescriptions();
    }

    public void clearReservation() {      
        UIWindowSingleton.getInstance().setPanel(new CustomerPanel());
        UIWindowSingleton.getInstance().refreshContent();
    }

    public void inserisciProdotti(Object codiceP, int quantity) {
            this.reserved_items.put(codiceP.toString(), quantity);
    }

    public void completaPrenotazione() throws RemoteException {
        Iterator it = this.reserved_items.entrySet().iterator();
        while (it.hasNext()) {
            try {
                Map.Entry pairs = (Map.Entry) it.next();
                this.controller.prenotaProdotto(new IDProdotto((String) pairs.getKey()),(int) pairs.getValue());
                System.out.println("Prodotto: "+ pairs.getKey() + " Quantità = " + pairs.getValue());
            } catch (ProdottoNotFoundException ex) {
                Logger.getLogger(PrenotaProdottoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.controller.terminaPrenotazione();
    }
 
}
