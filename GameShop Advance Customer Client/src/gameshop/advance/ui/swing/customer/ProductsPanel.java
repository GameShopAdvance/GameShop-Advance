/*
 * Created by JFormDesigner on Sun Mar 30 09:23:39 CEST 2014
 */

package gameshop.advance.ui.swing.customer;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.interfaces.remote.IDescrizioneProdottoRemote;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
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
    
    public ProductsPanel() {
        initComponents();
        this.productsModel = new DefaultListModel<>();
        this.productsList.setCellRenderer(new ProductCell());
        this.productsList.setModel(this.productsModel);
    }
    
    public void addProduct(IDescrizioneProdottoRemote desc){
        this.productsModel.addElement(desc);
    }

    private void productsListValueChanged(ListSelectionEvent e) {
        int index = this.productsList.getSelectedIndex();
        
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        layeredPane1 = new JLayeredPane();
        mainPanel = new JPanel();
        label1 = new JLabel();
        button1 = new JButton();
        scrollPane1 = new JScrollPane();
        productsList = new JList();
        button2 = new JButton();
        Totale = new JLabel();

        //======== this ========
        setName("this");

        //======== layeredPane1 ========
        {
            layeredPane1.setName("layeredPane1");

            //======== mainPanel ========
            {
                mainPanel.setName("mainPanel");

                //---- label1 ----
                label1.setText("Catalogo Prodotti");
                label1.setLabelFor(productsList);
                label1.setName("label1");

                //---- button1 ----
                button1.setText("Riepilogo Prenotazione");
                button1.setName("button1");

                //======== scrollPane1 ========
                {
                    scrollPane1.setName("scrollPane1");

                    //---- productsList ----
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

                //---- Totale ----
                Totale.setName("Totale");

                PanelBuilder mainPanelBuilder = new PanelBuilder(new FormLayout(
                    "[15dlu,default], $lcgap, 73dlu, $lcgap, default:grow, $lcgap, 73dlu, $lcgap, [15dlu,default]",
                    "2*([15dlu,default], $lgap), default:grow, $lgap, 15dlu, $lgap, [15dlu,default]"), mainPanel);

                mainPanelBuilder.add(label1,      CC.xywh(3, 3, 3, 1, CC.FILL, CC.FILL));
                mainPanelBuilder.add(button1,     CC.xy  (7, 3));
                mainPanelBuilder.add(scrollPane1, CC.xywh(3, 5, 5, 1, CC.FILL, CC.FILL));
                mainPanelBuilder.add(button2,     CC.xy  (3, 7));
                mainPanelBuilder.add(Totale,      CC.xy  (7, 7));
            }
            layeredPane1.add(mainPanel, JLayeredPane.DEFAULT_LAYER);
            mainPanel.setBounds(0, 0, 540, 395);
        }

        PanelBuilder builder = new PanelBuilder(new FormLayout(
            "15dlu:grow, $lcgap, [480px,default]",
            "2*([15dlu,default]:grow, $lgap), [180dlu,default]:grow, $lgap, fill:[15dlu,default]:grow"), this);

        builder.add(layeredPane1, CC.xywh(1, 1, 3, 7, CC.FILL, CC.FILL));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLayeredPane layeredPane1;
    private JPanel mainPanel;
    private JLabel label1;
    private JButton button1;
    private JScrollPane scrollPane1;
    private JList productsList;
    private JButton button2;
    private JLabel Totale;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
