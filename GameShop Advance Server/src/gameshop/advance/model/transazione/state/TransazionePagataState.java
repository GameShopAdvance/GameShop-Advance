/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.transazione.state;

import gameshop.advance.interfaces.ITransazione;
import gameshop.advance.interfaces.ITransazioneState;
import gameshop.advance.utility.Money;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class TransazionePagataState implements ITransazioneState {

    @Override
    public void pagaAcconto(ITransazione trans) {
        
    }

    @Override
    public void gestisciPagamento(ITransazione trans, Money ammontare) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Money getTotal(ITransazione trans) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Money getResto(ITransazione trans) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
