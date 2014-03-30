/*
 * Created by JFormDesigner on Sun Mar 30 09:50:48 CEST 2014
 */

package gameshop.advance.ui.swing.customer;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.interfaces.remote.IDescrizioneProdottoRemote;
import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.ListCellRenderer;
import org.joda.time.DateTime;

/**
 * @author Lorenzo Di Giuseppe
 */
public class ProductCell extends JPanel implements ListCellRenderer<IDescrizioneProdottoRemote>{
    
    public ProductCell(){
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        imageBox = new JPanel();
        title = new JLabel();
        price = new JLabel();
        label2 = new JLabel();
        quantity = new JLabel();
        separator1 = new JSeparator();

        //======== this ========
        setName("this");

        //======== imageBox ========
        {
            imageBox.setName("imageBox");

            PanelBuilder imageBoxBuilder = new PanelBuilder(new FormLayout(
                "default:grow",
                "fill:41dlu"), imageBox);

        }

        //---- title ----
        title.setText("text");
        title.setName("title");

        //---- price ----
        price.setText("text");
        price.setName("price");

        //---- label2 ----
        label2.setText("qty #");
        label2.setName("label2");

        //---- quantity ----
        quantity.setText("text");
        quantity.setName("quantity");

        //---- separator1 ----
        separator1.setName("separator1");

        PanelBuilder builder = new PanelBuilder(new FormLayout(
            "[15dlu,default], $lcgap, [40dlu,default], $lcgap, 62dlu:grow, 2*($lcgap, 25dlu), $lcgap, [15dlu,default]",
            "[10dlu,default], $lgap, [15dlu,default], $lgap, [22dlu,default], $lgap, 10dlu"), this);

        builder.add(imageBox,   CC.xywh(3, 3,       1,          3));
        builder.add(title,      CC.xy  (5, 3, CC.FILL,    CC.FILL));
        builder.add(price,      CC.xywh(7, 3,       3,          1, CC.FILL, CC.FILL   ));
        builder.add(label2,     CC.xy  (7, 5, CC.FILL, CC.DEFAULT));
        builder.add(quantity,   CC.xy  (9, 5, CC.FILL, CC.DEFAULT));
        builder.add(separator1, CC.xywh(1, 7,      11,          1, CC.FILL, CC.DEFAULT));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel imageBox;
    private JLabel title;
    private JLabel price;
    private JLabel label2;
    private JLabel quantity;
    private JSeparator separator1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    @Override
    public Component getListCellRendererComponent(JList<? extends IDescrizioneProdottoRemote> list, IDescrizioneProdottoRemote value, int index, boolean isSelected, boolean cellHasFocus) {
        try {
            this.title.setText(value.getDescrizione());
        }
        catch (Exception ex) {
            Logger.getLogger(ProductCell.class.getName()).log(Level.SEVERE, null, ex);
            this.title.setText("???");
        }
        try {
            this.price.setText(value.getPrezzo(DateTime.now()).toString());
        }
        catch (Exception ex) {
            Logger.getLogger(ProductCell.class.getName()).log(Level.SEVERE, null, ex);
            this.price.setText("???");
        }
        try{
            this.quantity.setText(""+value.getQuantitaDisponibile());
        }
        catch(Exception ex){
            Logger.getLogger(ProductCell.class.getName()).log(Level.SEVERE, null, ex);
            this.quantity.setText("???");
        }
        
        return this;
    }
}
