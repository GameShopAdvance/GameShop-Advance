/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.vendita.sconto.vendita;

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
public class ScontoPercentualeStrategy implements IScontoVenditaStrategy {
    
    private int percentuale;
    
    public ScontoPercentualeStrategy(int p){
        
        this.percentuale = p;
    }
    
    public void setScontoPercentuale(int p){
        
        this.percentuale = p;
    }
    
    public int getScontoPercentuale(){
        
        return this.percentuale;
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
                Logger.getLogger(ScontoPercentualeStrategy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return totale.subtract(totale.multiply(percentuale).divide(100));
    }
    
}