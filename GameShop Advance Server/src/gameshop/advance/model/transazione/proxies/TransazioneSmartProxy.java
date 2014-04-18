/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.transazione.proxies;

import com.db4o.activation.ActivationPurpose;
import com.db4o.activation.Activator;
import com.db4o.ta.Activatable;
import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.exceptions.products.QuantityNotInStockException;
import gameshop.advance.exceptions.sales.AlreadyPayedException;
import gameshop.advance.exceptions.sales.InvalidSaleState;
import gameshop.advance.interfaces.IDescrizioneProdotto;
import gameshop.advance.interfaces.IScontoVenditaStrategy;
import gameshop.advance.interfaces.ITransazione;
import gameshop.advance.interfaces.remote.sales.IRigaDiTransazioneRemote;
import gameshop.advance.interfaces.remote.utility.IIteratorWrapperRemote;
import gameshop.advance.interfaces.remote.utility.IRemoteObserver;
import gameshop.advance.model.transazione.CartaCliente;
import gameshop.advance.model.transazione.sconto.vendita.ScontoVenditaStrategyComposite;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;
import java.util.LinkedList;
import org.joda.time.DateTime;

/**
 * Smart Proxy della Transazione estende Interfaccia Activable in modo tale che possa supportare la Transparent Activation
 *
 * @author Matteo Gentile
 */
public class TransazioneSmartProxy implements ITransazione, Activatable{
    
    private ITransazione trans;
    private transient Activator _activator;

    public TransazioneSmartProxy(ITransazione trans)
    {
        this.trans = trans;
    }
    
    @Override
    public void completaTransazione() throws RemoteException {
        this.trans.completaTransazione();
    }

    @Override
    public void inserisciProdotto(IDescrizioneProdotto desc, int quantity) throws RemoteException, QuantityNotInStockException {
        this.trans.inserisciProdotto(desc, quantity);
    }

    @Override
    public void gestisciPagamento(Money ammontare) throws InvalidMoneyException, RemoteException, InvalidSaleState, AlreadyPayedException {
        this.trans.gestisciPagamento(ammontare);
    }

    @Override
    public CartaCliente getCliente() {
        activate(ActivationPurpose.READ);
        return this.trans.getCliente();
    }

    @Override
    public void setCliente(CartaCliente c) throws RemoteException {
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
    public IIteratorWrapperRemote<IRigaDiTransazioneRemote> getRigheDiVendita() throws RemoteException {
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

    @Override
    public void annulla() throws RemoteException {
        this.trans.annulla();
    }
        
    }
