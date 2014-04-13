/*
 * Created by JFormDesigner on Sat Apr 12 18:03:51 CEST 2014
 */

package gameshop.advance.ui.swing.customer;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.ReservationControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.interfaces.remote.IRigaDiTransazioneRemote;
import gameshop.advance.ui.interfaces.PopActionListener;
import gameshop.advance.utility.Money;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

/**
 * @author Lorenzo Di Giuseppe
 */
public class ChartPanel extends JPanel {
    private PopActionListener listener;
    private final String name = "Chart Panel";
    private final DefaultListModel bookListModel;
    
    public ChartPanel() {
        initComponents();
        this.bookListModel = new DefaultListModel();
        this.bookListModel.addElement(null);
        this.bookList.setCellRenderer(new BookCellRenderer());
        this.bookList.setModel(this.bookListModel);
    }
    
    public void addProduct(IRigaDiTransazioneRemote rdt)
    {
        this.bookListModel.addElement(rdt);
    }
    
    public void clearList()
    {
        this.bookListModel.removeAllElements();
        this.bookListModel.addElement(null);
    }
    
    @Override
    public String getName()
    {
        return this.name;
    }
    
    private void reserveActionPerformed(ActionEvent e) {
        try {
            ReservationControllerSingleton.getInstance().completaPrenotazione();
            this.setTotal(new Money());
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
            this.clearList();
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

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label2 = new JLabel();
        totalLabel = new JLabel();
        scrollPane1 = new JScrollPane();
        bookList = new JList();
        totale = new JLabel();
        back = new JButton();
        button2 = new JButton();
        button1 = new JButton();

        //======== this ========
        setLayout(new FormLayout(
            "[15dlu,default], $lcgap, [75dlu,default], $lcgap, default:grow, 2*($lcgap, [75dlu,default]), $lcgap, [15dlu,default]",
            "[15dlu,default], $lgap, [20dlu,default], $lgap, [50dlu,default]:grow, $lgap, [20dlu,default], $lgap, [35dlu,default], $lgap, [15dlu,default]"));

        //---- label2 ----
        label2.setText("Riepilogo prenotazione");
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(label2, CC.xywh(5, 3, 3, 1, CC.FILL, CC.FILL));

        //---- totalLabel ----
        totalLabel.setText("Totale");
        totalLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(totalLabel, CC.xy(7, 7, CC.DEFAULT, CC.FILL));

        //======== scrollPane1 ========
        {

            //---- bookList ----
            bookList.setFont(new Font("Tahoma", Font.PLAIN, 14));
            bookList.setBackground(SystemColor.controlHighlight);
            scrollPane1.setViewportView(bookList);
        }
        add(scrollPane1, CC.xywh(3, 5, 7, 1, CC.FILL, CC.FILL));

        //---- totale ----
        totale.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(totale, CC.xy(9, 7, CC.FILL, CC.FILL));

        //---- back ----
        back.setText("Indietro");
        back.setFont(new Font("Tahoma", Font.PLAIN, 14));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backActionPerformed(e);
            }
        });
        add(back, CC.xy(3, 9, CC.FILL, CC.FILL));

        //---- button2 ----
        button2.setText("Cancella");
        button2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelActionPerformed(e);
            }
        });
        add(button2, CC.xy(7, 9, CC.FILL, CC.FILL));

        //---- button1 ----
        button1.setText("Conferma");
        button1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reserveActionPerformed(e);
            }
        });
        add(button1, CC.xy(9, 9, CC.FILL, CC.FILL));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label2;
    private JLabel totalLabel;
    private JScrollPane scrollPane1;
    private JList bookList;
    private JLabel totale;
    private JButton back;
    private JButton button2;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
