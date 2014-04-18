/*
 * Created by JFormDesigner on Wed Apr 02 18:28:03 CEST 2014
 */

package gameshop.advance.ui.swing.manager;

import com.jgoodies.forms.builder.PanelBuilder;
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

        panel1 = new JPanel();
        panel2 = new JPanel();
        scrollPane2 = new JScrollPane();
        offertsList = new JList<>();
        panel3 = new JPanel();
        scrollPane1 = new JScrollPane();
        lowQuantityList = new JList<>();

        //======== this ========
        setMinimumSize(new Dimension(720, 480));
        setName("this");

        //---- fornitureButton ----
        fornitureButton.setText("Gestisci Forniture");
        fornitureButton.setName("fornitureButton");
        fornitureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fornitureActionPerformed(e);
            }
        });

        //======== panel1 ========
        {
            panel1.setName("panel1");

            //======== panel2 ========
            {
                panel2.setBorder(new TitledBorder("Prodotti in esaurimento scorte"));
                panel2.setName("panel2");

                //======== scrollPane2 ========
                {
                    scrollPane2.setName("scrollPane2");

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
                    offertsList.setName("offertsList");
                    scrollPane2.setViewportView(offertsList);
                }

                PanelBuilder panel2Builder = new PanelBuilder(new FormLayout(
                    "[245px,pref]:grow",
                    "fill:81dlu:grow"), panel2);

                panel2Builder.add(scrollPane2, CC.xy(1, 1));
            }

            //======== panel3 ========
            {
                panel3.setBorder(new TitledBorder("Attualmente in offerta"));
                panel3.setName("panel3");

                //======== scrollPane1 ========
                {
                    scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                    scrollPane1.setViewportBorder(null);
                    scrollPane1.setName("scrollPane1");

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
                    lowQuantityList.setName("lowQuantityList");
                    scrollPane1.setViewportView(lowQuantityList);
                }

                PanelBuilder panel3Builder = new PanelBuilder(new FormLayout(
                    "[245px,pref]:grow",
                    "fill:[35px,default]:grow"), panel3);

                panel3Builder.add(scrollPane1, CC.xy(1, 1));
            }

            PanelBuilder panel1Builder = new PanelBuilder(new FormLayout(
                "[288px,pref]:grow",
                "fill:30dlu, $lgap, fill:30dlu:grow, 2*($lgap, fill:30dlu), $lgap, fill:30dlu:grow, $lgap, fill:30dlu"), panel1);
            ((FormLayout)panel1.getLayout()).setRowGroups(new int[][] {{1, 3, 5}, {7, 9, 11}});

            panel1Builder.add(panel2, CC.xywh(1, 1, 1, 5));
            panel1Builder.add(panel3, CC.xywh(1, 7, 1, 5));
        }

        //---- magazzinoButton ----
        magazzinoButton.setText("Controlla Magazzino");
        magazzinoButton.setEnabled(false);
        magazzinoButton.setName("magazzinoButton");

        //---- nullButton1 ----
        nullButton1.setEnabled(false);
        nullButton1.setName("nullButton1");

        //---- prezziButton ----
        prezziButton.setText("Gestisci Prezzi");
        prezziButton.setEnabled(false);
        prezziButton.setName("prezziButton");

        //---- scontiButton ----
        scontiButton.setText("Gestisci Sconti");
        scontiButton.setEnabled(false);
        scontiButton.setName("scontiButton");

        //---- nullButton2 ----
        nullButton2.setEnabled(false);
        nullButton2.setName("nullButton2");

        //---- venditeButton ----
        venditeButton.setText("Analizza Vendite");
        venditeButton.setEnabled(false);
        venditeButton.setName("venditeButton");

        PanelBuilder builder = new PanelBuilder(new FormLayout(
            "$rgap, [303px,pref]:grow, $ugap, [288px,pref]:grow, $rgap",
            "$rgap, 6*(fill:30dlu, $lgap), fill:30dlu, $rgap, default:grow"), this);

        builder.add(fornitureButton, CC.xy  (2,  2));
        builder.add(panel1,          CC.xywh(4,  2, 1, 15));
        builder.add(magazzinoButton, CC.xy  (2,  4));
        builder.add(nullButton1,     CC.xy  (2,  6));
        builder.add(prezziButton,    CC.xy  (2,  8));
        builder.add(scontiButton,    CC.xy  (2, 10));
        builder.add(nullButton2,     CC.xy  (2, 12));
        builder.add(venditeButton,   CC.xy  (2, 14));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JButton fornitureButton;
    private JPanel panel1;
    private JPanel panel2;
    private JScrollPane scrollPane2;
    private JList<String> offertsList;
    private JPanel panel3;
    private JScrollPane scrollPane1;
    private JList<String> lowQuantityList;
    private JButton magazzinoButton;
    private JButton nullButton1;
    private JButton prezziButton;
    private JButton scontiButton;
    private JButton nullButton2;
    private JButton venditeButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
