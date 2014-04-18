/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.ui.swing.factory;

import java.awt.Color;
import java.awt.Font;

/**
 * Classe che definisce lo stile degli elementi delle varie schermate.
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class UIStyleSingleton {

    private Color successColor = new Color(102, 204, 0);
    private Color alertColor = new Color(255, 51, 0);
    private Color errorColor = new Color(255, 51, 51);
    
    private Color buttonTextColor = new Color(255, 255, 255);
    private Color buttonSuccessTextColor = new Color(255, 255, 255);
    private Color buttonAlertTextColor = new Color(255, 255, 255);
    private Color headerTextColor = new Color(0, 0, 0);
    private Color textColor = new Color(0, 0, 0);
    private Color windowBackgroundColor = new Color(238, 238, 238);
    
    private Font bigFont = new Font("Tahoma", Font.PLAIN, 16);
    private Font normalFont = new Font("Tahoma", Font.PLAIN, 14);
    private Font boldFont = new Font("Tahoma", Font.BOLD, 14);
    
    private static UIStyleSingleton instance;

    /**
     * @return
     */
    public Font getBigFont() {
        return bigFont;
    }

    /**
     * @param bigFont
     */
    public void setBigFont(Font bigFont) {
        this.bigFont = bigFont;
    }

    /**
     * @return
     */
    public Font getNormalFont() {
        return normalFont;
    }

    /**
     * @param normalFont
     */
    public void setNormalFont(Font normalFont) {
        this.normalFont = normalFont;
    }

    /**
     * @return
     */
    public Font getBoldFont() {
        return boldFont;
    }

    /**
     * @param boldFont
     */
    public void setBoldFont(Font boldFont) {
        this.boldFont = boldFont;
    }
    
    private UIStyleSingleton()
    {
        
    }
    
    /**
     * Metodo che ritorna l'istanza della classe.
     * @return
     */
    public static UIStyleSingleton getInstance()
    {
        if(instance == null)
            instance = new UIStyleSingleton();
        
        return instance;
    }

    /**
     * @return
     */
    public Color getSuccessColor() {
        return successColor;
    }

    /**
     * @param successColor
     */
    public void setSuccessColor(Color successColor) {
        this.successColor = successColor;
    }

    /**
     * @return
     */
    public Color getAlertColor() {
        return alertColor;
    }

    /**
     * @param alertColor
     */
    public void setAlertColor(Color alertColor) {
        this.alertColor = alertColor;
    }

    /**
     * @return
     */
    public Color getErrorColor() {
        return errorColor;
    }

    /**
     * @param errorColor
     */
    public void setErrorColor(Color errorColor) {
        this.errorColor = errorColor;
    }

    /**
     * @return
     */
    public Color getButtonTextColor() {
        return this.buttonTextColor;
    }

    /**
     * @return
     */
    public Color getButtonSuccessTextColor() {
        return this.buttonSuccessTextColor;
    }

    /**
     * @return
     */
    public Color getButtonAlertTextColor() {
        return this.buttonAlertTextColor;
    }

    /**
     * @return
     */
    public Color getHeaderTextColor() {
        return this.headerTextColor;
    }

    /**
     * @return
     */
    public Color getTextColor(){
        return this.textColor;
    }

    Color getWindowBackgroundColor() {
        return this.windowBackgroundColor;
    }
    
}
