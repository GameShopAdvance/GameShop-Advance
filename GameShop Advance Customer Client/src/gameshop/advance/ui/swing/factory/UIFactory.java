/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.ui.swing.factory;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Classe Factory per la generazione degli elementi delle schermate dell'interfaccia.
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class UIFactory {
    private static UIFactory instance;
    
    private UIFactory(){
        
    }
    
    /**
     * Metodo che ritorna l'istanza della classe Singleton.
     * @return
     */
    public static UIFactory getInstance()
    {
        if(instance == null)
            instance = new UIFactory();
        
        return instance;
    }

    /**
     * @param button
     */
    protected void setButtonSize(JButton button){
        
    }
    
    /**
     * @return
     */
    public JButton getConfirmButton(){
        JButton button = new JButton();
        button.setBackground(UIStyleSingleton.getInstance().getSuccessColor());
        button.setForeground(UIStyleSingleton.getInstance().getButtonSuccessTextColor());
        this.setButtonSize(button);
        return button;
    }
    
    /**
     * @return
     */
    public JButton getCancelButton(){
        JButton button = new JButton();
        button.setBackground(UIStyleSingleton.getInstance().getAlertColor());
        button.setForeground(UIStyleSingleton.getInstance().getButtonAlertTextColor());
        this.setButtonSize(button);
        return button;
    }
    
    /**
     * @return
     */
    public JButton getSimpleButton(){
        JButton button = new JButton();
//        button.setBackground(UIStyleSingleton.getInstance().getSuccessColor());
//        button.setForeground(UIStyleSingleton.getInstance().getButtonSuccessTextColor());
        this.setButtonSize(button);
        return button;     
    }
    
    /**
     * @return
     */
    public JButton getMenuButton(){
        return this.getSimpleButton();
    }
    
    /**
     * @return
     */
    public JLabel getHeaderLabel(){
        JLabel label = new JLabel();
        label.setForeground(UIStyleSingleton.getInstance().getHeaderTextColor());
        label.setFont(UIStyleSingleton.getInstance().getBigFont());
        return label;        
    }
    
    /**
     * @return
     */
    public JLabel getBoldLabel(){
        JLabel label = new JLabel();
        label.setForeground(UIStyleSingleton.getInstance().getTextColor());
        label.setFont(UIStyleSingleton.getInstance().getBoldFont());
        return label;        
    }
    
    /**
     * @return
     */
    public JLabel getBodyLabel(){
        JLabel label = new JLabel();
        label.setForeground(UIStyleSingleton.getInstance().getTextColor());
        label.setFont(UIStyleSingleton.getInstance().getNormalFont());
        return label; 
    }
    
    /**
     * @return
     */
    public JTextField getTextField(){
        JTextField textField = new JTextField();
        textField.setForeground(UIStyleSingleton.getInstance().getTextColor());
        textField.setFont(UIStyleSingleton.getInstance().getNormalFont());
        return textField;
    }

    /**
     * @return
     */
    public JTextArea getLongTextArea() {
        JTextArea textArea = new JTextArea();
        textArea.setBackground(UIStyleSingleton.getInstance().getWindowBackgroundColor());
        textArea.setBorder(null);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(UIStyleSingleton.getInstance().getNormalFont());
        return textArea;
    }
}
