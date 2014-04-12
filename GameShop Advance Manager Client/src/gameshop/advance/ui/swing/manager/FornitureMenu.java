/*
 * Created by JFormDesigner on Wed Apr 02 20:25:37 CEST 2014
 */

package gameshop.advance.ui.swing.manager;

import java.awt.*;
import javax.swing.plaf.*;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.FornitureControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.interfaces.remote.IInformazioniProdottoRemote;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class FornitureMenu extends JPanel{
    
    private final DefaultListModel<IInformazioniProdottoRemote> infoProducts;
    
    public FornitureMenu() { 
        initComponents();
        this.infoProducts = new DefaultListModel<>();
        this.infoList.setCellRenderer(new InfoCellRenderer());
        this.infoList.setModel(this.infoProducts);
    }
    
    public void addInfoProduct(IInformazioniProdottoRemote infoProd){
        this.infoProducts.addElement(infoProd);
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

    
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        infoList = new JList();
        clearForniture = new JButton();
        button2 = new JButton();

        //======== this ========
        setLayout(new FormLayout(
            "default, $lcgap, 143dlu, 2*($lcgap, 20dlu), $lcgap, 143dlu, $lcgap, default",
            "[25px,default], $lgap, fill:177dlu, $lgap, fill:[72px,default], $lgap"));

        //======== scrollPane1 ========
        {

            //---- infoList ----
            infoList.setBackground(new Color(240, 240, 240));
            scrollPane1.setViewportView(infoList);
        }
        add(scrollPane1, CC.xywh(3, 3, 7, 1));

        //---- clearForniture ----
        clearForniture.setText("Indietro");
        clearForniture.setFont(new Font("Tahoma", Font.PLAIN, 20));
        clearForniture.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFornitureActionPerformed(e);
            }
        });
        add(clearForniture, CC.xy(3, 5));

        //---- button2 ----
        button2.setText("Procedi");
        button2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        button2.setEnabled(false);
        add(button2, CC.xy(9, 5));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JList infoList;
    private JButton clearForniture;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
