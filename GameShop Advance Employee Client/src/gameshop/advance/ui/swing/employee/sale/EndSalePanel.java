/*
 * Created by JFormDesigner on Fri Jan 31 21:43:02 CET 2014
 */

package gameshop.advance.ui.swing.employee.sale;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.SaleControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.ui.swing.UIWindowSingleton;
import gameshop.advance.ui.swing.employee.EmployeeMenuPanel;
import gameshop.advance.utility.Money;
import java.awt.Color;
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
 * 
 */
public class EndSalePanel extends JPanel {
    public EndSalePanel() {
        initComponents();
        try {
            this.displayRest.setText(SaleControllerSingleton.getInstance().getResto().toString());
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
    
    
     private void startNewSaleActionPerformed(java.awt.event.ActionEvent evt) {                                             
        try{
            SaleControllerSingleton.getInstance().avviaNuovaVendita();
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

    private void goToMenuActionPerformed(java.awt.event.ActionEvent evt) {                                         
        UIWindowSingleton.getInstance().setPanel(new EmployeeMenuPanel());
        UIWindowSingleton.getInstance().refreshContent();
    }      
    
    public void setRest(Money rest)
    {
        this.displayRest.setText(rest.toString());
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
        startNewSale = new JButton();
        goToMenu = new JButton();

        //======== this ========
        setLayout(new FormLayout(
            "$lcgap, center:[150px,min], [50px,min], $lcgap, center:[257px,min], [164px,min], $lcgap",
            "21dlu, fill:25dlu, $lgap, 85dlu, $lgap, 44dlu, $rgap, 49dlu"));

        //---- label1 ----
        label1.setText("Resto");
        label1.setLabelFor(displayRest);
        label1.setFont(new Font("Tahoma", Font.PLAIN, 22));
        add(label1, CC.xy(3, 2, CC.FILL, CC.FILL));
        add(displayRest, CC.xy(5, 2, CC.FILL, CC.DEFAULT));

        //---- label2 ----
        label2.setText("Grazie per aver effettuato acquisti da noi!");
        label2.setFont(new Font("Tahoma", Font.PLAIN, 28));
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        add(label2, CC.xywh(2, 4, 5, 1, CC.FILL, CC.FILL));

        //---- startNewSale ----
        startNewSale.setText("Nuova Vendita");
        startNewSale.setBackground(new Color(102, 255, 102));
        startNewSale.setFont(new Font("Tahoma", Font.PLAIN, 22));
        startNewSale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewSaleActionPerformed(e);
            }
        });
        add(startNewSale, CC.xy(2, 8, CC.DEFAULT, CC.FILL));

        //---- goToMenu ----
        goToMenu.setText("Torna al Men\u00f9");
        goToMenu.setFont(new Font("Tahoma", Font.PLAIN, 22));
        goToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToMenuActionPerformed(e);
            }
        });
        add(goToMenu, CC.xy(6, 8, CC.DEFAULT, CC.FILL));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JTextField displayRest;
    private JLabel label2;
    private JButton startNewSale;
    private JButton goToMenu;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
