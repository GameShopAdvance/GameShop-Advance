/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.controller;

import gameshop.advance.exceptions.QuantityException;
import gameshop.advance.exceptions.db.ObjectAlreadyExistsDbException;
import gameshop.advance.exceptions.products.ProdottoNotFoundException;
import gameshop.advance.interfaces.IDescrizioneProdotto;
import gameshop.advance.interfaces.remote.IDescrizioneProdottoRemote;
import gameshop.advance.interfaces.remote.factory.IInventarioControllerRemote;
import gameshop.advance.interfaces.remote.utility.IRemoteObserver;
import gameshop.advance.model.CatalogoProdottiSingleton;
import gameshop.advance.remote.DescrizioneRemoteProxy;
import gameshop.advance.technicalservices.db.DbDescrizioneProdottoSingleton;
import gameshop.advance.utility.IDProdotto;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Controller relativo alla operazioni di sistema necessarie a compiere l'inventario del negozio
 *
 * @author Salx
 */
public class GestisciInventarioController extends UnicastRemoteObject implements IInventarioControllerRemote {
    
    private LinkedList<IDescrizioneProdotto> descrizioni;
    private LinkedList<IRemoteObserver> observers;
    
    /**
     * @throws java.rmi.RemoteException   
    */
    public GestisciInventarioController () throws RemoteException {
        this.descrizioni = new LinkedList<>();
        this.observers = new LinkedList<>();
    }
    /**
     * @throws java.rmi.RemoteException
    */
    @Override
    public void avviaInventario()  throws RemoteException{
    }
    /**
     * Inserisce un nuovo prodotto nel Catalogo
     * @param codiceProdotto 
     * @param quantity 
     * @throws java.rmi.RemoteException
     * @throws gameshop.advance.exceptions.QuantityException
     * @throws gameshop.advance.exceptions.products.ProdottoNotFoundException
    */
    @Override
    public void inserisciProdotto(IDProdotto codiceProdotto, int quantity) throws RemoteException, QuantityException, ProdottoNotFoundException{
       
        if (quantity < 1){
            throw new QuantityException(quantity);
        }
        IDescrizioneProdotto desc = CatalogoProdottiSingleton.getInstance().getDescrizioneProdotto(codiceProdotto);
        desc.addQuantitaDisponibile(quantity);
        descrizioni.add(desc);
        this.notificaListeners();

    }
    /**
     * @throws java.rmi.RemoteException
    */
    private void notificaListeners() throws RemoteException {
        for(IRemoteObserver o : this.observers){
            o.notifica(this);
        }
    }
    /**
     * @param obs 
     * @throws java.rmi.RemoteException
    */
    @Override
    public void aggiungiListener(IRemoteObserver obs)  throws RemoteException{
        this.observers.add(obs);
    }
    /**
     * @param obs 
     * @throws java.rmi.RemoteException
    */
    @Override
    public void rimuoviListener(IRemoteObserver obs)  throws RemoteException{
         if(obs == null)
             this.observers = null;
         else
            this.observers.remove(obs);
     }
    
    /**
     *
     * @return Interfaccia remota della Descrizione Prodotto
     * @throws RemoteException
     */
    @Override
    public IDescrizioneProdottoRemote getLastDescription() throws RemoteException{
        IDescrizioneProdotto desc = this.descrizioni.getLast();
        return new DescrizioneRemoteProxy(desc);
    }
    /**
     *
     * @throws RemoteException
     */
    @Override
    public void cancel() throws RemoteException{
        
    }
     /**
     * Chiude l'inventario e salva le modifiche
     * 
     * @throws RemoteException
     */
    @Override
    public void terminaInventario() throws RemoteException{
        for (IDescrizioneProdotto desc : this.descrizioni) {
            try {
                DbDescrizioneProdottoSingleton.getInstance().create(desc);
            } catch (ObjectAlreadyExistsDbException ex) {
                Logger.getLogger(GestisciInventarioController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.descrizioni = null;
    }

    
    
    
}
