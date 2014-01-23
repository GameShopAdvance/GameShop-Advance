/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.vendita.sconto.vendita;

import gameshop.advance.model.vendita.sconto.IScontoVenditaStrategy;
import gameshop.advance.model.vendita.IVendita;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class ScontoVenditaMigliorePerClienteStrategyComposite extends ScontoVenditaStrategyComposite{

    @Override
    public Money getTotal(IVendita vendita) {
        List<IScontoVenditaStrategy> components = super.getComponents();
        Money minimoTotale = super.getRealTotal(vendita);
        for(IScontoVenditaStrategy strategy: components)
        {
            try {
                Money total = strategy.getTotal(vendita);
                if(minimoTotale.greater(total))
                    minimoTotale = total;
            } catch (RemoteException ex) {
                Logger.getLogger(ScontoVenditaMigliorePerClienteStrategyComposite.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return minimoTotale;
    }
    
}
