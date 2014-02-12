/*
 * Created by JFormDesigner on Wed Feb 12 09:56:25 CET 2014
 */

package gameshop.advance.ui.swing.customer;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * @author Matteog
 */
public class ReservationPanel extends JPanel {
    public ReservationPanel() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        button1 = new JButton();
        button2 = new JButton();
        panel1 = new JPanel();
        label1 = new JLabel();
        textField1 = new JTextField();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();

        //======== this ========
        setLayout(new FormLayout(
            "45dlu, $lcgap, [120dlu,min], $lcgap, 45dlu",
            "[20dlu,min], $lgap, 50dlu, $lgap, 80dlu, $lgap, [20dlu,default]:grow"));

        //---- button1 ----
        button1.setText("Indietro");
        add(button1, CC.xy(1, 1));

        //---- button2 ----
        button2.setText("Avanti");
        add(button2, CC.xy(5, 1));

        //======== panel1 ========
        {
            panel1.setLayout(new FormLayout(
                "50dlu, $lcgap, 80dlu",
                "default"));

            //---- label1 ----
            label1.setText("Cerca:");
            panel1.add(label1, CC.xy(1, 1));
            panel1.add(textField1, CC.xy(3, 1));
        }
        add(panel1, CC.xy(3, 3));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        add(scrollPane1, CC.xy(3, 5));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JButton button1;
    private JButton button2;
    private JPanel panel1;
    private JLabel label1;
    private JTextField textField1;
    private JScrollPane scrollPane1;
    private JTable table1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
