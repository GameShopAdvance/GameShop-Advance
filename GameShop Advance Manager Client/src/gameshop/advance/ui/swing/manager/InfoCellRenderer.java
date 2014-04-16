/*
 * Created by JFormDesigner on Sun Mar 30 09:50:48 CEST 2014
 */

package gameshop.advance.ui.swing.manager;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.interfaces.remote.IInformazioniProdottoRemote;
import gameshop.advance.ui.swing.UIFactory;
import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
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
        separator1 = new JSeparator();

        //======== this ========
        setName("this");

        //---- title ----
        title.setText("Titolo");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(LineBorder.createBlackLineBorder());
        title.setName("title");

        //---- prenotazioni ----
        prenotazioni.setText("Prenotati");
        prenotazioni.setHorizontalAlignment(SwingConstants.CENTER);
        prenotazioni.setBorder(new EtchedBorder());
        prenotazioni.setName("prenotazioni");

        //---- quantitaPrenotazioni ----
        quantitaPrenotazioni.setText("#qty");
        quantitaPrenotazioni.setHorizontalAlignment(SwingConstants.CENTER);
        quantitaPrenotazioni.setBorder(new EtchedBorder());
        quantitaPrenotazioni.setName("quantitaPrenotazioni");

        //---- inArrivo ----
        inArrivo.setText("In Arrivo");
        inArrivo.setHorizontalAlignment(SwingConstants.CENTER);
        inArrivo.setBorder(new EtchedBorder());
        inArrivo.setName("inArrivo");

        //---- quantitaInArrivo ----
        quantitaInArrivo.setText("0");
        quantitaInArrivo.setHorizontalAlignment(SwingConstants.CENTER);
        quantitaInArrivo.setBorder(new EtchedBorder());
        quantitaInArrivo.setName("quantitaInArrivo");

        //---- disponibilita ----
        disponibilita.setText("Disponibili");
        disponibilita.setHorizontalAlignment(SwingConstants.CENTER);
        disponibilita.setBorder(new EtchedBorder());
        disponibilita.setName("disponibilita");

        //---- quantitaDisponibile ----
        quantitaDisponibile.setText("#qty");
        quantitaDisponibile.setHorizontalAlignment(SwingConstants.CENTER);
        quantitaDisponibile.setBorder(new EtchedBorder());
        quantitaDisponibile.setName("quantitaDisponibile");

        //---- ordine ----
        ordine.setText("Ordine");
        ordine.setHorizontalAlignment(SwingConstants.CENTER);
        ordine.setBorder(new EtchedBorder());
        ordine.setName("ordine");

        //---- quantitaOrdine ----
        quantitaOrdine.setHorizontalAlignment(SwingConstants.CENTER);
        quantitaOrdine.setName("quantitaOrdine");

        //---- separator1 ----
        separator1.setName("separator1");

        PanelBuilder builder = new PanelBuilder(new FormLayout(
            "$lcgap, [60dlu,default,140dlu]:grow, $ugap, [50dlu,default,100dlu]:grow, $lcgap, [20dlu,default,40dlu]:grow, $ugap, [50dlu,default,100dlu]:grow, $lcgap, [20dlu,default,40dlu]:grow, $lcgap",
            "$lgap, fill:[27dlu,default], $lgap, fill:26dlu, $lgap, $rgap"), this);

        builder.add(title,                CC.xywh( 2, 2,  1, 3));
        builder.add(prenotazioni,         CC.xy  ( 4, 2));
        builder.add(quantitaPrenotazioni, CC.xy  ( 6, 2));
        builder.add(inArrivo,             CC.xy  ( 8, 2));
        builder.add(quantitaInArrivo,     CC.xy  (10, 2));
        builder.add(disponibilita,        CC.xy  ( 4, 4));
        builder.add(quantitaDisponibile,  CC.xy  ( 6, 4));
        builder.add(ordine,               CC.xy  ( 8, 4));
        builder.add(quantitaOrdine,       CC.xy  (10, 4));
        builder.add(separator1,           CC.xywh( 1, 6, 11, 1));
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
    private JSeparator separator1;
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
