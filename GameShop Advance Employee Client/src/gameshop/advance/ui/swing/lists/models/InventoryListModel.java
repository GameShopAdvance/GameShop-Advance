/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.ui.swing.lists.models;

import gameshop.advance.controller.valueData.AggiuntaProdotti;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.LinkedList;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 * Classe che gestisce sotto forma di hashmap i vari prodotti che verranno aggiunti dalla schermata 
 * dell'inventario.
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class InventoryListModel implements ListModel<AggiuntaProdotti>{
    private LinkedList<ListDataListener> listeners = new LinkedList<ListDataListener>();
    
    private HashMap<String, AggiuntaProdotti> inventory = new HashMap<>();
    
    private boolean header;

    @Override
    public int getSize() {
        if(header)
            return this.inventory.size()+1;
        else
            return this.inventory.size();
    }


    public void clear(){
        this.inventory.clear();
    }
    
    /**
     * @param head
     */
    public void setHeader(boolean head)
    {
        this.header = head;
    }
    
    /**
     * @param code
     * @param aggiunta
     */
    public void addElement(String code, AggiuntaProdotti aggiunta)
    {
        if(!this.inventory.containsKey(code))
            this.inventory.put(code, aggiunta);
        this.fireContentsChanged(0, this.inventory.size()-1);
    }

    /**
     * @param code
     * @throws RemoteException
     */
    public void remove(String code) throws RemoteException
    {
        this.inventory.remove(code);
        this.fireContentsChanged(0, this.inventory.size()-1);
    }
    
    @Override
    public AggiuntaProdotti getElementAt(int index) {
        if(header)
        {
            if(index == 0)
                return null;
            index -= 1;
        }
        return (AggiuntaProdotti) this.inventory.values().toArray()[index];
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        this.listeners.add(l);
    }
    
    /**
     * @return
     */
    public ListDataListener[] getListDataListeners(){
        return (ListDataListener[]) this.listeners.toArray();
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        this.listeners.remove(l);
    }  

    /**
     * @param index0
     * @param index1
     */
    protected void fireIntervalAdded(int index0, int index1)
    {
        if(this.listeners.size() > 0)
        {
            ListDataEvent le = new ListDataEvent(this, ListDataEvent.INTERVAL_ADDED, index0, index1);
            for(ListDataListener listener:listeners)
                listener.intervalAdded(le);
        }
    }
    
    /**
     * @param source
     * @param index0
     * @param index1
     */
    protected void fireIntervalRemoved(Object source, int index0, int index1)
    {
        if(this.listeners.size() > 0)
        {
            ListDataEvent le = new ListDataEvent(this, ListDataEvent.INTERVAL_REMOVED, index0, index1);
            for(ListDataListener listener:listeners)
                listener.intervalRemoved(le);
        }
    }
    
    /**
     * @param index0
     * @param index1
     */
    protected void fireContentsChanged(int index0, int index1)
    {
        if(this.listeners.size() > 0)
        {
            ListDataEvent le = new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, index0, index1);
            for(ListDataListener listener:listeners)
                listener.contentsChanged(le);
        }
    }

    /**
     * @param codice
     * @return
     */
    public AggiuntaProdotti getElement(String codice) {
        return this.inventory.get(codice);
    }
}
