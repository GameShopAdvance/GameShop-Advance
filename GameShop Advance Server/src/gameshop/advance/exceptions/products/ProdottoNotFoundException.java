/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.exceptions.products;

import gameshop.advance.utility.IDProdotto;


/**
 * Classe che gestisce le estensioni riguardanti i prodotti non trovati
 * dopo l'imput del codice del prodotto.
 * @author matteog
 */
public class ProdottoNotFoundException extends Exception {
    
    private IDProdotto codice;
  
   
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
    * @return  messaggio da visualizzare.
    */
    @Override
    public String getMessage() {
        return "Prodotto con codice: "+ this.codice +" non trovato o codice non valido";
    }
}
