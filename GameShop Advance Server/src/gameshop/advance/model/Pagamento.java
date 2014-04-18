package gameshop.advance.model;

import gameshop.advance.utility.Money;

/**
 * Il Pagamento contiene la variabile Money e rappresenta il pagamento di una 
 * vendita.
 * 
 * @author Salx
 */
public class Pagamento
{

    private Money ammontare;
    
    /**
     * Il Costruttore utilizza il valore ricevuto in ingresso per impostare la variabile ammontare.
     * @param ammontare
     */
    public Pagamento(Money ammontare)
    {
        this.ammontare = ammontare;
    }

    public void setAmmontare(Money ammontare) {
        this.ammontare = ammontare;
    }

    public Money getAmmontare() {
        return this.ammontare;
    }

}