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
 * Interfaccia remota per la classe che gestisce l'aggiunta e la rimozione di oggetti
 * informazioni prodotto.
 * @author Lorenzo Di Giuseppe
 */
public interface IRemoteFornitureClient extends Remote
{

    /**
     * @param iter
     * @throws RemoteException
     */
    void setInformazioniProdotto(IIteratorWrapperRemote<IInformazioniProdottoRemote> iter) throws RemoteException;
    
    /**
     * @param info
     * @throws RemoteException
     */
    void rimuoviInformazioneProdotto(IInformazioniProdottoRemote info) throws RemoteException;
}
