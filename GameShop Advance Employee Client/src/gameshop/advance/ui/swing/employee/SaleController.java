
package gameshop.advance.ui.swing.employee;

import gameshop.advance.config.ConfigurationControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.exceptions.ProdottoNotFoundException;
import gameshop.advance.exceptions.QuantityException;
import gameshop.advance.interfaces.remote.ICassaRemote;
import gameshop.advance.interfaces.remote.IRemoteClient;
import gameshop.advance.interfaces.remote.IRemoteFactory;
import gameshop.advance.interfaces.remote.IRemoteObserver;
import gameshop.advance.model.vendita.SaleObserver;
import gameshop.advance.model.vendita.SaleRestObserver;
import gameshop.advance.ui.swing.UIWindowSingleton;
import gameshop.advance.utility.IDProdotto;
import gameshop.advance.utility.Money;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.JPanel;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class SaleController extends UnicastRemoteObject implements IRemoteClient {
    
    private static SaleController instance;
    private ICassaRemote cassa;
    private IRemoteObserver saleTotalObserver;
    private IRemoteObserver saleRestObserver;
    private Money totale;
    private Money resto;
    
    private SaleController() throws RemoteException
    {}

    private void configure() throws ConfigurationException, RemoteException, NotBoundException {
          
        ConfigurationControllerSingleton controllerConfig = ConfigurationControllerSingleton.getInstance();

        Registry reg = LocateRegistry.getRegistry(controllerConfig.getServerAddress(), controllerConfig.getServerPort());
        
        IRemoteFactory factory = (IRemoteFactory) reg.lookup("RemoteFactory");
        this.cassa = factory.creaCassa(controllerConfig.getIdCassa());
        this.saleTotalObserver = new SaleObserver(instance);
        this.saleRestObserver = new SaleRestObserver(instance);
    }
    
    public static SaleController getInstance() throws NullPointerException, RemoteException, ConfigurationException
    {
        if(instance==null)
        {
            try {
                instance = new SaleController();
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
        this.cassa.inserisciTesseraCliente(1);
        aggiornaWindow(new InsertItemPanel());
    }
    
    public void inserisciProdotto(String productID, int quantity) throws RemoteException, ProdottoNotFoundException, QuantityException
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
    
    public void effettuaPagamento(Double payment) throws RemoteException, InvalidMoneyException
    {
        cassa.aggiungiListener(this.saleRestObserver);
        cassa.gestisciPagamento(new Money(payment));
        aggiornaWindow(new EndSalePanel());
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
        this.resto = m;
    }
    
    private void aggiornaWindow(JPanel panel) {
        UIWindowSingleton.getInstance().setPanel(panel);
        UIWindowSingleton.getInstance().refreshContent();
    }
    
    public Money getTotal()
    {
        return this.totale;
    }
    
    public Money getResto()
    {
        return this.resto;
    }

    void clearSale() {
        //da implementare
    }
    
}
