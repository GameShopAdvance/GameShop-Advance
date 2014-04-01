/*
 * Created by JFormDesigner on Sun Mar 30 09:33:44 CEST 2014
 */

package gameshop.advance.ui.swing.customer;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.interfaces.remote.IDescrizioneProdottoRemote;
import gameshop.advance.ui.interfaces.PopActionListener;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
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
    private PopActionListener listener;
    private final String name = "Product Detail";
    
    private IDescrizioneProdottoRemote product;
    
    public ProductPanel(){
        initComponents();
    }
    
    @Override
    public String getName()
    {
        return this.name;
    }
    
    public void setListener(PopActionListener listener){
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
            layout.last(this.panelSwitch);
        }
        else
        {
            layout.first(this.panelSwitch);
        }
        this.product = desc;
    }

    private void backActionPerformed(ActionEvent e) {
        this.listener.popPanel();
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
        button2 = new JButton();
        buyPanel = new JPanel();
        label3 = new JLabel();
        label4 = new JLabel();
        quantity = new JLabel();

        //======== this ========
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

                //---- button2 ----
                button2.setText("Prenota");
                button2.setName("button2");

                PanelBuilder bookPanelBuilder = new PanelBuilder(new FormLayout(
                    "54dlu:grow, $lcgap, [49dlu,default], $lcgap, [0dlu,default]:grow, $lcgap, 73dlu",
                    "default:grow, $lgap, 15dlu, $lgap, [15dlu,default], $lgap, fill:6dlu:grow, $lgap, [20dlu,default]"), bookPanel);

                bookPanelBuilder.add(label5,           CC.xywh(1, 3,          7,       1, CC.FILL  , CC.FILL));
                bookPanelBuilder.add(label6,           CC.xywh(1, 5,          7,       1, CC.CENTER, CC.FILL));
                bookPanelBuilder.add(label7,           CC.xy  (1, 9,    CC.FILL, CC.FILL));
                bookPanelBuilder.add(requiredQuantity, CC.xy  (3, 9, CC.DEFAULT, CC.FILL));
                bookPanelBuilder.add(button2,          CC.xy  (7, 9));
            }
            panelSwitch.add(bookPanel, "prenotaPanel");

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
        }

        PanelBuilder builder = new PanelBuilder(new FormLayout(
            "[15dlu,default], $lcgap, [73dlu,default], $lcgap, default:grow, $lcgap, [30dlu,default], $lcgap, [40dlu,default], $lcgap, [15dlu,default]",
            "[15dlu,default], $lgap, default, $lgap, 15dlu, $lgap, fill:[73dlu,default], $lgap, fill:[27dlu,default]:grow, [15dlu,default]"), this);

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
    private JButton button2;
    private JPanel buyPanel;
    private JLabel label3;
    private JLabel label4;
    private JLabel quantity;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
