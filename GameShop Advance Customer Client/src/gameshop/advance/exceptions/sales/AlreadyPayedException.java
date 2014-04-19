/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.exceptions.sales;

/**
 * Classe che gestisce le eccezioni lanciate quando si tenta di pagare un prodotto prenotato più di una volta 

 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class AlreadyPayedException extends Exception {

    public AlreadyPayedException() {
    }
    
    @Override
     public String getMessage() {
        return "Attenzione la prenotazione è già stata competata e pagata!";
    }

}
