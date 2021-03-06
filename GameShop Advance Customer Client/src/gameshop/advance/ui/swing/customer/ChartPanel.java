/*
 * Created by JFormDesigner on Sat Apr 12 18:03:51 CEST 2014
 */

package gameshop.advance.ui.swing.customer;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.ReservationControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.interfaces.IListPanel;
import gameshop.advance.interfaces.IPopActionListener;
import gameshop.advance.technicalservices.ExceptionHandlerSingleton;
import gameshop.advance.technicalservices.LoggerSingleton;
import gameshop.advance.ui.swing.UIWindowSingleton;
import gameshop.advance.ui.swing.factory.UIFactory;
import gameshop.advance.utility.Money;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.SwingConstants;

/**
 * Schermata di Carrello per riepilogare la prenotazione fino a quel punto effettuata.
 * @author Lorenzo Di Giuseppe
 */
public class ChartPanel extends JPanel implements IListPanel{
    private IPopActionListener listener;
    private final String name = "Chart Panel";

    public ChartPanel() {
        initComponents();
        
        try
        {
            ReservationControllerSingleton.getInstance().setReservationList(this);
        } 
        catch (NullPointerException ex)
        {
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
    
    private void reserveActionPerformed(ActionEvent e) {
        try {
            ReservationControllerSingleton.getInstance().completaPrenotazione();
            this.setTotal(new Money());
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
    
    public void setListener(IPopActionListener listener){
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
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }
        catch (NullPointerException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }
        catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }
    }

    private void backActionPerformed(ActionEvent e) {
        this.listener.popPanel();
    }

    private void createUIComponents() {
        this.title = UIFactory.getInstance().getHeaderLabel();
        this.confirm = UIFactory.getInstance().getConfirmButton();
        this.totalLabel = UIFactory.getInstance().getBodyLabel();
        this.totale = UIFactory.getInstance().getBoldLabel();
        this.back = UIFactory.getInstance().getSimpleButton();
        this.cancel = UIFactory.getInstance().getCancelButton();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        createUIComponents();

        scrollPane1 = new JScrollPane();
        bookList = new JList();

        //======== this ========
        setName("this");

        //---- title ----
        title.setText("Riepilogo prenotazione");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setName("title");

        //---- totalLabel ----
        totalLabel.setText("Totale");
        totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        totalLabel.setName("totalLabel");

        //======== scrollPane1 ========
        {
            scrollPane1.setName("scrollPane1");

            //---- bookList ----
            bookList.setFont(new Font("Tahoma", Font.PLAIN, 14));
            bookList.setBackground(SystemColor.controlHighlight);
            bookList.setName("bookList");
            scrollPane1.setViewportView(bookList);
        }

        //---- totale ----
        totale.setName("totale");

        //---- back ----
        back.setText("Indietro");
        back.setName("back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backActionPerformed(e);
            }
        });

        //---- cancel ----
        cancel.setText("Cancella");
        cancel.setName("cancel");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelActionPerformed(e);
            }
        });

        //---- confirm ----
        confirm.setText("Conferma");
        confirm.setName("confirm");
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reserveActionPerformed(e);
            }
        });

        PanelBuilder builder = new PanelBuilder(new FormLayout(
            "$rgap, [75dlu,default], $lcgap, default:grow, 2*($lcgap, [75dlu,default]), $rgap",
            "$rgap, [20dlu,default], $lgap, [50dlu,default]:grow, 2*($lgap, [35dlu,default]), $rgap"), this);

        builder.add(title,       CC.xywh(4, 2,          3,       1, CC.FILL, CC.FILL));
        builder.add(totalLabel,  CC.xy  (6, 6, CC.DEFAULT, CC.FILL));
        builder.add(scrollPane1, CC.xywh(2, 4,          7,       1, CC.FILL, CC.FILL));
        builder.add(totale,      CC.xy  (8, 6,    CC.FILL, CC.FILL));
        builder.add(back,        CC.xy  (2, 8,    CC.FILL, CC.FILL));
        builder.add(cancel,      CC.xy  (6, 8,    CC.FILL, CC.FILL));
        builder.add(confirm,     CC.xy  (8, 8,    CC.FILL, CC.FILL));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel title;
    private JLabel totalLabel;
    private JScrollPane scrollPane1;
    private JList bookList;
    private JLabel totale;
    private JButton back;
    private JButton cancel;
    private JButton confirm;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    @Override
    public void setList(ListModel model, ListCellRenderer renderer)
    {
        this.bookList.setModel(model);
        this.bookList.setCellRenderer(renderer);
    }
}
