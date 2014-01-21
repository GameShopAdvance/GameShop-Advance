package gameshop.advance.model;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.model.vendita.Vendita;
import java.rmi.RemoteException;
import java.util.LinkedList;

/**
 * Il Negozio rappresenta il vero Negozio fisico.Dato che il negozio è uno e uno solo,
 * tale classe è stata implementata come Singleton.Imposta e restituisce le casse e il 
 * catalogo dei prodotti.Inoltre contiene una lista di tutte le vendite completate.
 * @author Salx
 */
public class NegozioSingleton
{
    private static NegozioSingleton store = null;
    
    private LinkedList<Cassa> casse;
    private CatalogoProdottiSingleton catalogo;
    private LinkedList<Vendita> venditeCompletate;

    /**
     * Il Costruttore imposta tutte le variabili di Negozio.
     */
    private NegozioSingleton() throws RemoteException, InvalidMoneyException
    {
        this.catalogo = CatalogoProdottiSingleton.getInstance();
        this.casse = new LinkedList<>();
        this.venditeCompletate = new LinkedList<>();
    }

    /**
     * Impedisce la creazione di più oggetti Negozio dato che Negozio è Singleton-
     * @return Negozio
     * @throws RemoteException
     * @throws InvalidMoneyException
     */
    public static synchronized NegozioSingleton getInstance() throws RemoteException, InvalidMoneyException
    {
        if(NegozioSingleton.store == null)
        {
            NegozioSingleton.store = new NegozioSingleton();
        }
        return NegozioSingleton.store;
    }

    /**
     * Utilizza il parametro in ingresso idCassa per recuperare la Cassa che possiede
     * tale id,all'interno dell'elenco di casse che fanno parte del negozio.
     * @param idCassa
     * @return l'oggetto Cassa richiesto
     */
    public Cassa getCassa(int idCassa) {
        for (Cassa cassa : this.casse) {
            if ( cassa.getIdCassa() == idCassa )
                return cassa;
        }   
        return null;
    }

    /**
     * Aggiunge la cassa ricevuta all'elenco delle casse presenti e attive in negozio.
     * @param cassa
     */
    public void addCassa(Cassa cassa) {
        this.casse.add(cassa);
       
    }

    /**
     * @return il catalogo dei prodotti
     */
    public CatalogoProdottiSingleton getCatalogo() {
        return catalogo;
    }

    /**
     * Imposta il catalogo ricevuto come catalogo del negozio.
     * @param catalogo
     */
    public void setCatalogo(CatalogoProdottiSingleton catalogo) {
        this.catalogo = catalogo;
    }

    /**
     * Aggiunge la vendita ricevuta all'elenco delle vendite completate nel negozio.
     * @param v
     */
    public void aggiungiVendita(Vendita v)
    {
        this.venditeCompletate.add(v);            
    }

}