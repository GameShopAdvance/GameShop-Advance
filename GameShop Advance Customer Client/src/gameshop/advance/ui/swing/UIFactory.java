/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.ui.swing;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class UIFactory {
    private static UIFactory instance;
    
    private UIFactory(){
        
    }
    
    public static UIFactory getInstance()
    {
        if(instance == null)
            instance = new UIFactory();
        
        return instance;
    }

    protected void setButtonSize(JButton button){
        
    }
    
    public JButton getConfirmButton(){
        JButton button = new JButton();
        button.setBackground(UIStyleSingleton.getInstance().getSuccessColor());
        button.setForeground(UIStyleSingleton.getInstance().getButtonSuccessTextColor());
        this.setButtonSize(button);
        return button;
    }
    
    public JButton getCancelButton(){
        JButton button = new JButton();
        button.setBackground(UIStyleSingleton.getInstance().getSuccessColor());
        button.setForeground(UIStyleSingleton.getInstance().getButtonSuccessTextColor());
        this.setButtonSize(button);
        return button;
    }
    
    public JButton getSimpleButton(){
        JButton button = new JButton();
        button.setBackground(UIStyleSingleton.getInstance().getSuccessColor());
        button.setForeground(UIStyleSingleton.getInstance().getButtonSuccessTextColor());
        this.setButtonSize(button);
        return button;     
    }
    
    public JLabel getHeaderLabel(){
        JLabel label = new JLabel();
        label.setForeground(Color.red);
        label.setFont(UIStyleSingleton.getInstance().getBigFont());
        return label;        
    }
    
    public JLabel getBoldLabel(){
        JLabel label = new JLabel();
        label.setForeground(Color.red);
        label.setFont(UIStyleSingleton.getInstance().getBoldFont());
        return label;        
    }
    
    public JLabel getBodyLabel(){
        JLabel label = new JLabel();
        label.setForeground(Color.red);
        label.setFont(UIStyleSingleton.getInstance().getNormalFont());
        return label; 
    }
    
    public JTextField getTextField(){
        JTextField textField = new JTextField();
        textField.setFont(UIStyleSingleton.getInstance().getNormalFont());
        return textField;
    }
}
