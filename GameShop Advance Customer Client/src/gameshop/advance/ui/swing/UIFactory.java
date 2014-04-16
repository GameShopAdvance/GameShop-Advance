/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.ui.swing;

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

    
    
    public JButton getConfirmButton(){
        JButton button = new JButton();
        button.setBackground(UIStyleSingleton.getInstance().getSuccessColor());
        button.setForeground(UIStyleSingleton.getInstance().getButtonSuccessTextColor());
        return button;
    }
    
    public JButton getCancelButton(){
        return null;
        
    }
    
    public JButton getSimpleButton(){
        return null;
        
    }
    
    public JLabel getHeaderLabel(){
        return null;
        
    }
    
    public JLabel getBodyLabel(){
        return null;
        
    }
    
    public JTextField getTextField(){
        return null;
        
    }
}
