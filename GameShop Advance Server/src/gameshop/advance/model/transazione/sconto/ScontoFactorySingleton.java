package gameshop.advance.model.transazione.sconto;

import gameshop.advance.interfaces.IScontoProdottoStrategy;
import gameshop.advance.model.transazione.sconto.prodotti.ScontoProdottoMigliorePerClienteStrategyComposite;
import gameshop.advance.model.transazione.sconto.vendita.ScontoVenditaMigliorePerClienteStrategyComposite;
import gameshop.advance.model.transazione.sconto.vendita.ScontoVenditaStrategyComposite;

/**
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
    
    public ScontoVenditaStrategyComposite getStrategiaScontoVendita()
    {
        return new ScontoVenditaMigliorePerClienteStrategyComposite();
    }
    
    public IScontoProdottoStrategy getStrategiaScontoProdotto()
    {
        //String className = System.getProperty( "strategiadiscontoprodotti.class.name" );
        //IScontoProdottoStrategy strategy = (IScontoProdottoStrategy) Class.forName( className ).newInstance();
        //return strategy;
        return new ScontoProdottoMigliorePerClienteStrategyComposite();
    }
}
