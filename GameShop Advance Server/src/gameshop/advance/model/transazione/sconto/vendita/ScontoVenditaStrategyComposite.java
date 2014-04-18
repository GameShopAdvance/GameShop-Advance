package gameshop.advance.model.transazione.sconto.vendita;

import gameshop.advance.interfaces.IScontoVenditaStrategy;
import gameshop.advance.interfaces.ITransazione;
import gameshop.advance.interfaces.remote.utility.IIteratorWrapperRemote;
import gameshop.advance.interfaces.remote.sales.IRigaDiTransazioneRemote;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public abstract class ScontoVenditaStrategyComposite implements IScontoVenditaStrategy {

    private LinkedList<IScontoVenditaStrategy> components = new LinkedList<>();
    
    @Override
    public abstract Money getTotal(ITransazione vendita) throws RemoteException;
    
    public void add(IScontoVenditaStrategy sconto){
        this.components.add(sconto);
    }
    
    protected List getComponents()
    {
        return this.components;
    }
    
    protected Money getRealTotal(ITransazione vendita) throws RemoteException
    {
        IIteratorWrapperRemote<IRigaDiTransazioneRemote> righe = vendita.getRigheDiVendita();
        Money totale = new Money();
        while(righe.hasNext())
        {
            totale = totale.add(righe.next().getSubTotal());

        }
        return totale;
    }
        
}
