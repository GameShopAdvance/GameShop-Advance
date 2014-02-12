/*
 * Created by JFormDesigner on Wed Feb 12 17:45:43 CET 2014
 */

package gameshop.advance.ui.swing.employee.book;

import gameshop.advance.ui.swing.employee.sale.EndSalePanel;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.BookControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.ui.swing.UIWindowSingleton;
import gameshop.advance.ui.swing.employee.EmployeeMenuPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        this2 = new JPanel();
        label1 = new JLabel();
        displayRest = new JTextField();
        label2 = new JLabel();
        goToMenu = new JButton();

        //======== this ========
        setLayout(new FormLayout(
            "default, $lcgap, default",
            "2*(default, $lgap), default"));

        //======== this2 ========
        {
            this2.setLayout(new FormLayout(
                "default:grow, 45dlu, $lcgap, [150px,min], default:grow",
                "default:grow, 25dlu, $lgap, 20dlu, $lgap, 25dlu, $rgap, 25dlu, fill:25dlu:grow"));

            //---- label1 ----
            label1.setText("Resto:");
            label1.setLabelFor(displayRest);
            this2.add(label1, CC.xy(2, 2, CC.FILL, CC.FILL));
            this2.add(displayRest, CC.xy(4, 2, CC.FILL, CC.DEFAULT));

            //---- label2 ----
            label2.setText("Prenotazione effettuata!");
            this2.add(label2, CC.xy(4, 4, CC.FILL, CC.FILL));

            //---- goToMenu ----
            goToMenu.setText("Torna al Men\u00f9");
            goToMenu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    goToMenuActionPerformed(e);
                }
            });
            this2.add(goToMenu, CC.xywh(4, 6, 1, 3, CC.DEFAULT, CC.FILL));
        }
        add(this2, CC.xy(3, 3));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel this2;
    private JLabel label1;
    private JTextField displayRest;
    private JLabel label2;
    private JButton goToMenu;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
