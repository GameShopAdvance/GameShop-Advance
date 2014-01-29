/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.config;

import gameshop.advance.employee.Configuration;
import gameshop.advance.exceptions.ConfigurationException;

/**
 *
 * @author Salx
 */
public class ConfigurationControllerSingleton {
    
    private static ConfigurationControllerSingleton instance;
    private Configuration config;
    
    private ConfigurationControllerSingleton() throws ConfigurationException{
        
        config = new Configuration();
    }
    
    public static ConfigurationControllerSingleton getInstance() throws ConfigurationException {
        
        if (instance == null){
       
            instance = new ConfigurationControllerSingleton();
        }
        
        return instance;
    }
    
    public void setConfiguration(String address, int port, int idCassa){
        
        this.config.setServerAddress(address);
        this.config.setServerPort(port);
        this.config.setIdCassa(idCassa);
        this.storeConfiguration();
        
    }
    
    public Configuration getConfiguration(){
        
        return this.config;
    }
    
    public void setServerAddress(String address){
        
        this.config.setServerAddress(address);
    }
    
    public String getServerAddress(){
        
        String address;
        address = this.config.getServerAddress();
        return address;
    }
    
    public void setServerPort(int port){
     
        this.config.setServerPort(port);
    }
    
    public int getServerPort(){
        
        int port;
        port = this.config.getServerPort();
        return port;
    }
    
    public void setIdCassa(int idCassa){
        
        this.config.setIdCassa(idCassa);
    }
    
    public int getIdCassa(){
        
        int idCassa;
        idCassa = this.config.getIdCassa();
        return idCassa;
    }
    

    public void storeConfiguration(){
        
        DbConfigurationSingleton.getInstance().create(this.config);
    }
    
    public Configuration loadConfiguration(){
        
        this.config = DbConfigurationSingleton.getInstance().read();
        return this.config;
    }
    
    public void updateConfiguration(){
        
        DbConfigurationSingleton.getInstance().delete();
        DbConfigurationSingleton.getInstance().create(this.config);
    }
        
}
