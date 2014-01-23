/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.vendita.sconto.vendita;

import gameshop.advance.model.vendita.sconto.IScontoVenditaStrategy;
import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.model.vendita.IVendita;
import gameshop.advance.model.vendita.RigaDiVendita;
import gameshop.advance.utility.Money;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Salx
 */
public class ScontoTotaleVenditaStrategy implements IScontoVenditaStrategy {
    
    private Money ammontare;
    
    public ScontoTotaleVenditaStrategy(Money m){
        
        this.ammontare = m;
    }
    
    public void setScontoTotale(Money m){
        
        this.ammontare = m;
    }
    
    public Money getScontoTotale(){
        
        return this.ammontare;
    }
    
    @Override
    public Money getTotal(IVendita vendita){
        List<RigaDiVendita> righe = vendita.getRigheDiVendita();
        Money totale = new Money();
        for(RigaDiVendita riga:righe)
        {
            try {
                totale = totale.add(riga.getSubTotal(vendita));
            } catch (InvalidMoneyException ex) {
                Logger.getLogger(ScontoTotaleVenditaStrategy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return totale.subtract(this.ammontare);
    }
    
}