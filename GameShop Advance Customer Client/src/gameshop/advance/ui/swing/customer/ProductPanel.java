/*
 * Created by JFormDesigner on Sun Mar 30 09:33:44 CEST 2014
 */

package gameshop.advance.ui.swing.customer;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.interfaces.remote.IDescrizioneProdottoRemote;
import java.rmi.RemoteException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import org.joda.time.DateTime;

/**
 * @author Lorenzo Di Giuseppe
 */
public class ProductPanel extends JPanel {
    private IDescrizioneProdottoRemote product;
    
    public ProductPanel(){
        initComponents();
    }
    
    public void setValues(IDescrizioneProdottoRemote desc) throws RemoteException{
        this.price.setText(desc.getPrezzo(DateTime.now()).toString());
        this.title.setText(desc.getDescrizione());
        this.product = desc;
        
//        this.actionButton.setText(TOOL_TIP_TEXT_KEY);
    }
    
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        title = new JLabel();
        price = new JLabel();
        imagePanel = new JPanel();
        scrollPane1 = new JScrollPane();
        description = new JTextPane();
        button1 = new JButton();
        actionButton = new JButton();

        //======== this ========
        setName("this");

        //---- title ----
        title.setText("text");
        title.setName("title");

        //---- price ----
        price.setText("text");
        price.setName("price");

        //======== imagePanel ========
        {
            imagePanel.setName("imagePanel");

            PanelBuilder imagePanelBuilder = new PanelBuilder(new FormLayout(
                "default:grow",
                "default:grow"), imagePanel);

        }

        //======== scrollPane1 ========
        {
            scrollPane1.setName("scrollPane1");

            //---- description ----
            description.setName("description");
            scrollPane1.setViewportView(description);
        }

        //---- button1 ----
        button1.setText("Indietro");
        button1.setName("button1");

        //---- actionButton ----
        actionButton.setText("Prenota");
        actionButton.setName("actionButton");

        PanelBuilder builder = new PanelBuilder(new FormLayout(
            "[15dlu,default], $lcgap, [73dlu,default], $lcgap, default:grow, $lcgap, [30dlu,default], $lcgap, [40dlu,default], $lcgap, [15dlu,default]",
            "[15dlu,default], $lgap, 15dlu, $lgap, fill:[73dlu,default], $lgap, fill:[41dlu,default]:grow, $lgap, default, $lgap, [15dlu,default]"), this);

        builder.add(title,        CC.xywh(3, 3,       5,       1, CC.DEFAULT, CC.FILL));
        builder.add(price,        CC.xy  (9, 3, CC.FILL, CC.FILL));
        builder.add(imagePanel,   CC.xy  (3, 5, CC.FILL, CC.FILL));
        builder.add(scrollPane1,  CC.xywh(5, 5,       5,       3, CC.FILL   , CC.FILL));
        builder.add(button1,      CC.xy  (3, 9));
        builder.add(actionButton, CC.xywh(7, 9,       3,       1));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel title;
    private JLabel price;
    private JPanel imagePanel;
    private JScrollPane scrollPane1;
    private JTextPane description;
    private JButton button1;
    private JButton actionButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
