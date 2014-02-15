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
    
    private final String[] columnNames = {"Id", "Descrizione", "Prezzo", "Prenota"};
    
     
    public ReservationPanel() {
        try {
            initComponents();
            this.DisplayData(PrenotaProdottoController.getInstance().getDescriptionList());
            this.setUpReservationColumn(this.table1, this.table1.getColumnModel().getColumn(3));
        } catch (RemoteException ex) {
            Logger.getLogger(ReservationPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            Logger.getLogger(ReservationPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConfigurationException ex) {
            Logger.getLogger(ReservationPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

 public void setUpReservationColumn(JTable table, TableColumn rColumn) {
        //Set up the editor for the sport cells.
        JComboBox comboBox = new JComboBox();
        comboBox.addItem(1);
        comboBox.addItem(2);
        comboBox.addItem(3);
        comboBox.addItem(4);
        comboBox.addItem(5);
        comboBox.addItem(6);
        rColumn.setCellEditor(new DefaultCellEditor(comboBox));

        //Set up cells
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setToolTipText("Click per sceglere le copie");
        rColumn.setCellRenderer(renderer);
    }
     
     private void DisplayData(IIteratorWrapperRemote<IDescrizioneProdottoRemote> ProductsList) {
        try {
            DefaultTableModel aModel = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    if( column > 2 )
                            return true;
                    else
                        return false;
                }
            };
            //setting the column name
            final Object[] names = this.columnNames;
            
            
            aModel.setColumnIdentifiers(names);
            if (ProductsList == null) {
                this.table1.setModel(aModel);
                return;
            }
            
            Object[] objects = new Object[3];
            IIteratorWrapperRemote<IDescrizioneProdottoRemote> products = ProductsList;
            //populating the tablemodel
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
            
             aModel.addTableModelListener( new TableModelListener() {
               private Money total = new Money();

                @Override
                public void tableChanged(TableModelEvent e) {
                   if (e.getType() == TableModelEvent.UPDATE)
                    {
                        int row = e.getFirstRow();
                        int column = e.getColumn();
                        
                        int quantity = 0;
                        
                        if (column == 2 ||column == 3)
                        {
                            TableModel aModel = (TableModel)e.getSource();
                            quantity = ((Integer) aModel.getValueAt(row, 3)).intValue();
                            System.err.println("Row:"+quantity );
                            Money price = ((Money) aModel.getValueAt(row, 2));
                            total = total.add(price.multiply(quantity));
                           
                         }
                                
                               
                            
                            
                            //aModel.setValueAt(quantity, row, 3);
                        }
                         textField1.setText(total.toString());
                        
                    }
                
            });
           
            this.table1.setModel(aModel);
        } catch (RemoteException ex) {
            Logger.getLogger(ReservationPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
     
};

private void clearReservationActionPerformed(ActionEvent e) {
        try {
            PrenotaProdottoController.getInstance().clearReservation();// TODO add your code here
        } catch (NullPointerException ex) {
            Logger.getLogger(ReservationPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ReservationPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConfigurationException ex) {
            Logger.getLogger(ReservationPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
}

private void goToReviewActionPerformed(ActionEvent e) {
        try {
            PrenotaProdottoController.getInstance().riepilogoPrenotazione();
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
        button2.setText("Avanti");
        button2.setBackground(new Color(102, 204, 0));
        button2.setFont(button2.getFont().deriveFont(button2.getFont().getStyle() | Font.BOLD));
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToReviewActionPerformed(e);
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
