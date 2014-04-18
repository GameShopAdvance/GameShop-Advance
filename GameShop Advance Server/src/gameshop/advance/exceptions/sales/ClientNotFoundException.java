/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.exceptions.sales;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class ClientNotFoundException extends Exception {

    private int codiceTessera;
    
    public ClientNotFoundException(int codiceTessera) {
        this.codiceTessera = codiceTessera;
    }

    public int getCodiceTessera() {
        return codiceTessera;
    }

    public void setCodiceTessera(int codiceTessera) {
        this.codiceTessera = codiceTessera;
    }
    
}
