/*
 * Created by JFormDesigner on Sun Feb 16 15:43:22 CET 2014
 */

package gameshop.advance.ui.swing.customer;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.ReservationControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.exceptions.ExceptionHandlerSingleton;
import gameshop.advance.technicalservices.LoggerSingleton;
import gameshop.advance.ui.swing.UIWindowSingleton;
import gameshop.advance.ui.swing.factory.UIFactory;
import gameshop.advance.utility.Money;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/*
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
 
public class CompletedReservationPanel extends JPanel {
    
    private final String name = "Completed Panel";
    
    public CompletedReservationPanel() {
        try {
            initComponents();
            this.setID(ReservationControllerSingleton.getInstance().getID());
            this.setTotal(ReservationControllerSingleton.getInstance().getTotal());
            this.setPartial(ReservationControllerSingleton.getInstance().getPartial());
        }
        catch (NullPointerException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }
        catch (RemoteException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }
        catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }
    }
    
    @Override
    public String getName()
    {
        return this.name;
    }
    
    public void setID(Integer id)
    {
        this.numeroPrenotazione.setText(id.toString());
    }

    public void setTotal(Money m)
    {
        this.totale.setText(m.toString());
    }
    
    public void setPartial(Money m)
    {
        this.acconto.setText(m.toString());
    }

    private void endActionPerformed(ActionEvent e) {
        try {
            ReservationControllerSingleton.getInstance().avviaPrenotazione();
        }
        catch (NullPointerException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }
        catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }
        catch (RemoteException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }
        
    }

    private void createUIComponents() {
        this.title = UIFactory.getInstance().getHeaderLabel();
        this.button1 = UIFactory.getInstance().getConfirmButton();
        
        this.totale = UIFactory.getInstance().getBodyLabel();
        this.acconto = UIFactory.getInstance().getBodyLabel();
        this.totaleLabel = UIFactory.getInstance().getBoldLabel();
        this.accontoLabel = UIFactory.getInstance().getBoldLabel();
        
        this.label2 = UIFactory.getInstance().getBodyLabel();
        this.label3 = UIFactory.getInstance().getBodyLabel();
        this.label4 = UIFactory.getInstance().getBodyLabel();
        this.label7 = UIFactory.getInstance().getBodyLabel();
        this.numeroPrenotazione = UIFactory.getInstance().getBoldLabel();
    }
    
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        createUIComponents();


        //======== this ========
        setName("this");

        //---- title ----
        title.setText("Congratulazioni: la tua prenotazione \u00e8 completata");
        title.setName("title");

        //---- label2 ----
        label2.setText("Puoi pagare i prodotti prenotati in cassa fornendo il numero di prenotazione");
        label2.setName("label2");

        //---- label3 ----
        label3.setText("Numero prenotazione");
        label3.setHorizontalAlignment(SwingConstants.RIGHT);
        label3.setName("label3");

        //---- numeroPrenotazione ----
        numeroPrenotazione.setName("numeroPrenotazione");

        //---- label7 ----
        label7.setText("Se desideri puoi lasciare un acconto e saldare il totale quando avrai i prodotti");
        label7.setName("label7");

        //---- totaleLabel ----
        totaleLabel.setText("Totale");
        totaleLabel.setName("totaleLabel");

        //---- accontoLabel ----
        accontoLabel.setText("Acconto");
        accontoLabel.setName("accontoLabel");

        //---- totale ----
        totale.setHorizontalAlignment(SwingConstants.CENTER);
        totale.setName("totale");

        //---- acconto ----
        acconto.setHorizontalAlignment(SwingConstants.CENTER);
        acconto.setName("acconto");

        //---- label4 ----
        label4.setText("Ti ringraziamo per aver scelto di utilizzare i nostri sistemi automatici.");
        label4.setName("label4");

        //---- button1 ----
        button1.setText("Chiudi");
        button1.setName("button1");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                endActionPerformed(e);
            }
        });

        PanelBuilder builder = new PanelBuilder(new FormLayout(
            "[15dlu,default], $lcgap, 100dlu, $lcgap, default:grow, $lcgap, [75dlu,default], $lcgap, default:grow, $lcgap, 100dlu, $lcgap, [15dlu,default]",
            "15dlu, $lgap, [20dlu,default], 5dlu, fill:15dlu, $lgap, fill:20dlu, 5dlu, 3*(15dlu, $lgap), [30dlu,default]:grow, $lgap, [35dlu,default], $lgap, 16dlu"), this);

        builder.add(title,              CC.xywh( 3,  3,         9,       1, CC.CENTER, CC.FILL   ));
        builder.add(label2,             CC.xywh( 3,  5,         9,       1, CC.CENTER, CC.FILL   ));
        builder.add(label3,             CC.xywh( 3,  7,         3,       1, CC.FILL  , CC.DEFAULT));
        builder.add(numeroPrenotazione, CC.xy  ( 7,  7));
        builder.add(label7,             CC.xywh( 3,  9,         9,       1, CC.CENTER, CC.FILL   ));
        builder.add(totaleLabel,        CC.xy  ( 3, 11, CC.CENTER, CC.FILL));
        builder.add(accontoLabel,       CC.xy  (11, 11, CC.CENTER, CC.FILL));
        builder.add(totale,             CC.xy  ( 3, 13,   CC.FILL, CC.FILL));
        builder.add(acconto,            CC.xy  (11, 13,   CC.FILL, CC.FILL));
        builder.add(label4,             CC.xywh( 3, 15,         9,       1, CC.CENTER, CC.FILL   ));
        builder.add(button1,            CC.xy  ( 7, 17,   CC.FILL, CC.FILL));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel title;
    private JLabel label2;
    private JLabel label3;
    private JLabel numeroPrenotazione;
    private JLabel label7;
    private JLabel totaleLabel;
    private JLabel accontoLabel;
    private JLabel totale;
    private JLabel acconto;
    private JLabel label4;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
