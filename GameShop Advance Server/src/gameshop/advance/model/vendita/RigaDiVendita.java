package gameshop.advance.model.vendita;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.model.DescrizioneProdotto;
import gameshop.advance.model.vendita.sconto.strategy.ScontoFactorySingleton;
import gameshop.advance.model.vendita.sconto.strategy.prodotti.IScontoProdottoStrategy;
import gameshop.advance.utility.Money;
import java.util.List;

/**
 * Rappresenta la riga di vendita che verrà stampata sullo scontrino.Contiene
 * la descrizione del prodotto e la quantità di prodotti acquistata.
 * @author Salx
 */
public class RigaDiVendita
{
    private IScontoProdottoStrategy strategiaDiSconto;
    private DescrizioneProdotto descrizione;
    private int quantity;

    /**
     * Il Costruttore utilizza i dati ricevuti in ingresso (descrizione prodotto e
     * quantità) per creare una nuova riga di vendita.
     * @param desc
     * @param quantity
     * @throws java.lang.ClassNotFoundException
     * @throws java.lang.InstantiationException
     * @throws java.lang.IllegalAccessException
     */
    public RigaDiVendita(DescrizioneProdotto desc, int quantity) throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        this.descrizione = desc;
        this.quantity = quantity;
        this.strategiaDiSconto = ScontoFactorySingleton.getInstance().getStrategiaScontoProdotto();
        List<IScontoProdottoStrategy> sconti = desc.getSconti();
        for(IScontoProdottoStrategy sconto:sconti)
        {
            this.strategiaDiSconto.add(sconto);
        }
    }

    public DescrizioneProdotto getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(DescrizioneProdotto descrizione) {
        this.descrizione = descrizione;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Money getSubTotal(Vendita v) throws InvalidMoneyException
    {
        return this.strategiaDiSconto.getSubtotal(v, this);
    }

}