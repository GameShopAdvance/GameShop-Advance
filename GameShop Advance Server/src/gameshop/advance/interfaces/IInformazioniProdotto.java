/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces;

import gameshop.advance.exceptions.QuantityException;

/**
 * Interfaccia remota per l'esportazione delle classi che la implementano tramite
 * Java RMI, IInformazioniProdotto consente di modificare le informazioni relative a un prodotto del catalogo.
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public interface IInformazioniProdotto {
    /** Aggiunge descrizioni prodotto 
     *
     * @param descrizione
     */
    void setDescrizione(IDescrizioneProdotto descrizione);
    /** 
     *
     * @param prenotati
     * @throws gameshop.advance.exceptions.QuantityException
     */
    void setPrenotati(int prenotati) throws QuantityException;
    /** 
     *
     * @param ordinati
     * @throws gameshop.advance.exceptions.QuantityException
     */
    void setOrdinati(int ordinati) throws QuantityException;
}
