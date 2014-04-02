/*
 * Created by JFormDesigner on Wed Apr 02 18:28:03 CEST 2014
 */

package gameshop.advance.ui.swing.manager;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

public class ManagerMenu extends JPanel {
    public ManagerMenu() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel2 = new JPanel();
        scrollPane1 = new JScrollPane();
        lowQuantityList = new JList();
        button4 = new JButton();
        button7 = new JButton();
        panel3 = new JPanel();
        scrollPane2 = new JScrollPane();
        offertsList = new JList();
        button5 = new JButton();
        button6 = new JButton();

        //======== this ========
        setName("this");

        //======== panel2 ========
        {
            panel2.setBorder(new TitledBorder("Prodotti in esaurimento scorte"));
            panel2.setName("panel2");

            //======== scrollPane1 ========
            {
                scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane1.setViewportBorder(null);
                scrollPane1.setName("scrollPane1");

                //---- lowQuantityList ----
                lowQuantityList.setName("lowQuantityList");
                scrollPane1.setViewportView(lowQuantityList);
            }

            PanelBuilder panel2Builder = new PanelBuilder(new FormLayout(
                "[245px,pref]:grow",
                "fill:35px:grow"), panel2);

            panel2Builder.add(scrollPane1, CC.xy(1, 1));
        }

        //---- button4 ----
        button4.setText("Gestisci Forniture");
        button4.setName("button4");

        //---- button7 ----
        button7.setText("Controlla Magazzino");
        button7.setName("button7");

        //======== panel3 ========
        {
            panel3.setBorder(new TitledBorder("Attualmente in offerta"));
            panel3.setName("panel3");

            //======== scrollPane2 ========
            {
                scrollPane2.setName("scrollPane2");

                //---- offertsList ----
                offertsList.setName("offertsList");
                scrollPane2.setViewportView(offertsList);
            }

            PanelBuilder panel3Builder = new PanelBuilder(new FormLayout(
                "[245px,pref]:grow",
                "fill:[35px,default]:grow"), panel3);

            panel3Builder.add(scrollPane2, CC.xy(1, 1));
        }

        //---- button5 ----
        button5.setText("Gestisci Sconti");
        button5.setName("button5");

        //---- button6 ----
        button6.setText("Gestisci Prezzi");
        button6.setName("button6");

        PanelBuilder builder = new PanelBuilder(new FormLayout(
            "[15px,default]:grow, $lcgap, [245px,pref], 10dlu, [185px,pref], $lcgap, [15px,default]:grow",
            "fill:[15px,default]:grow, 2*($lgap, [25dlu,default]), $lgap, fill:[25dlu,default], $lgap, 5px, 3*($lgap, fill:[25dlu,default]), $lgap, fill:[15px,default]:grow"), this);

        builder.add(panel2,  CC.xywh(3,  2,       1,       6));
        builder.add(button4, CC.xy  (5,  3, CC.FILL, CC.FILL));
        builder.add(button7, CC.xy  (5,  5, CC.FILL, CC.FILL));
        builder.add(panel3,  CC.xywh(3, 10,       1,       6));
        builder.add(button5, CC.xy  (5, 11, CC.FILL, CC.FILL));
        builder.add(button6, CC.xy  (5, 13));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel2;
    private JScrollPane scrollPane1;
    private JList lowQuantityList;
    private JButton button4;
    private JButton button7;
    private JPanel panel3;
    private JScrollPane scrollPane2;
    private JList offertsList;
    private JButton button5;
    private JButton button6;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
