/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.vendita.sconto.prodotti;

import gameshop.advance.interfaces.IScontoProdottoStrategy;
import gameshop.advance.interfaces.ITransazione;
import gameshop.advance.model.vendita.RigaDiVendita;
import gameshop.advance.utility.Money;
import java.util.List;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class ScontoProdottoMigliorePerClienteStrategyComposite extends ScontoProdottoStrategyComposite {

    /**
     *
     * @param v
     * @param rdv
     * @return
     */
    @Override
    public Money getSubtotal(ITransazione v, RigaDiVendita rdv)
    {
        List<IScontoProdottoStrategy> components = super.getComponents();
        Money minimaSpesa = super.getRealSubtotal(rdv);
        for(IScontoProdottoStrategy sconto: components)
        {
            Money subtotal = sconto.getSubtotal(v, rdv);
            if(minimaSpesa.greater(subtotal))
                minimaSpesa = subtotal;
        }
        return minimaSpesa;
    }

    
}
