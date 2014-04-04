/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.observers;

import gameshop.advance.interfaces.IObserver;
import gameshop.advance.manager.ManagerProdottiSingleton;
import gameshop.advance.model.DescrizioneProdotto;

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
        ManagerProdottiSingleton manager = ManagerProdottiSingleton.getInstance();
        
        if (desc.sottoSoglia()){
            manager.addDescrizione(desc);
        }
        else {
            manager.deleteDescrizione(desc);
        }
    }
}
