/*
 * Created by JFormDesigner on Sat Apr 12 13:17:50 CEST 2014
 */

package gameshop.advance.ui.swing.employee.sale;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.interfaces.remote.IRigaDiTransazioneRemote;
import java.awt.Component;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.ListCellRenderer;

/**
 * @author Matteo Gentile
 * 
 * */
public class rdvCellRender extends JPanel implements ListCellRenderer<IRigaDiTransazioneRemote> {
    public rdvCellRender() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        description = new JLabel();
        qty = new JLabel();
        subTotal = new JLabel();
        separator1 = new JSeparator();

        //======== this ========
        setLayout(new FormLayout(
            "[15dlu,default], $lcgap, [40dlu,default], $lcgap, 62dlu:grow, $lcgap, 25dlu, $lcgap, default",
            "[10dlu,default], $lgap, 30dlu, $lgap, 1dlu"));

        //---- description ----
        description.setText("text");
        add(description, CC.xy(3, 3, CC.FILL, CC.FILL));

        //---- qty ----
        qty.setText("qty #");
        add(qty, CC.xy(5, 3, CC.FILL, CC.DEFAULT));

        //---- subTotal ----
        subTotal.setText("text");
        add(subTotal, CC.xywh(7, 3, 2, 1, CC.FILL, CC.FILL));
        add(separator1, CC.xywh(1, 5, 9, 1));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel description;
    private JLabel qty;
    private JLabel subTotal;
    private JSeparator separator1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    @Override
    public Component getListCellRendererComponent(JList<? extends IRigaDiTransazioneRemote> list, IRigaDiTransazioneRemote value, int index, boolean isSelected, boolean cellHasFocus) {
        try {
            System.err.println("Descri:"+ value.getDescrizione().getDescrizione());
            this.description.setText(value.getDescrizione().getDescrizione());
            this.subTotal.setText(value.getSubTotal().toString());
            this.qty.setText(Integer.toString(value.getQuantity()));
        } catch (RemoteException ex) {
            Logger.getLogger(rdvCellRender.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this;
    }
}
