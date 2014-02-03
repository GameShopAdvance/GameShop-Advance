/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.utility;
import org.joda.time.DateTime;

/**
 *
 * @author matteog
 */
public class IntervalloDiTempo {
    
     DateTime startDate; 
     DateTime endDate;
          
     public IntervalloDiTempo(DateTime start, DateTime end) {
        this.endDate = end;
        this.startDate = start;
    }
  
    public boolean isActual(){
        return this.startDate.isBeforeNow() && this.endDate.isAfterNow();
     }
    

    
}
