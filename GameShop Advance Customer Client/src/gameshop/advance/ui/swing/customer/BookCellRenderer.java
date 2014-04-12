/*
 * Created by JFormDesigner on Tue Apr 01 14:37:18 CEST 2014
 */

package gameshop.advance.ui.swing.customer;

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
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 * @author Pippo
 */
public class BookCellRenderer extends JPanel implements ListCellRenderer<IRigaDiTransazioneRemote>{
    public BookCellRenderer() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        name = new JLabel();
        quantity = new JLabel();
        separator1 = new JSeparator();
        subTotal = new JLabel();

        //======== this ========
        setBorder(LineBorder.createBlackLineBorder());
        setLayout(new FormLayout(
            "[100dlu,default]:grow, $lcgap, 50dlu, $lcgap, 10dlu, $lcgap, 50dlu",
            "fill:default:grow"));
        add(name, CC.xy(1, 1));

        //---- quantity ----
        quantity.setHorizontalAlignment(SwingConstants.CENTER);
        add(quantity, CC.xy(3, 1));

        //---- separator1 ----
        separator1.setOrientation(SwingConstants.VERTICAL);
        add(separator1, CC.xy(5, 1, CC.CENTER, CC.DEFAULT));

        //---- subTotal ----
        subTotal.setHorizontalAlignment(SwingConstants.CENTER);
        add(subTotal, CC.xy(7, 1));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel name;
    private JLabel quantity;
    private JSeparator separator1;
    private JLabel subTotal;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    @Override
    public Component getListCellRendererComponent(JList<? extends IRigaDiTransazioneRemote> list, IRigaDiTransazioneRemote value, int index, boolean isSelected, boolean cellHasFocus) {
        try {
            this.name.setText(value.getDescrizione().getDescrizione());
        } catch (RemoteException ex) {
            Logger.getLogger(BookCellRenderer.class.getName()).log(Level.SEVERE, null, ex);
            this.name.setText("Prodotto... (!!!)");
        }
        try {
            this.quantity.setText(""+value.getQuantity());
        } catch (RemoteException ex) {
            Logger.getLogger(BookCellRenderer.class.getName()).log(Level.SEVERE, null, ex);
            this.quantity.setText("...");
        }
        try {
            this.subTotal.setText(value.getSubTotal().toString());
        } catch (RemoteException ex) {
            Logger.getLogger(BookCellRenderer.class.getName()).log(Level.SEVERE, null, ex);
            this.subTotal.setText("!!!");
        }
        
        return this;
    }
}
