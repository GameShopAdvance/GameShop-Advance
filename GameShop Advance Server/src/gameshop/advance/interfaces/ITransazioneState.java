/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public interface ITransazioneState {
    
    void pagaAcconto(ITransazione trans) throws RemoteException;
    
    void gestisciPagamento(ITransazione trans, Money ammontare)  throws RemoteException, InvalidMoneyException;
    
    Money getTotal(ITransazione trans) throws RemoteException;
    
    Money getResto(ITransazione trans) throws RemoteException;
}
