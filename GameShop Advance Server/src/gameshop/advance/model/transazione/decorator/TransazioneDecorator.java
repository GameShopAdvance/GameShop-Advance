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
    
    public void addListener(IRemoteObserver obs)
    {
        this.listeners.add(obs);
    }
    
    public void removeListener(IRemoteObserver obs)
    {
        if(obs==null)
            this.listeners=null;
        else
            this.listeners.remove(obs);
    }
    
    
    protected void notificaListeners() throws RemoteException
    {
        for(IRemoteObserver o:this.listeners)
        {
            o.notifica(new TransazioneDecoratorRemoteProxy(this));
        }
    }
    
    @Override
    public void completaVendita() throws RemoteException {
        this.wrapped.completaVendita();
    }

    @Override
    public void inserisciProdotto(DescrizioneProdotto desc, int quantity) throws RemoteException {
        this.wrapped.inserisciProdotto(desc, quantity);
    }

    @Override
    public void gestisciPagamento(Money ammontare) throws InvalidMoneyException, RemoteException {
        this.wrapped.gestisciPagamento(ammontare);;
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
    public Money getResto() throws InvalidMoneyException {
        return this.wrapped.getResto();
    }

    @Override
    public List getRigheDiVendita() {
        return this.wrapped.getRigheDiVendita();
    }

    @Override
    public Money getTotal() {
        return this.wrapped.getTotal();
    }

    @Override
    public void setCliente(CartaCliente c) throws RemoteException {
        this.wrapped.setCliente(c);
    }

    @Override
    public void setIdVendita(Integer idVendita) {
        this.wrapped.setIdVendita(idVendita);
    }

    @Override
    public void setSconti(LinkedList<IScontoVenditaStrategy> scontiAttuali) {
        this.wrapped.setSconti(scontiAttuali);
    }
    
}
