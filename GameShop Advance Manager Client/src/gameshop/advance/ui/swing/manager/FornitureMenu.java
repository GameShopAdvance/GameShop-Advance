/*
 * Created by JFormDesigner on Wed Apr 02 20:25:37 CEST 2014
 */

package gameshop.advance.ui.swing.manager;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.FornitureControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.interfaces.IListPanel;
import gameshop.advance.ui.swing.factory.UIFactory;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;

public class FornitureMenu extends JPanel implements IListPanel {
       
    public FornitureMenu() throws RemoteException { 
        try
        {
            initComponents();
            FornitureControllerSingleton.getInstance().setFornitureList(this);
        } catch (ConfigurationException ex)
        {
            Logger.getLogger(FornitureMenu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex)
        {
            Logger.getLogger(FornitureMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void clearFornitureActionPerformed(ActionEvent e){ 
        try{
            FornitureControllerSingleton.getInstance().clearForniture();
        } catch (ConfigurationException ex) {
            Logger.getLogger(FornitureMenu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(FornitureMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createUIComponents() {
        this.clearForniture = UIFactory.getInstance().getSimpleButton();
        this.procediButton = UIFactory.getInstance().getSimpleButton();
    }

    
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        createUIComponents();

        scrollPane1 = new JScrollPane();
        infoList = new JList();

        //======== this ========
        setName("this");

        //======== scrollPane1 ========
        {
            scrollPane1.setName("scrollPane1");

            //---- infoList ----
            infoList.setBackground(new Color(240, 240, 240));
            infoList.setName("infoList");
            scrollPane1.setViewportView(infoList);
        }

        //---- clearForniture ----
        clearForniture.setText("Indietro");
        clearForniture.setFont(new Font("Tahoma", Font.PLAIN, 14));
        clearForniture.setName("clearForniture");
        clearForniture.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFornitureActionPerformed(e);
            }
        });

        //---- procediButton ----
        procediButton.setText("Procedi");
        procediButton.setEnabled(false);
        procediButton.setName("procediButton");

        PanelBuilder builder = new PanelBuilder(new FormLayout(
            "[15dlu,default]:grow, $lcgap, [75dlu,default], $lcgap, [140dlu,default]:grow, $lcgap, [75dlu,default], $lcgap, [15dlu,default]:grow",
            "[15px,default]:grow, $lgap, fill:177dlu, $lgap, fill:[35px,default], $lgap, [15dlu,default]:grow, $lgap"), this);

        builder.add(scrollPane1,    CC.xywh(3, 3, 5, 1));
        builder.add(clearForniture, CC.xy  (3, 5));
        builder.add(procediButton,  CC.xy  (7, 5));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JList infoList;
    private JButton clearForniture;
    private JButton procediButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    @Override
    public void setList(ListModel model, ListCellRenderer renderer)
    {
        this.infoList.setCellRenderer(renderer);
        this.infoList.setModel(model);
    }
}
