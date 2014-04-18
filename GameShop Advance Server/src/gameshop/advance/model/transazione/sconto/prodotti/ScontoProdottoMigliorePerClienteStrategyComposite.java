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

/** Stategy composita migliore per cliente
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class ScontoProdottoMigliorePerClienteStrategyComposite extends ScontoProdottoStrategyComposite {

    public ScontoProdottoMigliorePerClienteStrategyComposite(ITransazione trans) {
        super(trans);
    }

    /**
     * @param rdv
     * @param trans 
     * @return Il sottototale con eventuali sconti applicati
     * @throws java.rmi.RemoteException
     */
    @Override
    public Money getSubtotal(RigaDiTransazione rdv, ITransazione trans) throws RemoteException
    {
        List<IScontoProdottoStrategy> components = super.getComponents();
        Money minimaSpesa = super.getRealSubtotal(rdv, this.getTransazione().getDate());
        for(IScontoProdottoStrategy sconto: components)
        {
            Money subtotal = sconto.getSubtotal(rdv, this.getTransazione());
            if(minimaSpesa.greater(subtotal))
                minimaSpesa = subtotal;
        }
        return minimaSpesa;
    }

    
}
