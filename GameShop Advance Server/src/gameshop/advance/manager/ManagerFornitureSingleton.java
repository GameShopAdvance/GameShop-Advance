/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.manager;

import gameshop.advance.exceptions.QuantityException;
import gameshop.advance.interfaces.IDescrizioneProdotto;
import gameshop.advance.interfaces.IPrenotazione;
import gameshop.advance.interfaces.remote.forniture.IInformazioniProdottoRemote;
import gameshop.advance.interfaces.remote.sales.IRigaDiTransazioneRemote;
import gameshop.advance.interfaces.remote.utility.IIteratorWrapperRemote;
import gameshop.advance.interfaces.remote.utility.IRemoteObserver;
import gameshop.advance.model.transazione.RigaDiTransazione;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Recupera e gestisce le informazioni sui prodotti e alle prenotazioni effettuate su di questi
 *
 * @author Lorenzo Di Giuseppe
 */
public class ManagerFornitureSingleton {
    
    private static ManagerFornitureSingleton instance;
    
    private final LinkedList<IRemoteObserver> listeners = new LinkedList<>();
    private LinkedList<IRemoteObserver> removeListener = new LinkedList<IRemoteObserver>();
    
    private HashMap<String, IInformazioniProdottoRemote> informazioni;
    
    
    private ManagerFornitureSingleton(){
        try {
            this.informazioni = new HashMap<>();
            this.aggiornaDescrizioni(ManagerProdottiSingleton.getInstance().getMonitored());
            this.aggiornaPrenotazioni(ManagerPrenotazioniSingleton.getInstance().getNotProcessed());
        } catch (RemoteException ex) {
            Logger.getLogger(ManagerFornitureSingleton.class.getName()).log(Level.SEVERE, null, ex);
        } catch (QuantityException ex)
        {
            Logger.getLogger(ManagerFornitureSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ManagerFornitureSingleton getInstance(){
        
        if (ManagerFornitureSingleton.instance == null){
            ManagerFornitureSingleton.instance = new ManagerFornitureSingleton();
        }
        return ManagerFornitureSingleton.instance;
    }
    
    public void addListener(IRemoteObserver obs){
        this.listeners.add(obs);
    }
    
    public void removeListener(IRemoteObserver obs){
        if(obs != null)
            this.listeners.remove(obs);
        else
            this.listeners.clear();
    }
    
    protected void notificaListeners()
    {
        if(this.listeners.size() > 0)
        {
            for(IRemoteObserver obs:this.listeners)
            {
                try
                {
                    obs.notifica(new FornitureRemoteProxy(this));
                } catch (RemoteException ex)
                {
                    Logger.getLogger(ManagerFornitureSingleton.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    protected void notificaRemoveListeners(IInformazioniProdottoRemote info)
    {
        if(this.removeListener.size() > 0)
        {
            for(IRemoteObserver obs:this.removeListener)
            {
                try
                {
                    obs.notifica(info);
                } catch (RemoteException ex)
                {
                    Logger.getLogger(ManagerFornitureSingleton.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }        
    }
    
    /**
     *
     * @param descrizioni
     * @throws java.rmi.RemoteException
     */
    public void aggiornaDescrizioni(List<IDescrizioneProdotto> descrizioni) throws RemoteException
    {   
        System.err.println("Descrizioni forniture: "+descrizioni.size());
        if(descrizioni != null && !descrizioni.isEmpty())
        {
            Iterator<IDescrizioneProdotto> iter = descrizioni.iterator();
            while(iter.hasNext())
            {
                this.addDescrizione(iter.next());
            }
            this.notificaListeners();
        }
    }
    /**
     *
     * @param desc
     * @throws java.rmi.RemoteException
     */
    public void addDescrizione(IDescrizioneProdotto desc) throws RemoteException
    {    
        if(this.informazioni.containsKey(desc.getCodiceProdotto().getCodice()))
        {
            InformazioniProdotto ip = (InformazioniProdotto) this.informazioni.get(desc.getCodiceProdotto().getCodice());
            ip.setDescrizione(desc);
        }
        else{
            InformazioniProdotto ip = new InformazioniProdotto(desc);
            this.informazioni.put(desc.getCodiceProdotto().getCodice(), ip);
        }
        this.notificaListeners();
    }
    /**
     *
     * @param desc
     * @throws java.rmi.RemoteException
     */
    public void removeDescrizione(IDescrizioneProdotto desc) throws RemoteException
    {
        System.err.println("Rimuovi da manager "+desc.getNomeProdotto());
        if(this.informazioni.containsKey(desc.getCodiceProdotto().getCodice()))
        {
            InformazioniProdotto ip = (InformazioniProdotto) this.informazioni.get(desc.getCodiceProdotto().getCodice());
            if(ip.getDescrizione().getQuantitaDisponibile() - ip.getPrenotati() > ip.getDescrizione().getQuantitaDiSoglia()) {
                this.informazioni.remove(desc.getCodiceProdotto().getCodice());
            }
            this.notificaRemoveListeners(ip);
        }
    }
    
    /**
     *
     * @param prenotazioni
     * @throws java.rmi.RemoteException
     * @throws gameshop.advance.exceptions.QuantityException
     */
    private void aggiornaPrenotazioni(List<IPrenotazione> prenotazioni) throws RemoteException, QuantityException
    {
        if(prenotazioni != null && !prenotazioni.isEmpty())
        {
            Iterator<IPrenotazione> iterator = prenotazioni.iterator();
            while(iterator.hasNext())
            {
                IPrenotazione pren = iterator.next();
                this.addPrenotazione(pren);
            }
        }
    }
    /**
     *
     * @param pren
     * @throws java.rmi.RemoteException
     * @throws gameshop.advance.exceptions.QuantityException
     */
    public void addPrenotazione(IPrenotazione pren) throws RemoteException, QuantityException{
        IIteratorWrapperRemote<IRigaDiTransazioneRemote> iter = pren.getRigheDiVendita();
        boolean notify = false;
        while(iter.hasNext())
        {
            RigaDiTransazione rdt = (RigaDiTransazione) iter.next();
            IDescrizioneProdotto desc = rdt.getDescrizione();
            if(this.informazioni.containsKey(desc.getCodiceProdotto().getCodice()))
            {
                InformazioniProdotto ip = (InformazioniProdotto) this.informazioni.get(desc.getCodiceProdotto().getCodice());
                ip.setPrenotati(ip.getPrenotati()+rdt.getQuantity());
            }
            else{
                if(!notify)
                    notify = true;
                InformazioniProdotto ip = new InformazioniProdotto(desc, rdt.getQuantity());
                this.informazioni.put(desc.getCodiceProdotto().getCodice(), ip);
            }
        }
        if(notify)
            this.notificaListeners();
    }
    
    /**
     *
     * @param pren
     * @throws java.rmi.RemoteException
     * @throws gameshop.advance.exceptions.QuantityException
     */
    public void removePrenotazione(IPrenotazione pren) throws RemoteException, QuantityException{
        IIteratorWrapperRemote<IRigaDiTransazioneRemote> iter = pren.getRigheDiVendita();
        while(iter.hasNext())
        {
            RigaDiTransazione rdt = (RigaDiTransazione) iter.next();
            IDescrizioneProdotto desc = rdt.getDescrizione();
            if(this.informazioni.containsKey(desc.getCodiceProdotto().getCodice()))
            {
                InformazioniProdotto ip = (InformazioniProdotto) this.informazioni.get(desc.getCodiceProdotto().getCodice());
                int prenQty = ip.getPrenotati()-rdt.getQuantity();
                if(prenQty > 0)
                    ip.setPrenotati(prenQty);
                else
                    ip.setPrenotati(0);
            }
        }
    }
    /**
     *
     * @return L'iteratore delle informazioni prodotto
     */
    public Iterator<IInformazioniProdottoRemote> getInformazioni(){
        return this.informazioni.values().iterator();
    }
    
    public void addRemoveListener(IRemoteObserver obs) {
        this.removeListener.add(obs);
    }
}
