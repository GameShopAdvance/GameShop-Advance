/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model;

import gameshop.advance.utility.Money;

/**
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
    
    
}
