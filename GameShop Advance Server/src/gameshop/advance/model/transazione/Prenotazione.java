/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.transazione;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.interfaces.remote.IPrenotazioneRemote;
import gameshop.advance.model.Pagamento;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class Prenotazione extends Vendita implements IPrenotazioneRemote {
    private Pagamento acconto;
    private int percentualeAcconto;

    public Prenotazione(int percentualeAcconto) {
        this.percentualeAcconto = percentualeAcconto;
    }
    
    public void pagaAcconto(Money ammontare) throws RemoteException, InvalidMoneyException {
        if(this.getAcconto().greater(ammontare))
            throw new InvalidMoneyException(ammontare);
        this.acconto = new Pagamento(ammontare);
    }

    @Override
    public Money getAcconto() throws RemoteException {
        return this.getTotal().multiply(percentualeAcconto).divide(100);
    }
    
    @Override
    public Money getRestoAcconto() throws RemoteException{
        return this.acconto.getAmmontare().subtract(this.getAcconto());
    }
    
}
