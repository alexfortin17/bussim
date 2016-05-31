/*
 * Ce code est la propriete de l'empereur Alexandre Fortin
 * Each line should be prefixed with  * 
 */
package InfrastructureMetier;

import Fondation.SimulExcept;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author alfo
 */
public class SnapShotPassArret extends SnapshotPassager implements Serializable{
    
    private Arret arret;
    
    public SnapShotPassArret(Date ts, Arret arr) throws SimulExcept{
        
        super(ts);
        this.arret = arr;
    }

    public Arret getArret() {
        return arret;
    }

    
}
