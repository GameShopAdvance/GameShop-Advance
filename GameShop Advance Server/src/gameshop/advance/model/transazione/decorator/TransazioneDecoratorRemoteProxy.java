/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.transazione.decorator;

import gameshop.advance.interfaces.ITransazione;
import java.io.Serializable;
import java.rmi.RemoteException;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class TransazioneDecoratorRemoteProxy extends TransazioneDecorator implements Serializable {
    
    public TransazioneDecoratorRemoteProxy(ITransazione trans) throws RemoteException
    {
        super(trans);
    }
}
