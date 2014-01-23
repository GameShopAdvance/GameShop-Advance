/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.vendita.sconto.prodotti;

import gameshop.advance.model.vendita.sconto.IScontoProdottoStrategy;
import gameshop.advance.model.vendita.IVendita;
import gameshop.advance.model.vendita.RigaDiVendita;
import gameshop.advance.utility.Money;
import java.util.List;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class ScontoProdottoMigliorePerVenditoreStrategyComposite extends ScontoProdottoStrategyComposite {
    
    
    @Override
    public Money getSubtotal(IVendita v, RigaDiVendita rdv) 
    {
        List<IScontoProdottoStrategy> components = super.getComponents();
        Money massimaSpesa = super.getRealSubtotal(rdv);
        for(IScontoProdottoStrategy sconto: components)
        {
            Money subtotal = sconto.getSubtotal(v, rdv);
            if(subtotal.greater(massimaSpesa))
                massimaSpesa = subtotal;
        }
        return massimaSpesa;
    }

}
