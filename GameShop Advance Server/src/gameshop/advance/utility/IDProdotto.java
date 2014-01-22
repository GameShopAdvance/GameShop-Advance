

package gameshop.advance.utility;

import java.io.Serializable;
import java.util.Objects;

/**
 * Tale classe gestisce tutte le operazioni riguardanti l'id di un prodotto.La 
 * classe verrà estesa in un periodo successivo.
 * @author loki
 */
public class IDProdotto implements Serializable{
    private String codice;

    public IDProdotto(String id)
    {
        this.codice = id;
    }

    public String getCodice() {
        return codice;
    }
    
    /**
     * @return l'id del prodotto come stringa
     */
    @Override
    public String toString()
    {
        return this.codice;
    }

    /**
     * Controlla se l'oggetto ricevuto come parametro è un
     * oggetto IDProdotto.In tal caso controlla se il parametro codice 
     * dell'oggetto ricevuto è uguale al proprio codice.Solo in tal caso
     * restituirà true.
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final IDProdotto other = (IDProdotto) obj;
        if (!Objects.equals(this.codice, other.codice)) {
            return false;
        }
        return true;
    }

    /**
     * @return il codice hash del prodotto.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        //hash = this.getClass().toString().hashCode();
        hash = 67 * hash + Objects.hashCode(this.codice);
        return hash;
    }
    
    public void setCodice(String codice) {
        this.codice = codice;
    }
    
    
}
