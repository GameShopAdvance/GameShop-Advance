/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.ui.swing.lists.models;

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
 * Classe che si occupa della gestione delle righe di transazione su terminale Customer.Tale classe raccoglie le
 * righe di transazione dei vari prodotti prenotati in una hashmap per la corretta e ordinata visualizzazione
 * sul terminale.
 * @author Lorenzo Di Giuseppe
 */
public class RigheDiVenditaListModel implements ListModel<IRigaDiTransazioneRemote> {

    private LinkedList<ListDataListener> listeners;
    
    private HashMap<String, IRigaDiTransazioneRemote> righe = new HashMap<String, IRigaDiTransazioneRemote>();
    
    private boolean header;
    
    public RigheDiVenditaListModel()
    {
        this.listeners = new LinkedList<>();
    }
    
    /**
     * @param righe
     * @throws RemoteException
     */
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
    
    /**
     * @param head
     */
    public void setHeader(boolean head)
    {
        this.header = head;
    }
    
    @Override
    public int getSize() {
        if(!header)
            return this.righe.size();
        else
            return this.righe.size()+1;
    }

    public void clear(){
        this.righe.clear();
    }
    
    /**
     * @param riga
     */
    public void addElement(IRigaDiTransazioneRemote riga)
    {
        try
        {
            String code = riga.getDescrizione().getCodiceProdotto().getCodice();
            if(!this.righe.containsKey(code))
                this.righe.put(code, riga);
        } catch (RemoteException ex)
        {
            Logger.getLogger(RigheDiVenditaListModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.fireContentsChanged(0, this.righe.size()-1);
    }
    
    /**
     * @param riga
     * @throws RemoteException
     */
    public void remove(IRigaDiTransazioneRemote riga) throws RemoteException
    {
        String code = riga.getDescrizione().getCodiceProdotto().getCodice();
        this.righe.remove(code);
        this.fireContentsChanged(0, this.righe.size()-1);
    }
    
    @Override
    public IRigaDiTransazioneRemote getElementAt(int index) {
        if(header)
        {
            if(index == 0)
                return null;
            index -= 1;
        }
        return (IRigaDiTransazioneRemote) this.righe.values().toArray()[index];
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
}
