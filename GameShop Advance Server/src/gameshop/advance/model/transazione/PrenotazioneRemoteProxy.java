/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.transazione;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.interfaces.remote.sales.IRigaDiTransazioneRemote;
import gameshop.advance.interfaces.remote.sales.reservations.IPrenotazioneRemote;
import gameshop.advance.interfaces.remote.utility.IIteratorWrapperRemote;
import gameshop.advance.utility.IteratorWrapper;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

/** Proxy di protezione della prenotazione
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class PrenotazioneRemoteProxy extends UnicastRemoteObject implements IPrenotazioneRemote {

    private final IPrenotazioneRemote protectedRemoteObject;
    
    public PrenotazioneRemoteProxy(IPrenotazioneRemote pren) throws RemoteException
    {
        this.protectedRemoteObject = pren;
    }
    
    @Override
    public Money getAcconto() throws RemoteException {
        return this.protectedRemoteObject.getAcconto();
    }

    @Override
    public Money getResto() throws InvalidMoneyException, RemoteException {
        return this.protectedRemoteObject.getResto();
    }

    @Override
    public Money getTotal() throws RemoteException {
        return this.protectedRemoteObject.getTotal();
    }

    @Override
    public Integer getId() throws RemoteException {
        return this.protectedRemoteObject.getId();
    }
    
    @Override
    public Money getRestoAcconto() throws RemoteException{
        return this.protectedRemoteObject.getRestoAcconto();
    }

    @Override
    public IIteratorWrapperRemote<IRigaDiTransazioneRemote> getRigheDiVendita() throws RemoteException {
        IIteratorWrapperRemote<IRigaDiTransazioneRemote> righeDiVendita = this.protectedRemoteObject.getRigheDiVendita();
        LinkedList<IRigaDiTransazioneRemote> righeProtette = new LinkedList<>();
            while(righeDiVendita.hasNext())
            {
                righeProtette.add(new RigaDiTransazioneRemoteProxy(righeDiVendita.next()));
            }
        return new IteratorWrapper<>(righeProtette.iterator());
    }
    
}
