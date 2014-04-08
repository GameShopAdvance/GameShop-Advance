/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.observers;

import gameshop.advance.interfaces.IObserver;
import gameshop.advance.manager.ManagerFornitureSingleton;
import gameshop.advance.manager.ManagerProdottiSingleton;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class ManagerProdottiObserver implements IObserver {

    @Override
    public void notifica(Object o) {
        ManagerFornitureSingleton.getInstance().aggiornaDescrizioni(ManagerProdottiSingleton.getInstance().getMonitored());
    }
    
}
