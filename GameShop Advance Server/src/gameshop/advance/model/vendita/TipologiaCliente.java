/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.vendita;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class TipologiaCliente {
    private int code;
    private String name;
    private String description;
    
    public TipologiaCliente(String name, String description)
    {
        this.name = name;
        this.description = description;
    }
    
    /**
     *
     * @param cliente
     * @return 
     */
    public boolean equals(TipologiaCliente tc)
    {
        return this.code == tc.getCodice();           
    }

    private int getCodice() {
        return this.code;
    }
}
