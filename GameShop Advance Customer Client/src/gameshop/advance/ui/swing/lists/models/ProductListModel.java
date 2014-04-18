/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.ui.swing.lists.models;

import gameshop.advance.interfaces.remote.IDescrizioneProdottoRemote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 * Classe che si occupa della gestione delle informazioni prodotto raccolta in una hashmap per la corretta
 * e ordinata visualizzazione sul terminale.
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
    
    /**
     * @param descrizione
     */
    public void addElement(IDescrizioneProdottoRemote descrizione)
    {
        try
        {
            String code = descrizione.getCodiceProdotto().getCodice();
            if(!this.descrizioni.containsKey(code))
                this.descrizioni.put(code, descrizione);
        } catch (RemoteException ex)
        {
            Logger.getLogger(RigheDiVenditaListModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.fireContentsChanged(0, this.descrizioni.size()-1);
    }

    /**
     * @param descrizione
     * @throws RemoteException
     */
    public void remove(IDescrizioneProdottoRemote descrizione) throws RemoteException
    {
        String code = descrizione.getCodiceProdotto().getCodice();
        this.descrizioni.remove(code);
        this.fireContentsChanged(0, this.descrizioni.size()-1);
    }
    
    /**
     * @param index
     */
    @Override
    public IDescrizioneProdottoRemote getElementAt(int index) {
        return (IDescrizioneProdottoRemote) this.descrizioni.values().toArray()[index];
    }

    /**
     * @param l
     */
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

    /**
     * @return
     */
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
    
}
