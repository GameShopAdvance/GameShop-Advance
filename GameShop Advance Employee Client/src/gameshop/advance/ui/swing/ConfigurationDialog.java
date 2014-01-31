/*
 * Created by JFormDesigner on Wed Jan 29 17:35:14 CET 2014
 */

package gameshop.advance.ui.swing;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.config.ConfigurationControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 * @author Lorenzo Di Giuseppe
 */
public class ConfigurationDialog extends JDialog {
    
    public ConfigurationDialog(Frame owner) {
        super(owner);
        initComponents();
        this.setConfigurationValues();
    }

    public ConfigurationDialog(Dialog owner) {
        super(owner);
        initComponents();
        this.setConfigurationValues();
    }

    private void setConfigurationValues()
    {
        try{
            this.serverAddress.setText(ConfigurationControllerSingleton.getInstance().getServerAddress());
            this.serverPort.setText(""+ConfigurationControllerSingleton.getInstance().getServerPort());
            this.idCassa.setText(""+ConfigurationControllerSingleton.getInstance().getIdCassa());
        } catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError("Si è verificato un errore imprevisto.");
        }
    }
    
    public void addPanel(JPanel panel)
    {
        this.tabbedPane1.add(panel);
    }
    
    public void removePanel(JPanel panel)
    {
        this.tabbedPane1.remove(panel);
    }

    private void saveConfiguration(ActionEvent e) {
        try {
            System.err.println("Config: "+ConfigurationControllerSingleton.getInstance());
            String cassa = this.newIdCassa.getText();
            System.out.println("Cassa: " + cassa);
            boolean error = false;
            if(!cassa.isEmpty())
            {
                int nCassa = Integer.decode(cassa);
                ConfigurationControllerSingleton.getInstance().setIdCassa(nCassa);
            }
            try{
                ConfigurationControllerSingleton.getInstance().setServerAddress(this.serverAddress.getText());
                
            } catch (UnknownHostException ex) {
                UIWindowSingleton.getInstance().displayError("Indirizzo non valido.");
                this.serverAddress.setBackground(UIStyleSingleton.getInstance().getErrorColor());
                error = true;
            }
            
            String port = this.serverPort.getText();
            System.out.println("Porta: " + port);
            if(!port.isEmpty())
            {
                int nPort = Integer.decode(port);
                ConfigurationControllerSingleton.getInstance().setServerPort(nPort);
            }
            else
            {
                error = true;
                this.serverPort.setBackground(UIStyleSingleton.getInstance().getErrorColor());
            }
            if(!error)
                this.dispose();
        } catch (ConfigurationException ex) {
            Logger.getLogger(ConfigurationDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cancelModify(ActionEvent e) {
        this.dispose();
    }

    private void createUIComponents() {
        // TODO: add custom component creation code here
    }
    
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        tabbedPane1 = new JTabbedPane();
        panel1 = new JPanel();
        label1 = new JLabel();
        serverAddress = new JTextField();
        label2 = new JLabel();
        serverPort = new JTextField();
        panel2 = new JPanel();
        label3 = new JLabel();
        idCassa = new JTextField();
        label4 = new JLabel();
        newIdCassa = new JTextField();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        setName("this");
        Container contentPane = getContentPane();

        //======== tabbedPane1 ========
        {
            tabbedPane1.setName("tabbedPane1");

            //======== panel1 ========
            {
                panel1.setName("panel1");

                //---- label1 ----
                label1.setText("Indirizzo server");
                label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 2f));
                label1.setLabelFor(serverAddress);
                label1.setName("label1");

                //---- serverAddress ----
                serverAddress.setName("serverAddress");

                //---- label2 ----
                label2.setText("Porta");
                label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 2f));
                label2.setLabelFor(serverPort);
                label2.setName("label2");

                //---- serverPort ----
                serverPort.setName("serverPort");

                PanelBuilder panel1Builder = new PanelBuilder(new FormLayout(
                    "10dlu, $lcgap, 70dlu, $lcgap, 75dlu, $lcgap, [53dlu,default]:grow, $lcgap, [10dlu,default]",
                    "15dlu, $lgap, [30dlu,default], $ugap, [30dlu,default]"), panel1);

                panel1Builder.add(label1,        CC.xy  (3, 3));
                panel1Builder.add(serverAddress, CC.xywh(5, 3, 3, 1));
                panel1Builder.add(label2,        CC.xy  (3, 5));
                panel1Builder.add(serverPort,    CC.xy  (5, 5));
            }
            tabbedPane1.addTab("Connessione", panel1);

            //======== panel2 ========
            {
                panel2.setName("panel2");

                //---- label3 ----
                label3.setText("Numero di cassa attuale");
                label3.setFont(label3.getFont().deriveFont(label3.getFont().getSize() + 2f));
                label3.setLabelFor(idCassa);
                label3.setName("label3");

                //---- idCassa ----
                idCassa.setEditable(false);
                idCassa.setName("idCassa");

                //---- label4 ----
                label4.setText("Nuovo numero di cassa");
                label4.setFont(label4.getFont().deriveFont(label4.getFont().getSize() + 2f));
                label4.setLabelFor(newIdCassa);
                label4.setName("label4");

                //---- newIdCassa ----
                newIdCassa.setName("newIdCassa");

                PanelBuilder panel2Builder = new PanelBuilder(new FormLayout(
                    "[10dlu,default], $lcgap, [100dlu,default], $lcgap, [10dlu,default], $lcgap, [35dlu,default], 2*($lcgap, default)",
                    "15dlu, $lgap, 30dlu, $rgap, [30dlu,default]"), panel2);

                panel2Builder.add(label3,     CC.xy(3, 3));
                panel2Builder.add(idCassa,    CC.xy(7, 3));
                panel2Builder.add(label4,     CC.xy(3, 5));
                panel2Builder.add(newIdCassa, CC.xy(7, 5));
            }
            tabbedPane1.addTab("Cassa", panel2);
        }

        //---- button1 ----
        button1.setText("Annulla");
        button1.setToolTipText("Annulla i cambiamenti");
        button1.setBackground(new Color(153, 255, 51));
        button1.setName("button1");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelModify(e);
            }
        });

        //---- button2 ----
        button2.setText("Salva");
        button2.setBackground(new Color(255, 204, 51));
        button2.setToolTipText("Salva le modifiche apportate");
        button2.setName("button2");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveConfiguration(e);
            }
        });

        PanelBuilder contentPaneBuilder = new PanelBuilder(new FormLayout(
            "[15dlu,default], $lcgap, [70dlu,default], $lcgap, [98dlu,default]:grow, $lcgap, 70dlu, $lcgap, [15dlu,default]",
            "[20dlu,default], $lgap, fill:[125dlu,default]:grow, $rgap, 25dlu, $lgap, 20dlu"));
        contentPane.setLayout(new BorderLayout());
        contentPane.add(contentPaneBuilder.getPanel(), BorderLayout.CENTER);

        contentPaneBuilder.add(tabbedPane1, CC.xywh(3, 3, 5, 1, CC.FILL, CC.FILL));
        contentPaneBuilder.add(button1,     CC.xy  (3, 5));
        contentPaneBuilder.add(button2,     CC.xy  (7, 5));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JLabel label1;
    private JTextField serverAddress;
    private JLabel label2;
    private JTextField serverPort;
    private JPanel panel2;
    private JLabel label3;
    private JTextField idCassa;
    private JLabel label4;
    private JTextField newIdCassa;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
