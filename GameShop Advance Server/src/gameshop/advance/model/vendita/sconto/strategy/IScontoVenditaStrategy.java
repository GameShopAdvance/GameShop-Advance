/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.vendita.sconto.strategy;

import gameshop.advance.remote.interfaces.IVenditaRemote;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;

/**
 *
 * @author Salx
 */
public interface IScontoVenditaStrategy {
    
    Money getTotal(IVenditaRemote vendita) throws RemoteException;

}
