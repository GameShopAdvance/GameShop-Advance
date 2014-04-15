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
import gameshop.advance.exceptions.ProdottoNotFoundException;
import gameshop.advance.interfaces.remote.IDescrizioneProdottoRemote;
import gameshop.advance.ui.interfaces.IPopActionListener;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import org.joda.time.DateTime;

/**
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
    
    public void setListener(IPopActionListener listener){
        this.listener = listener;
    }
    
    public void setValues(IDescrizioneProdottoRemote desc) throws RemoteException{
        this.price.setText(desc.getPrezzo(DateTime.now()).toString());
        this.title.setText(desc.getDescrizione());
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
            Logger.getLogger(ProductPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ProdottoNotFoundException ex) {
            Logger.getLogger(ProductPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NullPointerException ex) {
            Logger.getLogger(ProductPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ConfigurationException ex) {
            Logger.getLogger(ProductPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        button1 = new JButton();
        title = new JLabel();
        price = new JLabel();
        imagePanel = new JPanel();
        label1 = new JLabel();
        panel1 = new JPanel();
        label2 = new JLabel();
        panelSwitch = new JPanel();
        bookPanel = new JPanel();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        requiredQuantity = new JTextField();
        bookButton = new JButton();
        buyPanel = new JPanel();
        label3 = new JLabel();
        label4 = new JLabel();
        quantity = new JLabel();
        bookCompleted = new JPanel();
        label8 = new JLabel();
        label9 = new JLabel();
        label10 = new JLabel();

        //======== this ========
        setName("this");

        //---- button1 ----
        button1.setText("Indietro");
        button1.setFont(new Font("Dialog", Font.PLAIN, 14));
        button1.setName("button1");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backActionPerformed(e);
            }
        });

        //---- title ----
        title.setText("text");
        title.setName("title");

        //---- price ----
        price.setText("text");
        price.setName("price");

        //======== imagePanel ========
        {
            imagePanel.setBorder(LineBorder.createBlackLineBorder());
            imagePanel.setName("imagePanel");

            //---- label1 ----
            label1.setText("Image Here");
            label1.setName("label1");

            PanelBuilder imagePanelBuilder = new PanelBuilder(new FormLayout(
                "default:grow",
                "default:grow"), imagePanel);

            imagePanelBuilder.add(label1, CC.xy(1, 1, CC.CENTER, CC.CENTER));
        }

        //======== panel1 ========
        {
            panel1.setName("panel1");

            //---- label2 ----
            label2.setText("Description Here");
            label2.setName("label2");

            PanelBuilder panel1Builder = new PanelBuilder(new FormLayout(
                "default:grow",
                "default:grow"), panel1);

            panel1Builder.add(label2, CC.xy(1, 1, CC.CENTER, CC.DEFAULT));
        }

        //======== panelSwitch ========
        {
            panelSwitch.setName("panelSwitch");
            panelSwitch.setLayout(new CardLayout());

            //======== bookPanel ========
            {
                bookPanel.setName("bookPanel");

                //---- label5 ----
                label5.setText("Siamo spiacenti, ma il prodotto non \u00e8 al momento disponibile");
                label5.setHorizontalAlignment(SwingConstants.CENTER);
                label5.setName("label5");

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
                    "54dlu:grow, $lcgap, [49dlu,default], $lcgap, [0dlu,default]:grow, $lcgap, 73dlu",
                    "default:grow, $lgap, 15dlu, $lgap, [15dlu,default], $lgap, fill:6dlu:grow, $lgap, [20dlu,default]"), bookPanel);

                bookPanelBuilder.add(label5,           CC.xywh(1, 3,          7,       1, CC.FILL  , CC.FILL));
                bookPanelBuilder.add(label6,           CC.xywh(1, 5,          7,       1, CC.CENTER, CC.FILL));
                bookPanelBuilder.add(label7,           CC.xy  (1, 9,    CC.FILL, CC.FILL));
                bookPanelBuilder.add(requiredQuantity, CC.xy  (3, 9, CC.DEFAULT, CC.FILL));
                bookPanelBuilder.add(bookButton,       CC.xy  (7, 9,    CC.FILL, CC.FILL));
            }
            panelSwitch.add(bookPanel, "bookPanel");

            //======== buyPanel ========
            {
                buyPanel.setName("buyPanel");
                buyPanel.setLayout(new GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), 0, 0));

                //---- label3 ----
                label3.setText("Il prodotto \u00e8 disponibile presso gli sportelli.");
                label3.setHorizontalAlignment(SwingConstants.CENTER);
                label3.setFont(new Font("Dialog", Font.PLAIN, 12));
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

                //---- label8 ----
                label8.setText("L'oggetto \u00e8 stato aggiunto alla tua prenotazione.");
                label8.setHorizontalAlignment(SwingConstants.CENTER);
                label8.setFont(new Font("Dialog", Font.BOLD, 11));
                label8.setName("label8");

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

                bookCompletedBuilder.add(label8,  CC.xy(3, 3, CC.FILL, CC.FILL));
                bookCompletedBuilder.add(label9,  CC.xy(3, 5, CC.FILL, CC.FILL));
                bookCompletedBuilder.add(label10, CC.xy(3, 7, CC.FILL, CC.FILL));
            }
            panelSwitch.add(bookCompleted, "bookCompleted");
        }

        PanelBuilder builder = new PanelBuilder(new FormLayout(
            "[15dlu,default], $lcgap, [75dlu,default], $lcgap, default:grow, $lcgap, [30dlu,default], $lcgap, [40dlu,default], $lcgap, [15dlu,default]",
            "[15dlu,default], $lgap, [35dlu,default], $lgap, 15dlu, $lgap, fill:[73dlu,default], $lgap, fill:[27dlu,default]:grow, [15dlu,default]"), this);

        builder.add(button1,     CC.xy  (3, 3, CC.FILL, CC.FILL));
        builder.add(title,       CC.xywh(3, 5,       5,       1, CC.DEFAULT, CC.FILL));
        builder.add(price,       CC.xy  (9, 5, CC.FILL, CC.FILL));
        builder.add(imagePanel,  CC.xy  (3, 7, CC.FILL, CC.FILL));
        builder.add(panel1,      CC.xywh(5, 7,       5,       1));
        builder.add(panelSwitch, CC.xywh(3, 9,       7,       1));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JButton button1;
    private JLabel title;
    private JLabel price;
    private JPanel imagePanel;
    private JLabel label1;
    private JPanel panel1;
    private JLabel label2;
    private JPanel panelSwitch;
    private JPanel bookPanel;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JTextField requiredQuantity;
    private JButton bookButton;
    private JPanel buyPanel;
    private JLabel label3;
    private JLabel label4;
    private JLabel quantity;
    private JPanel bookCompleted;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
