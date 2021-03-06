/*
 * Created by JFormDesigner on Wed Jan 29 15:25:11 CET 2014
 */

package gameshop.advance.ui.swing;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * 
 * @author Matteo Gentile
 */
public class UIWindowSingleton extends JFrame {
    
    private static JFrame instance;
    
    private JComponent panel;
    
    private Dimension minimumSize = new Dimension(920, 480);
    
    public UIWindowSingleton() {
        this.panel = null;
        this.initComponents();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(this.minimumSize);
    }

    
    private void modificaConfigurazione(ActionEvent e) {
          JDialog d = new ConfigurationDialog(UIWindowSingleton.getInstance());
          d.setVisible(true);
    }

    private void exit(ActionEvent e) {
        this.dispose();
        System.exit(0);
    }

    private void thisComponentResized(ComponentEvent e) {
        Dimension d = UIWindowSingleton.getInstance().getSize();
        if(d.width < this.minimumSize.width)
            d.width=this.minimumSize.width;
        if(d.height<this.minimumSize.height)
            d.height = this.minimumSize.height;
        UIWindowSingleton.getInstance().setSize(d);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar1 = new JMenuBar();
        menu2 = new JMenu();
        menuItem3 = new JMenuItem();
        menuItem1 = new JMenuItem();
        mainPanel = new JScrollPane();

        //======== this ========
        setTitle("GameShop Advance - Terminale Manager");
        setMinimumSize(new Dimension(920, 480));
        setName("this");
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                thisComponentResized(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== menuBar1 ========
        {
            menuBar1.setName("menuBar1");

            //======== menu2 ========
            {
                menu2.setText("File");
                menu2.setName("menu2");

                //---- menuItem3 ----
                menuItem3.setText("Impostazioni");
                menuItem3.setName("menuItem3");
                menuItem3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        modificaConfigurazione(e);
                    }
                });
                menu2.add(menuItem3);

                //---- menuItem1 ----
                menuItem1.setText("Esci");
                menuItem1.setName("menuItem1");
                menuItem1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        exit(e);
                    }
                });
                menu2.add(menuItem1);
            }
            menuBar1.add(menu2);
        }
        setJMenuBar(menuBar1);

        //======== mainPanel ========
        {
            mainPanel.setViewportBorder(null);
            mainPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            mainPanel.setName("mainPanel");
        }
        contentPane.add(mainPanel, BorderLayout.CENTER);
        setSize(1025, 540);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
    
    /**
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
     * @param panel pannello da visualizzare nella finestra.
     */
    public void setPanel(JComponent panel)
    {
        if(this.panel != null)
            this.remove(this.panel);
        this.panel = panel;
    }
    
    /**
     * @return JPanel
     */
    public JComponent getPanel()
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
    
    
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar1;
    private JMenu menu2;
    private JMenuItem menuItem3;
    private JMenuItem menuItem1;
    private JScrollPane mainPanel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
