package gameshop.advance.model;

import gameshop.advance.exceptions.ObjectAlreadyExistsDbException;
import gameshop.advance.interfaces.IScontoVenditaStrategy;
import gameshop.advance.model.transazione.CartaCliente;
import gameshop.advance.model.transazione.decorator.TransazioneDecorator;
import gameshop.advance.technicalservices.db.DbCartaClienteSingleton;
import gameshop.advance.technicalservices.db.DbScontoVenditaSingleton;
import gameshop.advance.technicalservices.db.DbTransazioneSingleton;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Il Negozio rappresenta il vero Negozio fisico.Dato che il negozio è uno e uno solo,
 * tale classe è stata implementata come Singleton.Imposta e restituisce le casse e il 
 * catalogo dei prodotti.Inoltre contiene una lista di tutte le vendite completate.
 * @author Salx
 */
public class NegozioSingleton
{
    private static NegozioSingleton store = null;

    /**
     * Il Costruttore imposta tutte le variabili di Negozio.
     */
    private NegozioSingleton()
    {
        
    }

    /**
     * Impedisce la creazione di più oggetti Negozio dato che Negozio è Singleton-
     * @return Negozio
     */
    public static synchronized NegozioSingleton getInstance()
    {
        if(NegozioSingleton.store == null)
        {
            NegozioSingleton.store = new NegozioSingleton();
        }
        return NegozioSingleton.store;
    }

    /**
     * Aggiunge la vendita ricevuta all'elenco delle vendite completate nel negozio.
     * @param v
     */
    public void registraTransazione(TransazioneDecorator v)
    {
        try {
            v.setId(DbTransazioneSingleton.getInstance().count());
            System.err.println("Transazione n."+v.getId());
            DbTransazioneSingleton.getInstance().create(v);
        } catch (ObjectAlreadyExistsDbException ex) {
            Logger.getLogger(NegozioSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public TransazioneDecorator riprendiTransazione(Integer id)
    {
        return DbTransazioneSingleton.getInstance().read(id);
    }
    
    public CartaCliente getCliente(int codiceTessera) {
        System.err.println("Negozio ---- looking for client");
            return DbCartaClienteSingleton.getInstance().read(codiceTessera);
    }
    
    
    public LinkedList<IScontoVenditaStrategy> getScontiAttuali(){
      
        LinkedList<IScontoVenditaStrategy> scontiAttuali = new LinkedList<>();
        
        DbScontoVenditaSingleton dbSconto = DbScontoVenditaSingleton.getInstance();
        Iterator<Object> sconti = dbSconto.read();
        
        while(sconti.hasNext())
        {
            IScontoVenditaStrategy sconto = (IScontoVenditaStrategy) sconti.next();
            if(sconto.isActual()){
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