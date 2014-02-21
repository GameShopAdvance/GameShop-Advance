/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.exceptions;

import gameshop.advance.utility.IDProdotto;



/**
 * Classe che si occupa delle Exception riguardanti i prodotti non trovati nel db.
 * @author matteog
 */
public class ProdottoNotFoundException extends Exception {
    
    private IDProdotto codice;
  
    public ProdottoNotFoundException(IDProdotto codice)
    {
       this.codice = codice;
    }

    /**
     *
     * @return
     */
    public IDProdotto getCodice()
    {
      return codice;
    }
    
    @Override
    public String getMessage() {
        return "Prodotto con codice: "+ this.codice +" non trovato o codice non valido";
    }
}
