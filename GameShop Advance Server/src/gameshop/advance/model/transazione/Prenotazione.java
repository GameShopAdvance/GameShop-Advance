/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.transazione;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.exceptions.QuantityNotInStockException;
import gameshop.advance.interfaces.IDescrizioneProdotto;
import gameshop.advance.interfaces.IPrenotazione;
import gameshop.advance.interfaces.remote.IRemoteObserver;
import gameshop.advance.model.Pagamento;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class Prenotazione extends Vendita implements IPrenotazione {
    private Pagamento acconto;
    private int percentualeAcconto;
    private boolean evasa = false;

    public Prenotazione(int percentualeAcconto) {
        super();
        this.percentualeAcconto = percentualeAcconto;
    }
    
    @Override
    public void pagaAcconto(Money ammontare) throws RemoteException, InvalidMoneyException {
        if(this.getAcconto().greater(ammontare))
            throw new InvalidMoneyException(ammontare);
        this.acconto = new Pagamento(ammontare);
    }

    @Override
    public Money getAcconto() throws RemoteException {
        return super.getTotal().multiply(percentualeAcconto).divide(100);
    }
    
    @Override
    public Money getRestoAcconto() throws RemoteException{
        return this.acconto.getAmmontare().subtract(this.getAcconto());
    }
    
    @Override
    protected void notificaListener() throws RemoteException {
        List<IRemoteObserver> listeners = this.getListeners();
        if(listeners != null)
        {
            for(IRemoteObserver obs:listeners) {
                obs.notifica(new PrenotazioneRemoteProxy(this));
            }
        }
    }
    
    @Override
    protected void quantityCheck(IDescrizioneProdotto desc, int quantity) throws QuantityNotInStockException, RemoteException{
        
    }
    
    @Override
    public void evadi()
    {
        this.evasa = true;
    }
    
    @Override
    public boolean getEvasa()
    {
        return this.evasa;
    }
}
