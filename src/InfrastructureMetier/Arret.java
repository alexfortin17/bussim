//TODO : Valider conversion pixels->lat/lon une fois méthode implantée dans Utils
package InfrastructureMetier;
import Fondation.SimulExcept;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Arret extends Point implements Serializable{
    
    
    /**
    * Déclaration des variables privées
    */
   private boolean departItineraire = false;//indique si l'arrêt correspond au départ d'un itinéraire
   private ArrayList<Passager> listPassagers = new ArrayList();//Liste des passagers à l'arrêt courant
   private int compteurPassagers;

   /**
    * Constructeur de la classe Arrêt
    * @param coord: Coordonnées de l'arrêt
     * @throws Fondation.SimulExcept
    */
   public Arret(Coordonnee coord) throws SimulExcept{
        super(coord);//Défini un point aux coordonnées de l'arrêt
        this.compteurPassagers = 0;
   }
   
   /**
    * Constructeur d'un arrêt avec un nom d'arrêt
    * @param coord: Coordonnées de l'arrêt
    * @param nom: Nom de l'arrêt
    * @throws SimulExcept 
    */
   public Arret(Coordonnee coordPixel, String nom) throws SimulExcept{
       super(coordPixel);//Défini un point aux coordonnées de l'arrêt
       this.setNom(nom);
       this.compteurPassagers = 0;
   }
   
   
   /**
    * Constructeur copie de Arret
    * @param arr 
    * @throws Fondation.SimulExcept 
    */
   public Arret(Arret arr) throws SimulExcept{
       super(arr.getCoordPixels());
       this.listPassagers = arr.getListPassagers();
       this.setListSegment(arr.getListSegment());
       this.departItineraire = arr.getDepartItineraire();
       this.setId(arr.getId());
       this.setNom(arr.getNom());
      
   }
   
   /**
    * Accesseur de departItineraire
     * @return departItineraire
     */
    public boolean getDepartItineraire() {
        return departItineraire;
    }

    /**
     * Setter de departItineraire
     * @param departItineraire la valeur à définir
     */
    public void setDepartItineraire(boolean departItineraire) {
        this.departItineraire = departItineraire;
    }

    /**
     * Acceseur de la liste de passagers
     * @return la liste de passagers à cet arrêt
     */
    public ArrayList<Passager> getListPassagers() {
        return listPassagers;
    }

    /**
     * Setter de la liste de passagers
     * @param listPassagers la liste de passagers à définir
     */
    public void setListPassagers(ArrayList<Passager> listPassagers) {
        this.listPassagers = listPassagers;
    }

    /**
     * Accesseur compteurPassagers
     * @return compteurPassagers
     */
    public int getCompteurPassagers() {
        return compteurPassagers;
    }

    /**
     * Setter de compteurPassagers
     * @param compteurPassagers la valeur à définir
     */
    public void setCompteurPassagers(int compteurPassagers) {
        this.compteurPassagers = compteurPassagers;
    }

   /**
    * Permet d'ajouter des passagers à la liste
    * @param pass Passagers à ajouter
    */
   public void addPassagers(Passager pass){
        this.listPassagers.add(pass);
        setCompteurPassagers(getCompteurPassagers() + 1);
    }
    
   /**
    * Permet de supprimer un passager
    * @param pass : Passager à supprimer
    */
    public void delPassagers(Passager pass){
        for(int i=0;i<listPassagers.size();i++){
            if(this.listPassagers.get(i).getId()==pass.getId()){
                this.listPassagers.remove(i);
                this.setCompteurPassagers(this.getCompteurPassagers() - 1);
            }
        }
    }

    
   /**public void creationDepartItineraire(double min, double mode, double max) throws SimulExcept{
       
       if(min > mode || min > max || mode > max){
           throw new SimulExcept(5, "Les parametres pour la distro triangulaire sont invalides");
       }
       
       this.departItineraire = true;
       this.tempsMin = min;
       this.tempsMode = mode;
       this.tempsMax = max;
   }**/
   
   
}
