package gameshop.advance.employee;

import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.ui.swing.UIWindowSingleton;
import gameshop.advance.ui.swing.employee.EmployeeMenuPanel;

/**
 * Classe di avviamento del client.
 * @author loki
 */
public class GameShopAdvanceEmployee {

    /**
     * @param args the command line arguments
     * @throws ConfigurationException
     */
    public static void main(String[] args) throws ConfigurationException {

        if(System.getSecurityManager() == null)
            System.setSecurityManager(new SecurityManager());          
        UIWindowSingleton window = UIWindowSingleton.getInstance();
        window.setPanel(new EmployeeMenuPanel());
        window.setVisible(true);
        window.refreshContent();      
    } 
}
