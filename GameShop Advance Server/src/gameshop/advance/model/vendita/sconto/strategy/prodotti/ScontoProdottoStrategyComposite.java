/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.vendita.sconto.strategy.prodotti;

import gameshop.advance.model.vendita.RigaDiVendita;
import gameshop.advance.model.vendita.Vendita;
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
    
    public void addComponent(IScontoProdottoStrategy sconto)
    {
        this.components.add(sconto);
    }
    
    public void addComponent(List<IScontoProdottoStrategy> components)
    {
        this.components.addAll(components);
    }
    
    @Override
    public abstract Money getSubtotal(Vendita v, RigaDiVendita rdv);
}
