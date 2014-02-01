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
import gameshop.advance.ui.swing.UIStyleSingleton;
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
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

/**
 * @author Matteo Gentile
 */
public class InsertItemPanel extends JScrollPane {

    public InsertItemPanel() {
        initComponents();
        this.clearSale.setBackground(UIStyleSingleton.getInstance().getAlertColor());
        this.clearSale.setForeground(UIStyleSingleton.getInstance().getButtonTextColor());
        this.button2.setBackground(UIStyleSingleton.getInstance().getSuccessColor());
        this.button2.setForeground(UIStyleSingleton.getInstance().getButtonTextColor());
               
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
            Integer code = Integer.parseInt(this.clientCode.getText());
            SaleController.getInstance().inserisciCartaCliente(code);
            this.clientCode.setEditable(false);
        } catch (NullPointerException ex) {
            Logger.getLogger(InsertItemPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError("Ci sono problemi di configurazione. Se il problema persiste contattare l'amministratore di sistema.");
        } catch (RemoteException ex) {
            UIWindowSingleton.getInstance().displayError("Non è stato possibile convalidare il codice cliente.");
        }
    }

    private void clearSaleActionPerformed(ActionEvent e) {
        try{
            SaleController.getInstance().clearSale();
        } catch (NullPointerException ex) {
            Logger.getLogger(InsertItemPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(InsertItemPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConfigurationException ex) {
            Logger.getLogger(InsertItemPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void createUIComponents() {
        // TODO: add custom component creation code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel3 = new JPanel();
        clearSale = new JButton();
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

        //======== this ========
        setComponentPopupMenu(null);
        setName("this");

        //======== panel3 ========
        {
            panel3.setName("panel3");

            //---- clearSale ----
            clearSale.setText("Annulla");
            clearSale.setBackground(new Color(255, 51, 0));
            clearSale.setFont(clearSale.getFont().deriveFont(clearSale.getFont().getStyle() | Font.BOLD));
            clearSale.setForeground(Color.white);
            clearSale.setIcon(null);
            clearSale.setNextFocusableComponent(button2);
            clearSale.setName("clearSale");
            clearSale.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    clearSaleActionPerformed(e);
                    clearSaleActionPerformed(e);
                }
            });

            //---- label1 ----
            label1.setText("Totale");
            label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 1f));
            label1.setLabelFor(total);
            label1.setName("label1");

            //---- total ----
            total.setEditable(false);
            total.setName("total");

            //---- button2 ----
            button2.setText("Avanti");
            button2.setBackground(new Color(102, 204, 0));
            button2.setFont(button2.getFont().deriveFont(button2.getFont().getStyle() | Font.BOLD));
            button2.setName("nextButton");
            button2.setNextFocusableComponent(productIdTextField);
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
                productIdTextField.setNextFocusableComponent(quantityTextField);
                productIdTextField.setName("productIdTextField");

                //---- label3 ----
                label3.setText("Quantit\u00e0");
                label3.setFont(label3.getFont().deriveFont(label3.getFont().getSize() + 1f));
                label3.setLabelFor(quantityTextField);
                label3.setName("label3");

                //---- quantityTextField ----
                quantityTextField.setNextFocusableComponent(addProductButton);
                quantityTextField.setName("quantityTextField");

                //---- addProductButton ----
                addProductButton.setText("Aggiungi");
                addProductButton.setForeground(Color.black);
                addProductButton.setFont(addProductButton.getFont().deriveFont(addProductButton.getFont().getStyle() & ~Font.BOLD));
                addProductButton.setNextFocusableComponent(clientCode);
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
                clientCode.setNextFocusableComponent(insertClientCode);
                clientCode.setName("clientCode");

                //---- insertClientCode ----
                insertClientCode.setText("Inserisci");
                insertClientCode.setForeground(Color.black);
                insertClientCode.setFont(insertClientCode.getFont().deriveFont(insertClientCode.getFont().getStyle() & ~Font.BOLD));
                insertClientCode.setNextFocusableComponent(button2);
                insertClientCode.setName("insertClientCode");
                insertClientCode.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        insertClientCodeActionPerformed(e);
                    }
                });

                PanelBuilder panel1Builder = new PanelBuilder(new FormLayout(
                    "[67dlu,default], $lcgap, 110dlu",
                    "[20dlu,default], $lgap, default"), panel1);

                panel1Builder.add(label4,           CC.xy(1, 1));
                panel1Builder.add(clientCode,       CC.xy(3, 1, CC.FILL, CC.FILL));
                panel1Builder.add(insertClientCode, CC.xy(3, 3));
            }

            PanelBuilder panel3Builder = new PanelBuilder(new FormLayout(
                "5dlu, 65dlu, [20dlu,default]:grow, 65dlu, $lcgap, 61dlu, $lcgap, 56dlu, [20dlu,default]:grow, 65dlu, 5dlu",
                "5dlu, fill:[20dlu,default], $lgap, [20dlu,default,40dlu], [20dlu,default], fill:[15dlu,default,40dlu], 60dlu, fill:default:grow"), panel3);

            panel3Builder.add(clearSale,  CC.xy  ( 2, 2,    CC.FILL, CC.FILL));
            panel3Builder.add(label1,     CC.xy  ( 4, 2));
            panel3Builder.add(total,      CC.xy  ( 6, 2, CC.DEFAULT, CC.FILL));
            panel3Builder.add(button2,    CC.xy  (10, 2));
            panel3Builder.add(separator1, CC.xywh( 4, 4,          5,       1));
            panel3Builder.add(panel2,     CC.xywh( 4, 5,          5,       1));
            panel3Builder.add(panel1,     CC.xywh( 4, 7,          5,       1));
        }
        setViewportView(panel3);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel3;
    private JButton clearSale;
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
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
