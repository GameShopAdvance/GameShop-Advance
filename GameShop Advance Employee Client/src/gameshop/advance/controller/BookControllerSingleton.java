/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.controller;

import gameshop.advance.config.ConfigurationControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.interfaces.remote.ICassaRemote;
import gameshop.advance.interfaces.remote.IPrenotaProdottoRemote;
import gameshop.advance.interfaces.remote.IRemoteClient;
import gameshop.advance.interfaces.remote.IRemoteFactory;
import gameshop.advance.interfaces.remote.IRemoteObserver;
import gameshop.advance.observer.SaleObserver;
import gameshop.advance.observer.SaleRestObserver;
import gameshop.advance.ui.swing.UIWindowSingleton;
import gameshop.advance.ui.swing.employee.BookPanel;
import gameshop.advance.ui.swing.employee.EmployeeMenuPanel;
import gameshop.advance.utility.Money;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.JComponent;

/**
 *
 * @author Salx
 */
public class BookControllerSingleton  extends UnicastRemoteObject implements IRemoteClient{
    
    private static BookControllerSingleton instance;
    private IPrenotaProdottoRemote controller;
    private ICassaRemote cassa;
    private IRemoteObserver saleTotalObserver;
    private IRemoteObserver saleRestObserver;
    
    private BookControllerSingleton() throws RemoteException{
        
    }
    
    private void configure() throws ConfigurationException, RemoteException, NotBoundException {
          
        ConfigurationControllerSingleton controllerConfig = ConfigurationControllerSingleton.getInstance();
        Registry reg = LocateRegistry.getRegistry(controllerConfig.getServerAddress(), controllerConfig.getServerPort()); 
        IRemoteFactory factory = (IRemoteFactory) reg.lookup("RemoteFactory");
        this.controller = factory.getPrenotaProdottoController();
        this.cassa = factory.creaCassa(controllerConfig.getIdCassa());
        this.saleTotalObserver = new SaleObserver(instance);
        this.saleRestObserver = new SaleRestObserver(instance);
        
    }
    
    
    public static BookControllerSingleton getInstance() throws NullPointerException, RemoteException, ConfigurationException{
        
        try {
               instance = new BookControllerSingleton();
               instance.configure();
            } catch (RemoteException | NotBoundException ex) {
               instance = null;
               throw new RemoteException("Ci sono problemi di comunicazione con il server.");
               //Instanziare un logger per tener traccia delle eccezioni e consentire la loro analisi
            }
        return instance;
    }
    
    public void gestisciPrenotazione() throws RemoteException{
        aggiornaWindow(new BookPanel());
    }
    
    public void recuperaPrenotazione(int codicePrenotazione) throws RemoteException {
        
        this.controller.recuperaPrenotazione(codicePrenotazione);
    }
    
    public void terminaPrenotazione() throws RemoteException {
        
        this.controller.terminaPrenotazione();
    }
    
    private void aggiornaWindow(JComponent panel) {
        UIWindowSingleton.getInstance().setPanel(panel);
        UIWindowSingleton.getInstance().refreshContent();
    }
    
    public void clearPrenotazione() {
        UIWindowSingleton.getInstance().setPanel(new EmployeeMenuPanel());
        UIWindowSingleton.getInstance().refreshContent();
    }

    @Override
    public void aggiornaTotale(Money m) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void aggiornaResto(Money m) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
