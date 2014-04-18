/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.controller;

import gameshop.advance.config.ConfigurationControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.exceptions.QuantityException;
import gameshop.advance.interfaces.IListPanel;
import gameshop.advance.interfaces.remote.factory.IFornitureControllerRemote;
import gameshop.advance.interfaces.remote.factory.IRemoteFactory;
import gameshop.advance.interfaces.remote.forniture.IInformazioniProdottoRemote;
import gameshop.advance.interfaces.remote.forniture.IRemoteFornitureClient;
import gameshop.advance.interfaces.remote.utility.IIteratorWrapperRemote;
import gameshop.advance.interfaces.remote.utility.IRemoteObserver;
import gameshop.advance.manager.observer.FornitureObserver;
import gameshop.advance.manager.observer.FornitureRemoveObserver;
import gameshop.advance.technicalservices.LoggerSingleton;
import gameshop.advance.ui.swing.UIWindowSingleton;
import gameshop.advance.ui.swing.lists.models.FornitureListModel;
import gameshop.advance.ui.swing.lists.renderer.InfoCellRenderer;
import gameshop.advance.ui.swing.manager.FornitureMenu;
import gameshop.advance.ui.swing.manager.ManagerMenu;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.JComponent;

/**
 * Controller che gestisce tutte le operazioni riguardanti la gestione delle forniture.
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class FornitureControllerSingleton extends UnicastRemoteObject implements IRemoteFornitureClient {
    
    private static FornitureControllerSingleton instance;
    private IFornitureControllerRemote controller;
    
    private FornitureListModel listaForniture;
    private IRemoteObserver informationListener;
    private IRemoteObserver informationRemovedListener;
    
    private FornitureControllerSingleton() throws RemoteException
    {
        this.listaForniture = new FornitureListModel();
    }
    
    /**
     * @param iter
     * @throws RemoteException
     */
    @Override
    public void setInformazioniProdotto(IIteratorWrapperRemote<IInformazioniProdottoRemote> iter) throws RemoteException
    {
        while(iter.hasNext())
            this.listaForniture.addElement(iter.next());
    }
    
    /**
     * Metodo che ritorna l'istanza della classe Singleton.
     * @return
     * @throws ConfigurationException
     * @throws RemoteException
     */
    public static FornitureControllerSingleton getInstance() throws ConfigurationException, RemoteException
    {
        if(instance == null)
        {
            try {
                instance = new FornitureControllerSingleton();
                instance.configure();
            } catch (RemoteException | NotBoundException ex) {
                instance = null;
                LoggerSingleton.getInstance().log(ex);
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
        
        this.informationListener = new FornitureObserver(instance);
        this.informationRemovedListener = new FornitureRemoveObserver(instance);
        this.controller.addListener(this.informationListener);
        this.controller.addDeleteListener(this.informationRemovedListener);
    }
    
    private void aggiornaWindow(JComponent panel) 
    {
        UIWindowSingleton.getInstance().setPanel(panel);
        UIWindowSingleton.getInstance().refreshContent();
    }
    
    /**
     * @throws RemoteException
     * @throws QuantityException
     */
    public void avviaGestioneForniture() throws RemoteException, QuantityException{
        FornitureMenu panel = new FornitureMenu();
        this.aggiornaWindow(panel);
        
        IIteratorWrapperRemote<IInformazioniProdottoRemote> iter = this.controller.getDatiForniture();
        while(iter.hasNext()) {
           this.listaForniture.addElement(iter.next());
       }
    }
    
    public void clearForniture() {
        UIWindowSingleton.getInstance().setPanel(new ManagerMenu());
        UIWindowSingleton.getInstance().refreshContent();
    }

    /**
     * @param panel
     */
    public void setFornitureList(IListPanel panel)
    {
        panel.setList(this.listaForniture, new InfoCellRenderer());
    }

    /**
     * @param info
     * @throws RemoteException
     */
    @Override
    public void rimuoviInformazioneProdotto(IInformazioniProdottoRemote info) throws RemoteException {
        this.listaForniture.remove(info);
    }
}
