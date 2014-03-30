/*
 * Created by JFormDesigner on Sun Mar 30 09:23:39 CEST 2014
 */

package gameshop.advance.ui.swing.customer;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.interfaces.remote.IDescrizioneProdottoRemote;
import gameshop.advance.utility.Money;
import java.awt.CardLayout;
import java.awt.Cursor;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * @author Lorenzo Di Giuseppe
 */
public class ProductsPanel extends JPanel {
    private DefaultListModel<IDescrizioneProdottoRemote> productsModel;
    private ProductPanel productDetail;
    private final String productPanelTitle = "Product Detail";
    
    public ProductsPanel() {
        initComponents();
        this.productsModel = new DefaultListModel<>();
        this.productsList.setCellRenderer(new ProductCell());
        this.productsList.setModel(this.productsModel);
        this.productDetail = new ProductPanel();
        this.Totale.setText(new Money().toString());
        this.add(this.productDetail, this.productPanelTitle);
    }
    
    public void addProduct(IDescrizioneProdottoRemote desc){
        this.productsModel.addElement(desc);
    }

    private void productsListValueChanged(ListSelectionEvent e) {
        try {
            int index = this.productsList.getSelectedIndex();
            System.err.println("Hai selezionato l'elemento "+index);
            CardLayout cards = (CardLayout) this.getLayout();
            this.productDetail.setValues(this.productsModel.elementAt(index));
            cards.show(this, this.productPanelTitle);
        }
        catch (RemoteException ex) {
            Logger.getLogger(ProductsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        mainPanel = new JPanel();
        label1 = new JLabel();
        scrollPane1 = new JScrollPane();
        productsList = new JList();
        button2 = new JButton();
        button1 = new JButton();
        Totale = new JLabel();

        //======== this ========
        setName("this");
        setLayout(new CardLayout());

        //======== mainPanel ========
        {
            mainPanel.setOpaque(false);
            mainPanel.setName("mainPanel");

            //---- label1 ----
            label1.setText("Catalogo Prodotti");
            label1.setLabelFor(productsList);
            label1.setName("label1");

            //======== scrollPane1 ========
            {
                scrollPane1.setName("scrollPane1");

                //---- productsList ----
                productsList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                productsList.setName("productsList");
                productsList.addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        productsListValueChanged(e);
                    }
                });
                scrollPane1.setViewportView(productsList);
            }

            //---- button2 ----
            button2.setText("Cancella");
            button2.setName("button2");

            //---- button1 ----
            button1.setText("Carrello");
            button1.setName("button1");

            //---- Totale ----
            Totale.setName("Totale");

            PanelBuilder mainPanelBuilder = new PanelBuilder(new FormLayout(
                "[15dlu,default], $lcgap, 73dlu, $lcgap, default:grow, $lcgap, [73dlu,default], $lcgap, 73dlu, $lcgap, [15dlu,default]",
                "2*([15dlu,default], $lgap), default:grow, $lgap, 15dlu, $lgap, [15dlu,default]"), mainPanel);

            mainPanelBuilder.add(label1,      CC.xywh(3, 3,       7,       1, CC.FILL, CC.FILL));
            mainPanelBuilder.add(scrollPane1, CC.xywh(3, 5,       7,       1, CC.FILL, CC.FILL));
            mainPanelBuilder.add(button2,     CC.xy  (3, 7));
            mainPanelBuilder.add(button1,     CC.xy  (7, 7));
            mainPanelBuilder.add(Totale,      CC.xy  (9, 7, CC.FILL, CC.FILL));
        }
        add(mainPanel, "mainCard");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel mainPanel;
    private JLabel label1;
    private JScrollPane scrollPane1;
    private JList productsList;
    private JButton button2;
    private JButton button1;
    private JLabel Totale;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
