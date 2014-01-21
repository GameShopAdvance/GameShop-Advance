/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.vendita;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.remote.interfaces.IRemoteClient;
import gameshop.advance.remote.interfaces.IRemoteObserver;
import gameshop.advance.remote.interfaces.IVenditaRemote;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SaleRestObserver implements IRemoteObserver, Serializable {
    private IRemoteClient client;

    public SaleRestObserver(IRemoteClient client) throws RemoteException {
        this.client = client;
    }
    
    @Override
    public void notifica(Object o) throws RemoteException {
        IVenditaRemote sale = (IVenditaRemote) o;
        try {        
            this.client.aggiornaResto(sale.getResto());
        } catch (InvalidMoneyException ex) {
            Logger.getLogger(SaleRestObserver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
