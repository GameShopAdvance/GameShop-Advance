/*
 * Created by JFormDesigner on Sun Mar 30 09:23:39 CEST 2014
 */

package gameshop.advance.ui.swing.customer;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.ReservationControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.interfaces.remote.IDescrizioneProdottoRemote;
import gameshop.advance.interfaces.remote.IIteratorWrapperRemote;
import gameshop.advance.interfaces.remote.IRigaDiTransazioneRemote;
import gameshop.advance.ui.interfaces.PopActionListener;
import gameshop.advance.utility.Money;
import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class ProductsPanel extends JPanel implements PopActionListener {
    private final DefaultListModel<IDescrizioneProdottoRemote> productsModel;
    private ProductPanel productDetail;
    private ChartPanel chart;
    
    private LinkedList<JPanel> panelStack;
    
    public ProductsPanel() {
        initComponents();
        this.panelStack = new LinkedList<>();
        this.productsModel = new DefaultListModel<>();
        this.productsList.setCellRenderer(new ProductCellRenderer());
        this.productsList.setModel(this.productsModel);
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
    
    public void addProduct(IDescrizioneProdottoRemote desc){
        this.productsModel.addElement(desc);
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
            this.chart.clearList();
            this.pushPanel(this.chart);
            IIteratorWrapperRemote<IRigaDiTransazioneRemote> iter = ReservationControllerSingleton.getInstance().getListaProdotti();
            while(iter.hasNext())
            {
                this.chart.addProduct(iter.next());
            }
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
            this.productDetail.setValues(this.productsModel.elementAt(index));
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

            //---- label1 ----
            label1.setText("Prenotazione Prodotti");
            label1.setLabelFor(productsList);

            //---- button1 ----
            button1.setText("Carrello");
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    chartActionPerformed(e);
                }
            });

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

            //---- label2 ----
            label2.setText("Totale");

            PanelBuilder mainPanelBuilder = new PanelBuilder(new FormLayout(
                "[15dlu,default], $lcgap, 73dlu, $lcgap, default:grow, $lcgap, [73dlu,default], $lcgap, 73dlu, $lcgap, [15dlu,default]",
                "2*([15dlu,default], $lgap), default:grow, $lgap, 15dlu, $lgap, [15dlu,default]"), mainPanel);

            mainPanelBuilder.add(label1,      CC.xywh(3, 3,       5,       1, CC.FILL, CC.FILL));
            mainPanelBuilder.add(button1,     CC.xy  (9, 3));
            mainPanelBuilder.add(scrollPane1, CC.xywh(3, 5,       7,       1, CC.FILL, CC.FILL));
            mainPanelBuilder.add(label2,      CC.xy  (7, 7, CC.FILL, CC.FILL));
            mainPanelBuilder.add(totale,      CC.xy  (9, 7, CC.FILL, CC.FILL));
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
}
