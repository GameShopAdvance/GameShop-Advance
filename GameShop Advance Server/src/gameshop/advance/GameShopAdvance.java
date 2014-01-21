/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance;

import gameshop.advance.model.RemoteFactorySingleton;
import gameshop.advance.remote.interfaces.IRemoteFactory;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe che si occupa della creazione del registry RMI, con il quale comunicheranno
 * tutti i client.Su tale registry vengono implementate le interfaccie presenti all'interno
 * dei client.
 * @author loki
 */
public class GameShopAdvance {

    /**
     * Crea il registry RMI e imposta il valore della porta sulla quale deve rimanere
     * in ascolto.Instanzia poi la factory che avrà il compito di creare e instanziare
     * gli oggetti Cassa.In caso di errori li segnalerà in output.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            //bind to a specific ip
            // System.setProperty("java.rmi.server.hostname", "10.154.42.76");
            
            if(System.getSecurityManager() == null)
                System.setSecurityManager(new SecurityManager());
            
            LocateRegistry.createRegistry(1099);
            Registry reg = LocateRegistry.getRegistry();
            System.err.println("Server registry: "+LocateRegistry.getRegistry());
            IRemoteFactory factory = (IRemoteFactory) RemoteFactorySingleton.getIstance();
            Naming.rebind("RemoteFactory", factory);
        } catch (RemoteException | MalformedURLException ex) {
            Logger.getLogger(GameShopAdvance.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
}