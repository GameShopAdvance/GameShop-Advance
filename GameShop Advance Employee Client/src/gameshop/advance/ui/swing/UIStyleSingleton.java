/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.ui.swing;

import java.awt.Color;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class UIStyleSingleton {

    private Color successColor = new Color(102, 204, 0);
    private Color alertColor = new Color(255, 51, 0);
    private Color errorColor = new Color(255, 51, 51);
    
    private Color buttonTextColor = new Color(255, 255, 255);
    
    private static UIStyleSingleton instance;
    
    private UIStyleSingleton()
    {
        
    }
    
    public static UIStyleSingleton getInstance()
    {
        if(instance == null)
            instance = new UIStyleSingleton();
        
        return instance;
    }

    public Color getSuccessColor() {
        return successColor;
    }

    public void setSuccessColor(Color successColor) {
        this.successColor = successColor;
    }

    public Color getAlertColor() {
        return alertColor;
    }

    public void setAlertColor(Color alertColor) {
        this.alertColor = alertColor;
    }

    public Color getErrorColor() {
        return errorColor;
    }

    public void setErrorColor(Color errorColor) {
        this.errorColor = errorColor;
    }

    public Color getButtonTextColor() {
        return this.buttonTextColor;
    }

    
    
}
