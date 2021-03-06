/*
 * Created by JFormDesigner on Sun Mar 30 09:33:44 CEST 2014
 */

package gameshop.advance.ui.swing.customer;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.ReservationControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.exceptions.products.ProdottoNotFoundException;
import gameshop.advance.interfaces.IPopActionListener;
import gameshop.advance.interfaces.remote.IDescrizioneProdottoRemote;
import gameshop.advance.technicalservices.ExceptionHandlerSingleton;
import gameshop.advance.technicalservices.LoggerSingleton;
import gameshop.advance.ui.swing.UIWindowSingleton;
import gameshop.advance.ui.swing.factory.UIFactory;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import org.joda.time.DateTime;

/**
 * Schermata di dettaglio di un prodotto.
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class ProductPanel extends JPanel {
    
    private IPopActionListener listener;
    private final String name = "Product Detail";
    
    private IDescrizioneProdottoRemote product;
    
    public ProductPanel(){
        initComponents();
        CardLayout layout = (CardLayout) this.panelSwitch.getLayout();
    }
    
    @Override
    public String getName()
    {
        return this.name;
    }
    
    /**
     * @param listener
     */
    public void setListener(IPopActionListener listener){
        this.listener = listener;
    }
    
    /**
     * @param desc
     * @throws RemoteException
     */
    public void setValues(IDescrizioneProdottoRemote desc) throws RemoteException{
        this.price.setText(desc.getPrezzo(DateTime.now()).toString());
        this.title.setText(desc.getNomeProdotto());
        this.description.setText(desc.getDescrizione());
        Integer quantity = desc.getQuantitaDisponibile();
        this.quantity.setText(quantity.toString());
        CardLayout layout = (CardLayout) this.panelSwitch.getLayout();
        if(quantity.intValue() != 0)
        {
            layout.show(this.panelSwitch, this.buyPanel.getName());
        }
        else
        {
            layout.show(this.panelSwitch, this.bookPanel.getName());
        }
        try{
            this.image.setIcon(desc.getImmagine().getImage());
        }
        catch(RemoteException ex)
        {
            this.image.setIcon(null);
        }
        this.product = desc;
    }

    private void backActionPerformed(ActionEvent e) {
        this.listener.popPanel();
    }

    private void bookActionPerformed(ActionEvent e) {
        try {
            Integer qty = Integer.parseInt(this.requiredQuantity.getText());
            ReservationControllerSingleton.getInstance().inserisciProdotto(this.product, qty);
            
            CardLayout layout = (CardLayout) this.panelSwitch.getLayout();
            layout.show(this.panelSwitch, this.bookCompleted.getName());
        }
        catch (RemoteException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }
        catch (ProdottoNotFoundException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }
        catch (NullPointerException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }
        catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }
    }

    private void createUIComponents() {
        this.bookButton = UIFactory.getInstance().getConfirmButton();
        this.title = UIFactory.getInstance().getHeaderLabel();
        this.price = UIFactory.getInstance().getBoldLabel();
        this.requiredQuantity = UIFactory.getInstance().getTextField();
        this.notAvailableLabel = UIFactory.getInstance().getBoldLabel();
        this.bookedLabel = UIFactory.getInstance().getBoldLabel();
        this.button1 = UIFactory.getInstance().getSimpleButton();
        this.quantity = UIFactory.getInstance().getBodyLabel();
        this.label10 = UIFactory.getInstance().getBodyLabel();
        this.description = UIFactory.getInstance().getLongTextArea();
        this.label3 = UIFactory.getInstance().getBodyLabel();
        this.label4 = UIFactory.getInstance().getBodyLabel();
        this.label6 = UIFactory.getInstance().getBodyLabel();
        this.label7 = UIFactory.getInstance().getBodyLabel();
        this.label9 = UIFactory.getInstance().getBodyLabel();
    }
    
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        createUIComponents();

        imagePanel = new JPanel();
        image = new JLabel();
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        description = new JTextArea();
        panelSwitch = new JPanel();
        bookPanel = new JPanel();
        buyPanel = new JPanel();
        bookCompleted = new JPanel();

        //======== this ========
        setMinimumSize(new Dimension(720, 480));
        setName("this");

        //---- button1 ----
        button1.setText("Indietro");
        button1.setName("button1");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backActionPerformed(e);
            }
        });

        //======== imagePanel ========
        {
            imagePanel.setBorder(LineBorder.createBlackLineBorder());
            imagePanel.setName("imagePanel");
            imagePanel.setLayout(new BorderLayout());

            //---- image ----
            image.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, Color.lightGray, Color.white));
            image.setName("image");
            imagePanel.add(image, BorderLayout.CENTER);
        }

        //---- title ----
        title.setText("text");
        title.setName("title");

        //---- price ----
        price.setText("text");
        price.setName("price");

        //======== panel1 ========
        {
            panel1.setBorder(new TitledBorder("Descrizione"));
            panel1.setName("panel1");

            //======== scrollPane1 ========
            {
                scrollPane1.setBorder(null);
                scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane1.setName("scrollPane1");

                //---- description ----
                description.setBackground(new Color(238, 238, 238));
                description.setLineWrap(true);
                description.setName("description");
                scrollPane1.setViewportView(description);
            }

            PanelBuilder panel1Builder = new PanelBuilder(new FormLayout(
                "default:grow",
                "default:grow"), panel1);

            panel1Builder.add(scrollPane1, CC.xy(1, 1, CC.FILL, CC.FILL));
        }

        //======== panelSwitch ========
        {
            panelSwitch.setName("panelSwitch");
            panelSwitch.setLayout(new CardLayout());

            //======== bookPanel ========
            {
                bookPanel.setName("bookPanel");

                //---- notAvailableLabel ----
                notAvailableLabel.setText("Siamo spiacenti, ma il prodotto non \u00e8 al momento disponibile");
                notAvailableLabel.setHorizontalAlignment(SwingConstants.CENTER);
                notAvailableLabel.setName("notAvailableLabel");

                //---- label6 ----
                label6.setText("Se lo desidera pu\u00f2 prenotarlo.");
                label6.setName("label6");

                //---- label7 ----
                label7.setText("Quantit\u00e0");
                label7.setHorizontalAlignment(SwingConstants.CENTER);
                label7.setName("label7");

                //---- requiredQuantity ----
                requiredQuantity.setText("1");
                requiredQuantity.setHorizontalAlignment(SwingConstants.LEFT);
                requiredQuantity.setName("requiredQuantity");

                //---- bookButton ----
                bookButton.setText("Prenota");
                bookButton.setName("bookButton");
                bookButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        bookActionPerformed(e);
                    }
                });

                PanelBuilder bookPanelBuilder = new PanelBuilder(new FormLayout(
                    "54dlu:grow, $lcgap, [49dlu,default], $lcgap, [0dlu,default]:grow, $lcgap, [75dlu,default]",
                    "default:grow, $lgap, 15dlu, $lgap, [15dlu,default], $lgap, fill:6dlu:grow, $lgap, [35dlu,default]"), bookPanel);

                bookPanelBuilder.add(notAvailableLabel, CC.xywh(1, 3,       7,       1, CC.FILL  , CC.FILL));
                bookPanelBuilder.add(label6,            CC.xywh(1, 5,       7,       1, CC.CENTER, CC.FILL));
                bookPanelBuilder.add(label7,            CC.xy  (1, 9, CC.FILL, CC.FILL));
                bookPanelBuilder.add(requiredQuantity,  CC.xy  (3, 9, CC.FILL, CC.FILL));
                bookPanelBuilder.add(bookButton,        CC.xy  (7, 9, CC.FILL, CC.FILL));
            }
            panelSwitch.add(bookPanel, "bookPanel");

            //======== buyPanel ========
            {
                buyPanel.setName("buyPanel");
                buyPanel.setLayout(new GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), 0, 0));

                //---- label3 ----
                label3.setText("Il prodotto \u00e8 disponibile presso gli sportelli.");
                label3.setHorizontalAlignment(SwingConstants.CENTER);
                label3.setName("label3");
                buyPanel.add(label3, new GridConstraints(1, 0, 1, 3,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));

                //---- label4 ----
                label4.setText("Quantit\u00e0 disponibile");
                label4.setHorizontalAlignment(SwingConstants.CENTER);
                label4.setName("label4");
                buyPanel.add(label4, new GridConstraints(2, 0, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));

                //---- quantity ----
                quantity.setName("quantity");
                buyPanel.add(quantity, new GridConstraints(2, 2, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));
            }
            panelSwitch.add(buyPanel, "buyPanel");

            //======== bookCompleted ========
            {
                bookCompleted.setName("bookCompleted");

                //---- bookedLabel ----
                bookedLabel.setText("L'oggetto \u00e8 stato aggiunto alla tua prenotazione.");
                bookedLabel.setHorizontalAlignment(SwingConstants.CENTER);
                bookedLabel.setName("bookedLabel");

                //---- label9 ----
                label9.setText("Ricorda: puoi rivedere e modificare i dettagli della tua ");
                label9.setHorizontalAlignment(SwingConstants.CENTER);
                label9.setName("label9");

                //---- label10 ----
                label10.setText("prenotazione facendo click su carrello.");
                label10.setHorizontalAlignment(SwingConstants.CENTER);
                label10.setName("label10");

                PanelBuilder bookCompletedBuilder = new PanelBuilder(new FormLayout(
                    "[15dlu,default]:grow, $lcgap, default:grow, $lcgap, [15dlu,default]:grow",
                    "[15dlu,default]:grow, $lgap, 17dlu, 5dlu, 2*(12dlu, $lgap), [15dlu,default]:grow"), bookCompleted);

                bookCompletedBuilder.add(bookedLabel, CC.xy(3, 3, CC.FILL, CC.FILL));
                bookCompletedBuilder.add(label9,      CC.xy(3, 5, CC.FILL, CC.FILL));
                bookCompletedBuilder.add(label10,     CC.xy(3, 7, CC.FILL, CC.FILL));
            }
            panelSwitch.add(bookCompleted, "bookCompleted");
        }

        PanelBuilder builder = new PanelBuilder(new FormLayout(
            "$rgap, [75dlu,default], $lcgap, [50dlu,default], $ugap, default:grow, $lcgap, [30dlu,default], $lcgap, [40dlu,default], $rgap",
            "$rgap, [35dlu,default], $ugap, [35dlu,default,100px], $lgap, fill:[75dlu,default,300px], $lgap, fill:[27dlu,default,250px]:grow, $rgap"), this);

        builder.add(button1,     CC.xy  ( 2, 2, CC.FILL, CC.FILL));
        builder.add(imagePanel,  CC.xywh( 2, 4,       3,       5, CC.FILL   , CC.FILL));
        builder.add(title,       CC.xywh( 6, 4,       3,       1, CC.DEFAULT, CC.FILL));
        builder.add(price,       CC.xy  (10, 4, CC.FILL, CC.FILL));
        builder.add(panel1,      CC.xywh( 6, 6,       5,       1, CC.DEFAULT, CC.FILL));
        builder.add(panelSwitch, CC.xywh( 6, 8,       5,       1));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JButton button1;
    private JPanel imagePanel;
    private JLabel image;
    private JLabel title;
    private JLabel price;
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTextArea description;
    private JPanel panelSwitch;
    private JPanel bookPanel;
    private JLabel notAvailableLabel;
    private JLabel label6;
    private JLabel label7;
    private JTextField requiredQuantity;
    private JButton bookButton;
    private JPanel buyPanel;
    private JLabel label3;
    private JLabel label4;
    private JLabel quantity;
    private JPanel bookCompleted;
    private JLabel bookedLabel;
    private JLabel label9;
    private JLabel label10;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
