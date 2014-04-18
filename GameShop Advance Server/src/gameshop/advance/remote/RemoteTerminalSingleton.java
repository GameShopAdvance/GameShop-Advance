/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.remote;

import gameshop.advance.model.Cassa;
import java.util.LinkedList;

/** Gestisce la disponibilità dei terminali di tipo cassa.
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class RemoteTerminalSingleton {
    private LinkedList<Cassa> casse;
    
    private static RemoteTerminalSingleton instance;
    
    private RemoteTerminalSingleton()
    {
        this.casse = new LinkedList<>();
    }
    
    public static synchronized RemoteTerminalSingleton getInstance()
    {
        if(instance == null)
            instance = new RemoteTerminalSingleton();
        return instance;
    }
    /**
     * Utilizza il parametro in ingresso idCassa per recuperare la Cassa che possiede
     * tale id,all'interno dell'elenco di casse che fanno parte del negozio.
     * @param idCassa
     * @return l'oggetto Cassa richiesto
     */
    public Cassa getCassa(int idCassa) {
        for (Cassa cassa : this.casse) {
            if (cassa.getIdCassa() == idCassa) {
                return cassa;
            }
        }
        return null;
    }

    /**
     * Aggiunge la cassa ricevuta all'elenco delle casse presenti e attive in negozio.
     * @param cassa
     */
    public void addCassa(Cassa cassa) {
        this.casse.add(cassa);
    }
    
}
