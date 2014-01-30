/*
 * Created by JFormDesigner on Wed Jan 29 15:25:11 CET 2014
 */

package gameshop.advance.ui.swing;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author matteog
 */
public class UIWindowSingleton extends JFrame {
    
    private static JFrame instance;
    
    private JPanel panel;
    
    
    public UIWindowSingleton() {
        this.panel = null;
        this.initComponents();
        //this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem1 = new JMenuItem();
        menuItem2 = new JMenuItem();
        mainPanel = new JPanel();

        //======== this ========
        setTitle("GameShopAdvance Cassa");
        Container contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "235dlu",
            "[153dlu,default]"));

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("Configurazione");

                //---- menuItem1 ----
                menuItem1.setText("Nuova");
                menu1.add(menuItem1);

                //---- menuItem2 ----
                menuItem2.setText("Modifica");
                menu1.add(menuItem2);
            }
            menuBar1.add(menu1);
        }
        setJMenuBar(menuBar1);

        //======== mainPanel ========
        {
            mainPanel.setLayout(new FormLayout(
                "234dlu",
                "[139dlu,default]"));
        }
        contentPane.add(mainPanel, CC.xy(1, 1));
        setSize(480, 320);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
    
    /**
     * 
     * @return UIWindowSingletonSingletonSingleton unica istanza di classe.
     * Metodo di accesso all'unica istanza di classe.
     */
    public static UIWindowSingleton getInstance()
    {
        if(instance == null)
        {
            instance = new UIWindowSingleton();
        }
        return (UIWindowSingleton) instance;
    }
    
     /**
     * 
     * @param panel pannello da visualizzare nella finestra.
     */
    public void setPanel(JPanel panel)
    {
        if(this.panel != null)
            this.mainPanel.remove(this.panel);
        this.panel = panel;
    }
    
    /**
     * 
     * @return JPanel
     */
    public JPanel getPanel()
    {
        return this.panel;
    }
    
    /**
     * Il metodo aggiorna il contenuto della finestra.
     */
    public void refreshContent()
    {   
        this.setContentPane(this.panel);
        this.revalidate();
    }
    
    /**
     * Il metodo si occupa della visualizzazione degli errori.
     * @param message 
     */
    public void displayError(String message) {
        JOptionPane.showMessageDialog(this, message,"Errore",JOptionPane.WARNING_MESSAGE);
    }
    
    public void clearError()
    {
       //ErrorDialog.getInstance().setText(this,"");
    }
    
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private JPanel mainPanel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
