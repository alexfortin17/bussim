//TODO Implanter méthode qui génère les autobus (Olivier)
package InfrastructureMetier;
import Fondation.SimulExcept;
import Fondation.Utils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Admin
 */
abstract public class Circuit implements Serializable{
    
     /**
     * Déclaration des variables 
     */
    private String nomCircuit;//Nom du circuit
    private ArrayList<Point> ListPointsCircuit = new ArrayList();//Liste de points parcouru par le circuit
    protected static int iterCircuit = 0;//Iterateur 
    private int id;//ID du circuit
    private boolean loop;//Définir si le circuit est une boucle ou un allez-simple
    private int capaciteMaxCircuit = -1;//Nombre maximal d'autobus sur le circuit
    protected int compteurBus = 0;//Compteur du nombre d'autobus
    private double min;//Temps minimal 
    private double mode;//Temps modal
    private double max;//Temps maximal
    private Point source;//Point source du circuit
    
    /**
     * Constructeur de la classe Circuit
     * @param nom: Nom du circuit
     * @param listPoints: Array contenant chacun les Points du circuit
     * @param min temps minimal
     * @param mode temps modal
     * @param max temps maximal
     * @throws Fondation.SimulExcept
     */
    public Circuit(String nom, ArrayList<Point> listPoints, double min, double mode, double max, Point src) throws SimulExcept{
        if(nom.isEmpty()||nom.equals("")){
            throw new SimulExcept(18, "Le nom du circuit ne peut etre vide", "construc Circuit");
        }
        iterCircuit++;
        this.id = iterCircuit;
        this.nomCircuit=nom;      
        ListPointsCircuit.addAll(listPoints);
        this.min = min;
        this.mode = mode;
        this.max=max;
        this.source = src;
    }
    
        /**
     * Constructeur de la classe Circuit avec paramètres capaciteMaxCircuit
     * @param nom: Nom du circuit
     * @param listPoints: Array contenant chacun les Points du circuit
     * @param min temps minimal
     * @param mode temps modal
     * @param max temps maximal
     * @param capMaxCircuit Capacité maximale du circuit
     * @throws Fondation.SimulExcept
     */
    public Circuit(String nom, ArrayList<Point> listPoints, double min, double mode, double max, int capMaxCircuit, Point src) throws SimulExcept{
        if(nom.isEmpty()||nom.equals("")){
            throw new SimulExcept(18, "Le nom du circuit ne peut etre vide", "construc Circuit");
        }
        if(capMaxCircuit<1){
            throw new SimulExcept(20,"Le nombre max de bus sur un circuit ne peut etre plus petit que 1","Circuit");
        }
        iterCircuit++;
        this.id = iterCircuit;
        this.nomCircuit=nom;      
        this.ListPointsCircuit=listPoints;
        this.min = min;
        this.mode = mode;
        this.max=max;
        this.capaciteMaxCircuit=capMaxCircuit;
        this.source = src;
    }
    
    /**
     * Méthode qui permet de définir le nom du circuit
     * @param nom: Nom du circuit 
     * @throws Fondation.SimulExcept 
     */
    public void setNomCircuit(String nom) throws SimulExcept{
        if(nom.equals("")||nom.isEmpty()){
            throw new SimulExcept(13,"Le nom d'un circuit ne peut être vide","setNomCircuit");
        }
        this.nomCircuit = nom;
    }
    
    /**
     * Méthode qui permet de définir la liste de points du Circuit
     * @param listPoints: Array contenant chacun des segments du circuit 
     * @throws Fondation.SimulExcept 
     */
    public void setCircuit(ArrayList<Point> listPoints) throws SimulExcept{
        if(listPoints.size()<2){
            throw new SimulExcept(14,"Le circuit n'est pas valide","setCircuit");
        }
        this.ListPointsCircuit = listPoints;
    }
   
    /**
     * Méthode permettant de définir si un circuit est une boucle ou un allez-simple
     * @param loop boolean représentant vrai(boucle) ou faux(allez-simple)
     */
    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    /**
     * Méthode permettant de définir la capacité maximale du circuit
     * @param capaciteMaxCircuit La capacité à définir
     * @throws Fondation.SimulExcept
     */
    public void setCapaciteMaxCircuit(int capaciteMaxCircuit) throws SimulExcept{
        if(capaciteMaxCircuit<1){
            this.capaciteMaxCircuit = -1;
        }
        else{
            this.capaciteMaxCircuit = capaciteMaxCircuit;
        }
        
    }

    /**
     * Méthode permettant de définir le temps minimal du circuit
     * @param min Le min à définir
     * @throws Fondation.SimulExcept
     */
    public void setMin(double min) throws SimulExcept{
        if(min<1){
            throw new SimulExcept(22, "Le temps minimal/maximal/modal du circuit doit être plus grand que 1","setMin");
        }
        this.min = min;
    }

    /**
     * Méthode permettant de définir le temps modal du circuit
     * @param mode Le mode à définir
     * @throws Fondation.SimulExcept
     */
    public void setMode(double mode) throws SimulExcept {
        if(mode<1){
            throw new SimulExcept(22, "Le temps minimal/maximal/modal du circuit doit être plus grand que 1","setMode");
        }
        this.mode = mode;
    }

    /**
     * Méthode permettant de définir le temps maximal du circuit
     * @param max Le max à définir
     * @throws Fondation.SimulExcept
     */
    public void setMax(double max) throws SimulExcept {
        if(max<1){
            throw new SimulExcept(22, "Le temps minimal/maximal/modal du circuit doit être plus grand que 1","setMax");
        }
        this.max = max;
    }

    /**
     * Méthode permettant de définir le point source du circuit
     * @param source Le point source à définir
     * @throws Fondation.SimulExcept
     */
    public void setSource(Point source) throws SimulExcept {
        this.source = source;
    }
    
    /**
     * Accesseur du nom du circuit
     * @return:String représentant le nom du circuit
     */
    public String getNomCircuit(){
        return this.nomCircuit;
    }
    
    /**
     * Acesseur de la liste de points du circuit
     * @return: Array contenant chacun des segments du circuit 
     */
    public ArrayList<Point> getCircuit(){
        return getListPointsCircuit();
    }
    
    /**
     * Méthode permettant d'avoir accès au ID du circuit
     * @return Le ID
     */
    public int getId() {
        return id;
    }

    /**
     * Méthode permettant d'avoir accès au paramètre loop
     * @return Vrai/Faux si le circuit est une boucle ou non
     */
    public boolean isLoop() {
        return loop;
    }

    /**
     * Méthode permettant d'avoir accès à la capacité max d'autobus du circuit
     * @return La capacité
     */
    public int getCapaciteMaxCircuit() {
        return capaciteMaxCircuit;
    }

    /**
     * Méthode permettant d'obtenir le temps minimal du circuit
     * @return Le temps min
     */
    public double getMin() {
        return min;
    }

    /**
     * Méthode permettant d'obtenir le temps modal du circuit
     * @return Le temps modal
     */
    public double getMode() {
        return mode;
    }

    /**
     * Méthode permettant d'obtenir le temps maximal du circuit
     * @return Le temps maximal
     */
    public double getMax() {
        return max;
    }

    /**
     * Méthode permettant d'obtenir le point source du circuit
     * @return Le point source
     */
    public Point getSource() {
        return source;
    }
    
    /**
     * Méthode permettant d'obtenir la liste de points du circuit
     * @return La liste de points
     */
    public ArrayList<Point> getListPointsCircuit() {
        return ListPointsCircuit;
    }
    
    /**
     * Méthode permettant de modifier un circuit
     * @param nom Nouveau nom du circuit
     * @param listPoints Nouvelle liste de point du circuit
     * @param min Nouveau temps minimal du circuit
     * @param mode Nouveau temps modal du circuit
     * @param max Nouveau temps maximal du circuit
     * @param source Nouveau point source du circuit
     * @throws Fondation.SimulExcept
     */
    public void modifCircuit(String nom, ArrayList<Point> listPoints, double min, double mode, double max) throws SimulExcept{
        if(nom.equals("")||nom.isEmpty()){
            throw new SimulExcept(13,"Le nom d'un circuit ne peut être vide","modifCircuit");
        }
        if(listPoints.size()<2){
            throw new SimulExcept(14,"Le circuit n'est pas valide","modifCircuit");
        }
        if(min<1 || mode<1 || max<1){ 
            throw new SimulExcept(22,"Le temps minimal/maximal/modal du circuit doit être plus grand que 1","modifCircuit");
        }
        /*
        if(source.getArret()!=true){
            throw new SimulExcept(23,"Le point de départ d'un circuit doit être un arrêt","modifCircuit");
        }
        */
        if(listPoints.get(0) instanceof Arret == false)
        {
            throw new SimulExcept(23,"Le point de départ d'un circuit doit être un arrêt","modifCircuit");
        }
        this.nomCircuit = nom;
        this.ListPointsCircuit=listPoints;
        this.min = min;
        this.mode = mode;
        this.max = max;
    }
    
    public void supprCircuit(Circuit circ){
        circ=null;
    }
    
    public abstract void AddBus(Bus bus) throws SimulExcept, InterruptedException;
    
    public abstract ArrayList<Point> getCircdepuisSource();
    
    
    public abstract ArrayList<Bus> getListBus();
    
    public ArrayList<Segment> getListSegmentCirc(){
        ArrayList<Segment> listSeg = new ArrayList();
        ArrayList<Point> listPt = getListPointsCircuit();
        
        
        for(int i =0; i<getListPointsCircuit().size() - 1; i++){
            for(Segment seg: listPt.get(i).getListSegment()){
                if(seg.getArrivee() == listPt.get(i+1)){
                    listSeg.add(seg);
                }
                    
            }
            
        }
        
        return listSeg;
            
    }
}