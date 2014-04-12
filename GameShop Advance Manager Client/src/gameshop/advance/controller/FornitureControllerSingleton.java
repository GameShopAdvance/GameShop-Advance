/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.controller;

import gameshop.advance.config.ConfigurationControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.exceptions.QuantityException;
import gameshop.advance.interfaces.remote.IFornitureControllerRemote;
import gameshop.advance.interfaces.remote.IInformazioniProdottoRemote;
import gameshop.advance.interfaces.remote.IIteratorWrapperRemote;
import gameshop.advance.interfaces.remote.IRemoteFactory;
import gameshop.advance.ui.swing.UIWindowSingleton;
import gameshop.advance.ui.swing.manager.FornitureMenu;
import gameshop.advance.ui.swing.manager.ManagerMenu;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JComponent;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class FornitureControllerSingleton {
    
    private static FornitureControllerSingleton instance;
    private IFornitureControllerRemote controller;
    
    private FornitureControllerSingleton()
    {
    
    }
    
    public static FornitureControllerSingleton getInstance() throws ConfigurationException, RemoteException
    {
        if(instance == null)
        {
            try {
                instance = new FornitureControllerSingleton();
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
        this.controller = factory.getAnalizzaFornitureController();
    }
    
    private void aggiornaWindow(JComponent panel) 
    {
        UIWindowSingleton.getInstance().setPanel(panel);
        UIWindowSingleton.getInstance().refreshContent();
    }
    
    public void avviaGestioneForniture() throws RemoteException, QuantityException{
        //UIWindowSingleton.getInstance().setPanel(new FornitureMenu());
        FornitureMenu panel = new FornitureMenu();
        this.aggiornaWindow(panel);
        
        IIteratorWrapperRemote<IInformazioniProdottoRemote> iter = this.controller.getDatiForniture();
        
        while(iter.hasNext()) {
           panel.addInfoProduct(iter.next());
       }
    }
    
    public void clearForniture() {
        UIWindowSingleton.getInstance().setPanel(new ManagerMenu());
        UIWindowSingleton.getInstance().refreshContent();
    }
}
