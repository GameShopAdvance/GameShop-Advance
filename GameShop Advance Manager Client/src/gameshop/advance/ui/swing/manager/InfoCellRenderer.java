/*
 * Created by JFormDesigner on Sun Mar 30 09:50:48 CEST 2014
 */

package gameshop.advance.ui.swing.manager;

import com.jgoodies.forms.layout.FormLayout;
import gameshop.advance.interfaces.remote.IDescrizioneProdottoRemote;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

/**
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class InfoCellRenderer extends JPanel implements ListCellRenderer<IDescrizioneProdottoRemote>{
    
    public InfoCellRenderer(){
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents

        //======== this ========
        setLayout(new FormLayout(
            "[15dlu,default], $lcgap, [227dlu,default], $lcgap, 16dlu",
            "[13dlu,default], $lgap, 30dlu, $lgap, [13dlu,default]"));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    @Override
    public Component getListCellRendererComponent(JList<? extends IDescrizioneProdottoRemote> list, IDescrizioneProdottoRemote value, int index, boolean isSelected, boolean cellHasFocus) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
