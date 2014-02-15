/*
 * Created by JFormDesigner on Thu Jan 30 12:02:33 CET 2014
 */

package gameshop.advance.ui.swing.employee.sale;

import gameshop.advance.controller.SaleControllerSingleton;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
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

/**
 * @author Andrea
 */
public class PaymentPanel extends JPanel {
    
    public PaymentPanel() {
        initComponents();
        try {
            this.displayTotal.setText(SaleControllerSingleton.getInstance().getTotal().toString());
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

    private void payButtonActionPerformed(ActionEvent e) {
        try{
            SaleControllerSingleton.getInstance().effettuaPagamento(Double.parseDouble(this.payment.getText()));
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        displayTotal = new JTextField();
        payment = new JTextField();
        label2 = new JLabel();
        payButton = new JButton();

        //======== this ========
        setName("this");

        //---- label1 ----
        label1.setText("Totale");
        label1.setFont(new Font("Dialog", Font.PLAIN, 14));
        label1.setAlignmentX(0.5F);
        label1.setName("label1");

        //---- displayTotal ----
        displayTotal.setEditable(false);
        displayTotal.setName("displayTotal");

        //---- payment ----
        payment.setName("payment");

        //---- label2 ----
        label2.setText("Pagamento");
        label2.setFont(new Font("Dialog", Font.PLAIN, 14));
        label2.setAlignmentX(0.5F);
        label2.setName("label2");

        //---- payButton ----
        payButton.setText("Paga");
        payButton.setFont(new Font("Dialog", Font.PLAIN, 14));
        payButton.setAlignmentX(0.5F);
        payButton.setName("payButton");
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                payButtonActionPerformed(e);
            }
        });

        PanelBuilder builder = new PanelBuilder(new FormLayout(
            "default:grow, $lcgap, 73dlu, $lcgap, 113dlu, $lcgap, default:grow",
            "fill:default:grow, [20dlu,default], $rgap, 20dlu, $rgap, 25dlu, fill:default:grow"), this);

        builder.add(label1,       CC.xy(3, 2));
        builder.add(displayTotal, CC.xy(5, 2, CC.FILL, CC.FILL));
        builder.add(payment,      CC.xy(5, 4, CC.FILL, CC.FILL));
        builder.add(label2,       CC.xy(3, 4));
        builder.add(payButton,    CC.xy(5, 6));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JTextField displayTotal;
    private JTextField payment;
    private JLabel label2;
    private JButton payButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}