/*
 * Created by JFormDesigner on Wed Jan 29 18:24:29 CET 2014
 */

package gameshop.advance.ui.swing.employee.sale;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.SaleControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.exceptions.QuantityException;
import gameshop.advance.exceptions.products.ProdottoNotFoundException;
import gameshop.advance.exceptions.products.QuantityNotInStockException;
import gameshop.advance.interfaces.IListPanel;
import gameshop.advance.technicalservices.ExceptionHandlerSingleton;
import gameshop.advance.technicalservices.LoggerSingleton;
import gameshop.advance.ui.swing.UIWindowSingleton;
import gameshop.advance.ui.swing.factory.UIFactory;
import gameshop.advance.ui.swing.utility.UIStyleSingleton;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
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
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        } catch (RemoteException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        } catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
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
                    UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
                    LoggerSingleton.getInstance().log(ex);
                }
            }
            SaleControllerSingleton.getInstance().inserisciProdotto(this.productIdTextField.getText(), quantity);
            this.clearFields();
            this.total.setText(SaleControllerSingleton.getInstance().getTotal().toString());
        }
        catch (NullPointerException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        } catch (RemoteException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        } catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        } catch (ProdottoNotFoundException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }  catch (QuantityException ex){
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }
        catch (QuantityNotInStockException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
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
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        } catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        } catch (RemoteException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        } catch (NumberFormatException ex){
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }
    }

    private void clearSaleActionPerformed(ActionEvent e) {
        try{
            SaleControllerSingleton.getInstance().clearSale();
        } catch (NullPointerException ex) {
             UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        } catch (RemoteException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        } catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }
        
    }

    private void createUIComponents() {
       this.button1 = UIFactory.getInstance().getSimpleButton();
       this.button7 = UIFactory.getInstance().getSimpleButton();
       this.clearSale = UIFactory.getInstance().getCancelButton();
       this.payButton = UIFactory.getInstance().getConfirmButton();
       this.label1 = UIFactory.getInstance().getBodyLabel();
       this.label2 = UIFactory.getInstance().getBodyLabel();
       this.label3 = UIFactory.getInstance().getBodyLabel();
       this.label4 = UIFactory.getInstance().getBodyLabel();
       this.label5 = UIFactory.getInstance().getBodyLabel();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        createUIComponents();

        panel1 = new JPanel();
        productIdTextField = new JTextField();
        quantityTextField = new JTextField();
        panel4 = new JPanel();
        total = new JTextField();
        scrollPane1 = new JScrollPane();
        rdvList = new JList();
        clientPanel = new JPanel();
        main = new JPanel();
        clientCode = new JTextField();
        panel5 = new JPanel();
        label5 = new JLabel();
        panel3 = new JPanel();

        //======== this ========
        setComponentPopupMenu(null);
        setName("this");

        //======== panel1 ========
        {
            panel1.setBorder(new TitledBorder("Inserisci prodotto"));
            panel1.setName("panel1");

            //---- label1 ----
            label1.setText("Codice");
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            label1.setName("label1");

            //---- productIdTextField ----
            productIdTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
            productIdTextField.setName("productIdTextField");

            //---- label2 ----
            label2.setText("Quantit\u00e0");
            label2.setHorizontalAlignment(SwingConstants.CENTER);
            label2.setName("label2");

            //---- quantityTextField ----
            quantityTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
            quantityTextField.setName("quantityTextField");

            //---- button1 ----
            button1.setText("Aggiungi");
            button1.setName("button1");
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addProductButtonActionPerformed(e);
                }
            });

            PanelBuilder panel1Builder = new PanelBuilder(new FormLayout(
                "70dlu, $lcgap, 80dlu:grow, $lcgap, [75dlu,default]",
                "[35dlu,default], $lgap, [35dlu,default]"), panel1);

            panel1Builder.add(label1,             CC.xy  (1, 1, CC.FILL, CC.FILL));
            panel1Builder.add(productIdTextField, CC.xywh(3, 1,       3,       1, CC.FILL, CC.FILL));
            panel1Builder.add(label2,             CC.xy  (1, 3, CC.FILL, CC.FILL));
            panel1Builder.add(quantityTextField,  CC.xy  (3, 3, CC.FILL, CC.FILL));
            panel1Builder.add(button1,            CC.xy  (5, 3, CC.FILL, CC.FILL));
        }

        //======== panel4 ========
        {
            panel4.setName("panel4");

            //---- label3 ----
            label3.setText("Totale");
            label3.setHorizontalAlignment(SwingConstants.CENTER);
            label3.setName("label3");

            //---- total ----
            total.setFont(new Font("Tahoma", Font.PLAIN, 14));
            total.setEditable(false);
            total.setName("total");

            //======== scrollPane1 ========
            {
                scrollPane1.setName("scrollPane1");

                //---- rdvList ----
                rdvList.setFont(new Font("Tahoma", Font.PLAIN, 14));
                rdvList.setName("rdvList");
                scrollPane1.setViewportView(rdvList);
            }

            PanelBuilder panel4Builder = new PanelBuilder(new FormLayout(
                "[10dlu,default]:grow, $lcgap, [50dlu,default], $lcgap, [90dlu,default], $lcgap, $rgap",
                "[25dlu,default], $lgap, default:grow"), panel4);

            panel4Builder.add(label3,      CC.xy  (3, 1, CC.FILL, CC.FILL));
            panel4Builder.add(total,       CC.xy  (5, 1, CC.FILL, CC.FILL));
            panel4Builder.add(scrollPane1, CC.xywh(1, 3,       5,       1, CC.FILL, CC.FILL));
        }

        //======== clientPanel ========
        {
            clientPanel.setBorder(new TitledBorder("Carta fedelt\u00e0"));
            clientPanel.setName("clientPanel");
            clientPanel.setLayout(new CardLayout());

            //======== main ========
            {
                main.setName("main");

                //---- label4 ----
                label4.setText("Numero tessera");
                label4.setHorizontalAlignment(SwingConstants.CENTER);
                label4.setName("label4");

                //---- clientCode ----
                clientCode.setName("clientCode");

                //---- button7 ----
                button7.setText("Inserisci");
                button7.setName("button7");
                button7.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        insertClientCodeActionPerformed(e);
                    }
                });

                PanelBuilder mainBuilder = new PanelBuilder(new FormLayout(
                    "70dlu, $lcgap, default:grow, $lcgap, [75dlu,default]",
                    "[35dlu,default], $lgap, [35dlu,default]"), main);

                mainBuilder.add(label4,     CC.xy  (1, 1));
                mainBuilder.add(clientCode, CC.xywh(3, 1,       3,       1, CC.FILL, CC.FILL));
                mainBuilder.add(button7,    CC.xy  (5, 3, CC.FILL, CC.FILL));
            }
            clientPanel.add(main, "card1");

            //======== panel5 ========
            {
                panel5.setName("panel5");

                //---- label5 ----
                label5.setText("Cliente autenticato.");
                label5.setFont(new Font("Tahoma", Font.PLAIN, 14));
                label5.setHorizontalAlignment(SwingConstants.CENTER);
                label5.setName("label5");

                PanelBuilder panel5Builder = new PanelBuilder(new FormLayout(
                    "[15dlu,default], $lcgap, [75dlu,default]:grow, $lcgap, [15dlu,default]",
                    "[15dlu,default], $lgap, default:grow, $lgap, [15dlu,default]"), panel5);

                panel5Builder.add(label5, CC.xy(3, 3, CC.FILL, CC.FILL));
            }
            clientPanel.add(panel5, "card2");
        }

        //======== panel3 ========
        {
            panel3.setName("panel3");

            //---- clearSale ----
            clearSale.setText("Annulla");
            clearSale.setName("clearSale");
            clearSale.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    clearSaleActionPerformed(e);
                }
            });

            PanelBuilder panel3Builder = new PanelBuilder(new FormLayout(
                "2*([75dlu,default], $lcgap), default",
                "fill:default:grow"), panel3);

            panel3Builder.add(clearSale, CC.xy(1, 1));
        }

        //---- payButton ----
        payButton.setText("Paga");
        payButton.setName("payButton");
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToPaymentButtonActionPerformed(e);
            }
        });

        PanelBuilder builder = new PanelBuilder(new FormLayout(
            "[15dlu,default]:grow, $lcgap, [150dlu,default], $lcgap, [100dlu,default]:grow, $lcgap, [75dlu,default], $lcgap, [15dlu,default]:grow",
            "fill:[15dlu,default]:grow, $rgap, default, $lgap, 90dlu, $lgap, [35dlu,default], $lgap, [15dlu,default]:grow"), this);

        builder.add(panel1,      CC.xy  (3, 3));
        builder.add(panel4,      CC.xywh(5, 3,       4,       3));
        builder.add(clientPanel, CC.xy  (3, 5, CC.FILL, CC.FILL));
        builder.add(panel3,      CC.xy  (3, 7, CC.FILL, CC.FILL));
        builder.add(payButton,   CC.xy  (7, 7, CC.FILL, CC.FILL));
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

    @Override
    public void setList(ListModel listaProdotti, ListCellRenderer renderer)
    {
        this.rdvList.setCellRenderer(renderer);
        this.rdvList.setModel(listaProdotti);
    }
}
