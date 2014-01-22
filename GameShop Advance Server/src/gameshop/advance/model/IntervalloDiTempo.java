/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model;
import org.joda.time.DateTime;

/**
 *
 * @author matteog
 */
public class IntervalloDiTempo {
    
     DateTime startDate;
     DateTime endDate;
     
     public IntervalloDiTempo(DateTime start, DateTime end)
     {
         this.startDate = start;
         this.endDate = end;
     }
        
     public boolean isActual(){
         if(this.startDate.isBeforeNow() && this.endDate.isAfterNow())
             return true;
         else
             return false;
     }
    
    
}
