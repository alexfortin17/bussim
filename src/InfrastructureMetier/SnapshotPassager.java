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
public abstract class SnapshotPassager extends Snapshot implements Serializable{
    

    public SnapshotPassager(Date ts) {
        super(ts);
    }
    
    
    
}
