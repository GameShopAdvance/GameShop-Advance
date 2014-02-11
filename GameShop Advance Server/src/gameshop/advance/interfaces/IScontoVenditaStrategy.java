/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces;

import gameshop.advance.utility.Money;

/**
 *
 * @author Salx
 */
public interface IScontoVenditaStrategy {
    
    Money getTotal(ITransazione vendita);

    public boolean isActual();

}
