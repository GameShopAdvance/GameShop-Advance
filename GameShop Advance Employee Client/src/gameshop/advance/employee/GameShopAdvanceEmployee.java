package gameshop.advance.employee;

import gameshop.advance.config.ConfigurationControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.ui.swing.UIWindowSingleton;
import gameshop.advance.ui.swing.employee.EmployeeMenuPanel;

/**
 *
 * @author loki
 */
public class GameShopAdvanceEmployee {

    /**
     * @param args the command line arguments
     * @throws gameshop.advance.exceptions.ConfigurationException
     */
    public static void main(String[] args) throws ConfigurationException {
            System.setSecurityManager(new SecurityManager());
            
            if(args != null)
            {
                if(args.length > 0)
                {
                    //Configuration.getInstance().setConfigurationFile(args[0]);
                    //Configuration.getInstance().readConfiguration();
            
                    //controllerConfig.getConfiguration();
                }
            }
                        
           
            ConfigurationControllerSingleton controllerConfig = ConfigurationControllerSingleton.getInstance();
            controllerConfig.loadConfiguration();
            
            UIWindowSingleton window = UIWindowSingleton.getInstance();
            window.setPanel(new EmployeeMenuPanel());
            window.setVisible(true);
            window.refreshContent();
            
    }
    
    
}
