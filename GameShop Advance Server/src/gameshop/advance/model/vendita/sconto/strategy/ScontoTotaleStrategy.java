/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.vendita.sconto.strategy;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.remote.interfaces.IVenditaRemote;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Salx
 */
public class ScontoTotaleStrategy implements IScontoVenditaStrategy {
    
    private Money ammontare;
    
    public ScontoTotaleStrategy(Money m){
        
        this.ammontare = m;
    }
    
    public void setScontoTotale(Money m){
        
        this.ammontare = m;
    }
    
    public Money getScontoTotale(){
        
        return this.ammontare;
    }
    
    @Override
    public Money getTotal(IVenditaRemote vendita) throws RemoteException{
        
        Money prezzoScontato = this.calcolaSconto(vendita);
        return prezzoScontato;
    }
    
    public Money calcolaSconto (IVenditaRemote vendita) throws RemoteException{
        
        Money prezzoPieno = new Money();
        
        try {
            prezzoPieno = vendita.getTotal();
        } catch (InvalidMoneyException ex) {
            Logger.getLogger(ScontoPercentualeStrategy.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        prezzoPieno.subtract(this.ammontare);
        return prezzoPieno;
  
    }
}