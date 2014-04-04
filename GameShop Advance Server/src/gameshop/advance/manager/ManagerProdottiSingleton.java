/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.manager;

import gameshop.advance.interfaces.IDescrizioneProdotto;
import gameshop.advance.model.DescrizioneProdotto;
import gameshop.advance.technicalservices.db.DbDescrizioneProdottoSingleton;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Salx
 */
public class ManagerProdottiSingleton {
    
    private static ManagerProdottiSingleton instance;
    private LinkedList<IDescrizioneProdotto> descrizioni;
    
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
    
    public void addDescrizione(DescrizioneProdotto desc){
        if(descrizioni.contains(desc)){
        }
        else {
            descrizioni.add(desc);
        }
    }
    
    public void deleteDescrizione(DescrizioneProdotto desc){
       if(descrizioni.contains(desc)){
           descrizioni.remove(desc);
       } 
    }

    public List getMonitored() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }   
}
