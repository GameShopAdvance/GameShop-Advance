/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.transazione.sconto.prodotti;

import gameshop.advance.interfaces.IScontoProdottoStrategy;
import gameshop.advance.interfaces.ITransazione;
import gameshop.advance.model.transazione.RigaDiTransazione;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import org.joda.time.DateTime;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public abstract class ScontoProdottoStrategyComposite implements IScontoProdottoStrategy{
    private ITransazione transazione;
    private LinkedList<IScontoProdottoStrategy> components = new LinkedList<>();
    
    public ScontoProdottoStrategyComposite(ITransazione trans){
        this.transazione = trans;
    }
    
    protected ITransazione getTransazione()
    {
        return this.transazione;
    }
    
    protected List<IScontoProdottoStrategy> getComponents()
    {
        return this.components;
    }
    
    @Override
    public void add(IScontoProdottoStrategy sconto)
    {
        this.components.add(sconto);
    }
    
    public void add(List<IScontoProdottoStrategy> components)
    {
        this.components.addAll(components);
    }
    
    @Override
    public boolean isValid(DateTime period)
    {
        return true;
    }
    
    @Override
    public abstract Money getSubtotal(RigaDiTransazione rdv, ITransazione trans) throws RemoteException;
    
    public Money getRealSubtotal(RigaDiTransazione rdv, DateTime period) throws RemoteException
    {
        Money subtotal =  rdv.getDescrizione().getPrezzo(period).multiply(rdv.getQuantity());
        return subtotal;
    }
}
