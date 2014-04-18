/*
 * Created by JFormDesigner on Wed Jan 29 17:35:14 CET 2014
 */

package gameshop.advance.ui.swing;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.config.ConfigurationControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.ui.swing.factory.UIFactory;
import gameshop.advance.ui.swing.factory.UIStyleSingleton;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Schermata per la configurazione della connessione del client.
 * @author Lorenzo Di Giuseppe
 */
public class ConfigurationDialog extends JDialog {
    
    public ConfigurationDialog(Frame owner) {
        super(owner);
        initComponents();
        this.setConfigurationValues();
        this.button1.setBackground(UIStyleSingleton.getInstance().getSuccessColor());
        this.button1.setForeground(UIStyleSingleton.getInstance().getButtonTextColor());
        this.button2.setBackground(UIStyleSingleton.getInstance().getAlertColor());
        this.button2.setForeground(UIStyleSingleton.getInstance().getButtonTextColor());
    }

    /**
     * @param owner
     */
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
        } catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError("Si è verificato un errore imprevisto.");
        }
    }

    private void saveConfiguration(ActionEvent e) {
        try {
            boolean error = false;
            
            try{
                ConfigurationControllerSingleton.getInstance().setServerAddress(this.serverAddress.getText());
                
            } catch (UnknownHostException ex) {
                UIWindowSingleton.getInstance().displayError("Indirizzo non valido.");
                this.serverAddress.setBackground(UIStyleSingleton.getInstance().getErrorColor());
                error = true;
            }
            
            String port = this.serverPort.getText();
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
            {
                ConfigurationControllerSingleton.getInstance().storeConfiguration();
                this.dispose();
            }
        } catch (ConfigurationException ex) {
               UIWindowSingleton.getInstance().displayError("Ci sono problemi nella lettura del file di configurazione: "+ex.getConfigurationPath()+"."
                    + " Per maggiori informazioni rivolgersi all'amministratore di sistema.");
        }
    }

    private void cancelModify(ActionEvent e) {
        this.dispose();
    }

    private void createUIComponents() {
        this.label1 = UIFactory.getInstance().getBodyLabel();
        this.label2 = UIFactory.getInstance().getBodyLabel();
        
        this.serverAddress = UIFactory.getInstance().getTextField();
        this.serverPort = UIFactory.getInstance().getTextField();
        
        this.button2 = UIFactory.getInstance().getCancelButton();
        this.button1 = UIFactory.getInstance().getConfirmButton();
    }
    
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        createUIComponents();

        panel1 = new JPanel();

        //======== this ========
        setName("this");
        Container contentPane = getContentPane();

        //======== panel1 ========
        {
            panel1.setName("panel1");

            //---- label1 ----
            label1.setText("Indirizzo server");
            label1.setName("label1");

            //---- serverAddress ----
            serverAddress.setName("serverAddress");

            //---- label2 ----
            label2.setText("Porta");
            label2.setLabelFor(serverPort);
            label2.setName("label2");

            //---- serverPort ----
            serverPort.setName("serverPort");

            PanelBuilder panel1Builder = new PanelBuilder(new FormLayout(
                "10dlu, $lcgap, 70dlu, $lcgap, 75dlu, $lcgap, [53dlu,default]:grow, $lcgap, [10dlu,default]",
                "15dlu, $lgap, [35dlu,default], $ugap, [35dlu,default]"), panel1);

            panel1Builder.add(label1,        CC.xy  (3, 3, CC.DEFAULT, CC.FILL));
            panel1Builder.add(serverAddress, CC.xywh(5, 3,          3,       1, CC.FILL, CC.FILL));
            panel1Builder.add(label2,        CC.xy  (3, 5, CC.DEFAULT, CC.FILL));
            panel1Builder.add(serverPort,    CC.xy  (5, 5, CC.DEFAULT, CC.FILL));
        }

        //---- button1 ----
        button1.setText("Annulla");
        button1.setToolTipText("Annulla i cambiamenti");
        button1.setName("button1");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelModify(e);
            }
        });

        //---- button2 ----
        button2.setText("Salva");
        button2.setToolTipText("Salva le modifiche apportate");
        button2.setName("button2");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveConfiguration(e);
            }
        });

        PanelBuilder contentPaneBuilder = new PanelBuilder(new FormLayout(
            "[15dlu,default], $lcgap, [75dlu,default], $lcgap, [98dlu,default]:grow, $lcgap, [75dlu,default], $lcgap, [15dlu,default]",
            "[20dlu,default], $lgap, fill:[125dlu,default]:grow, $rgap, [35dlu,default], $lgap, 20dlu"));
        contentPane.setLayout(new BorderLayout());
        contentPane.add(contentPaneBuilder.getPanel(), BorderLayout.CENTER);

        contentPaneBuilder.add(panel1,  CC.xywh(3, 3,       5,       1));
        contentPaneBuilder.add(button1, CC.xy  (3, 5, CC.FILL, CC.FILL));
        contentPaneBuilder.add(button2, CC.xy  (7, 5, CC.FILL, CC.FILL));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JLabel label1;
    private JTextField serverAddress;
    private JLabel label2;
    private JTextField serverPort;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
