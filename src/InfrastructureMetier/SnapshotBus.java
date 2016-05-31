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
public class SnapshotBus extends Snapshot implements Serializable{
    
    private Point ptCourant;

    public SnapshotBus(Date ts, Point pt) {
        super(ts);
        this.ptCourant = pt;
    }
    
    public Point getPtCourant(){
        return this.ptCourant;
    }
    
    
    
}
