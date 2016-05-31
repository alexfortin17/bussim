/*
 * Ce code est la propriete de l'empereur Alexandre Fortin
 * Each line should be prefixed with  * 
 */
package InfrastructureMetier;
import Fondation.SimulExcept;
import java.io.Serializable;

/**
 *
 * @author alfo
 */
public class Coordonnee implements Serializable {
    
    //Variable
    public double lat;
    public double lon;
    
    /**
     * Constructeur de coordonnées
     * @param lat: Latitude
     * @param lon: Longitude
     * @throws SimulExcept 
     */
     public Coordonnee(double lat, double lon) throws SimulExcept{
        this.lat = lat;
        this.lon = lon;
    }

    /**
     * Accesseur de latitude
     * @return la latitude
     */
    public double getLat() {
        return lat;
    }

    /**
     * Setteur de latitude
     * @param lat la latitude a definir
     */
    public void setLat(double lat) {
        this.lat = lat;
    }

    /**
     * Accesseur de longitude
     * @return La longitude
     */
    public double getLon() {
        return lon;
    }

    /**
     * Setteur de longitude
     * @param lon La longitude a definir
     */
    public void setLon(double lon) {
        this.lon = lon;
    }
}