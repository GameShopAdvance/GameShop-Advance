/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance;

import gameshop.advance.exceptions.ObjectAlreadyExistsDbException;
import gameshop.advance.interfaces.IScontoProdottoStrategy;
import gameshop.advance.model.CatalogoProdottiSingleton;
import gameshop.advance.model.DescrizioneProdotto;
import gameshop.advance.utility.Prezzo;
import gameshop.advance.model.vendita.CartaCliente;
import gameshop.advance.model.vendita.TipologiaCliente;
import gameshop.advance.model.vendita.sconto.prodotti.ScontoPrendiPaghiClienteProdottoStrategy;
import gameshop.advance.technicalservices.db.DbCartaClienteSingleton;
import gameshop.advance.technicalservices.db.DbDescrizioneProdottoSingleton;
import gameshop.advance.technicalservices.db.DbManagerSingleton;
import gameshop.advance.utility.IDProdotto;
import gameshop.advance.utility.IntervalloDiTempo;
import gameshop.advance.utility.Money;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.DateTime;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class InitializeDb {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            for(int i=1; i<6;i++)
            {
                IDProdotto id = new IDProdotto("AB"+i);
                Money money = new Money(new Double(i*5));
                Prezzo p = new Prezzo(money, new IntervalloDiTempo(new DateTime(), new DateTime().plusDays(5)));
                DescrizioneProdotto desc = new DescrizioneProdotto(id, p, "Prodotto "+i);
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
            DescrizioneProdotto desc;
            desc = DbDescrizioneProdottoSingleton.getInstance().read(new IDProdotto("AB1"));
            System.out.println("Descrizione prodotto dal DB: "+desc);
            IScontoProdottoStrategy scontoAB1 = new ScontoPrendiPaghiClienteProdottoStrategy(3,2, periodo, tipo);
            desc.addSconto(scontoAB1);
            System.err.println("Sconti desc: "+desc.getTuttiSconti().size());
            DbDescrizioneProdottoSingleton.getInstance().update(desc);
            
            
        } catch (ObjectAlreadyExistsDbException ex) {
            Logger.getLogger(InitializeDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
