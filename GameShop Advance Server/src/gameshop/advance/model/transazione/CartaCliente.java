/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model.transazione;

/**
 * Model della carta cliente
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class CartaCliente {
    
    private String nomeCliente;
    private String cognomeCliente;
    private TipologiaCliente tipo;
    private int codice;
    
    public CartaCliente(String name, String surname, int code, TipologiaCliente tipo)
    {
        this.nomeCliente = name;
        this.cognomeCliente = surname;
        this.codice = code;
        this.tipo = tipo;
    }
    /**
     * @param id
     */
    public CartaCliente(int id)
    {
        this.codice = id;
    }
    /**
     * @return  La tipologia di cliente associata alla carta
     */
    public TipologiaCliente getTipo()
    {
        return tipo;
    }
    /**
     * @return  Il codice della carta
     */
    public int getId()
    {
        return this.codice;
    }
}
