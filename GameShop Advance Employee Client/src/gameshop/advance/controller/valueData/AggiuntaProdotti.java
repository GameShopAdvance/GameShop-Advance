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
 * Classe che conserva le descrizioni dei prodotti aggiunti e la quantità inserita.
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class AggiuntaProdotti {
    private IDescrizioneProdottoRemote descrizione;
    private Integer quantity;

    /**
     * @param desc
     * @param quantity
     */
    public AggiuntaProdotti(IDescrizioneProdottoRemote desc, int quantity){
        this.descrizione = desc;
        this.quantity = quantity;
    }

    /**
     * @return codice prodotto
     * @throws RemoteException
     */
    public IDProdotto getId() throws RemoteException {
        return this.descrizione.getCodiceProdotto();
    }
        
    /**
     * @return descrizione
     * @throws RemoteException
     */
    public IDescrizioneProdottoRemote getDescrizione() throws RemoteException {
        return this.descrizione;
    }

    /**
     * @return
     */
    public int getAddedQuantity() {
        return quantity;
    }

    /**
     * @param quantity
     */
    public void setAddedQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @param desc
     */
    public void setDescription(IDescrizioneProdottoRemote desc) {
        this.descrizione = desc;
    }
    
    /**
     * @return
     */
    @Override
    public String toString()
    {
        try {
            return this.getId().getCodice() + ": "+this.getDescrizione() + "  +"+this.quantity.toString();
        } catch (RemoteException ex) {
            Logger.getLogger(AggiuntaProdotti.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.quantity.toString();
    }
    
    
}
