/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.transazione;

import gameshop.advance.interfaces.remote.IDescrizioneProdottoRemote;
import gameshop.advance.interfaces.remote.sales.IRigaDiTransazioneRemote;
import gameshop.advance.remote.DescrizioneRemoteProxy;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Lorenzo Di Giuseppe
 */
class RigaDiTransazioneRemoteProxy extends UnicastRemoteObject implements IRigaDiTransazioneRemote {

    private IRigaDiTransazioneRemote protectedRDT;
    
    public RigaDiTransazioneRemoteProxy(IRigaDiTransazioneRemote rdt) throws RemoteException {
        this.protectedRDT = rdt;
    }

    @Override
    public IDescrizioneProdottoRemote getDescrizione() throws RemoteException {
        return (IDescrizioneProdottoRemote) new DescrizioneRemoteProxy(this.protectedRDT.getDescrizione());
    }

    @Override
    public int getQuantity() throws RemoteException {
        return this.protectedRDT.getQuantity();
    }

    @Override
    public Money getSubTotal() throws RemoteException {
        return this.protectedRDT.getSubTotal();
    }
    
}
