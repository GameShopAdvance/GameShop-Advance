/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.ui.swing.customer;

import gameshop.advance.interfaces.remote.IDescrizioneProdottoRemote;
import gameshop.advance.ui.swing.RigheDiVenditaListModel;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Lorenzo Di Giuseppe
 */
public class ProductListModel implements ListModel<IDescrizioneProdottoRemote>
{
    private LinkedList<ListDataListener> listeners = new LinkedList<ListDataListener>();
    
    private HashMap<String, IDescrizioneProdottoRemote> descrizioni = new HashMap<>();

    @Override
    public int getSize() {
        return this.descrizioni.size();
    }

    public void clear(){
        this.descrizioni.clear();
    }
    
    public void addElement(IDescrizioneProdottoRemote descrizione)
    {
        try
        {
            String code = descrizione.getCodiceProdotto().getCodice();
            System.err.println("Aggiungi elemento: "+code);
            if(!this.descrizioni.containsKey(code))
                this.descrizioni.put(code, descrizione);
        } catch (RemoteException ex)
        {
            Logger.getLogger(RigheDiVenditaListModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.fireContentsChanged(0, this.descrizioni.size()-1);
    }

    public void remove(IDescrizioneProdottoRemote descrizione) throws RemoteException
    {
        String code = descrizione.getCodiceProdotto().getCodice();
        this.descrizioni.remove(code);
        this.fireContentsChanged(0, this.descrizioni.size()-1);
    }
    
    @Override
    public IDescrizioneProdottoRemote getElementAt(int index) {
        System.err.println("Asked index: "+index);
        return (IDescrizioneProdottoRemote) this.descrizioni.values().toArray()[index];
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        this.listeners.add(l);
    }
    
    public ListDataListener[] getListDataListeners(){
        return (ListDataListener[]) this.listeners.toArray();
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        this.listeners.remove(l);
    }  

    protected void fireIntervalAdded(int index0, int index1)
    {
        if(this.listeners.size() > 0)
        {
            ListDataEvent le = new ListDataEvent(this, ListDataEvent.INTERVAL_ADDED, index0, index1);
            for(ListDataListener listener:listeners)
                listener.intervalAdded(le);
        }
    }
    
    protected void fireIntervalRemoved(Object source, int index0, int index1)
    {
        if(this.listeners.size() > 0)
        {
            ListDataEvent le = new ListDataEvent(this, ListDataEvent.INTERVAL_REMOVED, index0, index1);
            for(ListDataListener listener:listeners)
                listener.intervalRemoved(le);
        }
    }
    
    protected void fireContentsChanged(int index0, int index1)
    {
        if(this.listeners.size() > 0)
        {
            ListDataEvent le = new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, index0, index1);
            for(ListDataListener listener:listeners)
                listener.contentsChanged(le);
        }
    }
    
}
