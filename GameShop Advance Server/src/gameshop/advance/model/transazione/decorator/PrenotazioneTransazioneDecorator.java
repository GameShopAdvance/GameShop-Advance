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
import gameshop.advance.model.transazione.sconto.ScontoFactorySingleton;
import gameshop.advance.model.transazione.sconto.vendita.ScontoVenditaStrategyComposite;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;

/**
 *
 * @author matteog
 */
public class PrenotazioneTransazioneDecorator extends TransazioneDecorator {
    
     public PrenotazioneTransazioneDecorator(ITransazione t) {
        super(t);
        ScontoVenditaStrategyComposite sconto = ScontoFactorySingleton.getInstance().getStrategiaScontoPrenotazione();
        super.setSconto(sconto);
    }
     
     @Override
    public void gestisciPagamento(Money ammontare) throws InvalidMoneyException, RemoteException
    {
        Money acconto = super.getPagamento();
        Money zero = new Money();
        if(acconto.greater(zero))
        {
            if(ammontare.greater(zero))
                super.gestisciPagamento(acconto.add(ammontare));
            else
                throw new InvalidMoneyException("La quantità di denaro non è sufficiente a pagare la prenotazione");
        }
        else
            super.gestisciPagamento(ammontare);
        
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
        Money resto = this.wrapped.getResto();
        Money zero = new Money();
        if(zero.greater(resto))
            //resto per pagamento in acconto
            return zero;
        else
            //resto per pagamento totale
            return resto;
    }

     @Override
    public Money getTotal() throws RemoteException
    {
        Money totale = super.getTotal();
        Money acconto = super.getPagamento();
        if(acconto.greater(totale))
            return new Money();
        else
            return totale.subtract(acconto);
    }
    
}
