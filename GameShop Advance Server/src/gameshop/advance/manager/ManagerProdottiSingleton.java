/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.manager;

import gameshop.advance.exceptions.QuantityException;
import gameshop.advance.interfaces.IDescrizioneProdotto;
import gameshop.advance.interfaces.IObserver;
import gameshop.advance.technicalservices.db.DbDescrizioneProdottoSingleton;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Salx
 */
public class ManagerProdottiSingleton {
    
    private static ManagerProdottiSingleton instance;
    private LinkedList<IDescrizioneProdotto> descrizioni;
    private LinkedList<IObserver> listeners;
    
    
    public ManagerProdottiSingleton(){
        DbDescrizioneProdottoSingleton db = DbDescrizioneProdottoSingleton.getInstance();
        descrizioni = db.readBelowThreshold();
    }
    
    public static ManagerProdottiSingleton getInstance(){
        
        if (ManagerProdottiSingleton.instance == null){
            ManagerProdottiSingleton.instance = new ManagerProdottiSingleton();
        }
        return ManagerProdottiSingleton.instance;
    }
    
    public void addDescrizione(IDescrizioneProdotto desc) throws QuantityException, RemoteException{
        
        ManagerFornitureSingleton forniture = ManagerFornitureSingleton.getInstance();
        forniture.addDescrizione(desc);
        /*if(descrizioni.contains(desc)){
        }
        else {
            descrizioni.add(desc);
        }*/
    }
    
    public void deleteDescrizione(IDescrizioneProdotto desc) throws QuantityException, RemoteException{
        
        ManagerFornitureSingleton forniture = ManagerFornitureSingleton.getInstance();
        forniture.removeDescrizione(desc);
    }
    
    /*public void deleteDescrizione(DescrizioneProdotto desc){
       if(descrizioni.contains(desc)){
           descrizioni.remove(desc);
       } 
    }*/

    public List getMonitored() {
        return descrizioni;
    }   

    public void addListener(IObserver obs) {
        this.listeners.add(obs);
    }
}
