/*
 * Created by JFormDesigner on Fri Feb 07 22:03:46 CET 2014
 */

package gameshop.advance.ui.swing.customer;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.PrenotaProdottoController;
import gameshop.advance.exceptions.ConfigurationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author Matteo Gentile
 */
public class CustomerPanel extends JPanel {
    public CustomerPanel() {
        initComponents();
    }

    private void avviaPrenotazione(ActionEvent e) {    
            try {
                PrenotaProdottoController.getInstance().avviaPrenotazione();
            } catch (NullPointerException ex) {
                Logger.getLogger(CustomerPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RemoteException ex) {
                Logger.getLogger(CustomerPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ConfigurationException ex) {
                Logger.getLogger(CustomerPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        button1 = new JButton();

        //======== this ========
        setLayout(new FormLayout(
            "[150px,min]:grow, $lcgap, [150px,default], $lcgap, [150px,min]:grow",
            "[70px,min]:grow, $lgap, 45px, $lgap, [160px,min]:grow"));

        //---- button1 ----
        button1.setText("Avvia Prenotazione");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                avviaPrenotazione(e);
            }
        });
        add(button1, CC.xy(3, 3, CC.FILL, CC.FILL));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
