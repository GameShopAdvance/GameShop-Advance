/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.ui.swing.lists.models;

import gameshop.advance.interfaces.remote.forniture.IInformazioniProdottoRemote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 * Classe che definisce gli elementi della lista che conterrà tutti i vari prodotti da ordinare.
 * @author Lorenzo Di Giuseppe
 */
public class FornitureListModel implements ListModel<IInformazioniProdottoRemote>
{
    private LinkedList<ListDataListener> listeners;
    
    private HashMap<String, IInformazioniProdottoRemote> informazioni = new HashMap<>();
    
    /**
     *
     */
    public FornitureListModel()
    {
        this.listeners = new LinkedList<>();
    }
    
    /**
     * @param informazioni
     * @throws RemoteException
     */
    public FornitureListModel(List<IInformazioniProdottoRemote> informazioni) throws RemoteException
    {
        this();
        for(IInformazioniProdottoRemote info: informazioni)
        {
            String code = info.getDescrizione().getCodiceProdotto().getCodice();
//            if(!this.righe.containsKey(code))
                this.informazioni.put(code, info);
        }
    }
    
    @Override
    public int getSize() {
        return this.informazioni.size();
    }

    public void clear(){
//        this.informazioni.clear();
    }
    
    /**
     * @param info
     * @throws RemoteException
     */
    public void addElement(IInformazioniProdottoRemote info) throws RemoteException
    {
        String code = info.getDescrizione().getCodiceProdotto().getCodice();
        if(!this.informazioni.containsKey(code))
            this.informazioni.put(code, info);
        this.fireContentsChanged(0, this.informazioni.size()-1);
    }
    
    /**
     * @param riga
     * @throws RemoteException
     */
    public void remove(IInformazioniProdottoRemote riga) throws RemoteException
    {
        String code = riga.getDescrizione().getCodiceProdotto().getCodice();
        this.informazioni.remove(code);
        this.fireContentsChanged(0, this.informazioni.size()-1);
    }
    
    /**
     * @param index
     */
    @Override
    public IInformazioniProdottoRemote getElementAt(int index) {
        return (IInformazioniProdottoRemote) this.informazioni.values().toArray()[index];
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
