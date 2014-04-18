/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.exceptions.products;

import gameshop.advance.interfaces.remote.IDescrizioneProdottoRemote;
import java.rmi.RemoteException;

/**
 * Classe che gestisce le eccezioni riguardanti i prodotti non presenti in inventario

 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class QuantityNotInStockException extends Exception{
    
    private final int disponibili;
    private final String nome;
    
    /**
     * @param desc
     * @throws java.rmi.RemoteException
    */
    public QuantityNotInStockException(IDescrizioneProdottoRemote desc) throws RemoteException
    {
        this.disponibili = desc.getQuantitaDisponibile();
        this.nome = desc.getNomeProdotto();
    }

    /**
    * @return  messaggio da visualizzare.
    */
    @Override
    public String getMessage() {
        return "Sono disponibili"+ this.disponibili +" unità di "+this.nome;
    }
}
