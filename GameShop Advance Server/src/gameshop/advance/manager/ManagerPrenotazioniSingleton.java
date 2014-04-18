package gameshop.advance.manager;

import gameshop.advance.exceptions.QuantityException;
import gameshop.advance.exceptions.db.ObjectAlreadyExistsDbException;
import gameshop.advance.interfaces.IObserver;
import gameshop.advance.interfaces.IPrenotazione;
import gameshop.advance.technicalservices.db.DbPrenotazioneSingleton;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class ManagerPrenotazioniSingleton {
    
    private static ManagerPrenotazioniSingleton instance;
    private LinkedList<IPrenotazione> prenotazioniDaEvadere = new LinkedList<IPrenotazione>();
    private LinkedList<IObserver> listeners;
    
    private ManagerPrenotazioniSingleton(){
        this.listeners = new LinkedList<IObserver>();
        LinkedList<IPrenotazione> readNotProcessed = DbPrenotazioneSingleton.getInstance().readNotProcessed();
        if(readNotProcessed != null)
            this.prenotazioniDaEvadere = readNotProcessed;
    }
    
    public static ManagerPrenotazioniSingleton getInstance(){
        
        if (ManagerPrenotazioniSingleton.instance == null){
            ManagerPrenotazioniSingleton.instance = new ManagerPrenotazioniSingleton();
        }
        return ManagerPrenotazioniSingleton.instance;
    }
    
    public void addListener(IObserver obs){
        this.listeners.add(obs);
    }
    
    public void removeListeners(IObserver obs){
        if(obs == null)
            this.listeners.clear();
        else
            this.listeners.remove(obs);
    }
    
    public void store(IPrenotazione prenotazione) throws ObjectAlreadyExistsDbException, RemoteException, QuantityException
    {
        System.err.println("Prenotazione in manager: "+prenotazione);
        DbPrenotazioneSingleton.getInstance().create(prenotazione);
        System.err.println("Prenotazione in manager dopo create: "+prenotazione);
        this.addPrenotazione(prenotazione);
        System.err.println("Prenotazione in manager dopo addPrenotazione: "+prenotazione);
        this.notificaListeners();
        System.err.println("Prenotazione in manager dopo notificaListeners: "+prenotazione);
    }

    private void notificaListeners() {
        Iterator<IObserver> iter = this.listeners.iterator();
        while(iter.hasNext())
        {
            iter.next().notifica(this);
        }
    }
    
    public void addPrenotazione(IPrenotazione pren) throws RemoteException, QuantityException{
        System.err.println("Prenotazione in addPrenotazione: "+pren);
        if(pren != null && pren.isCompleted() && !pren.getEvasa() && this.prenotazioniDaEvadere.indexOf(pren) < 0)
        {
            this.prenotazioniDaEvadere.push(pren);
            ManagerFornitureSingleton.getInstance().addPrenotazione(pren);
        }
    }
    
    public void removePrenotazione(IPrenotazione pren) throws QuantityException, RemoteException{
        if(pren != null && pren.isCompleted() && !pren.getEvasa())
        {
            if(this.prenotazioniDaEvadere.indexOf(pren) >= 0)
            {
                this.prenotazioniDaEvadere.remove(pren);
            }
            ManagerFornitureSingleton.getInstance().removePrenotazione(pren);
        }
    }
    
    public IPrenotazione getLastNotProcessed(){
        return this.prenotazioniDaEvadere.getLast();        
    }
    
    public List<IPrenotazione> getNotProcessed()
    {
        return this.prenotazioniDaEvadere;
    }
}
