package gameshop.advance.model;

import gameshop.advance.interfaces.IDescrizioneProdotto;
import gameshop.advance.interfaces.IObserver;
import gameshop.advance.interfaces.IScontoProdottoStrategy;
import gameshop.advance.observers.DescrizioneProdottoObserver;
import gameshop.advance.utility.IDProdotto;
import gameshop.advance.utility.Money;
import gameshop.advance.utility.Prezzo;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.joda.time.DateTime;

/**
 * La DescrizioneProdotto contiene tutte le informazioni relative ad un tipo di
 * prodotto: id, prezzo e descrizione testuale.
 * @author Salx
 */
public class DescrizioneProdotto implements IDescrizioneProdotto
{

    private IDProdotto codiceProdotto;
    private LinkedList<Prezzo> prezzi = new LinkedList<>();
    private String descrizione;
    private LinkedList<IScontoProdottoStrategy> sconti;
    private int quantitaDisponibile = 0;
    private int quantitaDiSoglia = 1;
    private IObserver listener;

    /**
     * Il Costruttore imposta tutte le variabili di DescrizioneProdotto utilizzando
     * i valori ricevuti in input.
     * @param codiceProdotto
     * @param prezzo
     * @param descrizione
     * @param disponibile
     * @param soglia
     * @throws java.rmi.RemoteException
     */
    public DescrizioneProdotto(IDProdotto codiceProdotto, Prezzo prezzo, String descrizione, int disponibile, int soglia ) throws RemoteException{
        this.descrizione = descrizione;
        this.prezzi.add(prezzo);
        this.codiceProdotto = codiceProdotto;
        this.sconti = new LinkedList<>();
        this.quantitaDisponibile = disponibile;
        this.quantitaDiSoglia = soglia;
        this.listener = new DescrizioneProdottoObserver();
    }
    
    public DescrizioneProdotto(IDProdotto codiceProdotto, Prezzo prezzo, String descrizione, int disponibile){
        this.descrizione = descrizione;
        this.prezzi.add(prezzo);
        this.codiceProdotto = codiceProdotto;
        this.sconti = new LinkedList<>();
        this.quantitaDisponibile = disponibile;
        this.listener = new DescrizioneProdottoObserver(); 
    }

    /**
     * @param period
     * @return il prezzo di un prodotto.
     */
    @Override
    public Money getPrezzo(DateTime period)
    {
        Prezzo p = this.getPrezzoAttuale();
        if(p!=null)
            return p.getMoney();
        else
            return null;
    }
    
    @Override
    public IDProdotto getCodiceProdotto()
    {
        return this.codiceProdotto;
    }

    /**
     * @return la descizione di un prodotto.
     */
    @Override
    public String getDescrizione()
    {
        return this.descrizione;
    }

    @Override
    public void addSconto(IScontoProdottoStrategy sconto)
    {
        this.sconti.add(sconto);
    }
    
    @Override
    public void addSconti(List<IScontoProdottoStrategy> sconti)
    {
        this.sconti.addAll(sconti);
    }

    @Override
     public void setDescrizione(String descrizione)
    {
        this.descrizione = descrizione;
    }
         
    @Override
     public List<IScontoProdottoStrategy> getSconti(DateTime period)
     {
         LinkedList<IScontoProdottoStrategy> scontiValidi = new LinkedList<>();
         for(IScontoProdottoStrategy sconto: this.sconti)
         {
             System.out.println("Sconto: "+sconto.getClass().toString());
             if(sconto.isValid(period))
             {
                 scontiValidi.add(sconto);
                 System.err.println("Sconto Valido");
             }
         }
         return scontiValidi;
     }
     
    @Override
    public synchronized void setQuantitaDisponibile(int quantity){
        this.quantitaDisponibile = quantity;
        this.listener.notifica(this);
    }
    
    @Override
    public synchronized int getQuantitaDisponibile(){
        return this.quantitaDisponibile;
    }
    
    @Override
    public void setQuantitaDiSoglia(int soglia){
        this.quantitaDiSoglia = soglia;
    }
    
    @Override
    public int getQuantitaDiSoglia(){
        return this.quantitaDiSoglia;
    }
    
    @Override
    public boolean sottoSoglia(){
        return this.quantitaDisponibile < this.quantitaDiSoglia;
    }
    
    @Override
    public synchronized void addQuantitaDisponibile(int quantity){
        this.quantitaDisponibile = this.quantitaDisponibile + quantity;
    }

    private Prezzo getPrezzoAttuale() {
        
        Iterator iter = this.prezzi.iterator();
        Prezzo prezzo;
        while(iter.hasNext())
        {
            prezzo = (Prezzo) iter.next();
            if(prezzo.isActual())
                return prezzo;
        }
        return null;
    }
    
    @Override
    public List<Prezzo> getTuttiPrezzi()
    {
        return this.prezzi;
    }
    
    @Override
    public List<IScontoProdottoStrategy> getTuttiSconti()
    {
        return this.sconti;
    }
}
