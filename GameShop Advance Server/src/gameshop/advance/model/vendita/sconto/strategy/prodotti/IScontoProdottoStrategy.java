/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.vendita.sconto.strategy.prodotti;

import gameshop.advance.model.vendita.RigaDiVendita;
import gameshop.advance.model.vendita.Vendita;
import gameshop.advance.utility.Money;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public interface IScontoProdottoStrategy {
    
    public Money getSubtotal( Vendita v, RigaDiVendita rdv );
    
    public boolean isValid();
    
    public void add(IScontoProdottoStrategy sp);
}
