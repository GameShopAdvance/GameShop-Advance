/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.utility;

import org.joda.time.DateTime;

/**
 * Classe che modella i prezzi associando a una quantità in denaro un intervallo di tempo
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class Prezzo {
    private Money ammontare;
    private IntervalloDiTempo periodo;
    
    public Prezzo(Money m, IntervalloDiTempo time)
    {
        this.ammontare = m;
        this.periodo = time;
    }

    public Money getMoney() {
        return this.ammontare;        
    }

    public boolean isActual() 
    {
        return this.periodo.isActual();
    }
    
    public boolean isValidInDate(DateTime period)
    {
        return this.periodo.isInPeriod(period);
    }
    
}
