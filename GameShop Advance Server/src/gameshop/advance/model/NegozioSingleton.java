package gameshop.advance.model;

import gameshop.advance.exceptions.QuantityException;
import gameshop.advance.exceptions.db.ObjectAlreadyExistsDbException;
import gameshop.advance.exceptions.db.ReservationNotFoundDbException;
import gameshop.advance.exceptions.sales.ClientNotFoundException;
import gameshop.advance.interfaces.IPrenotazione;
import gameshop.advance.interfaces.IScontoVenditaStrategy;
import gameshop.advance.interfaces.ITransazione;
import gameshop.advance.manager.ManagerPrenotazioniSingleton;
import gameshop.advance.model.transazione.CartaCliente;
import gameshop.advance.technicalservices.LoggerSingleton;
import gameshop.advance.technicalservices.db.DbCartaClienteSingleton;
import gameshop.advance.technicalservices.db.DbPrenotazioneSingleton;
import gameshop.advance.technicalservices.db.DbScontoVenditaSingleton;
import gameshop.advance.technicalservices.db.DbVenditaSingleton;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.LinkedList;
import org.joda.time.DateTime;

/**
 * Il Negozio rappresenta il vero Negozio fisico.Dato che il negozio è uno e uno solo,
 * tale classe è stata implementata come Singleton.Imposta e restituisce le casse e il 
 * catalogo dei prodotti.Inoltre contiene una lista di tutte le vendite completate.
 * 
 * @author Salx
 */
public class NegozioSingleton
{
    private static NegozioSingleton store = null;

    private static final int percentualeAcconto = 20;
    /**
     * Il Costruttore imposta tutte le variabili di Negozio.
     */
    private NegozioSingleton()
    {
        
    }

    /**
     * Impedisce la creazione di più oggetti Negozio dato che Negozio è Singleton-
     * 
     * @return NegozioSingleton
     */
    public static synchronized NegozioSingleton getInstance()
    {
        if(NegozioSingleton.store == null)
        {
            NegozioSingleton.store = new NegozioSingleton();
        }
        return NegozioSingleton.store;
    }

    public int getPercentualeAcconto()
    {
        return NegozioSingleton.percentualeAcconto;
    }
    
    public synchronized Integer getNextSaleId()
    {
        return DbVenditaSingleton.getInstance().count();
    }
    
    public synchronized Integer getNextBookId()
    {
        return DbPrenotazioneSingleton.getInstance().count();
    }
    
    /**
     * Aggiunge la vendita ricevuta all'elenco delle vendite completate nel negozio.
     * 
     * @param v
     * @throws java.rmi.RemoteException
     * @throws gameshop.advance.exceptions.db.ObjectAlreadyExistsDbException
     */
    public void registraVendita(ITransazione v) throws RemoteException
    {
        try {
            DbVenditaSingleton.getInstance().create(v);
            
        } catch (ObjectAlreadyExistsDbException ex) {
            LoggerSingleton.getInstance().log(ex);
        }
    }
    /**
     * Aggiunge la prenotazione ricevuta all'elenco delle prenotazioni effettuate snel negozio.
     * 
     * @param p
     * @throws java.rmi.RemoteException
     * @throws gameshop.advance.exceptions.QuantityException
     */
     public void registraPrenotazione(IPrenotazione p) throws RemoteException, QuantityException
    { 
        try {
            ManagerPrenotazioniSingleton.getInstance().store(p);
        } catch (ObjectAlreadyExistsDbException ex) {
            LoggerSingleton.getInstance().log(ex);
        }
    }
    
    public ITransazione riprendiPrenotazione(Integer id) throws ReservationNotFoundDbException
    {
        IPrenotazione read = DbPrenotazioneSingleton.getInstance().read(id);
        if(read == null)
            throw new ReservationNotFoundDbException();
        return read;
    }
    
    public CartaCliente getCliente(int codiceTessera) throws ClientNotFoundException {
        CartaCliente read = DbCartaClienteSingleton.getInstance().read(codiceTessera);
        if(read == null)
            throw new ClientNotFoundException(codiceTessera);
        return read;
    }
    
    /**
     * 
     * @return gli sconti applicabili in quel momento
     */
    public LinkedList<IScontoVenditaStrategy> getScontiAttuali(){
      
        LinkedList<IScontoVenditaStrategy> scontiAttuali = new LinkedList<>();
        
        DbScontoVenditaSingleton dbSconto = DbScontoVenditaSingleton.getInstance();
        Iterator<Object> sconti = dbSconto.read();
        
        while(sconti.hasNext())
        {
            IScontoVenditaStrategy sconto = (IScontoVenditaStrategy) sconti.next();
            if(sconto.isValid(DateTime.now())){
                scontiAttuali.add(sconto);
            }
        }
        
        return scontiAttuali;
        
    }
    
    public void salvaScontoVendita(IScontoVenditaStrategy sconto) throws ObjectAlreadyExistsDbException{
        
        DbScontoVenditaSingleton dbSconto = DbScontoVenditaSingleton.getInstance();
        dbSconto.create(sconto);
    }


}
