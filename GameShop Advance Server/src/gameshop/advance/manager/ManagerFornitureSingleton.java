/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.manager;

import gameshop.advance.interfaces.IDescrizioneProdotto;
import gameshop.advance.interfaces.IObserver;
import gameshop.advance.interfaces.IPrenotazione;

/**
 *
 * @author Salx
 */
public class ManagerFornitureSingleton implements IObserver {
    
    private static ManagerFornitureSingleton instance;
    
    public ManagerFornitureSingleton(){
        
    }
    
    public static ManagerFornitureSingleton getInstance(){
        
        if (ManagerFornitureSingleton.instance == null){
            ManagerFornitureSingleton.instance = new ManagerFornitureSingleton();
        }
        return ManagerFornitureSingleton.instance;
    }

    @Override
    public void notifica(Object o) {
        
    }
    
    /**
     *
     * @param descrizione
     */
    public void aggiorna(IDescrizioneProdotto descrizione){
        
    }
    
    public void aggiorna(IPrenotazione prenotazione){
        
    }
    
}
