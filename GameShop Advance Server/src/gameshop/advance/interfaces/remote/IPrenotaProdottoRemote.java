/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces.remote;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.exceptions.ProdottoNotFoundException;
import gameshop.advance.utility.IDProdotto;
import gameshop.advance.utility.Money;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public interface IPrenotaProdottoRemote extends Remote{

    void addListener(IRemoteObserver obs) throws RemoteException;
    
    //OPERAZIONI DI SISTEMA LATO CLIENTE
    
    IIteratorWrapperRemote<IDescrizioneProdottoRemote> getDescriptions() throws RemoteException;
    
    void avviaPrenotazione() throws RemoteException;

    void prenotaProdotto(IDProdotto codiceProdotto, int quantity) throws RemoteException, ProdottoNotFoundException;
    
    void terminaPrenotazione() throws RemoteException;
    
    //OPERAZIONI DI SISTEMA LATO COMMESSO
    
    void recuperaPrenotazione(Integer id) throws RemoteException;
    
    void gestisciPagamento(Money amount) throws RemoteException, InvalidMoneyException;
    
    //metodo per la richiesta di pagamento in acconto

    /**
     *
     * @throws RemoteException
     * @throws InvalidMoneyException
     */
        void pagaAcconto() throws RemoteException, InvalidMoneyException;
}
