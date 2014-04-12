/*
 * Created by JFormDesigner on Tue Apr 01 14:37:18 CEST 2014
 */

package gameshop.advance.ui.swing.customer;

import java.awt.*;
import javax.swing.border.*;
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

/**
 * @author Pippo
 */
public class BookCellRenderer extends JPanel implements ListCellRenderer<IRigaDiTransazioneRemote>{
    public BookCellRenderer() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        name = new JLabel();
        separator2 = new JSeparator();
        quantity = new JLabel();
        separator1 = new JSeparator();
        subTotal = new JLabel();
        hSeparator = new JSeparator();

        //======== this ========
        setLayout(new BorderLayout());

        //======== panel1 ========
        {
            panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
            panel1.setLayout(new FormLayout(
                "[100dlu,default]:grow, $lcgap, 10dlu, $lcgap, 50dlu, $lcgap, 10dlu, $lcgap, 50dlu",
                "fill:[30dlu,default]:grow"));

            //---- name ----
            name.setFont(new Font("Tahoma", Font.PLAIN, 14));
            panel1.add(name, CC.xy(1, 1));

            //---- separator2 ----
            separator2.setOrientation(SwingConstants.VERTICAL);
            panel1.add(separator2, CC.xy(3, 1, CC.CENTER, CC.DEFAULT));

            //---- quantity ----
            quantity.setHorizontalAlignment(SwingConstants.CENTER);
            panel1.add(quantity, CC.xy(5, 1));

            //---- separator1 ----
            separator1.setOrientation(SwingConstants.VERTICAL);
            panel1.add(separator1, CC.xy(7, 1, CC.CENTER, CC.DEFAULT));

            //---- subTotal ----
            subTotal.setHorizontalAlignment(SwingConstants.CENTER);
            panel1.add(subTotal, CC.xy(9, 1));
        }
        add(panel1, BorderLayout.CENTER);

        //---- hSeparator ----
        hSeparator.setAutoscrolls(true);
        hSeparator.setPreferredSize(new Dimension(0, 1));
        hSeparator.setMaximumSize(new Dimension(32767, 1));
        add(hSeparator, BorderLayout.SOUTH);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JLabel name;
    private JSeparator separator2;
    private JLabel quantity;
    private JSeparator separator1;
    private JLabel subTotal;
    private JSeparator hSeparator;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    @Override
    public Component getListCellRendererComponent(JList<? extends IRigaDiTransazioneRemote> list, IRigaDiTransazioneRemote value, int index, boolean isSelected, boolean cellHasFocus) {
        if(index == 0 && value == null)
        {
            this.name.setText("Nome prodotto");
            this.quantity.setText("Quantità");
            this.subTotal.setText("Totale");
        }
        else{
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
        }
        
        return this;
    }
}
