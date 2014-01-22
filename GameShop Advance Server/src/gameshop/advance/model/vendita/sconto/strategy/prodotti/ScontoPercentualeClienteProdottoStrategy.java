/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.vendita.sconto.strategy.prodotti;

import gameshop.advance.model.vendita.CartaCliente;
import gameshop.advance.model.vendita.RigaDiVendita;
import gameshop.advance.model.vendita.TipologiaCliente;
import gameshop.advance.model.vendita.Vendita;
import gameshop.advance.utility.Money;
import java.util.List;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class ScontoPercentualeClienteProdottoStrategy implements IScontoProdottoStrategy{
    
    private List<TipologiaCliente> applicabile;
    private IntervalloDiTempo periodo;
    private int percentuale;
    
    public ScontoPercentualeClienteProdottoStrategy(int percentuale, List applicable)
    {
        this.percentuale = percentuale;
        this.applicabile = applicable;
    }

    @Override
    public Money getSubtotal(Vendita v, RigaDiVendita rdv) {
        CartaCliente c = v.getCliente();
        Money subtotal = rdv.getDescrizione().getPrezzo().multiply(rdv.getQuantity());
        if(c!=null && this.checkApplicable(c.getTipo()))
            return subtotal.subtract(subtotal.multiply(percentuale).divide(100));
        else
            return subtotal;
    }
    
    @Override
    public void add(IScontoProdottoStrategy sp){}
    
    public boolean checkApplicable(TipologiaCliente tc)
    {
        for(TipologiaCliente tcliente: applicabile)
        {
            if(tcliente.equals(tc))
                return true;
        }
        return false;
    }

    @Override
    public boolean isValid() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
