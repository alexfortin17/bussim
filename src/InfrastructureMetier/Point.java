//TODO : Conversion pixels->Lat/Lon une fois méthode dans Utils implantés
package InfrastructureMetier;
import Fondation.SimulExcept;
import Fondation.Utils;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
abstract public class Point implements Serializable{
       
    /**
     * Déclaration des variables
     */
    protected static int iterPoint = 0;
    private int id;
    private Coordonnee coord;
    private Coordonnee coordPixels;  
    protected String nom;
    protected ArrayList<Segment> ListSegment = new ArrayList();//Tous les segments dont ce point est la source
    //protected boolean arret;    
    
    /**
     * Constructeur de la classe Point
     * @param coordPixel Coordonnées en pixel du point
     * @throws SimulExcept
     */
    public Point(Coordonnee coordPixel) throws SimulExcept{
        //On vérifie si les paramètres sont valides
        if(coordPixel.lat < 0 || coordPixel.lon < 0){
            throw new SimulExcept(4, "Lat/lon invalide", "Construc Point");
        }
        else{
            iterPoint++;
            this.coordPixels = new Coordonnee(coordPixel.getLat(), coordPixel.getLon()); 
            Coordonnee CoordTemp = Utils.convertPixelToCoord(coordPixel.lat, coordPixel.lon);
            this.coord = new Coordonnee(CoordTemp.getLat(), CoordTemp.getLon());
            this.id=iterPoint;
        }
    }
    
    /**
     * Constructeur copie de point
     * @param pt : Point à copier
     */
    public Point(Point pt){
        this.ListSegment = pt.ListSegment;
        this.coord = pt.coord;
        this.coordPixels = pt.coordPixels;
        this.id= pt.id;
        this.nom = pt.nom;
    }
    
    /**
     * Méthode qui définie les coordonnées d'un point (géographique)
     * @param coord : Permet de définir les coordonnées du point
     */
    public void setCoord(Coordonnee coord){
        this.coord=coord;
    }
    
    /**
     * Méthode qui definie les coordonnées pixélisées
     * @param coordPixels Les coordonnées pixélisées à definir
     */
    public void setCoordPixels(Coordonnee coordPixels) {
        this.coordPixels = coordPixels;
    }
    
    /**
     * Permet de définir le ID du point
     * @param id Le ID à définir
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Setter du Id
     */
    public void setId(){
        iterPoint++;
        this.setId(iterPoint);
    }
    
    /**
     * Permet de définir le nom du point
     * @param nom : Nom du point
     */
    public void setNom(String nom){
        this.nom=nom;
    }
    
    /**
     * Permet de définir la liste de segment du point
     * @param ListSegment la ListSegment à définir
     */
    public void setListSegment(ArrayList<Segment> ListSegment) {
        this.ListSegment = ListSegment;
    }
    

    
    /**
     * Getter de coordonnées
     * @return Les coordonnées du point
     */
    public Coordonnee getCoord(){
        return this.coord;
    }
    
        /**
     * Accesseur coordPixels
     * @return Les coordonnées pixélisées
     */
    public Coordonnee getCoordPixels() {
        return coordPixels;
    }

    /**
     * Getter du id
     * @return Le ID du point
     */
    public int getId(){
        return this.id;
    }
    
    /**
     * Accesseur du nom du point
     * @return Le nom du point
     */
    public String getNom() {
        return nom;
    }

    /**
     * Permet de récupérer la liste de segments
     * @return La liste de segments
     */
    public ArrayList<Segment> getListSegment() {
        return ListSegment;
    }
    
    public ArrayList<Point> getListPtsArrivee(){
        ArrayList<Segment> listSeg = getListSegment();
        ArrayList<Point> listPtsArr = new ArrayList();
        for(Segment seg:listSeg){
            listPtsArr.add(seg.getArrivee());
        }
        return listPtsArr;
    }



    /**
     * Permet de créer un segment
     * @param arrivee : Point d'arrivé du segment
     * @param min : Temps minimal pour parcourir ce segment
     * @param mode : Temps modal pour parcourir ce segment
     * @param max : Temps maximal pour parcourir ce segment
     */
    public void creerSegment(Point arrivee, double min, double mode, double max){
        getListSegment().add(new Segment(this, arrivee, min, mode, max));
    }
    
    /**
     * Permet de modifier les paramètres d'un segment à l'aide de son point d'arrivé
     * @param arrivee : Point d'arrivée du segment à modifier
     * @param min : Temps minimum pour parcourir ce segment
     * @param mode : Temps modal pour parcourir ce segment
     * @param max : Temps maximal pour parcourir ce segment
     */
    public void modifSegment(Point arrivee, double min, double mode, double max){
        Segment SegUtil = getListSegment().stream().filter(seg -> seg.getArrivee() == arrivee).findAny().get();
        SegUtil.setTempsMin(min);
        SegUtil.setTempsMode(mode);
        SegUtil.setTempsMax(max);
    }
    
    
    /**
     * Permet de supprimer un segment
     * @param arrivee : Point d'arrivé du segment à supprimer
     */
    public void SupprSegment(Point arrivee){
        Segment SegUtil = getListSegment().stream().filter(seg -> seg.getArrivee() == arrivee).findAny().get();
        getListSegment().remove(SegUtil);
    }
    
    /**
     * Vérifie si les coordonnées (pixels) passées en paramètres sont dans le range du point courant
     * @param x
     * @param y
     * @return
     * @throws SimulExcept 
     */
    public Point GetPointDsRangePixel(double x, double y) throws SimulExcept{
        boolean getPointDansRangePixel = false;
        
        if(this.PointExisteDansRangePixel(x,y))
        {
            getPointDansRangePixel=true;
        }
                
        if(getPointDansRangePixel == true){
            return this;
        }
        else{
            return null;
        }
    }
    
    /**
     * Vérifie si le point courant est dans le range de pixels des coordonnées passées en paramètres 
     * @param x
     * @param y
     * @return
     * @throws SimulExcept 
     */
    public boolean PointExisteDansRangePixel(double x, double y) throws SimulExcept{
        return this.getCoordPixels().lat-5 <= x && this.getCoordPixels().lat+5 >= x && this.getCoordPixels().lon-5 <= y 
                && this.getCoordPixels().lon+5 >=y;
    }
    
    public boolean estContigu(Point pt){
        for(Segment seg: this.getListSegment()){
            if(seg.getArrivee() == pt){
                return true;
            }
        }
        
        return false;
    }
    
    public void modifPositionPoint (Coordonnee coordPix) throws SimulExcept{
        setCoordPixels(coordPix);
        setCoord(Utils.convertPixelToCoord(coordPix.getLat(), coordPix.getLon()));
    }
}