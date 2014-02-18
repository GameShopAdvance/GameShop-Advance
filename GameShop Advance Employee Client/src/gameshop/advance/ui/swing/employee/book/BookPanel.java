/*
 * Created by JFormDesigner on Wed Feb 12 11:01:23 CET 2014
 */

package gameshop.advance.ui.swing.employee.book;

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.BookControllerSingleton;
import gameshop.advance.controller.SaleControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.ui.swing.UIWindowSingleton;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

/**
 * @author Franco
 */
public class BookPanel extends JPanel {
    public BookPanel() {
        initComponents();
    }

    private void retrieveBookButtonActionPerformed(ActionEvent e) {
        
      try {
            Integer code = 1;
            try{
                 code = Integer.parseInt(this.bookCodeField.getText());
            } catch(NumberFormatException ex){
                if(!this.bookCodeField.getText().equals(""))
                    UIWindowSingleton.getInstance().displayError("Il formato di dato inserito per la quantità non è valido");
            }
            BookControllerSingleton.getInstance().recuperaPrenotazione(Integer.parseInt(this.bookCodeField.getText()));
         
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

    private void clearBookActionPerformed(ActionEvent e) {
        try{
            BookControllerSingleton.getInstance().clearBook();
        } catch (NullPointerException ex) {
             UIWindowSingleton.getInstance().displayError("Non è stato possibile convalidare il codice cliente.");       
        } catch (RemoteException ex) {
            UIWindowSingleton.getInstance().displayError("Non è possibile contattare il server. "
                    + "Si prega di riprovare. Se il problema persiste, contattare l'amministratore di sistema.");
        } catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError("Ci sono problemi di configurazione. Se il problema persiste contattare l'amministratore di sistema.");
        }
    }

    private void payPartialActionPerformed(ActionEvent e) {
        try{
            BookControllerSingleton.getInstance().mostraPagaAcconto();
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

    private void payTotalActionPerformed(ActionEvent e) {
        try {
            BookControllerSingleton.getInstance().mostraPagaTotale();
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

    
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel3 = new JPanel();
        clearBook = new JButton();
        panel2 = new JPanel();
        label2 = new JLabel();
        bookCodeField = new JTextField();
        retrieveBookButton = new JButton();
        panel1 = new JPanel();
        label4 = new JLabel();
        clientCode = new JTextField();
        insertClientCode = new JButton();
        separator2 = new JSeparator();
        label1 = new JLabel();
        total = new JTextField();
        label3 = new JLabel();
        partial = new JTextField();
        payTotal = new JButton();
        payPartial = new JButton();

        //======== panel3 ========
        {
            panel3.setLayout(new FormLayout(
                "5dlu, 65dlu, [7dlu,default]:grow, 59dlu, $lcgap, 61dlu, $lcgap, 69dlu, [47dlu,default]:grow, 23dlu, 5dlu",
                "5dlu, 23dlu, $lgap, fill:[43dlu,default], $lgap, [20dlu,default], fill:[36dlu,default,40dlu], 2dlu, 17dlu, 29dlu, $lgap, fill:19dlu:grow, $lgap, 1dlu"));

            //---- clearBook ----
            clearBook.setText("Annulla");
            clearBook.setBackground(new Color(255, 51, 0));
            clearBook.setFont(clearBook.getFont().deriveFont(clearBook.getFont().getStyle() | Font.BOLD));
            clearBook.setForeground(Color.white);
            clearBook.setIcon(null);
            clearBook.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    clearBookActionPerformed(e);
                }
            });
            panel3.add(clearBook, CC.xy(2, 2, CC.FILL, CC.FILL));

            //======== panel2 ========
            {
                panel2.setBorder(new CompoundBorder(
                    new TitledBorder("Prenotazione"),
                    Borders.DLU2_BORDER));
                panel2.setLayout(new FormLayout(
                    "65dlu, $lcgap, 53dlu:grow, $lcgap, 49dlu:grow",
                    "[20dlu,default], $rgap, [20dlu,default]"));

                //---- label2 ----
                label2.setText("Codice ");
                label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 1f));
                label2.setLabelFor(bookCodeField);
                panel2.add(label2, CC.xy(1, 1));

                //---- bookCodeField ----
                bookCodeField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
                panel2.add(bookCodeField, CC.xywh(3, 1, 3, 1, CC.DEFAULT, CC.FILL));

                //---- retrieveBookButton ----
                retrieveBookButton.setText("Cerca Prenotazione");
                retrieveBookButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        retrieveBookButtonActionPerformed(e);
                    }
                });
                panel2.add(retrieveBookButton, CC.xywh(3, 3, 3, 1));
            }
            panel3.add(panel2, CC.xywh(4, 4, 5, 1));

            //======== panel1 ========
            {
                panel1.setBorder(new CompoundBorder(
                    new TitledBorder("Carta Cliente"),
                    Borders.DLU2_BORDER));
                panel1.setLayout(new FormLayout(
                    "[67dlu,default], $lcgap, 103dlu",
                    "[20dlu,default], $lgap, 13dlu"));

                //---- label4 ----
                label4.setText("Codice");
                label4.setFont(label4.getFont().deriveFont(label4.getFont().getSize() + 1f));
                label4.setLabelFor(clientCode);
                panel1.add(label4, CC.xy(1, 1));

                //---- clientCode ----
                clientCode.setNextFocusableComponent(insertClientCode);
                panel1.add(clientCode, CC.xy(3, 1, CC.FILL, CC.FILL));

                //---- insertClientCode ----
                insertClientCode.setText("Inserisci");
                insertClientCode.setForeground(Color.black);
                insertClientCode.setFont(insertClientCode.getFont().deriveFont(insertClientCode.getFont().getStyle() & ~Font.BOLD));
                insertClientCode.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        insertClientCodeActionPerformed(e);
                    }
                });
                panel1.add(insertClientCode, CC.xy(3, 3));
            }
            panel3.add(panel1, CC.xywh(4, 6, 5, 2));
            panel3.add(separator2, CC.xywh(2, 9, 9, 1));

            //---- label1 ----
            label1.setText("Totale");
            label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 1f));
            label1.setLabelFor(total);
            panel3.add(label1, CC.xy(2, 10));

            //---- total ----
            total.setEditable(false);
            panel3.add(total, CC.xywh(3, 10, 2, 1, CC.DEFAULT, CC.FILL));

            //---- label3 ----
            label3.setText("Acconto");
            label3.setFont(label3.getFont().deriveFont(label3.getFont().getSize() + 1f));
            label3.setLabelFor(total);
            panel3.add(label3, CC.xy(8, 10));

            //---- partial ----
            partial.setEditable(false);
            panel3.add(partial, CC.xywh(9, 10, 2, 1, CC.DEFAULT, CC.FILL));

            //---- payTotal ----
            payTotal.setText("Paga Totale");
            payTotal.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    payTotalActionPerformed(e);
                }
            });
            panel3.add(payTotal, CC.xywh(3, 12, 2, 1));

            //---- payPartial ----
            payPartial.setText("Paga Acconto");
            payPartial.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    payPartialActionPerformed(e);
                }
            });
            panel3.add(payPartial, CC.xywh(9, 12, 2, 1));
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel3;
    private JButton clearBook;
    private JPanel panel2;
    private JLabel label2;
    private JTextField bookCodeField;
    private JButton retrieveBookButton;
    private JPanel panel1;
    private JLabel label4;
    private JTextField clientCode;
    private JButton insertClientCode;
    private JSeparator separator2;
    private JLabel label1;
    private JTextField total;
    private JLabel label3;
    private JTextField partial;
    private JButton payTotal;
    private JButton payPartial;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
