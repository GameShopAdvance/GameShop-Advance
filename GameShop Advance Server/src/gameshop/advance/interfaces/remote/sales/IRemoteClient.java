/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces.remote.sales;

import gameshop.advance.utility.Money;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaccia remota per l'esportazione delle classi che la implementano tramite
 * Java RMI.I metodi esportati da IRemoteClient serviranno aggiornare e far visualizzare le
 * informazioni di una vendita.
 * 
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public interface IRemoteClient extends Remote {

    /**
     *
     * @param m 
     * @throws RemoteException
     */
    void aggiornaTotale(Money m) throws RemoteException;
    
    /**
     *
     * @param m
     * @throws RemoteException
     */
    void aggiornaResto(Money m) throws RemoteException;
}
