/*
 * Created by JFormDesigner on Tue Feb 18 12:27:10 CET 2014
 */

package gameshop.advance.ui.swing.employee.book;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.BookControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.ui.swing.UIWindowSingleton;
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
import javax.swing.border.TitledBorder;

/**
 * @author Franco
 */
public class BookPartialPaymentPanel extends JPanel {
    public BookPartialPaymentPanel() {
        initComponents();
        try {
            this.displayPartial.setText(BookControllerSingleton.getInstance().getPartial().toString());
        } catch (NullPointerException ex) {
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

    private void payPartialButtonActionPerformed(ActionEvent e) {
        try{
            BookControllerSingleton.getInstance().pagaAcconto(Double.parseDouble(this.partialPayment.getText()));
        } catch (NullPointerException ex) {
            UIWindowSingleton.getInstance().displayError("Ci sono problemi di comunicazione,"
                    + " si prega di controllare la configurazione del sistema.");
        } catch (RemoteException ex) {
            UIWindowSingleton.getInstance().displayError("Non è possibile contattare il server. "
                    + "Si prega di riprovare. Se il problema persiste, contattare l'amministratore di sistema.");
        } catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError("Ci sono problemi nella lettura del file di configurazione: "+ex.getConfigurationPath()+"."
                    + " Per maggiori informazioni rivolgersi all'amministratore di sistema.");
        } catch (InvalidMoneyException ex) {
            Logger.getLogger(BookPartialPaymentPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        panel1 = new JPanel();
        label1 = new JLabel();
        displayPartial = new JTextField();
        label2 = new JLabel();
        partialPayment = new JTextField();
        payPartialButton = new JButton();

        //======== this ========
        setLayout(new FormLayout(
            "[300px,min], $lcgap, [332px,min]",
            "27dlu, $lgap, fill:197dlu"));

        //======== panel1 ========
        {
            panel1.setBorder(new TitledBorder("Paga Acconto"));
            panel1.setLayout(new FormLayout(
                "5dlu, $lcgap, [100px,min], $lcgap, [200px,min]",
                "2*(fill:[50px,min], $lgap), fill:[50px,min], 10dlu"));

            //---- label1 ----
            label1.setText("Acconto");
            label1.setFont(new Font("Tahoma", Font.PLAIN, 18));
            panel1.add(label1, CC.xy(3, 1));

            //---- displayPartial ----
            displayPartial.setEditable(false);
            panel1.add(displayPartial, CC.xy(5, 1));

            //---- label2 ----
            label2.setText("Pagamento");
            label2.setFont(new Font("Tahoma", Font.PLAIN, 18));
            panel1.add(label2, CC.xy(3, 3));
            panel1.add(partialPayment, CC.xy(5, 3));

            //---- payPartialButton ----
            payPartialButton.setText("Paga Acconto");
            payPartialButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
            payPartialButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    payPartialButtonActionPerformed(e);
                    payPartialButtonActionPerformed(e);
                    payPartialButtonActionPerformed(e);
                }
            });
            panel1.add(payPartialButton, CC.xy(5, 5));
        }
        add(panel1, CC.xy(3, 3));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JLabel label1;
    private JTextField displayPartial;
    private JLabel label2;
    private JTextField partialPayment;
    private JButton payPartialButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
