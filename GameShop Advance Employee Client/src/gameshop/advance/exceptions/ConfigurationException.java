/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.exceptions;

/**
 * Classe che gestisce le eccezioni di configurazione dell'architettura.
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class ConfigurationException extends Exception{

    private final String filePath;
    
    /**
     * @param path
     */
    public ConfigurationException(String path) {
        this.filePath = path;
    }
    
    /**
     * @param path
     * @param message
     */
    public ConfigurationException(String path, String message){
        super(message);
        this.filePath = path;
    }
    
    /**
     * @return
     */
    public String getConfigurationPath()
    {
        return this.filePath;
    }
}
