package gameshop.advance.model;

import gameshop.advance.model.vendita.sconto.strategy.prodotti.IScontoProdottoStrategy;
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
    private LinkedList<Prezzo> prezzo = new LinkedList<>();
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
        this.prezzo.add(prezzo);
        this.codiceProdotto = codiceProdotto;
    }

    /**
     * @return il prezzo di un prodotto.
     */
    public Money getPrezzo()
    {
        return this.getPrezzoAttuale().getMoney();
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
         LinkedList<IScontoProdottoStrategy>
         return this.sconti;
     }

    private Object getPrezzoAttuale() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}