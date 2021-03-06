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

/** Strategy degli sconti percentuali sul tipo di cliente
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class ScontoPercentualeClienteProdottoStrategy implements IScontoProdottoStrategy{
    
    private List<TipologiaCliente> applicabile;
    private IntervalloDiTempo periodo;
    private int percentuale;
    
    public ScontoPercentualeClienteProdottoStrategy(int percentuale, IntervalloDiTempo time, List<TipologiaCliente> applicable)
    {
        this.percentuale = percentuale;
        this.periodo = time;
        this.applicabile = applicable;
    }
    /**
     * @param rdv
     * @param trans 
     * @return Il sottototale con eventuali sconti applicati
     * @throws java.rmi.RemoteException
     */
    @Override
    public Money getSubtotal(RigaDiTransazione rdv, ITransazione trans) throws RemoteException {
        CartaCliente c = trans.getCliente();
        Money subtotal = rdv.getDescrizione().getPrezzo(trans.getDate()).multiply(rdv.getQuantity());
        if(c!=null && this.checkApplicable(c.getTipo()))
            return subtotal.subtract(subtotal.multiply(percentuale).divide(100));
        else
            return subtotal;
    }
    
    @Override
    public void add(IScontoProdottoStrategy sp){}
    /**
     * Controllo sull'applicabilità  dello sconto
     * 
     * @param tc 
     * @return Booleano risultato del controllo
     */
    private boolean checkApplicable(TipologiaCliente tc)
    {
        for(TipologiaCliente tcliente: applicabile)
        {
            if(tcliente.equals(tc))
                return true;
        }
        return false;
    }
    /**
     * Controllo sulla validità temporale dello sconto
     * 
     * @param period 
     * @return Booleano risultato del controllo
     */
    @Override
    public boolean isValid(DateTime period) {
        if(period == null)
            return periodo.isActual();
        else
            return periodo.isInPeriod(period);
    }
}
