/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.transazione.sconto.vendita;

import gameshop.advance.interfaces.IScontoVenditaStrategy;
import gameshop.advance.interfaces.ITransazione;
import gameshop.advance.interfaces.remote.IRigaDiTransazioneRemote;
import gameshop.advance.utility.IntervalloDiTempo;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;
import java.util.Iterator;
import org.joda.time.DateTime;

/**
 *
 * @author Salx
 */
public class ScontoPercentualeVenditaStrategy implements IScontoVenditaStrategy {
    
    private int percentuale;
    private IntervalloDiTempo periodo;
    
    public ScontoPercentualeVenditaStrategy(int p, IntervalloDiTempo time){
        
        this.percentuale = p;
        this.periodo = time;
    }
    
    public void setScontoPercentuale(int p){
        
        this.percentuale = p;
    }
    
    public int getScontoPercentuale(){
        
        return this.percentuale;
    }
    
    @Override
    public Money getTotal(ITransazione vendita) throws RemoteException {
        Iterator<IRigaDiTransazioneRemote> righe = vendita.getRigheDiVendita();
        Money totale = new Money();
        while(righe.hasNext())
        {
            totale = totale.add(righe.next().getSubTotal());
 
        }
        return totale.subtract(totale.multiply(percentuale).divide(100));
    }
    
    public boolean isValid(DateTime period) {
        return this.periodo.isInPeriod(period);
    }
    
}