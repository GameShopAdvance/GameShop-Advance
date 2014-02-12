/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces.remote;

import gameshop.advance.utility.IDProdotto;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;

/**
 *
 * @author Salx
 */
public interface IPrenotaProdottoRemote {

    //OPERAZIONI DI SISTEMA LATO CLIENTE
    
    void avviaPrenotazione() throws RemoteException;

    void gestisciPagamento(Money amount) throws RemoteException;

    void prenotaProdotto(IDProdotto codiceProdotto, int quantity) throws RemoteException;
    
    //OPERAZIONI DI SISTEMA LATO COMMESSO
    
    void gestisciPrenotazione() throws RemoteException;
    
    void recuperaPrenotazione(Integer id) throws RemoteException;
    
    void ricercaPrenotazione() throws RemoteException;

    void terminaPrenotazione() throws RemoteException;
    
}
