/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.exceptions.sales.AlreadyPartialPayedException;
import gameshop.advance.exceptions.sales.InvalidSaleState;
import gameshop.advance.interfaces.remote.sales.reservations.IPrenotazioneRemote;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;

/** Interfaccia della Prenotazione
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public interface IPrenotazione extends ITransazione, IPrenotazioneRemote {

    void pagaAcconto(Money ammontare) throws RemoteException, InvalidMoneyException, InvalidSaleState, AlreadyPartialPayedException;
    
    public void evadi();
    
    public boolean getEvasa();

}
