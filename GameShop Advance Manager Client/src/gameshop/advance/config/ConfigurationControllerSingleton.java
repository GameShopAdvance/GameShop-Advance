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
 * Controller che si occupa di gestire la configurazione del client necessaria per consentire il funzionamento
 * di Java RMI
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
    
    /**
     * Metodo che ritorna l'istanza della classe Singleton
     * @return
     * @throws ConfigurationException
     */
    public static ConfigurationControllerSingleton getInstance() throws ConfigurationException {
        
        if (instance == null)
        {
            instance = new ConfigurationControllerSingleton();
        }
        
        return instance;
    }
    
    /**
     * @param address
     * @param port
     */
    public void setConfiguration(String address, int port){
        
        this.config.setServerAddress(address);
        this.config.setServerPort(port);
    }
    
    /**
     * @return
     */
    public Configuration getConfiguration(){
        
        return this.config;
    }
    
    /**
     * @param address
     * @throws UnknownHostException
     */
    public void setServerAddress(String address) throws UnknownHostException{
        
        boolean goodAddress = IPAddressUtil.isIPv4LiteralAddress(address);
        if(!goodAddress)
            throw new UnknownHostException();
        this.config.setServerAddress(address);
    }
    
    /**
     * @return
     */
    public String getServerAddress(){
        
        String address;
        address = this.config.getServerAddress();
        return address;
    }
    
    /**
     * @param port
     */
    public void setServerPort(int port){
     
        this.config.setServerPort(port);
    }
    
    /**
     * @return
     */
    public int getServerPort(){
        
        int port;
        port = this.config.getServerPort();
        return port;
    }

    /**
     * Salva sul db l'oggetto cinfiguration.
     */
    public void storeConfiguration(){
        DbConfigurationSingleton.getInstance().store(this.config);
    }
        
}
