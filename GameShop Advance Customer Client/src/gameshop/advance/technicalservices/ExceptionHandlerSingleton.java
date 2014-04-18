/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.technicalservices;

import gameshop.advance.exceptions.ConfigurationException;
import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.exceptions.InvalidOperationException;
import gameshop.advance.exceptions.QuantityException;
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
        return "Ti siamo grati, ma è già stato trovato un pagamento. "
                + "GSA non ama rubare soldi ai suoi clienti.";
    }
    
    public String getMessage(RemoteException ex){
        return "Non è possibile contattare il server. "
                + "Si prega di riprovare. Se il problema persiste, contattare l'amministratore di sistema.";
    }
    
    public String getMessage(NullPointerException ex){
        return "Ci sono problemi di comunicazione, si prega di controllare la configurazione del sistema.";
    }
    
    public String getMessage(NotBoundException ex){
        return "Ci sono problemi di comunicazione con il server. "
                + "Potrebbe non essere attivo. Si prega di contattare l'amministratore di sistema.";
    }
    
    public String getMessage(InvalidMoneyException ex){
        return ex.getMessage();
    }
    
    public String getMessage(InvalidOperationException ex){
        return "L'operazione che si tentava di eseguire non è consentita.";
    }
    
    public String getMessage(QuantityException ex){
        return ex.getMessage();
    }
    
    public String getMessage(ConfigurationException ex){
        return "Ci sono problemi nella lettura del file di configurazione: "+ex.getConfigurationPath()+"."
                    + " Per maggiori informazioni rivolgersi all'amministratore di sistema.";
    }
    
    public String getMessage(ProdottoNotFoundException ex){
        return "MESSAGGIO VUOTO "+ex.getClass().toString();
    }
    
    public String getMessage(QuantityNotInStockException ex){
        return "MESSAGGIO VUOTO "+ex.getClass().toString();
    }
    
    public String getMessage(InvalidSaleState ex){
        return "MESSAGGIO VUOTO "+ex.getClass().toString();
    }
    
    public String getMessage(ObjectAlreadyExistsDbException ex){
        return "MESSAGGIO VUOTO "+ex.getClass().toString();
    }
    
    public String getMessage(ObjectNotExistsDbException ex){
        return "MESSAGGIO VUOTO "+ex.getClass().toString();
    }
}
