/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces;

import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 * Classe Interfaccia che si occupa della gestione dello spostamento tra le varie schermata all'interno del 
 * terminale Customer.
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public interface IPopActionListener extends ActionListener {
    
    void popPanel();
    
    void pushPanel(JPanel panel);
}
