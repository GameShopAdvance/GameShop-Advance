package gameshop.advance.model;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.exceptions.ProdottoNotFoundException;
import gameshop.advance.exceptions.QuantityException;
import gameshop.advance.exceptions.QuantityNotInStockException;
import gameshop.advance.interfaces.IDescrizioneProdotto;
import gameshop.advance.interfaces.ITransazione;
import gameshop.advance.interfaces.remote.ICassaRemote;
import gameshop.advance.interfaces.remote.IRemoteObserver;
import gameshop.advance.model.transazione.CartaCliente;
import gameshop.advance.model.transazione.Vendita;
import gameshop.advance.utility.IDProdotto;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * La classe Cassa svolge tutti i ruoli di un terminale cassa all'interno di un negozio.Gestisce 
 * le vendite, l'inserimento dei prodotti e i pagamenti.Ha un id proprio dato
 * che ogni cassa presente all'interno del negozio deve essere distinta dalle altre.Conosce 
 * il catalogo dei prodotti che utilizzerà ogni volta che verrà aggiunto un prodotto alla
 * vendita corrente.
 *
 */
public class Cassa extends UnicastRemoteObject implements ICassaRemote {

    private int numeroCassa;
    private ITransazione venditaCorrente;

    /**
     * Il Costruttore della Cassa recupera il catalogo dei prodotti,
     * imposta a null la variabile venditaCorrente, dato che all'avvio della 
     * Cassa non si sta facendo ancora nessuna vendita,
     * utilizza infine il parametro che riceve in input per impostare il numero
     * di Cassa.
     * @param idCassa
     * @throws RemoteException
     * @throws gameshop.advance.exceptions.InvalidMoneyException
     */
    public Cassa(int idCassa) throws RemoteException, InvalidMoneyException {
        this.venditaCorrente = null;
        this.numeroCassa = idCassa;
        System.err.println("Nuova cassa: n°" + this.numeroCassa);
    }

    /**
     * Avvia una nuova vendita e ne salva lo stato nella variabile venditaCorrente.
     * @throws java.rmi.RemoteException
     */
    @Override
    public void avviaNuovaVendita() throws RemoteException {
        this.venditaCorrente = new Vendita();
        System.err.println("Wrapped decorator");
        this.venditaCorrente.addSconti(NegozioSingleton.getInstance().getScontiAttuali());
    }

    /**
     * Aggiunge un osservatore remoto alla vendita che si sta trattando in quel momento.
     * @param obs
     */
    @Override
    public void aggiungiListener(IRemoteObserver obs) {
        this.venditaCorrente.aggiungiListener(obs);
    }
    
    /**
     * Rimuove un osservatore remoto dalla vendita che si sta trattando in quel momento.
     * @param obs
     */
    @Override
    public void rimuoviListener(IRemoteObserver obs){
        this.venditaCorrente.rimuoviListener(obs);
    }

    /**
     * Inserisce una nuova riga di vendita all'interno di una vendita.Tramite i parametri 
     * ricevuti in ingresso, recupera dal catalogo dei prodotti la descrizione del prodotto 
     * che si vuole aggiungere alla vendita.Utilizza infine la descrizione appena recuperata 
     * e la quantità(ricevuta in input) dei prodotti da aggiungere per creare una nuova riga di
     * vendita ed aggiungerla alla vendita corrente.Questo metodo segnala due eccezioni se 
     * la quantità ricevuta in input è inferiore di uno e se la descrizione del prodotto 
     * non viene trovata all'interno del catalogo.
     * @param codiceProdotto
     * @param quantity
     * @throws java.rmi.RemoteException
     * @throws gameshop.advance.exceptions.QuantityException
     * @throws gameshop.advance.exceptions.ProdottoNotFoundException
     * @throws gameshop.advance.exceptions.QuantityNotInStockException
     */
    @Override
    public void inserisciProdotto(IDProdotto codiceProdotto, Integer quantity) throws RemoteException, QuantityException, ProdottoNotFoundException, QuantityNotInStockException
    {
        if ( quantity < 1 )
            throw new QuantityException(quantity);
        
        IDescrizioneProdotto desc = CatalogoProdottiSingleton.getInstance().getDescrizioneProdotto(codiceProdotto);
        if(desc.getQuantitaDisponibile() < quantity)
        {
            throw new QuantityNotInStockException();
        }
        else
            desc.setQuantitaDisponibile(desc.getQuantitaDisponibile() - quantity);
        
        if(desc == null)
            throw new ProdottoNotFoundException(codiceProdotto);
        this.venditaCorrente.inserisciProdotto(desc, quantity);

    }

    /**
     * Conlude la vendita richiamando la funzione completaVendita della venditaCorrente.
     * @throws java.rmi.RemoteException
     */
    @Override
    public void concludiVendita() throws RemoteException {
        this.venditaCorrente.completaTransazione();
    }

    /**
     * Gestisce il pagamento di una vendita utilizzando il parametro ricevuto in ingresso, che 
     * rappresenta l'ammontare dovuto.Richiama il metodo gestisciPagamento di venditaCorrente 
     * passandogli tale ammontare e successivamente aggiunge la vendita all'elenco delle vendite
     * effettuate nel negozio.Infine, dato che la vendita è conclusa, imposta come nulla la vendita 
     * corrente.
     * @param ammontare
     * @throws RemoteException
     * @throws gameshop.advance.exceptions.InvalidMoneyException
     */
    @Override
    public void gestisciPagamento(Money ammontare) throws RemoteException, InvalidMoneyException {
        this.venditaCorrente.gestisciPagamento(ammontare);
        this.venditaCorrente.setId(NegozioSingleton.getInstance().getNextId());
        this.venditaCorrente.rimuoviListener(null);
        NegozioSingleton.getInstance().registraVendita(this.venditaCorrente);
    }

    public void setIdCassa(int idCassa) {
        this.numeroCassa = idCassa;
    }

    public int getIdCassa() {
        return numeroCassa;
    }
    
    @Override
    public void inserisciTesseraCliente(int codiceTessera) throws RemoteException
    {
        CartaCliente carta = NegozioSingleton.getInstance().getCliente(codiceTessera);
        System.err.println("Tessera: "+carta);
        if(carta!=null)
        {
            System.err.println("Carta cliente:"+carta);
            this.venditaCorrente.setCliente(carta);
        }
    }

}
