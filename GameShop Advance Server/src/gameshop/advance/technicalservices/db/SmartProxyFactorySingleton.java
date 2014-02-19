/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.technicalservices.db;

import gameshop.advance.interfaces.IDescrizioneProdotto;
import gameshop.advance.interfaces.IPrenotazione;
import gameshop.advance.interfaces.ITransazione;
import gameshop.advance.model.DescrizioneProdottoSmartProxy;
import gameshop.advance.model.transazione.proxies.PrenotazioneSmartProxy;
import gameshop.advance.model.transazione.proxies.TransazioneSmartProxy;

/**
 *
 * @author matteog
 */
public class SmartProxyFactorySingleton {
    
    private static SmartProxyFactorySingleton factory;
    
     
    public SmartProxyFactorySingleton() {
    }

    
    public static synchronized SmartProxyFactorySingleton getIstance() {
        if(SmartProxyFactorySingleton.factory == null)
        {
            SmartProxyFactorySingleton.factory = new SmartProxyFactorySingleton();
        }
    
        return SmartProxyFactorySingleton.factory;
    }

    
    public IPrenotazione creaProxyPrenotazione(IPrenotazione res) {
   
            return new PrenotazioneSmartProxy(res);
      
    }

    public IDescrizioneProdotto creaProxyDescrizioneProdotto(IDescrizioneProdotto desc){
         return new DescrizioneProdottoSmartProxy(desc);
    }
        
    public ITransazione creaProxyVendita(ITransazione v){
        return new TransazioneSmartProxy(v);
    }
    
  
}
