/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.config;

import gameshop.advance.exceptions.ConfigurationException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matteog
 */
public class InitializeConfiguration {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ConfigurationControllerSingleton controllerConfig = ConfigurationControllerSingleton.getInstance();
            controllerConfig.setConfiguration("127.0.0.1", 1099, 17);
        } catch (ConfigurationException ex) {
            Logger.getLogger(InitializeConfiguration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
