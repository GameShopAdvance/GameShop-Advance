package gameshop.advance.model.transazione.sconto.vendita;

import gameshop.advance.interfaces.IScontoVenditaStrategy;
import gameshop.advance.interfaces.ITransazione;
import gameshop.advance.model.transazione.RigaDiVendita;
import gameshop.advance.utility.Money;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public abstract class ScontoVenditaStrategyComposite implements IScontoVenditaStrategy {

    private LinkedList<IScontoVenditaStrategy> components = new LinkedList<>();
    
    @Override
    public abstract Money getTotal(ITransazione vendita);
    
    public void add(IScontoVenditaStrategy sconto){
        this.components.add(sconto);
    }
    
    protected List getComponents()
    {
        return this.components;
    }
    
    protected Money getRealTotal(ITransazione vendita)
    {
        List<RigaDiVendita> righe = vendita.getRigheDiVendita();
        Money totale = new Money();
        for(RigaDiVendita riga:righe)
        {
            totale = totale.add(riga.getSubTotal(vendita));

        }
        return totale;
    }
        
}
