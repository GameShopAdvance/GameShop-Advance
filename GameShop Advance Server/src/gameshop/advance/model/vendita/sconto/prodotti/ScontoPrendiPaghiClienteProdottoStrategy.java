/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.vendita.sconto.prodotti;

import gameshop.advance.interfaces.IScontoProdottoStrategy;
import gameshop.advance.utility.IntervalloDiTempo;
import gameshop.advance.model.vendita.CartaCliente;
import gameshop.advance.interfaces.IVendita;
import gameshop.advance.model.vendita.RigaDiVendita;
import gameshop.advance.model.vendita.TipologiaCliente;
import gameshop.advance.utility.Money;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class ScontoPrendiPaghiClienteProdottoStrategy implements IScontoProdottoStrategy {
    private int prendi;
    private int paghi;
    private IntervalloDiTempo periodo;
    private LinkedList<TipologiaCliente> applicabile;

    public ScontoPrendiPaghiClienteProdottoStrategy(int take, int pay, IntervalloDiTempo period, List<TipologiaCliente> applicable)
    {
        this.paghi = pay;
        this.prendi = take;
        this.periodo = period;
        this.applicabile = (LinkedList<TipologiaCliente>) applicable;
    }
    
    public ScontoPrendiPaghiClienteProdottoStrategy(int take, int pay, IntervalloDiTempo period, TipologiaCliente applicable)
    {
        this.paghi = pay;
        this.prendi = take;
        this.periodo = period;
        LinkedList<TipologiaCliente> list = new LinkedList<>();
        list.add(applicable);
        this.applicabile = list;
    }
    
    @Override
    public Money getSubtotal(IVendita v, RigaDiVendita rdv) {
        System.out.println("Strategia prendi "+this.prendi+" paghi "+this.paghi);
        CartaCliente c = v.getCliente();
        int quantity = rdv.getQuantity();
        if(c!=null && this.checkApplicable(c.getTipo()))
        {
            int quantityOnOffer = rdv.getQuantity()/this.prendi;
            int notPayedQuantity = quantityOnOffer*(this.prendi-this.paghi);
            quantity = rdv.getQuantity()-notPayedQuantity;
        }

        return rdv.getDescrizione().getPrezzo().multiply(quantity);
    }

    private boolean checkApplicable(TipologiaCliente tc)
    {
        for(TipologiaCliente tcliente: applicabile)
        {
            if(tcliente.equals(tc))
                return true;
        }
        return false;
    }
    
    @Override
    public boolean isValid() {
        return this.periodo.isActual();
    }

    @Override
    public void add(IScontoProdottoStrategy sp) {
        
    }
    
    
    
}
