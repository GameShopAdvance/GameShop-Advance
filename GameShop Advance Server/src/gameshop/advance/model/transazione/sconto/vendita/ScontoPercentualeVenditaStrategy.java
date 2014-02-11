/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.transazione.sconto.vendita;

import gameshop.advance.interfaces.IScontoVenditaStrategy;
import gameshop.advance.interfaces.ITransazione;
import gameshop.advance.model.transazione.RigaDiVendita;
import gameshop.advance.utility.IntervalloDiTempo;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;
import java.util.List;

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
        List<RigaDiVendita> righe = vendita.getRigheDiVendita();
        Money totale = new Money();
        for(RigaDiVendita riga:righe)
        {
            totale = totale.add(riga.getSubTotal(vendita));
 
        }
        return totale.subtract(totale.multiply(percentuale).divide(100));
    }
    
    public boolean isActual() {
        return this.periodo.isActual();
    }
    
}