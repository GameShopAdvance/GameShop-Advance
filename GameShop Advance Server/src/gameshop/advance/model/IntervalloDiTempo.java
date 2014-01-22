/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.model;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.Months;

/**
 *
 * @author matteog
 */
public class IntervalloDiTempo {
    
     DateTime startDate = new DateTime();
     DateTime endDate = startDate.plus(Months.months(2));
     
     Interval interval = new Interval(startDate, endDate);
        
     public boolean isActual(){
         Duration duration = this.interval.toDuration();
         if(duration.equals(0))
             return false;
         else
             return true;
     }
    
    
}
