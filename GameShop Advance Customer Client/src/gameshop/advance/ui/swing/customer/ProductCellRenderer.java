/*
 * Created by JFormDesigner on Sun Mar 30 09:50:48 CEST 2014
 */

package gameshop.advance.ui.swing.customer;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.interfaces.remote.IDescrizioneProdottoRemote;
import gameshop.advance.ui.swing.UIFactory;
import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import org.joda.time.DateTime;

/**
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class ProductCellRenderer extends JPanel implements ListCellRenderer<IDescrizioneProdottoRemote>{
    
    public ProductCellRenderer(){
        initComponents();
    }

    private void createUIComponents() {
        this.label2 = UIFactory.getInstance().getBodyLabel();
        this.title = UIFactory.getInstance().getHeaderLabel();
        this.price = UIFactory.getInstance().getBodyLabel();
        this.quantity = UIFactory.getInstance().getBodyLabel();
        this.label1 = new JLabel();
        /*ImageIcon image = new ImageIcon(getClass().getResource("cod4.jpg"));
        Image img = image.getImage();
        Image newimg = img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
        Icon newIcon = new ImageIcon(newimg);
        this.label1 = new JLabel(newIcon);
        this.label1.setIcon(newIcon);*/
        
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        createUIComponents();

        imageBox = new JPanel();
        separator1 = new JSeparator();

        //======== this ========
        setLayout(new FormLayout(
            "[15dlu,default], $lcgap, [40dlu,default,60dlu], $lcgap, [50dlu,default]:grow, $lcgap, [25dlu,default,50dlu]:grow, $lcgap, [25dlu,default,50dlu], $lcgap, [15dlu,default]",
            "[10dlu,default], 2*($lgap, 30dlu), $lgap, [10dlu,default], $lgap, 1dlu"));

        //======== imageBox ========
        {
            imageBox.setBorder(LineBorder.createBlackLineBorder());
            imageBox.setLayout(new FormLayout(
                "[30dlu,min,50dlu]:grow",
                "fill:100px:grow"));

            //---- label1 ----
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            imageBox.add(label1, CC.xy(1, 1));
        }
        add(imageBox, CC.xywh(3, 3, 1, 3));

        //---- title ----
        title.setText("text");
        add(title, CC.xy(5, 3, CC.FILL, CC.FILL));

        //---- price ----
        price.setText("text");
        add(price, CC.xywh(7, 3, 3, 1, CC.FILL, CC.FILL));

        //---- label2 ----
        label2.setText("qty #");
        add(label2, CC.xy(7, 5, CC.FILL, CC.DEFAULT));

        //---- quantity ----
        quantity.setText("text");
        add(quantity, CC.xy(9, 5, CC.FILL, CC.DEFAULT));
        add(separator1, CC.xywh(1, 9, 11, 1));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel imageBox;
    private JLabel label1;
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
            Logger.getLogger(ProductCellRenderer.class.getName()).log(Level.SEVERE, null, ex);
            this.title.setText("???");
        }
        try {
            this.price.setText(value.getPrezzo(DateTime.now()).toString());
        }
        catch (Exception ex) {
            Logger.getLogger(ProductCellRenderer.class.getName()).log(Level.SEVERE, null, ex);
            this.price.setText("???");
        }
        try{
            this.quantity.setText(""+value.getQuantitaDisponibile());
        }
        catch(Exception ex){
            Logger.getLogger(ProductCellRenderer.class.getName()).log(Level.SEVERE, null, ex);
            this.quantity.setText("???");
        }
        
        try{
            System.err.println("imgstamp"+value.getImmagine().getIconWidth());
            this.label1.setIcon(value.getImmagine());
        }
        catch(Exception ex){
            Logger.getLogger(ProductCellRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return this;
    }
}
