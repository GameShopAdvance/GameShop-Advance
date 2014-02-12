/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.controller;

import gameshop.advance.interfaces.remote.IPrenotaProdottoRemote;
import gameshop.advance.utility.IDProdotto;
import gameshop.advance.utility.Money;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class PrenotaProdottoController extends UnicastRemoteObject implements IPrenotaProdottoRemote {
    
    //OPERAZIONI DI SISTEMA LATO CLIENTE
    
    public PrenotaProdottoController() throws RemoteException
    {
        
    }
    
    @Override
    public void avviaPrenotazione() throws RemoteException
    {
        
    }
    
    @Override
    public void prenotaProdotto(IDProdotto codiceProdotto, int quantity) throws RemoteException
    {
        
    }
    
    @Override
    public void terminaPrenotazione() throws RemoteException
    {
        
    }
    
    //OPERAZIONI DI SISTEMA LATO COMMESSO
    
    @Override
    public void ricercaPrenotazione() throws RemoteException
    {
        
    }
    
    @Override
    public void recuperaPrenotazione(Integer id) throws RemoteException
    {
        
    }
    
    @Override
    public void gestisciPagamento(Money amount) throws RemoteException
    {
        
    }
    
}
