/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.transazione;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.exceptions.QuantityNotInStockException;
import gameshop.advance.interfaces.IDescrizioneProdotto;
import gameshop.advance.interfaces.IScontoProdottoStrategy;
import gameshop.advance.interfaces.IScontoVenditaStrategy;
import gameshop.advance.interfaces.ITransazione;
import gameshop.advance.interfaces.remote.IIteratorWrapperRemote;
import gameshop.advance.interfaces.remote.IRemoteObserver;
import gameshop.advance.interfaces.remote.IRigaDiTransazioneRemote;
import gameshop.advance.model.Pagamento;
import gameshop.advance.model.transazione.sconto.ScontoFactorySingleton;
import gameshop.advance.model.transazione.sconto.vendita.ScontoVenditaStrategyComposite;
import gameshop.advance.utility.IteratorWrapper;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.joda.time.DateTime;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class Vendita implements ITransazione {
    protected Integer idVendita;
    protected HashMap<IDescrizioneProdotto, IRigaDiTransazioneRemote> righeDiVendita = new HashMap<>();
    protected CartaCliente cliente;
    protected Pagamento pagamento;
    protected DateTime date;
    protected ScontoVenditaStrategyComposite strategiaDiSconto;
    protected boolean completata;
    private LinkedList<IRemoteObserver> listeners;

    public Vendita() {
        this.date = new DateTime();
        this.strategiaDiSconto = ScontoFactorySingleton.getInstance().getStrategiaScontoVendita();
    }

    @Override
    public Integer getId() {
        return idVendita;
    }

    /**
     *
     * @return
     */
    @Override
    public DateTime getDate()
    {
        return this.date;
    }
    
    @Override
    public void setId(Integer idVendita) throws RemoteException {
        this.idVendita = idVendita;
        this.notificaListener();
    }

    protected void quantityCheck(IDescrizioneProdotto desc, int quantity) throws QuantityNotInStockException, RemoteException{
        if(desc.getQuantitaDisponibile() < quantity)
        {
            throw new QuantityNotInStockException();
        }
        else
        {
            desc.setQuantitaDisponibile(desc.getQuantitaDisponibile() - quantity);
            System.err.println("Quantità "+desc.getDescrizione()+" : "+desc.getQuantitaDisponibile()+"/"+desc.getQuantitaDiSoglia());
        }
    }
    
    /**
     * Utilizza i parametri ricevuti in ingresso (descrizione prodotto e quantità) per
     * creare una riga di vendita.Aggiunge poi questa riga di vendita all'elenco di righe della
     * vendita corrente.Notifica tale aggiunta agli osservatori in ascolto per far
     * aggiornare l'output.
     * @param desc
     * @param quantity
     * @throws gameshop.advance.exceptions.QuantityNotInStockException
     */
    @Override
    public void inserisciProdotto(IDescrizioneProdotto desc, int quantity) throws RemoteException, QuantityNotInStockException {
        this.quantityCheck(desc, quantity);
        if(this.righeDiVendita.containsKey(desc)) {
            RigaDiTransazione rdt = (RigaDiTransazione) this.righeDiVendita.get(desc);
            rdt.setQuantity(rdt.getQuantity()+quantity);
        }
        else {
            IScontoProdottoStrategy strategiaSconto = ScontoFactorySingleton.getInstance().getStrategiaScontoProdotto(this);
            for(IScontoProdottoStrategy sconto:desc.getSconti(this.date))
            {
                strategiaSconto.add(sconto);
            }
            RigaDiTransazione rdv = new RigaDiTransazione(desc, quantity, strategiaSconto);
            this.righeDiVendita.put(desc, rdv);
        }
        this.notificaListener();
    }

    /**
     * Implementazione del metodo dell'interfaccia di vendita.Calcola il totale
     * della transazione sommando i valori sub-totali di tutte le righe di vendita
     * della transazione.
     * @return il totale della transazione
     * @throws java.rmi.RemoteException
     */
    @Override
    public Money getTotal() throws RemoteException {
        return this.strategiaDiSconto.getTotal(this);
    }

    /**
     * Utilizza il totale della transazione e il pagamento ricevuto per calcolare
     * il resto.
     * @return il resto da restituire
     * @throws InvalidMoneyException
     * @throws java.rmi.RemoteException
     */
    @Override
    public Money getResto() throws InvalidMoneyException, RemoteException {
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
    public void gestisciPagamento(Money ammontare) throws InvalidMoneyException,RemoteException {
        if(this.getTotal().greater(ammontare)) {
            throw new InvalidMoneyException(ammontare);
        }
        this.pagamento = new Pagamento(ammontare);
        this.notificaListener();
    }

    @Override
    public Money getPagamento()
    {
        return this.pagamento.getAmmontare();
    }
    /**
     * Imposta lo stato della vendita come completata e invia una notifica agli
     * osservatori in ascolto.
     * @throws java.rmi.RemoteException
     */
    @Override
    public void completaTransazione() throws RemoteException{
        this.completata = true;
        this.notificaListener();
    }

    /**
     *
     * @return
     * @throws java.rmi.RemoteException
     */
    @Override
    public IIteratorWrapperRemote<IRigaDiTransazioneRemote> getRigheDiVendita() throws RemoteException {
        return new IteratorWrapper<>(this.righeDiVendita.values().iterator());
    }

    @Override
    public CartaCliente getCliente() {
        return this.cliente;
    }

    @Override
    public void setCliente(CartaCliente c) {
        this.cliente = c;
    }
    
    @Override
    public void setStrategiaSconto(ScontoVenditaStrategyComposite sconto)
    {
        this.strategiaDiSconto = sconto;
    }

    @Override
    public void addSconti(LinkedList<IScontoVenditaStrategy> scontiAttuali) throws RemoteException {
        for (IScontoVenditaStrategy sconto : scontiAttuali) {
            this.strategiaDiSconto.add(sconto);
        }
        this.notificaListener();
    }

    @Override
    public boolean isCompleted() {
        return this.completata;
    }

    @Override
    public void aggiungiListener(IRemoteObserver obs) {
        if(this.listeners == null)
            this.listeners = new LinkedList<>();
        this.listeners.add(obs);
    }

    @Override
    public void rimuoviListener(IRemoteObserver obs) {
        if(obs == null)
            this.listeners = null;
        else
            this.listeners.remove(obs);
    }
    
    protected void notificaListener() throws RemoteException
    {
        if(this.listeners != null)
        {
            for(IRemoteObserver obs:this.listeners)
                obs.notifica(new TransazioneRemoteProxy(this));
        }
    }
    
    protected List<IRemoteObserver> getListeners()
    {
        return this.listeners;
    }
}
