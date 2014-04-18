
package gameshop.advance.config;

import gameshop.advance.exceptions.ConfigurationException;

/**
 * Classe che mantiene la configurazione del client.
 * @author Lorenzo Di Giuseppe
 */
public class Configuration {
    
    //private String configurationFile = "./src/gameshop/advance/employee/config/clientConfiguration.xml";

    private String serverAddress;
    private int serverPort;
    private int idCassa;
    
    /**
     * @throws ConfigurationException
     */
    public Configuration() throws ConfigurationException{
        
    }
    
    /**
     * Ritorna tutti i dati di configurazione come stringa.
     * @return configuration
     */
    @Override
    public String toString() {
        
        String configuration;
        configuration = "Configurazione del terminale - " +
                "Server Address: "+ this.getServerAddress() +
                "Server Port: " + this.getServerPort() +
                "Numero Cassa: " + this.getIdCassa();
        
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

    /**
     * @return
     */
    public int getIdCassa() {
        return idCassa;
    }
 
    /**
     * @param idCassa
     */
    public void setIdCassa(int idCassa){
        this.idCassa = idCassa;
    }
    
}
