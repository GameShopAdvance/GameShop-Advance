/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance;

import gameshop.advance.exceptions.ObjectAlreadyExistsDbException;
import gameshop.advance.interfaces.IDescrizioneProdotto;
import gameshop.advance.interfaces.IScontoProdottoStrategy;
import gameshop.advance.interfaces.remote.IRemoteFactory;
import gameshop.advance.manager.ManagerPrenotazioniSingleton;
import gameshop.advance.manager.ManagerProdottiSingleton;
import gameshop.advance.model.CatalogoProdottiSingleton;
import gameshop.advance.model.DescrizioneProdotto;
import gameshop.advance.model.transazione.CartaCliente;
import gameshop.advance.model.transazione.TipologiaCliente;
import gameshop.advance.model.transazione.sconto.prodotti.ScontoPrendiPaghiClienteProdottoStrategy;
import gameshop.advance.observers.ManagerPrenotazioniObserver;
import gameshop.advance.observers.ManagerProdottiObserver;
import gameshop.advance.remote.RemoteFactorySingleton;
import gameshop.advance.technicalservices.db.DbCartaClienteSingleton;
import gameshop.advance.technicalservices.db.DbDescrizioneProdottoSingleton;
import gameshop.advance.technicalservices.db.DbManagerSingleton;
import gameshop.advance.utility.IDProdotto;
import gameshop.advance.utility.IntervalloDiTempo;
import gameshop.advance.utility.Money;
import gameshop.advance.utility.Prezzo;
import java.io.File;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.DateTime;

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
            if(System.getSecurityManager() == null)
                System.setSecurityManager(new SecurityManager());
            File f = new File("./GSA.db");
            if(!f.exists() && !f.isDirectory())
                initializeDb();
//            startManagers();
            LocateRegistry.createRegistry(1099);
            Registry reg = LocateRegistry.getRegistry();
            System.err.println("Server registry: "+LocateRegistry.getRegistry());
            IRemoteFactory factory = (IRemoteFactory) RemoteFactorySingleton.getIstance();
            Naming.rebind("RemoteFactory", factory);
        } catch (RemoteException | MalformedURLException ex) {
            Logger.getLogger(GameShopAdvance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void initializeDb() throws RemoteException {
        try {
            for(int i=1; i<16;i++)
            {
                IDProdotto id = new IDProdotto("AB"+i);
                Money money = new Money(new Double(i*5));
                Prezzo p = new Prezzo(money, new IntervalloDiTempo(new DateTime(), new DateTime().plusDays(15)));
                DescrizioneProdotto desc = new DescrizioneProdotto(id, p, "Prodotto "+i, (int) (Math.random()*i*5));
                try {
                    DbDescrizioneProdottoSingleton.getInstance().create(desc);
                } catch (ObjectAlreadyExistsDbException ex) {
                    Logger.getLogger(CatalogoProdottiSingleton.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            TipologiaCliente tipo = new TipologiaCliente("Cliente", "cliente con tessera", 1);
            CartaCliente cliente = new CartaCliente("Pippo", "Disney", 1, tipo);
            DbCartaClienteSingleton.getInstance().create(cliente);
            IntervalloDiTempo periodo = new IntervalloDiTempo(DateTime.now(), DateTime.now().plusDays(10));
            DbManagerSingleton.getInstance().close();
            IDescrizioneProdotto desc;
            desc = DbDescrizioneProdottoSingleton.getInstance().read(new IDProdotto("AB1"));
            IScontoProdottoStrategy scontoAB1 = new ScontoPrendiPaghiClienteProdottoStrategy(3,2, periodo, tipo);
            desc.addSconto(scontoAB1);
            DbDescrizioneProdottoSingleton.getInstance().update(desc);
            
            
        } catch (ObjectAlreadyExistsDbException ex) {
            Logger.getLogger(GameShopAdvance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void startManagers() {
        ManagerPrenotazioniSingleton.getInstance().addListener(new ManagerPrenotazioniObserver());
        ManagerProdottiSingleton.getInstance().addListener(new ManagerProdottiObserver());
    }
    
    
    
}