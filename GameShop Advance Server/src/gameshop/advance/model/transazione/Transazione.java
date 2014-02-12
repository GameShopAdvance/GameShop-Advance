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
import gameshop.advance.model.transazione.sconto.vendita.ScontoVenditaStrategyComposite;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class Transazione implements ITransazione {
    protected Integer idVendita;
    protected LinkedList<RigaDiVendita> righeDiVendita = new LinkedList<>();
    protected CartaCliente cliente;
    protected Pagamento pagamento;
    protected Date date;
    protected ScontoVenditaStrategyComposite strategiaDiSconto;
    protected boolean completata;

    public Transazione() {
    }

    @Override
    public Integer getId() {
        return idVendita;
    }

    @Override
    public void setId(Integer idVendita) {
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
    public void inserisciProdotto(DescrizioneProdotto desc, int quantity) {
        RigaDiVendita rdv = new RigaDiVendita(desc, quantity);
        this.righeDiVendita.add(rdv);
    }

    /**
     * Implementazione del metodo dell'interfaccia di vendita.Calcola il totale
     * della transazione sommando i valori sub-totali di tutte le righe di vendita
     * della transazione.
     * @return il totale della transazione
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
        this.pagamento = new Pagamento(ammontare);
    }

    @Override
    public Money getPagamento()
    {
        return this.pagamento.getAmmontare();
    }
    /**
     * Imposta lo stato della vendita come completata e invia una notifica agli
     * osservatori in ascolto.
     */
    @Override
    public void completaTransazione() {
        this.completata = true;
    }

    @Override
    public List getRigheDiVendita() {
        return this.righeDiVendita;
    }

    @Override
    public CartaCliente getCliente() {
        return this.cliente;
    }

    @Override
    public void setCliente(CartaCliente c) {
        this.cliente = c;
    }
    
    public void setStrategiaSconto(ScontoVenditaStrategyComposite sconto)
    {
        this.strategiaDiSconto = sconto;
    }

    @Override
    public void addSconti(LinkedList<IScontoVenditaStrategy> scontiAttuali) {
        for (IScontoVenditaStrategy sconto : scontiAttuali) {
            this.strategiaDiSconto.add(sconto);
        }
    }

    @Override
    public void setSconto(ScontoVenditaStrategyComposite sconto) {
        this.strategiaDiSconto = sconto;
    }
    
}
