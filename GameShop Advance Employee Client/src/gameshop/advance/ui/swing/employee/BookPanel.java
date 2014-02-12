/*
 * Created by JFormDesigner on Wed Feb 12 11:01:23 CET 2014
 */

package gameshop.advance.ui.swing.employee;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.BookControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.ui.swing.UIWindowSingleton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Franco
 */
public class BookPanel extends JPanel {
    public BookPanel() {
        initComponents();
    }

    private void retrieveBookActionPerformed(ActionEvent e) {
        
      try {
            Integer code = 1;
            try{
                 code = Integer.parseInt(this.bookCodeField.getText());
            }
            catch(NumberFormatException ex)
            {
                if(!this.bookCodeField.getText().equals(""))
                    UIWindowSingleton.getInstance().displayError("Il formato di dato inserito per la quantità non è valido");
            }
          try {
              BookControllerSingleton.getInstance().recuperaPrenotazione(code);
          } catch (ConfigurationException ex) {
              Logger.getLogger(BookPanel.class.getName()).log(Level.SEVERE, null, ex);
          }
        } catch (NullPointerException ex) {
            UIWindowSingleton.getInstance().displayError("Ci sono problemi di comunicazione,"
                    + " si prega di controllare la configurazione del sistema.");
        } catch (RemoteException ex) {
            UIWindowSingleton.getInstance().displayError("Non è possibile contattare il server. "
                    + "Si prega di riprovare. Se il problema persiste, contattare l'amministratore di sistema.");
        }
        
        
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        bookCodeField = new JTextField();
        label1 = new JLabel();
        retrieveBook = new JButton();
        button1 = new JButton();

        //======== this ========
        setLayout(new FormLayout(
            "$lcgap, 13dlu, 69dlu, 177dlu, $lcgap",
            "75dlu, $lgap, 24dlu, $lgap, 14dlu, 71dlu, $lgap"));
        add(bookCodeField, CC.xywh(4, 2, 1, 2));

        //---- label1 ----
        label1.setText("Codice");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 5f));
        add(label1, CC.xywh(3, 2, 1, 2));

        //---- retrieveBook ----
        retrieveBook.setText("Recupera Prenotazione");
        retrieveBook.setFont(retrieveBook.getFont().deriveFont(retrieveBook.getFont().getSize() + 6f));
        retrieveBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                retrieveBookActionPerformed(e);
            }
        });
        add(retrieveBook, CC.xywh(4, 6, 1, 2));

        //---- button1 ----
        button1.setText("text");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTextField bookCodeField;
    private JLabel label1;
    private JButton retrieveBook;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
