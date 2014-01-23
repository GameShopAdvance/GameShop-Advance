package gameshop.advance.model;

import gameshop.advance.utility.Prezzo;
import gameshop.advance.interfaces.IScontoProdottoStrategy;
import gameshop.advance.utility.IDProdotto;
import gameshop.advance.utility.Money;
import java.util.LinkedList;
import java.util.List;

/**
 * La DescrizioneProdotto contiene tutte le informazioni relative ad un tipo di
 * prodotto: id, prezzo e descrizione testuale.
 * @author Salx
 */
public class DescrizioneProdotto
{

    private IDProdotto codiceProdotto;
    private LinkedList<Prezzo> prezzi = new LinkedList<>();
    private String descrizione;
    private LinkedList<IScontoProdottoStrategy> sconti = new LinkedList<>();

    /**
     * Il Costruttore imposta tutte le variabili di DescrizioneProdotto utilizzando
     * i valori ricevuti in input.
     * @param codiceProdotto
     * @param prezzo
     * @param descrizione
     */
    public DescrizioneProdotto(IDProdotto codiceProdotto, Prezzo prezzo, String descrizione ){
        this.descrizione = descrizione;
        this.prezzi.add(prezzo);
        this.codiceProdotto = codiceProdotto;
    }

    /**
     * @return il prezzo di un prodotto.
     */
    public Money getPrezzo()
    {
        Prezzo p = this.getPrezzoAttuale();
        if(p!=null)
            return p.getMoney();
        else
            return null;
    }
    
    public IDProdotto getCodiceProdotto()
    {
        return this.codiceProdotto;
    }

    /**
     * @return la descizione di un prodotto.
     */
    public String getDescrizione()
    {
        return this.descrizione;
    }

    public void addSconto(IScontoProdottoStrategy sconto)
    {
        this.sconti.add(sconto);
    }
    
    public void addSconti(List<IScontoProdottoStrategy> sconti)
    {
        this.sconti.addAll(sconti);
    }

     public void setDescrizione(String descrizione)
    {
        this.descrizione = descrizione;
    }
         
     public List<IScontoProdottoStrategy> getSconti()
     {
         LinkedList<IScontoProdottoStrategy> scontiValidi = new LinkedList<>();
         System.err.println("getSconti()");
         for(IScontoProdottoStrategy sconto: this.sconti)
         {
             System.out.println("Sconto: "+sconto.getClass().toString());
             if(sconto.isValid())
             {
                 scontiValidi.add(sconto);
                 System.err.println("Sconto Valido");
             }
         }
         return scontiValidi;
     }

    private Prezzo getPrezzoAttuale() {
        Prezzo p;
        for(Prezzo prezzo: this.prezzi)
        {
            if(prezzo.isActual())
                return prezzo;
        }
        return null;
    }
    
    public List<Prezzo> getTuttiPrezzi()
    {
        return this.prezzi;
    }
    
    public List<IScontoProdottoStrategy> getTuttiSconti()
    {
        return this.sconti;
    }
    
    public void clone(DescrizioneProdotto d)
    {
        this.codiceProdotto = d.getCodiceProdotto();
        this.descrizione = d.getDescrizione();
        this.prezzi = (LinkedList <Prezzo>) d.getTuttiPrezzi();
        this.sconti = (LinkedList<IScontoProdottoStrategy>) d.getTuttiSconti();
    }
}
