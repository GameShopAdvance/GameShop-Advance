/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces;

import gameshop.advance.model.transazione.RigaDiTransazione;
import gameshop.advance.utility.Money;
import org.joda.time.DateTime;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public interface IScontoProdottoStrategy {
    
    public Money getSubtotal(ITransazione v, RigaDiTransazione rdv );
    
    /**
     *
     * @param period
     * @return
     */
    public boolean isValid(DateTime period);
    
    public void add(IScontoProdottoStrategy sp);
}
