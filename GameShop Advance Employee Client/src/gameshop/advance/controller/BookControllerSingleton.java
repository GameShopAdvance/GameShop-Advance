/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.controller;

import gameshop.advance.config.ConfigurationControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.interfaces.remote.IPrenotaProdottoRemote;
import gameshop.advance.interfaces.remote.IRemoteClient;
import gameshop.advance.interfaces.remote.IRemoteFactory;
import gameshop.advance.interfaces.remote.IRemoteObserver;
import gameshop.advance.observer.BookPartialObserver;
import gameshop.advance.observer.BookRestObserver;
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
 *
 * @author Salx
 */
public class BookControllerSingleton  extends UnicastRemoteObject implements IRemoteClient{
    
    private static BookControllerSingleton instance;
    private IPrenotaProdottoRemote controller;
    private IRemoteObserver bookRestObserver;
    private IRemoteObserver bookPartialObserver;
    private Money totale;
    private Money acconto;
    private Money resto;
    
    private BookControllerSingleton() throws RemoteException{
        
    }
    
    private void configure() throws ConfigurationException, RemoteException, NotBoundException {
          
        ConfigurationControllerSingleton controllerConfig = ConfigurationControllerSingleton.getInstance();
        Registry reg = LocateRegistry.getRegistry(controllerConfig.getServerAddress(), controllerConfig.getServerPort()); 
        IRemoteFactory factory = (IRemoteFactory) reg.lookup("RemoteFactory");
        this.controller = factory.getPrenotaProdottoController();
        this.bookRestObserver = new BookRestObserver(instance);
        this.bookPartialObserver = new BookPartialObserver(instance);
        
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
        this.controller.avviaPrenotazione();
        this.controller.addListener(this.bookPartialObserver);
        this.aggiornaWindow(new BookPanel());
    }
    
    public void recuperaPrenotazione(int codicePrenotazione) throws RemoteException {
        this.controller.recuperaPrenotazione(codicePrenotazione);
    }
    
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
    
    public void pagaAcconto(double acconto) throws RemoteException, InvalidMoneyException{
        try{
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
    
    public void gestisciPagamento(Double ammontare){
        
        try{
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

    @Override
    public void aggiornaAcconto(Money m){
        this.acconto = m;
    }
    
    @Override
    public void aggiornaTotale(Money m) throws RemoteException {
        this.totale = m;
    }

    @Override
    public void aggiornaResto(Money m) throws RemoteException {
        this.resto = m;
    }

    public void inserisciCartaCliente(Integer code) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
