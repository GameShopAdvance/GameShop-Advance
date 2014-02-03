package gameshop.advance.model.vendita;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.interfaces.IVendita;
import gameshop.advance.interfaces.remote.IVenditaRemoteDecorator;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
class VenditaRemoteProxy extends UnicastRemoteObject implements IVenditaRemoteDecorator {

    private final IVendita sale;
    
    public VenditaRemoteProxy(IVendita sale) throws RemoteException {
        this.sale = sale;
    }
    
    @Override
    public Money getTotal()  throws RemoteException, InvalidMoneyException 
    {
        return this.sale.getTotal();
    }

    @Override
    public Money getResto() throws RemoteException, InvalidMoneyException {
        return this.sale.getResto();
    }
    
}
