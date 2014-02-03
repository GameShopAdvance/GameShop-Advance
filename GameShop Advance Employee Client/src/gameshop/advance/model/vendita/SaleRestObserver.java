/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.vendita;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.interfaces.remote.IRemoteClient;
import gameshop.advance.interfaces.remote.IRemoteObserver;
import gameshop.advance.interfaces.remote.IVenditaRemoteDecorator;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class SaleRestObserver extends UnicastRemoteObject implements IRemoteObserver {
    private IRemoteClient client;

    public SaleRestObserver(IRemoteClient client) throws RemoteException {
        this.client = client;
    }
    
    @Override
    public void notifica(IVenditaRemoteDecorator o) throws RemoteException {
        IVenditaRemoteDecorator sale = (IVenditaRemoteDecorator) o;
        try {        
            this.client.aggiornaResto(sale.getResto());
        } catch (InvalidMoneyException ex) {
             throw new RemoteException(ex.getMessage());
        }
    }

    
}
