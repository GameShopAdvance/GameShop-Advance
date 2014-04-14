/*
 * Created by JFormDesigner on Wed Apr 02 18:28:03 CEST 2014
 */

package gameshop.advance.ui.swing.manager;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.FornitureControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.exceptions.QuantityException;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

public class ManagerMenu extends JPanel {
    public ManagerMenu() {
        initComponents();
    }

    private void fornitureActionPerformed(ActionEvent e) {
        try {
            FornitureControllerSingleton.getInstance().avviaGestioneForniture();
        }
        catch (ConfigurationException ex) {
            Logger.getLogger(ManagerMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (RemoteException ex) {
            Logger.getLogger(ManagerMenu.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (QuantityException ex) {
            Logger.getLogger(ManagerMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        button4 = new JButton();
        panel2 = new JPanel();
        scrollPane2 = new JScrollPane();
        offertsList = new JList<>();
        button7 = new JButton();
        button1 = new JButton();
        button6 = new JButton();
        panel3 = new JPanel();
        scrollPane1 = new JScrollPane();
        lowQuantityList = new JList<>();
        button5 = new JButton();
        button2 = new JButton();
        button3 = new JButton();

        //======== this ========
        setLayout(new FormLayout(
            "$lcgap, [15dlu,default], [303px,pref], 20dlu, [288px,pref], $lcgap, [15dlu,default]:grow",
            "$lgap, [15dlu,default]:grow, 7*($lgap, fill:30dlu), $lgap, [15dlu,default]:grow"));

        //---- button4 ----
        button4.setText("Gestisci Forniture");
        button4.setFont(new Font("Tahoma", Font.PLAIN, 18));
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fornitureActionPerformed(e);
            }
        });
        add(button4, CC.xy(3, 4));

        //======== panel2 ========
        {
            panel2.setBorder(new TitledBorder("Prodotti in esaurimento scorte"));
            panel2.setLayout(new FormLayout(
                "[245px,pref]:grow",
                "fill:81dlu"));

            //======== scrollPane2 ========
            {

                //---- offertsList ----
                offertsList.setModel(new AbstractListModel<String>() {
                    String[] values = {
                        "Prodotto 1",
                        "Prodotto 2",
                        "Prodotto 3",
                        "..."
                    };
                    @Override
                    public int getSize() { return values.length; }
                    @Override
                    public String getElementAt(int i) { return values[i]; }
                });
                offertsList.setFont(new Font("Tahoma", Font.PLAIN, 14));
                scrollPane2.setViewportView(offertsList);
            }
            panel2.add(scrollPane2, CC.xy(1, 1));
        }
        add(panel2, CC.xywh(5, 4, 1, 5));

        //---- button7 ----
        button7.setText("Controlla Magazzino");
        button7.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(button7, CC.xy(3, 6));

        //---- button1 ----
        button1.setEnabled(false);
        add(button1, CC.xy(3, 8));

        //---- button6 ----
        button6.setText("Gestisci Prezzi");
        button6.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(button6, CC.xy(3, 10));

        //======== panel3 ========
        {
            panel3.setBorder(new TitledBorder("Attualmente in offerta"));
            panel3.setLayout(new FormLayout(
                "[245px,pref]:grow",
                "fill:[35px,default]:grow"));

            //======== scrollPane1 ========
            {
                scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane1.setViewportBorder(null);

                //---- lowQuantityList ----
                lowQuantityList.setModel(new AbstractListModel<String>() {
                    String[] values = {
                        "Prodotto 1",
                        "Prodotto 2",
                        "Prodotto 3",
                        "..."
                    };
                    @Override
                    public int getSize() { return values.length; }
                    @Override
                    public String getElementAt(int i) { return values[i]; }
                });
                lowQuantityList.setFont(new Font("Tahoma", Font.PLAIN, 14));
                scrollPane1.setViewportView(lowQuantityList);
            }
            panel3.add(scrollPane1, CC.xy(1, 1));
        }
        add(panel3, CC.xywh(5, 10, 1, 5));

        //---- button5 ----
        button5.setText("Gestisci Sconti");
        button5.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(button5, CC.xy(3, 12));

        //---- button2 ----
        button2.setEnabled(false);
        add(button2, CC.xy(3, 14));

        //---- button3 ----
        button3.setText("Analizza Vendite");
        button3.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(button3, CC.xy(3, 16));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JButton button4;
    private JPanel panel2;
    private JScrollPane scrollPane2;
    private JList<String> offertsList;
    private JButton button7;
    private JButton button1;
    private JButton button6;
    private JPanel panel3;
    private JScrollPane scrollPane1;
    private JList<String> lowQuantityList;
    private JButton button5;
    private JButton button2;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
