/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.vendita.sconto.strategy;

import gameshop.advance.model.vendita.IVendita;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;

/**
 *
 * @author Salx
 */
public interface IScontoVenditaStrategy {
    
    Money getTotal(IVendita vendita) throws RemoteException;

}
