/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.interfaces.remote.ITransazioneRemote;
import gameshop.advance.model.DescrizioneProdotto;
import gameshop.advance.model.transazione.CartaCliente;
import gameshop.advance.model.transazione.sconto.vendita.ScontoVenditaStrategyComposite;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;
import java.util.LinkedList;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public interface ITransazione extends ITransazioneRemote {

    /**
     * Imposta lo stato della vendita come completata e invia una notifica agli
     * osservatori in ascolto.
     */
    void completaTransazione();

    /**
     * Utilizza i parametri ricevuti in ingresso (descrizione prodotto e quantità) per
     * creare una riga di vendita.Aggiunge poi questa riga di vendita all'elenco di righe della
     * vendita corrente.Notifica tale aggiunta agli osservatori in ascolto per far
     * aggiornare l'output.
     * @param desc
     * @param quantity
     * @throws java.rmi.RemoteException
     */
    void inserisciProdotto(DescrizioneProdotto desc, int quantity) throws RemoteException;

    /**
     * Crea un nuovo oggetto pagamento utilizzando l'ammontare della vendita corrente.Aggiunge
     * tale pagamento alla vendita e invia una notifica gli osservatori in ascolto.
     * @param ammontare
     * @throws gameshop.advance.exceptions.InvalidMoneyException
     * @throws java.rmi.RemoteException
     */
    void gestisciPagamento(Money ammontare) throws InvalidMoneyException, RemoteException;

    CartaCliente getCliente();

    Integer getId();

    void setCliente(CartaCliente c);

    void setId(Integer idVendita);

    void addSconti(LinkedList<IScontoVenditaStrategy> scontiAttuali);
    
    void setSconto(ScontoVenditaStrategyComposite sconto);
    
}
