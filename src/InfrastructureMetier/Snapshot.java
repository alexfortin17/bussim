/*
 * Ce code est la propriete de l'empereur Alexandre Fortin
 * Each line should be prefixed with  * 
 */
package InfrastructureMetier;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author alfo
 */
public abstract class Snapshot implements Serializable {
    
    private Date timestamp;
    
    
    Snapshot(Date ts){
        this.timestamp = ts;
    }
    
    public Date getTimeStamp(){
        return this.timestamp;
    }
    
    
}
