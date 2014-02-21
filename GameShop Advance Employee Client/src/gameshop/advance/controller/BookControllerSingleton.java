package gameshop.advance.controller;

import gameshop.advance.config.ConfigurationControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.interfaces.remote.IPrenotaProdottoRemote;
import gameshop.advance.interfaces.remote.IRemoteBookClient;
import gameshop.advance.interfaces.remote.IRemoteFactory;
import gameshop.advance.interfaces.remote.IRemoteObserver;
import gameshop.advance.observer.PartialObserver;
import gameshop.advance.observer.RestObserver;
import gameshop.advance.observer.RestPartialObserver;
import gameshop.advance.observer.TotalObserver;
import gameshop.advance.ui.swing.UIWindowSingleton;
import gameshop.advance.ui.swing.employee.EmployeeMenuPanel;
import gameshop.advance.ui.swing.employee.book.BookPanel;
import gameshop.advance.ui.swing.employee.book.BookTotalPaymentPanel;
import gameshop.advance.ui.swing.employee.book.EndBookPanel;
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
 * Controller lato client delle funzioni relative alle prenotazioni.
 * @author Salx
 */
public class BookControllerSingleton  extends UnicastRemoteObject implements IRemoteBookClient{
    
    private static BookControllerSingleton instance;
    private IPrenotaProdottoRemote controller;
    private IRemoteObserver bookRestObserver;
    private IRemoteObserver bookPartialObserver;
    private IRemoteObserver bookTotalObserver;
    private Money totale = new Money();
    private Money acconto = new Money();
    private Money resto = new Money();
    
    private BookControllerSingleton() throws RemoteException{
        
    }
    
    private void configure() throws ConfigurationException, RemoteException, NotBoundException {
          
        ConfigurationControllerSingleton controllerConfig = ConfigurationControllerSingleton.getInstance();
        Registry reg = LocateRegistry.getRegistry(controllerConfig.getServerAddress(), controllerConfig.getServerPort()); 
        IRemoteFactory factory = (IRemoteFactory) reg.lookup("RemoteFactory");
        this.controller = factory.getPrenotaProdottoController();
        this.bookPartialObserver = new PartialObserver(instance);
        this.bookTotalObserver = new TotalObserver(instance);
    }

    /**
     * @return istanza di BookControllerSingleton
     * @throws NullPointerException
     * @throws RemoteException
     * @throws ConfigurationException
     */
    public static BookControllerSingleton getInstance() throws NullPointerException, RemoteException, ConfigurationException{
        if(instance == null)
        {
            try {
               instance = new BookControllerSingleton();
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
     * Aggiorna i panel.
     * @throws RemoteException
     */
    public void gestisciPrenotazione() throws RemoteException{
        this.aggiornaWindow(new BookPanel());
    }
    
    /**
     * Avvia la funzione del recupero della prenotazione e aggiunge gli observer alla prenotazione.
     * @param codicePrenotazione
     * @throws RemoteException
     */
    public void recuperaPrenotazione(int codicePrenotazione) throws RemoteException {
        this.controller.recuperaPrenotazione(codicePrenotazione);
        this.controller.addListener(this.bookPartialObserver);
        this.controller.addListener(this.bookTotalObserver);
        this.controller.completaPrenotazione();
    }
    
    /**
     * @throws RemoteException
     */
    public void terminaPrenotazione() throws RemoteException {
        this.aggiornaWindow(new BookTotalPaymentPanel());
        this.controller.terminaPrenotazione();
    }
    
    public Money getTotal(){
        return this.totale;
    }
    
    public Money getPartial(){
        return this.acconto;
    }
    
    public Money getResto(){
        return this.resto;
    }
    
    /**
     * Funziona che avvia il pagamento dell'acconto di una prenotazione.
     * @param acconto
     * @throws RemoteException
     * @throws InvalidMoneyException
     */
    public void pagaAcconto(double acconto) throws RemoteException, InvalidMoneyException{
        try{
            this.bookRestObserver = new RestPartialObserver(instance);
            this.controller.addListener(this.bookRestObserver);
            this.controller.pagaAcconto(new Money(acconto));
            aggiornaWindow(new EndBookPanel());
        }
        catch (InvalidMoneyException | RemoteException ex) {
            Logger.getLogger(SaleControllerSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mostraPagaTotale(){
        aggiornaWindow(new BookTotalPaymentPanel());
    }
    
    public void mostraPagaAcconto(){
        aggiornaWindow(new BookTotalPaymentPanel());
    }
    
    /**
     * Funziona che avvia il pagamento del totale di una prenotazione.
     * @param ammontare
     */
    public void gestisciPagamento(Double ammontare){
        
        try{
            this.bookRestObserver = new RestObserver(instance);
            this.controller.addListener(this.bookRestObserver);
            this.controller.gestisciPagamento(new Money(ammontare));
            aggiornaWindow(new EndBookPanel());
        }
        catch (InvalidMoneyException | RemoteException ex) {
            Logger.getLogger(SaleControllerSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void aggiornaWindow(JComponent panel) {
        UIWindowSingleton.getInstance().setPanel(panel);
        UIWindowSingleton.getInstance().refreshContent();
    }
    
    public void clearBook() {
        UIWindowSingleton.getInstance().setPanel(new EmployeeMenuPanel());
        UIWindowSingleton.getInstance().refreshContent();
    }

    /**
     * @param m
     * @throws RemoteException
     */
    @Override
    public void aggiornaAcconto(Money m) throws RemoteException {
        System.out.println("Acconto: "+m);
        this.acconto = m;
        System.err.println("Acconto da controller: "+this.acconto);
    }
    
    /**
     * @param m
     * @throws RemoteException
     */
    @Override
    public void aggiornaTotale(Money m) throws RemoteException {
        System.out.println("Totale: "+m);
        this.totale = m;
        System.err.println("Totale da controller: "+this.totale);
    }

    /**
     * @param m
     * @throws RemoteException
     */
    @Override
    public void aggiornaResto(Money m) throws RemoteException {
        System.out.println("Resto: "+m);
        this.resto = m;
    }

    /**
     * @param code
     */
    public void inserisciCartaCliente(Integer code) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
