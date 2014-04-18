/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces.remote;

import gameshop.advance.interfaces.remote.utility.IRemoteImage;
import gameshop.advance.utility.IDProdotto;
import gameshop.advance.utility.Money;
import java.rmi.Remote;
import java.rmi.RemoteException;
import org.joda.time.DateTime;

/**
 * Interfaccia remota della descrizione del prodotto.
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public interface IDescrizioneProdottoRemote extends Remote {
   
    /**
     *
     * @return
     * @throws RemoteException
     */
    IDProdotto getCodiceProdotto() throws RemoteException;

    /**
     * @return la descizione di un prodotto.
     * @throws java.rmi.RemoteException
     */
    String getDescrizione() throws RemoteException;

    /**
     * @param period
     * @return il prezzo di un prodotto.
     * @throws java.rmi.RemoteException
     */
    Money getPrezzo(DateTime period) throws RemoteException;
    
    /**
     * @return
     * @throws RemoteException
     */
    int getQuantitaDisponibile() throws RemoteException;
    
    /**
     * @return
     * @throws RemoteException
     */
    int getQuantitaDiSoglia() throws RemoteException;
    
    /**
     * @return
     * @throws RemoteException
     */
    IRemoteImage getImmagine() throws RemoteException;
    
    /**
     * @return
     * @throws RemoteException
     */
    String getNomeProdotto() throws RemoteException;
}
