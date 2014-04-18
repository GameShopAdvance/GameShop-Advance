/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.manager;

import gameshop.advance.exceptions.QuantityException;
import gameshop.advance.interfaces.IDescrizioneProdotto;
import gameshop.advance.interfaces.IInformazioniProdotto;
import gameshop.advance.interfaces.remote.IDescrizioneProdottoRemote;
import gameshop.advance.interfaces.remote.forniture.IInformazioniProdottoRemote;
import gameshop.advance.remote.DescrizioneRemoteProxy;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Lorenzo Di Giuseppe
 */
public class InformazioniProdotto extends UnicastRemoteObject implements IInformazioniProdottoRemote, IInformazioniProdotto {
    
    private IDescrizioneProdottoRemote descrizione;
    
    private int prenotati = 0;
    
    private int ordinati = 0;
    
    public InformazioniProdotto(IDescrizioneProdotto desc) throws RemoteException
    {
        this(desc, 0, 0);
    }
    
    public InformazioniProdotto(IDescrizioneProdotto desc, int prenotati) throws RemoteException
    {
        this(desc, prenotati, 0);
    }
    
    public InformazioniProdotto(IDescrizioneProdotto desc, int prenotati, int ordinati) throws RemoteException
    {
        this.descrizione = desc;
        this.prenotati = prenotati;
        this.ordinati = ordinati;
    }

    @Override
    public IDescrizioneProdottoRemote getDescrizione() throws RemoteException {
        return new DescrizioneRemoteProxy(this.descrizione);
    }

    public void setDescrizione(IDescrizioneProdotto descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public int getPrenotati() {
        return prenotati;
    }

    public void setPrenotati(int prenotati) throws QuantityException {
        if(prenotati < 0)
            throw new QuantityException(prenotati);
        this.prenotati = prenotati;
    }

    @Override
    public int getOrdinati() {
        return ordinati;
    }

    public void setOrdinati(int ordinati) throws QuantityException {
        if(ordinati < 0)
            throw new QuantityException(ordinati);
        this.ordinati = ordinati;
    }
    
    
}
