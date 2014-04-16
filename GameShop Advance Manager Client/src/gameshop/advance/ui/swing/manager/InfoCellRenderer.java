/*
 * Created by JFormDesigner on Sun Mar 30 09:50:48 CEST 2014
 */

package gameshop.advance.ui.swing.manager;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.interfaces.remote.IInformazioniProdottoRemote;
import gameshop.advance.ui.swing.UIFactory;
import java.awt.Color;
import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

/**
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class InfoCellRenderer extends JPanel implements ListCellRenderer<IInformazioniProdottoRemote>{
    
    public InfoCellRenderer(){
        initComponents();
    }

    private void createUIComponents() {
        this.title = UIFactory.getInstance().getBodyLabel();
        this.prenotazioni = UIFactory.getInstance().getBodyLabel();
        this.disponibilita = UIFactory.getInstance().getBodyLabel();
        this.quantitaPrenotazioni = UIFactory.getInstance().getBodyLabel();
        this.quantitaDisponibile = UIFactory.getInstance().getBodyLabel();
        this.inArrivo = UIFactory.getInstance().getBodyLabel();
        this.ordine = UIFactory.getInstance().getBodyLabel();
        this.quantitaInArrivo = UIFactory.getInstance().getBodyLabel();
        this.quantitaOrdine = UIFactory.getInstance().getTextField();
   
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        createUIComponents();

        title = new JLabel();
        prenotazioni = new JLabel();
        quantitaPrenotazioni = new JLabel();
        inArrivo = new JLabel();
        quantitaInArrivo = new JLabel();
        disponibilita = new JLabel();
        quantitaDisponibile = new JLabel();
        ordine = new JLabel();

        //======== this ========
        setLayout(new FormLayout(
            "$lcgap, 69dlu, $lcgap, 90dlu, $lcgap, [56dlu,default], 10dlu, $lcgap, 64dlu, $lcgap, 45dlu, $lcgap",
            "$lgap, fill:[27dlu,default], $lgap, fill:26dlu, $lgap"));

        //---- title ----
        title.setText("Titolo");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(new LineBorder(Color.black));
        add(title, CC.xywh(2, 2, 1, 3));

        //---- prenotazioni ----
        prenotazioni.setText("Prenotati");
        prenotazioni.setHorizontalAlignment(SwingConstants.CENTER);
        prenotazioni.setBorder(new EtchedBorder());
        add(prenotazioni, CC.xy(4, 2));

        //---- quantitaPrenotazioni ----
        quantitaPrenotazioni.setText("#qty");
        quantitaPrenotazioni.setHorizontalAlignment(SwingConstants.CENTER);
        quantitaPrenotazioni.setBorder(new EtchedBorder());
        add(quantitaPrenotazioni, CC.xy(6, 2));

        //---- inArrivo ----
        inArrivo.setText("In Arrivo");
        inArrivo.setHorizontalAlignment(SwingConstants.CENTER);
        inArrivo.setBorder(new EtchedBorder());
        add(inArrivo, CC.xy(9, 2));

        //---- quantitaInArrivo ----
        quantitaInArrivo.setText("#qty");
        quantitaInArrivo.setHorizontalAlignment(SwingConstants.CENTER);
        quantitaInArrivo.setBorder(new EtchedBorder());
        add(quantitaInArrivo, CC.xy(11, 2));

        //---- disponibilita ----
        disponibilita.setText("Disponibili");
        disponibilita.setHorizontalAlignment(SwingConstants.CENTER);
        disponibilita.setBorder(new EtchedBorder());
        add(disponibilita, CC.xy(4, 4));

        //---- quantitaDisponibile ----
        quantitaDisponibile.setText("#qty");
        quantitaDisponibile.setHorizontalAlignment(SwingConstants.CENTER);
        quantitaDisponibile.setBorder(new EtchedBorder());
        add(quantitaDisponibile, CC.xy(6, 4));

        //---- ordine ----
        ordine.setText("Ordine");
        ordine.setHorizontalAlignment(SwingConstants.CENTER);
        ordine.setBorder(new EtchedBorder());
        add(ordine, CC.xy(9, 4));

        //---- quantitaOrdine ----
        quantitaOrdine.setHorizontalAlignment(SwingConstants.CENTER);
        add(quantitaOrdine, CC.xy(11, 4));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel title;
    private JLabel prenotazioni;
    private JLabel quantitaPrenotazioni;
    private JLabel inArrivo;
    private JLabel quantitaInArrivo;
    private JLabel disponibilita;
    private JLabel quantitaDisponibile;
    private JLabel ordine;
    private JTextField quantitaOrdine;
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
