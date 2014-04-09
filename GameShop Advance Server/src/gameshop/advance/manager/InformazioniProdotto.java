/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.manager;

import gameshop.advance.interfaces.IDescrizioneProdotto;
import gameshop.advance.interfaces.IOrdine;
import gameshop.advance.interfaces.IPrenotazione;
import java.util.LinkedList;

/**
 *
 * @author Lorenzo Di Giuseppe
 */
public class InformazioniProdotto {
    
    private IDescrizioneProdotto descrizione;
    
    private LinkedList<IPrenotazione> prenotazioni;
    
    private LinkedList<IOrdine> ordinazioni;
    
    public InformazioniProdotto(IDescrizioneProdotto desc)
    {
        this.descrizione = desc;
        this.prenotazioni = new LinkedList<>();
        this.ordinazioni = new LinkedList<>();
    }
    
    public void addPrenotazione(IPrenotazione pren){
        this.prenotazioni.add(pren);
    }
}
