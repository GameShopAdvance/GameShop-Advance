/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.vendita.sconto.vendita;

import gameshop.advance.interfaces.IScontoVenditaStrategy;
import gameshop.advance.interfaces.IVendita;
import gameshop.advance.utility.Money;
import java.util.List;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class ScontoVenditaMigliorePerClienteStrategyComposite extends ScontoVenditaStrategyComposite{

    /**
     *
     * @param vendita
     * @return
     */
    @Override
    public Money getTotal(IVendita vendita) {
        List<IScontoVenditaStrategy> components = super.getComponents();
        Money minimoTotale = super.getRealTotal(vendita);
        for(IScontoVenditaStrategy strategy: components)
        {
            Money total = strategy.getTotal(vendita);
            if(minimoTotale.greater(total))
                minimoTotale = total;
         
        }
        return minimoTotale;
    }

    @Override
    public boolean isActual() {
        return false;
    }
    
}
