/*
 * Created by JFormDesigner on Fri Jan 31 21:43:02 CET 2014
 */

package gameshop.advance.ui.swing.employee;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.ui.swing.UIWindowSingleton;
import gameshop.advance.utility.Money;
import java.awt.Color;
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
 * @aJButtonswiimport javax.swing.JPanel;
import javax.swing.JTextField;
ng.JLabel
import javaxPiForimport javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
mLayout;
import javax.swing.JButtonpo
 */
public class EndSalePanel extends JPanel {
    public EndSalePanel() {
        initComponents();
        try {
            this.displayRest.setText(SaleController.getInstance().getResto().toString());
        } catch (NullPointerException ex) {
            Logger.getLogger(EndSalePanel.class.getName()).log(Level.SEVERE, null, ex);
            UIWindowSingleton.getInstance().displayError("Ci sono problemi di comunicazione,"
                    + " si prega di controllare la configurazione del sistema.");
        } catch (RemoteException ex) {
            Logger.getLogger(EndSalePanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConfigurationException ex) {
            Logger.getLogger(EndSalePanel.class.getName()).log(Level.SEVERE, null, ex);
            UIWindowSingleton.getInstance().displayError("Ci sono problemi nella lettura del file di configurazione: "+ex.getConfigurationPath()+"."
                    + " Per maggiori informazioni rivolgersi all'amministratore di sistema.");
        }
    }
    
    
     private void startNewSaleActionPerformed(java.awt.event.ActionEvent evt) {                                             
        try{
            SaleController.getInstance().avviaNuovaVendita();
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
    
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        displayRest = new JTextField();
        label2 = new JLabel();
        startNewSale = new JButton();
        goToMenu = new JButton();

        //======== this ========
        setLayout(new FormLayout(
            "45dlu:grow, $lcgap, [150px,min]:grow, $lcgap, [45dlu,default]:grow",
            "25dlu:grow, $lgap, 20dlu:grow, $lgap, default:grow, $lgap, 25dlu:grow, $lgap, default:grow, 2*($lgap, 25dlu:grow)"));

        //---- label1 ----
        label1.setText("Resto:");
        add(label1, CC.xy(1, 1, CC.CENTER, CC.DEFAULT));
        add(displayRest, CC.xy(3, 1));

        //---- label2 ----
        label2.setText("Grazie per aver effettuato acquisti da noi!");
        add(label2, CC.xy(3, 3));

        //---- startNewSale ----
        startNewSale.setText("Avvia una nuova Vendita");
        startNewSale.setBackground(new Color(102, 255, 102));
        startNewSale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewSaleActionPerformed(e);
            }
        });
        add(startNewSale, CC.xy(3, 7, CC.DEFAULT, CC.FILL));

        //---- goToMenu ----
        goToMenu.setText("Torna al Men\u00f9");
        goToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToMenuActionPerformed(e);
            }
        });
        add(goToMenu, CC.xy(3, 11, CC.DEFAULT, CC.FILL));
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
