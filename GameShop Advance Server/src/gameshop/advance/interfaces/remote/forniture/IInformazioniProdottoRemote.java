/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces.remote.forniture;

import gameshop.advance.interfaces.remote.IDescrizioneProdottoRemote;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaccia remota per l'esportazione della classe factory che la implementano tramite
 * Java RMI, Consente di esportare i metodi necessari ad ottenere i dati relativi alle quantità disponibili e prenotate dei prodotti
 * 
 * @author Lorenzo Di Giuseppe
 */
public interface IInformazioniProdottoRemote extends Remote {
    /**
     *
     * @return
     * @throws RemoteException
     */
    IDescrizioneProdottoRemote getDescrizione() throws RemoteException;
    /**
     *
     * @return Numero di unità ordinate di un prodotto
     * @throws RemoteException
     */
    int getOrdinati() throws RemoteException;
    /**
     *
     * @return Numero di unità prenotate di un prodotto
     * @throws RemoteException
     */
    int getPrenotati() throws RemoteException;
    
}
