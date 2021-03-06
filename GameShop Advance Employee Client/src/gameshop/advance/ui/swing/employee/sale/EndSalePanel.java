/*
 * Created by JFormDesigner on Fri Jan 31 21:43:02 CET 2014
 */

package gameshop.advance.ui.swing.employee.sale;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.SaleControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.technicalservices.ExceptionHandlerSingleton;
import gameshop.advance.technicalservices.LoggerSingleton;
import gameshop.advance.ui.swing.UIWindowSingleton;
import gameshop.advance.ui.swing.employee.EmployeeMenuPanel;
import gameshop.advance.ui.swing.factory.UIFactory;
import gameshop.advance.utility.Money;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


/**
 * Schermata mostrata al termine della vendita.Mostra il resto dovuto.Permette di cominciare una nuova 
 * vendita o di tornare al menu.
 * @author Matteo Gentile
 */
public class EndSalePanel extends JPanel {
    
    public EndSalePanel() {
        initComponents();
        try {
            this.displayRest.setText(SaleControllerSingleton.getInstance().getResto().toString());
        } catch (NullPointerException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        } catch (RemoteException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        } catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }
    }
    
    
     private void startNewSaleActionPerformed(java.awt.event.ActionEvent evt) {                                             
        try{
            SaleControllerSingleton.getInstance().avviaNuovaVendita();
        } catch (NullPointerException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        } catch (RemoteException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        } catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
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


    private void createUIComponents() {
        this.label1 = UIFactory.getInstance().getBodyLabel();
        this.label2 = UIFactory.getInstance().getBodyLabel();
        this.goToMenu = UIFactory.getInstance().getSimpleButton();
        this.startNewSale = UIFactory.getInstance().getSimpleButton();
    }
    
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        createUIComponents();

        displayRest = new JTextField();

        //======== this ========
        setName("this");

        //---- label1 ----
        label1.setText("Resto");
        label1.setLabelFor(displayRest);
        label1.setName("label1");

        //---- displayRest ----
        displayRest.setName("displayRest");

        //---- label2 ----
        label2.setText("Grazie per aver effettuato acquisti da noi!");
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setName("label2");

        //---- startNewSale ----
        startNewSale.setText("Nuova Vendita");
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
            "$rgap, center:[75dlu,default], $lcgap, default:grow, $lcgap, [50px,min], $lcgap, center:[257px,min], $lcgap, default:grow, $lcgap, [75dlu,default], $rgap",
            "21dlu:grow, fill:25dlu, $lgap, 85dlu, $lgap, 44dlu, $rgap, [35dlu,default], $rgap"), this);

        builder.add(label1,       CC.xy  ( 6, 2, CC.FILL,    CC.FILL));
        builder.add(displayRest,  CC.xy  ( 8, 2, CC.FILL, CC.DEFAULT));
        builder.add(label2,       CC.xywh( 2, 4,      11,          1, CC.FILL, CC.FILL));
        builder.add(startNewSale, CC.xy  ( 2, 8, CC.FILL,    CC.FILL));
        builder.add(goToMenu,     CC.xy  (12, 8, CC.FILL,    CC.FILL));
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
