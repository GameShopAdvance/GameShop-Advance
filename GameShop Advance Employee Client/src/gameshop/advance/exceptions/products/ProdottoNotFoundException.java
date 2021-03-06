/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.exceptions.products;

import gameshop.advance.utility.IDProdotto;


/**
 * Classe che gestisce le eccezioni riguardanti i prodotti non trovati quando viene inserito il codice del prodotto.
 * 
 * @author Matteo Gentile
 */
public class ProdottoNotFoundException extends Exception {
    
    private final IDProdotto codice;
  
   /**
    * 
    * @param codice
    */
    public ProdottoNotFoundException(IDProdotto codice)
    {
        this.codice = codice;
    }

   /**
    * @return  l'id del prodotto.
    */
    public IDProdotto getCodice()
    {
        return codice;
    }

    /**
    * @return  messaggio di errore che verrà visualizzato.
    */
    @Override
    public String getMessage() {
        return "Prodotto con codice: "+ this.codice +" non trovato o codice non valido";
    }
}
