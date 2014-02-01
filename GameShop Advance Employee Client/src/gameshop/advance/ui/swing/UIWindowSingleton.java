/*
 * Created by JFormDesigner on Wed Jan 29 15:25:11 CET 2014
 */

package gameshop.advance.ui.swing;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author Matteo Gentile
 */
public class UIWindowSingleton extends JFrame {
    
    private static JFrame instance;
    
    private JPanel panel;
    
    
    public UIWindowSingleton() {
        this.panel = null;
        this.initComponents();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }

    private void modificaConfigurazione(ActionEvent e) {
          JDialog d = new ConfigurationDialog(UIWindowSingleton.getInstance());
          d.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem2 = new JMenuItem();
        mainPanel = new JPanel();

        //======== this ========
        setTitle("GameShopAdvance Cassa");
        setName("this");
        Container contentPane = getContentPane();

        //======== menuBar1 ========
        {
            menuBar1.setName("menuBar1");

            //======== menu1 ========
            {
                menu1.setText("Configurazione");
                menu1.setName("menu1");

                //---- menuItem2 ----
                menuItem2.setText("Modifica");
                menuItem2.setName("menuItem2");
                menuItem2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        modificaConfigurazione(e);
                    }
                });
                menu1.add(menuItem2);
            }
            menuBar1.add(menu1);
        }
        setJMenuBar(menuBar1);

        //======== mainPanel ========
        {
            mainPanel.setName("mainPanel");

            PanelBuilder mainPanelBuilder = new PanelBuilder(new FormLayout(
                "[269dlu,default]:grow",
                "[181dlu,default]:grow"), mainPanel);

        }

        PanelBuilder contentPaneBuilder = new PanelBuilder(new FormLayout(
            "268dlu",
            "[153dlu,default]"));
        contentPane.setLayout(new BorderLayout());
        contentPane.add(contentPaneBuilder.getPanel(), BorderLayout.CENTER);

        contentPaneBuilder.add(mainPanel, CC.xy(1, 1, CC.FILL, CC.FILL));
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
            this.getContentPane().remove(this.panel);
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
        this.getContentPane().setSize(this.panel.getSize());
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
    
    
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem2;
    private JPanel mainPanel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
