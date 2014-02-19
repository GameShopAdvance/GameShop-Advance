/*
 * Created by JFormDesigner on Wed Jan 29 18:24:38 CET 2014
 */

package gameshop.advance.ui.swing.employee;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.BookControllerSingleton;
import gameshop.advance.controller.InventoryControllerSingleton;
import gameshop.advance.controller.SaleControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.ui.swing.UIWindowSingleton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author Matteo Gentile
 */
public class EmployeeMenuPanel extends JPanel {
    
    public EmployeeMenuPanel() {
        initComponents();
    }
    
     /**
     * Invia al controller il comando di avviare una nuova vendita.Se non si riesce a
     * stabilire una connessione con il server visualizzerà gli errori.
     */
    private void startSaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startSaleActionPerformed
       
        try{
            SaleControllerSingleton.getInstance().avviaNuovaVendita();
        } catch(NullPointerException e){
            UIWindowSingleton.getInstance().displayError("Ci sono problemi di comunicazione,"
                    + " si prega di controllare la configurazione del sistema.");
        } catch(RemoteException e){  
            UIWindowSingleton.getInstance().displayError("Non è possibile contattare il server. "
                    + "Si prega di riprovare. Se il problema persiste, contattare l'amministratore di sistema.");
        } catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError("Ci sono problemi nella lettura del file di configurazione: "+ex.getConfigurationPath()+"."
                    + " Per maggiori informazioni rivolgersi all'amministratore di sistema.");
        }
        
    }//GEN-LAST:event_startSaleActionPerformed

    private void faiInventario(ActionEvent e) {
        try {
            InventoryControllerSingleton.getInstance().avviaInventario();
        } catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError("Ci sono problemi nella lettura del file di configurazione: "+ex.getConfigurationPath()+"."
                    + " Per maggiori informazioni rivolgersi all'amministratore di sistema.");
        } catch (RemoteException | NotBoundException ex) {
            UIWindowSingleton.getInstance().displayError("Non è possibile contattare il server. "
                    + "Si prega di riprovare. Se il problema persiste, contattare l'amministratore di sistema.");
        }
    }


    private void manageBookActionPerformed(ActionEvent e) {
        try {
            BookControllerSingleton.getInstance().gestisciPrenotazione();
        } catch (Exception ex) {
            Logger.getLogger(EmployeeMenuPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
//        } catch (ConfigurationException ex) {
//            UIWindowSingleton.getInstance().displayError("Ci sono problemi nella lettura del file di configurazione: "+ex.getConfigurationPath()+"."
//                    + " Per maggiori informazioni rivolgersi all'amministratore di sistema.");
//         catch (RemoteException ex) {
//            UIWindowSingleton.getInstance().displayError("Non è possibile contattare il server. "
//                    + "Si prega di riprovare. Se il problema persiste, contattare l'amministratore di sistema.");
//        } catch(NullPointerException ex){
//            UIWindowSingleton.getInstance().displayError("Ci sono problemi di comunicazione,"
//                    + " si prega di controllare la configurazione del sistema.");
//        }
//        
//        catch (ConfigurationException ex) {
//            Logger.getLogger(EmployeeMenuPanel.class.getName()).log(Level.SEVERE, null, ex);
//        } 
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        startSale = new JButton();
        manageBook = new JButton();
        button1 = new JButton();

        //======== this ========
        setLayout(new FormLayout(
            "[150px,min]:grow, $lcgap, [150px,default], $lcgap, 150px:grow",
            "50px:grow, 2*($lgap, 45px), $lgap, 50px:grow"));

        //---- startSale ----
        startSale.setText("Avvia Vendita");
        startSale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startSaleActionPerformed(e);
            }
        });
        add(startSale, CC.xywh(1, 3, 1, 3, CC.FILL, CC.FILL));

        //---- manageBook ----
        manageBook.setText("Gestisci Prenotazioni");
        manageBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manageBookActionPerformed(e);
            }
        });
        add(manageBook, CC.xywh(3, 3, 1, 3));

        //---- button1 ----
        button1.setText("Fai Inventario");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                faiInventario(e);
            }
        });
        add(button1, CC.xywh(5, 3, 1, 3, CC.FILL, CC.FILL));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JButton startSale;
    private JButton manageBook;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
