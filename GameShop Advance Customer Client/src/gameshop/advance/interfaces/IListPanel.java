/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces;

import javax.swing.ListCellRenderer;
import javax.swing.ListModel;

/**
 * Classe Interfaccia che imposta una lista per visualizzare gli oggetti sul terminale.
 * @author Lorenzo Di Giuseppe
 */
public interface IListPanel
{

    /**
     * @param model
     * @param renderer
     */
    void setList(ListModel model, ListCellRenderer renderer);
}
