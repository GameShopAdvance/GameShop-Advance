/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.controller;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.exceptions.ProdottoNotFoundException;
import gameshop.advance.interfaces.remote.IDescrizioneProdottoRemote;
import gameshop.advance.interfaces.remote.IPrenotaProdottoRemote;
import gameshop.advance.interfaces.remote.IRemoteObserver;
import gameshop.advance.model.CatalogoProdottiSingleton;
import gameshop.advance.model.DescrizioneProdotto;
import gameshop.advance.model.NegozioSingleton;
import gameshop.advance.model.transazione.Transazione;
import gameshop.advance.model.transazione.decorator.PagataInAccontoTransazioneDecorator;
import gameshop.advance.model.transazione.decorator.PagataInTotaleTransazioneDecorator;
import gameshop.advance.model.transazione.decorator.PrenotazioneTransazioneDecorator;
import gameshop.advance.model.transazione.decorator.TransazioneDecorator;
import gameshop.advance.remote.DescrizioneRemoteProxy;
import gameshop.advance.technicalservices.db.DbDescrizioneProdottoSingleton;
import gameshop.advance.utility.IDProdotto;
import gameshop.advance.utility.IteratorWrapper;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class PrenotaProdottoController extends UnicastRemoteObject implements IPrenotaProdottoRemote {
    
    private TransazioneDecorator prenotazione;
    
    //OPERAZIONI DI SISTEMA LATO CLIENTE
    
    public PrenotaProdottoController() throws RemoteException
    {
        
    }
    
    /**
     *
     * @return
     * @throws RemoteException
     */
    @Override
    public IteratorWrapper<IDescrizioneProdottoRemote> getDescriptions() throws RemoteException
    {
        Iterator<Object> iter = DbDescrizioneProdottoSingleton.getInstance().read();
        LinkedList<IDescrizioneProdottoRemote> list = new LinkedList<>();
        while(iter.hasNext())
        {
            list.add(new DescrizioneRemoteProxy((DescrizioneProdotto) iter.next()));
        }
        return new IteratorWrapper<> (list.iterator());
    }
    
    @Override
    public void avviaPrenotazione() throws RemoteException
    {
        this.prenotazione = new PrenotazioneTransazioneDecorator(new Transazione());
    }
    
    @Override
    public void prenotaProdotto(IDProdotto codiceProdotto, int quantity) throws RemoteException, ProdottoNotFoundException
    {
        DescrizioneProdotto desc;
        desc = CatalogoProdottiSingleton.getInstance().getDescrizioneProdotto(codiceProdotto);
        this.prenotazione.inserisciProdotto(desc, quantity);
    }
    
    @Override
    public void terminaPrenotazione() throws RemoteException
    {
        NegozioSingleton.getInstance().registraTransazione(this.prenotazione);
           
    }
    
    //OPERAZIONI DI SISTEMA LATO COMMESSO
    
    @Override
    public void recuperaPrenotazione(Integer id) throws RemoteException
    {
        this.prenotazione = NegozioSingleton.getInstance().riprendiTransazione(id);
    }
    
    @Override
    public void pagaAcconto(Money amount) throws RemoteException, InvalidMoneyException
    {
        this.prenotazione = new PagataInAccontoTransazioneDecorator(this.prenotazione);
        this.prenotazione.gestisciPagamento(amount);
        this.prenotazione = (TransazioneDecorator) this.prenotazione.unwrap();
    }
    
    /**
     *
     * @param obs
     * @throws RemoteException
     */
    @Override
    public void addListener(IRemoteObserver obs) throws RemoteException
    {
        this.prenotazione.aggiungiListener(obs);
    }
    
    /**
     *
     * @param amount
     * @throws RemoteException
     * @throws InvalidMoneyException
     */
    @Override
    public void pagaTotale(Money amount) throws RemoteException, InvalidMoneyException
    {
        this.prenotazione = new PagataInTotaleTransazioneDecorator(this.prenotazione.unwrap());
        this.prenotazione.gestisciPagamento(amount);
        this.prenotazione = (TransazioneDecorator) this.prenotazione.unwrap();
    }
    
}
