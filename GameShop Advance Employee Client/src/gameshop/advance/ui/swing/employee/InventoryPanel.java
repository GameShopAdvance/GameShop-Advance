/*
 * Created by JFormDesigner on Fri Feb 07 17:35:08 CET 2014
 */

package gameshop.advance.ui.swing.employee;


import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.InventoryControllerSingleton;
import gameshop.advance.controller.valueData.AggiuntaProdotti;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.exceptions.ProdottoNotFoundException;
import gameshop.advance.exceptions.QuantityException;
import gameshop.advance.ui.swing.UIWindowSingleton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

/**
 * @author Pippo
 */
public class InventoryPanel extends JPanel {
        
    private final String[] columnNames = {"id", "descrizione", "quantità aggiunta"};
    
    public InventoryPanel() {
        initComponents();
        this.refreshTable();
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
            
            InventoryControllerSingleton.getInstance().inserisciProdotto(this.codiceProdotto.getText(), quantity);
            this.clearFields();
            this.refreshTable();
        } catch (RemoteException | NotBoundException ex) {
             UIWindowSingleton.getInstance().displayError("Non è possibile contattare il server. "
                    + "Si prega di riprovare. Se il problema persiste, contattare l'amministratore di sistema.");
        } catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError("Ci sono problemi nella lettura del file di configurazione: "+ex.getConfigurationPath()+"."
                    + " Per maggiori informazioni rivolgersi all'amministratore di sistema.");
        }catch (QuantityException ex) {
            UIWindowSingleton.getInstance().displayError("La quantità inserita "+ this.quantitaProdotto.getText() +" non è valida.");
        } catch (ProdottoNotFoundException ex) {
            UIWindowSingleton.getInstance().displayError("Il codice prodotto inserito "+this.codiceProdotto.getText()+" non è valido.");
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
        } catch (RemoteException | NotBoundException ex) {
            UIWindowSingleton.getInstance().displayError("Non è possibile contattare il server. "
                    + "Si prega di riprovare. Se il problema persiste, contattare l'amministratore di sistema.");
        } catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError("Ci sono problemi nella lettura del file di configurazione: "+ex.getConfigurationPath()+"."
                    + " Per maggiori informazioni rivolgersi all'amministratore di sistema.");
        }
    }

    private void endInventoryButtonActionPerformed(ActionEvent e) {
        try {
            InventoryControllerSingleton.getInstance().terminaInventario();
        } catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError("Ci sono problemi nella lettura del file di configurazione: "+ex.getConfigurationPath()+"."
                    + " Per maggiori informazioni rivolgersi all'amministratore di sistema.");
        } catch (RemoteException | NotBoundException ex) {
            UIWindowSingleton.getInstance().displayError("Non è possibile contattare il server. "
                    + "Si prega di riprovare. Se il problema persiste, contattare l'amministratore di sistema.");
        }
    }

    private void createUIComponents() {
        // TODO: add custom component creation code here
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
        panel = new JScrollPane();
        table = new JTable();

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
        endInventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                endInventoryButtonActionPerformed(e);
            }
        });

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

        //======== panel ========
        {
            panel.setName("panel");

            //---- table ----
            table.setName("table");
            panel.setViewportView(table);
        }

        PanelBuilder builder = new PanelBuilder(new FormLayout(
            "45dlu, $lcgap, [120dlu,default], $lcgap, 45dlu",
            "fill:[20dlu,default], $lgap, 51dlu, $lgap, 77dlu:grow, $lgap, [20dlu,default]:grow"), this);

        builder.add(cancelButton,       CC.xy(1, 1));
        builder.add(endInventoryButton, CC.xy(5, 1));
        builder.add(panel1,             CC.xy(3, 3));
        builder.add(panel,              CC.xy(3, 5, CC.FILL, CC.FILL));
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
    private JScrollPane panel;
    private JTable table;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private void refreshTable() {
        try {
            Collection<AggiuntaProdotti> descriptions = InventoryControllerSingleton.getInstance().getDescriptionList();
            final Object[] aggiunte = descriptions.toArray();
            for(Object o: aggiunte)
                System.err.println("Aggiunte "+o.toString());
            final String[] names = this.columnNames;
            this.table.setModel(new AbstractTableModel() {

                @Override
                public String getColumnName(int column){
                    return names[column];
                }
                
                @Override
                public int getRowCount() {
                    return aggiunte.length;
                }

                @Override
                public int getColumnCount() {
                    return names.length;
                }

                @Override
                public Object getValueAt(int rowIndex, int columnIndex) {
                    try {
                        AggiuntaProdotti ap = (AggiuntaProdotti) aggiunte[rowIndex];
                        switch(columnIndex)
                        {
                            case 0: return ap.getId();
                            case 1: return ap.getDescrizione();
                            case 2: return ap.getAddedQuantity();
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(InventoryPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return null;
                }
            });
        } catch (RemoteException | NotBoundException ex) {
            UIWindowSingleton.getInstance().displayError("Non è possibile contattare il server. "
                    + "Si prega di riprovare. Se il problema persiste, contattare l'amministratore di sistema.");
        } catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError("Ci sono problemi nella lettura del file di configurazione: "+ex.getConfigurationPath()+"."
                    + " Per maggiori informazioni rivolgersi all'amministratore di sistema.");
        }
    }
}