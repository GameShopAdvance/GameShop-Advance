/*
 * Created by JFormDesigner on Fri Feb 07 22:03:46 CET 2014
 */

package gameshop.advance.ui.swing.customer;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.ReservationControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.ui.swing.factory.UIFactory;
import gameshop.advance.ui.swing.factory.UIStyleSingleton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * @author Matteo Gentile
 */
public class CustomerPanel extends JPanel {
    public CustomerPanel() {
        initComponents();
        this.setStyle();
    }

    protected void setStyle(){
        this.label1.setFont(UIStyleSingleton.getInstance().getBigFont());
        this.button1.setFont(UIStyleSingleton.getInstance().getNormalFont());
    }
    
    private void avviaPrenotazione(ActionEvent e) {    
            try {
                ReservationControllerSingleton.getInstance().avviaPrenotazione();
            } catch (NullPointerException ex) {
                Logger.getLogger(CustomerPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RemoteException ex) {
                Logger.getLogger(CustomerPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ConfigurationException ex) {
                Logger.getLogger(CustomerPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    private void createUIComponents() {
        this.label1 = UIFactory.getInstance().getHeaderLabel();
        this.button1 = UIFactory.getInstance().getMenuButton();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        createUIComponents();

        label1 = new JLabel();

        //======== this ========
        setName("this");

        //---- label1 ----
        label1.setText("Prenota uno dei nostri prodotti!");
        label1.setFont(new Font("Dialog", Font.PLAIN, 16));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setName("label1");

        //---- button1 ----
        button1.setText("Avvia Prenotazione");
        button1.setFont(new Font("Ubuntu", Font.PLAIN, 14));
        button1.setName("button1");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                avviaPrenotazione(e);
            }
        });

        PanelBuilder builder = new PanelBuilder(new FormLayout(
            "[150px,min]:grow, $lcgap, [75dlu,default], $lcgap, [150px,min]:grow",
            "[100px,min]:grow, $lgap, [35dlu,min], $lgap, fill:[100px,min]:grow"), this);

        builder.add(label1,  CC.xy(3, 1));
        builder.add(button1, CC.xy(3, 3, CC.FILL, CC.FILL));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
