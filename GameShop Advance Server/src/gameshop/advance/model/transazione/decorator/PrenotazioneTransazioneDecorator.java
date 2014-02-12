/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.transazione.decorator;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.interfaces.remote.IRemoteObserver;
import gameshop.advance.model.Pagamento;
import gameshop.advance.model.transazione.TransazioneRemoteProxy;
import gameshop.advance.model.transazione.sconto.ScontoFactorySingleton;
import gameshop.advance.model.transazione.sconto.vendita.ScontoVenditaStrategyComposite;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;

/**
 *
 * @author matteog
 */
public class PrenotazioneTransazioneDecorator extends TransazioneDecorator {
    
    private Pagamento acconto;

    
     public PrenotazioneTransazioneDecorator(TransazioneDecorator t) {
        super(t);
        ScontoVenditaStrategyComposite sconto = ScontoFactorySingleton.getInstance().getStrategiaScontoVendita();
        super.setSconto(sconto);
    }
    
    public void gestisciAcconto(Money ammontare) throws InvalidMoneyException, RemoteException {
       if(ammontare.greater(this.getTotal())) {
            throw new InvalidMoneyException(ammontare);
        }
        this.acconto = new Pagamento(ammontare);
    }
    
    @Override
    protected void notificaListener() throws RemoteException
    {
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
}
