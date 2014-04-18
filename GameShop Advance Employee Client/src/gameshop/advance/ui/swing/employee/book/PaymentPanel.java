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
import gameshop.advance.exceptions.sales.AlredyPayedException;
import gameshop.advance.exceptions.sales.InvalidSaleState;
import gameshop.advance.interfaces.IPopActionListener;
import gameshop.advance.ui.swing.UIWindowSingleton;
import gameshop.advance.ui.swing.factory.UIFactory;
import gameshop.advance.utility.Money;
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
    private IPopActionListener listener;
    
    public PaymentPanel() {
        initComponents();
    }
    
     @Override
    public String getName()
    {
        return this.name;
    }
    
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
       CardLayout layout = (CardLayout) this.swapPanel.getLayout();
       layout.next(this.swapPanel);      
    }
    
    public void setPagaTotale() {
       CardLayout layout = (CardLayout) this.swapPanel.getLayout();
       layout.last(this.swapPanel);
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
            BookControllerSingleton.getInstance().pagaAcconto(Double.parseDouble(this.totalPayment.getText()));
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
        catch (InvalidMoneyException ex) {
            Logger.getLogger(PaymentPanel.class.getName()).log(Level.SEVERE, null, ex);
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
        displayPartial = new JTextField();
        partialPayment = new JTextField();
        payTotalCard = new JPanel();
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

            //---- button2 ----
            button2.setText("Indietro");
            button2.setName("button2");
            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    indietroActionPerformed(e);
                }
            });

            PanelBuilder panel6Builder = new PanelBuilder(new FormLayout(
                "[25dlu,min], $lcgap, [168dlu,min], $lcgap, [25dlu,default]",
                "default:grow, 2*($lgap, [35dlu,default])"), panel6);

            panel6Builder.add(scrollPane1, CC.xy(3, 3));
            panel6Builder.add(button2,     CC.xy(3, 5, CC.DEFAULT, CC.FILL));
        }

        //======== swapPanel ========
        {
            swapPanel.setName("swapPanel");
            swapPanel.setLayout(new CardLayout());

            //======== payPartialCard ========
            {
                payPartialCard.setBorder(new TitledBorder("Paga Acconto"));
                payPartialCard.setName("payPartialCard");

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

                //---- payPartialButton ----
                payPartialButton.setText("Paga Acconto");
                payPartialButton.setName("payPartialButton");
                payPartialButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        payPartialButtonActionPerformed(e);
                        payPartialButtonActionPerformed(e);
                        payPartialButtonActionPerformed(e);
                    }
                });

                PanelBuilder payPartialCardBuilder = new PanelBuilder(new FormLayout(
                    "5dlu, $lcgap, 70dlu, $lcgap, [25dlu,default], $lcgap, [75dlu,default]",
                    "fill:[35dlu,default], 2*($lgap, fill:[35dlu,min])"), payPartialCard);

                payPartialCardBuilder.add(label5,           CC.xy  (3, 1));
                payPartialCardBuilder.add(displayPartial,   CC.xywh(5, 1, 3, 1));
                payPartialCardBuilder.add(label6,           CC.xy  (3, 3));
                payPartialCardBuilder.add(partialPayment,   CC.xywh(5, 3, 3, 1));
                payPartialCardBuilder.add(payPartialButton, CC.xy  (7, 5));
            }
            swapPanel.add(payPartialCard, "card1");

            //======== payTotalCard ========
            {
                payTotalCard.setBorder(new TitledBorder("Paga Totale"));
                payTotalCard.setName("payTotalCard");

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
                    "5dlu, $lcgap, 70dlu, $lcgap, [25dlu,default], $lcgap, [75dlu,default]",
                    "2*(fill:[35dlu,min], $lgap), fill:[35dlu,min]"), payTotalCard);

                payTotalCardBuilder.add(label7,         CC.xy  (3, 1));
                payTotalCardBuilder.add(displayTotal,   CC.xywh(5, 1, 3, 1));
                payTotalCardBuilder.add(label8,         CC.xy  (3, 3));
                payTotalCardBuilder.add(totalPayment,   CC.xywh(5, 3, 3, 1));
                payTotalCardBuilder.add(payTotalButton, CC.xy  (7, 5));
            }
            swapPanel.add(payTotalCard, "card2");
        }

        PanelBuilder builder = new PanelBuilder(new FormLayout(
            "[15dlu,default], $lcgap, [150dlu,default]:grow, $lcgap, 193dlu:grow, $lcgap, [15dlu,default]",
            "[15dlu,default], $lgap, default:grow, $lgap, [15dlu,default]"), this);

        builder.add(panel6,    CC.xy(3, 3));
        builder.add(swapPanel, CC.xy(5, 3));
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
