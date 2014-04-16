/*
 * Created by JFormDesigner on Wed Jan 29 18:24:29 CET 2014
 */

package gameshop.advance.ui.swing.employee.sale;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.SaleControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.exceptions.ProdottoNotFoundException;
import gameshop.advance.exceptions.QuantityException;
import gameshop.advance.exceptions.QuantityNotInStockException;
import gameshop.advance.interfaces.remote.IRigaDiTransazioneRemote;
import gameshop.advance.technicalservices.LoggerSingleton;
import gameshop.advance.ui.interfaces.IListPanel;
import gameshop.advance.ui.swing.UIStyleSingleton;
import gameshop.advance.ui.swing.UIWindowSingleton;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

/**
 * @author Matteo Gentile
 */

public class InsertItemPanel extends JPanel implements IListPanel{
    private DefaultListModel<IRigaDiTransazioneRemote> rdvListModel;

    public InsertItemPanel() {
        initComponents();
        this.clearSale.setBackground(UIStyleSingleton.getInstance().getAlertColor());
        this.clearSale.setForeground(UIStyleSingleton.getInstance().getButtonTextColor());
        this.payButton.setBackground(UIStyleSingleton.getInstance().getSuccessColor());
        this.payButton.setForeground(UIStyleSingleton.getInstance().getButtonTextColor());    
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
                if(!this.quantityTextField.getText().equals("")) {
                    UIWindowSingleton.getInstance().displayError("Il formato di dato inserito per la quantità non è valido");
                }
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
            CardLayout layout = (CardLayout) this.clientPanel.getLayout();
            layout.next(this.clientPanel);
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
             LoggerSingleton.getInstance().log(ex);
        } catch (RemoteException ex) {
            UIWindowSingleton.getInstance().displayError("Non è possibile contattare il server. "
                    + "Si prega di riprovare. Se il problema persiste, contattare l'amministratore di sistema.");
            LoggerSingleton.getInstance().log(ex);
        } catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError("Ci sono problemi di configurazione. Se il problema persiste contattare l'amministratore di sistema.");
            LoggerSingleton.getInstance().log(ex);
        }
        
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        label1 = new JLabel();
        productIdTextField = new JTextField();
        label2 = new JLabel();
        quantityTextField = new JTextField();
        button1 = new JButton();
        panel4 = new JPanel();
        label3 = new JLabel();
        total = new JTextField();
        scrollPane1 = new JScrollPane();
        rdvList = new JList();
        clientPanel = new JPanel();
        main = new JPanel();
        label4 = new JLabel();
        clientCode = new JTextField();
        button7 = new JButton();
        panel5 = new JPanel();
        label5 = new JLabel();
        panel3 = new JPanel();
        clearSale = new JButton();
        payButton = new JButton();

        //======== this ========
        setComponentPopupMenu(null);
        setLayout(new FormLayout(
            "[15dlu,default]:grow, $lcgap, [150dlu,default], $lcgap, [100dlu,default]:grow, $lcgap, [75dlu,default], $lcgap, [15dlu,default]:grow",
            "fill:[15dlu,default]:grow, $rgap, default, $lgap, 90dlu, $lgap, [35dlu,default], $lgap, [15dlu,default]:grow"));

        //======== panel1 ========
        {
            panel1.setBorder(new TitledBorder("Inserisci prodotto"));
            panel1.setLayout(new FormLayout(
                "70dlu, $lcgap, 80dlu:grow, $lcgap, [75dlu,default]",
                "[35dlu,default], $lgap, [35dlu,default]"));

            //---- label1 ----
            label1.setText("Codice");
            label1.setFont(new Font("Tahoma", Font.PLAIN, 14));
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            panel1.add(label1, CC.xy(1, 1, CC.FILL, CC.FILL));

            //---- productIdTextField ----
            productIdTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
            panel1.add(productIdTextField, CC.xywh(3, 1, 3, 1, CC.FILL, CC.FILL));

            //---- label2 ----
            label2.setText("Quantit\u00e0");
            label2.setFont(new Font("Tahoma", Font.PLAIN, 14));
            label2.setHorizontalAlignment(SwingConstants.CENTER);
            panel1.add(label2, CC.xy(1, 3, CC.FILL, CC.FILL));

            //---- quantityTextField ----
            quantityTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
            panel1.add(quantityTextField, CC.xy(3, 3, CC.FILL, CC.FILL));

            //---- button1 ----
            button1.setText("Aggiungi");
            button1.setFont(new Font("Tahoma", Font.PLAIN, 14));
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addProductButtonActionPerformed(e);
                }
            });
            panel1.add(button1, CC.xy(5, 3, CC.FILL, CC.FILL));
        }
        add(panel1, CC.xy(3, 3));

        //======== panel4 ========
        {
            panel4.setLayout(new FormLayout(
                "[10dlu,default]:grow, $lcgap, [50dlu,default], $lcgap, [90dlu,default], $lcgap, $rgap",
                "[25dlu,default], $lgap, default:grow"));

            //---- label3 ----
            label3.setText("Totale");
            label3.setFont(new Font("Tahoma", Font.PLAIN, 14));
            label3.setHorizontalAlignment(SwingConstants.CENTER);
            panel4.add(label3, CC.xy(3, 1, CC.FILL, CC.FILL));

            //---- total ----
            total.setFont(new Font("Tahoma", Font.PLAIN, 14));
            total.setEditable(false);
            panel4.add(total, CC.xy(5, 1, CC.FILL, CC.FILL));

            //======== scrollPane1 ========
            {

                //---- rdvList ----
                rdvList.setFont(new Font("Tahoma", Font.PLAIN, 14));
                scrollPane1.setViewportView(rdvList);
            }
            panel4.add(scrollPane1, CC.xywh(1, 3, 5, 1, CC.FILL, CC.FILL));
        }
        add(panel4, CC.xywh(5, 3, 4, 3));

        //======== clientPanel ========
        {
            clientPanel.setBorder(new TitledBorder("Carta fedelt\u00e0"));
            clientPanel.setLayout(new CardLayout());

            //======== main ========
            {
                main.setLayout(new FormLayout(
                    "[85dlu,default], $lcgap, default:grow, $lcgap, [75dlu,default]",
                    "[35dlu,default], $lgap, [35dlu,default]"));

                //---- label4 ----
                label4.setText("Numero tessera");
                label4.setFont(new Font("Tahoma", Font.PLAIN, 14));
                label4.setHorizontalAlignment(SwingConstants.CENTER);
                main.add(label4, CC.xy(1, 1));
                main.add(clientCode, CC.xywh(3, 1, 3, 1, CC.FILL, CC.FILL));

                //---- button7 ----
                button7.setText("Inserisci");
                button7.setFont(new Font("Tahoma", Font.PLAIN, 14));
                button7.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        insertClientCodeActionPerformed(e);
                    }
                });
                main.add(button7, CC.xy(5, 3, CC.FILL, CC.FILL));
            }
            clientPanel.add(main, "card1");

            //======== panel5 ========
            {
                panel5.setLayout(new FormLayout(
                    "[15dlu,default], $lcgap, [75dlu,default]:grow, $lcgap, [15dlu,default]",
                    "[15dlu,default], $lgap, default:grow, $lgap, [15dlu,default]"));

                //---- label5 ----
                label5.setText("Cliente autenticato.");
                label5.setFont(new Font("Tahoma", Font.PLAIN, 14));
                label5.setHorizontalAlignment(SwingConstants.CENTER);
                panel5.add(label5, CC.xy(3, 3, CC.FILL, CC.FILL));
            }
            clientPanel.add(panel5, "card2");
        }
        add(clientPanel, CC.xy(3, 5, CC.FILL, CC.FILL));

        //======== panel3 ========
        {
            panel3.setLayout(new FormLayout(
                "2*([75dlu,default], $lcgap), default",
                "fill:default:grow"));

            //---- clearSale ----
            clearSale.setText("Annulla");
            clearSale.setFont(new Font("Tahoma", Font.PLAIN, 14));
            clearSale.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    clearSaleActionPerformed(e);
                }
            });
            panel3.add(clearSale, CC.xy(1, 1));
        }
        add(panel3, CC.xy(3, 7, CC.FILL, CC.FILL));

        //---- payButton ----
        payButton.setText("Paga");
        payButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToPaymentButtonActionPerformed(e);
            }
        });
        add(payButton, CC.xy(7, 7, CC.FILL, CC.FILL));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JLabel label1;
    private JTextField productIdTextField;
    private JLabel label2;
    private JTextField quantityTextField;
    private JButton button1;
    private JPanel panel4;
    private JLabel label3;
    private JTextField total;
    private JScrollPane scrollPane1;
    private JList rdvList;
    private JPanel clientPanel;
    private JPanel main;
    private JLabel label4;
    private JTextField clientCode;
    private JButton button7;
    private JPanel panel5;
    private JLabel label5;
    private JPanel panel3;
    private JButton clearSale;
    private JButton payButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public void setList(ListModel listaProdotti, ListCellRenderer renderer)
    {
        this.rdvList.setCellRenderer(renderer);
        this.rdvList.setModel(listaProdotti);
    }
}
