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
import gameshop.advance.exceptions.db.ReservationNotFoundDbException;
import gameshop.advance.exceptions.db.SaleNotFoundDbException;
import gameshop.advance.exceptions.products.ProdottoNotFoundException;
import gameshop.advance.exceptions.products.QuantityNotInStockException;
import gameshop.advance.exceptions.sales.AlreadyPartialPayedException;
import gameshop.advance.exceptions.sales.AlreadyPayedException;
import gameshop.advance.exceptions.sales.ClientNotFoundException;
import gameshop.advance.exceptions.sales.InvalidSaleState;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Classe che gestisce le varie eccezioni generate dal sistema.In base al tipo di eccezione verrà mostrato 
 * un messaggio differente.
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class ExceptionHandlerSingleton {
    
    private static ExceptionHandlerSingleton instance;
    
    private ExceptionHandlerSingleton(){}
    
    /**
     * Metodo che ritorna l'istanza della classe Singleton.
     * @return
     */
    public static ExceptionHandlerSingleton getInstance(){
        if(instance == null)
            instance = new ExceptionHandlerSingleton();
        return instance;
    }
    
    /**
     * @param ex
     * @return
     */
    public String getMessage(AlreadyPayedException ex){
        return "Ti siamo grati, ma è già stato trovato un pagamento. "
                + "GSA non ama rubare soldi ai suoi clienti.";
    }
    
    /**
     * @param ex
     * @return
     */
    public String getMessage(RemoteException ex){
        return "Non è possibile contattare il server. "
                + "Si prega di riprovare. Se il problema persiste, contattare l'amministratore di sistema.";
    }
    
    /**
     * @param ex
     * @return
     */
    public String getMessage(NullPointerException ex){
        return "Ci sono problemi di comunicazione, si prega di controllare la configurazione del sistema.";
    }
    
    /**
     * @param ex
     * @return
     */
    public String getMessage(NotBoundException ex){
        return "Ci sono problemi di comunicazione con il server. "
                + "Potrebbe non essere attivo. Si prega di contattare l'amministratore di sistema.";
    }
    
    /**
     * @param ex
     * @return
     */
    public String getMessage(InvalidMoneyException ex){
        return ex.getMessage();
    }
    
    /**
     * @param ex
     * @return
     */
    public String getMessage(InvalidOperationException ex){
        return "L'operazione che si tentava di eseguire non è consentita.";
    }
    
    /**
     * @param ex
     * @return
     */
    public String getMessage(QuantityException ex){
        return ex.getMessage();
    }
    
    /**
     * @param ex
     * @return
     */
    public String getMessage(ConfigurationException ex){
        return "Ci sono problemi nella lettura del file di configurazione: "+ex.getConfigurationPath()+"."
                    + " Per maggiori informazioni rivolgersi all'amministratore di sistema.";
    }
    
    /**
     * @param ex
     * @return
     */
    public String getMessage(ProdottoNotFoundException ex){
        return ex.getMessage();
    }
    
    /**
     * @param ex
     * @return
     */
    public String getMessage(QuantityNotInStockException ex){
        return ex.getMessage();
    }
    
    /**
     * @param ex
     * @return
     */
    public String getMessage(InvalidSaleState ex){
        return "Sembra che la vendita/prenotazione non sia stata conclusa correttamente.";
    }
    
    /**
     * @param ex
     * @return
     */
    public String getMessage(ObjectAlreadyExistsDbException ex){
        return "Si è verificato un errore interno. "
                + "Si prega di contattare l'amministratore del sistema.";
    }
    
    /**
     * @param ex
     * @return
     */
    public String getMessage(ObjectNotExistsDbException ex){
        return "L'oggetto richiesto non esiste.";
    }
    
    /**
     *
     * @param ex
     * @return
     */
    public String getMessage(SaleNotFoundDbException ex){
        return "La vendita che si stava cercando non esiste. "
                + "Controllare l'identificativo inserito. ";
    }
    
    public String getMessage(ReservationNotFoundDbException ex){
        return "La prenotazione cercata non esiste. Controllare il numero di prenotazione inserito.";
    }
    
    public String getMessage(AlreadyPartialPayedException ex){
        return "Ci spiace, ma è già stato versato un acconto. "
                + "Si prega di saldare il totale per completare la prenotazione.";
    }
    
    public String getMessage(NumberFormatException ex){
        return "Si prega di inserire un numero valido.";
    }
    
    public String getMessage(ClientNotFoundException ex){
        return "Il numero di carta cliente "+ex.getCodiceTessera()+" non sembra essere valido. "
               + "Si prega di provare una carta differente o di contattare l'amministratore di sistema.";
    }
    
    public String getMessage(Exception ex){
        return ex.getMessage();
    }
}
