/*
 * Created by JFormDesigner on Tue Feb 18 18:12:16 CET 2014
 */

package gameshop.advance.ui.swing.employee.book;

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.BookControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.technicalservices.LoggerSingleton;
import gameshop.advance.ui.swing.UIWindowSingleton;
import gameshop.advance.utility.Money;
import java.awt.CardLayout;
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
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

/**
 * @author Franco
 */
public class BookPanel extends JPanel {

    public BookPanel() {
        initComponents();
        CardLayout layout = (CardLayout) this.mainPanel.getLayout();
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
            CardLayout layout = (CardLayout) this.mainPanel.getLayout();
            this.displayTotal.setText(BookControllerSingleton.getInstance().getTotal().toString());
            layout.last(this.mainPanel);
        } catch (NullPointerException ex) {
            UIWindowSingleton.getInstance().displayError("Errore di sistema. "
                    + "Se il problema persiste, contattare l'amministratore di sistema.");
        } catch (RemoteException ex) {
            UIWindowSingleton.getInstance().displayError("Non è possibile contattare il server. "
                    + "Si prega di riprovare. Se il problema persiste, contattare l'amministratore di sistema.");
        } catch (ConfigurationException ex) {
             UIWindowSingleton.getInstance().displayError("Ci sono problemi nella lettura del file di configurazione: "+ex.getConfigurationPath()+"."
                   + " Per maggiori informazioni rivolgersi all'amministratore di sistema.");
        }
    }
    
    private void goToPayPartialActionPerformed(ActionEvent e) {
        try {
            CardLayout layout = (CardLayout) this.mainPanel.getLayout();
            this.displayPartial.setText(BookControllerSingleton.getInstance().getPartial().toString());
            layout.next(this.mainPanel);
        } catch (NullPointerException ex) {
            UIWindowSingleton.getInstance().displayError("Errore di sistema. "
                    + "Si prega di riprovare. Se il problema persiste, contattare l'amministratore di sistema.");
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
            UIWindowSingleton.getInstance().displayError("Ci sono problemi nella lettura del file di configurazione: "+ex.getConfigurationPath()+"."
                   + " Per maggiori informazioni rivolgersi all'amministratore di sistema.");
        }
    }

    private void indietroActionPerformed(ActionEvent e) {
            CardLayout layout = (CardLayout) this.mainPanel.getLayout();
            layout.first(this.mainPanel);
    }

    private void payPartialButtonActionPerformed(ActionEvent e) {
        try {
            BookControllerSingleton.getInstance().pagaAcconto(Double.parseDouble(this.partialPayment.getText()));
        } catch (NullPointerException ex) {
             UIWindowSingleton.getInstance().displayError("Non è possibile convalidare l'importo inserito.");   
        } catch (RemoteException ex) {
            UIWindowSingleton.getInstance().displayError("Non è possibile contattare il server. "
                    + "Si prega di riprovare. Se il problema persiste, contattare l'amministratore di sistema.");
        } catch (ConfigurationException ex) {
             UIWindowSingleton.getInstance().displayError("Ci sono problemi nella lettura del file di configurazione: "+ex.getConfigurationPath()+"."
                   + " Per maggiori informazioni rivolgersi all'amministratore di sistema.");
        } catch (InvalidMoneyException ex) {
            Logger.getLogger(BookPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void payTotalButtonActionPerformed(ActionEvent e) {
        try {
            BookControllerSingleton.getInstance().gestisciPagamento(Double.parseDouble(this.totalPayment.getText()));
        } catch (NullPointerException ex) {
             UIWindowSingleton.getInstance().displayError("Non è possibile convalidare l'importo inserito.");   
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
        mainPanel = new JPanel();
        getReservationCard = new JPanel();
        panel2 = new JPanel();
        label2 = new JLabel();
        bookCode = new JTextField();
        retrieveBook = new JButton();
        panel1 = new JPanel();
        label4 = new JLabel();
        clientCode = new JTextField();
        insertClientCode = new JButton();
        panel5 = new JPanel();
        label1 = new JLabel();
        label3 = new JLabel();
        clearBook = new JButton();
        total = new JTextField();
        partial = new JTextField();
        goToPayTotal = new JButton();
        goToPayPartial = new JButton();
        payPartialCard = new JPanel();
        panel4 = new JPanel();
        button1 = new JButton();
        panel3 = new JPanel();
        label5 = new JLabel();
        displayPartial = new JTextField();
        label6 = new JLabel();
        partialPayment = new JTextField();
        payPartialButton = new JButton();
        payTotalCard = new JPanel();
        panel6 = new JPanel();
        button2 = new JButton();
        panel8 = new JPanel();
        label7 = new JLabel();
        displayTotal = new JTextField();
        label8 = new JLabel();
        totalPayment = new JTextField();
        payTotalButton = new JButton();

        //======== this ========
        setLayout(new FormLayout(
            "default:grow",
            "fill:default:grow"));

        //======== mainPanel ========
        {
            mainPanel.setLayout(new CardLayout());

            //======== getReservationCard ========
            {
                getReservationCard.setLayout(new FormLayout(
                    "default, $lcgap, default:grow, $lcgap, default",
                    "3*(default:grow, $lgap), default"));

                //======== panel2 ========
                {
                    panel2.setBorder(new CompoundBorder(
                        new TitledBorder("Prenotazione"),
                        Borders.DLU2_BORDER));
                    panel2.setLayout(new FormLayout(
                        "center:[190px,min]:grow, $lcgap, [184px,min]:grow, $lcgap, [140px,min]:grow",
                        "[50px,min], $rgap, fill:[40px,min]"));

                    //---- label2 ----
                    label2.setText("Codice Prenotazione");
                    label2.setFont(new Font("Tahoma", Font.PLAIN, 18));
                    label2.setLabelFor(bookCode);
                    panel2.add(label2, CC.xy(1, 1, CC.LEFT, CC.DEFAULT));
                    panel2.add(bookCode, CC.xywh(3, 1, 3, 1, CC.FILL, CC.FILL));

                    //---- retrieveBook ----
                    retrieveBook.setText("Aggiungi");
                    retrieveBook.setForeground(Color.black);
                    retrieveBook.setFont(new Font("Tahoma", Font.PLAIN, 20));
                    retrieveBook.setNextFocusableComponent(clientCode);
                    retrieveBook.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            retrieveBookActionPerformed(e);
                        }
                    });
                    panel2.add(retrieveBook, CC.xy(5, 3));
                }
                getReservationCard.add(panel2, CC.xy(3, 1));

                //======== panel1 ========
                {
                    panel1.setBorder(new CompoundBorder(
                        new TitledBorder("Carta Cliente"),
                        Borders.DLU2_BORDER));
                    panel1.setFont(new Font("Tahoma", Font.PLAIN, 20));
                    panel1.setLayout(new FormLayout(
                        "left:[190px,min]:grow, $lcgap, [184px,min]:grow, $lcgap, [140px,min]:grow",
                        "[50px,min], $lgap, fill:[40px,min]"));

                    //---- label4 ----
                    label4.setText("Codice");
                    label4.setFont(new Font("Tahoma", Font.PLAIN, 18));
                    label4.setLabelFor(clientCode);
                    panel1.add(label4, CC.xy(1, 1));

                    //---- clientCode ----
                    clientCode.setNextFocusableComponent(insertClientCode);
                    panel1.add(clientCode, CC.xywh(3, 1, 3, 1, CC.FILL, CC.FILL));

                    //---- insertClientCode ----
                    insertClientCode.setText("Inserisci");
                    insertClientCode.setForeground(Color.black);
                    insertClientCode.setFont(new Font("Tahoma", Font.PLAIN, 20));
                    insertClientCode.setNextFocusableComponent(goToPayPartial);
                    insertClientCode.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            insertClientCodeActionPerformed(e);
                        }
                    });
                    panel1.add(insertClientCode, CC.xy(5, 3));
                }
                getReservationCard.add(panel1, CC.xy(3, 3));

                //======== panel5 ========
                {
                    panel5.setLayout(new FormLayout(
                        "91dlu:grow, 2*($lcgap, default), $lcgap, center:default:grow, $lcgap, default, $lcgap, center:default:grow",
                        "default, $lgap, 23dlu, 2*($lgap, default)"));

                    //---- label1 ----
                    label1.setText("Totale");
                    label1.setFont(new Font("Tahoma", Font.PLAIN, 20));
                    label1.setLabelFor(total);
                    panel5.add(label1, CC.xy(7, 1));

                    //---- label3 ----
                    label3.setText("Acconto");
                    label3.setFont(new Font("Tahoma", Font.PLAIN, 20));
                    panel5.add(label3, CC.xy(11, 1));

                    //---- clearBook ----
                    clearBook.setText("Annulla");
                    clearBook.setBackground(new Color(255, 51, 0));
                    clearBook.setFont(new Font("Tahoma", Font.PLAIN, 22));
                    clearBook.setForeground(Color.white);
                    clearBook.setIcon(null);
                    clearBook.setNextFocusableComponent(null);
                    clearBook.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            clearBookActionPerformed(e);
                        }
                    });
                    panel5.add(clearBook, CC.xy(1, 3));

                    //---- total ----
                    total.setEditable(false);
                    panel5.add(total, CC.xy(7, 3, CC.FILL, CC.FILL));

                    //---- partial ----
                    partial.setEditable(false);
                    panel5.add(partial, CC.xy(11, 3, CC.FILL, CC.FILL));

                    //---- goToPayTotal ----
                    goToPayTotal.setText("Paga Totale");
                    goToPayTotal.setFont(new Font("Tahoma", Font.PLAIN, 22));
                    goToPayTotal.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            goToPayTotalActionPerformed(e);
                        }
                    });
                    panel5.add(goToPayTotal, CC.xy(7, 5, CC.FILL, CC.DEFAULT));

                    //---- goToPayPartial ----
                    goToPayPartial.setText("Paga Acconto");
                    goToPayPartial.setFont(new Font("Tahoma", Font.PLAIN, 22));
                    goToPayPartial.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            goToPayPartialActionPerformed(e);
                        }
                    });
                    panel5.add(goToPayPartial, CC.xy(11, 5, CC.FILL, CC.DEFAULT));
                }
                getReservationCard.add(panel5, CC.xy(3, 5));
            }
            mainPanel.add(getReservationCard, "card1");

            //======== payPartialCard ========
            {
                payPartialCard.setLayout(new FormLayout(
                    "default, $lcgap, 159dlu:grow, $lcgap, default:grow, $lcgap, default",
                    "default, $lgap, default:grow, $lgap, 21dlu"));

                //======== panel4 ========
                {
                    panel4.setLayout(new FormLayout(
                        "[50px,min], $lcgap, [150px,min], $lcgap, [50px,min]",
                        "default, $lgap, [40px,min], $lgap, default"));

                    //---- button1 ----
                    button1.setText("Indietro");
                    button1.setFont(new Font("Ubuntu", Font.PLAIN, 22));
                    button1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            indietroActionPerformed(e);
                        }
                    });
                    panel4.add(button1, CC.xy(3, 3, CC.DEFAULT, CC.FILL));
                }
                payPartialCard.add(panel4, CC.xy(3, 3));

                //======== panel3 ========
                {
                    panel3.setBorder(new TitledBorder("Paga Acconto"));
                    panel3.setLayout(new FormLayout(
                        "5dlu, $lcgap, [100px,min], $lcgap, [191px,min]",
                        "2*(fill:[50px,min], $lgap), fill:[50px,min], 10dlu"));

                    //---- label5 ----
                    label5.setText("Acconto");
                    label5.setFont(new Font("Tahoma", Font.PLAIN, 18));
                    panel3.add(label5, CC.xy(3, 1));

                    //---- displayPartial ----
                    displayPartial.setEditable(false);
                    panel3.add(displayPartial, CC.xy(5, 1));

                    //---- label6 ----
                    label6.setText("Pagamento");
                    label6.setFont(new Font("Tahoma", Font.PLAIN, 18));
                    panel3.add(label6, CC.xy(3, 3));
                    panel3.add(partialPayment, CC.xy(5, 3));

                    //---- payPartialButton ----
                    payPartialButton.setText("Paga Acconto");
                    payPartialButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
                    payPartialButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            payPartialButtonActionPerformed(e);
                            payPartialButtonActionPerformed(e);
                            payPartialButtonActionPerformed(e);
                        }
                    });
                    panel3.add(payPartialButton, CC.xy(5, 5));
                }
                payPartialCard.add(panel3, CC.xy(5, 3));
            }
            mainPanel.add(payPartialCard, "card2");

            //======== payTotalCard ========
            {
                payTotalCard.setLayout(new FormLayout(
                    "default, 163dlu, default:grow, default",
                    "default, $lgap, default:grow, $lgap, default"));

                //======== panel6 ========
                {
                    panel6.setLayout(new FormLayout(
                        "[50px,min], $lcgap, [150px,min], $lcgap, [50px,min]",
                        "default, $lgap, [40px,min], $lgap, default"));

                    //---- button2 ----
                    button2.setText("Indietro");
                    button2.setFont(new Font("Ubuntu", Font.PLAIN, 22));
                    button2.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            indietroActionPerformed(e);
                        }
                    });
                    panel6.add(button2, CC.xy(3, 3));
                }
                payTotalCard.add(panel6, CC.xy(2, 3));

                //======== panel8 ========
                {
                    panel8.setBorder(new TitledBorder("Paga Totale"));
                    panel8.setLayout(new FormLayout(
                        "5dlu, $lcgap, [100px,min], $lcgap, [200px,min]",
                        "2*(fill:[50px,min], $lgap), fill:[50px,min]"));

                    //---- label7 ----
                    label7.setText("Totale");
                    label7.setFont(new Font("Tahoma", Font.PLAIN, 18));
                    panel8.add(label7, CC.xy(3, 1));

                    //---- displayTotal ----
                    displayTotal.setEditable(false);
                    panel8.add(displayTotal, CC.xy(5, 1));

                    //---- label8 ----
                    label8.setText("Pagamento");
                    label8.setFont(new Font("Tahoma", Font.PLAIN, 18));
                    panel8.add(label8, CC.xy(3, 3));
                    panel8.add(totalPayment, CC.xy(5, 3));

                    //---- payTotalButton ----
                    payTotalButton.setText("Paga Totale");
                    payTotalButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
                    payTotalButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            payTotalButtonActionPerformed(e);
                        }
                    });
                    panel8.add(payTotalButton, CC.xy(5, 5));
                }
                payTotalCard.add(panel8, CC.xy(3, 3));
            }
            mainPanel.add(payTotalCard, "card3");
        }
        add(mainPanel, CC.xy(1, 1));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel mainPanel;
    private JPanel getReservationCard;
    private JPanel panel2;
    private JLabel label2;
    private JTextField bookCode;
    private JButton retrieveBook;
    private JPanel panel1;
    private JLabel label4;
    private JTextField clientCode;
    private JButton insertClientCode;
    private JPanel panel5;
    private JLabel label1;
    private JLabel label3;
    private JButton clearBook;
    private JTextField total;
    private JTextField partial;
    private JButton goToPayTotal;
    private JButton goToPayPartial;
    private JPanel payPartialCard;
    private JPanel panel4;
    private JButton button1;
    private JPanel panel3;
    private JLabel label5;
    private JTextField displayPartial;
    private JLabel label6;
    private JTextField partialPayment;
    private JButton payPartialButton;
    private JPanel payTotalCard;
    private JPanel panel6;
    private JButton button2;
    private JPanel panel8;
    private JLabel label7;
    private JTextField displayTotal;
    private JLabel label8;
    private JTextField totalPayment;
    private JButton payTotalButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
