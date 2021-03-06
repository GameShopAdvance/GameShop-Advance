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

/** Gestisce le informazioni sulle pernotazioni
 * 
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class ManagerPrenotazioniSingleton {
    
    private static ManagerPrenotazioniSingleton instance;
    private final LinkedList<IPrenotazione> prenotazioniDaEvadere;
    private final LinkedList<IObserver> listeners;
    
    private ManagerPrenotazioniSingleton(){
        this.listeners = new LinkedList<>();
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
    
    public void store(IPrenotazione prenotazione) throws ObjectAlreadyExistsDbException, RemoteException, QuantityException
    {
        DbPrenotazioneSingleton.getInstance().create(prenotazione);
        this.addPrenotazione(prenotazione);
        this.notificaListeners();
    }

    private void notificaListeners() {
        Iterator<IObserver> iter = this.listeners.iterator();
        while(iter.hasNext())
        {
            iter.next().notifica(this);
        }
    }
    /**
     *
     * @param pren
     * @throws java.rmi.RemoteException
     * @throws gameshop.advance.exceptions.QuantityException
     */
    public void addPrenotazione(IPrenotazione pren) throws RemoteException, QuantityException{
        if(pren != null && pren.isCompleted() && !pren.getEvasa() && this.prenotazioniDaEvadere.indexOf(pren) < 0)
        {
            this.prenotazioniDaEvadere.push(pren);
            ManagerFornitureSingleton.getInstance().addPrenotazione(pren);
        }
    }
     /**
     *
     * @param pren
     * @throws java.rmi.RemoteException
     * @throws gameshop.advance.exceptions.QuantityException
     */
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
     /**
     *
     * @return L'ultima prenotazione non evasa
     */
    public IPrenotazione getLastNotProcessed(){
        return this.prenotazioniDaEvadere.getLast();        
    }
     /**
     *
     * @return Lista delle prenotazioni da evadere
     */
    public List<IPrenotazione> getNotProcessed()
    {
        return this.prenotazioniDaEvadere;
    }
}
