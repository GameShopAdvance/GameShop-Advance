/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.transazione;

import gameshop.advance.exceptions.InvalidMoneyException;
import gameshop.advance.interfaces.ITransazione;
import gameshop.advance.interfaces.remote.sales.IRigaDiTransazioneRemote;
import gameshop.advance.interfaces.remote.sales.ITransazioneRemote;
import gameshop.advance.interfaces.remote.utility.IIteratorWrapperRemote;
import gameshop.advance.utility.IteratorWrapper;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

/** Proxy di protezione della transazione
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class TransazioneRemoteProxy extends UnicastRemoteObject implements ITransazioneRemote {
    
    private final ITransazione protectedRemoteTransaction;
    
    public TransazioneRemoteProxy(ITransazione trans) throws RemoteException
    {
        this.protectedRemoteTransaction = trans;
    }

    @Override
    public Money getResto() throws InvalidMoneyException, RemoteException {
        return this.protectedRemoteTransaction.getResto();
    }

    @Override
    public Money getTotal() throws RemoteException {
        return this.protectedRemoteTransaction.getTotal();
    }

    @Override
    public Integer getId() throws RemoteException {
        return this.protectedRemoteTransaction.getId();
    }
    /**
     *
     * @return Le righe di vendita wrappate
     * @throws java.rmi.RemoteException
     */
    @Override
    public IIteratorWrapperRemote<IRigaDiTransazioneRemote> getRigheDiVendita() throws RemoteException {
        IIteratorWrapperRemote<IRigaDiTransazioneRemote> righeDiVendita = this.protectedRemoteTransaction.getRigheDiVendita();
        LinkedList<IRigaDiTransazioneRemote> righeProtette = new LinkedList<>();
        while(righeDiVendita.hasNext())
        {
            righeProtette.add(new RigaDiTransazioneRemoteProxy(righeDiVendita.next()));
        }
        
        return new IteratorWrapper<>(righeProtette.iterator());
    }
    
}
