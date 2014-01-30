/*
 * Created by JFormDesigner on Wed Jan 29 18:24:29 CET 2014
 */

package gameshop.advance.ui.swing.employee;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.exceptions.ProdottoNotFoundException;
import gameshop.advance.exceptions.QuantityException;
import gameshop.advance.ui.swing.UIWindowSingleton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

/**
 * @author Pippo
 */
public class InsertItem extends JPanel {
    public InsertItem() {
        initComponents();
    }

    private void goToPaymentButtonActionPerformed(ActionEvent e) {
        try {
            SaleController.getInstance().concludiVendita();
        } catch (NullPointerException ex) {
            UIWindowSingleton.getInstance().displayError("Ci sono problemi di comunicazione,"
                    + " si prega di controllare la configurazione del sistema.");
        } catch (RemoteException ex) {
            UIWindowSingleton.getInstance().displayError("Non è possibile contattare il server. "
                    + "Si prega di riprovare. Se il problema persiste, contattare l'amministratore di sistema.");
        } catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError("Ci sono problemi nella lettura del file di configurazione: "+ex.getConfigurationPath()+"."
                    + " Per maggiori informazioni rivolgersi all'amministratore di sistema.");
        }
    }

    public void clearFields()
    {
        this.productIdTextField.setText("");
        this.quantityTextField.setText("");
    }
    
    private void addProductButtonActionPerformed(ActionEvent e) {
        try {
            UIWindowSingleton.getInstance().clearError();
            Integer quantity = Integer.parseInt(this.quantityTextField.getText());
            SaleController.getInstance().inserisciProdotto(this.productIdTextField.getText(), quantity);
            this.clearFields();
            this.total.setText(SaleController.getInstance().getTotal().toString());
        } catch (NullPointerException ex) {
            UIWindowSingleton.getInstance().displayError("Ci sono problemi di comunicazione,"
                    + " si prega di controllare la configurazione del sistema.");
        } catch (RemoteException ex) {
            UIWindowSingleton.getInstance().displayError("Non è possibile contattare il server. "
                    + "Si prega di riprovare. Se il problema persiste, contattare l'amministratore di sistema.");
        } catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError("Ci sono problemi nella lettura del file di configurazione: "+ex.getConfigurationPath()+"."
                    + " Per maggiori informazioni rivolgersi all'amministratore di sistema.");
        } catch (ProdottoNotFoundException ex) {
            UIWindowSingleton.getInstance().displayError("Il codice prodotto: " +ex.getCodice()+" inserito non è valido.");
        } catch (NumberFormatException ex) {
            UIWindowSingleton.getInstance().displayError("Il formato di dato inserito per la quantità non è valido");
        } catch (QuantityException ex){
            UIWindowSingleton.getInstance().displayError("La quantità inserita: " +ex.getQuantity()+ "non è valida.");
        }
    }

    private void insertClientCodeActionPerformed(ActionEvent e) {
        try{
            UIWindowSingleton.getInstance().clearError();
            Integer code = Integer.parseInt(this.clientCode.getText());
            SaleController.getInstance().inserisciCartaCliente(code);
        } catch (NullPointerException ex) {
            Logger.getLogger(InsertItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError("Ci sono problemi di configurazione. Se il problema persiste contattare l'amministratore di sistema.");
        } catch (RemoteException ex) {
            UIWindowSingleton.getInstance().displayError("Non è stato possibile convalidare il codice cliente.");
        }
    }

    private void clearSaleActionPerformed(ActionEvent e) {
        try{
            UIWindowSingleton.getInstance().clearError();
            SaleController.getInstance().clearSale();
        } catch (NullPointerException ex) {
            Logger.getLogger(InsertItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(InsertItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConfigurationException ex) {
            Logger.getLogger(InsertItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        total = new JTextField();
        button2 = new JButton();
        separator1 = new JSeparator();
        panel2 = new JPanel();
        label2 = new JLabel();
        productIdTextField = new JTextField();
        label3 = new JLabel();
        quantityTextField = new JTextField();
        addProductButton = new JButton();
        panel1 = new JPanel();
        label4 = new JLabel();
        clientCode = new JTextField();
        insertClientCode = new JButton();
        clearSale = new JButton();

        //======== this ========
        setName("this");

        //---- label1 ----
        label1.setText("Totale");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 1f));
        label1.setLabelFor(total);
        label1.setName("label1");

        //---- total ----
        total.setName("total");

        //---- button2 ----
        button2.setText("Paga");
        button2.setBackground(new Color(102, 255, 102));
        button2.setFont(button2.getFont().deriveFont(button2.getFont().getStyle() | Font.BOLD));
        button2.setName("button2");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToPaymentButtonActionPerformed(e);
            }
        });

        //---- separator1 ----
        separator1.setName("separator1");

        //======== panel2 ========
        {
            panel2.setBorder(new CompoundBorder(
                new TitledBorder("Prodotto"),
                Borders.DLU2_BORDER));
            panel2.setName("panel2");

            //---- label2 ----
            label2.setText("Codice Prodotto");
            label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 1f));
            label2.setLabelFor(productIdTextField);
            label2.setName("label2");

            //---- productIdTextField ----
            productIdTextField.setName("productIdTextField");

            //---- label3 ----
            label3.setText("Quantit\u00e0");
            label3.setFont(label3.getFont().deriveFont(label3.getFont().getSize() + 1f));
            label3.setLabelFor(quantityTextField);
            label3.setName("label3");

            //---- quantityTextField ----
            quantityTextField.setName("quantityTextField");

            //---- addProductButton ----
            addProductButton.setText("Aggiungi");
            addProductButton.setBackground(new Color(153, 153, 255));
            addProductButton.setForeground(Color.white);
            addProductButton.setFont(addProductButton.getFont().deriveFont(addProductButton.getFont().getStyle() | Font.BOLD));
            addProductButton.setName("addProductButton");
            addProductButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addProductButtonActionPerformed(e);
                }
            });

            PanelBuilder panel2Builder = new PanelBuilder(new FormLayout(
                "65dlu, $lcgap, 53dlu:grow, $lcgap, 49dlu:grow",
                "[20dlu,default], $rgap, [20dlu,default]"), panel2);

            panel2Builder.add(label2,             CC.xy  (1, 1));
            panel2Builder.add(productIdTextField, CC.xywh(3, 1,          3,       1, CC.DEFAULT, CC.FILL));
            panel2Builder.add(label3,             CC.xy  (1, 3));
            panel2Builder.add(quantityTextField,  CC.xy  (3, 3, CC.DEFAULT, CC.FILL));
            panel2Builder.add(addProductButton,   CC.xy  (5, 3));
        }

        //======== panel1 ========
        {
            panel1.setBorder(new CompoundBorder(
                new TitledBorder("Carta Cliente"),
                Borders.DLU2_BORDER));
            panel1.setName("panel1");

            //---- label4 ----
            label4.setText("Codice");
            label4.setFont(label4.getFont().deriveFont(label4.getFont().getSize() + 1f));
            label4.setLabelFor(clientCode);
            label4.setName("label4");

            //---- clientCode ----
            clientCode.setName("clientCode");

            //---- insertClientCode ----
            insertClientCode.setText("Inserisci");
            insertClientCode.setForeground(Color.white);
            insertClientCode.setBackground(new Color(153, 153, 255));
            insertClientCode.setFont(insertClientCode.getFont().deriveFont(insertClientCode.getFont().getStyle() | Font.BOLD));
            insertClientCode.setName("insertClientCode");
            insertClientCode.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    insertClientCodeActionPerformed(e);
                }
            });

            PanelBuilder panel1Builder = new PanelBuilder(new FormLayout(
                "[67dlu,default], $lcgap, 110dlu",
                "24dlu, $lgap, default"), panel1);

            panel1Builder.add(label4,           CC.xy(1, 1));
            panel1Builder.add(clientCode,       CC.xy(3, 1, CC.FILL, CC.FILL));
            panel1Builder.add(insertClientCode, CC.xy(3, 3));
        }

        //---- clearSale ----
        clearSale.setText("Annulla");
        clearSale.setBackground(new Color(255, 51, 0));
        clearSale.setFont(clearSale.getFont().deriveFont(clearSale.getFont().getStyle() | Font.BOLD));
        clearSale.setForeground(Color.white);
        clearSale.setName("clearSale");
        clearSale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearSaleActionPerformed(e);
            }
        });

        PanelBuilder builder = new PanelBuilder(new FormLayout(
            "[20dlu,default], $lcgap, 65dlu, $lcgap, 61dlu, $lcgap, 56dlu, $lcgap, 20dlu",
            "[15dlu,default], $lgap, 17dlu, $rgap, [9dlu,default], $rgap, [20dlu,default], $rgap, 60dlu, $rgap, [25dlu,default], $lgap, default"), this);

        builder.add(label1,     CC.xy  (3,  3));
        builder.add(total,      CC.xy  (5,  3, CC.DEFAULT, CC.FILL));
        builder.add(button2,    CC.xy  (7,  3));
        builder.add(separator1, CC.xywh(2,  5,          6,       1));
        builder.add(panel2,     CC.xywh(3,  7,          5,       1));
        builder.add(panel1,     CC.xywh(3,  9,          5,       1));
        builder.add(clearSale,  CC.xy  (3, 11));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JTextField total;
    private JButton button2;
    private JSeparator separator1;
    private JPanel panel2;
    private JLabel label2;
    private JTextField productIdTextField;
    private JLabel label3;
    private JTextField quantityTextField;
    private JButton addProductButton;
    private JPanel panel1;
    private JLabel label4;
    private JTextField clientCode;
    private JButton insertClientCode;
    private JButton clearSale;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
