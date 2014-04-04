/*
 * Created by JFormDesigner on Sun Feb 16 15:43:22 CET 2014
 */

package gameshop.advance.ui.swing.customer;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.ReservationControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.utility.Money;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            Logger.getLogger(CompletedReservationPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (RemoteException ex) {
            Logger.getLogger(CompletedReservationPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ConfigurationException ex) {
            Logger.getLogger(CompletedReservationPanel.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CompletedReservationPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ConfigurationException ex) {
            Logger.getLogger(CompletedReservationPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (RemoteException ex) {
            Logger.getLogger(CompletedReservationPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        numeroPrenotazione = new JLabel();
        label7 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        totale = new JLabel();
        acconto = new JLabel();
        label4 = new JLabel();
        button1 = new JButton();

        //======== this ========
        setName("this");

        //---- label1 ----
        label1.setText("Congratulazioni: la tua prenotazione \u00e8 completata");
        label1.setFont(new Font("Dialog", Font.BOLD, 12));
        label1.setName("label1");

        //---- label2 ----
        label2.setText("Puoi pagare i prodotti prenotati in cassa fornendo il numero di prenotazione");
        label2.setName("label2");

        //---- label3 ----
        label3.setText("Numero prenotazione");
        label3.setFont(label3.getFont().deriveFont(label3.getFont().getStyle() | Font.BOLD));
        label3.setName("label3");

        //---- numeroPrenotazione ----
        numeroPrenotazione.setName("numeroPrenotazione");

        //---- label7 ----
        label7.setText("Se desideri puoi lasciare un acconto e saldare il totale quando avrai i prodotti");
        label7.setName("label7");

        //---- label5 ----
        label5.setText("Totale");
        label5.setFont(label5.getFont().deriveFont(label5.getFont().getStyle() | Font.BOLD));
        label5.setName("label5");

        //---- label6 ----
        label6.setText("Acconto");
        label6.setFont(label6.getFont().deriveFont(label6.getFont().getStyle() | Font.BOLD));
        label6.setName("label6");

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
            "[15dlu,default], $lcgap, 100dlu, $lcgap, default:grow, $lcgap, 100dlu, $lcgap, [15dlu,default]",
            "15dlu, $lgap, [20dlu,default], 5dlu, fill:15dlu, $lgap, fill:20dlu, 5dlu, 3*(15dlu, $lgap), [30dlu,default]:grow, $lgap, [20dlu,default], $lgap, 16dlu"), this);

        builder.add(label1,             CC.xywh(3,  3,         5,          1, CC.CENTER, CC.FILL));
        builder.add(label2,             CC.xywh(3,  5,         5,          1, CC.CENTER, CC.FILL));
        builder.add(label3,             CC.xy  (3,  7, CC.CENTER, CC.DEFAULT));
        builder.add(numeroPrenotazione, CC.xy  (5,  7));
        builder.add(label7,             CC.xywh(3,  9,         5,          1, CC.CENTER, CC.FILL));
        builder.add(label5,             CC.xy  (3, 11, CC.CENTER,    CC.FILL));
        builder.add(label6,             CC.xy  (7, 11, CC.CENTER,    CC.FILL));
        builder.add(totale,             CC.xy  (3, 13,   CC.FILL,    CC.FILL));
        builder.add(acconto,            CC.xy  (7, 13,   CC.FILL,    CC.FILL));
        builder.add(label4,             CC.xywh(3, 15,         5,          1, CC.CENTER, CC.FILL));
        builder.add(button1,            CC.xy  (5, 17,   CC.FILL,    CC.FILL));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel numeroPrenotazione;
    private JLabel label7;
    private JLabel label5;
    private JLabel label6;
    private JLabel totale;
    private JLabel acconto;
    private JLabel label4;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
