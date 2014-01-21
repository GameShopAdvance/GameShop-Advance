/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.remote.interfaces;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.utility.Money;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public interface IVenditaRemote extends Remote {

    Money getTotal() throws RemoteException, InvalidMoneyException;
    
    Money getResto() throws RemoteException, InvalidMoneyException;
}
