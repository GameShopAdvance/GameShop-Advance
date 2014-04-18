/*
 * Created by JFormDesigner on Sun Mar 30 09:50:48 CEST 2014
 */

package gameshop.advance.ui.swing.lists.renderer;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.interfaces.remote.IDescrizioneProdottoRemote;
import gameshop.advance.technicalservices.LoggerSingleton;
import gameshop.advance.ui.swing.factory.UIFactory;
import java.awt.Component;
import java.rmi.RemoteException;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
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
        setName("this");

        //======== imageBox ========
        {
            imageBox.setBorder(LineBorder.createBlackLineBorder());
            imageBox.setName("imageBox");

            //---- label1 ----
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            label1.setBorder(new BevelBorder(BevelBorder.LOWERED));
            label1.setName("label1");

            PanelBuilder imageBoxBuilder = new PanelBuilder(new FormLayout(
                "[30dlu,min,50dlu]:grow",
                "fill:100px:grow"), imageBox);

            imageBoxBuilder.add(label1, CC.xy(1, 1));
        }

        //---- title ----
        title.setText("text");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setName("title");

        //---- price ----
        price.setText("text");
        price.setHorizontalAlignment(SwingConstants.RIGHT);
        price.setName("price");

        //---- label2 ----
        label2.setText("Disponibili:");
        label2.setHorizontalAlignment(SwingConstants.RIGHT);
        label2.setName("label2");

        //---- quantity ----
        quantity.setText("text");
        quantity.setName("quantity");

        //---- separator1 ----
        separator1.setName("separator1");

        PanelBuilder builder = new PanelBuilder(new FormLayout(
            "[15dlu,default], $lcgap, [40dlu,default,210px], $ugap, [50dlu,default]:grow, $lcgap, [25dlu,default,50dlu]:grow, $lcgap, [25dlu,default,50dlu], $lcgap, [15dlu,default]",
            "[10dlu,default], 2*($lgap, [35dlu,default,120px]), $lgap, [10dlu,default], $lgap, 1dlu"), this);

        builder.add(imageBox,   CC.xywh(3, 3,       1,          3));
        builder.add(title,      CC.xy  (5, 3, CC.FILL,    CC.FILL));
        builder.add(price,      CC.xy  (9, 3, CC.FILL,    CC.FILL));
        builder.add(label2,     CC.xy  (7, 5, CC.FILL, CC.DEFAULT));
        builder.add(quantity,   CC.xy  (9, 5, CC.FILL, CC.DEFAULT));
        builder.add(separator1, CC.xywh(1, 9,      11,          1));
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
            this.title.setText(value.getNomeProdotto());
        }
        catch (RemoteException ex) {
            LoggerSingleton.getInstance().log(ex);
            this.title.setText("???");
        }
        try {
            this.price.setText(value.getPrezzo(DateTime.now()).toString());
        }
        catch (RemoteException ex) {
            LoggerSingleton.getInstance().log(ex);
            this.price.setText("???");
        }
        try{
            this.quantity.setText(""+value.getQuantitaDisponibile());
        }
        catch(RemoteException ex){
            LoggerSingleton.getInstance().log(ex);
            this.quantity.setText("???");
        }
        
        try{
            this.label1.setIcon(value.getImmagine().getIcon());
        }
        catch(RemoteException ex){
            this.label1.setText("Immagine non trovata");
            LoggerSingleton.getInstance().log(ex);
        }
        
        
        return this;
    }
}
