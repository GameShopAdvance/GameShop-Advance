package gameshop.advance.model;

import gameshop.advance.exceptions.ObjectAlreadyExistsDbException;
import gameshop.advance.model.vendita.CartaCliente;
import gameshop.advance.model.vendita.Vendita;
import gameshop.advance.technicalservices.db.DbCartaClienteSingleton;
import gameshop.advance.technicalservices.db.DbVenditaSingleton;
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
    public void aggiungiVendita(Vendita v)
    {
        try {
            v.setIdVendita(DbVenditaSingleton.getInstance().count());
            System.err.println("Vendita n."+v.getIdVendita());
            DbVenditaSingleton.getInstance().create(v);
        } catch (ObjectAlreadyExistsDbException ex) {
            Logger.getLogger(NegozioSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public CartaCliente getCliente(int codiceTessera) {
        System.err.println("Negozio ---- looking for client");
            return DbCartaClienteSingleton.getInstance().read(codiceTessera);
    }

}