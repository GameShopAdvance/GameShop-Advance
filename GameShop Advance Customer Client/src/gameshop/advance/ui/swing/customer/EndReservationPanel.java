/*
 * Created by JFormDesigner on Sat Feb 15 00:12:13 CET 2014
 */

package gameshop.advance.ui.swing.customer;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Matteo Gentile
 */
public class EndReservationPanel extends JPanel {
    public EndReservationPanel() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        button1 = new JButton();
        button2 = new JButton();
        panel1 = new JPanel();
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        textField2 = new JTextField();

        //======== this ========
        setLayout(new FormLayout(
            "[20dlu,default]:grow, $lcgap, [30dlu,default], $lcgap, 130dlu, $lcgap, [30dlu,default], $lcgap, [20dlu,default]:grow",
            "[20dlu,default]:grow, $lgap, default, $lgap, [75dlu,default], $lgap, [54dlu,default]:grow"));

        //---- button1 ----
        button1.setText("Annulla");
        button1.setBackground(new Color(255, 51, 0));
        button1.setFont(button1.getFont().deriveFont(button1.getFont().getStyle() | Font.BOLD));
        add(button1, CC.xy(3, 3));

        //---- button2 ----
        button2.setText("Completa");
        button2.setBackground(new Color(102, 204, 0));
        button2.setFont(button2.getFont().deriveFont(button2.getFont().getStyle() | Font.BOLD));
        add(button2, CC.xy(7, 3));

        //======== panel1 ========
        {
            panel1.setLayout(new FormLayout(
                "61dlu, $lcgap, 63dlu",
                "default, $lgap, default"));

            //---- label1 ----
            label1.setText("Totale:");
            panel1.add(label1, CC.xy(1, 1));
            panel1.add(textField1, CC.xy(3, 1));

            //---- label2 ----
            label2.setText("N.Prenotazione:");
            panel1.add(label2, CC.xy(1, 3));
            panel1.add(textField2, CC.xy(3, 3));
        }
        add(panel1, CC.xy(5, 5));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JButton button1;
    private JButton button2;
    private JPanel panel1;
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JTextField textField2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
