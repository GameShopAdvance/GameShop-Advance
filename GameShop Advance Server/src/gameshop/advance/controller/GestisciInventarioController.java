/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.controller;

import gameshop.advance.exceptions.ObjectAlreadyExistsDbException;
import gameshop.advance.exceptions.ProdottoNotFoundException;
import gameshop.advance.exceptions.QuantityException;
import gameshop.advance.interfaces.remote.IInventarioControllerRemote;
import gameshop.advance.interfaces.remote.IRemoteObserver;
import gameshop.advance.model.CatalogoProdottiSingleton;
import gameshop.advance.model.DescrizioneProdotto;
import gameshop.advance.technicalservices.db.DbDescrizioneProdottoSingleton;
import gameshop.advance.utility.IDProdotto;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Salx
 */
public class GestisciInventarioController extends UnicastRemoteObject implements IInventarioControllerRemote {
    
    private LinkedList<DescrizioneProdotto> descrizioni;
    private LinkedList<IRemoteObserver> observers = new LinkedList<>();
    
    
    public GestisciInventarioController () throws RemoteException {
        this.descrizioni = new LinkedList<>();
        System.err.println("Gest. Inv. Contr.");
    }
    
    @Override
    public void avviaInventario()  throws RemoteException{
        
    }
  
    @Override
    public void inserisciProdotto(IDProdotto codiceProdotto, int quantity) throws RemoteException, QuantityException, ProdottoNotFoundException{
       
        if (quantity < 1){
            throw new QuantityException(quantity);
        }
       
        DescrizioneProdotto desc = CatalogoProdottiSingleton.getInstance().getDescrizioneProdotto(codiceProdotto);
        System.err.println("Desc: "+desc);
        desc.addQuantitaDisponibile(quantity);
        System.err.println("Desc after update: "+desc);
        descrizioni.add(desc);
        this.notificaListeners();

    }
    
    private void notificaListeners() throws RemoteException {
        
        for(IRemoteObserver o : this.observers){
            o.notifica(this);
        }
    }
    
    public void aggiungiListener(IRemoteObserver obs)  throws RemoteException{
        this.observers.add(obs);
    }
    
    public void rimuoviListener(IRemoteObserver obs)  throws RemoteException{
         if(obs == null)
             this.observers = null;
         else
            this.observers.remove(obs);
     }
    
    /**
     *
     * @return
     * @throws RemoteException
     */
    @Override
    public DescrizioneProdotto getLastDescription() throws RemoteException{
        
        return this.descrizioni.getLast();
    }
    
    @Override
    public void cancel() throws RemoteException{
        
    }
    
    @Override
    public void terminaInventario() throws RemoteException{
        for (DescrizioneProdotto desc : this.descrizioni) {
            try {
                DbDescrizioneProdottoSingleton.getInstance().create(desc);
                System.out.println("Descrizioni aggiornate");
            } catch (ObjectAlreadyExistsDbException ex) {
                Logger.getLogger(GestisciInventarioController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.descrizioni = null;
    }

    
    
    
}
