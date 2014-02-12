/*
 * Created by JFormDesigner on Wed Feb 12 09:56:25 CET 2014
 */

package gameshop.advance.ui.swing.customer;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.PrenotaProdottoController;
import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.interfaces.remote.IDescrizioneProdottoRemote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 * @author Matteog
 */
public class ReservationPanel extends JPanel {
    
    private final String[] columnNames = {"Id", "Descrizione", "Prezzo", "Prenota"};
    private Object[] prodotti;
     
    public ReservationPanel() {
        initComponents();
        this.refreshTable();
    }
    
    
    
     private void refreshTable()  {
        try {
            final String[] names = this.columnNames;
            Collection<IDescrizioneProdottoRemote> descriptionList = PrenotaProdottoController.getInstance().getDescriptionList();
            prodotti = descriptionList.toArray();
                    this.table1.setModel(new AbstractTableModel() {
           
                        @Override
                        public String getColumnName(int column){
                            return names[column];
                        }
                        
                        @Override
                        public int getRowCount() {
                            return prodotti.length;
                        }
                        
                        @Override
                        public int getColumnCount() {
                            return names.length;
                        }
                   
                        @Override
                        public boolean isCellEditable(int row, int col) {
                            return (col == 3); 
                        }
                        
                        @Override
                        public Class getColumnClass(int column) {
                                return (getValueAt(0, column).getClass());
                        }
                        
                        @Override
                        public Object getValueAt(int rowIndex, int columnIndex) {
                            try {
                               IDescrizioneProdottoRemote mp = (IDescrizioneProdottoRemote) prodotti[rowIndex];
                                switch(columnIndex)
                                {
                                    case 0: return mp.getCodiceProdotto();
                                    case 1: return mp.getDescrizione();
                                    case 2: return mp.getPrezzo();
                                    case 3: return Boolean.false;
                                }
                                return null;
                            } catch (RemoteException ex) {
                                Logger.getLogger(ReservationPanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            return null;
                        }
                        
                        
                    });
        } catch (NullPointerException ex) {
            Logger.getLogger(ReservationPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConfigurationException ex) {
            Logger.getLogger(ReservationPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
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

        //======== this ========
        setLayout(new FormLayout(
            "[45dlu,min], $lcgap, [120dlu,min]:grow, $lcgap, [45dlu,min]",
            "[30dlu,min], $lgap, 80dlu:grow, $lgap, [20dlu,default]:grow"));

        //---- button1 ----
        button1.setText("Indietro");
        add(button1, CC.xy(1, 1));

        //---- label1 ----
        label1.setText("Prodotti");
        add(label1, CC.xy(3, 1, CC.CENTER, CC.DEFAULT));

        //---- button2 ----
        button2.setText("Avanti");
        add(button2, CC.xy(5, 1));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        add(scrollPane1, CC.xy(3, 3));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JButton button1;
    private JLabel label1;
    private JButton button2;
    private JScrollPane scrollPane1;
    private JTable table1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
