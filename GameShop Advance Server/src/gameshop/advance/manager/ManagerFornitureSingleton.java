/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.manager;

import gameshop.advance.interfaces.IDescrizioneProdotto;
import gameshop.advance.interfaces.IPrenotazione;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Salx
 */
public class ManagerFornitureSingleton {
    
    private static ManagerFornitureSingleton instance;
    
    private LinkedList<IPrenotazione> prenotazioni;
    
    private LinkedList<IDescrizioneProdotto> descrizioni;
    
    public ManagerFornitureSingleton(){
        
    }
    
    public static ManagerFornitureSingleton getInstance(){
        
        if (ManagerFornitureSingleton.instance == null){
            ManagerFornitureSingleton.instance = new ManagerFornitureSingleton();
        }
        return ManagerFornitureSingleton.instance;
    }
    
    /**
     *
     * @param descrizioni
     */
    public void aggiornaDescrizioni(List<IDescrizioneProdotto> descrizioni){
        this.descrizioni = (LinkedList<IDescrizioneProdotto>) descrizioni;
    }
    
    /**
     *
     * @param prenotazioni
     */
    public void aggiornaPrenotazioni(List<IPrenotazione> prenotazioni){
        this.prenotazioni = (LinkedList<IPrenotazione>) prenotazioni;
    }

    public List<IPrenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    public List<IDescrizioneProdotto> getDescrizioni() {
        return descrizioni;
    }
    
    
}
