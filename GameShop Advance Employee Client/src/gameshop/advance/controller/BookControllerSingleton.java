package gameshop.advance.controller;

import gameshop.advance.config.ConfigurationControllerSingleton;
import gameshop.advance.exceptions.AlredyPayedException;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.exceptions.InvalidSaleState;
import gameshop.advance.interfaces.remote.IIteratorWrapperRemote;
import gameshop.advance.interfaces.remote.IPrenotaProdottoRemote;
import gameshop.advance.interfaces.remote.IRemoteBookClient;
import gameshop.advance.interfaces.remote.IRemoteFactory;
import gameshop.advance.interfaces.remote.IRemoteObserver;
import gameshop.advance.interfaces.remote.IRemoteReservationClient;
import gameshop.advance.interfaces.remote.IRigaDiTransazioneRemote;
import gameshop.advance.observer.PartialObserver;
import gameshop.advance.observer.RestObserver;
import gameshop.advance.observer.RestPartialObserver;
import gameshop.advance.observer.TotalObserver;
import gameshop.advance.observer.TransactionObserver;
import gameshop.advance.ui.swing.RigheDiVenditaListModel;
import gameshop.advance.ui.swing.UIWindowSingleton;
import gameshop.advance.ui.swing.employee.EmployeeMenuPanel;
import gameshop.advance.ui.swing.employee.book.BookPanel;
import gameshop.advance.ui.swing.employee.book.EndBookPanel;
import gameshop.advance.ui.swing.employee.sale.BookCellRenderer;
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
public class BookControllerSingleton  extends UnicastRemoteObject implements IRemoteBookClient, IRemoteReservationClient{
    
    private static BookControllerSingleton instance;
    private IPrenotaProdottoRemote controller;
    private IRemoteObserver bookPartialObserver;
    private IRemoteObserver bookTotalObserver;
    private TransactionObserver transactionObserver;
    private Money totale = new Money();
    private Money acconto = new Money();
    private Money resto = new Money();
    
    private final RigheDiVenditaListModel listaProdottiPrenotati;
    
    
    private BookControllerSingleton() throws RemoteException{
        
         this.listaProdottiPrenotati = new RigheDiVenditaListModel();
        
    }
    
    private void configure() throws ConfigurationException, RemoteException, NotBoundException {
          
        ConfigurationControllerSingleton controllerConfig = ConfigurationControllerSingleton.getInstance();
        Registry reg = LocateRegistry.getRegistry(controllerConfig.getServerAddress(), controllerConfig.getServerPort()); 
        IRemoteFactory factory = (IRemoteFactory) reg.lookup("RemoteFactory");
        this.controller = factory.getPrenotaProdottoController();
        this.bookPartialObserver = new PartialObserver(instance);
        this.bookTotalObserver = new TotalObserver(instance);
        this.transactionObserver = new TransactionObserver(instance);
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
        this.listaProdottiPrenotati.clear();
        BookPanel panel = new BookPanel();
        panel.setList(this.listaProdottiPrenotati, new BookCellRenderer());
        this.aggiornaWindow(panel);
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
        this.controller.addListener(this.transactionObserver);
        this.listaProdottiPrenotati.clear();
        this.controller.completaPrenotazione();
    }
    
    /**
     * @throws RemoteException
     */
    public void terminaPrenotazione() throws RemoteException {
        this.aggiornaWindow(new EndBookPanel());
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
    public void pagaAcconto(Double acconto) throws RemoteException, InvalidMoneyException, InvalidSaleState, AlredyPayedException{
        try{
            System.err.println("PAga acconto");
            this.controller.addListener(new RestPartialObserver(instance));
            this.controller.pagaAcconto(new Money(acconto));
            aggiornaWindow(new EndBookPanel());
        }
        catch (InvalidMoneyException | RemoteException ex) {
            Logger.getLogger(SaleControllerSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    /**
     * Funziona che avvia il pagamento del totale di una prenotazione.
     * @param ammontare
     */
    public void gestisciPagamento(Double ammontare) throws InvalidSaleState, AlredyPayedException{
        
        try{
            this.controller.addListener(new RestObserver(instance));
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

    @Override
    public void aggiornaIdPrenotazione(int id) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void aggiornaListaProdotti(IIteratorWrapperRemote<IRigaDiTransazioneRemote> iter) throws RemoteException {
         while(iter.hasNext())
        {
            IRigaDiTransazioneRemote rdt = iter.next();
            this.listaProdottiPrenotati.addElement(rdt);
        }
    }
}
