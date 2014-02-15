/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces.remote;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.exceptions.ProdottoNotFoundException;
import gameshop.advance.utility.IDProdotto;
import gameshop.advance.utility.IteratorWrapper;
import gameshop.advance.utility.Money;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public interface IPrenotaProdottoRemote extends Remote{

    //OPERAZIONI DI SISTEMA LATO CLIENTE
    
    IteratorWrapper<IDescrizioneProdottoRemote> getDescriptions() throws RemoteException;
    
    void avviaPrenotazione() throws RemoteException;

    void terminaPrenotazione() throws RemoteException;

    void prenotaProdotto(IDProdotto codiceProdotto, int quantity) throws RemoteException, ProdottoNotFoundException;
    
    //OPERAZIONI DI SISTEMA LATO COMMESSO
    
    void recuperaPrenotazione(Integer id) throws RemoteException;
    
    void pagaTotale(Money amount) throws RemoteException, InvalidMoneyException;
    
    void pagaAcconto(Money amount) throws RemoteException, InvalidMoneyException;
    
}
