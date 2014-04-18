/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.customer.client;

import gameshop.advance.ui.swing.UIWindowSingleton;
import gameshop.advance.ui.swing.customer.CustomerPanel;

/**
 * Main class per il client Customer. 
 * @author Matteo Gentile
 */
public class GameShopAdvanceCustomerClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UIWindowSingleton window = UIWindowSingleton.getInstance();
        window.setPanel(new CustomerPanel());
        window.setVisible(true);
        window.refreshContent();
    }
    
}
