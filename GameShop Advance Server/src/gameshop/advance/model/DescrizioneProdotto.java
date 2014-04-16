package gameshop.advance.model;

import gameshop.advance.interfaces.IDescrizioneProdotto;
import gameshop.advance.interfaces.IObserver;
import gameshop.advance.interfaces.IScontoProdottoStrategy;
import gameshop.advance.interfaces.remote.IRemoteImage;
import gameshop.advance.observers.DescrizioneProdottoObserver;
import gameshop.advance.remote.ImageProxy;
import gameshop.advance.utility.IDProdotto;
import gameshop.advance.utility.Money;
import gameshop.advance.utility.Prezzo;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
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
    private String nomeProdotto;
    private String urlImmagine;
    private LinkedList<IScontoProdottoStrategy> sconti;
    private Integer quantitaDisponibile;
    private Integer quantitaDiSoglia;
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
    public DescrizioneProdotto(IDProdotto codiceProdotto, Prezzo prezzo, String nome, String descrizione, String img, int disponibile, int soglia ) throws RemoteException{
        this.descrizione = descrizione;
        this.prezzi.add(prezzo);
        this.codiceProdotto = codiceProdotto;
        this.nomeProdotto = nome;
        this.sconti = new LinkedList<>();
        this.quantitaDisponibile = new Integer(disponibile);
        this.quantitaDiSoglia = new Integer(soglia);
        this.listener = new DescrizioneProdottoObserver();
        this.urlImmagine = img;
    }
    
    public DescrizioneProdotto(IDProdotto codiceProdotto, Prezzo prezzo, String nome, String descrizione, String img, int disponibile) throws RemoteException{
        this(codiceProdotto, prezzo, nome, descrizione, img, disponibile, 0);
    }
    
    public DescrizioneProdotto(IDProdotto codiceProdotto, Prezzo prezzo, String nome, String descrizione, String img) throws RemoteException{
        this(codiceProdotto, prezzo, descrizione, nome, img, 1, 0);
    }
    
    public DescrizioneProdotto(IDProdotto codiceProdotto, Prezzo prezzo, String nome, String descrizione) throws RemoteException{
        this(codiceProdotto, prezzo, descrizione, nome, null, 1, 0);
    }
    
    @Override
    public String getNomeProdotto() throws RemoteException {
        return nomeProdotto;
    }

    public void setNomeProdotto(String nomeProdotto) {
        this.nomeProdotto = nomeProdotto;
    }

    public String getUrlImmagine() {
        return urlImmagine;
    }

    public void setUrlImmagine(String urlImmagine) {
        this.urlImmagine = urlImmagine;
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
             if(sconto.isValid(period))
             {
                 scontiValidi.add(sconto);
             }
         }
         return scontiValidi;
     }
     
    @Override
    public synchronized void setQuantitaDisponibile(int quantity){
        this.quantitaDisponibile = new Integer(quantity);
        this.notifica();
    }
    
    protected void notifica(){
        this.listener.notifica(this);
    }
    
    @Override
    public synchronized int getQuantitaDisponibile(){
        return this.quantitaDisponibile.intValue();
    }
    
    @Override
    public void setQuantitaDiSoglia(int soglia){
        this.quantitaDiSoglia = new Integer(soglia);
        this.notifica();
    }
    
    @Override
    public int getQuantitaDiSoglia(){
        return this.quantitaDiSoglia.intValue();
    }
    
    @Override
    public boolean sottoSoglia(){
        return !(this.quantitaDisponibile.intValue() > this.quantitaDiSoglia.intValue());
    }
    
    @Override
    public synchronized void addQuantitaDisponibile(int quantity){
        this.quantitaDisponibile = new Integer(this.quantitaDisponibile.intValue() + quantity);
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.codiceProdotto);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DescrizioneProdotto other = (DescrizioneProdotto) obj;
        return Objects.equals(this.codiceProdotto, other.codiceProdotto);
    }

    @Override
    public IRemoteImage getImmagine() throws RemoteException {
        return new ImageProxy(urlImmagine);
    }
}
