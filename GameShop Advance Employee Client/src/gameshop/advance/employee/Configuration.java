
package gameshop.advance.employee;

import gameshop.advance.exceptions.ConfigurationException;

/**
 *
 * @author Lorenzo Di Giuseppe
 * Classe che mantiene la configurazione del client.
 */
public class Configuration {
    
    //private String configurationFile = "./src/gameshop/advance/employee/config/clientConfiguration.xml";

    private String serverAddress;
    private int serverPort;
    private int idCassa;
    
    public Configuration() throws ConfigurationException
    {
        
    }
   
    
    
    /*public void setConfigurationFile(String path)
    {
        this.configurationFile = path;
    }
    
    public void readConfiguration() throws ConfigurationException
    {
        try {
            SimpleXMLParser parser = new SimpleXMLParser(this.configurationFile);
            this.serverAddress = parser.parseByID("registryaddress");
            String port = parser.parseByID("registryport");
            if(port != null)
                this.serverPort = Integer.parseInt(port);
            else
                this.serverPort = 0;
            String id = parser.parseByID("idCassa");
            if(!id.equals(""))
                this.idCassa = Integer.parseInt(id);
            else
                throw new ConfigurationException(configurationFile, "Non è possibile ricavare il numero della cassa.");
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            throw new ConfigurationException(configurationFile, "Impossibile leggere il file di configurazione.");
        }
    }*/

    
    public String printConfiguration() throws ConfigurationException {
        
        String configuration;
        configuration = "Configurazione del terminale - " +
                "Server Address: "+ this.getServerAddress() +
                "Server Port: " + this.getServerPort() +
                "Numero Cassa: " + this.getIdCassa();
        
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
