package gameshop.advance.model;

import com.db4o.collections.ActivatableHashMap;
import gameshop.advance.exceptions.ObjectAlreadyExistsDbException;
import gameshop.advance.exceptions.ProdottoNotFoundException;
import gameshop.advance.interfaces.IDescrizioneProdotto;
import gameshop.advance.technicalservices.db.DbDescrizioneProdottoSingleton;
import gameshop.advance.utility.IDProdotto;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Il CatalogoProdotti rappresenta il vero catalogo di un negozio, e consente di
 * recuperare tutti i prodotti che il negozio tratta.Dato che il catalogo è uno e uno
 * solo tale classe è stata implementata come Singleton.Attualmente crea dentro di sè
 * i prodotti presenti in negozio; successivamente recupererà tali prodotti da un database.
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
     * @throws gameshop.advance.exceptions.ProdottoNotFoundException
     */
    public IDescrizioneProdotto getDescrizioneProdotto(IDProdotto codiceProdotto) throws ProdottoNotFoundException
    {
//        if(this.descriptions.containsKey(codiceProdotto.getCodice()))
//            return this.descriptions.get(codiceProdotto.getCodice());
//        else{
//            IDescrizioneProdotto desc = DbDescrizioneProdottoSingleton.getInstance().read(codiceProdotto);
//            
//            if(desc == null)
//                throw new ProdottoNotFoundException(codiceProdotto);
//            
//            this.descriptions.put(codiceProdotto.getCodice(), desc);
//            return desc;
//        }
        IDescrizioneProdotto desc = DbDescrizioneProdottoSingleton.getInstance().read(codiceProdotto);
  
        if(desc == null)
            throw new ProdottoNotFoundException(codiceProdotto);
        
        return desc;
    }

    public void aggiornaDescrizioni() {
        Collection<IDescrizioneProdotto> values = this.descriptions.values();
        for(IDescrizioneProdotto desc: values)
        {
            try {
                DbDescrizioneProdottoSingleton.getInstance().update(desc);
            } catch (ObjectAlreadyExistsDbException ex) {
                Logger.getLogger(CatalogoProdottiSingleton.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public Iterator<Object> getDescrizioni(){
        return DbDescrizioneProdottoSingleton.getInstance().read();
    }
}
