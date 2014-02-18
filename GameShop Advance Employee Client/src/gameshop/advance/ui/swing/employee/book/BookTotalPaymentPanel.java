/*
 * Created by JFormDesigner on Wed Feb 12 15:48:38 CET 2014
 */

package gameshop.advance.ui.swing.employee.book;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.BookControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.ui.swing.UIWindowSingleton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


/**
 * @author Franco
 */
public class BookTotalPaymentPanel extends JPanel {
    
    public BookTotalPaymentPanel() {
        initComponents();
        try {
            this.displayTotal.setText(BookControllerSingleton.getInstance().getTotal().toString());
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


    private void payTotalButtonActionPerformed(ActionEvent e) {
        try{
            BookControllerSingleton.getInstance().gestisciPagamento(Double.parseDouble(this.totalPayment.getText()));
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

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel2 = new JPanel();
        displayTotal = new JTextField();
        label3 = new JLabel();
        totalPayment = new JTextField();
        payTotalButton = new JButton();
        label4 = new JLabel();

        //======== this ========
        setLayout(new FormLayout(
            "default, $lcgap, 91dlu, $lcgap, 115dlu, $lcgap, 110dlu, $lcgap, 109dlu, $lcgap, 11dlu",
            "default, $lgap, 32dlu, $lgap, 48dlu, $lgap, 13dlu, $lgap, 32dlu, $lgap, 49dlu, $lgap, default"));

        //======== panel2 ========
        {
            panel2.setBorder(new TitledBorder("Paga Totale"));
            panel2.setLayout(new FormLayout(
                "default, $lcgap, 71dlu, $lcgap, 141dlu, $lcgap, 34dlu, $lcgap, 155dlu",
                "7dlu, $lgap, 25dlu, $lgap, 24dlu, $lgap, 3dlu"));

            //---- displayTotal ----
            displayTotal.setEditable(false);
            panel2.add(displayTotal, CC.xywh(5, 2, 1, 2));

            //---- label3 ----
            label3.setText("Totale");
            label3.setFont(label3.getFont().deriveFont(label3.getFont().getSize() + 3f));
            panel2.add(label3, CC.xy(3, 3));
            panel2.add(totalPayment, CC.xywh(5, 5, 1, 2));

            //---- payTotalButton ----
            payTotalButton.setText("Paga Totale");
            payTotalButton.setFont(payTotalButton.getFont().deriveFont(payTotalButton.getFont().getSize() + 5f));
            payTotalButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    payTotalButtonActionPerformed(e);
                }
            });
            panel2.add(payTotalButton, CC.xywh(9, 4, 1, 4));

            //---- label4 ----
            label4.setText("Pagamento");
            label4.setFont(label4.getFont().deriveFont(label4.getFont().getSize() + 3f));
            panel2.add(label4, CC.xy(3, 5));
        }
        add(panel2, CC.xywh(3, 3, 7, 3));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel2;
    private JTextField displayTotal;
    private JLabel label3;
    private JTextField totalPayment;
    private JButton payTotalButton;
    private JLabel label4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
