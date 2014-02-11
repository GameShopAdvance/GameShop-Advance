/*
 * Created by JFormDesigner on Fri Jan 31 21:43:02 CET 2014
 */

package gameshop.advance.ui.swing.employee;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.SaleControllerSingleton;
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
    
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        displayRest = new JTextField();
        label2 = new JLabel();
        startNewSale = new JButton();
        goToMenu = new JButton();

        //======== this ========
        setName("this");

        //---- label1 ----
        label1.setText("Resto:");
        label1.setLabelFor(displayRest);
        label1.setName("label1");

        //---- displayRest ----
        displayRest.setName("displayRest");

        //---- label2 ----
        label2.setText("Grazie per aver effettuato acquisti da noi!");
        label2.setName("label2");

        //---- startNewSale ----
        startNewSale.setText("Avvia una nuova Vendita");
        startNewSale.setBackground(new Color(102, 255, 102));
        startNewSale.setName("startNewSale");
        startNewSale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewSaleActionPerformed(e);
            }
        });

        //---- goToMenu ----
        goToMenu.setText("Torna al Men\u00f9");
        goToMenu.setName("goToMenu");
        goToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToMenuActionPerformed(e);
            }
        });

        PanelBuilder builder = new PanelBuilder(new FormLayout(
            "default:grow, 45dlu, $lcgap, [150px,min], default:grow",
            "default:grow, 25dlu, $lgap, 20dlu, $lgap, 25dlu, $rgap, 25dlu, fill:25dlu:grow"), this);

        builder.add(label1,       CC.xy(2, 2,    CC.FILL,    CC.FILL));
        builder.add(displayRest,  CC.xy(4, 2,    CC.FILL, CC.DEFAULT));
        builder.add(label2,       CC.xy(4, 4,    CC.FILL,    CC.FILL));
        builder.add(startNewSale, CC.xy(4, 6, CC.DEFAULT,    CC.FILL));
        builder.add(goToMenu,     CC.xy(4, 8, CC.DEFAULT,    CC.FILL));
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
