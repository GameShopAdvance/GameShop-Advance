/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.exceptions;

import gameshop.advance.utility.Money;

/**
 *
 * @author matteog
 */
public class InvalidMoneyException extends Exception {
        
    private Money amount;
        
    public InvalidMoneyException(Money amount) {
            this.amount = amount;
    }
    
    public InvalidMoneyException(String message) {
           super(message);
    }
    
    public Money getAmount()
   {
     return amount;
   }
    
}
