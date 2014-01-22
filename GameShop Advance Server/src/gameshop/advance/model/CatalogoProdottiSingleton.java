package gameshop.advance.model;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.utility.IDProdotto;
import gameshop.advance.utility.Money;
import java.util.HashMap;

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
    private  HashMap<IDProdotto,DescrizioneProdotto> descrizioni = new HashMap<>();
    
    /**
     * Il Costruttore di CatalogoProdotti attualmente crea un insieme di 
     * DescrizioneProdotto e lo salva nella variabile descrizioni.Successivamente
     * andrà a recuperare le descrizioni dei prodotti nel database.
     */
    private CatalogoProdottiSingleton () throws InvalidMoneyException {
       //  Dobbiamo recuperare i prodotti dalla cache o dal db
       for(int i=1; i<5;i++)
       {
           IDProdotto id = new IDProdotto("AB"+i);
           Money money = new Money(new Double(i*5));
           DescrizioneProdotto desc = new DescrizioneProdotto(id, money, "Prodotto "+i);
           this.descrizioni.put(id, desc);
       }
    }

    /**
     * Impedisce la creazione di più cataloghi dato che il CatalogoProdotto è Singleton.
     * @return il catalogo dei prodotti
     * @throws InvalidMoneyException
     */
    public static synchronized CatalogoProdottiSingleton getInstance() throws InvalidMoneyException
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
     */
    public DescrizioneProdotto getDescrizioneProdotto(IDProdotto codiceProdotto)
    {
        DescrizioneProdotto desc = this.descrizioni.get(codiceProdotto);
        return desc;
    }
}