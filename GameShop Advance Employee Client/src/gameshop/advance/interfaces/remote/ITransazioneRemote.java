/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces.remote;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.utility.Money;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Interfaccia remota per esportare Transazione sui client tramite Java RMI.
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public interface ITransazioneRemote extends Remote {

    /**
     * Utilizza il totale della transazione e il pagamento ricevuto per calcolare
     * il resto.
     * @return il resto da restituire
     * @throws InvalidMoneyException
     */
    Money getResto() throws InvalidMoneyException, RemoteException;

    /**
     * @return
     * @throws RemoteException
     */
    List getRigheDiVendita() throws RemoteException;

    /**
     * Implementazione del metodo dell'interfaccia di vendita.Calcola il totale
     * della transazione sommando i valori sub-totali di tutte le righe di vendita
     * della transazione.
     * @return il totale della transazione
     */
    Money getTotal() throws RemoteException;
    
    /**
     * @return
     * @throws RemoteException
     */
    Integer getId() throws RemoteException;

}
