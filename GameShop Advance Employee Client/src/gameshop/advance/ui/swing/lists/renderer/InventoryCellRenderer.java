/*
 * Created by JFormDesigner on Fri Apr 18 11:14:51 CEST 2014
 */

package gameshop.advance.ui.swing.lists.renderer;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.controller.valueData.AggiuntaProdotti;
import gameshop.advance.technicalservices.LoggerSingleton;
import gameshop.advance.ui.swing.factory.UIFactory;
import java.awt.Component;
import java.rmi.RemoteException;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

/**
 * Layout associato ai vari prodotti nella lista dei prodotti aggiunti all'inventario del negozio.
 * @author Salx
 */
public class InventoryCellRenderer extends JPanel implements ListCellRenderer<AggiuntaProdotti> {
    
    public InventoryCellRenderer() {
        initComponents();
    }

    private void createUIComponents() {
        this.codiceProdotto = UIFactory.getInstance().getBodyLabel();
        this.nomeProdotto = UIFactory.getInstance().getBodyLabel();
        this.quantitaDisponibile = UIFactory.getInstance().getBodyLabel();
        this.quantitaAggiunta = UIFactory.getInstance().getBodyLabel();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        createUIComponents();


        //======== this ========
        setName("this");

        //---- codiceProdotto ----
        codiceProdotto.setText("text");
        codiceProdotto.setHorizontalAlignment(SwingConstants.CENTER);
        codiceProdotto.setName("codiceProdotto");

        //---- nomeProdotto ----
        nomeProdotto.setText("text");
        nomeProdotto.setName("nomeProdotto");

        //---- quantitaAggiunta ----
        quantitaAggiunta.setText("text");
        quantitaAggiunta.setHorizontalAlignment(SwingConstants.CENTER);
        quantitaAggiunta.setName("quantitaAggiunta");

        //---- quantitaDisponibile ----
        quantitaDisponibile.setText("text");
        quantitaDisponibile.setHorizontalAlignment(SwingConstants.CENTER);
        quantitaDisponibile.setName("quantitaDisponibile");

        PanelBuilder builder = new PanelBuilder(new FormLayout(
            "[25dlu,default,50dlu]:grow, $lcgap, [150dlu,default]:grow, $lcgap, [25dlu,default,40dlu]:grow, $lcgap, [25dlu,default,50dlu]:grow",
            "[35dlu,default]:grow"), this);

        builder.add(codiceProdotto,      CC.xy(1, 1,    CC.FILL, CC.FILL));
        builder.add(nomeProdotto,        CC.xy(3, 1,    CC.FILL, CC.FILL));
        builder.add(quantitaAggiunta,    CC.xy(5, 1,    CC.FILL, CC.FILL));
        builder.add(quantitaDisponibile, CC.xy(7, 1, CC.DEFAULT, CC.FILL));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel codiceProdotto;
    private JLabel nomeProdotto;
    private JLabel quantitaAggiunta;
    private JLabel quantitaDisponibile;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    @Override
    public Component getListCellRendererComponent(JList list, AggiuntaProdotti value, int index, boolean isSelected, boolean cellHasFocus) {
        if(index == 0 && value == null)
        {
            this.codiceProdotto.setText("Codice");
            this.nomeProdotto.setText("Nome prodotto");
            this.quantitaDisponibile.setText("Disponibili");
            this.quantitaAggiunta.setText("Aggiunti");
        }
        else{
            try {
                this.codiceProdotto.setText(value.getId().getCodice());
            }
            catch (RemoteException ex) {
                this.codiceProdotto.setText("???");
                LoggerSingleton.getInstance().log(ex);
            }
            try {
                this.nomeProdotto.setText(value.getDescrizione().getNomeProdotto());
            }
            catch (RemoteException ex) {
                this.nomeProdotto.setText("???");
                LoggerSingleton.getInstance().log(ex);
            }
            try {
                this.quantitaDisponibile.setText(""+value.getDescrizione().getQuantitaDisponibile());
            }
            catch (RemoteException ex) {
                this.quantitaDisponibile.setText("???");
                LoggerSingleton.getInstance().log(ex);
            }
            this.quantitaAggiunta.setText(""+value.getAddedQuantity());
        }
        return this;
    }
}
