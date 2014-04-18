package gameshop.advance.model.transazione.sconto;

import gameshop.advance.interfaces.IScontoProdottoStrategy;
import gameshop.advance.interfaces.ITransazione;
import gameshop.advance.model.transazione.sconto.prodotti.ScontoProdottoMigliorePerClienteStrategyComposite;
import gameshop.advance.model.transazione.sconto.vendita.ScontoVenditaMigliorePerClienteStrategyComposite;
import gameshop.advance.model.transazione.sconto.vendita.ScontoVenditaStrategyComposite;

/** Factory degli sconti
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class ScontoFactorySingleton {
    private static ScontoFactorySingleton instance;
    
    private ScontoFactorySingleton()
    {}
    
    public static ScontoFactorySingleton getInstance()
    {
        if(instance == null)
            instance = new ScontoFactorySingleton();
        return instance;
    }
    /** 
     * @return La strategia di sconto applicata sulla vendita
     */
    public ScontoVenditaStrategyComposite getStrategiaScontoVendita()
    {
        return new ScontoVenditaMigliorePerClienteStrategyComposite();
    }
    /**
     * @param t 
     * @return La strategia di sconto applicata sul prodotto
     */
    public IScontoProdottoStrategy getStrategiaScontoProdotto(ITransazione t)
    {
        return new ScontoProdottoMigliorePerClienteStrategyComposite(t);
    }
    /** 
     * @return La strategia di sconto applicata sulla prenotazione
     */
    public ScontoVenditaStrategyComposite getStrategiaScontoPrenotazione() {
        return new ScontoVenditaMigliorePerClienteStrategyComposite();
    }
}
