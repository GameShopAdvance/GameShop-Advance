/*
 * Created by JFormDesigner on Wed Feb 12 11:01:23 CET 2014
 */

package gameshop.advance.ui.swing.employee;

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.BookControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.ui.swing.UIWindowSingleton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

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
            } catch(NumberFormatException ex){
                if(!this.bookCodeField.getText().equals(""))
                    UIWindowSingleton.getInstance().displayError("Il formato di dato inserito per la quantità non è valido");
            }
            BookControllerSingleton.getInstance().recuperaPrenotazione(code);
         
        } catch (NullPointerException ex) {
            UIWindowSingleton.getInstance().displayError("Ci sono problemi di comunicazione,"
                    + " si prega di controllare la configurazione del sistema.");
        } catch (RemoteException ex) {
            UIWindowSingleton.getInstance().displayError("Non è possibile contattare il server. "
                    + "Si prega di riprovare. Se il problema persiste, contattare l'amministratore di sistema.");
        } catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError("Ci sono problemi nella lettura del file di configurazione: "+ex.getConfigurationPath()+"."
                    + " Per maggiori informazioni rivolgersi all'amministratore di sistema.");
        }
        
        
    }

    private void clearSaleActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void goToPaymentButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void addProductButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void insertClientCodeActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel3 = new JPanel();
        clearBook = new JButton();
        label1 = new JLabel();
        total = new JTextField();
        button2 = new JButton();
        separator1 = new JSeparator();
        panel2 = new JPanel();
        label2 = new JLabel();
        bookCodeField = new JTextField();
        panel1 = new JPanel();
        label4 = new JLabel();
        clientCode = new JTextField();
        insertClientCode = new JButton();

        //======== panel3 ========
        {
            panel3.setLayout(new FormLayout(
                "5dlu, 65dlu, [20dlu,default]:grow, 65dlu, $lcgap, 61dlu, $lcgap, 56dlu, [20dlu,default]:grow, 65dlu, 5dlu",
                "5dlu, fill:[20dlu,default], $lgap, [20dlu,default,40dlu], [20dlu,default], fill:[15dlu,default,40dlu], 60dlu, fill:default:grow"));

            //---- clearBook ----
            clearBook.setText("Annulla");
            clearBook.setBackground(new Color(255, 51, 0));
            clearBook.setFont(clearBook.getFont().deriveFont(clearBook.getFont().getStyle() | Font.BOLD));
            clearBook.setForeground(Color.white);
            clearBook.setIcon(null);
            clearBook.setNextFocusableComponent(button2);
            panel3.add(clearBook, CC.xy(2, 2, CC.FILL, CC.FILL));

            //---- label1 ----
            label1.setText("Totale");
            label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 1f));
            label1.setLabelFor(total);
            panel3.add(label1, CC.xy(4, 2));

            //---- total ----
            total.setEditable(false);
            panel3.add(total, CC.xy(6, 2, CC.DEFAULT, CC.FILL));

            //---- button2 ----
            button2.setText("Avanti");
            button2.setBackground(new Color(102, 204, 0));
            button2.setFont(button2.getFont().deriveFont(button2.getFont().getStyle() | Font.BOLD));
            button2.setName("nextButton");
            button2.setNextFocusableComponent(bookCodeField);
            panel3.add(button2, CC.xy(10, 2));
            panel3.add(separator1, CC.xywh(4, 4, 5, 1));

            //======== panel2 ========
            {
                panel2.setBorder(new CompoundBorder(
                    new TitledBorder("Prenotazione"),
                    Borders.DLU2_BORDER));
                panel2.setLayout(new FormLayout(
                    "65dlu, $lcgap, 53dlu:grow, $lcgap, 49dlu:grow",
                    "[20dlu,default], $rgap, [20dlu,default]"));

                //---- label2 ----
                label2.setText("Codice ");
                label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 1f));
                label2.setLabelFor(bookCodeField);
                panel2.add(label2, CC.xy(1, 1));
                panel2.add(bookCodeField, CC.xywh(3, 1, 3, 1, CC.DEFAULT, CC.FILL));
            }
            panel3.add(panel2, CC.xywh(4, 5, 5, 1));

            //======== panel1 ========
            {
                panel1.setBorder(new CompoundBorder(
                    new TitledBorder("Carta Cliente"),
                    Borders.DLU2_BORDER));
                panel1.setLayout(new FormLayout(
                    "[67dlu,default], $lcgap, 110dlu",
                    "[20dlu,default], $lgap, default"));

                //---- label4 ----
                label4.setText("Codice");
                label4.setFont(label4.getFont().deriveFont(label4.getFont().getSize() + 1f));
                label4.setLabelFor(clientCode);
                panel1.add(label4, CC.xy(1, 1));

                //---- clientCode ----
                clientCode.setNextFocusableComponent(insertClientCode);
                panel1.add(clientCode, CC.xy(3, 1, CC.FILL, CC.FILL));

                //---- insertClientCode ----
                insertClientCode.setText("Inserisci");
                insertClientCode.setForeground(Color.black);
                insertClientCode.setFont(insertClientCode.getFont().deriveFont(insertClientCode.getFont().getStyle() & ~Font.BOLD));
                insertClientCode.setNextFocusableComponent(button2);
                insertClientCode.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        insertClientCodeActionPerformed(e);
                    }
                });
                panel1.add(insertClientCode, CC.xy(3, 3));
            }
            panel3.add(panel1, CC.xywh(4, 7, 5, 1));
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel3;
    private JButton clearBook;
    private JLabel label1;
    private JTextField total;
    private JButton button2;
    private JSeparator separator1;
    private JPanel panel2;
    private JLabel label2;
    private JTextField bookCodeField;
    private JPanel panel1;
    private JLabel label4;
    private JTextField clientCode;
    private JButton insertClientCode;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
