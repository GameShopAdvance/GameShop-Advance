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
import gameshop.advance.interfaces.remote.IProdottiRemote;
import gameshop.advance.interfaces.remote.IRemoteFactory;
import gameshop.advance.ui.interfaces.ListPanel;
import gameshop.advance.ui.swing.customer.ProductCellRenderer;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.DefaultListModel;

/**
 *
 * @author Lorenzo Di Giuseppe
 */
public class ProductsControllerSingleton
{
    private static ProductsControllerSingleton instance;
    private IProdottiRemote controller;
    private DefaultListModel<IDescrizioneProdottoRemote> listaDescrizioni;
    
    private ProductsControllerSingleton()
    {
    }
    
    private void configure() throws ConfigurationException, RemoteException, NotBoundException 
     {    
        ConfigurationControllerSingleton controllerConfig = ConfigurationControllerSingleton.getInstance();
        Registry reg = LocateRegistry.getRegistry(controllerConfig.getServerAddress(), controllerConfig.getServerPort());
        IRemoteFactory factory = (IRemoteFactory) reg.lookup("RemoteFactory");
        this.controller = factory.getProdottiController();
    }
    
    public static ProductsControllerSingleton getInstance()
    {
        if(instance == null)
            instance = new ProductsControllerSingleton();
        return instance;
    }
    
    public void setDescriptions(ListPanel panel)
    {
        panel.setList(this.listaDescrizioni, new ProductCellRenderer());
    }
    
    public void aggiornaDescrizioni()
    {
        IIteratorWrapperRemote<IDescrizioneProdottoRemote> iter = this.controller.getDescrizioni();
        while(iter.hasNext())
        {
            this.listaDescrizioni.addElement(iter.next());
        }
    }
}
