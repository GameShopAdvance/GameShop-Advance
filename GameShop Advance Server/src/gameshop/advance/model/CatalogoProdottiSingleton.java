package gameshop.advance.model;

import com.db4o.collections.ActivatableHashMap;
import gameshop.advance.exceptions.products.ProdottoNotFoundException;
import gameshop.advance.interfaces.IDescrizioneProdotto;
import gameshop.advance.technicalservices.db.DbDescrizioneProdottoSingleton;
import gameshop.advance.utility.IDProdotto;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Il CatalogoProdotti rappresenta il vero catalogo di un negozio, e consente di
 * recuperare tutti i prodotti che il negozio tratta.Dato che il catalogo è uno e uno
 * solo tale classe è stata implementata come Singleton.Attualmente crea dentro di sè
 * i prodotti presenti in negozio; successivamente recupererà tali prodotti da un database.
 * 
 * @author Salx
 */
public class CatalogoProdottiSingleton
{

    private static CatalogoProdottiSingleton catalog;
    
    private HashMap<String, IDescrizioneProdotto> descriptions;
    
    /**
     * Il Costruttore di CatalogoProdotti attualmente crea un insieme di 
     * DescrizioneProdotto e lo salva nella variabile descrizioni.Successivamente
     * andrà a recuperare le descrizioni dei prodotti nel database.
     */
    private CatalogoProdottiSingleton (){
        this.descriptions = new ActivatableHashMap<>();
    }

    /**
     * Impedisce la creazione di più cataloghi dato che il CatalogoProdotto è Singleton.
     * @return il catalogo dei prodotti
     */
    public static synchronized CatalogoProdottiSingleton getInstance()
        {
            if(CatalogoProdottiSingleton.catalog == null)
            {
                CatalogoProdottiSingleton.catalog = new CatalogoProdottiSingleton();
            }
            return CatalogoProdottiSingleton.catalog;
        }
        
    /**
     * Recupera la descrizione di un prodotto dalla variabile descrizioni tramite
     * l'id del prodotto ricevuto in input.
     * @param codiceProdotto
     * @return  la descrizione del prodotto
     * @throws gameshop.advance.exceptions.products.ProdottoNotFoundException
     */
    public IDescrizioneProdotto getDescrizioneProdotto(IDProdotto codiceProdotto) throws ProdottoNotFoundException
    {
        IDescrizioneProdotto desc = DbDescrizioneProdottoSingleton.getInstance().read(codiceProdotto);
        
        if(desc == null)
            throw new ProdottoNotFoundException(codiceProdotto);
        
        return desc;
    }

    public void aggiornaDescrizioni() {
        Collection<IDescrizioneProdotto> values = this.descriptions.values();
        for(IDescrizioneProdotto desc: values)
        {
            DbDescrizioneProdottoSingleton.getInstance().update(desc);
        }
    }
    
    public Iterator<Object> getDescrizioni(){
        return DbDescrizioneProdottoSingleton.getInstance().read();
    }
    
    public synchronized void aggiornaDescrizione(IDescrizioneProdotto desc)
    {
        DbDescrizioneProdottoSingleton.getInstance().update(desc);
    }
}
