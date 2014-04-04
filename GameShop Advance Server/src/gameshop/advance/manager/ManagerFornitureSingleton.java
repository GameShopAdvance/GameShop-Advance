/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.manager;

import gameshop.advance.interfaces.IDescrizioneProdotto;
import gameshop.advance.interfaces.IObserver;
import gameshop.advance.interfaces.IPrenotazione;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Salx
 */
public class ManagerFornitureSingleton implements IObserver {
    
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

    @Override
    public synchronized void notifica(Object o) {
        this.aggiornaPrenotazioni(ManagerPrenotazioniSingleton.getInstance().getNotProcessed());
        //this.aggiornaDescrizioni(ManagerProdottiSingleton.getInstance());
    }
    
    /**
     *
     * @param descrizioni
     */
    public void aggiornaDescrizioni(List<IDescrizioneProdotto> descrizioni){
        
    }
    
    /**
     *
     * @param prenotazioni
     */
    public void aggiornaPrenotazioni(List<IPrenotazione> prenotazioni){
        this.prenotazioni = (LinkedList<IPrenotazione>) prenotazioni;
        
        Iterator<IPrenotazione> iter = this.prenotazioni.iterator();
        while(iter.hasNext())
        {
            System.err.println("Prenotazione: "+iter.next());
        }
    }
    
}
