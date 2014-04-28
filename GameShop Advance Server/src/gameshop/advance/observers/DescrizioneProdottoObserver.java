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
import gameshop.advance.technicalservices.LoggerSingleton;
import java.rmi.RemoteException;

/** Observer che notifica gli ogetti interessati quando un prodotto scende sotto la soglia prevista.
 * 
 * @author Salx
 */
public class DescrizioneProdottoObserver implements IObserver {
   
    public DescrizioneProdottoObserver(){
      
    }
    
    @Override
    public void notifica(Object o) {
        
        DescrizioneProdotto desc = (DescrizioneProdotto) o;
        if(desc.sottoSoglia()){
            try {
                ManagerProdottiSingleton.getInstance().addDescrizione(desc);
            } catch (QuantityException ex) {
                LoggerSingleton.getInstance().log(ex);
            } catch (RemoteException ex) {
                LoggerSingleton.getInstance().log(ex);
            }
        }
        else {
            try {
                ManagerProdottiSingleton.getInstance().deleteDescrizione(desc);
            } catch (QuantityException ex) {
                LoggerSingleton.getInstance().log(ex);
            } catch (RemoteException ex) {
                LoggerSingleton.getInstance().log(ex);
            }
        }
    }
}
