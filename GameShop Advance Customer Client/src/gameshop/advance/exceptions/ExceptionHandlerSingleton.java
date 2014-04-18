/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.exceptions;

import gameshop.advance.exceptions.db.ObjectAlreadyExistsDbException;
import gameshop.advance.exceptions.db.ObjectNotExistsDbException;
import gameshop.advance.exceptions.products.ProdottoNotFoundException;
import gameshop.advance.exceptions.products.QuantityNotInStockException;
import gameshop.advance.exceptions.sales.AlredyPayedException;
import gameshop.advance.exceptions.sales.InvalidSaleState;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class ExceptionHandlerSingleton {
    
    private static ExceptionHandlerSingleton instance;
    
    private ExceptionHandlerSingleton(){}
    
    public static ExceptionHandlerSingleton getInstance(){
        if(instance == null)
            instance = new ExceptionHandlerSingleton();
        return instance;
    }
    
    public String getMessage(AlredyPayedException ex){
        return null;
    }
    
    public String getMessage(RemoteException ex){
        return "Non è possibile contattare il server. "
                + "Si prega di riprovare. Se il problema persiste, contattare l'amministratore di sistema.";
    }
    
    public String getMessage(NullPointerException ex){
        return "Ci sono problemi di comunicazione, si prega di controllare la configurazione del sistema.";
    }
    
    public String getMessage(NotBoundException ex){
        return null;
    }
    
    public String getMessage(InvalidMoneyException ex){
        return null;
    }
    
    public String getMessage(InvalidOperationException ex){
        return null;
    }
    
    public String getMessage(QuantityException ex){
        return null;
    }
    
    public String getMessage(ConfigurationException ex){
        return "Ci sono problemi nella lettura del file di configurazione: "+ex.getConfigurationPath()+"."
                    + " Per maggiori informazioni rivolgersi all'amministratore di sistema.";
    }
    
    public String getMessage(ProdottoNotFoundException ex){
        return null;
    }
    
    public String getMessage(QuantityNotInStockException ex){
        return null;
    }
    
    public String getMessage(InvalidSaleState ex){
        return null;
    }
    
    public String getMessage(ObjectAlreadyExistsDbException ex){
        return null;
    }
    
    public String getMessage(ObjectNotExistsDbException ex){
        return null;
    }
}
