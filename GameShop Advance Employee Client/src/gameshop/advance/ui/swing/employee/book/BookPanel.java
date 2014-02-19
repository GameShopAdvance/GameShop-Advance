/*
 * Created by JFormDesigner on Tue Feb 18 18:12:16 CET 2014
 */

package gameshop.advance.ui.swing.employee.book;

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.BookControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.technicalservices.LoggerSingleton;
import gameshop.advance.ui.swing.UIWindowSingleton;
import gameshop.advance.utility.Money;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
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

    private void retrieveBookActionPerformed(ActionEvent e) {
        try {
            BookControllerSingleton.getInstance().recuperaPrenotazione(Integer.parseInt(this.bookCode.getText()));
            this.clearFields();
            Money partial = BookControllerSingleton.getInstance().getPartial();
            System.err.println("Partial in panel: "+partial);
            this.partial.setText(partial.toString());
            this.total.setText(BookControllerSingleton.getInstance().getTotal().toString());
        } catch (NullPointerException ex) {
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
        }  
    }
    private void insertClientCodeActionPerformed(ActionEvent e) {
        try{
            Integer code = Integer.parseInt(this.clientCode.getText());
            BookControllerSingleton.getInstance().inserisciCartaCliente(code);
            this.clientCode.setEditable(false);
            this.partial.setText(BookControllerSingleton.getInstance().getPartial().toString());
        } catch (NullPointerException | RemoteException ex) {
             UIWindowSingleton.getInstance().displayError("Non è stato possibile convalidare il codice cliente.");
        } catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError("Ci sono problemi di configurazione. Se il problema persiste contattare l'amministratore di sistema.");
        } catch (NumberFormatException ex){
            UIWindowSingleton.getInstance().displayError("Il codice cliente inserito non è valido o il suo formato non è corretto");
        }
    }
    private void goToPayTotalActionPerformed(ActionEvent e) {
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
    
    private void goToPayPartialActionPerformed(ActionEvent e) {
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

    public void clearFields()
    {
        this.bookCode.setText("");
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

    private void backActionPerformed(ActionEvent e) {
        // TODO add your code here
    }
    

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        clearBook = new JButton();
        panel2 = new JPanel();
        label2 = new JLabel();
        bookCode = new JTextField();
        retrieveBook = new JButton();
        panel1 = new JPanel();
        label4 = new JLabel();
        clientCode = new JTextField();
        insertClientCode = new JButton();
        label1 = new JLabel();
        total = new JTextField();
        label3 = new JLabel();
        partial = new JTextField();
        goToPayTotal = new JButton();
        goToPayPartial = new JButton();

        //======== this ========
        setLayout(new FormLayout(
            "4dlu, $lcgap, 45dlu, $lcgap, 47dlu, $lcgap, 64dlu, $lcgap, 54dlu, $lcgap, 49dlu, $lcgap, 47dlu, $lcgap, 49dlu, $lcgap, 50dlu, $lcgap, 1dlu",
            "7dlu, $lgap, 25dlu, $lgap, 33dlu, $lgap, default, $lgap, 18dlu, $lgap, 29dlu, $lgap, 26dlu"));

        //---- clearBook ----
        clearBook.setText("Annulla");
        clearBook.setBackground(new Color(255, 51, 0));
        clearBook.setFont(clearBook.getFont().deriveFont(clearBook.getFont().getStyle() | Font.BOLD));
        clearBook.setForeground(Color.white);
        clearBook.setIcon(null);
        clearBook.setNextFocusableComponent(null);
        clearBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearBookActionPerformed(e);
            }
        });
        add(clearBook, CC.xywh(3, 3, 3, 2, CC.FILL, CC.FILL));

        //======== panel2 ========
        {
            panel2.setBorder(new CompoundBorder(
                new TitledBorder("Prenotazione"),
                Borders.DLU2_BORDER));
            panel2.setLayout(new FormLayout(
                "79dlu, $lcgap, 53dlu:grow, $lcgap, 49dlu:grow",
                "[20dlu,default], $rgap, [20dlu,default]"));

            //---- label2 ----
            label2.setText("Codice Prenotazione");
            label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 1f));
            label2.setLabelFor(bookCode);
            panel2.add(label2, CC.xy(1, 1));
            panel2.add(bookCode, CC.xywh(3, 1, 3, 1, CC.DEFAULT, CC.FILL));

            //---- retrieveBook ----
            retrieveBook.setText("Aggiungi");
            retrieveBook.setForeground(Color.black);
            retrieveBook.setFont(retrieveBook.getFont().deriveFont(retrieveBook.getFont().getStyle() & ~Font.BOLD));
            retrieveBook.setNextFocusableComponent(clientCode);
            retrieveBook.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    retrieveBookActionPerformed(e);
                }
            });
            panel2.add(retrieveBook, CC.xy(5, 3));
        }
        add(panel2, CC.xywh(7, 3, 7, 3));

        //======== panel1 ========
        {
            panel1.setBorder(new CompoundBorder(
                new TitledBorder("Carta Cliente"),
                Borders.DLU2_BORDER));
            panel1.setLayout(new FormLayout(
                "[67dlu,default], $lcgap, 110dlu",
                "[20dlu,default], $lgap, default"));

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
            insertClientCode.setNextFocusableComponent(goToPayPartial);
            insertClientCode.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    insertClientCodeActionPerformed(e);
                }
            });
            panel1.add(insertClientCode, CC.xy(3, 3));
        }
        add(panel1, CC.xywh(7, 7, 7, 1));

        //---- label1 ----
        label1.setText("Totale");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 3f));
        label1.setLabelFor(total);
        add(label1, CC.xy(3, 11));

        //---- total ----
        total.setEditable(false);
        add(total, CC.xywh(5, 11, 3, 1, CC.DEFAULT, CC.FILL));

        //---- label3 ----
        label3.setText("Acconto");
        label3.setFont(label3.getFont().deriveFont(label3.getFont().getSize() + 4f));
        add(label3, CC.xy(11, 11));

        //---- partial ----
        partial.setEditable(false);
        add(partial, CC.xywh(13, 11, 3, 1, CC.DEFAULT, CC.FILL));

        //---- goToPayTotal ----
        goToPayTotal.setText("Paga Totale");
        goToPayTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToPayTotalActionPerformed(e);
            }
        });
        add(goToPayTotal, CC.xywh(5, 13, 3, 1, CC.DEFAULT, CC.FILL));

        //---- goToPayPartial ----
        goToPayPartial.setText("Paga Acconto");
        goToPayPartial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToPayPartialActionPerformed(e);
            }
        });
        add(goToPayPartial, CC.xywh(13, 13, 3, 1, CC.DEFAULT, CC.FILL));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JButton clearBook;
    private JPanel panel2;
    private JLabel label2;
    private JTextField bookCode;
    private JButton retrieveBook;
    private JPanel panel1;
    private JLabel label4;
    private JTextField clientCode;
    private JButton insertClientCode;
    private JLabel label1;
    private JTextField total;
    private JLabel label3;
    private JTextField partial;
    private JButton goToPayTotal;
    private JButton goToPayPartial;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
