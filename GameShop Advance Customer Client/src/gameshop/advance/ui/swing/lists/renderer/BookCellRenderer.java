/*
 * Created by JFormDesigner on Tue Apr 01 14:37:18 CEST 2014
 */

package gameshop.advance.ui.swing.lists.renderer;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.interfaces.remote.sales.IRigaDiTransazioneRemote;
import gameshop.advance.technicalservices.LoggerSingleton;
import gameshop.advance.ui.swing.factory.UIFactory;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.rmi.RemoteException;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * Layout associato ai vari prodotti compresi nella prenotazione.
 * @author Lorenzo Di Giuseppe
 */
public class BookCellRenderer extends JPanel implements ListCellRenderer<IRigaDiTransazioneRemote>{
    
    public BookCellRenderer() {
        initComponents();
    }

    private void createUIComponents() {
        this.name = UIFactory.getInstance().getHeaderLabel();
        this.quantity = UIFactory.getInstance().getBodyLabel();
        this.subTotal = UIFactory.getInstance().getBodyLabel();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        createUIComponents();

        panel1 = new JPanel();
        hSeparator = new JSeparator();

        //======== this ========
        setName("this");
        setLayout(new BorderLayout());

        //======== panel1 ========
        {
            panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
            panel1.setName("panel1");

            //---- name ----
            name.setName("name");

            //---- quantity ----
            quantity.setHorizontalAlignment(SwingConstants.CENTER);
            quantity.setName("quantity");

            //---- subTotal ----
            subTotal.setHorizontalAlignment(SwingConstants.CENTER);
            subTotal.setName("subTotal");

            PanelBuilder panel1Builder = new PanelBuilder(new FormLayout(
                "[200dlu,default]:grow, $lcgap, [20dlu,default,40dlu]:grow, $lcgap, [25dlu,default,75dlu]:grow",
                "fill:[30dlu,default]:grow"), panel1);

            panel1Builder.add(name,     CC.xy(1, 1));
            panel1Builder.add(quantity, CC.xy(3, 1));
            panel1Builder.add(subTotal, CC.xy(5, 1));
        }
        add(panel1, BorderLayout.CENTER);

        //---- hSeparator ----
        hSeparator.setAutoscrolls(true);
        hSeparator.setPreferredSize(new Dimension(0, 1));
        hSeparator.setMaximumSize(new Dimension(32767, 1));
        hSeparator.setName("hSeparator");
        add(hSeparator, BorderLayout.SOUTH);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JLabel name;
    private JLabel quantity;
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
                this.name.setText(value.getDescrizione().getNomeProdotto());
            } catch (RemoteException ex) {
                LoggerSingleton.getInstance().log(ex);
                this.name.setText("Prodotto... (!!!)");
            }
            try {
                this.quantity.setText(""+value.getQuantity());
            } catch (RemoteException ex) {
                LoggerSingleton.getInstance().log(ex);
                this.quantity.setText("...");
            }
            try {
                this.subTotal.setText(value.getSubTotal().toString());
            } catch (RemoteException ex) {
                LoggerSingleton.getInstance().log(ex);
                this.subTotal.setText("!!!");
            }
        }
        
        return this;
    }
}
