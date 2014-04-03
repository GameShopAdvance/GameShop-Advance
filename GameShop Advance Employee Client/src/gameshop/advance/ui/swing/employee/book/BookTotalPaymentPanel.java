/*
 * Created by JFormDesigner on Wed Feb 12 15:48:38 CET 2014
 */

package gameshop.advance.ui.swing.employee.book;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.BookControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.ui.swing.UIWindowSingleton;
import java.awt.Font;
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

    private void payPartialButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
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
        panel2 = new JPanel();
        label3 = new JLabel();
        displayTotal = new JTextField();
        label4 = new JLabel();
        totalPayment = new JTextField();
        payTotalButton = new JButton();

        //======== this ========
        setLayout(new FormLayout(
            "[300px,min], $lcgap, [332px,min]",
            "27dlu, $lgap, fill:196dlu"));

        //======== panel2 ========
        {
            panel2.setBorder(new TitledBorder("Paga Totale"));
            panel2.setLayout(new FormLayout(
                "5dlu, $lcgap, [100px,min], $lcgap, [200px,min]",
                "2*(fill:[50px,min], $lgap), fill:[50px,min]"));

            //---- label3 ----
            label3.setText("Totale");
            label3.setFont(new Font("Tahoma", Font.PLAIN, 18));
            panel2.add(label3, CC.xy(3, 1));

            //---- displayTotal ----
            displayTotal.setEditable(false);
            panel2.add(displayTotal, CC.xy(5, 1));

            //---- label4 ----
            label4.setText("Pagamento");
            label4.setFont(new Font("Tahoma", Font.PLAIN, 18));
            panel2.add(label4, CC.xy(3, 3));
            panel2.add(totalPayment, CC.xy(5, 3));

            //---- payTotalButton ----
            payTotalButton.setText("Paga Totale");
            payTotalButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
            payTotalButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    payTotalButtonActionPerformed(e);
                }
            });
            panel2.add(payTotalButton, CC.xy(5, 5));
        }
        add(panel2, CC.xy(3, 3));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel2;
    private JLabel label3;
    private JTextField displayTotal;
    private JLabel label4;
    private JTextField totalPayment;
    private JButton payTotalButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
