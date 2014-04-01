/*
 * Created by JFormDesigner on Tue Apr 01 14:37:18 CEST 2014
 */

package gameshop.advance.ui.swing.customer;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.interfaces.remote.IRigaDiVenditaRemote;
import gameshop.advance.utility.Money;
import java.awt.Color;
import java.awt.Component;
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
public class BookCellRenderer extends JPanel implements ListCellRenderer<IRigaDiVenditaRemote>{
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
        setBorder(new LineBorder(Color.black));
        setName("this");

        //---- name ----
        name.setName("name");

        //---- quantity ----
        quantity.setHorizontalAlignment(SwingConstants.CENTER);
        quantity.setName("quantity");

        //---- separator1 ----
        separator1.setOrientation(SwingConstants.VERTICAL);
        separator1.setName("separator1");

        //---- subTotal ----
        subTotal.setHorizontalAlignment(SwingConstants.CENTER);
        subTotal.setName("subTotal");

        PanelBuilder builder = new PanelBuilder(new FormLayout(
            "[100dlu,default]:grow, $lcgap, 50dlu, $lcgap, 10dlu, $lcgap, 50dlu",
            "fill:default:grow"), this);

        builder.add(name,       CC.xy(1, 1));
        builder.add(quantity,   CC.xy(3, 1));
        builder.add(separator1, CC.xy(5, 1, CC.CENTER, CC.DEFAULT));
        builder.add(subTotal,   CC.xy(7, 1));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel name;
    private JLabel quantity;
    private JSeparator separator1;
    private JLabel subTotal;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    @Override
    public Component getListCellRendererComponent(JList<? extends IRigaDiVenditaRemote> list, IRigaDiVenditaRemote value, int index, boolean isSelected, boolean cellHasFocus) {
        this.name.setText("Prodotto...manca observer delle righe di vendita");
        this.quantity.setText("???");
        this.subTotal.setText(new Money().toString());
        
        return this;
    }
}
