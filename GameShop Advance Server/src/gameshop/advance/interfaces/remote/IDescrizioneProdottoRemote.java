/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces.remote;

import gameshop.advance.utility.IDProdotto;
import gameshop.advance.utility.Money;
import java.rmi.Remote;
import java.rmi.RemoteException;
import javax.swing.ImageIcon;
import org.joda.time.DateTime;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public interface IDescrizioneProdottoRemote extends Remote {

    
    ImageIcon getImmagine() throws RemoteException;
    
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
     * @return il prezzo di un prodotto.
     * @throws java.rmi.RemoteException
     */
    Money getPrezzo(DateTime period) throws RemoteException;
    
    int getQuantitaDisponibile() throws RemoteException;
    
    
    int getQuantitaDiSoglia() throws RemoteException;
}
