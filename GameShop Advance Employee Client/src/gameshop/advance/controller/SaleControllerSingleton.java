
package gameshop.advance.controller;

import gameshop.advance.config.ConfigurationControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.exceptions.ProdottoNotFoundException;
import gameshop.advance.exceptions.QuantityException;
import gameshop.advance.exceptions.QuantityNotInStockException;
import gameshop.advance.interfaces.remote.ICassaRemote;
import gameshop.advance.interfaces.remote.IRemoteClient;
import gameshop.advance.interfaces.remote.IRemoteFactory;
import gameshop.advance.interfaces.remote.IRemoteObserver;
import gameshop.advance.observer.TotalObserver;
import gameshop.advance.observer.RestObserver;
import gameshop.advance.ui.swing.UIWindowSingleton;
import gameshop.advance.ui.swing.employee.EmployeeMenuPanel;
import gameshop.advance.ui.swing.employee.sale.EndSalePanel;
import gameshop.advance.ui.swing.employee.sale.InsertItemPanel;
import gameshop.advance.ui.swing.employee.sale.PaymentPanel;
import gameshop.advance.utility.IDProdotto;
import gameshop.advance.utility.Money;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class SaleControllerSingleton extends UnicastRemoteObject implements IRemoteClient {
    
    private static SaleControllerSingleton instance;
    private ICassaRemote cassa;
    private IRemoteObserver saleTotalObserver;
    private IRemoteObserver saleRestObserver;
    private Money totale;
    private Money resto;
    
    private SaleControllerSingleton() throws RemoteException
    {
        
    }

    private void configure() throws ConfigurationException, RemoteException, NotBoundException {
          
        ConfigurationControllerSingleton controllerConfig = ConfigurationControllerSingleton.getInstance();

        Registry reg = LocateRegistry.getRegistry(controllerConfig.getServerAddress(), controllerConfig.getServerPort());
        
        IRemoteFactory factory = (IRemoteFactory) reg.lookup("RemoteFactory");
        this.cassa = factory.creaCassa(controllerConfig.getIdCassa());
        this.saleTotalObserver = new TotalObserver(instance);
        this.saleRestObserver = new RestObserver(instance);
    }
    
    public static SaleControllerSingleton getInstance() throws NullPointerException, RemoteException, ConfigurationException
    {
        if(instance==null)
        {
            try {
                instance = new SaleControllerSingleton();
                instance.configure();
            } catch (RemoteException | NotBoundException ex) {
                instance = null;
                throw new RemoteException("Ci sono problemi di comunicazione con il server.");
                //Instanziare un logger per tener traccia delle eccezioni e consentire la loro analisi
            }
        }
        
        return instance;
    }
    
    public void avviaNuovaVendita() throws RemoteException
    {
        this.cassa.avviaNuovaVendita();
        this.cassa.aggiungiListener(this.saleTotalObserver);
        aggiornaWindow(new InsertItemPanel());
    }
    
    public void inserisciProdotto(String productID, int quantity) throws RemoteException, ProdottoNotFoundException, QuantityException, QuantityNotInStockException
    {
        if(quantity < 1)
            throw new QuantityException("La quantità selezionata non è valida.");
        IDProdotto id = new IDProdotto(productID);
        this.cassa.inserisciProdotto(id, quantity);
    }
    
    /**
     *
     * @throws java.rmi.RemoteException
     */
    public void concludiVendita() throws RemoteException
    {
        this.aggiornaWindow(new PaymentPanel());
        this.cassa.concludiVendita();
    }
    
    public void effettuaPagamento(Double payment) throws RemoteException
    {
        try{
            cassa.aggiungiListener(this.saleRestObserver);
            cassa.gestisciPagamento(new Money(payment));
            aggiornaWindow(new EndSalePanel());
        }
        catch (Exception ex) {
            Logger.getLogger(SaleControllerSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void inserisciCartaCliente(int code) throws RemoteException
    {
        cassa.inserisciTesseraCliente(code);
    }
    
    /**
     *
     * @param m
     */
    @Override
    public void aggiornaTotale(Money m)
    {
        this.totale = m;
    }
    
    @Override
    public void aggiornaResto(Money m)
    {
        System.err.println("Resto "+m);
        this.resto = m;
    }
    
    private void aggiornaWindow(JComponent panel) {
        UIWindowSingleton.getInstance().setPanel(panel);
        UIWindowSingleton.getInstance().refreshContent();
    }
    
    public Money getTotal()
    {
        return this.totale;
    }
    
    public Money getResto()
    {
        System.err.println("Resto "+this.resto.toString());
        return this.resto;
    }

    public void clearSale() {
        UIWindowSingleton.getInstance().setPanel(new EmployeeMenuPanel());
        UIWindowSingleton.getInstance().refreshContent();
    }

}
