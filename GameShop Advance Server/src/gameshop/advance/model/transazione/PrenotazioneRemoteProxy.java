/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.transazione;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.interfaces.remote.utility.IIteratorWrapperRemote;
import gameshop.advance.interfaces.remote.sales.reservations.IPrenotazioneRemote;
import gameshop.advance.interfaces.remote.sales.IRigaDiTransazioneRemote;
import gameshop.advance.utility.IteratorWrapper;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class PrenotazioneRemoteProxy extends UnicastRemoteObject implements IPrenotazioneRemote {

    private final IPrenotazioneRemote protectedRemoteObject;
    
    public PrenotazioneRemoteProxy(IPrenotazioneRemote pren) throws RemoteException
    {
        System.err.println("Aggiunta prenotazione a proxy remoto");
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
        System.err.println("Richieste righe di vendita a proxy remoto della prenotazione");
        IIteratorWrapperRemote<IRigaDiTransazioneRemote> righeDiVendita = this.protectedRemoteObject.getRigheDiVendita();
        System.err.println("Recuperato iteratore righe di vendita da oggetto protetto");
        LinkedList<IRigaDiTransazioneRemote> righeProtette = new LinkedList<>();
        System.err.println("Creata lista");
        while(righeDiVendita.hasNext())
        {
            righeProtette.add(new RigaDiTransazioneRemoteProxy(righeDiVendita.next()));
            System.err.println("Aggiunta riga protetta a lista");
        }
        System.err.println("Pronto ad invio dell'iteratore remoto");
        return new IteratorWrapper<>(righeProtette.iterator());
    }
    
}
