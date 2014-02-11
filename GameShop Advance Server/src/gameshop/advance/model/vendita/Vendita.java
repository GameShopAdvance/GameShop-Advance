package gameshop.advance.model.vendita;

import gameshop.advance.interfaces.ITransazione;
import gameshop.advance.interfaces.remote.IRemoteObserver;
import gameshop.advance.model.vendita.sconto.ScontoFactorySingleton;
import gameshop.advance.model.vendita.sconto.vendita.ScontoVenditaStrategyComposite;
import java.util.Date;

/**
 * La classe Vendita implementa l'interfaccia remota IVenditaRemoteProxy.Gestisce tutte le
 azioni di una vendita inerenti i pagamenti;crea e aggiunge nuove righe di vendita alla
 vendita corrente;aggangia alla vendita corrente e informa gli osservatori che hanno 
 bisogno dei cambi di stato della vendita.
 * @author Salx
 */
public class Vendita extends Transazione
{
    private ITransazione transazione;
    
    
    /**
     * Costruttore di vendita.Imposta in automatico la data della vendita, e imposta
     * lo stato della vendita come non completato.
     */
    public Vendita()
    {
        this.date = new Date();
        this.completata = false;
        this.strategiaDiSconto = (ScontoVenditaStrategyComposite) ScontoFactorySingleton.getInstance().getStrategiaScontoVendita();
    }

    @Override
    public void aggiungiListener(IRemoteObserver obs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void rimuoviListener(IRemoteObserver obs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
