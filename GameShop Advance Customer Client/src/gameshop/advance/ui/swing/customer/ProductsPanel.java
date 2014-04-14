/*
 * Created by JFormDesigner on Sun Mar 30 09:23:39 CEST 2014
 */

package gameshop.advance.ui.swing.customer;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.ProductsControllerSingleton;
import gameshop.advance.controller.ReservationControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.ui.interfaces.IListPanel;
import gameshop.advance.ui.interfaces.IPopActionListener;
import gameshop.advance.utility.Money;
import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.SwingConstants;

/**
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class ProductsPanel extends JPanel implements IPopActionListener, IListPanel {
    private ProductPanel productDetail;
    private ChartPanel chart;
    private LinkedList<JPanel> panelStack;
    
    public ProductsPanel() {
        initComponents();
        this.panelStack = new LinkedList<>();
        this.productDetail = new ProductPanel();
        this.productDetail.setListener(this);
        this.chart = new ChartPanel();
        this.chart.setListener(this);
        CardLayout layout = (CardLayout) this.getLayout();
        layout.addLayoutComponent(this.productDetail, this.productDetail.getName());
        layout.addLayoutComponent(this.chart, this.chart.getName());
        this.add(this.productDetail);
        this.add(this.chart);
        this.aggiornaTotale();
    }

    private void aggiornaTotale()
    {
        try {
            this.totale.setText(ReservationControllerSingleton.getInstance().getTotal().toString());
        }
        catch (NullPointerException | RemoteException | ConfigurationException ex) {
            this.totale.setText(new Money().toString());
        }
    }
    
    @Override
    public void pushPanel(JPanel panel){
        this.panelStack.push(panel);
        CardLayout layout = (CardLayout) this.getLayout();
        layout.show(this, panel.getName());
    }
    
    @Override
    public void popPanel(){
        CardLayout layout = (CardLayout) this.getLayout();
        this.panelStack.pop();
        if(this.panelStack.isEmpty())
            layout.first(this);
        else{
            JPanel panel = this.panelStack.getFirst();
            layout.show(this, panel.getName());
        }
        this.aggiornaTotale();
    }

    private void chartActionPerformed(ActionEvent e) {
        try {
            this.chart.setTotal(ReservationControllerSingleton.getInstance().getTotal());
            this.pushPanel(this.chart);
        }
        catch (NullPointerException ex) {
            Logger.getLogger(ProductsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (RemoteException ex) {
            Logger.getLogger(ProductsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ConfigurationException ex) {
            Logger.getLogger(ProductsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void productsListMouseClicked(MouseEvent e) {
        try {
            int index = this.productsList.getSelectedIndex();
            this.productDetail.setValues(ProductsControllerSingleton.getInstance().getProduct(index));
            this.pushPanel(this.productDetail);
        }
        catch (RemoteException ex) {
            Logger.getLogger(ProductsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        mainPanel = new JPanel();
        label1 = new JLabel();
        button1 = new JButton();
        scrollPane1 = new JScrollPane();
        productsList = new JList();
        label2 = new JLabel();
        totale = new JLabel();

        //======== this ========
        setLayout(new CardLayout());

        //======== mainPanel ========
        {
            mainPanel.setOpaque(false);
            mainPanel.setLayout(new FormLayout(
                "[15dlu,default], $lcgap, 73dlu, $lcgap, default:grow, $lcgap, [73dlu,default], $lcgap, [75dlu,default], $lcgap, [15dlu,default]",
                "[15dlu,default], $lgap, [35dlu,default], $lgap, default:grow, $lgap, 15dlu, $lgap, [15dlu,default]"));

            //---- label1 ----
            label1.setText("Prenotazione Prodotti");
            label1.setLabelFor(productsList);
            label1.setFont(new Font("Tahoma", Font.PLAIN, 16));
            mainPanel.add(label1, CC.xywh(3, 3, 5, 1, CC.FILL, CC.FILL));

            //---- button1 ----
            button1.setText("Carrello");
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    chartActionPerformed(e);
                }
            });
            mainPanel.add(button1, CC.xy(9, 3, CC.FILL, CC.FILL));

            //======== scrollPane1 ========
            {

                //---- productsList ----
                productsList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                productsList.setToolTipText("Fai click per maggiori dettagli");
                productsList.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        productsListMouseClicked(e);
                    }
                });
                scrollPane1.setViewportView(productsList);
            }
            mainPanel.add(scrollPane1, CC.xywh(3, 5, 7, 1, CC.FILL, CC.FILL));

            //---- label2 ----
            label2.setText("Totale");
            label2.setFont(new Font("Tahoma", Font.PLAIN, 14));
            label2.setHorizontalAlignment(SwingConstants.RIGHT);
            mainPanel.add(label2, CC.xy(7, 7, CC.FILL, CC.FILL));

            //---- totale ----
            totale.setFont(new Font("Tahoma", Font.BOLD, 14));
            mainPanel.add(totale, CC.xy(9, 7, CC.FILL, CC.FILL));
        }
        add(mainPanel, "card1");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel mainPanel;
    private JLabel label1;
    private JButton button1;
    private JScrollPane scrollPane1;
    private JList productsList;
    private JLabel label2;
    private JLabel totale;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        this.popPanel();
    }

    @Override
    public void setList(ListModel model, ListCellRenderer renderer)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
