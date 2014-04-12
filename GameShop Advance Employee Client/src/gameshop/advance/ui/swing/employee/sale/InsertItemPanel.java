/*
 * Created by JFormDesigner on Wed Jan 29 18:24:29 CET 2014
 */

package gameshop.advance.ui.swing.employee.sale;

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.SaleControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.exceptions.ProdottoNotFoundException;
import gameshop.advance.exceptions.QuantityException;
import gameshop.advance.exceptions.QuantityNotInStockException;
import gameshop.advance.technicalservices.LoggerSingleton;
import gameshop.advance.ui.swing.UIStyleSingleton;
import gameshop.advance.ui.swing.UIWindowSingleton;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

/**
 * @author Matteo Gentile
 */

public class InsertItemPanel extends JScrollPane {
    private DefaultListModel<Object> rdvListModel;

    public InsertItemPanel() {
        initComponents();
        this.clearSale.setBackground(UIStyleSingleton.getInstance().getAlertColor());
        this.clearSale.setForeground(UIStyleSingleton.getInstance().getButtonTextColor());
        this.button2.setBackground(UIStyleSingleton.getInstance().getSuccessColor());
        this.button2.setForeground(UIStyleSingleton.getInstance().getButtonTextColor());
        this.rdvListModel = new DefaultListModel<>();
        this.rdvList.setCellRenderer(new rdvCellRender());
        this.rdvList.setModel(this.rdvListModel);
               
    }

    private void goToPaymentButtonActionPerformed(ActionEvent e) {
        try {
            SaleControllerSingleton.getInstance().concludiVendita();
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
            Integer quantity = 1;
            try{
                 quantity = Integer.parseInt(this.quantityTextField.getText());
            }
            catch(NumberFormatException ex)
            {
                if(!this.quantityTextField.getText().equals(""))
                    UIWindowSingleton.getInstance().displayError("Il formato di dato inserito per la quantità non è valido");
            }
            SaleControllerSingleton.getInstance().inserisciProdotto(this.productIdTextField.getText(), quantity);
            this.clearFields();
            this.total.setText(SaleControllerSingleton.getInstance().getTotal().toString());
        }
        catch (NullPointerException ex) {
            UIWindowSingleton.getInstance().displayError("Ci sono problemi di comunicazione,"
                    + " si prega di controllare la configurazione del sistema.");
            LoggerSingleton.getInstance().log(ex);
        } catch (RemoteException ex) {
            UIWindowSingleton.getInstance().displayError("Non è possibile contattare il server. "
                    + "Si prega di riprovare. Se il problema persiste, contattare l'amministratore di sistema.");
            LoggerSingleton.getInstance().log(ex);
        } catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError("Ci sono problemi nella lettura del file di configurazione: "+ex.getConfigurationPath()+"."
                    + " Per maggiori informazioni rivolgersi all'amministratore di sistema.");
            LoggerSingleton.getInstance().log(ex);
        } catch (ProdottoNotFoundException ex) {
            UIWindowSingleton.getInstance().displayError("Il codice prodotto: " +ex.getCodice()+" inserito non è valido.");
            LoggerSingleton.getInstance().log(ex);
        }  catch (QuantityException ex){
            UIWindowSingleton.getInstance().displayError("La quantità inserita: " +ex.getQuantity()+ "non è valida.");
            LoggerSingleton.getInstance().log(ex);
        }
        catch (QuantityNotInStockException ex) {
            UIWindowSingleton.getInstance().displayError("La quantità non è presente in magazzino, si consiglia di prenotarla.");
            LoggerSingleton.getInstance().log(ex);
        }
    }

    private void insertClientCodeActionPerformed(ActionEvent e) {
        try{
            Integer code = Integer.parseInt(this.clientCode.getText());
            SaleControllerSingleton.getInstance().inserisciCartaCliente(code);
            this.clientCode.setEditable(false);
            this.total.setText(SaleControllerSingleton.getInstance().getTotal().toString());
        } catch (NullPointerException ex) {
             UIWindowSingleton.getInstance().displayError("Non è stato possibile convalidare il codice cliente.");
        } catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError("Ci sono problemi di configurazione. Se il problema persiste contattare l'amministratore di sistema.");
        } catch (RemoteException ex) {
            UIWindowSingleton.getInstance().displayError("Non è stato possibile convalidare il codice cliente.");
        } catch (NumberFormatException ex){
            UIWindowSingleton.getInstance().displayError("Il codice cliente inserito non è valido o il suo formato non è corretto");
        }
    }

    private void clearSaleActionPerformed(ActionEvent e) {
        try{
            SaleControllerSingleton.getInstance().clearSale();
        } catch (NullPointerException ex) {
             UIWindowSingleton.getInstance().displayError("Non è stato possibile convalidare il codice cliente.");       
        } catch (RemoteException ex) {
            UIWindowSingleton.getInstance().displayError("Non è possibile contattare il server. "
                    + "Si prega di riprovare. Se il problema persiste, contattare l'amministratore di sistema.");
        } catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError("Ci sono problemi di configurazione. Se il problema persiste contattare l'amministratore di sistema.");
        }
        
    }
        

    private void createUIComponents() {
        // TODO: add custom component creation code here
    }

    private void startSaleActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void manageBookActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void faiInventario(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel3 = new JPanel();
        panel2 = new JPanel();
        label2 = new JLabel();
        productIdTextField = new JTextField();
        label3 = new JLabel();
        quantityTextField = new JTextField();
        addProductButton = new JButton();
        separator2 = new JSeparator();
        panel4 = new JPanel();
        scrollPane1 = new JScrollPane();
        rdvList = new JList();
        panel1 = new JPanel();
        label4 = new JLabel();
        clientCode = new JTextField();
        insertClientCode = new JButton();
        clearSale = new JButton();
        label1 = new JLabel();
        total = new JTextField();
        button2 = new JButton();

        //======== this ========
        setComponentPopupMenu(null);

        //======== panel3 ========
        {
            panel3.setLayout(new GridBagLayout());
            ((GridBagLayout)panel3.getLayout()).columnWidths = new int[] {5, 150, 100, 240, 10, 158, 5, 0};
            ((GridBagLayout)panel3.getLayout()).rowHeights = new int[] {8, 150, 159, 60, 0, 0};
            ((GridBagLayout)panel3.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel3.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

            //======== panel2 ========
            {
                panel2.setBorder(new CompoundBorder(
                    new TitledBorder("Prodotto"),
                    Borders.DLU2_BORDER));
                panel2.setLayout(new FormLayout(
                    "[90px,min], $lcgap, [143px,min], $lcgap, [199px,min]",
                    "[31dlu,default], $rgap, fill:[34dlu,default]"));

                //---- label2 ----
                label2.setText("Codice Prodotto");
                label2.setFont(new Font("Tahoma", Font.PLAIN, 16));
                label2.setLabelFor(productIdTextField);
                panel2.add(label2, CC.xy(1, 1));

                //---- productIdTextField ----
                productIdTextField.setNextFocusableComponent(quantityTextField);
                panel2.add(productIdTextField, CC.xywh(3, 1, 3, 1, CC.DEFAULT, CC.FILL));

                //---- label3 ----
                label3.setText("Quantit\u00e0");
                label3.setFont(new Font("Tahoma", Font.PLAIN, 16));
                label3.setLabelFor(quantityTextField);
                panel2.add(label3, CC.xy(1, 3));

                //---- quantityTextField ----
                quantityTextField.setNextFocusableComponent(addProductButton);
                panel2.add(quantityTextField, CC.xy(3, 3, CC.DEFAULT, CC.FILL));

                //---- addProductButton ----
                addProductButton.setText("Aggiungi");
                addProductButton.setForeground(Color.black);
                addProductButton.setNextFocusableComponent(clientCode);
                addProductButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
                addProductButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addProductButtonActionPerformed(e);
                    }
                });
                panel2.add(addProductButton, CC.xy(5, 3));
            }
            panel3.add(panel2, new GridBagConstraints(1, 1, 3, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));

            //---- separator2 ----
            separator2.setOrientation(SwingConstants.VERTICAL);
            panel3.add(separator2, new GridBagConstraints(4, 1, 1, 3, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 0), 0, 0));

            //======== panel4 ========
            {
                panel4.setLayout(new FormLayout(
                    "95dlu",
                    "default"));

                //======== scrollPane1 ========
                {
                    scrollPane1.setViewportView(rdvList);
                }
                panel4.add(scrollPane1, CC.xy(1, 1));
            }
            panel3.add(panel4, new GridBagConstraints(5, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));

            //======== panel1 ========
            {
                panel1.setBorder(new CompoundBorder(
                    new TitledBorder("Carta Cliente"),
                    Borders.DLU2_BORDER));
                panel1.setLayout(new FormLayout(
                    "[116px,min], $lcgap, [344px,min]",
                    "[33dlu,default], $lgap, fill:31dlu"));

                //---- label4 ----
                label4.setText("Codice");
                label4.setFont(new Font("Tahoma", Font.PLAIN, 16));
                label4.setLabelFor(clientCode);
                panel1.add(label4, CC.xy(1, 1));

                //---- clientCode ----
                clientCode.setNextFocusableComponent(insertClientCode);
                panel1.add(clientCode, CC.xy(3, 1, CC.FILL, CC.FILL));

                //---- insertClientCode ----
                insertClientCode.setText("Inserisci");
                insertClientCode.setForeground(Color.black);
                insertClientCode.setNextFocusableComponent(button2);
                insertClientCode.setFont(new Font("Tahoma", Font.PLAIN, 20));
                insertClientCode.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        insertClientCodeActionPerformed(e);
                    }
                });
                panel1.add(insertClientCode, CC.xy(3, 3));
            }
            panel3.add(panel1, new GridBagConstraints(1, 2, 3, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 0, 0));

            //---- clearSale ----
            clearSale.setText("Annulla");
            clearSale.setBackground(new Color(255, 51, 0));
            clearSale.setFont(new Font("Tahoma", Font.PLAIN, 22));
            clearSale.setForeground(Color.white);
            clearSale.setIcon(null);
            clearSale.setNextFocusableComponent(button2);
            clearSale.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    clearSaleActionPerformed(e);
                    clearSaleActionPerformed(e);
                }
            });
            panel3.add(clearSale, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));

            //---- label1 ----
            label1.setText("Totale");
            label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 12f));
            label1.setLabelFor(total);
            panel3.add(label1, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 0), 0, 0));

            //---- total ----
            total.setEditable(false);
            panel3.add(total, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));

            //---- button2 ----
            button2.setText("Avanti");
            button2.setBackground(new Color(102, 204, 0));
            button2.setFont(new Font("Tahoma", Font.PLAIN, 22));
            button2.setName("nextButton");
            button2.setNextFocusableComponent(productIdTextField);
            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    goToPaymentButtonActionPerformed(e);
                }
            });
            panel3.add(button2, new GridBagConstraints(5, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        }
        setViewportView(panel3);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel3;
    private JPanel panel2;
    private JLabel label2;
    private JTextField productIdTextField;
    private JLabel label3;
    private JTextField quantityTextField;
    private JButton addProductButton;
    private JSeparator separator2;
    private JPanel panel4;
    private JScrollPane scrollPane1;
    private JList rdvList;
    private JPanel panel1;
    private JLabel label4;
    private JTextField clientCode;
    private JButton insertClientCode;
    private JButton clearSale;
    private JLabel label1;
    private JTextField total;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
