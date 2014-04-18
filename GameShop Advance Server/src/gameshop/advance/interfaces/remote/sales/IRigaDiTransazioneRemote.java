/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces.remote.sales;

import gameshop.advance.interfaces.remote.IDescrizioneProdottoRemote;
import gameshop.advance.utility.Money;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *  Interfaccia remota per l'esportazione delle classi che la implementano tramite
 *  Java RMI.I metodi esportati da IRigaDiTransazioneRemote sono necessari sia alla vendita che alla prenotazione.
 * 
 *  @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public interface IRigaDiTransazioneRemote extends Remote{
    /** 
     * @return  La descrizione presente in una rdt
     * @throws RemoteException
     */
    IDescrizioneProdottoRemote getDescrizione() throws RemoteException;
     /** 
     * @return  La quantità di prodotti presenti in una rdt
     * @throws RemoteException
     */
    int getQuantity() throws RemoteException;
    /** 
     * @return  Il sottototale associato a una rdt
     * @throws RemoteException
     */
    Money getSubTotal() throws RemoteException;
}
