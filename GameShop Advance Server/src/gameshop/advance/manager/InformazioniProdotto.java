/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.manager;

import gameshop.advance.exceptions.QuantityException;
import gameshop.advance.interfaces.IDescrizioneProdotto;

/**
 *
 * @author Lorenzo Di Giuseppe
 */
public class InformazioniProdotto {
    
    private IDescrizioneProdotto descrizione;
    
    private int prenotati = 0;
    
    private int ordinati = 0;
    
    public InformazioniProdotto(IDescrizioneProdotto desc)
    {
        this(desc, 0, 0);
    }
    
    public InformazioniProdotto(IDescrizioneProdotto desc, int prenotati)
    {
        this(desc, prenotati, 0);
    }
    
    public InformazioniProdotto(IDescrizioneProdotto desc, int prenotati, int ordinati)
    {
        this.descrizione = desc;
        this.prenotati = prenotati;
        this.ordinati = ordinati;
    }

    public IDescrizioneProdotto getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(IDescrizioneProdotto descrizione) {
        this.descrizione = descrizione;
    }

    public int getPrenotati() {
        return prenotati;
    }

    public void setPrenotati(int prenotati) throws QuantityException {
        if(prenotati < 0)
            throw new QuantityException(prenotati);
        this.prenotati = prenotati;
    }

    public int getOrdinati() {
        return ordinati;
    }

    public void setOrdinati(int ordinati) throws QuantityException {
        if(ordinati < 0)
            throw new QuantityException(ordinati);
        this.ordinati = ordinati;
    }
    
    
}
