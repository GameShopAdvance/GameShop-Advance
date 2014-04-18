/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces.remote.forniture;

import gameshop.advance.interfaces.remote.utility.IIteratorWrapperRemote;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaccia remota per l'esportazione della classe factory che la implementano tramite
 * Java RMI, Consente di esportare i metodi utili all'analisi delle forniture.

 * @author Lorenzo Di Giuseppe
 */
public interface IFornitureManagerRemote extends Remote
{
    /**
     * @return Iteratore sulle Informazioni Prodotto
     * @throws java.rmi.RemoteException
     */
    IIteratorWrapperRemote<IInformazioniProdottoRemote> getInformazioni() throws RemoteException;
    
}
