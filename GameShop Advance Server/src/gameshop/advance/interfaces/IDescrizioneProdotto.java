/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.interfaces;

import gameshop.advance.interfaces.remote.IDescrizioneProdottoRemote;
import gameshop.advance.utility.Prezzo;
import java.util.List;
import org.joda.time.DateTime;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public interface IDescrizioneProdotto extends IDescrizioneProdottoRemote {

    void addQuantitaDisponibile(int quantity);

    void addSconti(List<IScontoProdottoStrategy> sconti);

    void addSconto(IScontoProdottoStrategy sconto);

    int getQuantitaDisponibile();
    
    void setQuantitaDiSoglia(int soglia);
    
    int getQuantitaDiSoglia();
    
    boolean sottoSoglia();

    List<IScontoProdottoStrategy> getSconti(DateTime period);

    List<Prezzo> getTuttiPrezzi();

    List<IScontoProdottoStrategy> getTuttiSconti();

    void setDescrizione(String descrizione);

    void setQuantitaDisponibile(int quantity);
    
}
