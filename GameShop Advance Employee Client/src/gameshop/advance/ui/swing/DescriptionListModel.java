/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.ui.swing;

import gameshop.advance.controller.valueData.AggiuntaProdotti;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.AbstractListModel;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class DescriptionListModel extends AbstractListModel<Object>{

    private LinkedList<Object> descriptions;
    
    public DescriptionListModel(LinkedList<AggiuntaProdotti> list)
    {
        Iterator<AggiuntaProdotti> iter = list.iterator();
        while(iter.hasNext())
            this.descriptions.add(iter.next());
    }
    
    @Override
    public int getSize() {
        return this.descriptions.size();
    }

    @Override
    public Object getElementAt(int index) {
        return this.descriptions.get(index);
    }
    
}
