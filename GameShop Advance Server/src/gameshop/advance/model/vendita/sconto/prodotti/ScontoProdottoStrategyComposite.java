/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.vendita.sconto.prodotti;

import gameshop.advance.interfaces.IScontoProdottoStrategy;
import gameshop.advance.interfaces.IVendita;
import gameshop.advance.model.vendita.RigaDiVendita;
import gameshop.advance.utility.Money;
import java.util.LinkedList;
import java.util.List;

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
    public boolean isValid()
    {
        return true;
    }
    
    @Override
    public abstract Money getSubtotal(IVendita v, RigaDiVendita rdv);
    
    public Money getRealSubtotal(RigaDiVendita rdv)
    {
        Money subtotal =  rdv.getDescrizione().getPrezzo().multiply(rdv.getQuantity());
        System.err.println("Sottototale non scontato: "+subtotal);
        return subtotal;
    }
}
