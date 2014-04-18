/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces;

/** Consente di implementare agli observer  che lo estendono il metodo notifica che serve ad aggiornare gli oggetti interessati.
 * 
 *
 * @author Salx
 */
public interface IObserver {
    /**
     *
     * @param o
     */
    void notifica(Object o);
    
}
