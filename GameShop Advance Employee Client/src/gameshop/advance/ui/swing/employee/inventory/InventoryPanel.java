/*
 * Created by JFormDesigner on Fri Feb 07 17:35:08 CET 2014
 */

package gameshop.advance.ui.swing.employee.inventory;


import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.InventoryControllerSingleton;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.exceptions.QuantityException;
import gameshop.advance.exceptions.db.ObjectAlreadyExistsDbException;
import gameshop.advance.exceptions.products.ProdottoNotFoundException;
import gameshop.advance.interfaces.IListPanel;
import gameshop.advance.technicalservices.ExceptionHandlerSingleton;
import gameshop.advance.technicalservices.LoggerSingleton;
import gameshop.advance.ui.swing.UIWindowSingleton;
import gameshop.advance.ui.swing.factory.UIFactory;
import gameshop.advance.ui.swing.utility.UIStyleSingleton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;

/**
 * Schermata dell'inventario.Permette al commesso di registrare i nuovi prodotti arrivati al negozio.
 * @author Matteo Gentile
 */
public class InventoryPanel extends JPanel implements IListPanel {
        
    private final String[] columnNames = {"id", "descrizione", "quantità aggiunta"};
    
    public InventoryPanel() {
        initComponents();
        this.cancelButton.setBackground(UIStyleSingleton.getInstance().getAlertColor());
        this.cancelButton.setForeground(UIStyleSingleton.getInstance().getButtonTextColor());
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
                    UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
                LoggerSingleton.getInstance().log(ex);
            }
            
            InventoryControllerSingleton.getInstance().inserisciProdotto(this.codiceProdotto.getText(), quantity);
            this.clearFields();
        } catch (RemoteException | NotBoundException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        } catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }catch (QuantityException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        } catch (ProdottoNotFoundException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
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
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        } catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }
    }

    private void endInventoryButtonActionPerformed(ActionEvent e) {
        try {
            InventoryControllerSingleton.getInstance().terminaInventario();
        } catch (ConfigurationException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        } catch (RemoteException | NotBoundException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }
        catch (ObjectAlreadyExistsDbException ex) {
            UIWindowSingleton.getInstance().displayError(ExceptionHandlerSingleton.getInstance().getMessage(ex));
            LoggerSingleton.getInstance().log(ex);
        }
    }

    private void createUIComponents() {
        this.button1 = UIFactory.getInstance().getSimpleButton();
        this.cancelButton = UIFactory.getInstance().getCancelButton();
        this.endInventoryButton = UIFactory.getInstance().getConfirmButton();
        this.label1 = UIFactory.getInstance().getBodyLabel();
        this.label2 = UIFactory.getInstance().getBodyLabel();
    }
    
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        createUIComponents();

        panel1 = new JPanel();
        codiceProdotto = new JTextField();
        quantitaProdotto = new JTextField();
        scrollPane1 = new JScrollPane();
        insertedList = new JList();

        //======== this ========
        setName("this");

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
                "70dlu, $lcgap, 80dlu, $lcgap, [75dlu,min]",
                "fill:[40px,min], $rgap, fill:[40px,default]"), panel1);

            panel1Builder.add(label1,           CC.xy  (1, 1));
            panel1Builder.add(codiceProdotto,   CC.xywh(3, 1,       3,          1, CC.FILL, CC.DEFAULT));
            panel1Builder.add(label2,           CC.xy  (1, 3));
            panel1Builder.add(quantitaProdotto, CC.xy  (3, 3, CC.FILL, CC.DEFAULT));
            panel1Builder.add(button1,          CC.xy  (5, 3));
        }

        //======== scrollPane1 ========
        {
            scrollPane1.setName("scrollPane1");

            //---- insertedList ----
            insertedList.setName("insertedList");
            scrollPane1.setViewportView(insertedList);
        }

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

        PanelBuilder builder = new PanelBuilder(new FormLayout(
            "$lcgap, [15dlu,default]:grow, $lcgap, [75dlu,min], $lcgap, [200dlu,min], $lcgap, [75dlu,min], $lcgap, [15dlu,default]:grow",
            "[15dlu,default]:grow, $lgap, 68dlu, $lgap, [200px,min], $lgap, fill:[35dlu,min], $lgap, [15dlu,default]:grow"), this);

        builder.add(panel1,             CC.xy  (6, 3));
        builder.add(scrollPane1,        CC.xywh(4, 5, 5, 1, CC.FILL, CC.FILL));
        builder.add(cancelButton,       CC.xy  (4, 7));
        builder.add(endInventoryButton, CC.xy  (8, 7));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JLabel label1;
    private JTextField codiceProdotto;
    private JLabel label2;
    private JTextField quantitaProdotto;
    private JButton button1;
    private JScrollPane scrollPane1;
    private JList insertedList;
    private JButton cancelButton;
    private JButton endInventoryButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    @Override
    public void setList(ListModel model, ListCellRenderer renderer) {
        this.insertedList.setModel(model);
        this.insertedList.setCellRenderer(renderer);
    }

    
}
