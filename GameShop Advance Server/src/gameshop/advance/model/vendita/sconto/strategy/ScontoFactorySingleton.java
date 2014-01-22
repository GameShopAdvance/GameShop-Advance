/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.vendita.sconto.strategy;

import gameshop.advance.model.vendita.sconto.strategy.prodotti.IScontoProdottoStrategy;
import gameshop.advance.model.vendita.sconto.strategy.prodotti.ScontoProdottoMigliorePerClienteStrategyComposite;

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
    
    public IScontoVenditaStrategy getStrategiaScontoVendita()
    {
        return new ScontoVenditaMigliorePerClienteStrategyComposite();
    }
    
    public IScontoProdottoStrategy getStrategiaScontoProdotto() throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        //String className = System.getProperty( "strategiadiscontoprodotti.class.name" );
        //IScontoProdottoStrategy strategy = (IScontoProdottoStrategy) Class.forName( className ).newInstance();
        //return strategy;
        return new ScontoProdottoMigliorePerClienteStrategyComposite();
    }
}
