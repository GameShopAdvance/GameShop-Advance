/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces;

import com.db4o.activation.ActivationPurpose;
import com.db4o.activation.Activator;
import com.db4o.ta.Activatable;
import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.interfaces.remote.IRemoteObserver;
import gameshop.advance.model.DescrizioneProdotto;
import gameshop.advance.model.transazione.CartaCliente;
import gameshop.advance.model.transazione.sconto.vendita.ScontoVenditaStrategyComposite;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import org.joda.time.DateTime;

/**
 *
 * @author matteog
 */
public class TransazioneSmartProxy implements ITransazione, Activatable{
    
    private ITransazione trans;
    private transient Activator _activator;

    @Override
    public void completaTransazione() throws RemoteException {
        this.trans.completaTransazione();
    }

    @Override
    public void inserisciProdotto(DescrizioneProdotto desc, int quantity) throws RemoteException {
        this.trans.inserisciProdotto(desc, quantity);
    }

    @Override
    public void gestisciPagamento(Money ammontare) throws InvalidMoneyException, RemoteException {
        this.trans.gestisciPagamento(ammontare);
    }

    @Override
    public CartaCliente getCliente() {
        activate(ActivationPurpose.READ);
        return this.trans.getCliente();
    }

    @Override
    public void setCliente(CartaCliente c) {
        this.trans.setCliente(c);
    }

    @Override
    public void setId(Integer idVendita) throws RemoteException {
       this.trans.setId(idVendita);
    }

    @Override
    public Money getPagamento() {
        return this.trans.getPagamento();
    }

    @Override
    public void addSconti(LinkedList<IScontoVenditaStrategy> scontiAttuali) throws RemoteException {
        this.trans.addSconti(scontiAttuali);
    }

    @Override
    public void setStrategiaSconto(ScontoVenditaStrategyComposite sconto) {
        this.trans.setStrategiaSconto(sconto);
    }

    @Override
    public boolean isCompleted() {
            return this.isCompleted();
    }

    @Override
    public void aggiungiListener(IRemoteObserver obs) {
        this.aggiungiListener(obs);
    }

    @Override
    public void rimuoviListener(IRemoteObserver obs) {
        this.trans.rimuoviListener(obs);
    }

    @Override
    public DateTime getDate() {
         activate(ActivationPurpose.READ);
        return this.trans.getDate();
    }

    @Override
    public Money getResto() throws InvalidMoneyException, RemoteException {
         activate(ActivationPurpose.READ);
        return this.getResto();
    }

    @Override
    public List getRigheDiVendita() throws RemoteException {
         activate(ActivationPurpose.READ);
        return this.trans.getRigheDiVendita();
    }

    @Override
    public Money getTotal() throws RemoteException {
         activate(ActivationPurpose.READ);
        return this.trans.getTotal();
    }

    @Override
    public Integer getId() throws RemoteException {
        activate(ActivationPurpose.READ);
        return this.trans.getId();
    }

    @Override
    public void bind(Activator actvtr) {
         if (_activator == actvtr) {
            return;
        }
        if (actvtr != null && _activator != null) {
            throw new IllegalStateException();
        }
        _activator = actvtr;
    }

    @Override
    public void activate(ActivationPurpose ap) {
      if(_activator != null) {
            _activator.activate(ap);
        }
    }
        
    }
