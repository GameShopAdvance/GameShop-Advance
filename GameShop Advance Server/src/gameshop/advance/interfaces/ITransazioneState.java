/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces;

import gameshop.advance.utility.Money;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public interface ITransazioneState {
    
    void pagaAcconto(ITransazione trans);
    
    void gestisciPagamento(ITransazione trans, Money ammontare);
    
    Money getTotal(ITransazione trans);
    
    Money getResto(ITransazione trans);
}
