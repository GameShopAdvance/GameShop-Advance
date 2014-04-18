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
 * Classe che si occupa della gestione, recupero e salvataggio, dell'oggetto configuration,
 * che contiene tutti i dati relativi alla configurazione del server.s
 * @author Salx
 */
public class ConfigurationControllerSingleton {
    
    private static ConfigurationControllerSingleton instance;
    
    private Configuration config;
    
    /**
     * Inizializza ConfigurationController.
     */
    private ConfigurationControllerSingleton() throws ConfigurationException{
        
        Configuration c = DbConfigurationSingleton.getInstance().read();
        if(c == null)
            this.config = new Configuration();
        else
            config = c;
    }
    
    /**
     * @return istanza di ConfigurationController
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
     * @param idCassa
     */
    public void setConfiguration(String address, int port, int idCassa){
        
        this.config.setServerAddress(address);
        this.config.setServerPort(port);
        this.config.setIdCassa(idCassa);     
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
     * @param idCassa
     */
    public void setIdCassa(int idCassa){
        
        this.config.setIdCassa(idCassa);
    }
    
    /**
     * @return
     */
    public int getIdCassa(){
        int idCassa;
        idCassa = this.config.getIdCassa();
        return idCassa;
    }

    /**
     * Salva la configurazione nel db locale.
     */
    public void storeConfiguration(){
        DbConfigurationSingleton.getInstance().store(this.config);
    }
        
}
