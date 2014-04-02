/*
 * Created by JFormDesigner on Wed Apr 02 20:25:37 CEST 2014
 */

package gameshop.advance.ui.swing.manager;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * @author Pippo
 */
public class FornitureMenu extends JPanel {
    public FornitureMenu() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();

        //======== this ========
        setName("this");

        //---- label1 ----
        label1.setText("Coming soon");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setName("label1");

        PanelBuilder builder = new PanelBuilder(new FormLayout(
            "default, $lcgap, default:grow, $lcgap, default",
            "[15px,default]:grow, $lgap, [35px,default,50dlu], $lgap, [15px,default]:grow"), this);

        builder.add(label1, CC.xy(3, 3, CC.FILL, CC.FILL));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
