/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.controller;

import gameshop.advance.config.ConfigurationControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.interfaces.IListPanel;
import gameshop.advance.interfaces.remote.IDescrizioneProdottoRemote;
import gameshop.advance.interfaces.remote.factory.IProdottiRemote;
import gameshop.advance.interfaces.remote.factory.IRemoteFactory;
import gameshop.advance.interfaces.remote.utility.IIteratorWrapperRemote;
import gameshop.advance.ui.swing.lists.models.ProductListModel;
import gameshop.advance.ui.swing.lists.renderer.ProductCellRenderer;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe Controller che si occupa della gestione dei prodotti sul client Customer.
 * @author Lorenzo Di Giuseppe
 */
public class ProductsControllerSingleton
{
    private static ProductsControllerSingleton instance;
    private IProdottiRemote controller;
    private ProductListModel listaDescrizioni;
    
    private ProductsControllerSingleton()
    {
        this.listaDescrizioni = new ProductListModel();
    }
    
    /**
     * Metodo che si occupa della configurazione degli attributi di classe e della comunicazione RMI.
     * @throws ConfigurationException
     * @throws RemoteException
     * @throws NotBoundException
     */
    private void configure() throws ConfigurationException, RemoteException, NotBoundException 
     {    
        ConfigurationControllerSingleton controllerConfig = ConfigurationControllerSingleton.getInstance();
        Registry reg = LocateRegistry.getRegistry(controllerConfig.getServerAddress(), controllerConfig.getServerPort());
        IRemoteFactory factory = (IRemoteFactory) reg.lookup("RemoteFactory");
        this.controller = factory.getProdottiFacade();
        this.aggiornaDescrizioni();
    }
    
    /**
     * Metodo che ritorna l'istanza della classe Singleton.
     * @return
     * @throws RemoteException
     */
    public static ProductsControllerSingleton getInstance() throws RemoteException
    {
        if(instance==null)
        {
            try {
                instance = new ProductsControllerSingleton();
                instance.configure();
            } catch (RemoteException | NotBoundException ex) {
                instance = null;
                Logger.getLogger(ProductsControllerSingleton.class.getName()).log(Level.SEVERE, null, ex);
                throw new RemoteException("Ci sono problemi di comunicazione con il server.");
            } catch (ConfigurationException ex)
            {
                Logger.getLogger(ProductsControllerSingleton.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return instance;
    }
    
    /**
     * @param panel
     */
    public void setDescriptions(IListPanel panel)
    {
        panel.setList(this.listaDescrizioni, new ProductCellRenderer());
    }
    
    /**
     * @param index
     * @return
     */
    public IDescrizioneProdottoRemote getProduct(int index){
        return this.listaDescrizioni.getElementAt(index);
    }
    
    /**
     * Aggiorna la lista di descrizioni prodotto che ha come attributo.
     * @throws RemoteException
     */
    public void aggiornaDescrizioni() throws RemoteException
    {
        IIteratorWrapperRemote<IDescrizioneProdottoRemote> iter = this.controller.getDescrizioni();
        while(iter.hasNext())
        {
            IDescrizioneProdottoRemote desc = iter.next();
            this.listaDescrizioni.addElement(desc);
        }
    }
}
