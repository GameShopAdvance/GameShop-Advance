/*
 * Created by JFormDesigner on Wed Apr 02 18:28:03 CEST 2014
 */

package gameshop.advance.ui.swing.manager;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.FornitureControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.exceptions.QuantityException;
import gameshop.advance.technicalservices.ExceptionHandlerSingleton;
import gameshop.advance.technicalservices.LoggerSingleton;
import gameshop.advance.ui.swing.UIWindowSingleton;
import gameshop.advance.ui.swing.factory.UIFactory;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

/**
 * Schermata principale del Client Manager.Contiene il menu delle operazioni che può effettuare il manager.
 * @author Salx
 */
public class ManagerMenu extends JPanel {
    
    public ManagerMenu() {
        initComponents();
    }

    private void fornitureActionPerformed(ActionEvent e) {
        try {
            FornitureControllerSingleton.getInstance().avviaGestioneForniture();
        }
        catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }
        catch (RemoteException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
            
        } catch (QuantityException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
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
        setMinimumSize(new Dimension(720, 480));
        setLayout(new FormLayout(
            "$rgap, [303px,pref]:grow, 20dlu, [288px,pref]:grow, $rgap",
            "[15dlu,default]:grow, 7*($lgap, fill:30dlu), $lgap, [15dlu,default]:grow"));

        //---- fornitureButton ----
        fornitureButton.setText("Gestisci Forniture");
        fornitureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fornitureActionPerformed(e);
            }
        });
        add(fornitureButton, CC.xy(2, 3));

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
        add(panel2, CC.xywh(4, 3, 1, 5));

        //---- magazzinoButton ----
        magazzinoButton.setText("Controlla Magazzino");
        magazzinoButton.setEnabled(false);
        add(magazzinoButton, CC.xy(2, 5));

        //---- nullButton1 ----
        nullButton1.setEnabled(false);
        add(nullButton1, CC.xy(2, 7));

        //---- prezziButton ----
        prezziButton.setText("Gestisci Prezzi");
        prezziButton.setEnabled(false);
        add(prezziButton, CC.xy(2, 9));

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
        add(panel3, CC.xywh(4, 9, 1, 5));

        //---- scontiButton ----
        scontiButton.setText("Gestisci Sconti");
        scontiButton.setEnabled(false);
        add(scontiButton, CC.xy(2, 11));

        //---- nullButton2 ----
        nullButton2.setEnabled(false);
        add(nullButton2, CC.xy(2, 13));

        //---- venditeButton ----
        venditeButton.setText("Analizza Vendite");
        venditeButton.setEnabled(false);
        add(venditeButton, CC.xy(2, 15));
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
