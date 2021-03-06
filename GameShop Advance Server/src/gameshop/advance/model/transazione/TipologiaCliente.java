/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.transazione;

/** 
 * Classe che modella le varie tipologie cliente
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class TipologiaCliente {
    private int code;
    private String name;
    private String description;
    
    public TipologiaCliente(String name, String description, int code)
    {
        this.name = name;
        this.description = description;
        this.code = code;
    }
    
    /**
     *
     * @param tc
     * @return Booleano risultato del controllo fra due tipologie cliente
     */
    public boolean equals(TipologiaCliente tc)
    {
        return this.code == tc.getCodice();           
    }
    
    public String getNome()
    {
        return this.name;
    }

    public int getCodice() {
        return this.code;
    }
}
