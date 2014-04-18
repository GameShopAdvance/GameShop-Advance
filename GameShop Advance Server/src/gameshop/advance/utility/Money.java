/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.utility;

import java.io.Serializable;
import java.util.Currency;
import java.util.Locale;

/**
 * Gestisce tutte le operazioni riguardanti il denaro.
 * 
 * @author Lorenzo Di Giuseppe
 */
public class Money implements Serializable{
    
    private long cents;
    private Currency currency;
    
    
    public Money() 
    {   
        this(0.00);
    }
    
    public Money(Double cents) 
    {   
          this(cents, Currency.getInstance(Locale.getDefault()));
    }
    
    public Money(Double cents, Currency cur)  {
            this.cents = cents.intValue() * 100;
            cents = cents * 100;
            this.cents += cents.intValue() - this.cents;
            this.currency = cur;
        
    }

    
    public long getCents()
    {
        return this.cents;
    }

    public Currency getCurrency()
    {
        return this.currency;
    }
    
    public void setCents(long cents)
    {
        this.cents = cents;
    }
    
    public Money add(Money m) 
    {
        Money result = new Money();
        result.setCents(this.cents+m.getCents());
        return result;
    }
    
    public Money subtract(Money m) 
    {
        Money result = new Money();
        long balance = this.cents-m.getCents();
        result.setCents(balance);
        return result;
        
    }
    
    public Money multiply(int k) 
    {
        Money result = new Money();
        result.setCents(this.cents*k);
        return result;
    }
    
    public Money divide(int k)
    {
        Money result = new Money();
        result.setCents(this.cents/k);
        return result;
    }
    
    public Boolean equals(Money m)
    {
        if(m!=null)
        {
            return this.cents == m.getCents() && this.currency.equals(m.getCurrency());
        }
        return false;
    }
    
    @Override
    public String toString()
    {
        long mainAmount = this.cents/100;
        long cents = this.cents %100;
        if(cents>10)
            return mainAmount+","+cents+this.currency;
        else
            return mainAmount+",0"+cents+this.currency;
    }
    
    
    public boolean greater(Money m)
    {
        if(this.getCurrency() == m.getCurrency())
            return this.cents > m.getCents();
        return false;
    }
    

}
