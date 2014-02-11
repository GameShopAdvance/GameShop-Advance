/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.transazione;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.interfaces.IScontoVenditaStrategy;
import gameshop.advance.interfaces.ITransazione;
import gameshop.advance.model.DescrizioneProdotto;
import gameshop.advance.model.Pagamento;
import gameshop.advance.model.vendita.sconto.vendita.ScontoVenditaStrategyComposite;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public abstract class Transazione implements ITransazione {
    protected Integer idVendita;
    protected LinkedList<RigaDiVendita> righeDiVendita = new LinkedList<>();
//    protected LinkedList<IRemoteObserver> observers = new LinkedList<>();
    protected CartaCliente cliente;
    protected Pagamento pagamento;
    protected Date date;
    protected ScontoVenditaStrategyComposite strategiaDiSconto;
    protected boolean completata;

    public Transazione() {
    }

    @Override
    public Integer getIdVendita() {
        return idVendita;
    }

    @Override
    public void setIdVendita(Integer idVendita) {
        this.idVendita = idVendita;
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
    @Override
    public void inserisciProdotto(DescrizioneProdotto desc, int quantity) throws RemoteException {
        RigaDiVendita rdv = new RigaDiVendita(desc, quantity);
        this.righeDiVendita.add(rdv);
//        this.notificaListeners();
    }
//
//    /**
//     * Aggiunge l'osservatore ricevuto in input alla vendita corrente.
//     * @param obs
//     */
//    @Override
//    public void aggiungiListener(IRemoteObserver obs) {
//        this.observers.add(obs);
//    }
//
//    /**
//     * Rimuove l'osservatore ricevuto in input dalla vendita corrente.
//     * @param obs
//     */
//    @Override
//    public void rimuoviListener(IRemoteObserver obs) {
//        if (obs == null) {
//            this.observers = null;
//        } else {
//            this.observers.remove(obs);
//        }
//    }

    /**
     * Implementazione del metodo dell'interfaccia di vendita.Calcola il totale
     * della transazione sommando i valori sub-totali di tutte le righe di vendita
     * della transazione.
     * @return il totale della transazione
     */
    @Override
    public Money getTotal() {
        return this.strategiaDiSconto.getTotal(this);
    }

    /**
     * Utilizza il totale della transazione e il pagamento ricevuto per calcolare
     * il resto.
     * @return il resto da restituire
     * @throws InvalidMoneyException
     */
    @Override
    public Money getResto() throws InvalidMoneyException {
        Money m = this.getTotal();
        if (this.pagamento == null) {
            return null;
        }
        Money p = this.pagamento.getAmmontare();
        return p.subtract(m);
    }

    /**
     * Crea un nuovo oggetto pagamento utilizzando l'ammontare della vendita corrente.Aggiunge
     * tale pagamento alla vendita e invia una notifica gli osservatori in ascolto.
     * @param ammontare
     * @throws gameshop.advance.exceptions.InvalidMoneyException
     * @throws java.rmi.RemoteException
     */
    @Override
    public void gestisciPagamento(Money ammontare) throws InvalidMoneyException, RemoteException {
        if (!ammontare.equals(this.getTotal()) && !ammontare.greater(this.getTotal())) {
            throw new InvalidMoneyException(ammontare);
        }
        this.pagamento = new Pagamento(ammontare);
//        this.notificaListeners();
    }

    /**
     * Imposta lo stato della vendita come completata e invia una notifica agli
     * osservatori in ascolto.
     * @throws RemoteException
     */
    @Override
    public void completaVendita() throws RemoteException {
        this.completata = true;
//        this.notificaListeners();
    }

//    /**
//     * Invia la vendita corrente a tutti gli osservatori in ascolto, i quali
//     * recupereranno poi da tale vendita le informazioni di cui hanno bisogno.
//     * @throws RemoteException
//     */
//    protected void notificaListeners() throws RemoteException {
//        for (IRemoteObserver o : this.observers) {
//            o.notifica();
//        }
//    }

    @Override
    public List getRigheDiVendita() {
        return this.righeDiVendita;
    }

    @Override
    public CartaCliente getCliente() {
        return this.cliente;
    }

    @Override
    public void setCliente(CartaCliente c) throws RemoteException {
        this.cliente = c;
//        this.notificaListeners();
    }
    
    public void setStrategiaSconto(ScontoVenditaStrategyComposite sconto)
    {
        this.strategiaDiSconto = sconto;
    }

    @Override
    public void setSconti(LinkedList<IScontoVenditaStrategy> scontiAttuali) {
        for (IScontoVenditaStrategy sconto : scontiAttuali) {
            this.strategiaDiSconto.add(sconto);
        }
    }
    
}
