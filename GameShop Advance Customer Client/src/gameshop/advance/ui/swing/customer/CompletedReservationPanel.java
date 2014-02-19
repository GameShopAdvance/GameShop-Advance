/*
 * Created by JFormDesigner on Sun Feb 16 15:43:22 CET 2014
 */

package gameshop.advance.ui.swing.customer;

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
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Matteo Gentile
 */
 
public class CompletedReservationPanel extends JPanel {
    public CompletedReservationPanel(Integer id) {
        initComponents();
        this.label1.setText("Numero prenotazione: "+ id.toString() +". Vai in cassa per concluderla");
        }
    

    private void startNewReservationActionPerformed(ActionEvent e) {
        try {
            ReservationControllerSingleton.getInstance().avviaPrenotazione();
        } catch (NullPointerException ex) {
            Logger.getLogger(CompletedReservationPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConfigurationException ex) {
            Logger.getLogger(CompletedReservationPanel.class.getName()).log(Level.SEVERE, null, ex);
        }   catch (RemoteException ex) {
            Logger.getLogger(CompletedReservationPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        label1 = new JLabel();
        button1 = new JButton();

        //======== this ========
        setLayout(new FormLayout(
            "25dlu:grow, $lcgap, [163dlu,default], $lcgap, [25dlu,default]:grow",
            "[25dlu,default]:grow, $lgap, 120dlu, $lgap, [20dlu,default]:grow"));

        //======== panel1 ========
        {
            panel1.setLayout(new FormLayout(
                "205dlu",
                "21dlu, $lgap, [19dlu,default], $lgap, 35dlu"));

            //---- label1 ----
            label1.setText("text");
            panel1.add(label1, CC.xy(1, 1, CC.CENTER, CC.FILL));

            //---- button1 ----
            button1.setText("Avvia Nuova Prenotazione");
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    startNewReservationActionPerformed(e);
                }
            });
            panel1.add(button1, CC.xy(1, 5, CC.DEFAULT, CC.FILL));
        }
        add(panel1, CC.xy(3, 3));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JLabel label1;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
