/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.observers;

import gameshop.advance.exceptions.QuantityException;
import gameshop.advance.interfaces.IObserver;
import gameshop.advance.manager.ManagerProdottiSingleton;
import gameshop.advance.model.DescrizioneProdotto;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Salx
 */
public class DescrizioneProdottoObserver implements IObserver {
   
    public DescrizioneProdottoObserver(){
      
    }
    
    @Override
    public void notifica(Object o) {
        
        DescrizioneProdotto desc = (DescrizioneProdotto) o;
        if (desc.sottoSoglia()){
            try {
                ManagerProdottiSingleton.getInstance().addDescrizione(desc);
            } catch (QuantityException ex) {
                Logger.getLogger(DescrizioneProdottoObserver.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RemoteException ex) {
                Logger.getLogger(DescrizioneProdottoObserver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            try {
                ManagerProdottiSingleton.getInstance().deleteDescrizione(desc);
            } catch (QuantityException ex) {
                Logger.getLogger(DescrizioneProdottoObserver.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RemoteException ex) {
                Logger.getLogger(DescrizioneProdottoObserver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
