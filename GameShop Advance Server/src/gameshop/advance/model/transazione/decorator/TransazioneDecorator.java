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
    
    private ITransazione wrapped;
    
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
            this.listeners.remove()
    }
    
    
    @Override
    public void completaVendita() throws RemoteException {
        this.wrapped
    }

    @Override
    public void inserisciProdotto(DescrizioneProdotto desc, int quantity) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void gestisciPagamento(Money ammontare) throws InvalidMoneyException, RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CartaCliente getCliente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getIdVendita() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Money getResto() throws InvalidMoneyException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List getRigheDiVendita() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Money getTotal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCliente(CartaCliente c) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setIdVendita(Integer idVendita) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSconti(LinkedList<IScontoVenditaStrategy> scontiAttuali) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
