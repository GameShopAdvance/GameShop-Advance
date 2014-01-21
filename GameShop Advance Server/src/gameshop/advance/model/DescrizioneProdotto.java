package gameshop.advance.model;

import gameshop.advance.utility.Money;
import gameshop.advance.utility.IDProdotto;
import java.io.Serializable;

/**
 * La DescrizioneProdotto contiene tutte le informazioni relative ad un tipo di
 * prodotto: id, prezzo e descrizione testuale.
 * @author Salx
 */
public class DescrizioneProdotto
{

    private IDProdotto codiceProdotto;
    private Money prezzo;
    private String descrizione;

    /**
     * Il Costruttore imposta tutte le variabili di DescrizioneProdotto utilizzando
     * i valori ricevuti in input.
     * @param codiceProdotto
     * @param prezzo
     * @param descrizione
     */
    public DescrizioneProdotto(IDProdotto codiceProdotto, Money prezzo, String descrizione ){
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.codiceProdotto = codiceProdotto;
    }

    /**
     * @return il prezzo di un prodotto.
     */
    public Money getPrezzo()
    {
        return this.prezzo;
    }

    /**
     * @return la descizione di un prodotto.
     */
    public String getDescrizione()
    {
        return this.descrizione;
    }


     public void setDescrizione(String descrizione)
    {
        this.descrizione = descrizione;
    }
         

}