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
public class BookPaymentPanel extends JPanel {
    
    public BookPaymentPanel() {
        initComponents();
        try {
            this.displayPartial.setText(BookControllerSingleton.getInstance().getPartial().toString());
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

    private void payPartialButtonActionPerformed(ActionEvent e) {
        try{
            BookControllerSingleton.getInstance().effettuaPagamentoAcconto(Double.parseDouble(this.partialPayment.getText()));
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
            BookControllerSingleton.getInstance().effettuaPagamentoTotale(Double.parseDouble(this.totalPayment.getText()));
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
        panel1 = new JPanel();
        displayPartial = new JTextField();
        label1 = new JLabel();
        payPartialButton = new JButton();
        label2 = new JLabel();
        partialPayment = new JTextField();
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

        //======== panel1 ========
        {
            panel1.setBorder(new TitledBorder("Paga Acconto"));
            panel1.setLayout(new FormLayout(
                "default, $lcgap, 71dlu, $lcgap, 141dlu, $lcgap, 34dlu, $lcgap, 155dlu",
                "6dlu, $lgap, 24dlu, $lgap, 25dlu, $lgap, 3dlu"));

            //---- displayPartial ----
            displayPartial.setEditable(false);
            panel1.add(displayPartial, CC.xywh(5, 2, 1, 2));

            //---- label1 ----
            label1.setText("Acconto");
            label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 3f));
            panel1.add(label1, CC.xy(3, 3));

            //---- payPartialButton ----
            payPartialButton.setText("Paga Acconto");
            payPartialButton.setFont(payPartialButton.getFont().deriveFont(payPartialButton.getFont().getSize() + 5f));
            payPartialButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    payPartialButtonActionPerformed(e);
                    payPartialButtonActionPerformed(e);
                }
            });
            panel1.add(payPartialButton, CC.xywh(9, 4, 1, 4));

            //---- label2 ----
            label2.setText("Pagamento");
            label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 3f));
            panel1.add(label2, CC.xy(3, 5));
            panel1.add(partialPayment, CC.xywh(5, 5, 1, 2));
        }
        add(panel1, CC.xywh(3, 3, 7, 3));

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
        add(panel2, CC.xywh(3, 9, 7, 3));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JTextField displayPartial;
    private JLabel label1;
    private JButton payPartialButton;
    private JLabel label2;
    private JTextField partialPayment;
    private JPanel panel2;
    private JTextField displayTotal;
    private JLabel label3;
    private JTextField totalPayment;
    private JButton payTotalButton;
    private JLabel label4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
