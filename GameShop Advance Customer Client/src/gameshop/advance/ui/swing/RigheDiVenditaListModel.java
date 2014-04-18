/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.ui.swing;

import gameshop.advance.interfaces.remote.sales.IRigaDiTransazioneRemote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Lorenzo Di Giuseppe
 */
public class RigheDiVenditaListModel implements ListModel<IRigaDiTransazioneRemote> {

    private LinkedList<ListDataListener> listeners;
    
    private HashMap<String, IRigaDiTransazioneRemote> righe = new HashMap<String, IRigaDiTransazioneRemote>();
    
    public RigheDiVenditaListModel()
    {
        this.listeners = new LinkedList<>();
    }
    
    public RigheDiVenditaListModel(List<IRigaDiTransazioneRemote> righe) throws RemoteException
    {
        this();
        for(IRigaDiTransazioneRemote riga: righe)
        {
            String code = riga.getDescrizione().getCodiceProdotto().getCodice();
//            if(!this.righe.containsKey(code))
                this.righe.put(code, riga);
        }
    }
    
    @Override
    public int getSize() {
        return this.righe.size();
    }

    public void clear(){
        this.righe.clear();
    }
    
    public void addElement(IRigaDiTransazioneRemote riga)
    {
        try
        {
            String code = riga.getDescrizione().getCodiceProdotto().getCodice();
            System.err.println("Aggiungi elemento: "+code);
            if(!this.righe.containsKey(code))
                this.righe.put(code, riga);
        } catch (RemoteException ex)
        {
            Logger.getLogger(RigheDiVenditaListModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.fireContentsChanged(0, this.righe.size()-1);
    }
    
    public void remove(IRigaDiTransazioneRemote riga) throws RemoteException
    {
        String code = riga.getDescrizione().getCodiceProdotto().getCodice();
        this.righe.remove(code);
        this.fireContentsChanged(0, this.righe.size()-1);
    }
    
    @Override
    public IRigaDiTransazioneRemote getElementAt(int index) {
        System.err.println("Asked index: "+index);
        return (IRigaDiTransazioneRemote) this.righe.values().toArray()[index];
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
