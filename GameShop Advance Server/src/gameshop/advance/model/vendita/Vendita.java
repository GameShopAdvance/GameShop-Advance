package gameshop.advance.model.vendita;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.model.DescrizioneProdotto;
import gameshop.advance.model.Pagamento;
import gameshop.advance.remote.interfaces.IRemoteObserver;
import gameshop.advance.remote.interfaces.IVenditaRemote;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.LinkedList;

/**
 * La classe Vendita implementa l'interfaccia remota IVenditaRemote.Gestisce tutte le
 * azioni di una vendita inerenti i pagamenti;crea e aggiunge nuove righe di vendita alla
 * vendita corrente;aggangia alla vendita corrente e informa gli osservatori che hanno 
 * bisogno dei cambi di stato della vendita.
 * @author Salx
 */
public class Vendita implements IVenditaRemote
{

    private LinkedList<RigaDiVendita> righeDiVendita = new LinkedList<>();
    private LinkedList<IRemoteObserver> observers = new LinkedList<>();
    private Pagamento pagamento;
    private Date date;
    private boolean completata;

    /**
     * Costruttore di vendita.Imposta in automatico la data della vendita, e imposta
     * lo stato della vendita come non completato.
     */
    public Vendita()
    {
        this.date = new Date();
        this.completata = false;            
    }

    /**
     * Utilizza i parametri ricevuti in ingresso (descrizione prodotto e quantità) per
     * creare una riga di vendita.Aggiunge poi questa riga di vendita all'elenco di righe della
     * vendita corrente.Notifica tale aggiunta agli osservatori in ascolto per far
     * aggiornare l'output.
     * @param desc
     * @param quantity
     * @throws java.rmi.RemoteException
     */
    public void creaRigaDiVendita(DescrizioneProdotto desc, int quantity) throws RemoteException 
    {
        RigaDiVendita rdv = new RigaDiVendita(desc, quantity);
        this.righeDiVendita.add(rdv);
        this.notificaListeners(); 
    }

    /**
     * Aggiunge l'osservatore ricevuto in input alla vendita corrente.
     * @param obs
     */
     public void aggiungiListener(IRemoteObserver obs){
        this.observers.add(obs);
    }

     /**
     * Rimuove l'osservatore ricevuto in input dalla vendita corrente.
     * @param obs
     */
     public void rimuoviListener(IRemoteObserver obs){
         if(obs == null)
             this.observers = null;
         else
            this.observers.remove(obs);
     }

    /**
     * Implementazione del metodo dell'interfaccia di vendita.Calcola il totale 
     * della transazione sommando i valori sub-totali di tutte le righe di vendita
     * della transazione.
     * @return il totale della transazione
     * @throws RemoteException
     * @throws InvalidMoneyException
     */
    @Override
    public Money getTotal() throws RemoteException, InvalidMoneyException
    {
        Money total = new Money();
        for(RigaDiVendita rdv : this.righeDiVendita)
        {
            total = total.add(rdv.getSubTotal());
        }
        return total;
    }

    /**
     * Utilizza il totale della transazione e il pagamento ricevuto per calcolare
     * il resto.
     * @return il resto da restituire 
     * @throws InvalidMoneyException
     * @throws RemoteException
     */
    @Override
    public Money getResto() throws InvalidMoneyException, RemoteException
    {
        Money m = this.getTotal();
        if(this.pagamento == null)
            return null;
        Money p = this.pagamento.getAmmontare();
        return p.subtract(m);
    }

    /**
     * Crea un nuovo oggetto pagamento utilizzando l'ammontare della vendita corrente.Aggiunge 
     * tale pagamento alla vendita e invia una notifica gli osservatori in ascolto.
     * @param ammontare
     * @throws java.rmi.RemoteException
     * @throws gameshop.advance.exceptions.InvalidMoneyException
     */
     public void gestisciPagamento(Money ammontare) throws RemoteException, InvalidMoneyException
    {
            if( !ammontare.equals(this.getTotal()) && !ammontare.greater(this.getTotal()))
                throw new InvalidMoneyException(ammontare);
            this.pagamento = new Pagamento(ammontare);
            
            this.notificaListeners();
    }

    /**
     * Imposta lo stato della vendita come completata e invia una notifica agli
     * osservatori in ascolto.
     * @throws RemoteException
     */
    public void completaVendita() throws RemoteException
    {
        this.completata = true;
        this.notificaListeners();
    }

    /**
     * Invia la vendita corrente a tutti gli osservatori in ascolto, i quali 
     * recupereranno poi da tale vendita le informazioni di cui hanno bisogno.
     * @throws RemoteException
     */
    private void notificaListeners() throws RemoteException {
        for(IRemoteObserver o : this.observers){
            System.out.println("Observer: "+o);
            o.notifica(this);
        }
    }

}
