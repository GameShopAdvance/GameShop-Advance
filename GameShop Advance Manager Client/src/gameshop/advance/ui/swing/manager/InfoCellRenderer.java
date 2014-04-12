/*
 * Created by JFormDesigner on Sun Mar 30 09:50:48 CEST 2014
 */

package gameshop.advance.ui.swing.manager;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.interfaces.remote.IInformazioniProdottoRemote;
import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

/**
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class InfoCellRenderer extends JPanel implements ListCellRenderer<IInformazioniProdottoRemote>{
    
    public InfoCellRenderer(){
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        title = new JTextField();
        prenotazioni = new JTextField();
        quantitaPrenotazioni = new JTextField();
        disponibilità = new JTextField();
        quantitaDisponibile = new JTextField();
        Ordine = new JTextField();
        numeroOrdine = new JTextField();

        //======== this ========
        setLayout(new FormLayout(
            "$lcgap, 69dlu, $lcgap, 90dlu, $lcgap, [56dlu,default], 2*($lcgap), 43dlu, $lcgap, 45dlu, $lcgap",
            "$lgap, fill:[27dlu,default], $lgap, fill:26dlu, $lgap"));

        //---- title ----
        title.setEditable(false);
        title.setText("Titolo");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title, CC.xywh(2, 2, 1, 3));

        //---- prenotazioni ----
        prenotazioni.setEditable(false);
        prenotazioni.setText("Prenotazioni");
        prenotazioni.setHorizontalAlignment(SwingConstants.CENTER);
        add(prenotazioni, CC.xy(4, 2));

        //---- quantitaPrenotazioni ----
        quantitaPrenotazioni.setText("#qty");
        quantitaPrenotazioni.setHorizontalAlignment(SwingConstants.CENTER);
        quantitaPrenotazioni.setEditable(false);
        add(quantitaPrenotazioni, CC.xy(6, 2));

        //---- disponibilità ----
        disponibilità.setEditable(false);
        disponibilità.setText("Disponibilit\u00e0");
        disponibilità.setHorizontalAlignment(SwingConstants.CENTER);
        add(disponibilità, CC.xy(4, 4));

        //---- quantitaDisponibile ----
        quantitaDisponibile.setEditable(false);
        quantitaDisponibile.setText("#qty");
        quantitaDisponibile.setHorizontalAlignment(SwingConstants.CENTER);
        add(quantitaDisponibile, CC.xy(6, 4));

        //---- Ordine ----
        Ordine.setText("Ordine");
        Ordine.setEditable(false);
        Ordine.setHorizontalAlignment(SwingConstants.CENTER);
        add(Ordine, CC.xy(9, 4));
        add(numeroOrdine, CC.xy(11, 4));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTextField title;
    private JTextField prenotazioni;
    private JTextField quantitaPrenotazioni;
    private JTextField disponibilità;
    private JTextField quantitaDisponibile;
    private JTextField Ordine;
    private JTextField numeroOrdine;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    @Override
    public Component getListCellRendererComponent(JList<? extends IInformazioniProdottoRemote> list, IInformazioniProdottoRemote value, int index, boolean isSelected, boolean cellHasFocus) {
        try {
            this.title.setText(value.getDescrizione().getDescrizione());
        }
        catch (Exception ex) {
            Logger.getLogger(InfoCellRenderer.class.getName()).log(Level.SEVERE, null, ex);
            this.title.setText("???");
        }
         
        try {
             this.quantitaPrenotazioni.setText(""+value.getPrenotati());
        }
        catch (Exception ex) {
            Logger.getLogger(InfoCellRenderer.class.getName()).log(Level.SEVERE, null, ex);
            this.quantitaPrenotazioni.setText("???");
        }
        
        try {
             this.quantitaDisponibile.setText(""+value.getDescrizione().getQuantitaDisponibile());
        }
        catch (Exception ex) {
            Logger.getLogger(InfoCellRenderer.class.getName()).log(Level.SEVERE, null, ex);
            this.quantitaPrenotazioni.setText("???");
        }
        
        return this;
    }


    
}
