/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.vendita;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.remote.interfaces.IRemoteClient;
import gameshop.advance.remote.interfaces.IRemoteObserver;
import gameshop.advance.remote.interfaces.IVenditaRemote;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Il SaleObserver osserva i cambi di stato della vendita ed in particolare
 * è interessato al resto che deve essere restituito.Invia poi tale resto ad un client
 * che si occuperà di farlo visualizzare.
 * @author Salx
 */
public class SaleRestObserver implements IRemoteObserver, Serializable {
    private IRemoteClient client;

    /**
     * Il Costruttore utilizza il riferimento a IRemoteClient ricevuto in ingresso
     * per salvare il client al quale l'osservatore invierà informazioni riguardanti
     * la vendita a cui è associato.
     * @param client
     * @throws RemoteException
     */
    public SaleRestObserver(IRemoteClient client) throws RemoteException {
        this.client = client;
    }
    
    /**
     * Invia al client il valore del resto che deve essere restituito.Per avere 
     * la quantità di tale resto interroga la vendita che ha ricevuto in ingresso.
     * @param o
     * @throws RemoteException
     */
    @Override
    public void notifica(Object o) throws RemoteException {
        IVenditaRemote sale = (IVenditaRemote) o;
        try {        
            this.client.aggiornaResto(sale.getResto());
        } catch (InvalidMoneyException ex) {
            Logger.getLogger(SaleRestObserver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
