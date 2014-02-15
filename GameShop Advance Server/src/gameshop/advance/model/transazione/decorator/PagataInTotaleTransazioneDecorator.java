/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.transazione.decorator;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.interfaces.ITransazione;
import gameshop.advance.interfaces.remote.IRemoteObserver;
import gameshop.advance.model.transazione.TransazioneRemoteProxy;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class PagataInTotaleTransazioneDecorator extends TransazioneDecorator {

    public PagataInTotaleTransazioneDecorator(ITransazione trans) {
        super(trans);
    }
    
    @Override
    public void gestisciPagamento(Money ammontare) throws RemoteException, InvalidMoneyException
    {
        if(ammontare.greater(this.getTotal()))
        {
            super.gestisciPagamento(ammontare.add(super.getPagamento()));
            
        }
        else
            throw new InvalidMoneyException(ammontare);
            
    }

    @Override
    void notificaListener() throws RemoteException {
        System.err.println("Calling listener");
        for(IRemoteObserver o:super.listeners)
        {
            o.notifica(new TransazioneRemoteProxy(this));
        }
    }

    @Override
    public Money getResto() throws InvalidMoneyException, RemoteException {
        return this.wrapped.getResto();
    }
    
    @Override
    public ITransazione unwrap()
    {
        return this.wrapped;
    }
}