/*
 * Created by JFormDesigner on Wed Jan 29 17:35:14 CET 2014
 */

package gameshop.advance.ui.swing;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * @author Pippo
 */
public class ConfigurationDialog extends JDialog {
    public ConfigurationDialog(Frame owner) {
        super(owner);
        initComponents();
    }

    public ConfigurationDialog(Dialog owner) {
        super(owner);
        initComponents();
    }

    public void addPanel(JPanel panel)
    {
        this.tabbedPane1.add(panel);
    }
    
    public void removePanel(JPanel panel)
    {
        this.tabbedPane1.remove(panel);
    }
    
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        tabbedPane1 = new JTabbedPane();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        setName("this");
        Container contentPane = getContentPane();

        //======== tabbedPane1 ========
        {
            tabbedPane1.setName("tabbedPane1");
        }

        //---- button1 ----
        button1.setText("Annulla");
        button1.setToolTipText("Annulla i cambiamenti");
        button1.setBackground(new Color(153, 255, 51));
        button1.setName("button1");

        //---- button2 ----
        button2.setText("Salva");
        button2.setBackground(new Color(255, 204, 51));
        button2.setToolTipText("Salva le modifiche apportate");
        button2.setName("button2");

        PanelBuilder contentPaneBuilder = new PanelBuilder(new FormLayout(
            "15dlu, $lcgap, 50dlu, $lcgap, default:grow, $lcgap, [50dlu,default], $lcgap, 15dlu",
            "[20dlu,default], $lgap, fill:[125dlu,default]:grow, $rgap, 25dlu, $lgap, 20dlu"));
        contentPane.setLayout(new BorderLayout());
        contentPane.add(contentPaneBuilder.getPanel(), BorderLayout.CENTER);

        contentPaneBuilder.add(tabbedPane1, CC.xywh(3, 3, 5, 1, CC.FILL, CC.FILL));
        contentPaneBuilder.add(button1,     CC.xy  (3, 5));
        contentPaneBuilder.add(button2,     CC.xy  (7, 5));
        setSize(500, 330);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTabbedPane tabbedPane1;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
