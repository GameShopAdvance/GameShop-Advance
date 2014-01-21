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
        
        prezzoPieno.divide(100);
        prezzoPieno.multiply(this.percentuale);
        return prezzoPieno;
  
    }
}