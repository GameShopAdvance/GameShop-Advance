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
import java.util.LinkedList;
import java.util.List;
import org.joda.time.DateTime;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public abstract class ScontoProdottoStrategyComposite implements IScontoProdottoStrategy{
    
    private LinkedList<IScontoProdottoStrategy> components = new LinkedList<>();
    
    protected List<IScontoProdottoStrategy> getComponents()
    {
        return this.components;
    }
    
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
    public abstract Money getSubtotal(ITransazione v, RigaDiTransazione rdv);
    
    public Money getRealSubtotal(RigaDiTransazione rdv, DateTime period)
    {
        Money subtotal =  rdv.getDescrizione().getPrezzo(period).multiply(rdv.getQuantity());
        return subtotal;
    }
}
