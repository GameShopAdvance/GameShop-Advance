/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.transazione;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.exceptions.products.QuantityNotInStockException;
import gameshop.advance.exceptions.sales.AlreadyPartialPayedException;
import gameshop.advance.exceptions.sales.InvalidSaleState;
import gameshop.advance.interfaces.IDescrizioneProdotto;
import gameshop.advance.interfaces.IPrenotazione;
import gameshop.advance.interfaces.remote.utility.IRemoteObserver;
import gameshop.advance.model.Pagamento;
import gameshop.advance.technicalservices.LoggerSingleton;
import gameshop.advance.utility.Money;
import java.rmi.ConnectException;
import java.rmi.RemoteException;
import java.util.Iterator;

/** 
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class Prenotazione extends Vendita implements IPrenotazione {
    private Pagamento acconto;
    private final int percentualeAcconto;
    private boolean evasa = false;

    public Prenotazione(int percentualeAcconto) {
        super();
        this.percentualeAcconto = percentualeAcconto;
    }
    /**
     * @return Booleano risultato del controllo sul pagamento in acconto
     * @throws java.rmi.RemoteException
     */
    public boolean pagataAcconto() throws RemoteException
    {
        if(this.acconto == null)
            return false;
        else
            return true;
    }
    /**
     * @param ammontare
     * @throws java.rmi.RemoteException
     * @throws gameshop.advance.exceptions.InvalidMoneyException
     * @throws gameshop.advance.exceptions.sales.InvalidSaleState
     * @throws gameshop.advance.exceptions.sales.AlreadyPartialPayedException
     * @throws gameshop.advance.exceptions.sales.AlredyPayedException
     */
    @Override
    public void pagaAcconto(Money ammontare) throws RemoteException, InvalidMoneyException, InvalidSaleState, AlreadyPartialPayedException {
        if(!this.completata)
            throw new InvalidSaleState();
        if(this.pagataAcconto() || this.pagataTotale())
            throw new AlreadyPartialPayedException();
        if(this.getAcconto().greater(ammontare))
            throw new InvalidMoneyException(ammontare);
        this.acconto = new Pagamento(ammontare);
        this.notificaListener();
    }
    /**
     * @return importo dell'acconto
     * @throws java.rmi.RemoteException
     */
    @Override
    public Money getAcconto() throws RemoteException {
        return super.getTotal().multiply(percentualeAcconto).divide(100);
    }
    /**
     * @return il resto dell'acconto
     * @throws java.rmi.RemoteException
     */
    @Override
    public Money getRestoAcconto() throws RemoteException{
        System.err.println("GET RESTO ACCONTO");
        return this.acconto.getAmmontare().subtract(this.getAcconto());
    }
    /**
     * @return il totale della prenotazione
     * @throws java.rmi.RemoteException
     */
    @Override
    public Money getTotal() throws RemoteException{
        Money total = super.getTotal();
        if(this.pagataAcconto())
            return total.subtract(this.getAcconto());
        else
            return total;
    }
    
    @Override
    protected void notificaListener() throws RemoteException {
        Iterator<IRemoteObserver> listeners = this.getListeners().iterator();
        while(listeners.hasNext()){
            IRemoteObserver obs = listeners.next();
            try{
                obs.notifica(new PrenotazioneRemoteProxy(this));
            }catch(ConnectException ex)
            {
                this.rimuoviListener(obs);
                LoggerSingleton.getInstance().log(ex);
            }
            catch(Exception ex){
         
                LoggerSingleton.getInstance().log(ex);
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
