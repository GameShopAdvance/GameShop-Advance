

package gameshop.advance.utility;

import java.io.Serializable;
import java.util.Objects;

/**
 * Classe da estendere in un periodo successivo
 * @author loki
 */
public class IDProdotto implements Serializable{
    private String codice;

    public String getCodice() {
        return codice;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString()
    {
        return this.codice;
    }

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
    
    public IDProdotto(String id)
    {
        this.codice = id;
    }
}
