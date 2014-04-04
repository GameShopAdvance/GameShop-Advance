/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.manager;

/**
 *
 * @author Salx
 */
public class ManagerFornitureSingleton {
    
    private static ManagerFornitureSingleton instance;
    
    public ManagerFornitureSingleton(){
        
    }
    
    public static ManagerFornitureSingleton getInstance(){
        
        if (ManagerFornitureSingleton.instance == null){
            ManagerFornitureSingleton.instance = new ManagerFornitureSingleton();
        }
        return ManagerFornitureSingleton.instance;
    }
    
}
