
package gameshop.advance.employee;

import gameshop.advance.exceptions.ConfigurationException;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Lorenzo Di Giuseppe
 * Classe che mantiene la configurazione del client.
 */
public class ConfigurationSingleton {
    
    private String configurationFile = "./configuration.xml";
    private static ConfigurationSingleton instance;
    private String serverAddress;
    private int serverPort;
    private int idCassa;
    
    private ConfigurationSingleton() throws ConfigurationException
    {
        this.readConfiguration();
    }

    public int getIdCassa() {
        return idCassa;
    }
    
    public static ConfigurationSingleton getInstance() throws ConfigurationException
    {
        if(instance == null)
        {
            instance = new ConfigurationSingleton();
        }
        return instance;
    }
    
    public void setConfigurationFile(String path)
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
    
}
