/*
 * Created by JFormDesigner on Wed Apr 16 13:24:41 CEST 2014
 */

package gameshop.advance.ui.swing.employee.book;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.BookControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.exceptions.sales.AlreadyPartialPayedException;
import gameshop.advance.exceptions.sales.AlreadyPayedException;
import gameshop.advance.exceptions.sales.InvalidSaleState;
import gameshop.advance.interfaces.IPopActionListener;
import gameshop.advance.technicalservices.ExceptionHandlerSingleton;
import gameshop.advance.technicalservices.LoggerSingleton;
import gameshop.advance.ui.swing.UIWindowSingleton;
import gameshop.advance.ui.swing.factory.UIFactory;
import gameshop.advance.utility.Money;
import java.awt.CardLayout;
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
import javax.swing.border.TitledBorder;

/**
 * Schermata del pagamento di una prenotazione.E' permesso il pagamento completo dell'operazione, o dell'acconto 
 * della stessa.
 * @author Matteo Gentile
 */
public class PaymentPanel extends JPanel {
    
    private final String name = "Payment Panel";
    private IPopActionListener listener;
    
    public PaymentPanel() {
        initComponents();
    }
    
     @Override
    public String getName()
    {
        return this.name;
    }
    
    /**
     * @param listener
     */
    public void setListener(IPopActionListener listener){
        this.listener = listener;
    }

    private void indietroActionPerformed(ActionEvent e) {
        this.listener.popPanel();
    }

    private void payTotalButtonActionPerformed(ActionEvent e) {
        try {
            BookControllerSingleton.getInstance().gestisciPagamento(Double.parseDouble(this.totalPayment.getText()));
        } catch (NullPointerException ex) {
             UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        } catch (RemoteException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        } catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        } catch (InvalidSaleState ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        } catch (AlreadyPayedException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }
        catch (InvalidMoneyException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }
    }
    
    public void setList(ListModel listaProdottiPrenotati, ListCellRenderer renderer)
    {
        this.resumeList.setCellRenderer(renderer);
        this.resumeList.setModel(listaProdottiPrenotati);
    }
    
    public void setPagaAcconto() {
       CardLayout layout = (CardLayout) this.swapPanel.getLayout();
       layout.show(this.swapPanel, "card1");      
    }
    
    public void setPagaTotale() {
       CardLayout layout = (CardLayout) this.swapPanel.getLayout();
       layout.show(this.swapPanel, "card2");  
    }
    
    private void createUIComponents() {
       this.label5 = UIFactory.getInstance().getBodyLabel();
       this.label6 = UIFactory.getInstance().getBodyLabel();
       this.label7 = UIFactory.getInstance().getBodyLabel();
       this.label8 = UIFactory.getInstance().getBodyLabel();
       this.button2 = UIFactory.getInstance().getCancelButton();
       this.payTotalButton = UIFactory.getInstance().getConfirmButton();
       this.payPartialButton = UIFactory.getInstance().getConfirmButton();
       }
    
    public void setPayment(Money m)
    {
        this.displayPartial.setText(m.toString());
        this.displayTotal.setText(m.toString());
    }

    private void payPartialButtonActionPerformed(ActionEvent e) {
        try {
            BookControllerSingleton.getInstance().pagaAcconto(Double.parseDouble(this.partialPayment.getText()));
        } catch (NullPointerException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);  
        } catch (RemoteException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        } catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        } catch (InvalidSaleState ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        } catch (AlreadyPayedException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }
        catch (InvalidMoneyException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }
        catch (AlreadyPartialPayedException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        createUIComponents();

        panel6 = new JPanel();
        scrollPane1 = new JScrollPane();
        resumeList = new JList();
        swapPanel = new JPanel();
        payPartialCard = new JPanel();
        panel1 = new JPanel();
        displayPartial = new JTextField();
        partialPayment = new JTextField();
        payTotalCard = new JPanel();
        panel2 = new JPanel();
        displayTotal = new JTextField();
        totalPayment = new JTextField();

        //======== this ========
        setName("this");

        //======== panel6 ========
        {
            panel6.setName("panel6");

            //======== scrollPane1 ========
            {
                scrollPane1.setName("scrollPane1");

                //---- resumeList ----
                resumeList.setName("resumeList");
                scrollPane1.setViewportView(resumeList);
            }

            PanelBuilder panel6Builder = new PanelBuilder(new FormLayout(
                "[75dlu,default]:grow",
                "default:grow, $lgap, [35dlu,default]:grow, $lgap, [35dlu,default]"), panel6);

            panel6Builder.add(scrollPane1, CC.xywh(1, 1, 1, 5, CC.FILL, CC.FILL));
        }

        //======== swapPanel ========
        {
            swapPanel.setName("swapPanel");
            swapPanel.setLayout(new CardLayout());

            //======== payPartialCard ========
            {
                payPartialCard.setBorder(null);
                payPartialCard.setName("payPartialCard");

                //======== panel1 ========
                {
                    panel1.setBorder(new TitledBorder("Paga acconto"));
                    panel1.setName("panel1");

                    //---- label5 ----
                    label5.setText("Acconto");
                    label5.setName("label5");

                    //---- displayPartial ----
                    displayPartial.setEditable(false);
                    displayPartial.setName("displayPartial");

                    //---- label6 ----
                    label6.setText("Pagamento");
                    label6.setName("label6");

                    //---- partialPayment ----
                    partialPayment.setName("partialPayment");

                    PanelBuilder panel1Builder = new PanelBuilder(new FormLayout(
                        "[50dlu,default], $lcgap, [10dlu,default]:grow, $lcgap, [75dlu,default]",
                        "fill:[35dlu,default], $lgap, fill:[35dlu,min]"), panel1);

                    panel1Builder.add(label5,         CC.xy  (1, 1));
                    panel1Builder.add(displayPartial, CC.xywh(3, 1, 3, 1));
                    panel1Builder.add(label6,         CC.xy  (1, 3));
                    panel1Builder.add(partialPayment, CC.xywh(3, 3, 3, 1));
                }

                //---- payPartialButton ----
                payPartialButton.setText("Paga Acconto");
                payPartialButton.setName("payPartialButton");
                payPartialButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        payPartialButtonActionPerformed(e);
                    }
                });

                PanelBuilder payPartialCardBuilder = new PanelBuilder(new FormLayout(
                    "[50dlu,default], $lcgap, [10dlu,default]:grow, $lcgap, [75dlu,default]",
                    "fill:[35dlu,default], $lgap, default:grow, $lgap, fill:[35dlu,min]"), payPartialCard);

                payPartialCardBuilder.add(panel1,           CC.xywh(1, 1, 5, 1));
                payPartialCardBuilder.add(payPartialButton, CC.xy  (5, 5));
            }
            swapPanel.add(payPartialCard, "card1");

            //======== payTotalCard ========
            {
                payTotalCard.setBorder(null);
                payTotalCard.setName("payTotalCard");

                //======== panel2 ========
                {
                    panel2.setBorder(new TitledBorder("Paga totale"));
                    panel2.setName("panel2");

                    //---- label7 ----
                    label7.setText("Totale");
                    label7.setName("label7");

                    //---- displayTotal ----
                    displayTotal.setEditable(false);
                    displayTotal.setName("displayTotal");

                    //---- label8 ----
                    label8.setText("Pagamento");
                    label8.setName("label8");

                    //---- totalPayment ----
                    totalPayment.setName("totalPayment");

                    PanelBuilder panel2Builder = new PanelBuilder(new FormLayout(
                        "50dlu, $lcgap, [10dlu,default]:grow, $lcgap, [75dlu,default]",
                        "fill:[35dlu,min], $lgap, fill:[35dlu,min]"), panel2);

                    panel2Builder.add(label7,       CC.xy  (1, 1));
                    panel2Builder.add(displayTotal, CC.xywh(3, 1, 3, 1));
                    panel2Builder.add(label8,       CC.xy  (1, 3));
                    panel2Builder.add(totalPayment, CC.xywh(3, 3, 3, 1));
                }

                //---- payTotalButton ----
                payTotalButton.setText("Paga Totale");
                payTotalButton.setName("payTotalButton");
                payTotalButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        payTotalButtonActionPerformed(e);
                    }
                });

                PanelBuilder payTotalCardBuilder = new PanelBuilder(new FormLayout(
                    "50dlu, $lcgap, [10dlu,default]:grow, $lcgap, [75dlu,default]",
                    "fill:[35dlu,min], $lgap, default:grow, $lgap, [35dlu,default]"), payTotalCard);

                payTotalCardBuilder.add(panel2,         CC.xywh(1, 1,       5,       1));
                payTotalCardBuilder.add(payTotalButton, CC.xy  (5, 5, CC.FILL, CC.FILL));
            }
            swapPanel.add(payTotalCard, "card2");
        }

        //---- button2 ----
        button2.setText("Indietro");
        button2.setName("button2");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                indietroActionPerformed(e);
            }
        });

        PanelBuilder builder = new PanelBuilder(new FormLayout(
            "[15dlu,default], $lcgap, [75dlu,default], $lcgap, [75dlu,default]:grow, 2*($lcgap, [75dlu,default]), $lcgap, [15dlu,default]",
            "[15dlu,default]:grow, 2*($lgap, 90dlu), $rgap, bottom:[35dlu,default], $lgap, [15dlu,default]:grow"), this);

        builder.add(panel6,    CC.xywh(3, 3,          3,       3, CC.FILL, CC.FILL));
        builder.add(swapPanel, CC.xywh(7, 3,          3,       5, CC.FILL, CC.FILL));
        builder.add(button2,   CC.xy  (3, 7, CC.DEFAULT, CC.FILL));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel6;
    private JScrollPane scrollPane1;
    private JList resumeList;
    private JPanel swapPanel;
    private JPanel payPartialCard;
    private JPanel panel1;
    private JLabel label5;
    private JTextField displayPartial;
    private JLabel label6;
    private JTextField partialPayment;
    private JButton payPartialButton;
    private JPanel payTotalCard;
    private JPanel panel2;
    private JLabel label7;
    private JTextField displayTotal;
    private JLabel label8;
    private JTextField totalPayment;
    private JButton payTotalButton;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

   
}
