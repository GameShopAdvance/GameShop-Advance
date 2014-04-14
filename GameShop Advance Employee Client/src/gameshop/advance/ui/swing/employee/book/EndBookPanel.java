/*
 * Created by JFormDesigner on Wed Feb 12 17:45:43 CET 2014
 */

package gameshop.advance.ui.swing.employee.book;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.BookControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.ui.swing.UIWindowSingleton;
import gameshop.advance.ui.swing.employee.EmployeeMenuPanel;
import gameshop.advance.ui.swing.employee.sale.EndSalePanel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * @author Franco
 */
public class EndBookPanel extends JPanel {
    public EndBookPanel() {
        initComponents();
        try {
            this.displayRest.setText(BookControllerSingleton.getInstance().getResto().toString());
        } catch (NullPointerException ex) {
            Logger.getLogger(EndSalePanel.class.getName()).log(Level.SEVERE, null, ex);
            UIWindowSingleton.getInstance().displayError("Ci sono problemi di comunicazione,"
                    + " si prega di controllare la configurazione del sistema.");
        } catch (RemoteException ex) {
           UIWindowSingleton.getInstance().displayError("Non è possibile contattare il server. "
                    + "Si prega di riprovare. Se il problema persiste, contattare l'amministratore di sistema.");
        } catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError("Ci sono problemi nella lettura del file di configurazione: "+ex.getConfigurationPath()+"."
                    + " Per maggiori informazioni rivolgersi all'amministratore di sistema.");
        }
    }


    private void goToMenuActionPerformed(ActionEvent e) {
        UIWindowSingleton.getInstance().setPanel(new EmployeeMenuPanel());
        UIWindowSingleton.getInstance().refreshContent();
    }

    private void startSaleActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void manageBookActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void faiInventario(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        displayRest = new JTextField();
        label2 = new JLabel();
        goToMenu = new JButton();

        //======== this ========
        setLayout(new FormLayout(
            "$lcgap, [15dlu,default]:grow, $lcgap, [150px,min], $lcgap, 56dlu, $lcgap, 155dlu, $lcgap, 80dlu, $lcgap, [15dlu,default]:grow",
            "24dlu, $lgap, fill:37dlu, $lgap, 59dlu, $lgap, 69dlu, $lgap, 59dlu, $lgap, [15dlu,default]:grow"));

        //---- label1 ----
        label1.setText("Resto");
        label1.setLabelFor(displayRest);
        label1.setFont(new Font("Tahoma", Font.PLAIN, 22));
        add(label1, CC.xy(6, 3, CC.FILL, CC.FILL));
        add(displayRest, CC.xy(8, 3, CC.FILL, CC.DEFAULT));

        //---- label2 ----
        label2.setText("Prenotazione effettuata!");
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setFont(new Font("Tahoma", Font.PLAIN, 30));
        add(label2, CC.xywh(4, 5, 7, 1, CC.FILL, CC.FILL));

        //---- goToMenu ----
        goToMenu.setText("Torna al Men\u00f9");
        goToMenu.setFont(new Font("Tahoma", Font.PLAIN, 22));
        goToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToMenuActionPerformed(e);
            }
        });
        add(goToMenu, CC.xy(4, 9, CC.DEFAULT, CC.FILL));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JTextField displayRest;
    private JLabel label2;
    private JButton goToMenu;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
