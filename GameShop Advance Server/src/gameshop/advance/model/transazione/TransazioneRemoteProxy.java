/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.transazione;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.interfaces.ITransazione;
import gameshop.advance.interfaces.remote.ITransazioneRemote;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class TransazioneRemoteProxy extends UnicastRemoteObject implements ITransazioneRemote {
    
    private ITransazione protectedRemoteTransaction;
    
    public TransazioneRemoteProxy(ITransazione trans) throws RemoteException
    {
        System.err.println("Creazione Proxy");
        this.protectedRemoteTransaction = trans;
    }

    @Override
    public Money getResto() throws InvalidMoneyException, RemoteException {
        return this.protectedRemoteTransaction.getResto();
    }

    @Override
    public List getRigheDiVendita() throws RemoteException {
        return this.protectedRemoteTransaction.getRigheDiVendita();
    }

    @Override
    public Money getTotal() throws RemoteException {
        return this.protectedRemoteTransaction.getTotal();
    }

    @Override
    public Integer getId() throws RemoteException {
        return this.protectedRemoteTransaction.getId();
    }
    
}
