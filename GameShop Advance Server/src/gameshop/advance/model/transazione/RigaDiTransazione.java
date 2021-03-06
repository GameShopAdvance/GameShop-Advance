package gameshop.advance.model.transazione;

import gameshop.advance.interfaces.IDescrizioneProdotto;
import gameshop.advance.interfaces.IScontoProdottoStrategy;
import gameshop.advance.interfaces.remote.sales.IRigaDiTransazioneRemote;
import gameshop.advance.model.DescrizioneProdotto;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;

/**
 * Rappresenta la riga di vendita che verrà stampata sullo scontrino.Contiene
 * la descrizione del prodotto e la quantità di prodotti acquistata.
 * 
 * @author Salx
 */
public class RigaDiTransazione implements IRigaDiTransazioneRemote
{
    private IScontoProdottoStrategy strategiaDiSconto;
    private IDescrizioneProdotto descrizione;
    private int quantity;

    /**
     * Il Costruttore utilizza i dati ricevuti in ingresso (descrizione prodotto e
     * quantità) per creare una nuova riga di vendita.
     * @param desc
     * @param quantity
     * @param sconto
     */
    public RigaDiTransazione(IDescrizioneProdotto desc, int quantity, IScontoProdottoStrategy sconto)
    {
        this.descrizione = desc;
        this.quantity = quantity;
        this.strategiaDiSconto = sconto;
    }

    @Override
    public IDescrizioneProdotto getDescrizione() throws RemoteException {
        return descrizione;
    }

    public void setDescrizione(DescrizioneProdotto descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public int getQuantity() throws RemoteException {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public Money getSubTotal() throws RemoteException
    {
        return this.strategiaDiSconto.getSubtotal(this, null);
    }

}