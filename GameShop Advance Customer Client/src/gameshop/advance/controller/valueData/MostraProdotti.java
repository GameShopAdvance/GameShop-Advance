/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.controller.valueData;

import gameshop.advance.interfaces.remote.IDescrizioneProdottoRemote;
import gameshop.advance.utility.IDProdotto;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class MostraProdotti {
    private IDescrizioneProdottoRemote descrizione;
    private Integer quantity;
    private Boolean prenotato;

    public MostraProdotti(IDescrizioneProdottoRemote desc, int quantity)
    {
        this.descrizione = desc;
        this.quantity = quantity;
    }

    public IDProdotto getId() throws RemoteException {
        return this.descrizione.getCodiceProdotto();
    }
        
    public String getDescrizione() throws RemoteException {
        return this.descrizione.getDescrizione();
    }

    public int getAddedQuantity() {
        return quantity;
    }

    public void setAddedQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDescription(IDescrizioneProdottoRemote desc) {
        this.descrizione = desc;
    }
    
    @Override
      public String toString()
    {
        try {
            return this.getId().getCodice() + ": "+this.getDescrizione() + "  +"+this.quantity.toString();
        } catch (RemoteException ex) {
            Logger.getLogger(MostraProdotti.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.quantity.toString();
    }
    
    
}
