/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces;

import com.db4o.activation.ActivationPurpose;
import com.db4o.activation.Activator;
import gameshop.advance.interfaces.remote.IDescrizioneProdottoRemote;
import gameshop.advance.utility.IDProdotto;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;
import org.joda.time.DateTime;

/**
 *
 * @author Salx
 */
public class DescrizioneProdottoSmartProxy implements IDescrizioneProdottoRemote{

    private final IDescrizioneProdottoRemote protectedRemoteObject;
    private transient Activator activator;
    
    public DescrizioneProdottoSmartProxy(IDescrizioneProdottoRemote desc) throws RemoteException
    {
        this.protectedRemoteObject = desc;
    }
    
    @Override
    public IDProdotto getCodiceProdotto() throws RemoteException {
        activate(ActivationPurpose.READ);
        return this.protectedRemoteObject.getCodiceProdotto();
    }

    @Override
    public String getDescrizione() throws RemoteException {
        activate(ActivationPurpose.READ);
        return this.protectedRemoteObject.getDescrizione();
    }

    @Override
    public Money getPrezzo(DateTime period) throws RemoteException {
        activate(ActivationPurpose.READ);
        return this.protectedRemoteObject.getPrezzo(period);
    }
    
    public void activate(ActivationPurpose purpose) {
        if(activator != null) {
            activator.activate(purpose);
        }
    }
    
    public void bind(Activator act) {
        if (activator == act) {
            return;
        }
        if (act != null && activator != null) {
            throw new IllegalStateException();
        }
        activator = act;
    }
}
