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
import gameshop.advance.interfaces.IDescrizioneProdotto;
import gameshop.advance.interfaces.IPrenotazione;
import gameshop.advance.interfaces.IScontoVenditaStrategy;
import gameshop.advance.interfaces.remote.IRemoteObserver;
import gameshop.advance.model.transazione.CartaCliente;
import gameshop.advance.model.transazione.sconto.vendita.ScontoVenditaStrategyComposite;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import org.joda.time.DateTime;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class PrenotazioneSmartProxy implements IPrenotazione, Activatable {

    private IPrenotazione prenotazione;
    
    private transient Activator _activator;
    
    public PrenotazioneSmartProxy(IPrenotazione prenotazione)
    {
        this.prenotazione = prenotazione;
    }
    
    @Override
    public void pagaAcconto(Money ammontare) throws RemoteException, InvalidMoneyException {
        this.prenotazione.pagaAcconto(ammontare);
    }

    @Override
    public void completaTransazione() throws RemoteException {
        this.prenotazione.completaTransazione();
    }

    @Override
    public void inserisciProdotto(IDescrizioneProdotto desc, int quantity) throws RemoteException {
        this.prenotazione.inserisciProdotto(desc, quantity);
    }

    @Override
    public void gestisciPagamento(Money ammontare) throws InvalidMoneyException, RemoteException {
        this.prenotazione.gestisciPagamento(ammontare);
    }

    @Override
    public CartaCliente getCliente() {
        this.activate(ActivationPurpose.READ);
        return this.prenotazione.getCliente();
    }

    @Override
    public void setCliente(CartaCliente c) {
        this.prenotazione.setCliente(c);
    }

    @Override
    public void setId(Integer idVendita) throws RemoteException {
        this.prenotazione.setId(idVendita);
    }

    @Override
    public Money getPagamento() {
        this.activate(ActivationPurpose.READ);
        return this.prenotazione.getPagamento();
    }

    @Override
    public void addSconti(LinkedList<IScontoVenditaStrategy> scontiAttuali) throws RemoteException {
        this.prenotazione.addSconti(scontiAttuali);
    }

    @Override
    public void setStrategiaSconto(ScontoVenditaStrategyComposite sconto) {
        this.prenotazione.setStrategiaSconto(sconto);
    }

    @Override
    public boolean isCompleted() {
        return this.prenotazione.isCompleted();
    }

    @Override
    public void aggiungiListener(IRemoteObserver obs) {
        this.prenotazione.aggiungiListener(obs);
    }

    @Override
    public void rimuoviListener(IRemoteObserver obs) {
        this.prenotazione.rimuoviListener(obs);
    }

    @Override
    public DateTime getDate() {
        this.activate(ActivationPurpose.READ);
        return this.prenotazione.getDate();
    }

    @Override
    public Money getResto() throws InvalidMoneyException, RemoteException {
        return this.prenotazione.getResto();
    }

    @Override
    public List getRigheDiVendita() throws RemoteException {
        return this.prenotazione.getRigheDiVendita();
    }

    @Override
    public Money getTotal() throws RemoteException {
        this.activate(ActivationPurpose.READ);
        return this.prenotazione.getTotal();
    }

    @Override
    public Integer getId() throws RemoteException {
        this.activate(ActivationPurpose.READ);
        return this.prenotazione.getId();
    }

    @Override
    public Money getAcconto() throws RemoteException {
        this.activate(ActivationPurpose.READ);
        return this.prenotazione.getAcconto();
    }

    @Override
    public Money getRestoAcconto() throws RemoteException {
        return this.prenotazione.getRestoAcconto();
    }

    @Override
    public void bind(Activator activator) {
        if (_activator == activator) {
            return;
        }
        if (activator != null && _activator != null) {
            throw new IllegalStateException();
        }
        _activator = activator;
    }

    @Override
    public void activate(ActivationPurpose purpose) {
        if(_activator != null) {
            _activator.activate(purpose);
        }
    }
    
}