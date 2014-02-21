
package gameshop.advance.config;

import gameshop.advance.exceptions.ConfigurationException;

/**
 *
 * @author Lorenzo Di Giuseppe
 * Classe che mantiene la configurazione del client.
 */
public class Configuration {
    

    private String serverAddress;
    private int serverPort;
    private int idCassa;
    
    public Configuration() throws ConfigurationException
    {
        
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString() {
        
        String configuration;
        configuration = " Configurazione del terminale - " +
                " Server Address: "+ this.getServerAddress() +
                " Server Port: " + this.getServerPort() +
                " Numero Cassa: " + this.getIdCassa();
        
        return configuration;
    }
    
    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }
    
    public int getIdCassa() {
        return idCassa;
    }
    
    public void setIdCassa(int idCassa){
        this.idCassa = idCassa;
    }
    
}
