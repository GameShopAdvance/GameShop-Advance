package gameshop.advance.manager;

import gameshop.advance.exceptions.ObjectAlreadyExistsDbException;
import gameshop.advance.interfaces.IObserver;
import gameshop.advance.interfaces.IPrenotazione;
import gameshop.advance.technicalservices.db.DbPrenotazioneSingleton;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class ManagerPrenotazioniSingleton {
    
    private static ManagerPrenotazioniSingleton instance;
    private LinkedList<IPrenotazione> prenotazioniDaEvadere;
    private LinkedList<IObserver> listeners;
    
    public ManagerPrenotazioniSingleton(){
        this.listeners = new LinkedList<IObserver>();
        this.prenotazioniDaEvadere = DbPrenotazioneSingleton.getInstance().readNotProcessed();
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
    
    public void store(IPrenotazione prenotazione) throws ObjectAlreadyExistsDbException
    {
        DbPrenotazioneSingleton.getInstance().create(prenotazione);
        this.notificaListeners();
    }

    private void notificaListeners() {
        Iterator<IObserver> iter = this.listeners.iterator();
        while(iter.hasNext())
        {
            iter.next().notifica(this);
        }
    }
    
    public void addPrenotazione(IPrenotazione pren){
        if(this.prenotazioniDaEvadere.indexOf(pren) < 0)
            this.prenotazioniDaEvadere.push(pren);
    }
    
    public IPrenotazione getLastNotProcessed(){
        return this.prenotazioniDaEvadere.getLast();        
    }
    
    public LinkedList<IPrenotazione> getNotProcessed()
    {
        return this.prenotazioniDaEvadere;
    }
}
