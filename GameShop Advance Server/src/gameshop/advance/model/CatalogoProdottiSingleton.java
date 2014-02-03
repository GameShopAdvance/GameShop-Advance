package gameshop.advance.model;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.exceptions.ProdottoNotFoundException;
import gameshop.advance.technicalservices.db.DbDescrizioneProdottoSingleton;
import gameshop.advance.utility.IDProdotto;

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
    
    /**
     * Il Costruttore di CatalogoProdotti attualmente crea un insieme di 
     * DescrizioneProdotto e lo salva nella variabile descrizioni.Successivamente
     * andrà a recuperare le descrizioni dei prodotti nel database.
     */
    private CatalogoProdottiSingleton (){}

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
     * @throws gameshop.advance.exceptions.ProdottoNotFoundException
     */
    public DescrizioneProdotto getDescrizioneProdotto(IDProdotto codiceProdotto) throws ProdottoNotFoundException
    {
        DescrizioneProdotto desc = DbDescrizioneProdottoSingleton.getInstance().read(codiceProdotto);
        if(desc == null)
            throw new ProdottoNotFoundException(codiceProdotto);
        return desc;
    }
}
