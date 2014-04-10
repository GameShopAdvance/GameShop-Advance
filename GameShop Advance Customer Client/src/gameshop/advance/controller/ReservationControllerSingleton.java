/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.controller;

import gameshop.advance.config.ConfigurationControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.exceptions.ProdottoNotFoundException;
import gameshop.advance.interfaces.remote.IDescrizioneProdottoRemote;
import gameshop.advance.interfaces.remote.IIteratorWrapperRemote;
import gameshop.advance.interfaces.remote.IPrenotaProdottoRemote;
import gameshop.advance.interfaces.remote.IRemoteBookClient;
import gameshop.advance.interfaces.remote.IRemoteFactory;
import gameshop.advance.interfaces.remote.IRemoteObserver;
import gameshop.advance.interfaces.remote.IRemoteReservationClient;
import gameshop.advance.interfaces.remote.IRigaDiTransazioneRemote;
import gameshop.advance.observer.PartialObserver;
import gameshop.advance.observer.ReservationObserver;
import gameshop.advance.observer.TotalObserver;
import gameshop.advance.observer.TransactionObserver;
import gameshop.advance.ui.swing.UIWindowSingleton;
import gameshop.advance.ui.swing.customer.CompletedReservationPanel;
import gameshop.advance.ui.swing.customer.ProductsPanel;
import gameshop.advance.utility.IDProdotto;
import gameshop.advance.utility.Money;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;
import javax.swing.JComponent;

/**
 *
 * @author Matteo Gentile
 */
public class ReservationControllerSingleton extends UnicastRemoteObject implements IRemoteReservationClient, IRemoteBookClient{

    private static ReservationControllerSingleton instance;
    private IPrenotaProdottoRemote controller;
    
    private IRemoteObserver reservationObserver;
    private IRemoteObserver totalObserver;
    private IRemoteObserver partialObserver;
    private IRemoteObserver productsObserver;
    
    private Integer idPrenotazione;
    private Money totale;
    private Money acconto;
    private Iterator<IRigaDiTransazioneRemote> prenotati;
    
    private boolean started = false;
    
    /**
     *
     * @throws RemoteException
     */
    public ReservationControllerSingleton() throws RemoteException {
        this.totale = new Money();
        this.acconto = new Money();
    }
    
     private void configure() throws ConfigurationException, RemoteException, NotBoundException 
     {    
        ConfigurationControllerSingleton controllerConfig = ConfigurationControllerSingleton.getInstance();
        Registry reg = LocateRegistry.getRegistry(controllerConfig.getServerAddress(), controllerConfig.getServerPort());
        IRemoteFactory factory = (IRemoteFactory) reg.lookup("RemoteFactory");
        this.controller = factory.getPrenotaProdottoController();
        this.reservationObserver = new ReservationObserver(instance);
        this.totalObserver = new TotalObserver(instance);
        this.partialObserver = new PartialObserver(instance);
        this.productsObserver = new TransactionObserver(instance);
    }
    
    /**
     *
     * @return
     * @throws NullPointerException
     * @throws RemoteException
     * @throws ConfigurationException
     */
    public static ReservationControllerSingleton getInstance() throws NullPointerException, RemoteException, ConfigurationException
    {
        if(instance==null)
        {
            try {
                instance = new ReservationControllerSingleton();
                instance.configure();
            } catch (RemoteException | NotBoundException ex) {
                instance = null;
                throw new RemoteException("Ci sono problemi di comunicazione con il server.");
            }
        }
        return instance;
    }
    
    
    private void aggiornaWindow(JComponent panel) 
    {
        UIWindowSingleton.getInstance().setPanel(panel);
        UIWindowSingleton.getInstance().refreshContent();
    }

    
    public void avviaPrenotazione() throws RemoteException 
    {
       ProductsPanel panel = new ProductsPanel();
       this.aggiornaWindow(panel);
       IIteratorWrapperRemote<IDescrizioneProdottoRemote> iter = this.controller.getDescriptions();

       while(iter.hasNext()) {
           panel.addProduct(iter.next());
       }
    }

    public void cancellaPrenotazione() throws RemoteException 
    {      
        this.controller.cancellaPrenotazione();
        this.started = false;
        this.totale = new Money();
        this.acconto = new Money();
    }

    public void inserisciProdotto(IDescrizioneProdottoRemote desc, int quantity) throws RemoteException, ProdottoNotFoundException 
    {
        if(!this.started)
        {
            this.controller.avviaPrenotazione();
            System.err.println("Prenotazione avviata");
            this.controller.addListener(this.totalObserver);
            this.controller.addListener(this.partialObserver);
            this.controller.addListener(this.productsObserver);
            this.started = true;
        }
        IDProdotto codiceProdotto = desc.getCodiceProdotto();
        this.controller.prenotaProdotto(codiceProdotto, quantity);
    }

    /** Metodo che si occupa di prendere dalla tabella dei prodotti disponibili per la prenotazione
     *  quelli selezionati e le rispettive quantità e richiede al controller di aggiungerli alla prenotazione.
     *
     * @throws RemoteException
     */
    public void completaPrenotazione() throws RemoteException 
    {
        this.controller.addListener(this.reservationObserver);
        this.controller.terminaPrenotazione();
        this.aggiornaWindow(new CompletedReservationPanel());
        this.started = false;
    }

    /** Aggiorna l'id della prenotazione una volta che questa è terminata
     *
     * @param id
     * @throws RemoteException
     */
    @Override
    public void aggiornaIdPrenotazione(int id) throws RemoteException {
        this.idPrenotazione = id;
    }

    @Override
    public void aggiornaListaProdotti(IIteratorWrapperRemote<IRigaDiTransazioneRemote> iter) throws RemoteException {
        this.prenotati = (Iterator<IRigaDiTransazioneRemote>) iter;
    }

    @Override
    public void aggiornaAcconto(Money ammontare) throws RemoteException {
        this.acconto = ammontare;
    }

    @Override
    public void aggiornaTotale(Money m) throws RemoteException {
        System.err.println("Totale: "+m.toString());
        this.totale = m;
    }

    @Override
    public void aggiornaResto(Money m) throws RemoteException {
        
    }
    
    public Integer getID()
    {
        return this.idPrenotazione;
    }
    
    public Money getTotal()
    {
        return this.totale;
    }
    
    public Money getPartial()
    {
        return this.acconto;
    }
    
    public Iterator<IRigaDiTransazioneRemote> getListaProdotti()
    {
        return this.prenotati;
    }
 
}
