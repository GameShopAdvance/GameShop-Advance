/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.transazione.decorator;

import gameshop.advance.interfaces.ITransazione;
import gameshop.advance.model.DescrizioneProdotto;
import gameshop.advance.model.transazione.sconto.ScontoFactorySingleton;
import gameshop.advance.model.transazione.sconto.vendita.ScontoVenditaStrategyComposite;
import java.rmi.RemoteException;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class VenditaTransazioneDecorator extends TransazioneDecorator{

    public VenditaTransazioneDecorator(ITransazione trans) {
        super(trans);
        ScontoVenditaStrategyComposite sconto = ScontoFactorySingleton.getInstance().getStrategiaScontoVendita();
        super.setSconto(sconto);
        
    }
    
    @Override
    public void inserisciProdotto(DescrizioneProdotto desc, int quantity) throws RemoteException{
        super.inserisciProdotto(desc, quantity);
        //TransazioneDecoratorRemoteProxy proxy = new TransazioneDecoratorRemoteProxy();
        super.notificaListeners();
        
    }    
}
