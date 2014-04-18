/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.remote;

import gameshop.advance.interfaces.remote.IDescrizioneProdottoRemote;
import gameshop.advance.interfaces.remote.utility.IRemoteImage;
import gameshop.advance.utility.IDProdotto;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import org.joda.time.DateTime;

/** Proxy delle descrizioni prodotto
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class DescrizioneRemoteProxy extends UnicastRemoteObject implements IDescrizioneProdottoRemote {
    
    private IDescrizioneProdottoRemote desc;
    
    public DescrizioneRemoteProxy(IDescrizioneProdottoRemote desc) throws RemoteException
    {
        this.desc = desc;
    }

    @Override
    public IDProdotto getCodiceProdotto() throws RemoteException {
        return this.desc.getCodiceProdotto();
    }

    @Override
    public String getDescrizione() throws RemoteException {
        return this.desc.getDescrizione();
    }

    @Override
    public Money getPrezzo(DateTime period) throws RemoteException {
        return this.desc.getPrezzo(period);
    }

    @Override
    public int getQuantitaDisponibile() throws RemoteException {
        return this.desc.getQuantitaDisponibile();
    }

    @Override
    public int getQuantitaDiSoglia() throws RemoteException {
        return this.desc.getQuantitaDiSoglia();
    }

    /**
     *
     * @return l'immagine associata alla descrizione
     * @throws RemoteException
     */
    @Override
    public IRemoteImage getImmagine() throws RemoteException {
        return this.desc.getImmagine();
    }

    @Override
    public String getNomeProdotto() throws RemoteException {
        return this.desc.getNomeProdotto();
    }
}
