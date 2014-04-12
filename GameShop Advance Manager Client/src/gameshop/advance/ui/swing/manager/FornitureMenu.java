/*
 * Created by JFormDesigner on Wed Apr 02 20:25:37 CEST 2014
 */

package gameshop.advance.ui.swing.manager;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.interfaces.remote.IInformazioniProdottoRemote;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class FornitureMenu extends JPanel{
    
    private final DefaultListModel<IInformazioniProdottoRemote> infoProducts;
    
    public FornitureMenu() {     
        this.infoProducts = new DefaultListModel<>();
        this.infoList.setCellRenderer(new InfoCellRenderer());
        this.infoList.setModel(this.infoProducts);
    }
    
    public void addInfoProduct(IInformazioniProdottoRemote infoProd){
        this.infoProducts.addElement(infoProd);
    }
    
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        infoList = new JList();

        //======== this ========
        setLayout(new FormLayout(
            "default, $lcgap, default:grow, $lcgap, default",
            "[25px,default], $lgap, fill:177dlu, $lgap, [77px,default]"));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(infoList);
        }
        add(scrollPane1, CC.xy(3, 3));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JList infoList;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
