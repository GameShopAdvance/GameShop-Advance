/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.manager;

import gameshop.advance.model.DescrizioneProdotto;
import java.util.LinkedList;

/**
 *
 * @author Salx
 */
public class ManagerProdottiSingleton {
    
    private static ManagerProdottiSingleton instance;
    private LinkedList<DescrizioneProdotto> descrizioni;
    
    public ManagerProdottiSingleton(){
        
    }
    
    public static ManagerProdottiSingleton getInstance(){
        
        if (ManagerProdottiSingleton.instance == null){
            ManagerProdottiSingleton.instance = new ManagerProdottiSingleton();
        }
        return ManagerProdottiSingleton.instance;
    }
    
    public void addDescrizione(DescrizioneProdotto desc){
        
    }
    
    public void deleteDescrizione(DescrizioneProdotto desc){
        
    }
}
