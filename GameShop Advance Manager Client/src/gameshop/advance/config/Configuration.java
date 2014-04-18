
package gameshop.advance.config;

import gameshop.advance.exceptions.ConfigurationException;

/**
 * Classe che mantiene la configurazione del client.
 * @author Lorenzo Di Giuseppe
 */
public class Configuration {
    

    private String serverAddress;
    private int serverPort;
    
    public Configuration() throws ConfigurationException
    {
        
    }
    
    /**
     * @return Stringa di configurazione del client.
     */
    @Override
    public String toString() {
        
        String configuration;
        configuration = " Configurazione del terminale - " +
                " Server Address: "+ this.getServerAddress() +
                " Server Port: " + this.getServerPort();
        
        return configuration;
    }
    
    /**
     * @return
     */
    public String getServerAddress() {
        return serverAddress;
    }

    /**
     * @param serverAddress
     */
    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    /**
     * @return
     */
    public int getServerPort() {
        return serverPort;
    }

    /**
     * @param serverPort
     */
    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }
    
}
