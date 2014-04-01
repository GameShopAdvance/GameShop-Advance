/*
 * Created by JFormDesigner on Tue Apr 01 09:02:03 CEST 2014
 */

package gameshop.advance.ui.swing.customer;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.ReservationControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.ui.interfaces.PopActionListener;
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

/**
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class ChartPanel extends JPanel implements ActionListener {
    private PopActionListener listener;
    private final String name = "Chart Panel";
    private CompletedReservationPanel completedPanel;
    
    public ChartPanel() {
        initComponents();
        this.completedPanel = new CompletedReservationPanel();
        this.completedPanel.addActionListener(this);
        this.add(this.completedPanel);
        CardLayout layout = (CardLayout) this.getLayout();
        layout.addLayoutComponent(this.completedPanel, this.completedPanel.getName());
        
    }
    
    @Override
    public String getName()
    {
        return this.name;
    }
    
    private void reserveActionPerformed(ActionEvent e) {
        try {
            ReservationControllerSingleton.getInstance().completaPrenotazione();
            this.completedPanel.setID(ReservationControllerSingleton.getInstance().getID());
            this.completedPanel.setTotal(ReservationControllerSingleton.getInstance().getTotal());
            this.completedPanel.setPartial(ReservationControllerSingleton.getInstance().getPartial());
            CardLayout layout = (CardLayout) this.getLayout();
            layout.show(this, this.completedPanel.getName());
        }
        catch (NullPointerException ex) {
            Logger.getLogger(ChartPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ConfigurationException ex) {
            Logger.getLogger(ChartPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (RemoteException ex) {
            Logger.getLogger(ChartPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setListener(PopActionListener listener){
        this.listener = listener;
    }
    
    public void setTotal(Money m)
    {
        this.totale.setText(m.toString());
    }

    private void cancelActionPerformed(ActionEvent e)
    {
        try {
            ReservationControllerSingleton.getInstance().cancellaPrenotazione();
            this.listener.popPanel();
        }
        catch (RemoteException ex) {
            Logger.getLogger(ChartPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NullPointerException ex) {
            Logger.getLogger(ChartPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ConfigurationException ex) {
            Logger.getLogger(ChartPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void backActionPerformed(ActionEvent e) {
        this.listener.popPanel();
    }

    private void button1ActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        this.panel1 = new JPanel();
        this.label2 = new JLabel();
        this.scrollPane1 = new JScrollPane();
        this.list1 = new JList();
        this.label1 = new JLabel();
        this.totale = new JLabel();
        this.back = new JButton();
        this.button2 = new JButton();
        this.button1 = new JButton();

        //======== this ========
        setName("this");
        setLayout(new CardLayout());

        //======== panel1 ========
        {
            this.panel1.setName("panel1");

            //---- label2 ----
            this.label2.setText("Riepilogo prenotazione");
            this.label2.setName("label2");

            //======== scrollPane1 ========
            {
                this.scrollPane1.setName("scrollPane1");

                //---- list1 ----
                this.list1.setName("list1");
                this.scrollPane1.setViewportView(this.list1);
            }

            //---- label1 ----
            this.label1.setText("Totale");
            this.label1.setName("label1");

            //---- totale ----
            this.totale.setName("totale");

            //---- back ----
            this.back.setText("Indietro");
            this.back.setName("back");
            this.back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    backActionPerformed(e);
                }
            });

            //---- button2 ----
            this.button2.setText("Cancella");
            this.button2.setName("button2");
            this.button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cancelActionPerformed(e);
                }
            });

            //---- button1 ----
            this.button1.setText("Conferma");
            this.button1.setName("button1");
            this.button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    reserveActionPerformed(e);
                }
            });

            PanelBuilder panel1Builder = new PanelBuilder(new FormLayout(
                "[15dlu,default], $lcgap, 73dlu, $lcgap, 5dlu:grow, 2*($lcgap, 73dlu), $lcgap, [15dlu,default]",
                "[15dlu,default], $lgap, [20dlu,default], $lgap, default:grow, 2*($lgap, [20dlu,default]), $lgap, 15dlu"), this.panel1);

            panel1Builder.add(this.label2,      CC.xywh(3, 3,       7,       1, CC.CENTER , CC.FILL));
            panel1Builder.add(this.scrollPane1, CC.xywh(3, 5,       7,       1, CC.FILL   , CC.FILL));
            panel1Builder.add(this.label1,      CC.xywh(3, 7,       5,       1, CC.DEFAULT, CC.FILL));
            panel1Builder.add(this.totale,      CC.xy  (9, 7, CC.FILL, CC.FILL));
            panel1Builder.add(this.back,        CC.xy  (3, 9, CC.FILL, CC.FILL));
            panel1Builder.add(this.button2,     CC.xy  (7, 9, CC.FILL, CC.FILL));
            panel1Builder.add(this.button1,     CC.xy  (9, 9, CC.FILL, CC.FILL));
        }
        add(this.panel1, "card1");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JLabel label2;
    private JScrollPane scrollPane1;
    private JList list1;
    private JLabel label1;
    private JLabel totale;
    private JButton back;
    private JButton button2;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        this.listener.popPanel();
        CardLayout layout = (CardLayout) this.getLayout();
        layout.first(this);
    }



}
