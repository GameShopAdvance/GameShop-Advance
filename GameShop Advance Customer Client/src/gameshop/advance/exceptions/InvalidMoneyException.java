/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.exceptions;

import gameshop.advance.utility.Money;

/**
 * Classe che gestisce le eccezioni riguardanti il denaro.
 * @author matteog
 */
public class InvalidMoneyException extends Exception {
        
    private Money amount;
        
    /**
     * @param amount
     */
    public InvalidMoneyException(Money amount) {
            this.amount = amount;
    }
    
    /**
     * @param message
     */
    public InvalidMoneyException(String message) {
           super(message);
    }
    
    /**
     * @return
     */
    public Money getAmount()
   {
     return amount;
   }
    
}
