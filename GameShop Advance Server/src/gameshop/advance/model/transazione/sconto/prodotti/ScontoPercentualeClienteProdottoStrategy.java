/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.transazione.sconto.prodotti;

import gameshop.advance.interfaces.IScontoProdottoStrategy;
import gameshop.advance.interfaces.ITransazione;
import gameshop.advance.model.transazione.CartaCliente;
import gameshop.advance.model.transazione.RigaDiTransazione;
import gameshop.advance.model.transazione.TipologiaCliente;
import gameshop.advance.utility.IntervalloDiTempo;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;
import java.util.List;
import org.joda.time.DateTime;

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
    public Money getSubtotal(ITransazione v, RigaDiTransazione rdv) throws RemoteException {
        CartaCliente c = v.getCliente();
        Money subtotal = rdv.getDescrizione().getPrezzo(v.getDate()).multiply(rdv.getQuantity());
        if(c!=null && this.checkApplicable(c.getTipo()))
            return subtotal.subtract(subtotal.multiply(percentuale).divide(100));
        else
            return subtotal;
    }
    
    @Override
    public void add(IScontoProdottoStrategy sp){}
    
    private boolean checkApplicable(TipologiaCliente tc)
    {
        for(TipologiaCliente tcliente: applicabile)
        {
            if(tcliente.equals(tc))
                return true;
        }
        return false;
    }

    @Override
    public boolean isValid(DateTime period) {
        if(period == null)
            return periodo.isActual();
        else
            return periodo.isInPeriod(period);
    }
}
