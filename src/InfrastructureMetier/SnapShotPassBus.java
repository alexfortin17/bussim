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
public class SnapShotPassBus extends SnapshotPassager implements Serializable{
    
    private Bus bus;
    
    public SnapShotPassBus(Date ts, Bus bus){
        super(ts);
        this.bus = bus;
    }
    

    public Bus getBus() {
        return bus;
    }

    
}
