/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.controller;

import gameshop.advance.config.ConfigurationControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.exceptions.products.ProdottoNotFoundException;
import gameshop.advance.interfaces.IListPanel;
import gameshop.advance.interfaces.remote.IDescrizioneProdottoRemote;
import gameshop.advance.interfaces.remote.factory.IPrenotaProdottoRemote;
import gameshop.advance.interfaces.remote.factory.IRemoteFactory;
import gameshop.advance.interfaces.remote.sales.IRigaDiTransazioneRemote;
import gameshop.advance.interfaces.remote.sales.reservations.IRemoteBookClient;
import gameshop.advance.interfaces.remote.sales.reservations.IRemoteReservationClient;
import gameshop.advance.interfaces.remote.utility.IIteratorWrapperRemote;
import gameshop.advance.interfaces.remote.utility.IRemoteObserver;
import gameshop.advance.observer.PartialObserver;
import gameshop.advance.observer.ReservationObserver;
import gameshop.advance.observer.TotalObserver;
import gameshop.advance.observer.TransactionObserver;
import gameshop.advance.ui.swing.UIWindowSingleton;
import gameshop.advance.ui.swing.customer.CompletedReservationPanel;
import gameshop.advance.ui.swing.customer.ProductsPanel;
import gameshop.advance.ui.swing.lists.models.RigheDiVenditaListModel;
import gameshop.advance.ui.swing.lists.renderer.BookCellRenderer;
import gameshop.advance.utility.IDProdotto;
import gameshop.advance.utility.Money;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.JComponent;
import javax.swing.ListCellRenderer;

/**
 * Classe Controller che si occupa della gestione delle prenotazioni sul client Customer.
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
    
    private RigheDiVenditaListModel listaPrenotati;
    
    private boolean started = false;
    
    /**
     * Costruttore di classe.
     * @throws RemoteException
     */
    public ReservationControllerSingleton() throws RemoteException {
        this.totale = new Money();
        this.acconto = new Money();
        this.listaPrenotati = new RigheDiVenditaListModel();
        this.listaPrenotati.setHeader(true);
    }
    
    /**
     * Metodo che si occupa della configurazione degli attributi di classe e della comunicazione RMI.
     * @return
     * @throws ConfigurationException
     * @throws RemoteException
     * @throws NotBoundException
     */
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
     * Metodo che ritorna l'istanza della classe Singleton.
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

    /**
     * @throws RemoteException
     */
    public void avviaPrenotazione() throws RemoteException 
    {
       ProductsPanel panel = new ProductsPanel();
       this.totale = new Money();
       this.acconto = new Money();
       this.aggiornaWindow(panel);
    }

    /**
     * @throws RemoteException
     */
    public void cancellaPrenotazione() throws RemoteException 
    {
        this.controller.cancellaPrenotazione();
        this.listaPrenotati.clear();
        this.started = false;
        this.totale = new Money();
        this.acconto = new Money();
    }

    /**
     * @param desc
     * @param quantity
     * @throws RemoteException
     * @throws ProdottoNotFoundException
     */
    public void inserisciProdotto(IDescrizioneProdottoRemote desc, int quantity) throws RemoteException, ProdottoNotFoundException 
    {
        if(!this.started)
        {
            this.controller.avviaPrenotazione();
            this.controller.addListener(this.totalObserver);
            this.controller.addListener(this.partialObserver);
            this.controller.addListener(this.productsObserver);
            this.started = true;
        }
        IDProdotto codiceProdotto = desc.getCodiceProdotto();
        this.controller.prenotaProdotto(codiceProdotto, quantity);
    }

    /** 
     * Metodo che si occupa di prendere dalla tabella dei prodotti disponibili per la prenotazione
     * quelli selezionati e le rispettive quantità e richiede al controller di aggiungerli alla prenotazione.
     * @throws RemoteException
     */
    public void completaPrenotazione() throws RemoteException 
    {
        this.controller.addListener(this.reservationObserver);
        this.controller.terminaPrenotazione();
        this.aggiornaWindow(new CompletedReservationPanel());
        this.started = false;
        this.listaPrenotati.clear();
    }

    /** 
     * Aggiorna l'id della prenotazione una volta che questa è terminata
     * @param id
     * @throws RemoteException
     */
    @Override
    public void aggiornaIdPrenotazione(int id) throws RemoteException {
        this.idPrenotazione = id;
    }
    
    
    /** 
     * @param iter
     * @throws RemoteException
     */
    @Override
    public void aggiornaListaProdotti(IIteratorWrapperRemote<IRigaDiTransazioneRemote> iter) throws RemoteException {
        while(iter.hasNext())
        {
            this.listaPrenotati.addElement(iter.next());
        }
    }

    /** 
     * @param ammontare
     * @throws RemoteException
     */
    @Override
    public void aggiornaAcconto(Money ammontare) throws RemoteException {
        this.acconto = ammontare;
    }

    /** 
     * @param m
     * @throws RemoteException
     */
    @Override
    public void aggiornaTotale(Money m) throws RemoteException {
        System.err.println("Totale: "+m.toString());
        this.totale = m;
    }

    /** 
     * @param m
     * @throws RemoteException
     */
    @Override
    public void aggiornaResto(Money m) throws RemoteException {
        
    }

    /**
     * @return
     */
    public Integer getID()
    {
        return this.idPrenotazione;
    }
    
    /**
     * @return
     */
    public Money getTotal()
    {
        return this.totale;
    }
    
    /**
     * @return
     */
    public Money getPartial()
    {
        return this.acconto;
    }

    /**
     * @param panel
     */
    public void setReservationList(IListPanel panel)
    {
        ListCellRenderer renderer = new BookCellRenderer();
        panel.setList(this.listaPrenotati, renderer);
    }
 
}
