/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.transazione;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.interfaces.remote.IPrenotazioneRemote;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class PrenotazioneRemoteProxy implements IPrenotazioneRemote {

    private final IPrenotazioneRemote protectedRemoteObject;
    
    public PrenotazioneRemoteProxy(IPrenotazioneRemote pren)
    {
        this.protectedRemoteObject = pren;
    }
    
    @Override
    public Money getAcconto() throws RemoteException {
        return this.protectedRemoteObject.getAcconto();
    }

    @Override
    public Money getResto() throws InvalidMoneyException, RemoteException {
        return this.protectedRemoteObject.getResto();
    }

    @Override
    public List getRigheDiVendita() throws RemoteException {
        return this.protectedRemoteObject.getRigheDiVendita();
    }

    @Override
    public Money getTotal() throws RemoteException {
        return this.protectedRemoteObject.getTotal();
    }

    @Override
    public Integer getId() throws RemoteException {
        return this.protectedRemoteObject.getId();
    }
    
    public Money getRestoAcconto() throws RemoteException{
        return this.protectedRemoteObject.getRestoAcconto();
    }
    
}
