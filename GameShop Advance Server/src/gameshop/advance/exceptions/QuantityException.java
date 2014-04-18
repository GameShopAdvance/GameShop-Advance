/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.exceptions;

/**
 * Classe che gestisce le eccezioni riguardanti le quantità di prodotto che si stanno acquistando.
 * 
 * @author Matteo Gentile
 */
public class QuantityException extends Exception {
    private int quantity;
    /**
    * 
    * @param message
    */
    public QuantityException(String message) {
        super(message);
    }
  /**
    * 
    * @param quantity
    */
    public QuantityException(int quantity) {
        this.quantity = quantity;
    }
  /**
    * 
    * @return La quantità inserita che ha generato l'errore
    */
    public int getQuantity(){
    return quantity;}
    
}
