/*
 * Created by JFormDesigner on Tue Feb 18 18:12:16 CET 2014
 */

package gameshop.advance.ui.swing.employee.book;

import com.jgoodies.forms.builder.PanelBuilder;
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
        total = new JTextField();
        partial = new JTextField();
        clearBook = new JButton();
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
        setName("this");

        //======== mainPanel ========
        {
            mainPanel.setName("mainPanel");
            mainPanel.setLayout(new CardLayout());

            //======== getReservationCard ========
            {
                getReservationCard.setName("getReservationCard");

                //======== panel2 ========
                {
                    panel2.setBorder(new CompoundBorder(
                        new TitledBorder("Prenotazione"),
                        Borders.DLU2_BORDER));
                    panel2.setName("panel2");

                    //---- label2 ----
                    label2.setText("Codice Prenotazione");
                    label2.setFont(new Font("Tahoma", Font.PLAIN, 14));
                    label2.setLabelFor(bookCode);
                    label2.setName("label2");

                    //---- bookCode ----
                    bookCode.setName("bookCode");

                    //---- retrieveBook ----
                    retrieveBook.setText("Aggiungi");
                    retrieveBook.setForeground(Color.black);
                    retrieveBook.setFont(new Font("Tahoma", Font.PLAIN, 14));
                    retrieveBook.setNextFocusableComponent(clientCode);
                    retrieveBook.setName("retrieveBook");
                    retrieveBook.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            retrieveBookActionPerformed(e);
                        }
                    });

                    PanelBuilder panel2Builder = new PanelBuilder(new FormLayout(
                        "center:[190px,min]:grow, $lcgap, [100dlu,min]:grow, $lcgap, [75dlu,default]:grow",
                        "[50px,min], $rgap, fill:[35dlu,min]"), panel2);

                    panel2Builder.add(label2,       CC.xy  (1, 1, CC.LEFT, CC.DEFAULT));
                    panel2Builder.add(bookCode,     CC.xywh(3, 1,       3,          1, CC.FILL, CC.FILL));
                    panel2Builder.add(retrieveBook, CC.xy  (5, 3));
                }

                //======== panel1 ========
                {
                    panel1.setBorder(new CompoundBorder(
                        new TitledBorder("Carta Cliente"),
                        Borders.DLU2_BORDER));
                    panel1.setFont(new Font("Tahoma", Font.PLAIN, 20));
                    panel1.setName("panel1");

                    //---- label4 ----
                    label4.setText("Codice");
                    label4.setFont(new Font("Tahoma", Font.PLAIN, 14));
                    label4.setLabelFor(clientCode);
                    label4.setName("label4");

                    //---- clientCode ----
                    clientCode.setNextFocusableComponent(insertClientCode);
                    clientCode.setName("clientCode");

                    //---- insertClientCode ----
                    insertClientCode.setText("Inserisci");
                    insertClientCode.setForeground(Color.black);
                    insertClientCode.setFont(new Font("Tahoma", Font.PLAIN, 14));
                    insertClientCode.setNextFocusableComponent(goToPayPartial);
                    insertClientCode.setName("insertClientCode");
                    insertClientCode.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            insertClientCodeActionPerformed(e);
                        }
                    });

                    PanelBuilder panel1Builder = new PanelBuilder(new FormLayout(
                        "left:[100dlu,min]:grow, $lcgap, [100dlu,min]:grow, $lcgap, [75dlu,default]:grow",
                        "[35dlu,min], $lgap, fill:[35dlu,min]"), panel1);

                    panel1Builder.add(label4,           CC.xy  (1, 1));
                    panel1Builder.add(clientCode,       CC.xywh(3, 1, 3, 1, CC.FILL, CC.FILL));
                    panel1Builder.add(insertClientCode, CC.xy  (5, 3));
                }

                //======== panel5 ========
                {
                    panel5.setName("panel5");

                    //---- label1 ----
                    label1.setText("Totale");
                    label1.setFont(new Font("Tahoma", Font.PLAIN, 14));
                    label1.setLabelFor(total);
                    label1.setName("label1");

                    //---- label3 ----
                    label3.setText("Acconto");
                    label3.setFont(new Font("Tahoma", Font.PLAIN, 14));
                    label3.setName("label3");

                    //---- total ----
                    total.setEditable(false);
                    total.setName("total");

                    //---- partial ----
                    partial.setEditable(false);
                    partial.setName("partial");

                    //---- clearBook ----
                    clearBook.setText("Annulla");
                    clearBook.setBackground(new Color(255, 51, 0));
                    clearBook.setFont(new Font("Tahoma", Font.PLAIN, 14));
                    clearBook.setForeground(Color.white);
                    clearBook.setIcon(null);
                    clearBook.setNextFocusableComponent(null);
                    clearBook.setName("clearBook");
                    clearBook.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            clearBookActionPerformed(e);
                        }
                    });

                    //---- goToPayTotal ----
                    goToPayTotal.setText("Paga Totale");
                    goToPayTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
                    goToPayTotal.setName("goToPayTotal");
                    goToPayTotal.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            goToPayTotalActionPerformed(e);
                        }
                    });

                    //---- goToPayPartial ----
                    goToPayPartial.setText("Paga Acconto");
                    goToPayPartial.setFont(new Font("Tahoma", Font.PLAIN, 14));
                    goToPayPartial.setName("goToPayPartial");
                    goToPayPartial.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            goToPayPartialActionPerformed(e);
                        }
                    });

                    PanelBuilder panel5Builder = new PanelBuilder(new FormLayout(
                        "[75dlu,default]:grow, 2*($lcgap, default), $lcgap, center:[75dlu,default]:grow, $lcgap, default, $lcgap, center:[75dlu,default]:grow",
                        "2*([25dlu,default], $lgap), [35dlu,default], $lgap, default"), panel5);

                    panel5Builder.add(label1,         CC.xy( 7, 1));
                    panel5Builder.add(label3,         CC.xy(11, 1));
                    panel5Builder.add(total,          CC.xy( 7, 3,    CC.FILL, CC.FILL));
                    panel5Builder.add(partial,        CC.xy(11, 3,    CC.FILL, CC.FILL));
                    panel5Builder.add(clearBook,      CC.xy( 1, 5, CC.DEFAULT, CC.FILL));
                    panel5Builder.add(goToPayTotal,   CC.xy( 7, 5,    CC.FILL, CC.FILL));
                    panel5Builder.add(goToPayPartial, CC.xy(11, 5,    CC.FILL, CC.FILL));
                }

                PanelBuilder getReservationCardBuilder = new PanelBuilder(new FormLayout(
                    "[15dlu,default]:grow, $lcgap, default:grow, $lcgap, [15dlu,default]:grow",
                    "[15dlu,default], 3*($lgap, default:grow), $lgap, [15dlu,default]:grow"), getReservationCard);

                getReservationCardBuilder.add(panel2, CC.xy(3, 3));
                getReservationCardBuilder.add(panel1, CC.xy(3, 5));
                getReservationCardBuilder.add(panel5, CC.xy(3, 7));
            }
            mainPanel.add(getReservationCard, "card1");

            //======== payPartialCard ========
            {
                payPartialCard.setName("payPartialCard");

                //======== panel4 ========
                {
                    panel4.setName("panel4");

                    //---- button1 ----
                    button1.setText("Indietro");
                    button1.setFont(new Font("Ubuntu", Font.PLAIN, 14));
                    button1.setName("button1");
                    button1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            indietroActionPerformed(e);
                        }
                    });

                    PanelBuilder panel4Builder = new PanelBuilder(new FormLayout(
                        "[50px,min], $lcgap, [75dlu,default], $lcgap, [50px,min]",
                        "default, $lgap, [35dlu,min], $lgap, default"), panel4);

                    panel4Builder.add(button1, CC.xy(3, 3, CC.DEFAULT, CC.FILL));
                }

                //======== panel3 ========
                {
                    panel3.setBorder(new TitledBorder("Paga Acconto"));
                    panel3.setName("panel3");

                    //---- label5 ----
                    label5.setText("Acconto");
                    label5.setFont(new Font("Tahoma", Font.PLAIN, 14));
                    label5.setName("label5");

                    //---- displayPartial ----
                    displayPartial.setEditable(false);
                    displayPartial.setName("displayPartial");

                    //---- label6 ----
                    label6.setText("Pagamento");
                    label6.setFont(new Font("Tahoma", Font.PLAIN, 14));
                    label6.setName("label6");

                    //---- partialPayment ----
                    partialPayment.setName("partialPayment");

                    //---- payPartialButton ----
                    payPartialButton.setText("Paga Acconto");
                    payPartialButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
                    payPartialButton.setName("payPartialButton");
                    payPartialButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            payPartialButtonActionPerformed(e);
                        }
                    });

                    PanelBuilder panel3Builder = new PanelBuilder(new FormLayout(
                        "5dlu, $lcgap, 70dlu, $lcgap, [25dlu,default], $lcgap, [75dlu,default]",
                        "fill:[35dlu,default], 2*($lgap, fill:[35dlu,min])"), panel3);

                    panel3Builder.add(label5,           CC.xy  (3, 1));
                    panel3Builder.add(displayPartial,   CC.xywh(5, 1, 3, 1));
                    panel3Builder.add(label6,           CC.xy  (3, 3));
                    panel3Builder.add(partialPayment,   CC.xywh(5, 3, 3, 1));
                    panel3Builder.add(payPartialButton, CC.xy  (7, 5));
                }

                PanelBuilder payPartialCardBuilder = new PanelBuilder(new FormLayout(
                    "[15dlu,default]:grow, $lcgap, 159dlu:grow, $lcgap, default:grow, $lcgap, [15dlu,default]:grow",
                    "[15dlu,default]:grow, $lgap, default:grow, $lgap, [15dlu,default]:grow"), payPartialCard);

                payPartialCardBuilder.add(panel4, CC.xy(3, 3));
                payPartialCardBuilder.add(panel3, CC.xy(5, 3));
            }
            mainPanel.add(payPartialCard, "card2");

            //======== payTotalCard ========
            {
                payTotalCard.setName("payTotalCard");

                //======== panel6 ========
                {
                    panel6.setName("panel6");

                    //---- button2 ----
                    button2.setText("Indietro");
                    button2.setFont(new Font("Ubuntu", Font.PLAIN, 14));
                    button2.setName("button2");
                    button2.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            indietroActionPerformed(e);
                        }
                    });

                    PanelBuilder panel6Builder = new PanelBuilder(new FormLayout(
                        "[50px,min], $lcgap, [75dlu,min], $lcgap, [50px,min]",
                        "default, $lgap, [35dlu,min], $lgap, default"), panel6);

                    panel6Builder.add(button2, CC.xy(3, 3, CC.DEFAULT, CC.FILL));
                }

                //======== panel8 ========
                {
                    panel8.setBorder(new TitledBorder("Paga Totale"));
                    panel8.setName("panel8");

                    //---- label7 ----
                    label7.setText("Totale");
                    label7.setFont(new Font("Tahoma", Font.PLAIN, 14));
                    label7.setName("label7");

                    //---- displayTotal ----
                    displayTotal.setEditable(false);
                    displayTotal.setName("displayTotal");

                    //---- label8 ----
                    label8.setText("Pagamento");
                    label8.setFont(new Font("Tahoma", Font.PLAIN, 14));
                    label8.setName("label8");

                    //---- totalPayment ----
                    totalPayment.setName("totalPayment");

                    //---- payTotalButton ----
                    payTotalButton.setText("Paga Totale");
                    payTotalButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
                    payTotalButton.setName("payTotalButton");
                    payTotalButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            payTotalButtonActionPerformed(e);
                        }
                    });

                    PanelBuilder panel8Builder = new PanelBuilder(new FormLayout(
                        "5dlu, $lcgap, 70dlu, $lcgap, [25dlu,default], $lcgap, [75dlu,default]",
                        "2*(fill:[35dlu,min], $lgap), fill:[35dlu,min]"), panel8);

                    panel8Builder.add(label7,         CC.xy  (3, 1));
                    panel8Builder.add(displayTotal,   CC.xywh(5, 1, 3, 1));
                    panel8Builder.add(label8,         CC.xy  (3, 3));
                    panel8Builder.add(totalPayment,   CC.xywh(5, 3, 3, 1));
                    panel8Builder.add(payTotalButton, CC.xy  (7, 5));
                }

                PanelBuilder payTotalCardBuilder = new PanelBuilder(new FormLayout(
                    "[15dlu,default]:grow, 163dlu, default:grow, [15dlu,default]:grow",
                    "[15dlu,default]:grow, $lgap, default:grow, $lgap, [15dlu,default]:grow"), payTotalCard);

                payTotalCardBuilder.add(panel6, CC.xy(2, 3));
                payTotalCardBuilder.add(panel8, CC.xy(3, 3));
            }
            mainPanel.add(payTotalCard, "card3");
        }

        PanelBuilder builder = new PanelBuilder(new FormLayout(
            "default:grow",
            "fill:default:grow"), this);

        builder.add(mainPanel, CC.xy(1, 1));
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
    private JTextField total;
    private JTextField partial;
    private JButton clearBook;
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
