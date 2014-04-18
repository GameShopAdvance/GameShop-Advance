/*
 * Created by JFormDesigner on Wed Feb 12 17:45:43 CET 2014
 */

package gameshop.advance.ui.swing.employee.book;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.BookControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.technicalservices.ExceptionHandlerSingleton;
import gameshop.advance.technicalservices.LoggerSingleton;
import gameshop.advance.ui.swing.UIWindowSingleton;
import gameshop.advance.ui.swing.employee.EmployeeMenuPanel;
import gameshop.advance.ui.swing.factory.UIFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * @author Matteo Gentile
 */
public class EndBookPanel extends JPanel {
    public EndBookPanel() {
        initComponents();
        try {
            this.displayRest.setText(BookControllerSingleton.getInstance().getResto().toString());
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


    private void goToMenuActionPerformed(ActionEvent e) {
        UIWindowSingleton.getInstance().setPanel(new EmployeeMenuPanel());
        UIWindowSingleton.getInstance().refreshContent();
    }


    private void createUIComponents() {
        this.label1 = UIFactory.getInstance().getBodyLabel();
        this.label2 = UIFactory.getInstance().getHeaderLabel();
        this.goToMenu = UIFactory.getInstance().getSimpleButton();
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
        label2.setText("Prenotazione effettuata!");
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setName("label2");

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
            "$lcgap, [15dlu,default]:grow, $lcgap, 56dlu, 2*($lcgap, [75dlu,default]), $lcgap, [15dlu,default]:grow",
            "[15dlu,default]:grow, $lgap, fill:37dlu, $lgap, [35dlu,default], $ugap, [35dlu,default], $lgap, [15dlu,default]:grow"), this);

        builder.add(label1,      CC.xy  (4, 3,    CC.FILL, CC.FILL));
        builder.add(displayRest, CC.xywh(6, 3,          3,       1, CC.FILL, CC.DEFAULT));
        builder.add(label2,      CC.xywh(4, 5,          5,       1, CC.FILL, CC.FILL   ));
        builder.add(goToMenu,    CC.xy  (6, 7, CC.DEFAULT, CC.FILL));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JTextField displayRest;
    private JLabel label2;
    private JButton goToMenu;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
