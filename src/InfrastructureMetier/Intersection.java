//TODO : Valider conversion pixels->lat/lon une fois méthode implantée dans Utils
package InfrastructureMetier;
import Fondation.SimulExcept;
import java.io.Serializable;

/**
 * @author Admin
 */
public class Intersection extends Point implements Serializable{
    
   /**
    * Constructeur de la classe intersection
     * @param coordPixel Les coordonnées pixélisées de l'intersection
    * @throws SimulExcept 
    */
    
   public Intersection(Coordonnee coordPixel) throws SimulExcept{
       super(coordPixel);//Crée un point à l'emplacement de l'intersection
   }
   
   //TODO : À Discuter l'utilité d'un constructeur de copie?
   /**
    * Constructeur copie d'intersection
    * @param inter : Intersection à copiée
    * @throws Fondation.SimulExcept 
    */
   
   public Intersection(Intersection inter) throws SimulExcept{
       super(inter.getCoordPixels());
       this.setListSegment(inter.getListSegment());
       this.setId(inter.getId());
       this.setNom(inter.getNom());
   }
   
   /**
    * Constrcteur d'int
    * @param coord Coordonnées de l'intersection
    * @param nom : Nom de l'intersection
     * @throws Fondation.SimulExcept 
    */
   
   public Intersection(Coordonnee coord, String nom) throws SimulExcept{
       super(coord);
       this.setNom(nom);
   }
}