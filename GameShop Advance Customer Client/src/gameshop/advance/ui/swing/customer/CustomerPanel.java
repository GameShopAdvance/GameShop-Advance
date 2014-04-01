/*
 * Created by JFormDesigner on Fri Feb 07 22:03:46 CET 2014
 */

package gameshop.advance.ui.swing.customer;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.ReservationControllerSingleton;
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
                ReservationControllerSingleton.getInstance().avviaPrenotazione();
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
        setName("this");

        //---- button1 ----
        button1.setText("Avvia Prenotazione");
        button1.setName("button1");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                avviaPrenotazione(e);
            }
        });

        PanelBuilder builder = new PanelBuilder(new FormLayout(
            "[150px,min]:grow, $lcgap, [150px,default], $lcgap, [150px,min]:grow",
            "[100px,min]:grow, $lgap, 40px, $lgap, fill:[100px,min]:grow"), this);

        builder.add(button1, CC.xy(3, 3, CC.FILL, CC.FILL));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
