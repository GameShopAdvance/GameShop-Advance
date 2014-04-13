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
import javax.swing.SwingConstants;

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
        panel1 = new JPanel();
        description = new JLabel();
        separator2 = new JSeparator();
        qty = new JLabel();
        subTotal = new JLabel();
        separator1 = new JSeparator();

        //======== this ========
        setLayout(new FormLayout(
            "[228dlu,default]",
            "22dlu, $lgap, 1dlu"));

        //======== panel1 ========
        {
            panel1.setLayout(new FormLayout(
                "70dlu, $lcgap, 10dlu, $lcgap, 21dlu, $lcgap, default, $lcgap, 102dlu",
                "27dlu"));
            panel1.add(description, CC.xy(1, 1, CC.FILL, CC.FILL));

            //---- separator2 ----
            separator2.setOrientation(SwingConstants.VERTICAL);
            panel1.add(separator2, CC.xywh(3, 1, 2, 1, CC.DEFAULT, CC.FILL));
            panel1.add(qty, CC.xy(5, 1, CC.FILL, CC.DEFAULT));
            panel1.add(subTotal, CC.xy(9, 1, CC.FILL, CC.FILL));
        }
        add(panel1, CC.xy(1, 1));
        add(separator1, CC.xy(1, 3));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JLabel description;
    private JSeparator separator2;
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
