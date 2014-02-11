/*
 * Created by JFormDesigner on Fri Feb 07 17:29:34 CET 2014
 */

package gameshop.advance.ui.swing.customer;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * @author Matteo Gentile
 */
public class SearchPanel extends JPanel {
    public SearchPanel() {
        initComponents();
        
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        scrollPane1 = new JScrollPane();
        list1 = new JList();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        button5 = new JButton();

        //======== this ========
        setLayout(new FormLayout(
            "[45px,default]:grow, $lcgap, 250px, $lcgap, 75px, 2*($lcgap, 55px), $lcgap, [125px,default], $lcgap, [45px,default]:grow",
            "[20px,min]:grow, 6*($lgap, 30px), $lgap, [50px,default]:grow"));

        //---- label1 ----
        label1.setText("Prodotti:");
        add(label1, CC.xy(3, 3));

        //---- label2 ----
        label2.setText("Prezzo(\u20ac)");
        add(label2, CC.xy(5, 3, CC.CENTER, CC.DEFAULT));

        //---- label3 ----
        label3.setText("Disp");
        add(label3, CC.xy(7, 3, CC.CENTER, CC.DEFAULT));

        //---- label4 ----
        label4.setText("Cat.");
        add(label4, CC.xy(9, 3, CC.CENTER, CC.DEFAULT));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(list1);
        }
        add(scrollPane1, CC.xywh(3, 5, 7, 9));

        //---- button1 ----
        button1.setText("Prenota");
        add(button1, CC.xy(11, 5));

        //---- button2 ----
        button2.setText("Prenota");
        add(button2, CC.xy(11, 7));

        //---- button3 ----
        button3.setText("Prenota");
        add(button3, CC.xy(11, 9));

        //---- button4 ----
        button4.setText("Prenota");
        add(button4, CC.xy(11, 11));

        //---- button5 ----
        button5.setText("Prenota");
        add(button5, CC.xy(11, 13));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JScrollPane scrollPane1;
    private JList list1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
