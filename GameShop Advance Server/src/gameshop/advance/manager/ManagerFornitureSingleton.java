/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.manager;

import gameshop.advance.GameShopAdvance;
import gameshop.advance.exceptions.QuantityException;
import gameshop.advance.interfaces.IDescrizioneProdotto;
import gameshop.advance.interfaces.IPrenotazione;
import gameshop.advance.interfaces.remote.IInformazioniProdottoRemote;
import gameshop.advance.interfaces.remote.IIteratorWrapperRemote;
import gameshop.advance.interfaces.remote.IRemoteObserver;
import gameshop.advance.interfaces.remote.IRigaDiTransazioneRemote;
import gameshop.advance.model.transazione.RigaDiTransazione;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Salx
 */
public class ManagerFornitureSingleton {
    
    private static ManagerFornitureSingleton instance;
    
    private final LinkedList<IRemoteObserver> listeners = new LinkedList<>();
    
    private HashMap<String, IInformazioniProdottoRemote> informazioni;
    
    public ManagerFornitureSingleton() throws QuantityException{
        try {
            this.informazioni = new HashMap<>();
            this.aggiornaDescrizioni(ManagerProdottiSingleton.getInstance().getMonitored());
            this.aggiornaPrenotazioni(ManagerPrenotazioniSingleton.getInstance().getNotProcessed());
        } catch (RemoteException ex) {
            Logger.getLogger(ManagerFornitureSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ManagerFornitureSingleton getInstance() throws QuantityException{
        
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
    
    /**
     *
     * @param descrizioni
     * @throws java.rmi.RemoteException
     */
    public void aggiornaDescrizioni(List<IDescrizioneProdotto> descrizioni) throws RemoteException
    {   
        if(descrizioni != null && !descrizioni.isEmpty())
        {
            Iterator<IDescrizioneProdotto> iter = descrizioni.iterator();
            while(iter.hasNext())
            {
                this.addDescrizione(iter.next());
            }
        }
    }
    
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
    }
    
    public void removeDescrizione(IDescrizioneProdotto desc) throws RemoteException
    {
        if(this.informazioni.containsKey(desc.getCodiceProdotto().getCodice()))
        {
            InformazioniProdotto ip = (InformazioniProdotto) this.informazioni.get(desc.getCodiceProdotto().getCodice());
            if(ip.getDescrizione().getQuantitaDisponibile() - ip.getPrenotati() > ip.getDescrizione().getQuantitaDiSoglia()) {
                this.informazioni.remove(desc.getCodiceProdotto().getCodice());
            }
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
    
    public void addPrenotazione(IPrenotazione pren) throws RemoteException, QuantityException{
        IIteratorWrapperRemote<IRigaDiTransazioneRemote> iter = pren.getRigheDiVendita();
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
                InformazioniProdotto ip = new InformazioniProdotto(desc, rdt.getQuantity());
                this.informazioni.put(desc.getCodiceProdotto().getCodice(), ip);
            }
        }
        this.print();
    }
    
    
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
        this.print();
    }

    public Iterator<IInformazioniProdottoRemote> getInformazioni() {
        return this.informazioni.values().iterator();
    }
    
    private void print(){
        try {
            Iterator<IInformazioniProdottoRemote> iter = this.getInformazioni();
            System.err.println("------FORNITURE------");
            while(iter.hasNext())
            {
                IInformazioniProdottoRemote next = iter.next();
                System.err.println(next.getDescrizione().getDescrizione());
                System.err.println("In stock: "+next.getDescrizione().getQuantitaDisponibile() +"/"
                                   +next.getDescrizione().getQuantitaDiSoglia());
                System.err.println("Prenotata: "+next.getPrenotati());
                System.err.println("Ordinata: "+next.getOrdinati());
                if(iter.hasNext())
                    System.err.println("--------------------");
            }
            System.err.println("------END------");
        }
        catch (RemoteException ex) {
            Logger.getLogger(GameShopAdvance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
