/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.manager.client;

import gameshop.advance.ui.swing.UIWindowSingleton;
import gameshop.advance.ui.swing.manager.ManagerMenu;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class ManagerMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(System.getSecurityManager() == null)
            System.setSecurityManager(new SecurityManager());          
        UIWindowSingleton window = UIWindowSingleton.getInstance();
        window.setPanel(new ManagerMenu());
        window.setVisible(true);
        window.refreshContent();  
    }
    
}
