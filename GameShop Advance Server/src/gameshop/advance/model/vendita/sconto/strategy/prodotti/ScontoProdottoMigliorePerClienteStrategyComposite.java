/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.vendita.sconto.strategy.prodotti;

import gameshop.advance.model.vendita.RigaDiVendita;
import gameshop.advance.model.vendita.Vendita;
import gameshop.advance.utility.Money;
import java.util.List;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class ScontoProdottoMigliorePerClienteStrategyComposite extends ScontoProdottoStrategyComposite {

    
    @Override
    public Money getSubtotal(Vendita v, RigaDiVendita rdv) 
    {
        List<IScontoProdottoStrategy> components = super.getComponents();
        Money minimaSpesa = new Money(Double.MAX_VALUE);
        for(IScontoProdottoStrategy sconto: components)
        {
            Money subtotal = sconto.getSubtotal(v, rdv);
            if(minimaSpesa.greater(subtotal))
                minimaSpesa = subtotal;
        }
        return minimaSpesa;
    }

    
}
