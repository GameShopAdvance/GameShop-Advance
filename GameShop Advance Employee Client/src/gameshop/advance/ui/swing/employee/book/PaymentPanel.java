/*
 * Created by JFormDesigner on Wed Apr 16 13:24:41 CEST 2014
 */

package gameshop.advance.ui.swing.employee.book;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.BookControllerSingleton;
import gameshop.advance.exceptions.AlredyPayedException;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.exceptions.InvalidSaleState;
import gameshop.advance.ui.swing.UIFactory;
import gameshop.advance.ui.swing.UIWindowSingleton;
import java.awt.CardLayout;
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
import javax.swing.border.TitledBorder;

/**
 * @author Matteo Gentile
 */
public class PaymentPanel extends JPanel {
    private final String name = "Payment Panel";
    
    public PaymentPanel() {
        initComponents();
        CardLayout layout = (CardLayout) this.swapPanel.getLayout();
    }
     @Override
    public String getName()
    {
        return this.name;
    }

    private void indietroActionPerformed(ActionEvent e) {
        try {
            BookControllerSingleton.getInstance().gestisciPrenotazione();
        } catch (RemoteException ex) {
            Logger.getLogger(PaymentPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            Logger.getLogger(PaymentPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConfigurationException ex) {
            Logger.getLogger(PaymentPanel.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (InvalidSaleState ex) {
            Logger.getLogger(PaymentPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AlredyPayedException ex) {
            Logger.getLogger(PaymentPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setList(ListModel listaProdottiPrenotati, ListCellRenderer renderer)
    {
        this.resumeList.setCellRenderer(renderer);
        this.resumeList.setModel(listaProdottiPrenotati);
    }
    
     public void setPagaAcconto() {
        try {
            CardLayout layout = (CardLayout) this.swapPanel.getLayout();
            this.displayPartial.setText(BookControllerSingleton.getInstance().getPartial().toString());
            layout.next(this.swapPanel);
//            layout.show(this.swapPanel, this.payPartialCard.getName()); 
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
     public void setPagaTotale() {
        try {
            CardLayout layout = (CardLayout) this.swapPanel.getLayout();
             this.displayTotal.setText(BookControllerSingleton.getInstance().getTotal().toString());
             layout.last(this.swapPanel);
             //layout.show(this.swapPanel, this.payTotalCard.getName());
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
    
    private void createUIComponents() {
       this.label5 = UIFactory.getInstance().getBodyLabel();
       this.label6 = UIFactory.getInstance().getBodyLabel();
       this.label7 = UIFactory.getInstance().getBodyLabel();
       this.label8 = UIFactory.getInstance().getBodyLabel();
       this.button2 = UIFactory.getInstance().getCancelButton();
       this.payTotalButton = UIFactory.getInstance().getConfirmButton();
       this.payPartialButton = UIFactory.getInstance().getConfirmButton();
       }

    private void payPartialButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        createUIComponents();

        panel6 = new JPanel();
        scrollPane1 = new JScrollPane();
        resumeList = new JList();
        swapPanel = new JPanel();
        payPartialCard = new JPanel();
        displayPartial = new JTextField();
        partialPayment = new JTextField();
        payTotalCard = new JPanel();
        displayTotal = new JTextField();
        totalPayment = new JTextField();

        //======== this ========
        setLayout(new FormLayout(
            "[15dlu,default], $lcgap, [150dlu,default]:grow, $lcgap, 193dlu:grow, $lcgap, [15dlu,default]",
            "[15dlu,default], $lgap, default:grow, $lgap, [15dlu,default]"));

        //======== panel6 ========
        {
            panel6.setLayout(new FormLayout(
                "[25dlu,min], $lcgap, [168dlu,min], $lcgap, [25dlu,default]",
                "default:grow, 2*($lgap, [35dlu,default])"));

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(resumeList);
            }
            panel6.add(scrollPane1, CC.xy(3, 3));

            //---- button2 ----
            button2.setText("Indietro");
            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    indietroActionPerformed(e);
                }
            });
            panel6.add(button2, CC.xy(3, 5, CC.DEFAULT, CC.FILL));
        }
        add(panel6, CC.xy(3, 3));

        //======== swapPanel ========
        {
            swapPanel.setLayout(new CardLayout());

            //======== payPartialCard ========
            {
                payPartialCard.setBorder(new TitledBorder("Paga Acconto"));
                payPartialCard.setLayout(new FormLayout(
                    "5dlu, $lcgap, 70dlu, $lcgap, [25dlu,default], $lcgap, [75dlu,default]",
                    "fill:[35dlu,default], 2*($lgap, fill:[35dlu,min])"));

                //---- label5 ----
                label5.setText("Acconto");
                payPartialCard.add(label5, CC.xy(3, 1));

                //---- displayPartial ----
                displayPartial.setEditable(false);
                payPartialCard.add(displayPartial, CC.xywh(5, 1, 3, 1));

                //---- label6 ----
                label6.setText("Pagamento");
                payPartialCard.add(label6, CC.xy(3, 3));
                payPartialCard.add(partialPayment, CC.xywh(5, 3, 3, 1));

                //---- payPartialButton ----
                payPartialButton.setText("Paga Acconto");
                payPartialButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        payPartialButtonActionPerformed(e);
                        payPartialButtonActionPerformed(e);
                        payPartialButtonActionPerformed(e);
                    }
                });
                payPartialCard.add(payPartialButton, CC.xy(7, 5));
            }
            swapPanel.add(payPartialCard, "card1");

            //======== payTotalCard ========
            {
                payTotalCard.setBorder(new TitledBorder("Paga Totale"));
                payTotalCard.setLayout(new FormLayout(
                    "5dlu, $lcgap, 70dlu, $lcgap, [25dlu,default], $lcgap, [75dlu,default]",
                    "2*(fill:[35dlu,min], $lgap), fill:[35dlu,min]"));

                //---- label7 ----
                label7.setText("Totale");
                payTotalCard.add(label7, CC.xy(3, 1));

                //---- displayTotal ----
                displayTotal.setEditable(false);
                payTotalCard.add(displayTotal, CC.xywh(5, 1, 3, 1));

                //---- label8 ----
                label8.setText("Pagamento");
                payTotalCard.add(label8, CC.xy(3, 3));
                payTotalCard.add(totalPayment, CC.xywh(5, 3, 3, 1));

                //---- payTotalButton ----
                payTotalButton.setText("Paga Totale");
                payTotalButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        payTotalButtonActionPerformed(e);
                    }
                });
                payTotalCard.add(payTotalButton, CC.xy(7, 5));
            }
            swapPanel.add(payTotalCard, "card2");
        }
        add(swapPanel, CC.xy(5, 3));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel6;
    private JScrollPane scrollPane1;
    private JList resumeList;
    private JButton button2;
    private JPanel swapPanel;
    private JPanel payPartialCard;
    private JLabel label5;
    private JTextField displayPartial;
    private JLabel label6;
    private JTextField partialPayment;
    private JButton payPartialButton;
    private JPanel payTotalCard;
    private JLabel label7;
    private JTextField displayTotal;
    private JLabel label8;
    private JTextField totalPayment;
    private JButton payTotalButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

   
}
