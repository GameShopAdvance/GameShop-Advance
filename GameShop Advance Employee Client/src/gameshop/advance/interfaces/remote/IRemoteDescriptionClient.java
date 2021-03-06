/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaccia remota per la gestione delle descrizioni aggiunte dall'inventario.
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public interface IRemoteDescriptionClient extends Remote {
    
    /** 
     * Aggiunge descrizioni prodotto tramite inventario
     * @param add 
     * @throws RemoteException
     */
    void addDescription(IDescrizioneProdottoRemote add) throws RemoteException;

}
