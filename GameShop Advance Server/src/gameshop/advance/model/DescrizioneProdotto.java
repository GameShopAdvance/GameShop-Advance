package gameshop.advance.model;

import gameshop.advance.interfaces.IScontoProdottoStrategy;
import gameshop.advance.interfaces.remote.IRemoteDescrizione;
import gameshop.advance.utility.IDProdotto;
import gameshop.advance.utility.Money;
import gameshop.advance.utility.Prezzo;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * La DescrizioneProdotto contiene tutte le informazioni relative ad un tipo di
 * prodotto: id, prezzo e descrizione testuale.
 * @author Salx
 */
public class DescrizioneProdotto extends UnicastRemoteObject implements IRemoteDescrizione
{

    private IDProdotto codiceProdotto;
    private LinkedList<Prezzo> prezzi = new LinkedList<>();
    private String descrizione;
    private LinkedList<IScontoProdottoStrategy> sconti;
    private int quantitaDisponibile;

    /**
     * Il Costruttore imposta tutte le variabili di DescrizioneProdotto utilizzando
     * i valori ricevuti in input.
     * @param codiceProdotto
     * @param prezzo
     * @param descrizione
     */
    public DescrizioneProdotto(IDProdotto codiceProdotto, Prezzo prezzo, String descrizione ) throws RemoteException{
        this.descrizione = descrizione;
        this.prezzi.add(prezzo);
        this.codiceProdotto = codiceProdotto;
        this.sconti = new LinkedList<>();
    }

    /**
     * @return il prezzo di un prodotto.
     */
    public Money getPrezzo()
    {
        Prezzo p = this.getPrezzoAttuale();
        if(p!=null)
            return p.getMoney();
        else
            return null;
    }
    
    public IDProdotto getCodiceProdotto()
    {
        return this.codiceProdotto;
    }

    /**
     * @return la descizione di un prodotto.
     */
    public String getDescrizione()
    {
        return this.descrizione;
    }

    public void addSconto(IScontoProdottoStrategy sconto)
    {
        this.sconti.add(sconto);
    }
    
    public void addSconti(List<IScontoProdottoStrategy> sconti)
    {
        this.sconti.addAll(sconti);
    }

     public void setDescrizione(String descrizione)
    {
        this.descrizione = descrizione;
    }
         
     public List<IScontoProdottoStrategy> getSconti()
     {
         LinkedList<IScontoProdottoStrategy> scontiValidi = new LinkedList<>();
         for(IScontoProdottoStrategy sconto: this.sconti)
         {
             System.out.println("Sconto: "+sconto.getClass().toString());
             if(sconto.isValid())
             {
                 scontiValidi.add(sconto);
                 System.err.println("Sconto Valido");
             }
         }
         return scontiValidi;
     }
     
    public void setQuantitaDisponibile(int quantity){
        this.quantitaDisponibile = quantity;
    }
    
    public int getQuantitaDisponibile(){
        return this.quantitaDisponibile;
    }
    
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
    
    public List<Prezzo> getTuttiPrezzi()
    {
        return this.prezzi;
    }
    
    public List<IScontoProdottoStrategy> getTuttiSconti()
    {
        return this.sconti;
    }
}
