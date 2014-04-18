/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance;

import gameshop.advance.exceptions.db.ObjectAlreadyExistsDbException;
import gameshop.advance.interfaces.IScontoProdottoStrategy;
import gameshop.advance.model.DescrizioneProdotto;
import gameshop.advance.model.transazione.CartaCliente;
import gameshop.advance.model.transazione.TipologiaCliente;
import gameshop.advance.model.transazione.sconto.prodotti.ScontoPercentualeClienteProdottoStrategy;
import gameshop.advance.model.transazione.sconto.prodotti.ScontoPrendiPaghiClienteProdottoStrategy;
import gameshop.advance.model.transazione.sconto.vendita.ScontoTotaleVenditaStrategy;
import gameshop.advance.technicalservices.db.DbCartaClienteSingleton;
import gameshop.advance.technicalservices.db.DbDescrizioneProdottoSingleton;
import gameshop.advance.technicalservices.db.DbScontoVenditaSingleton;
import gameshop.advance.utility.IDProdotto;
import gameshop.advance.utility.IntervalloDiTempo;
import gameshop.advance.utility.Money;
import gameshop.advance.utility.Prezzo;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.LinkedList;
import org.joda.time.DateTime;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
class DbInitializeSingleton {
    private static DbInitializeSingleton instance;
    private final IntervalloDiTempo time1;
    private LinkedList<DescrizioneProdotto> descrizioni;
    private LinkedList<CartaCliente> clienti;
    private LinkedList<TipologiaCliente> tipiCliente;
    
    private String lorem = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
            + "Cras iaculis libero sed dignissim convallis. Vivamus tortor enim, venenatis"
            + " vel viverra eu, fringilla eu risus. Proin semper erat et vehicula varius. "
            + "In tempor dui non odio pretium placerat. Fusce euismod vestibulum ante, sit "
            + "amet sodales erat lacinia sed. Donec placerat, ipsum eget facilisis euismod, "
            + "massa lorem tempor metus, in tempor orci magna vel massa. Praesent leo felis, mollis "
            + "dictum dolor ut, aliquet vestibulum nisi. Mauris aliquet sapien eu purus dignissim, at "
            + "auctor ligula lobortis. Aliquam eleifend ligula at cursus varius. Aenean eleifend massa"
            + " nec hendrerit bibendum. Aliquam scelerisque ornare tincidunt.\n" 
            +"Sed ut feugiat magna. Quisque tempus lorem eu eleifend ullamcorper. "
            + "Maecenas eu cursus libero, et suscipit turpis. Sed feugiat erat et vestibulum dignissim. "
            + "Nam molestie velit ante, et scelerisque diam pharetra sed. Mauris eu tortor aliquam, "
            + "fermentum erat eget, porttitor neque. Mauris tincidunt pellentesque sem quis convallis."
            + " Sed tristique, dolor sed consectetur accumsan, libero nibh tempus mauris, sit amet luctus "
            + "lorem dui at erat. Maecenas accumsan malesuada dolor vitae faucibus. Maecenas tempor lobortis"
            + " nulla, eget lobortis metus tincidunt vel. Nam congue nisi vitae aliquet venenatis. Aliquam "
            + "congue dignissim imperdiet. Donec sed venenatis ipsum. ";
    
    private DbInitializeSingleton(){
        this.time1 = new IntervalloDiTempo(DateTime.now(), DateTime.now().plusDays(10));
        this.descrizioni = new LinkedList<>();
        this.clienti = new LinkedList<>();
        this.tipiCliente = new LinkedList<>();
    }
    
    public static DbInitializeSingleton getInstance(){
        if(instance == null)
            instance = new DbInitializeSingleton();
        return instance;
    }
    
    public void initialize() throws RemoteException, ObjectAlreadyExistsDbException{
        this.initializeDescrizioni();
        this.initializeClienti();
        this.initializeSconti();
        this.flush();
    }
    
    protected void initializeDescrizioni() throws RemoteException{
        descrizioni.add(new DescrizioneProdotto(new IDProdotto("ab1"), 
                new Prezzo(new Money(39.98), this.time1), "Angry Birds: Star Wars", 
                this.lorem, "angryBirds.jpg", 15, 4));
        descrizioni.add(new DescrizioneProdotto(new IDProdotto("ab2"), 
                new Prezzo(new Money(30.98), this.time1), "Call of Duty 4: Modern Warfare", 
                this.lorem, "cod4.jpg", 7, 3));
        descrizioni.add(new DescrizioneProdotto(new IDProdotto("ab3"), 
                new Prezzo(new Money(40.98), this.time1), "Diablo 3", 
                this.lorem, "diablo.jpg", 6, 9));
        descrizioni.add(new DescrizioneProdotto(new IDProdotto("ab4"), 
                new Prezzo(new Money(9.99), this.time1), "Doom 2", 
                this.lorem, "doom.jpg", 3, 1));
        descrizioni.add(new DescrizioneProdotto(new IDProdotto("ab5"), 
                new Prezzo(new Money(70.98), this.time1), "GTA V", 
                this.lorem, "gta5.jpg", 7, 6));
        descrizioni.add(new DescrizioneProdotto(new IDProdotto("ab6"), 
                new Prezzo(new Money(53.73), this.time1), "Metal Gear Solid 5", 
                this.lorem, "metalGear.jpg", 5, 10));
        descrizioni.add(new DescrizioneProdotto(new IDProdotto("ab7"), 
                new Prezzo(new Money(20.00), this.time1), "Minecraft", 
                this.lorem, "minecraft.jpg", 13, 5));
        descrizioni.add(new DescrizioneProdotto(new IDProdotto("ab8"), 
                new Prezzo(new Money(43.50), this.time1), "Il segreto di Monkey Island", 
                this.lorem, "monkeyIsland.jpg", 4));
        descrizioni.add(new DescrizioneProdotto(new IDProdotto("ab9"), 
                new Prezzo(new Money(41.98), this.time1), "NBA Live 2014", 
                this.lorem, "nba2014.jpg", 0, 5));
        descrizioni.add(new DescrizioneProdotto(new IDProdotto("ab10"), 
                new Prezzo(new Money(30.98), this.time1), "Pes 2014", 
                this.lorem, "pes2014.jpg", 8, 5));
        descrizioni.add(new DescrizioneProdotto(new IDProdotto("ab11"), 
                new Prezzo(new Money(40.98), this.time1), "Skyrim", 
                this.lorem, "skyrim.jpg", 6, 4));
        descrizioni.add(new DescrizioneProdotto(new IDProdotto("ab12"), 
                new Prezzo(new Money(50.98), this.time1), "South Park: il bastone della verità", 
                this.lorem, "southPark.jpg", 5, 5));
        descrizioni.add(new DescrizioneProdotto(new IDProdotto("ab13"), 
                new Prezzo(new Money(35.98), this.time1), "Super Mario Bros U", 
                this.lorem, "superMario.jpg", 6, 3));
        descrizioni.add(new DescrizioneProdotto(new IDProdotto("ab14"), 
                new Prezzo(new Money(60.98), this.time1), "Watch Dogs", 
                this.lorem, "watchDogs.jpg", 0, 0));
        descrizioni.add(new DescrizioneProdotto(new IDProdotto("cd1"), 
                new Prezzo(new Money(499.99), this.time1), "Xbox One", 
                this.lorem, "xboxone.jpg", 4, 2));
        descrizioni.add(new DescrizioneProdotto(new IDProdotto("cd2"), 
                new Prezzo(new Money(40.98), this.time1), "Joystick PS4", 
                this.lorem, "joystick.jpg", 16, 3));
        descrizioni.add(new DescrizioneProdotto(new IDProdotto("cd3"), 
                new Prezzo(new Money(80.98), this.time1), "Cuffie Wi-Fi", 
                this.lorem, "cuffie.jpg", 8, 1));
    }
    
    protected void initializeClienti(){
        this.tipiCliente.add(new TipologiaCliente("Gold", "Clienti di fiducia", 1));
        this.tipiCliente.add(new TipologiaCliente("Premium", "Clienti abituali", 2));
        this.tipiCliente.add(new TipologiaCliente("Regular", "Clienti con carta semplice", 3));
        this.clienti.add(new CartaCliente("Santa", "Pazienza", 1, this.tipiCliente.get(2)));
        this.clienti.add(new CartaCliente("Margherita", "Pizza", 2, this.tipiCliente.get(1)));
        this.clienti.add(new CartaCliente("Guido", "Di Rado", 3, this.tipiCliente.get(0)));
        this.clienti.add(new CartaCliente("Kevin", "Costa", 4, this.tipiCliente.get(1)));
    }
    
    protected void initializeSconti() throws ObjectAlreadyExistsDbException{
        LinkedList<IScontoProdottoStrategy> sconti = new LinkedList<>();
        LinkedList<TipologiaCliente> applicable = new LinkedList<>();
        applicable.add(this.tipiCliente.get(1));
        applicable.add(this.tipiCliente.get(2));
        sconti.add(new ScontoPercentualeClienteProdottoStrategy(20, time1, this.clienti));
        sconti.add(new ScontoPrendiPaghiClienteProdottoStrategy(3, 2, time1, applicable));
        this.descrizioni.get(1).addSconto(sconti.get(0));
        this.descrizioni.get(9).addSconto(sconti.get(1));
        this.descrizioni.get(11).addSconti(sconti);
        this.descrizioni.get(15).addSconto(sconti.get(1));
        this.descrizioni.get(16).addSconto(sconti.get(0));

        DbScontoVenditaSingleton.getInstance().create(new ScontoTotaleVenditaStrategy(new Money(5.0), time1));
    }

    private void flush() throws ObjectAlreadyExistsDbException {
        Iterator<CartaCliente> iter2 = this.clienti.iterator();
        while(iter2.hasNext())
            DbCartaClienteSingleton.getInstance().create(iter2.next());
        
        Iterator<DescrizioneProdotto> iter = this.descrizioni.iterator();
        while(iter.hasNext())
            DbDescrizioneProdottoSingleton.getInstance().create(iter.next());
    }
}
