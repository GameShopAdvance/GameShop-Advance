/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.transazione.decorator;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.interfaces.IScontoVenditaStrategy;
import gameshop.advance.interfaces.ITransazione;
import gameshop.advance.interfaces.remote.IRemoteObserver;
import gameshop.advance.model.DescrizioneProdotto;
import gameshop.advance.model.transazione.CartaCliente;
import gameshop.advance.model.transazione.TransazioneRemoteProxy;
import gameshop.advance.model.transazione.sconto.vendita.ScontoVenditaStrategyComposite;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class TransazioneDecorator implements ITransazione {
    
    /**
     *
     */
    protected ITransazione wrapped;
    
    private LinkedList<IRemoteObserver> listeners;
    
    public TransazioneDecorator(ITransazione trans)
    {
        this.wrapped = trans;
        this.listeners = new LinkedList<>();
    }
    
    public void rimuoviListener(IRemoteObserver obs)
    {
        if(obs==null)
            this.listeners=null;
        else
            this.listeners.remove(obs);
    }
    
    public void aggiungiListener(IRemoteObserver obs) {
        this.listeners.add(obs);
    }
    
    
    protected void notificaListeners() throws RemoteException
    {
        System.err.println("Calling listener");
        for(IRemoteObserver o:this.listeners)
        {
            o.notifica(new TransazioneRemoteProxy(this));
        }
    }
    
    @Override
    public void completaVendita() {
        this.wrapped.completaVendita();
    }

    @Override
    public void inserisciProdotto(DescrizioneProdotto desc, int quantity) throws RemoteException{
        this.wrapped.inserisciProdotto(desc, quantity);
    }

    @Override
    public void gestisciPagamento(Money ammontare) throws InvalidMoneyException, RemoteException {
        this.wrapped.gestisciPagamento(ammontare);
    }

    @Override
    public CartaCliente getCliente() {
        return this.wrapped.getCliente();
    }

    @Override
    public Integer getIdVendita() {
        return this.wrapped.getIdVendita();
    }

    @Override
    public Money getResto() throws InvalidMoneyException, RemoteException {
        return this.wrapped.getResto();
    }

    @Override
    public List getRigheDiVendita() throws RemoteException {
        return this.wrapped.getRigheDiVendita();
    }

    @Override
    public Money getTotal() throws RemoteException {
        return this.wrapped.getTotal();
    }

    @Override
    public void setCliente(CartaCliente c) {
        this.wrapped.setCliente(c);
    }

    @Override
    public void setIdVendita(Integer idVendita) {
        this.wrapped.setIdVendita(idVendita);
    }

    @Override
    public void setSconto(ScontoVenditaStrategyComposite sconto)
    {
        this.wrapped.setSconto(sconto);
    }
    
    @Override
    public void addSconti(LinkedList<IScontoVenditaStrategy> scontiAttuali) {
        this.wrapped.addSconti(scontiAttuali);
    }
    
    
}
