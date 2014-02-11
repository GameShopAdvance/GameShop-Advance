/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.vendita.sconto.vendita;

import gameshop.advance.interfaces.IScontoVenditaStrategy;
import gameshop.advance.interfaces.ITransazione;
import gameshop.advance.model.vendita.RigaDiVendita;
import gameshop.advance.utility.Money;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public abstract class ScontoVenditaStrategyComposite implements IScontoVenditaStrategy {

    private LinkedList<IScontoVenditaStrategy> components = new LinkedList<>();
    
    @Override
    public abstract Money getTotal(ITransazione vendita);
    
    public void add(IScontoVenditaStrategy sconto){
        this.components.add(sconto);
    }
    
    protected List getComponents()
    {
        return this.components;
    }
    
    protected Money getRealTotal(ITransazione vendita)
    {
        List<RigaDiVendita> righe = vendita.getRigheDiVendita();
        Money totale = new Money();
        for(RigaDiVendita riga:righe)
        {
            totale = totale.add(riga.getSubTotal(vendita));

        }
        return totale;
    }
        
}
