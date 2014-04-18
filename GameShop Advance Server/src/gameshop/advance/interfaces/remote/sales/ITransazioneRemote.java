/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces.remote.sales;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.interfaces.remote.utility.IIteratorWrapperRemote;
import gameshop.advance.utility.Money;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *  Interfaccia remota per l'esportazione delle classi che la implementano tramite
 *  Java RMI.I metodi esportati da ITransazioneRemote sono necessari sia alla vendita che alla prenotazione.
 * 
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public interface ITransazioneRemote extends Remote {

    /**
     * Utilizza il totale della transazione e il pagamento ricevuto per calcolare
     * il resto.
     * @return il resto da restituire
     * @throws InvalidMoneyException
     * @throws java.rmi.RemoteException
     */
    Money getResto() throws InvalidMoneyException, RemoteException;

    /**
     * Implementazione del metodo dell'interfaccia di vendita.Calcola il totale
     * della transazione sommando i valori sub-totali di tutte le righe di vendita
     * della transazione.
     * @return il totale della transazione
     * @throws java.rmi.RemoteException
     */
    Money getTotal() throws RemoteException;
    
    /**
     *
     * @return L'id della transazione
     * @throws RemoteException
     */
    Integer getId() throws RemoteException;

    /**
     *
     * @return Le righe di transazione
     * @throws RemoteException
     */
    IIteratorWrapperRemote<IRigaDiTransazioneRemote> getRigheDiVendita() throws RemoteException;
    
}
