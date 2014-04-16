/*
 * Created by JFormDesigner on Tue Feb 18 18:12:16 CET 2014
 */

package gameshop.advance.ui.swing.employee.book;

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.BookControllerSingleton;
import gameshop.advance.exceptions.AlredyPayedException;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.exceptions.InvalidSaleState;
import gameshop.advance.technicalservices.LoggerSingleton;
import gameshop.advance.ui.swing.UIFactory;
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
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

/**
 * @author Matteo Gentile
 */
public class BookPanel extends JPanel {

    public BookPanel() {
        initComponents();
        CardLayout layout = (CardLayout) this.mainPanel.getLayout();
    }

    private void retrieveBookActionPerformed(ActionEvent e) {
        try {
            BookControllerSingleton.getInstance().recuperaPrenotazione(Integer.parseInt(this.bookCode.getText()));
            Money partial = BookControllerSingleton.getInstance().getPartial();
            System.err.println("Partial in panel: "+partial);
            this.disableField();
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
            //CardLayout layout = (CardLayout) this.mainPanel.getLayout();
//            this.displayTotal.setText(BookControllerSingleton.getInstance().getTotal().toString());
            BookControllerSingleton.getInstance().vaiAlpagamentoTotale();
           // layout.last(this.mainPanel);
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
//            CardLayout layout = (CardLayout) this.mainPanel.getLayout();
//            this.displayPartial.setText(BookControllerSingleton.getInstance().getPartial().toString());
            BookControllerSingleton.getInstance().vaiAlpagamentoAcconto();
//            layout.next(this.mainPanel);
        } catch (NullPointerException ex) {
             LoggerSingleton.getInstance().log(ex);
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

    public void disableField()
    {
        this.bookCode.setEnabled(false);
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
        catch (InvalidSaleState ex) {
            Logger.getLogger(BookPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (AlredyPayedException ex) {
            Logger.getLogger(BookPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void payTotalButtonActionPerformed(ActionEvent e) {
        try {
            BookControllerSingleton.getInstance().gestisciPagamento(Double.parseDouble(this.totalPayment.getText()));
        } catch (NullPointerException ex) {
             UIWindowSingleton.getInstance().displayError("Non è possibile convalidare l'importo inserito.");   
             LoggerSingleton.getInstance().log(ex);
        } catch (RemoteException ex) {
            UIWindowSingleton.getInstance().displayError("Non è possibile contattare il server. "
                    + "Si prega di riprovare. Se il problema persiste, contattare l'amministratore di sistema.");
        } catch (ConfigurationException ex) {
             UIWindowSingleton.getInstance().displayError("Ci sono problemi nella lettura del file di configurazione: "+ex.getConfigurationPath()+"."
                   + " Per maggiori informazioni rivolgersi all'amministratore di sistema.");
        }
        catch (InvalidSaleState ex) {
            Logger.getLogger(BookPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (AlredyPayedException ex) {
            Logger.getLogger(BookPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setList(ListModel listaProdottiPrenotati, ListCellRenderer renderer)
    {
        this.bookList.setCellRenderer(renderer);
        this.bookList.setModel(listaProdottiPrenotati);
    }

    private void createUIComponents() {
        this.insertClientCode = UIFactory.getInstance().getSimpleButton();
        this.clearBook = UIFactory.getInstance().getCancelButton();
        this.button1 = UIFactory.getInstance().getSimpleButton();
        this.label1 = UIFactory.getInstance().getBodyLabel();
        this.label2 = UIFactory.getInstance().getBodyLabel();
        this.label3 = UIFactory.getInstance().getBodyLabel();
        this.label4 = UIFactory.getInstance().getBodyLabel();
        this.label5 = UIFactory.getInstance().getBodyLabel();
        this.label6 = UIFactory.getInstance().getBodyLabel();
        this.label7 = UIFactory.getInstance().getBodyLabel();
        this.label8 = UIFactory.getInstance().getBodyLabel();
        this.goToPayPartial = UIFactory.getInstance().getSimpleButton();
        this.goToPayTotal = UIFactory.getInstance().getSimpleButton();
        this.retrieveBook = UIFactory.getInstance().getSimpleButton();
        this.payPartialButton = UIFactory.getInstance().getSimpleButton();
        this.payTotalButton = UIFactory.getInstance().getSimpleButton();
        this.button2 = UIFactory.getInstance().getSimpleButton();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        createUIComponents();

        mainPanel = new JPanel();
        getReservationCard = new JPanel();
        panel2 = new JPanel();
        bookCode = new JTextField();
        panel7 = new JPanel();
        scrollPane1 = new JScrollPane();
        bookList = new JList();
        total = new JTextField();
        partial = new JTextField();
        panel1 = new JPanel();
        clientCode = new JTextField();
        panel5 = new JPanel();
        payPartialCard = new JPanel();
        panel4 = new JPanel();
        panel3 = new JPanel();
        displayPartial = new JTextField();
        partialPayment = new JTextField();
        payTotalCard = new JPanel();
        panel6 = new JPanel();
        panel8 = new JPanel();
        displayTotal = new JTextField();
        totalPayment = new JTextField();

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
                    "[15dlu,default]:grow, $lcgap, default:grow, $lcgap, [150dlu,default]:grow, [15dlu,default]:grow",
                    "[15dlu,default], 3*($lgap, default:grow), $lgap, [15dlu,default]:grow"));

                //======== panel2 ========
                {
                    panel2.setBorder(new CompoundBorder(
                        new TitledBorder("Prenotazione"),
                        Borders.DLU2_BORDER));
                    panel2.setLayout(new FormLayout(
                        "center:[190px,min]:grow, $lcgap, [100dlu,min]:grow, $lcgap, [75dlu,default]:grow",
                        "[50px,min], $rgap, fill:[35dlu,min]"));

                    //---- label2 ----
                    label2.setText("Codice Prenotazione");
                    label2.setLabelFor(bookCode);
                    panel2.add(label2, CC.xy(1, 1, CC.LEFT, CC.DEFAULT));
                    panel2.add(bookCode, CC.xywh(3, 1, 3, 1, CC.FILL, CC.FILL));

                    //---- retrieveBook ----
                    retrieveBook.setText("Aggiungi");
                    retrieveBook.setForeground(Color.black);
                    retrieveBook.setNextFocusableComponent(clientCode);
                    retrieveBook.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            retrieveBookActionPerformed(e);
                        }
                    });
                    panel2.add(retrieveBook, CC.xy(5, 3));
                }
                getReservationCard.add(panel2, CC.xy(3, 3));

                //======== panel7 ========
                {
                    panel7.setLayout(new FormLayout(
                        "[5dlu,default], $lcgap, [75dlu,default], $lcgap, default, $lcgap, [75dlu,default], $lcgap, [5dlu,default]",
                        "2*(default:grow, $lgap), 2*([25dlu,default]), $lgap, [35dlu,default], $lgap, default"));

                    //======== scrollPane1 ========
                    {
                        scrollPane1.setViewportView(bookList);
                    }
                    panel7.add(scrollPane1, CC.xywh(1, 1, 9, 4));

                    //---- label1 ----
                    label1.setText("Totale");
                    label1.setLabelFor(total);
                    panel7.add(label1, CC.xy(3, 5));

                    //---- label3 ----
                    label3.setText("Acconto");
                    panel7.add(label3, CC.xy(7, 5));

                    //---- total ----
                    total.setEditable(false);
                    panel7.add(total, CC.xy(3, 6, CC.FILL, CC.FILL));

                    //---- partial ----
                    partial.setEditable(false);
                    panel7.add(partial, CC.xy(7, 6, CC.FILL, CC.FILL));

                    //---- goToPayTotal ----
                    goToPayTotal.setText("Paga Totale");
                    goToPayTotal.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            goToPayTotalActionPerformed(e);
                        }
                    });
                    panel7.add(goToPayTotal, CC.xy(3, 8, CC.FILL, CC.FILL));

                    //---- goToPayPartial ----
                    goToPayPartial.setText("Paga Acconto");
                    goToPayPartial.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            goToPayPartialActionPerformed(e);
                        }
                    });
                    panel7.add(goToPayPartial, CC.xy(7, 8, CC.FILL, CC.FILL));
                }
                getReservationCard.add(panel7, CC.xywh(5, 3, 1, 5));

                //======== panel1 ========
                {
                    panel1.setBorder(new CompoundBorder(
                        new TitledBorder("Carta Cliente"),
                        Borders.DLU2_BORDER));
                    panel1.setFont(new Font("Tahoma", Font.PLAIN, 20));
                    panel1.setLayout(new FormLayout(
                        "left:[100dlu,min]:grow, $lcgap, [100dlu,min]:grow, $lcgap, [75dlu,default]:grow",
                        "[35dlu,min], $lgap, fill:[35dlu,min]"));

                    //---- label4 ----
                    label4.setText("Codice");
                    label4.setLabelFor(clientCode);
                    panel1.add(label4, CC.xy(1, 1));

                    //---- clientCode ----
                    clientCode.setNextFocusableComponent(insertClientCode);
                    panel1.add(clientCode, CC.xywh(3, 1, 3, 1, CC.FILL, CC.FILL));

                    //---- insertClientCode ----
                    insertClientCode.setText("Inserisci");
                    insertClientCode.setForeground(Color.black);
                    insertClientCode.setNextFocusableComponent(goToPayPartial);
                    insertClientCode.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            insertClientCodeActionPerformed(e);
                        }
                    });
                    panel1.add(insertClientCode, CC.xy(5, 3));
                }
                getReservationCard.add(panel1, CC.xy(3, 5));

                //======== panel5 ========
                {
                    panel5.setLayout(new FormLayout(
                        "[75dlu,default]:grow, 2*($lcgap, default), $lcgap, center:[75dlu,default]:grow, $lcgap, default, $lcgap, center:[75dlu,default]:grow",
                        "2*([25dlu,default], $lgap), [35dlu,default], $lgap, default"));

                    //---- clearBook ----
                    clearBook.setText("Annulla");
                    clearBook.setBackground(new Color(255, 51, 0));
                    clearBook.setForeground(Color.white);
                    clearBook.setIcon(null);
                    clearBook.setNextFocusableComponent(null);
                    clearBook.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            clearBookActionPerformed(e);
                        }
                    });
                    panel5.add(clearBook, CC.xy(1, 5, CC.DEFAULT, CC.FILL));
                }
                getReservationCard.add(panel5, CC.xy(3, 7));
            }
            mainPanel.add(getReservationCard, "card1");

            //======== payPartialCard ========
            {
                payPartialCard.setLayout(new FormLayout(
                    "[15dlu,default]:grow, $lcgap, 159dlu:grow, $lcgap, default:grow, $lcgap, [15dlu,default]:grow",
                    "[15dlu,default]:grow, $lgap, default:grow, $lgap, [15dlu,default]:grow"));

                //======== panel4 ========
                {
                    panel4.setLayout(new FormLayout(
                        "[50px,min], $lcgap, [75dlu,default], $lcgap, [50px,min]",
                        "default, $lgap, [35dlu,min], $lgap, default"));

                    //---- button1 ----
                    button1.setText("Indietro");
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
                        "5dlu, $lcgap, 70dlu, $lcgap, [25dlu,default], $lcgap, [75dlu,default]",
                        "fill:[35dlu,default], 2*($lgap, fill:[35dlu,min])"));

                    //---- label5 ----
                    label5.setText("Acconto");
                    panel3.add(label5, CC.xy(3, 1));

                    //---- displayPartial ----
                    displayPartial.setEditable(false);
                    panel3.add(displayPartial, CC.xywh(5, 1, 3, 1));

                    //---- label6 ----
                    label6.setText("Pagamento");
                    panel3.add(label6, CC.xy(3, 3));
                    panel3.add(partialPayment, CC.xywh(5, 3, 3, 1));

                    //---- payPartialButton ----
                    payPartialButton.setText("Paga Acconto");
                    payPartialButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            payPartialButtonActionPerformed(e);
                        }
                    });
                    panel3.add(payPartialButton, CC.xy(7, 5));
                }
                payPartialCard.add(panel3, CC.xy(5, 3));
            }
            mainPanel.add(payPartialCard, "card2");

            //======== payTotalCard ========
            {
                payTotalCard.setLayout(new FormLayout(
                    "[15dlu,default]:grow, 163dlu, default:grow, [15dlu,default]:grow",
                    "[15dlu,default]:grow, $lgap, default:grow, $lgap, [15dlu,default]:grow"));

                //======== panel6 ========
                {
                    panel6.setLayout(new FormLayout(
                        "[50px,min], $lcgap, [75dlu,min], $lcgap, [50px,min]",
                        "default, $lgap, [35dlu,min], $lgap, default"));

                    //---- button2 ----
                    button2.setText("Indietro");
                    button2.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            indietroActionPerformed(e);
                        }
                    });
                    panel6.add(button2, CC.xy(3, 3, CC.DEFAULT, CC.FILL));
                }
                payTotalCard.add(panel6, CC.xy(2, 3));

                //======== panel8 ========
                {
                    panel8.setBorder(new TitledBorder("Paga Totale"));
                    panel8.setLayout(new FormLayout(
                        "5dlu, $lcgap, 70dlu, $lcgap, [25dlu,default], $lcgap, [75dlu,default]",
                        "2*(fill:[35dlu,min], $lgap), fill:[35dlu,min]"));

                    //---- label7 ----
                    label7.setText("Totale");
                    panel8.add(label7, CC.xy(3, 1));

                    //---- displayTotal ----
                    displayTotal.setEditable(false);
                    panel8.add(displayTotal, CC.xywh(5, 1, 3, 1));

                    //---- label8 ----
                    label8.setText("Pagamento");
                    panel8.add(label8, CC.xy(3, 3));
                    panel8.add(totalPayment, CC.xywh(5, 3, 3, 1));

                    //---- payTotalButton ----
                    payTotalButton.setText("Paga Totale");
                    payTotalButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            payTotalButtonActionPerformed(e);
                        }
                    });
                    panel8.add(payTotalButton, CC.xy(7, 5));
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
    private JPanel panel7;
    private JScrollPane scrollPane1;
    private JList bookList;
    private JLabel label1;
    private JLabel label3;
    private JTextField total;
    private JTextField partial;
    private JButton goToPayTotal;
    private JButton goToPayPartial;
    private JPanel panel1;
    private JLabel label4;
    private JTextField clientCode;
    private JButton insertClientCode;
    private JPanel panel5;
    private JButton clearBook;
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
