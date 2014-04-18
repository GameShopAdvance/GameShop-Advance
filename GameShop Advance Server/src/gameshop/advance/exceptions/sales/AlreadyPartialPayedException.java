/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.exceptions.sales;

/**
 *
 * @author Matteo Gentile
 */
public class AlreadyPartialPayedException extends Exception{
    

    public AlreadyPartialPayedException() {
    }
    
    @Override
     public String getMessage() {
        return "Attenzione l'acconto di questa prenotazione è già stato pagato!";
    }
}
