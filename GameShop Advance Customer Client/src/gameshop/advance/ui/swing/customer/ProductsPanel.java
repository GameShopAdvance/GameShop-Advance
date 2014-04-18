/*
 * Created by JFormDesigner on Sun Mar 30 09:23:39 CEST 2014
 */

package gameshop.advance.ui.swing.customer;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.ProductsControllerSingleton;
import gameshop.advance.controller.ReservationControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.interfaces.IListPanel;
import gameshop.advance.interfaces.IPopActionListener;
import gameshop.advance.technicalservices.ExceptionHandlerSingleton;
import gameshop.advance.technicalservices.LoggerSingleton;
import gameshop.advance.ui.swing.UIWindowSingleton;
import gameshop.advance.ui.swing.factory.UIFactory;
import gameshop.advance.utility.Money;
import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.SwingConstants;

/**
 * Schermata che mostra la lista di tutti i prodotti presenti all'interno del negozio.
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class ProductsPanel extends JPanel implements IPopActionListener, IListPanel {
    
    private ProductPanel productDetail;
    private ChartPanel chart;
    private LinkedList<JPanel> panelStack;
    
    /**
     * @throws RemoteException
     */
    public ProductsPanel() throws RemoteException {
        initComponents();
        this.setLinkedPanels();
        ProductsControllerSingleton.getInstance().setDescriptions(this);
        this.aggiornaTotale();
    }

    private void setLinkedPanels(){
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
    }
    
    private void aggiornaTotale()
    {
        try {
            this.totale.setText(ReservationControllerSingleton.getInstance().getTotal().toString());
        }
        catch(NullPointerException ex){
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
            this.totale.setText(new Money().toString());
        }
        catch (RemoteException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
            this.totale.setText(new Money().toString());
        }
        catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
            this.totale.setText(new Money().toString());
        }
        
    }
    
    /**
     * @param panel
     */
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
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }
        catch (RemoteException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }
        catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }
    }

    private void productsListMouseClicked(MouseEvent e) {
        try {
            int index = this.productsList.getSelectedIndex();
            this.productDetail.setValues(ProductsControllerSingleton.getInstance().getProduct(index));
            this.pushPanel(this.productDetail);
        }
        catch (RemoteException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }
    }

    private void createUIComponents() {
        this.chartButton = UIFactory.getInstance().getSimpleButton();
        this.title = UIFactory.getInstance().getHeaderLabel();
        this.totaleLabel = UIFactory.getInstance().getBodyLabel();
        this.totale = UIFactory.getInstance().getBoldLabel();
    }
    
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        createUIComponents();

        mainPanel = new JPanel();
        scrollPane1 = new JScrollPane();
        productsList = new JList();

        //======== this ========
        setLayout(new CardLayout());

        //======== mainPanel ========
        {
            mainPanel.setOpaque(false);

            //---- title ----
            title.setText("Prenotazione Prodotti");
            title.setLabelFor(productsList);

            //---- chartButton ----
            chartButton.setText("Carrello");
            chartButton.addActionListener(new ActionListener() {
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

            //---- totaleLabel ----
            totaleLabel.setText("Totale");
            totaleLabel.setHorizontalAlignment(SwingConstants.RIGHT);

            PanelBuilder mainPanelBuilder = new PanelBuilder(new FormLayout(
                "[15dlu,default], $lcgap, 73dlu, $lcgap, default:grow, $lcgap, [73dlu,default], $lcgap, [75dlu,default], $lcgap, [15dlu,default]",
                "[15dlu,default], $lgap, [35dlu,default], $lgap, default:grow, $lgap, 15dlu, $lgap, [15dlu,default]"), mainPanel);

            mainPanelBuilder.add(title,       CC.xywh(3, 3,       5,       1, CC.FILL, CC.FILL));
            mainPanelBuilder.add(chartButton, CC.xy  (9, 3, CC.FILL, CC.FILL));
            mainPanelBuilder.add(scrollPane1, CC.xywh(3, 5,       7,       1, CC.FILL, CC.FILL));
            mainPanelBuilder.add(totaleLabel, CC.xy  (7, 7, CC.FILL, CC.FILL));
            mainPanelBuilder.add(totale,      CC.xy  (9, 7, CC.FILL, CC.FILL));
        }
        add(mainPanel, "card1");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel mainPanel;
    private JLabel title;
    private JButton chartButton;
    private JScrollPane scrollPane1;
    private JList productsList;
    private JLabel totaleLabel;
    private JLabel totale;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        this.popPanel();
    }

    @Override
    public void setList(ListModel model, ListCellRenderer renderer)
    {
        this.productsList.setModel(model);
        this.productsList.setCellRenderer(renderer);
    }
}
