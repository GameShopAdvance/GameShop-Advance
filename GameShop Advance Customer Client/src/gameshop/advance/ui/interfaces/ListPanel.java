/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.ui.interfaces;

import javax.swing.ListCellRenderer;
import javax.swing.ListModel;

/**
 *
 * @author Lorenzo Di Giuseppe
 */
public interface ListPanel
{
    void setList(ListModel model, ListCellRenderer renderer);
}
