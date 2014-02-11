/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.transazione.decorator;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.interfaces.ITransazione;
import gameshop.advance.model.DescrizioneProdotto;
import gameshop.advance.model.transazione.sconto.ScontoFactorySingleton;
import gameshop.advance.model.transazione.sconto.vendita.ScontoVenditaStrategyComposite;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class VenditaTransazioneDecorator extends TransazioneDecorator{

    public VenditaTransazioneDecorator(ITransazione trans) {
        super(trans);
        ScontoVenditaStrategyComposite sconto = ScontoFactorySingleton.getInstance().getStrategiaScontoVendita();
        super.setSconto(sconto);
        
    }
    
    @Override
    public void inserisciProdotto(DescrizioneProdotto desc, int quantity) throws RemoteException {
        super.inserisciProdotto(desc, quantity);
        super.notificaListeners();
    }    
    
    @Override 
    public void gestisciPagamento(Money ammontare) throws InvalidMoneyException, RemoteException {
        if(!ammontare.equals(this.getTotal()) && !ammontare.greater(this.getTotal()))
            throw new InvalidMoneyException(ammontare);
        super.gestisciPagamento(ammontare);
    }
}
