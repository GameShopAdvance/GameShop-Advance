/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.transazione.sconto.vendita;

import gameshop.advance.interfaces.IScontoVenditaStrategy;
import gameshop.advance.interfaces.ITransazione;
import gameshop.advance.model.transazione.RigaDiTransazione;
import gameshop.advance.utility.IntervalloDiTempo;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;
import java.util.Iterator;
import org.joda.time.DateTime;

/**
 *
 * @author Salx
 */
public class ScontoTotaleVenditaStrategy implements IScontoVenditaStrategy {
    
    private Money ammontare;
    private IntervalloDiTempo periodo;
    
    public ScontoTotaleVenditaStrategy(Money m, IntervalloDiTempo time){
        
        this.ammontare = m;
        this.periodo = time;
    }
    
    public void setScontoTotale(Money m){
        
        this.ammontare = m;
    }
    
    public Money getScontoTotale(){
        
        return this.ammontare;
    }
    
    @Override
    public Money getTotal(ITransazione vendita) throws RemoteException{
        Iterator<RigaDiTransazione> righe = vendita.getRigheDiVendita();
        Money totale = new Money();
        while(righe.hasNext())
        {
            totale = totale.add(righe.next().getSubTotal());
        }
        return totale.subtract(this.ammontare);
    }
    
    @Override
    public boolean isValid(DateTime period) {
        return this.periodo.isInPeriod(period);
    }
}