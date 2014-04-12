/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces;

import gameshop.advance.exceptions.QuantityException;

/**
 *
 * @author Lorenzo Di Giuseppe
 */
public interface IInformazioniProdotto {
    void setDescrizione(IDescrizioneProdotto descrizione);

    void setPrenotati(int prenotati) throws QuantityException;

    void setOrdinati(int ordinati) throws QuantityException;
}
