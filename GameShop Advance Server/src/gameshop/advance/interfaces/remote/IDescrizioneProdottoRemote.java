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
 * Interfaccia remota per l'esportazione delle classi che la implementano tramite
 * Java RMI, Interfaccia remota delle descrizioni prodotto.
 * 
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
     * @return quantità disponibile di un prodotto.
     * @throws java.rmi.RemoteException
     */
    int getQuantitaDisponibile() throws RemoteException;
    
    /**
     * @return quantità di soglia imposta su un prodotto
     * @throws java.rmi.RemoteException
     */
    int getQuantitaDiSoglia() throws RemoteException;
    /**
     * @return immagine di un prodotto
     * @throws java.rmi.RemoteException
     */
    IRemoteImage getImmagine() throws RemoteException;
    /**
     * @return nome del prodotto
     * @throws java.rmi.RemoteException
     */
    String getNomeProdotto() throws RemoteException;
}
