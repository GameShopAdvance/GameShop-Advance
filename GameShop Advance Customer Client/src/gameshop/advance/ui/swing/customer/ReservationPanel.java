/*
 * Created by JFormDesigner on Wed Feb 12 09:56:25 CET 2014
 */

package gameshop.advance.ui.swing.customer;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.PrenotaProdottoController;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.interfaces.remote.IDescrizioneProdottoRemote;
import gameshop.advance.interfaces.remote.IIteratorWrapperRemote;
import gameshop.advance.ui.swing.UIWindowSingleton;
import gameshop.advance.utility.Money;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import org.joda.time.DateTime;

/**
 * @author Matteo Gentile
 */
public class ReservationPanel extends JPanel {
    
    private final String[] columnNames = {"Id", "Descrizione", "Prezzo", "Prenota","Subtotale"};
    private final int MAX_QUANTITY  = 10;
    
     
    public ReservationPanel() {
        try {
            initComponents();
            this.DisplayData(PrenotaProdottoController.getInstance().getDescriptionList());
            this.setUpReservationColumn( this.table1.getColumnModel().getColumn(3));
            this.setUpSubtotalColumn( this.table1.getColumnModel().getColumn(4));
        } catch (RemoteException ex) {
            Logger.getLogger(ReservationPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            Logger.getLogger(ReservationPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConfigurationException ex) {
            Logger.getLogger(ReservationPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

 public void setUpReservationColumn(TableColumn rColumn) {
        JComboBox comboBox = new JComboBox();
        
        for (int i=0; i<=MAX_QUANTITY; i++){
            comboBox.addItem(i);
        }
        
        rColumn.setCellEditor(new DefaultCellEditor(comboBox));
        comboBox.setSelectedItem(0);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setToolTipText("Clicca per sceglere le copie da prenotare");
        rColumn.setCellRenderer(renderer);
    }
     
     private void DisplayData(IIteratorWrapperRemote<IDescrizioneProdottoRemote> ProductsList) {
        try {
            DefaultTableModel aModel = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return column > 2;
                }
            };
           
            final Object[] names = this.columnNames;
            
            aModel.setColumnIdentifiers(names);
            if (ProductsList == null) {
                this.table1.setModel(aModel);
                return;
            }
            
            Object[] objects = new Object[3];
            IIteratorWrapperRemote<IDescrizioneProdottoRemote> products = ProductsList;
            // Populazione della TableModel
            while (products.hasNext()) {
                try {
                    IDescrizioneProdottoRemote p = products.next();
                    objects[0] = p.getCodiceProdotto();
                    objects[1] = p.getDescrizione();
                    objects[2] = p.getPrezzo(new DateTime());
                   
                    aModel.addRow(objects);
                } catch (RemoteException ex) {
                    Logger.getLogger(ReservationPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.table1.setModel(aModel);
            setTableModelListener();
            
        } catch (RemoteException ex) {
            Logger.getLogger(ReservationPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
     
};
     
     private void setTableModelListener(){
        TableModelListener tableModelListener;
        tableModelListener = new TableModelListener() {
            private Money total = new Money();
            private Money subtotal = new Money();
           
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE)
                {
                    int row = e.getFirstRow();
                    int column = e.getColumn();
                    int quantity = 0;
                    
                    if (column == 0 || column == 2 ||column == 3)
                    {   
                       
                        TableModel myModel = (TableModel)e.getSource();
                        try {
                            quantity = ((Integer) myModel.getValueAt(row, 3)).intValue();
                        }
                        catch(NullPointerException ex){  
                            UIWindowSingleton.getInstance().displayError("Attenzione seleziona una quantità valida");
                        }
                        Money price = (Money) myModel.getValueAt(row, 2);
                        Money old_subtotal = (Money) myModel.getValueAt(row, 4);
                        
                        if (old_subtotal == null)
                                old_subtotal = new Money();
                        
                         subtotal = price.multiply(quantity);
                         myModel.setValueAt(subtotal, row, 4);
                         
                            if (subtotal.equals(old_subtotal))
                                total = subtotal;
                            else
                                total = total.add(subtotal.subtract(old_subtotal));
                        
                            try {
                            PrenotaProdottoController.getInstance().inserisciProdotti(myModel.getValueAt(row, 0), quantity);
                        } catch ( NullPointerException | RemoteException | ConfigurationException ex) {
                            Logger.getLogger(ReservationPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                 
                textField1.setText(total.toString());
            }         
        };
            table1.getModel().addTableModelListener(tableModelListener);
     }
     
      private void setUpSubtotalColumn( TableColumn column) {
        JTextField jtextField3;
        jtextField3 = new JTextField();
        column.setCellEditor(new DefaultCellEditor(jtextField3));

        //Set up cells
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        column.setCellRenderer(renderer);
    }

private void clearReservationActionPerformed(ActionEvent e) {
        try {
            PrenotaProdottoController.getInstance().clearReservation();
        } catch (NullPointerException ex) {
            Logger.getLogger(ReservationPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ReservationPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConfigurationException ex) {
            Logger.getLogger(ReservationPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
}

private void completeReservationActionPerformed(ActionEvent e) {
        try {
            PrenotaProdottoController.getInstance().completaPrenotazione();
        } catch (NullPointerException ex) {
            Logger.getLogger(ReservationPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ReservationPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConfigurationException ex) {
            Logger.getLogger(ReservationPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
}

    
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        button1 = new JButton();
        label1 = new JLabel();
        button2 = new JButton();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        panel1 = new JPanel();
        label2 = new JLabel();
        textField1 = new JTextField();

        //======== this ========
        setLayout(new FormLayout(
            "[20dlu,default], $lcgap, [45dlu,min], $lcgap, [120dlu,min]:grow, $lcgap, [45dlu,min], $lcgap, [20dlu,default]",
            "[20dlu,default], $lgap, [30dlu,min], $lgap, 80dlu:grow, 2*($lgap, [25dlu,default]), $lgap, [25dlu,default]:grow"));

        //---- button1 ----
        button1.setText("Indietro");
        button1.setBackground(new Color(255, 51, 0));
        button1.setFont(button1.getFont().deriveFont(button1.getFont().getStyle() | Font.BOLD));
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearReservationActionPerformed(e);
            }
        });
        add(button1, CC.xy(3, 3, CC.DEFAULT, CC.FILL));

        //---- label1 ----
        label1.setText("Prodotti");
        add(label1, CC.xy(5, 3, CC.CENTER, CC.DEFAULT));

        //---- button2 ----
        button2.setText("Completa");
        button2.setBackground(new Color(102, 204, 0));
        button2.setFont(button2.getFont().deriveFont(button2.getFont().getStyle() | Font.BOLD));
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                completeReservationActionPerformed(e);
            }
        });
        add(button2, CC.xy(7, 3, CC.DEFAULT, CC.FILL));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        add(scrollPane1, CC.xy(5, 5));

        //======== panel1 ========
        {
            panel1.setLayout(new FormLayout(
                "50dlu, $lcgap, [70dlu,default]",
                "default"));

            //---- label2 ----
            label2.setText("Totale:");
            panel1.add(label2, CC.xy(1, 1));
            panel1.add(textField1, CC.xy(3, 1));
        }
        add(panel1, CC.xy(5, 9));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JButton button1;
    private JLabel label1;
    private JButton button2;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JPanel panel1;
    private JLabel label2;
    private JTextField textField1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

   
}
