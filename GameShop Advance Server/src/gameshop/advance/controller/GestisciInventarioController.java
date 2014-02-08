/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.controller;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.exceptions.ProdottoNotFoundException;
import gameshop.advance.exceptions.QuantityException;
import gameshop.advance.interfaces.remote.IInventarioControllerRemote;
import gameshop.advance.interfaces.remote.IRemoteObserver;
import gameshop.advance.model.CatalogoProdottiSingleton;
import gameshop.advance.model.DescrizioneProdotto;
import gameshop.advance.utility.IDProdotto;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;


/**
 *
 * @author Salx
 */
public class GestisciInventarioController extends UnicastRemoteObject implements IInventarioControllerRemote {
    
    CatalogoProdottiSingleton catalogo;
    private LinkedList<DescrizioneProdotto> descrizioni;
    private LinkedList<IRemoteObserver> observers = new LinkedList<>();
    
    
    public GestisciInventarioController () throws RemoteException, InvalidMoneyException{
        this.catalogo = CatalogoProdottiSingleton.getInstance();
        this.descrizioni = new LinkedList();
    }
    
    
    @Override
    public void inserisciProdotto(IDProdotto codiceProdotto, int quantity) throws RemoteException, QuantityException, ProdottoNotFoundException{
       
        if (quantity < 1){
            throw new QuantityException(quantity);
        }
       
        DescrizioneProdotto desc = this.catalogo.getDescrizioneProdotto(codiceProdotto);
        if(desc == null){
            throw new ProdottoNotFoundException(codiceProdotto);
        }
        desc.addQuantitaDisponibile (quantity);
        descrizioni.add(desc);
        this.notificaListeners();

    }
    
    private void notificaListeners() throws RemoteException {
        
        for(IRemoteObserver o : this.observers){
            o.notifica(this);
        }
    }
    
    public void aggiungiListener(IRemoteObserver obs){
        this.observers.add(obs);
    }
    
    public void rimuoviListener(IRemoteObserver obs){
         if(obs == null)
             this.observers = null;
         else
            this.observers.remove(obs);
     }
    
    public DescrizioneProdotto getLastDescription(){
        
        return this.descrizioni.getLast();
    }
    
    
    
}
