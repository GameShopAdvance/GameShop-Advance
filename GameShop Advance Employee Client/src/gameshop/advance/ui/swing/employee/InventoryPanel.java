/*
 * Created by JFormDesigner on Fri Feb 07 17:35:08 CET 2014
 */

package gameshop.advance.ui.swing.employee;


import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.InventoryControllerSingleton;
import gameshop.advance.ui.swing.DescriptionListCellRenderer;
import gameshop.advance.ui.swing.DescriptionListModel;
import gameshop.advance.ui.swing.UIWindowSingleton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.RemoteException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * @author Pippo
 */
public class InventoryPanel extends JPanel {
    
    private DescriptionListModel descList;
    
    public InventoryPanel() {
        try {
            initComponents();
            this.descriptions.setCellRenderer(new DescriptionListCellRenderer(this.descriptions.getWidth(), 40));
        } catch (IOException ex) {
            UIWindowSingleton.getInstance().displayError("Non è stato possibile inizializzare alcuni elementi grafici.");
        }
    }

    private void aggiungiProdotto(ActionEvent e) {
        try {
            Integer quantity = 1;
            try{
                quantity = Integer.parseInt(this.quantitaProdotto.getText());
            }
            catch(NumberFormatException ex)
            {
                if(!this.quantitaProdotto.getText().equals(""))
                    UIWindowSingleton.getInstance().displayError("Il formato di dato inserito per la quantità non è valido");
            }
            this.clearFields();
            InventoryControllerSingleton.getInstance().inserisciProdotto(this.codiceProdotto.getText(), quantity);
            this.descList = new DescriptionListModel(InventoryControllerSingleton.getInstance().getDescriptions());
        } catch (RemoteException ex) {
             UIWindowSingleton.getInstance().displayError("Non è possibile contattare il server. "
                    + "Si prega di riprovare. Se il problema persiste, contattare l'amministratore di sistema.");
        }
    }

    
    public void clearFields()
    {
        this.codiceProdotto.setText("");
        this.quantitaProdotto.setText("");
    }

    private void cancelButtonActionPerformed(ActionEvent e) {
        try {
            InventoryControllerSingleton.getInstance().cancel();
        } catch (RemoteException ex) {
            UIWindowSingleton.getInstance().displayError("Non è possibile contattare il server. "
                    + "Si prega di riprovare. Se il problema persiste, contattare l'amministratore di sistema.");
        }
    }
    
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        cancelButton = new JButton();
        endInventoryButton = new JButton();
        panel1 = new JPanel();
        label1 = new JLabel();
        codiceProdotto = new JTextField();
        label2 = new JLabel();
        quantitaProdotto = new JTextField();
        button1 = new JButton();
        scrollPane1 = new JScrollPane();
        descriptions = new JList();

        //======== this ========
        setName("this");

        //---- cancelButton ----
        cancelButton.setText("Annulla");
        cancelButton.setName("cancelButton");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelButtonActionPerformed(e);
            }
        });

        //---- endInventoryButton ----
        endInventoryButton.setText("Fine");
        endInventoryButton.setName("endInventoryButton");

        //======== panel1 ========
        {
            panel1.setName("panel1");

            //---- label1 ----
            label1.setText("Codice Prodotto");
            label1.setName("label1");

            //---- codiceProdotto ----
            codiceProdotto.setName("codiceProdotto");

            //---- label2 ----
            label2.setText("Quantit\u00e0");
            label2.setName("label2");

            //---- quantitaProdotto ----
            quantitaProdotto.setName("quantitaProdotto");

            //---- button1 ----
            button1.setText("Inserisci");
            button1.setName("button1");
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    aggiungiProdotto(e);
                }
            });

            PanelBuilder panel1Builder = new PanelBuilder(new FormLayout(
                "[60dlu,default], $lcgap, 50dlu, $lcgap, [60dlu,default]",
                "default, $rgap, default, $ugap, default"), panel1);

            panel1Builder.add(label1,           CC.xy  (1, 1));
            panel1Builder.add(codiceProdotto,   CC.xywh(3, 1,       3,          1, CC.FILL, CC.DEFAULT));
            panel1Builder.add(label2,           CC.xy  (1, 3));
            panel1Builder.add(quantitaProdotto, CC.xy  (3, 3, CC.FILL, CC.DEFAULT));
            panel1Builder.add(button1,          CC.xy  (3, 5));
        }

        //======== scrollPane1 ========
        {
            scrollPane1.setName("scrollPane1");

            //---- descriptions ----
            descriptions.setName("descriptions");
            scrollPane1.setViewportView(descriptions);
        }

        PanelBuilder builder = new PanelBuilder(new FormLayout(
            "45dlu, $lcgap, [120dlu,default], $lcgap, 45dlu",
            "fill:[20dlu,default], $lgap, 51dlu, $lgap, 77dlu:grow, $lgap, [20dlu,default]:grow"), this);

        builder.add(cancelButton,       CC.xy(1, 1));
        builder.add(endInventoryButton, CC.xy(5, 1));
        builder.add(panel1,             CC.xy(3, 3));
        builder.add(scrollPane1,        CC.xy(3, 5, CC.FILL, CC.FILL));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JButton cancelButton;
    private JButton endInventoryButton;
    private JPanel panel1;
    private JLabel label1;
    private JTextField codiceProdotto;
    private JLabel label2;
    private JTextField quantitaProdotto;
    private JButton button1;
    private JScrollPane scrollPane1;
    private JList descriptions;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
