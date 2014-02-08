/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces.remote;

import gameshop.advance.exceptions.ProdottoNotFoundException;
import gameshop.advance.exceptions.QuantityException;
import gameshop.advance.utility.IDProdotto;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Salx
 */
public interface IInventarioControllerRemote extends Remote{
    
    /**
     *
     * @param codiceProdotto
     * @param quantity
     * @throws RemoteException
     * @throws QuantityException
     * @throws ProdottoNotFoundException
     */
    public void inserisciProdotto(IDProdotto codiceProdotto, int quantity) throws RemoteException, QuantityException, ProdottoNotFoundException;
    
}
