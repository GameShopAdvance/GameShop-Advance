/*
 * Created by JFormDesigner on Sun Mar 30 09:50:48 CEST 2014
 */

package gameshop.advance.ui.swing.lists.renderer;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.interfaces.remote.forniture.IInformazioniProdottoRemote;
import gameshop.advance.technicalservices.LoggerSingleton;
import gameshop.advance.ui.swing.factory.UIFactory;
import java.awt.Component;
import java.rmi.RemoteException;
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
        this.label1 = UIFactory.getInstance().getBodyLabel();
        this.soglia = UIFactory.getInstance().getBodyLabel();
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

        //---- label1 ----
        label1.setText("/");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setName("label1");

        //---- soglia ----
        soglia.setText("text");
        soglia.setBorder(new EtchedBorder());
        soglia.setHorizontalAlignment(SwingConstants.CENTER);
        soglia.setName("soglia");

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
            "$lcgap, [60dlu,default,140dlu]:grow, $ugap, [50dlu,default,100dlu]:grow, $lcgap, [8dlu,default,16dlu]:grow, [4dlu,default,8dlu], [8dlu,default,16dlu]:grow, $ugap, [50dlu,default,100dlu]:grow, $lcgap, [20dlu,default,40dlu]:grow, $lcgap",
            "$lgap, fill:[27dlu,default], $lgap, fill:26dlu, $lgap, $rgap"), this);

        builder.add(title,                CC.xywh( 2, 2,  1, 3));
        builder.add(prenotazioni,         CC.xy  ( 4, 2));
        builder.add(quantitaPrenotazioni, CC.xywh( 6, 2,  3, 1));
        builder.add(inArrivo,             CC.xy  (10, 2));
        builder.add(quantitaInArrivo,     CC.xy  (12, 2));
        builder.add(disponibilita,        CC.xy  ( 4, 4));
        builder.add(quantitaDisponibile,  CC.xy  ( 6, 4));
        builder.add(label1,               CC.xy  ( 7, 4));
        builder.add(soglia,               CC.xy  ( 8, 4));
        builder.add(ordine,               CC.xy  (10, 4));
        builder.add(quantitaOrdine,       CC.xy  (12, 4));
        builder.add(separator1,           CC.xywh( 1, 6, 13, 1));
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
    private JLabel label1;
    private JLabel soglia;
    private JLabel ordine;
    private JTextField quantitaOrdine;
    private JSeparator separator1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    @Override
    public Component getListCellRendererComponent(JList<? extends IInformazioniProdottoRemote> list, IInformazioniProdottoRemote value, int index, boolean isSelected, boolean cellHasFocus) {
        try {
            this.title.setText(value.getDescrizione().getNomeProdotto());
        }
        catch (RemoteException ex) {
            LoggerSingleton.getInstance().log(ex);
            this.title.setText("???");
        }
         
        try {
             this.quantitaPrenotazioni.setText(""+value.getPrenotati());
        }
        catch (RemoteException ex) {
            LoggerSingleton.getInstance().log(ex);
            this.quantitaPrenotazioni.setText("???");
        }
        
        try {
             this.quantitaDisponibile.setText(""+value.getDescrizione().getQuantitaDisponibile());
        }
        catch (RemoteException ex) {
            LoggerSingleton.getInstance().log(ex);
            this.quantitaPrenotazioni.setText("???");
        }
        try {
             this.soglia.setText(""+value.getDescrizione().getQuantitaDiSoglia());
        }
        catch (RemoteException ex) {
            LoggerSingleton.getInstance().log(ex);
            this.soglia.setText("???");
        }
        
        
        return this;
    }


    
}
