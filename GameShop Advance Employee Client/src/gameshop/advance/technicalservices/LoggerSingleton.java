/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.technicalservices;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class LoggerSingleton {
    
    private static LoggerSingleton instance;
    
    private LoggerSingleton(){
        
    }
    
    public static LoggerSingleton getInstance()
    {
        if(instance == null)
            instance = new LoggerSingleton();
        return instance;
    }
    
    public void log(Exception ex)
    {
        Logger.getLogger(LoggerSingleton.class.getName()).log(Level.SEVERE, null, ex);
    }
    
}
