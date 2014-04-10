/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.manager;

import gameshop.advance.interfaces.IDescrizioneProdotto;
import gameshop.advance.interfaces.IPrenotazione;
import gameshop.advance.interfaces.remote.IRigaDiTransazioneRemote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Salx
 */
public class ManagerFornitureSingleton {
    
    private static ManagerFornitureSingleton instance;
    
    private HashMap<String, InformazioniProdotto> informazioni;
    
    public ManagerFornitureSingleton(){
        try {
            this.informazioni = new HashMap<>();
            this.aggiornaDescrizioni(ManagerProdottiSingleton.getInstance().getMonitored());
            this.aggiornaPrenotazioni(ManagerPrenotazioniSingleton.getInstance().getNotProcessed());
        } catch (RemoteException ex) {
            Logger.getLogger(ManagerFornitureSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ManagerFornitureSingleton getInstance(){
        
        if (ManagerFornitureSingleton.instance == null){
            ManagerFornitureSingleton.instance = new ManagerFornitureSingleton();
        }
        return ManagerFornitureSingleton.instance;
    }
    
    /**
     *
     * @param descrizioni
     */
    public void aggiornaDescrizioni(List<IDescrizioneProdotto> descrizioni){
        
    }
    
    /**
     *
     * @param prenotazioni
     */
    public void aggiornaPrenotazioni(List<IPrenotazione> prenotazioni) throws RemoteException{
        for(IPrenotazione pren: prenotazioni)
        {
            Iterator<IRigaDiTransazioneRemote> iter = (Iterator<IRigaDiTransazioneRemote>) pren.getRigheDiVendita();
            while(iter.hasNext())
            {
                IRigaDiTransazioneRemote rdt = iter.next();
                IDescrizioneProdotto desc = (IDescrizioneProdotto) rdt.getDescrizione();
                if(this.informazioni.containsKey(desc.getCodiceProdotto().getCodice()))
                {
                    this.informazioni.get(desc.getCodiceProdotto().getCodice()).addPrenotazione(pren);
                }
                else{
                    InformazioniProdotto ip = new InformazioniProdotto(desc);
                    ip.addPrenotazione(pren);
                    this.informazioni.put(desc.getCodiceProdotto().getCodice(), ip);
                }
            }
        }
    }
    
    public void addPrenotazione(IPrenotazione pren){
        
    }
    
    
    public void removePrenotazione(IPrenotazione pren){
        
    }

    public Iterator<InformazioniProdotto> getInformazioni() {
        return this.informazioni.values().iterator();
    }    
}
