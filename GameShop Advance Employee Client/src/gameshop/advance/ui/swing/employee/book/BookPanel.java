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
import gameshop.advance.technicalservices.LoggerSingleton;
import gameshop.advance.ui.interfaces.IPopActionListener;
import gameshop.advance.ui.swing.UIFactory;
import gameshop.advance.ui.swing.UIWindowSingleton;
import gameshop.advance.utility.Money;
import java.awt.CardLayout;
import java.awt.Color;
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
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

/**
 * @author Matteo Gentile
 */
public class BookPanel extends JPanel implements IPopActionListener {

    private PaymentPanel panel;
    
    public BookPanel() {
        initComponents();
        CardLayout layout = (CardLayout) this.mainPanel.getLayout();
        this.panel = new PaymentPanel();
        this.panel.setListener(this);
        layout.addLayoutComponent(this.panel, this.panel.getName());
        this.mainPanel.add(this.panel);
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
            this.panel.setPagaTotale();
            this.panel.setPayment(BookControllerSingleton.getInstance().getTotal());
            this.pushPanel(this.panel);
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
            this.panel.setPagaAcconto();
            this.panel.setPayment(BookControllerSingleton.getInstance().getPartial());
            this.pushPanel(this.panel);
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
    
    public void setList(ListModel listaProdottiPrenotati, ListCellRenderer renderer)
    {
        this.bookList.setCellRenderer(renderer);
        this.bookList.setModel(listaProdottiPrenotati);
        
        this.panel.setList(listaProdottiPrenotati, renderer);
    }

    private void createUIComponents() {
        this.insertClientCode = UIFactory.getInstance().getSimpleButton();
        this.clearBook = UIFactory.getInstance().getCancelButton();
        this.label1 = UIFactory.getInstance().getBodyLabel();
        this.label2 = UIFactory.getInstance().getBodyLabel();
        this.label3 = UIFactory.getInstance().getBodyLabel();
        this.label4 = UIFactory.getInstance().getBodyLabel();
        this.goToPayPartial = UIFactory.getInstance().getSimpleButton();
        this.goToPayTotal = UIFactory.getInstance().getSimpleButton();
        this.retrieveBook = UIFactory.getInstance().getSimpleButton();
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
                    label2.setLabelFor(bookCode);
                    label2.setName("label2");

                    //---- bookCode ----
                    bookCode.setName("bookCode");

                    //---- retrieveBook ----
                    retrieveBook.setText("Aggiungi");
                    retrieveBook.setForeground(Color.black);
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

                //======== panel7 ========
                {
                    panel7.setName("panel7");

                    //======== scrollPane1 ========
                    {
                        scrollPane1.setName("scrollPane1");

                        //---- bookList ----
                        bookList.setName("bookList");
                        scrollPane1.setViewportView(bookList);
                    }

                    //---- label1 ----
                    label1.setText("Totale");
                    label1.setLabelFor(total);
                    label1.setName("label1");

                    //---- label3 ----
                    label3.setText("Acconto");
                    label3.setName("label3");

                    //---- total ----
                    total.setEditable(false);
                    total.setName("total");

                    //---- partial ----
                    partial.setEditable(false);
                    partial.setName("partial");

                    //---- goToPayTotal ----
                    goToPayTotal.setText("Paga Totale");
                    goToPayTotal.setName("goToPayTotal");
                    goToPayTotal.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            goToPayTotalActionPerformed(e);
                        }
                    });

                    //---- goToPayPartial ----
                    goToPayPartial.setText("Paga Acconto");
                    goToPayPartial.setName("goToPayPartial");
                    goToPayPartial.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            goToPayPartialActionPerformed(e);
                        }
                    });

                    PanelBuilder panel7Builder = new PanelBuilder(new FormLayout(
                        "[5dlu,default], $lcgap, [75dlu,default], $lcgap, default, $lcgap, [75dlu,default], $lcgap, [5dlu,default]",
                        "2*(default:grow, $lgap), 2*([25dlu,default]), $lgap, [35dlu,default], $lgap, default"), panel7);

                    panel7Builder.add(scrollPane1,    CC.xywh(1, 1,       9,       4));
                    panel7Builder.add(label1,         CC.xy  (3, 5));
                    panel7Builder.add(label3,         CC.xy  (7, 5));
                    panel7Builder.add(total,          CC.xy  (3, 6, CC.FILL, CC.FILL));
                    panel7Builder.add(partial,        CC.xy  (7, 6, CC.FILL, CC.FILL));
                    panel7Builder.add(goToPayTotal,   CC.xy  (3, 8, CC.FILL, CC.FILL));
                    panel7Builder.add(goToPayPartial, CC.xy  (7, 8, CC.FILL, CC.FILL));
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
                    label4.setLabelFor(clientCode);
                    label4.setName("label4");

                    //---- clientCode ----
                    clientCode.setNextFocusableComponent(insertClientCode);
                    clientCode.setName("clientCode");

                    //---- insertClientCode ----
                    insertClientCode.setText("Inserisci");
                    insertClientCode.setForeground(Color.black);
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

                    //---- clearBook ----
                    clearBook.setText("Annulla");
                    clearBook.setBackground(new Color(255, 51, 0));
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

                    PanelBuilder panel5Builder = new PanelBuilder(new FormLayout(
                        "[75dlu,default]:grow, 2*($lcgap, default), $lcgap, center:[75dlu,default]:grow, $lcgap, default, $lcgap, center:[75dlu,default]:grow",
                        "2*([25dlu,default], $lgap), [35dlu,default], $lgap, default"), panel5);

                    panel5Builder.add(clearBook, CC.xy(1, 5, CC.DEFAULT, CC.FILL));
                }

                PanelBuilder getReservationCardBuilder = new PanelBuilder(new FormLayout(
                    "[15dlu,default]:grow, $lcgap, default:grow, $lcgap, [150dlu,default]:grow, [15dlu,default]:grow",
                    "[15dlu,default], 3*($lgap, default:grow), $lgap, [15dlu,default]:grow"), getReservationCard);

                getReservationCardBuilder.add(panel2, CC.xy  (3, 3));
                getReservationCardBuilder.add(panel7, CC.xywh(5, 3, 1, 5));
                getReservationCardBuilder.add(panel1, CC.xy  (3, 5));
                getReservationCardBuilder.add(panel5, CC.xy  (3, 7));
            }
            mainPanel.add(getReservationCard, "card1");
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
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    @Override
    public void popPanel() {
        CardLayout layout = (CardLayout) this.mainPanel.getLayout();
        layout.first(this.mainPanel);
    }

    @Override
    public void pushPanel(JPanel panel) {
        CardLayout layout = (CardLayout) this.mainPanel.getLayout();
        layout.show(this.mainPanel, this.panel.getName());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.popPanel();
    }

}
