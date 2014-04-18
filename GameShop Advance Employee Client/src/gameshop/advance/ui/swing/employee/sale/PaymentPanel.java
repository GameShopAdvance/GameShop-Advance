/*
 * Created by JFormDesigner on Thu Jan 30 12:02:33 CET 2014
 */

package gameshop.advance.ui.swing.employee.sale;


import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.SaleControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.exceptions.sales.AlredyPayedException;
import gameshop.advance.exceptions.sales.InvalidSaleState;
import gameshop.advance.interfaces.IListPanel;
import gameshop.advance.technicalservices.ExceptionHandlerSingleton;
import gameshop.advance.technicalservices.LoggerSingleton;
import gameshop.advance.ui.swing.UIWindowSingleton;
import gameshop.advance.ui.swing.factory.UIFactory;
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
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

/**
 * Schermata per il pagamento della vendita.Mostra il riepilogo degli oggetti acquistati, e il totale da pagare.
 * @author Andrea
 */
public class PaymentPanel extends JPanel implements IListPanel {
    
    public PaymentPanel() {
        initComponents();
        try {
            this.displayTotal.setText(SaleControllerSingleton.getInstance().getTotal().toString());
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

    private void payButtonActionPerformed(ActionEvent e) {
        try{
            SaleControllerSingleton.getInstance().effettuaPagamento(Double.parseDouble(this.payment.getText()));
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
        catch (InvalidMoneyException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }
        catch (InvalidSaleState ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }
        catch (AlredyPayedException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }
    }
    
    public void setList(ListModel listaProdotti, ListCellRenderer renderer)
    {
        this.resumeList.setCellRenderer(renderer);
        this.resumeList.setModel(listaProdotti);
    }

    private void createUIComponents() {
        this.label1 = UIFactory.getInstance().getBodyLabel();
        this.label2 = UIFactory.getInstance().getBodyLabel();
        this.payButton = UIFactory.getInstance().getConfirmButton();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        createUIComponents();

        scrollPane1 = new JScrollPane();
        resumeList = new JList();
        displayTotal = new JTextField();
        payment = new JTextField();

        //======== this ========
        setLayout(new FormLayout(
            "[15dlu,default], $lcgap, [350px,min,600dlu], $lcgap, [146px,min], $lcgap, [25dlu,default], $lcgap, [75dlu,default], $lcgap, [15dlu,default]",
            "fill:[15dlu,default], 2*($rgap, [35dlu,default]), $rgap, fill:[35dlu,default], $lgap, [35dlu,default]:grow, $rgap, [15dlu,default]"));

        //======== scrollPane1 ========
        {
            scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane1.setViewportView(resumeList);
        }
        add(scrollPane1, CC.xywh(3, 3, 1, 7));

        //---- label1 ----
        label1.setText("Totale");
        label1.setAlignmentX(0.5F);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        add(label1, CC.xy(5, 3, CC.FILL, CC.FILL));

        //---- displayTotal ----
        displayTotal.setEditable(false);
        displayTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(displayTotal, CC.xywh(7, 3, 3, 1, CC.FILL, CC.FILL));

        //---- payment ----
        payment.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(payment, CC.xywh(7, 5, 3, 1, CC.FILL, CC.FILL));

        //---- label2 ----
        label2.setText("Pagamento");
        label2.setAlignmentX(0.5F);
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        add(label2, CC.xy(5, 5, CC.FILL, CC.FILL));

        //---- payButton ----
        payButton.setText("Paga");
        payButton.setAlignmentX(0.5F);
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                payButtonActionPerformed(e);
            }
        });
        add(payButton, CC.xy(9, 7));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JList resumeList;
    private JLabel label1;
    private JTextField displayTotal;
    private JTextField payment;
    private JLabel label2;
    private JButton payButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
