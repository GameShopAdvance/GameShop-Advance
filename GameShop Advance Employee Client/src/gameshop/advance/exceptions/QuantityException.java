/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.exceptions;

/**
 * Classe che gestisce le eccezioni riguardanti la quantità dei prodotti acquistati.
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class QuantityException extends Exception {
    private int quantity;
        
    public QuantityException(String message) {
        super(message);
    }
    
    public QuantityException(int quantity) {
        this.quantity = quantity;
    }
    
    public int getQuantity(){
    return quantity;}
    
}
