/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces;

import gameshop.advance.model.transazione.RigaDiTransazione;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;
import org.joda.time.DateTime;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public interface IScontoProdottoStrategy {
    
    /**
     *
     * @param rdv
     * @return
     * @throws RemoteException
     */
    public Money getSubtotal(RigaDiTransazione rdv, ITransazione trans) throws RemoteException;
    
    /**
     *
     * @param period
     * @return
     */
    public boolean isValid(DateTime period);
    
    public void add(IScontoProdottoStrategy sp);
}
