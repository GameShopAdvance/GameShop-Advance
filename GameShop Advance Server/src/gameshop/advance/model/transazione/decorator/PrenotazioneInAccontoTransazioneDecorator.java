package gameshop.advance.model.transazione.decorator;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.interfaces.ITransazione;
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
public class PrenotazioneInAccontoTransazioneDecorator extends TransazioneDecorator {
    
     private static final int percentualeAcconto = 20;
     private Pagamento acconto;
    
     public PrenotazioneInAccontoTransazioneDecorator(ITransazione t) {
        super(t);
        ScontoVenditaStrategyComposite sconto = ScontoFactorySingleton.getInstance().getStrategiaScontoPrenotazione();
        super.setSconto(sconto);
    }
     
     @Override
    public void gestisciPagamento(Money ammontare) throws InvalidMoneyException, RemoteException
    {
        Money totaleAcconto = this.getTotal();
        if(ammontare.greater(totaleAcconto))
        {
            this.acconto = new Pagamento(ammontare);
            super.gestisciPagamento(ammontare.multiply(percentualeAcconto).divide(percentualeAcconto));
        }
        else 
            throw new InvalidMoneyException(ammontare);
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
        return this.acconto.getAmmontare().subtract(this.getTotal());
    }

     @Override
    public Money getTotal() throws RemoteException
    {
        Money totale = this.getTotal();
        return totale.multiply(percentualeAcconto).divide(100);
    }
    
    
     @Override
    public void completaTransazione()
    {
        //La transazione non può essere completata fino a che non verrà completamente pagata
    }
}
