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
 *  Interfaccia remota per l'esportazione della classe factory che la implementano tramite
 *  Java RMI, Consente di esportare i metodi necessari al manager per l'analisi delle forniture del negozio
 * 
 * @author Lorenzo Di Giuseppe
 */
public interface IRemoteFornitureClient extends Remote
{
    /**
     *
     * @param iter
     * @throws RemoteException
     */
    void setInformazioniProdotto(IIteratorWrapperRemote<IInformazioniProdottoRemote> iter) throws RemoteException;
    /**
     *
     * @param info
     * @throws RemoteException
     */
    void rimuoviInformazioneProdotto(IInformazioniProdottoRemote info) throws RemoteException;
}
