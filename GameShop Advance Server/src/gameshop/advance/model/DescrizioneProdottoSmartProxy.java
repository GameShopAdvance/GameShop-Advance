/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model;

import com.db4o.activation.ActivationPurpose;
import com.db4o.activation.Activator;
import gameshop.advance.interfaces.IDescrizioneProdotto;
import gameshop.advance.interfaces.IScontoProdottoStrategy;
import gameshop.advance.utility.IDProdotto;
import gameshop.advance.utility.Money;
import gameshop.advance.utility.Prezzo;
import java.rmi.RemoteException;
import java.util.List;
import org.joda.time.DateTime;

/**
 *
 * @author Salx
 */
public class DescrizioneProdottoSmartProxy implements IDescrizioneProdotto {

    private final IDescrizioneProdotto descrizione;
    private transient Activator activator;
    
    public DescrizioneProdottoSmartProxy(IDescrizioneProdotto desc)
    {
        this.descrizione = desc;
    }
    
    @Override
    public IDProdotto getCodiceProdotto() throws RemoteException {
        this.activate(ActivationPurpose.READ);
        return this.descrizione.getCodiceProdotto();
    }

    @Override
    public String getDescrizione() throws RemoteException {
        this.activate(ActivationPurpose.READ);
        return this.descrizione.getDescrizione();
    }

    @Override
    public Money getPrezzo(DateTime period) throws RemoteException {
        this.activate(ActivationPurpose.READ);
        return this.descrizione.getPrezzo(period);
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

    @Override
    public void addQuantitaDisponibile(int quantity) {
        this.descrizione.addQuantitaDisponibile(quantity);
    }

    @Override
    public void addSconti(List<IScontoProdottoStrategy> sconti) {
        this.descrizione.addSconti(sconti);
    }

    @Override
    public void addSconto(IScontoProdottoStrategy sconto) {
        this.descrizione.addSconto(sconto);
    }

    @Override
    public int getQuantitaDisponibile() {
        return this.descrizione.getQuantitaDisponibile();
    }
    
    @Override
    public void setQuantitaDiSoglia(int soglia){
        this.descrizione.setQuantitaDiSoglia(soglia);
    }
    
    @Override
    public int getQuantitaDiSoglia(){
        return this.descrizione.getQuantitaDiSoglia();
    }
    
    @Override
    public boolean sottoSoglia(){
        return this.descrizione.sottoSoglia();
    }

    @Override
    public List<IScontoProdottoStrategy> getSconti(DateTime period) {
        this.activate(ActivationPurpose.READ);
        return this.descrizione.getSconti(period);
    }

    @Override
    public List<Prezzo> getTuttiPrezzi() {
        this.activate(ActivationPurpose.READ);
        return this.descrizione.getTuttiPrezzi();
    }

    @Override
    public List<IScontoProdottoStrategy> getTuttiSconti() {
        this.activate(ActivationPurpose.READ);
        return this.descrizione.getTuttiSconti();
    }

    @Override
    public void setDescrizione(String descrizione) {
        this.descrizione.setDescrizione(descrizione);
    }

    @Override
    public void setQuantitaDisponibile(int quantity) {
//        this.activate(ActivationPurpose.WRITE);
        this.descrizione.setQuantitaDisponibile(quantity);
    }
}
