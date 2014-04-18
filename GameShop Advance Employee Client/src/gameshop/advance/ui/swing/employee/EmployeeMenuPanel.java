/*
 * Created by JFormDesigner on Wed Jan 29 18:24:38 CET 2014
 */

package gameshop.advance.ui.swing.employee;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.BookControllerSingleton;
import gameshop.advance.controller.InventoryControllerSingleton;
import gameshop.advance.controller.SaleControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.technicalservices.ExceptionHandlerSingleton;
import gameshop.advance.technicalservices.LoggerSingleton;
import gameshop.advance.ui.swing.UIWindowSingleton;
import gameshop.advance.ui.swing.factory.UIFactory;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Schermata principale del Client del commesso.Mostra tutte le operazioni che può effettuare il commesso.
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
        } catch(NullPointerException ex){
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        } catch(RemoteException ex){  
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        } catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }
        
    }

    private void faiInventario(ActionEvent e) {
        try {
            InventoryControllerSingleton.getInstance().avviaInventario();
        } catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        } catch (RemoteException | NotBoundException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }
    }


    private void manageBookActionPerformed(ActionEvent e) {
        try {
            BookControllerSingleton.getInstance().gestisciPrenotazione();
        }
        catch (NullPointerException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }
        catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }
        catch (RemoteException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }

    }

    private void createUIComponents() {
        this.startSale = UIFactory.getInstance().getSimpleButton();
        this.manageBook = UIFactory.getInstance().getSimpleButton();
        this.button1 = UIFactory.getInstance().getSimpleButton();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        createUIComponents();

        button2 = new JButton();

        //======== this ========
        setMaximumSize(new Dimension(111111, 111111));
        setName("this");

        //---- startSale ----
        startSale.setText("Vendita");
        startSale.setName("startSale");
        startSale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startSaleActionPerformed(e);
            }
        });

        //---- manageBook ----
        manageBook.setText("Prenotazioni");
        manageBook.setMaximumSize(new Dimension(169, 37));
        manageBook.setMinimumSize(new Dimension(169, 37));
        manageBook.setPreferredSize(new Dimension(169, 37));
        manageBook.setName("manageBook");
        manageBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manageBookActionPerformed(e);
            }
        });

        //---- button1 ----
        button1.setText("Inventario");
        button1.setName("button1");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                faiInventario(e);
            }
        });

        //---- button2 ----
        button2.setAction(null);
        button2.setEnabled(false);
        button2.setName("button2");

        PanelBuilder builder = new PanelBuilder(new FormLayout(
            "$lcgap, default:grow, $rgap, 75dlu, 15dlu, 75dlu, $rgap, default:grow",
            "$lgap, default:grow, $rgap, fill:[35dlu,default], 15dlu, fill:[35dlu,default], $rgap, default:grow"), this);

        builder.add(startSale,  CC.xy(4, 4, CC.FILL, CC.FILL));
        builder.add(manageBook, CC.xy(6, 4, CC.FILL, CC.FILL));
        builder.add(button1,    CC.xy(4, 6, CC.FILL, CC.FILL));
        builder.add(button2,    CC.xy(6, 6));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JButton startSale;
    private JButton manageBook;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
