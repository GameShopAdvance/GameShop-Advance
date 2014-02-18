/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.transazione.state;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.interfaces.ITransazione;
import gameshop.advance.interfaces.ITransazioneState;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class TransazioneNonPagataState implements ITransazioneState {

    public TransazioneNonPagataState() {
    }
    
    @Override
    public void pagaAcconto(ITransazione trans) throws RemoteException {
        trans.setState(new TransazionePagataInAccontoState());
    }

    @Override
    public void gestisciPagamento(ITransazione trans, Money ammontare) throws RemoteException, InvalidMoneyException{
        Money total = trans.getTotal();
        if(total.greater(ammontare))
            throw new InvalidMoneyException(ammontare);
        else
            trans.setPagamento(ammontare);
    }

    @Override
    public Money getTotal(ITransazione trans) throws RemoteException {
        return trans.getScontoVendita().getTotal(trans);
    }

    @Override
    public Money getResto(ITransazione trans) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
