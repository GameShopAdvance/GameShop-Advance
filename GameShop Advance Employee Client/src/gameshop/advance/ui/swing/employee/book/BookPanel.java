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
import gameshop.advance.exceptions.db.ReservationNotFoundDbException;
import gameshop.advance.exceptions.sales.ClientNotFoundException;
import gameshop.advance.interfaces.IPopActionListener;
import gameshop.advance.technicalservices.ExceptionHandlerSingleton;
import gameshop.advance.technicalservices.LoggerSingleton;
import gameshop.advance.ui.swing.UIWindowSingleton;
import gameshop.advance.ui.swing.factory.UIFactory;
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
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

/**
 * Schermata per il recupero di una prenotazione effettuata da terminale cliente.La recupera e la mostra, e permette
 * il pagamento.
 * @author Matteo Gentile
 */
public class BookPanel extends JPanel implements IPopActionListener {

    private PaymentPanel panel;
    
    public BookPanel() {
        initComponents();
        CardLayout layout = (CardLayout) this.getLayout();
        this.panel = new PaymentPanel();
        this.panel.setListener(this);
        layout.addLayoutComponent(this.panel, this.panel.getName());
        this.add(this.panel);
    }

    private void retrieveBookActionPerformed(ActionEvent e) {
        try {
            BookControllerSingleton.getInstance().recuperaPrenotazione(Integer.parseInt(this.bookCode.getText()));
            Money partial = BookControllerSingleton.getInstance().getPartial();
            this.disableField();
            this.partial.setText(partial.toString());
            this.total.setText(BookControllerSingleton.getInstance().getTotal().toString());
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
        catch (ReservationNotFoundDbException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }  
    }
    private void insertClientCodeActionPerformed(ActionEvent e) {
        try{
            Integer code = Integer.parseInt(this.clientCode.getText());
            BookControllerSingleton.getInstance().inserisciCartaCliente(code);
            this.clientCode.setEditable(false);
            this.partial.setText(BookControllerSingleton.getInstance().getPartial().toString());
            this.total.setText(BookControllerSingleton.getInstance().getTotal().toString());
        } catch (NullPointerException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        } catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        } catch (NumberFormatException ex){
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }
        catch (ClientNotFoundException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }
        catch (RemoteException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }
    }
    private void goToPayTotalActionPerformed(ActionEvent e) {
        try {
            this.panel.setPagaTotale();
            this.panel.setPayment(BookControllerSingleton.getInstance().getTotal());
            this.pushPanel(this.panel);
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
    
    private void goToPayPartialActionPerformed(ActionEvent e) {
        try {
            this.panel.setPagaAcconto();
            this.panel.setPayment(BookControllerSingleton.getInstance().getPartial());
            this.pushPanel(this.panel);
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

    public void disableField()
    {
        this.bookCode.setEnabled(false);
    }

    private void clearBookActionPerformed(ActionEvent e) {
        try{
            BookControllerSingleton.getInstance().clearBook();
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

    private void indietroActionPerformed(ActionEvent e) {
            CardLayout layout = (CardLayout) this.getLayout();
            layout.first(this);
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
        this.goToPayPartial = UIFactory.getInstance().getConfirmButton();
        this.goToPayTotal = UIFactory.getInstance().getConfirmButton();
        this.retrieveBook = UIFactory.getInstance().getSimpleButton();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        createUIComponents();

        getReservationCard = new JPanel();
        panel2 = new JPanel();
        bookCode = new JTextField();
        panel3 = new JPanel();
        label1 = new JLabel();
        partial = new JTextField();
        label3 = new JLabel();
        total = new JTextField();
        scrollPane1 = new JScrollPane();
        bookList = new JList();
        panel1 = new JPanel();
        clientCode = new JTextField();
        panel4 = new JPanel();

        //======== this ========
        setName("this");
        setLayout(new CardLayout());

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
                label2.setText("Codice");
                label2.setLabelFor(bookCode);
                label2.setHorizontalAlignment(SwingConstants.CENTER);
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
                    "70dlu, $lcgap, default:grow, $lcgap, [75dlu,default]",
                    "[50px,min], $rgap, fill:[35dlu,min]"), panel2);

                panel2Builder.add(label2,       CC.xy  (1, 1, CC.FILL, CC.FILL));
                panel2Builder.add(bookCode,     CC.xywh(3, 1,       3,       1, CC.FILL, CC.FILL));
                panel2Builder.add(retrieveBook, CC.xy  (5, 3));
            }

            //======== panel3 ========
            {
                panel3.setName("panel3");

                //---- label1 ----
                label1.setText("Acconto");
                label1.setHorizontalAlignment(SwingConstants.RIGHT);
                label1.setName("label1");

                //---- partial ----
                partial.setEditable(false);
                partial.setName("partial");

                //---- label3 ----
                label3.setText("Totale");
                label3.setHorizontalAlignment(SwingConstants.RIGHT);
                label3.setName("label3");

                //---- total ----
                total.setEditable(false);
                total.setName("total");

                //======== scrollPane1 ========
                {
                    scrollPane1.setName("scrollPane1");

                    //---- bookList ----
                    bookList.setName("bookList");
                    scrollPane1.setViewportView(bookList);
                }

                PanelBuilder panel3Builder = new PanelBuilder(new FormLayout(
                    "[40dlu,default]:grow, $lcgap, [60dlu,default], $lcgap, [40dlu,default]:grow, $lcgap, [60dlu,default]",
                    "[35dlu,default], $lgap, fill:default:grow"), panel3);

                panel3Builder.add(label1,      CC.xy  (1, 1, CC.DEFAULT, CC.FILL));
                panel3Builder.add(partial,     CC.xy  (3, 1, CC.DEFAULT, CC.FILL));
                panel3Builder.add(label3,      CC.xy  (5, 1, CC.DEFAULT, CC.FILL));
                panel3Builder.add(total,       CC.xy  (7, 1, CC.DEFAULT, CC.FILL));
                panel3Builder.add(scrollPane1, CC.xywh(1, 3,          7,       1));
            }

            //======== panel1 ========
            {
                panel1.setBorder(new CompoundBorder(
                    new TitledBorder("Carta Cliente"),
                    Borders.DLU2_BORDER));
                panel1.setFont(new Font("Tahoma", Font.PLAIN, 20));
                panel1.setName("panel1");

                //---- label4 ----
                label4.setText("Numero tessera");
                label4.setLabelFor(clientCode);
                label4.setHorizontalAlignment(SwingConstants.CENTER);
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
                    "70dlu, $lcgap, default:grow, $lcgap, [75dlu,default]",
                    "[35dlu,min], $lgap, fill:[35dlu,min]"), panel1);

                panel1Builder.add(label4,           CC.xy  (1, 1, CC.FILL, CC.FILL));
                panel1Builder.add(clientCode,       CC.xywh(3, 1,       3,       1, CC.FILL, CC.FILL));
                panel1Builder.add(insertClientCode, CC.xy  (5, 3));
            }

            //======== panel4 ========
            {
                panel4.setName("panel4");

                //---- clearBook ----
                clearBook.setText("Annulla");
                clearBook.setIcon(null);
                clearBook.setNextFocusableComponent(null);
                clearBook.setName("clearBook");
                clearBook.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        clearBookActionPerformed(e);
                    }
                });

                PanelBuilder panel4Builder = new PanelBuilder(new FormLayout(
                    "left:[86dlu,default]",
                    "[35dlu,default]"), panel4);

                panel4Builder.add(clearBook, CC.xy(1, 1, CC.FILL, CC.FILL));
            }

            //---- goToPayPartial ----
            goToPayPartial.setText("Paga Acconto");
            goToPayPartial.setName("goToPayPartial");
            goToPayPartial.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    goToPayPartialActionPerformed(e);
                }
            });

            //---- goToPayTotal ----
            goToPayTotal.setText("Paga Totale");
            goToPayTotal.setName("goToPayTotal");
            goToPayTotal.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    goToPayTotalActionPerformed(e);
                }
            });

            PanelBuilder getReservationCardBuilder = new PanelBuilder(new FormLayout(
                "$rgap, center:[140dlu,default,180dlu], $lcgap, left:[72dlu,default]:grow, $lcgap, left:[75dlu,default], $lcgap, left:[75dlu,min], $rgap",
                "$rgap, default, $lgap, 90dlu, $lgap, default:grow, $rgap, [35dlu,default], $rgap"), getReservationCard);

            getReservationCardBuilder.add(panel2,         CC.xy  (2, 2));
            getReservationCardBuilder.add(panel3,         CC.xywh(4, 2,       5,          5, CC.FILL, CC.FILL));
            getReservationCardBuilder.add(panel1,         CC.xy  (2, 4));
            getReservationCardBuilder.add(panel4,         CC.xy  (2, 8, CC.FILL, CC.DEFAULT));
            getReservationCardBuilder.add(goToPayPartial, CC.xy  (6, 8, CC.FILL,    CC.FILL));
            getReservationCardBuilder.add(goToPayTotal,   CC.xy  (8, 8, CC.FILL,    CC.FILL));
        }
        add(getReservationCard, "card2");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel getReservationCard;
    private JPanel panel2;
    private JLabel label2;
    private JTextField bookCode;
    private JButton retrieveBook;
    private JPanel panel3;
    private JLabel label1;
    private JTextField partial;
    private JLabel label3;
    private JTextField total;
    private JScrollPane scrollPane1;
    private JList bookList;
    private JPanel panel1;
    private JLabel label4;
    private JTextField clientCode;
    private JButton insertClientCode;
    private JPanel panel4;
    private JButton clearBook;
    private JButton goToPayPartial;
    private JButton goToPayTotal;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    @Override
    public void popPanel() {
        CardLayout layout = (CardLayout) this.getLayout();
        layout.first(this);
    }

    @Override
    public void pushPanel(JPanel panel) {
        CardLayout layout = (CardLayout) this.getLayout();
        layout.show(this, this.panel.getName());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.popPanel();
    }

}
