/*
 * Created by JFormDesigner on Wed Apr 02 18:28:03 CEST 2014
 */

package gameshop.advance.ui.swing.manager;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.FornitureControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.exceptions.QuantityException;
import gameshop.advance.ui.swing.UIFactory;
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

    private void createUIComponents() {
        this.fornitureButton = UIFactory.getInstance().getSimpleButton();
        this.magazzinoButton = UIFactory.getInstance().getSimpleButton();
        this.prezziButton = UIFactory.getInstance().getSimpleButton();
        this.scontiButton = UIFactory.getInstance().getSimpleButton();
        this.venditeButton = UIFactory.getInstance().getSimpleButton();
        this.nullButton1 = UIFactory.getInstance().getSimpleButton();
        this.nullButton2 = UIFactory.getInstance().getSimpleButton();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        createUIComponents();

        panel2 = new JPanel();
        scrollPane2 = new JScrollPane();
        offertsList = new JList<>();
        panel3 = new JPanel();
        scrollPane1 = new JScrollPane();
        lowQuantityList = new JList<>();

        //======== this ========
        setLayout(new FormLayout(
            "$lcgap, [15dlu,default], [303px,pref], 20dlu, [288px,pref], $lcgap, [15dlu,default]:grow",
            "$lgap, [15dlu,default]:grow, 7*($lgap, fill:30dlu), $lgap, [15dlu,default]:grow"));

        //---- fornitureButton ----
        fornitureButton.setText("Gestisci Forniture");
        fornitureButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        fornitureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fornitureActionPerformed(e);
            }
        });
        add(fornitureButton, CC.xy(3, 4));

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

        //---- magazzinoButton ----
        magazzinoButton.setText("Controlla Magazzino");
        magazzinoButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        magazzinoButton.setEnabled(false);
        add(magazzinoButton, CC.xy(3, 6));

        //---- nullButton1 ----
        nullButton1.setEnabled(false);
        add(nullButton1, CC.xy(3, 8));

        //---- prezziButton ----
        prezziButton.setText("Gestisci Prezzi");
        prezziButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        prezziButton.setEnabled(false);
        add(prezziButton, CC.xy(3, 10));

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

        //---- scontiButton ----
        scontiButton.setText("Gestisci Sconti");
        scontiButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        scontiButton.setEnabled(false);
        add(scontiButton, CC.xy(3, 12));

        //---- nullButton2 ----
        nullButton2.setEnabled(false);
        add(nullButton2, CC.xy(3, 14));

        //---- venditeButton ----
        venditeButton.setText("Analizza Vendite");
        venditeButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        venditeButton.setEnabled(false);
        add(venditeButton, CC.xy(3, 16));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JButton fornitureButton;
    private JPanel panel2;
    private JScrollPane scrollPane2;
    private JList<String> offertsList;
    private JButton magazzinoButton;
    private JButton nullButton1;
    private JButton prezziButton;
    private JPanel panel3;
    private JScrollPane scrollPane1;
    private JList<String> lowQuantityList;
    private JButton scontiButton;
    private JButton nullButton2;
    private JButton venditeButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
