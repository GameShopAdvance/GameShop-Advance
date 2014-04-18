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
        this.button1.setBackground(UIStyleSingleton.getInstance().getAlertColor());
        this.button1.setForeground(UIStyleSingleton.getInstance().getButtonTextColor());
        this.button2.setBackground(UIStyleSingleton.getInstance().getSuccessColor());
        this.button2.setForeground(UIStyleSingleton.getInstance().getButtonTextColor());
    }

    public ConfigurationDialog(Dialog owner) {
        super(owner);
        initComponents();
        this.setConfigurationValues();
    }

    private void setConfigurationValues()
    {
        try{
            System.err.println("Config ip: "+ConfigurationControllerSingleton.getInstance().getServerAddress());
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
        int nCassa = 0;
        try {
            System.err.println("Config: "+ConfigurationControllerSingleton.getInstance());
            String cassa = this.newIdCassa.getText();
            System.out.println("Cassa: " + cassa);
            boolean error = false;
            if(!cassa.isEmpty())
            {
                try{
                    nCassa = Integer.decode(cassa);
                    ConfigurationControllerSingleton.getInstance().setIdCassa(nCassa);
                } catch(NumberFormatException ex) {
                    UIWindowSingleton.getInstance().displayError("Il numero di cassa inserito: " + cassa + "non è valido.");
                }
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
        this.button2 = UIFactory.getInstance().getConfirmButton();
        this.button1 = UIFactory.getInstance().getCancelButton();
        this.label1 = UIFactory.getInstance().getBodyLabel();
        this.label2 = UIFactory.getInstance().getBodyLabel();
        this.label3 = UIFactory.getInstance().getBodyLabel();
        this.label4 = UIFactory.getInstance().getBodyLabel();
        this.serverAddress = UIFactory.getInstance().getTextField();
        this.serverPort = UIFactory.getInstance().getTextField();
        this.newIdCassa = UIFactory.getInstance().getTextField();
        this.idCassa = UIFactory.getInstance().getTextField();
    }
    
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        createUIComponents();

        tabbedPane1 = new JTabbedPane();
        panel1 = new JPanel();
        panel2 = new JPanel();

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
                    "15dlu, $lgap, [30dlu,default], $ugap, [30dlu,default]"), panel1);

                panel1Builder.add(label1,        CC.xy  (3, 3, CC.DEFAULT, CC.FILL));
                panel1Builder.add(serverAddress, CC.xywh(5, 3,          3,       1, CC.FILL, CC.FILL));
                panel1Builder.add(label2,        CC.xy  (3, 5,    CC.FILL, CC.FILL));
                panel1Builder.add(serverPort,    CC.xy  (5, 5,    CC.FILL, CC.FILL));
            }
            tabbedPane1.addTab("Connessione", panel1);

            //======== panel2 ========
            {
                panel2.setName("panel2");

                //---- label3 ----
                label3.setText("Numero di cassa attuale");
                label3.setLabelFor(idCassa);
                label3.setName("label3");

                //---- idCassa ----
                idCassa.setEditable(false);
                idCassa.setName("idCassa");

                //---- label4 ----
                label4.setText("Nuovo numero di cassa");
                label4.setLabelFor(newIdCassa);
                label4.setName("label4");

                //---- newIdCassa ----
                newIdCassa.setName("newIdCassa");

                PanelBuilder panel2Builder = new PanelBuilder(new FormLayout(
                    "[10dlu,default], $lcgap, [100dlu,default], $lcgap, [10dlu,default], $lcgap, [35dlu,default], 2*($lcgap, default)",
                    "15dlu, $lgap, 30dlu, $rgap, [30dlu,default]"), panel2);

                panel2Builder.add(label3,     CC.xy(3, 3, CC.FILL, CC.FILL));
                panel2Builder.add(idCassa,    CC.xy(7, 3, CC.FILL, CC.FILL));
                panel2Builder.add(label4,     CC.xy(3, 5, CC.FILL, CC.FILL));
                panel2Builder.add(newIdCassa, CC.xy(7, 5, CC.FILL, CC.FILL));
            }
            tabbedPane1.addTab("Cassa", panel2);
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
            "[15dlu,default], $lcgap, [70dlu,default], $lcgap, [98dlu,default]:grow, $lcgap, 70dlu, $lcgap, [15dlu,default]",
            "[20dlu,default], $lgap, fill:[125dlu,default]:grow, $rgap, [35dlu,default], $lgap, 20dlu"));
        contentPane.setLayout(new BorderLayout());
        contentPane.add(contentPaneBuilder.getPanel(), BorderLayout.CENTER);

        contentPaneBuilder.add(tabbedPane1, CC.xywh(3, 3,       5,       1, CC.FILL, CC.FILL));
        contentPaneBuilder.add(button1,     CC.xy  (3, 5, CC.FILL, CC.FILL));
        contentPaneBuilder.add(button2,     CC.xy  (7, 5, CC.FILL, CC.FILL));
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
