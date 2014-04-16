/*
 * Created by JFormDesigner on Wed Jan 29 17:35:14 CET 2014
 */

package gameshop.advance.ui.swing;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.config.ConfigurationControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
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
        this.button1 = UIFactory.getInstance().getConfirmButton();
        this.button2 = UIFactory.getInstance().getCancelButton();
        this.label1 = UIFactory.getInstance().getBodyLabel();
        this.label2 = UIFactory.getInstance().getBodyLabel();
        this.label3 = UIFactory.getInstance().getBodyLabel();
        this.label4 = UIFactory.getInstance().getBodyLabel();
    }
    
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        createUIComponents();

        tabbedPane1 = new JTabbedPane();
        panel1 = new JPanel();
        serverAddress = new JFormattedTextField();
        serverPort = new JTextField();
        panel2 = new JPanel();
        idCassa = new JTextField();
        newIdCassa = new JTextField();
        button1 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "[15dlu,default], $lcgap, [70dlu,default], $lcgap, [98dlu,default]:grow, $lcgap, 70dlu, $lcgap, [15dlu,default]",
            "[20dlu,default], $lgap, fill:[125dlu,default]:grow, $rgap, 25dlu, $lgap, 20dlu"));

        //======== tabbedPane1 ========
        {

            //======== panel1 ========
            {
                panel1.setLayout(new FormLayout(
                    "10dlu, $lcgap, 70dlu, $lcgap, 75dlu, $lcgap, [53dlu,default]:grow, $lcgap, [10dlu,default]",
                    "15dlu, $lgap, [30dlu,default], $ugap, [30dlu,default]"));

                //---- label1 ----
                label1.setText("Indirizzo server");
                panel1.add(label1, CC.xy(3, 3));
                panel1.add(serverAddress, CC.xywh(5, 3, 3, 1, CC.FILL, CC.DEFAULT));

                //---- label2 ----
                label2.setText("Porta");
                label2.setLabelFor(serverPort);
                panel1.add(label2, CC.xy(3, 5));
                panel1.add(serverPort, CC.xy(5, 5));
            }
            tabbedPane1.addTab("Connessione", panel1);

            //======== panel2 ========
            {
                panel2.setLayout(new FormLayout(
                    "[10dlu,default], $lcgap, [100dlu,default], $lcgap, [10dlu,default], $lcgap, [35dlu,default], 2*($lcgap, default)",
                    "15dlu, $lgap, 30dlu, $rgap, [30dlu,default]"));

                //---- label3 ----
                label3.setText("Numero di cassa attuale");
                label3.setLabelFor(idCassa);
                panel2.add(label3, CC.xy(3, 3));

                //---- idCassa ----
                idCassa.setEditable(false);
                panel2.add(idCassa, CC.xy(7, 3));

                //---- label4 ----
                label4.setText("Nuovo numero di cassa");
                label4.setLabelFor(newIdCassa);
                panel2.add(label4, CC.xy(3, 5));
                panel2.add(newIdCassa, CC.xy(7, 5));
            }
            tabbedPane1.addTab("Cassa", panel2);
        }
        contentPane.add(tabbedPane1, CC.xywh(3, 3, 5, 1, CC.FILL, CC.FILL));

        //---- button1 ----
        button1.setText("Annulla");
        button1.setToolTipText("Annulla i cambiamenti");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelModify(e);
            }
        });
        contentPane.add(button1, CC.xy(3, 5));

        //---- button2 ----
        button2.setText("Salva");
        button2.setToolTipText("Salva le modifiche apportate");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveConfiguration(e);
            }
        });
        contentPane.add(button2, CC.xy(7, 5));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JLabel label1;
    private JFormattedTextField serverAddress;
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
