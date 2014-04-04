/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.transazione.sconto.prodotti;

import gameshop.advance.interfaces.IScontoProdottoStrategy;
import gameshop.advance.interfaces.ITransazione;
import gameshop.advance.model.transazione.RigaDiTransazione;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class ScontoProdottoMigliorePerVenditoreStrategyComposite extends ScontoProdottoStrategyComposite {

    public ScontoProdottoMigliorePerVenditoreStrategyComposite(ITransazione trans) {
        super(trans);
    }
    
    
    @Override
    public Money getSubtotal(RigaDiTransazione rdv, ITransazione trans) throws RemoteException 
    {
        List<IScontoProdottoStrategy> components = super.getComponents();
        Money massimaSpesa = new Money();
        for(IScontoProdottoStrategy sconto: components)
        {
            Money subtotal = sconto.getSubtotal(rdv, this.getTransazione());
            if(subtotal.greater(massimaSpesa))
                massimaSpesa = subtotal;
        }
        return massimaSpesa;
    }

}
