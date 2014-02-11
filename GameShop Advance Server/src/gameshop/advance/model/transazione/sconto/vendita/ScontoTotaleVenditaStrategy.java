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
        List<RigaDiVendita> righe = vendita.getRigheDiVendita();
        Money totale = new Money();
        for(RigaDiVendita riga:righe)
        {
            totale = totale.add(riga.getSubTotal(vendita));
        }
        return totale.subtract(this.ammontare);
    }
    
    @Override
    public boolean isActual() {
        return this.periodo.isActual();
    }
}