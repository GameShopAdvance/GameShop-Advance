/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces.remote;

import gameshop.advance.utility.IDProdotto;
import gameshop.advance.utility.Money;
import java.rmi.Remote;

/**
 *
 * @author Salx
 */
public interface IRemoteDescrizione extends Remote {
    
    public Money getPrezzo();
    
    public IDProdotto getCodiceProdotto();
    
    public String getDescrizione();
    
    public int getQuantitaDisponibile();
}
