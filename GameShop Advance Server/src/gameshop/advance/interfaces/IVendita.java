/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.model.vendita.CartaCliente;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public interface IVendita {

    List getRigheDiVendita();
    
    CartaCliente getCliente();
    
    Money getTotal() throws RemoteException, InvalidMoneyException;
    
    Money getResto() throws RemoteException, InvalidMoneyException;
}
