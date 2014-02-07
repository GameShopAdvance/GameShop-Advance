/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.config;

import gameshop.advance.exceptions.ConfigurationException;
import java.net.UnknownHostException;
import sun.net.util.IPAddressUtil;

/**
 *
 * @author Salx
 */
public class ConfigurationControllerSingleton {
    
    private static ConfigurationControllerSingleton instance;
    
    private Configuration config;
    
    private ConfigurationControllerSingleton() throws ConfigurationException{
        
        Configuration c = DbConfigurationSingleton.getInstance().read();
        if(c == null)
            this.config = new Configuration();
        else
            config = c;
    }
    
    public static ConfigurationControllerSingleton getInstance() throws ConfigurationException {
        
        if (instance == null)
        {
            instance = new ConfigurationControllerSingleton();
        }
        
        return instance;
    }
    
    public void setConfiguration(String address, int port, int idCassa){
        
        this.config.setServerAddress(address);
        this.config.setServerPort(port);
        this.config.setIdCassa(idCassa);     
    }
    
    public Configuration getConfiguration(){
        
        return this.config;
    }
    
    public void setServerAddress(String address) throws UnknownHostException{
        
        boolean goodAddress = IPAddressUtil.isIPv4LiteralAddress(address);
        if(!goodAddress)
            throw new UnknownHostException();
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
        DbConfigurationSingleton.getInstance().store(this.config);
    }
        
}
