/*
 * Ce code est la propriete de l'empereur Alexandre Fortin
 * Each line should be prefixed with  * 
 */

//TODO Olivier : Faire prototype des méthodes à implanter
package Application;
import Domaine.Graphe;
import Domaine.ObjectCloner;
import Domaine.ReseauAutobus;
import Domaine.Simulation;
import Domaine.Statistique;
import Domaine.uiExcept;
import Fondation.SimulExcept;
import Fondation.Factory;
import Fondation.Utils;
import InfrastructureMetier.Arret;
import InfrastructureMetier.Bus;
import InfrastructureMetier.Circuit;
import InfrastructureMetier.Coordonnee;
import InfrastructureMetier.Itineraire;
import InfrastructureMetier.Point;
import InfrastructureMetier.Segment;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import sun.java2d.pipe.SpanShapeRenderer;

/**
 *
 * @author Admin
 */
public class Application {
  
    ReseauAutobus reseau;
    ArrayList<Simulation> listSimulation = new ArrayList();
    
    /**
     * Constructeur d'application, crée un nouveau graphe
     */
    public Application(){
        Graphe carte = Factory.CreerGraphe();
        reseau = Factory.CreerReseau(carte);
    }
    
    /**
     * Méthode permettant de vérifier s'il y a un point dans le range des coordonnées passées en paramètres
     * @param x Coordonnées en X 
     * @param y Coordonnées en Y
     * @return Point étant dans le range de ces coordonnées, null si aucun
     * @throws uiExcept 
     */
    public Point verifClicPoint(double x, double y) throws uiExcept{
        try{
            return reseau.getCarte().verifPointRange(x, y);
        }
        
        catch (SimulExcept ex){
            throw new uiExcept(ex.getMessage());
        }
        
        catch(Exception e){
            throw new uiExcept(e.getMessage());
        }
        
    }
    
    /**
     * Méthode permettant l'ajout de point au graphe
     * @param x Coordonnées du point en X (pixels)
     * @param y Coordonnées du point en Y (pixels)
     * @param arret Type de point (true pour arrêt, false pour intersection)
     * @param nom Nom du point
     * @throws uiExcept 
     */
    public void ajouterPoint(double x, double y, boolean arret, String nom) throws uiExcept {
        try{
            if(nom == null){
            nom = "";
            }
            if(arret == true){
            reseau.getCarte().ajoutArret(new Coordonnee(x, y), nom);
            }
            else{
                reseau.getCarte().ajoutIntersec(new Coordonnee(x, y), nom);
            }
        }
        catch(SimulExcept ex){
            throw new uiExcept(ex.getMessage());
        }
        
        catch(Exception e){
            throw new uiExcept(e.getMessage());
        }
        
    }
    
    public void ajouterPoint(Point pt) throws uiExcept{
        
        try{
            this.getReseau().getCarte().ajoutPoint(pt);
        }
        catch(Exception ex){
            throw new uiExcept(ex.getMessage());
        }
        
    }
    
    /**
     * Méthode permettant de récupérer la lsite de points du graphe
     * @return ArrayList<Point> contenant tous les points du graphe
     */
    public ArrayList<Point> getListePoints() throws uiExcept{
        try{
            return reseau.getCarte().getListPoints();
        }
        catch(Exception e){
            throw new uiExcept(e.getMessage());
        }
    }
    
     public ArrayList<Point> getListePoints(String sim) throws uiExcept{
        try{
            return getSimParNom(sim).getReseau().getCarte().getListPoints();
        }
        catch(Exception e){
            throw new uiExcept(e.getMessage());
        }
    }
    
    
    
    public Point getPoint(double x, double y) throws uiExcept{
        try{
            return reseau.getCarte().getPoint(x, y);
        }
        catch(SimulExcept ex){
            throw new uiExcept(ex.getMessage());
        }
        catch(Exception e){
            throw new uiExcept(e.getMessage());
        }
    }
    
    public ArrayList<Segment> verifClicSegment(double x, double y){
        return reseau.getCarte().verifSegDsRange(x, y);
    }
    
    /**
     * Méthode permettant d'ajouter un segment au graphe
     * @param dep Point de depart
     * @param arr Point d'arrivée
     * @param min Minimum
     * @param mode Mode
     * @param max Maximum
     * @throws uiExcept 
     */
    public void ajouterSegment(Point dep, Point arr, double min, double mode, double max) throws uiExcept{
        try{
            reseau.getCarte().ajoutSegment(dep, arr, min, mode, max);
        }
        catch(SimulExcept ex){
          throw new uiExcept(ex.getMessage());
        }
        catch(Exception e){
            throw new uiExcept(e.getMessage());
        }
        
    }
    
    public void ajouterSegment(Segment seg) throws uiExcept{
        
        try{
            this.getReseau().getCarte().ajouterSegment(seg);
        }
        catch(Exception ex){
            throw new uiExcept(ex.getMessage());
        }
    }
    
    /**
     * Permet de récupérer la liste de points potentiels lors de la création d'un segment
     * @param pt Point de départ 
     * @return ArrayList<Point> contenant tous les points potentiels 
     */
    public ArrayList<Point> getListePtArrPotentielsNouveauSegment(Point pt) throws uiExcept{
        try{
            return reseau.getCarte().getListePtArrPotentielsNouveauSegment(pt);
        }
        catch(Exception e){
            throw new uiExcept(e.getMessage());
        }
    }
    
    /**
     * Méthode permettant d'obtenir la liste de points étant reliée par un segment au point passé en paramètre
     * @param pt Point d'origine
     * @return ArrayList<Point> contenant tous les points relié par un segment au point d'origine pt
     */
    public ArrayList<Point> getListePtArr(Point pt) throws uiExcept{
        try{
            return reseau.getCarte().getListePtArr(pt);
        }
        catch(Exception e){
            throw new uiExcept(e.getMessage());
        }
    }
    
    /**
     * Méthode permettant d'obtenir la liste de segments contigu d'un point
     * @param pt Point d'origine
     * @return ArrayList<Segment> contenant tous les segments contigu du point pt
     */
    public ArrayList<Segment> getListSegContigu(Point pt) throws uiExcept{
        try{
            return reseau.getCarte().getListSegContigu(pt);
        }
        catch(Exception e){
            throw new uiExcept(e.getMessage());
        }
    }
    
    /**
     * Méthode permettant de modifier un segment 
     * @param ptDep Point de depart du segment
     * @param ptArr Point d'arrivée du segment
     * @param min Nouveau min
     * @param mode Nouveau mode
     * @param max Nouveau max
     * @throws uiExcept 
     */
    public void modifSeg(Point ptDep, Point ptArr, double min, double mode, double max) throws uiExcept{
        try{
        reseau.getCarte().modifSegment(ptDep, ptArr, min, mode, max);
        }
        catch(SimulExcept ex){
            throw new uiExcept(ex.getMessage());
        }
        catch(Exception e){
            throw new uiExcept(e.getMessage());
        }
    }
    
    /**
     * Méthode permettant de supprimer un segment
     * @param ptDep Point de depart du segment
     * @param ptArr Point d'arrivée du segment
     * @throws uiExcept 
     */
    public ArrayList<Circuit> supprSeg(Segment seg) throws uiExcept{
        try{
            return reseau.supprSegmentReseau(seg);
        }
        catch(SimulExcept ex){
            throw new uiExcept(ex.getMessage());
        }
        catch(Exception e){
            throw new uiExcept(e.getMessage());
        }
        
    }
    
    /**
     * Méthode permettant de supprimer un point
     * @param pt Point a supprimer
     * @throws uiExcept 
     */
    public ArrayList<Circuit> supprPoint(Point pt) throws uiExcept{
        try{
           return reseau.supprPtReseau(pt);
        }
        catch(SimulExcept ex){
            throw new uiExcept(ex.getMessage());
        }
        catch(Exception e){
            throw new uiExcept(e.getMessage());
        }
    }
    
    /**
     * Méthode permettant de modifier le nom d'un point
     * @param pt Point a modifier
     * @param nom Nouveau nom du point
     * @throws uiExcept 
     */
    public void modifPoint(Point pt, String nom) throws uiExcept{
        try{
            reseau.getCarte().modifNomPoint(pt, nom);
        }
        catch(SimulExcept ex){
            throw new uiExcept(ex.getMessage());
        }
        catch(Exception e){
            throw new uiExcept(e.getMessage());
        }
    }
    
    /**
     * Méthode permettant de créer un circuit d'autobus
     * @param nom Nom du circuit
     * @param loop Boucle ou non
     * @param ListPtsCircuit Liste de points du circuit
     * @param min Temps minimum
     * @param mode Temps modal
     * @param max Temps maximal
     * @throws uiExcept 
     */
    public void creerCircuit(String nom, boolean loop, ArrayList<Point> ListPtsCircuit, double min, double mode, double max, Point src)throws uiExcept{
        try{
            reseau.creerCircuit(nom, loop, ListPtsCircuit, min, mode, max, src);
        }
        catch(SimulExcept ex){
            throw new uiExcept(ex.getMessage());
        }
        catch(Exception e){
            throw new uiExcept(e.getMessage());
        }
        
    }
    
     public void ajoutItineraire(Itineraire iti){
        this.getReseau().ajoutItineraire(iti);
    }
     
     
      public void ajoutCircuit(Circuit circ){
        this.getReseau().ajoutCircuit(circ);
    }
    
    /**
     * Méthode permettant de créer un circuit avec un nombre max de bus
     * @param nom Nom du circuit
     * @param loop Circuit boucle ou non
     * @param ListPtsCircuit Liste de points du circuit
     * @param min Temps minimal
     * @param mode Temps modal
     * @param max Temps maximal
     * @param nbMaxBus Nombre max de bus
     * @throws uiExcept 
     */
    public void creerCircuit(String nom, boolean loop, ArrayList<Point> ListPtsCircuit, double min, double mode, double max, int nbMaxBus, Point src)throws uiExcept{
        try{
            reseau.creerCircuit(nom, loop, ListPtsCircuit, min, mode, max, nbMaxBus, src);
        }
        catch(SimulExcept ex){
            throw new uiExcept(ex.getMessage());
        }
        catch(Exception e){
            throw new uiExcept(e.getMessage());
        }
        
    }
    
    /**
     * Méthode permettant de modifier le nom d'un circuit
     * @param nom Nouveau nom
     * @param circ Circuit
     * @throws uiExcept 
     */
    public void modifNomCircuit(String nom, Circuit circ) throws uiExcept{
        try{
           reseau.modifierNomCircuit(circ, nom);
        }
        catch(SimulExcept ex){
            throw new uiExcept(ex.getMessage());
        }
        catch(Exception e){
            throw new uiExcept(e.getMessage());
        }
        
    }
    
    /**
     * Méthode permettant de modifier les parametres d'un circuit
     * @param circ Circuit
     * @param min Nouveau min
     * @param mode Nouveau mode
     * @param max Nouveau max
     * @throws uiExcept 
     */
    public void modifParamCircuit(Circuit circ, double min, double mode, double max, int capaciteMax) throws uiExcept{
        try{
            reseau.modifParamCircuit(circ, min, mode, max, capaciteMax);
        }
        catch(SimulExcept ex){
            throw new uiExcept(ex.getMessage());
        }
        catch(Exception e){
            throw new uiExcept(e.getMessage());
        }
        
    }
    
    public void modifPtSource(Circuit circ, Point src) throws uiExcept{
        try{
            reseau.modifPtSource(circ, src);
        }
        catch(SimulExcept ex){
            throw new uiExcept(ex.getMessage());
        }
        catch(Exception e){
            throw new uiExcept(e.getMessage());
        }
    }
    
    /**
     * Méthode permettant de supprimer un circuit
     * @param circ  Circuit a supprimer
     */
    public ArrayList<Itineraire> supprCircuit(Circuit circ) throws uiExcept{
        try{
            return reseau.supprCircuit(circ);
        }
        catch(Exception e){
            throw new uiExcept(e.getMessage());
        }
    }
    
    /**
     * Méthode permettant de récupérer la liste des circuits
     * @return ArrayList<LCircuit> contenant la liste des circuits
     */
    //TODO : Generer exceptions si get liste vide
    public ArrayList<Circuit> getListCircuit() throws uiExcept{
        try{
            return reseau.getListCircuits();
        }
        catch(Exception e){
            throw new uiExcept(e.getMessage());
        }
    }
    
    public ArrayList<Itineraire> getListIti() throws uiExcept{
        try{
            return reseau.getListItineraires();
        }
        catch(Exception e){
            throw new uiExcept(e.getMessage());
        }
    }
    
    //TODO
    public ArrayList<Bus> getListTotaleBus(String nomSim) throws uiExcept{
        try{
            return getSimParNom(nomSim).getListeTotaleBus();
        }
        catch(Exception e){
            throw new uiExcept(e.getMessage());
        }
        
    }
    
    /**
     * Méthode permettant de récupérer un circuit par son nom
     * @param nom Nom du circuit
     * @return Le circuit
     */
    public Circuit getCircuit(String nom) throws uiExcept{
        try{
            return reseau.getCircuit(nom);
        }
        catch(Exception e){
            throw new uiExcept(e.getMessage());
        }
    }
    
    
    
    /**
     * Méthode permettant de lancer la simulation
     * @param hrDep Heure de debut
     * @param hrArr Heure de fin
     * @throws SimulExcept
     * @throws InterruptedException 
     */
    public void CreerSim(Date hrDep, Date hrArr, String nom) throws uiExcept {
        
        try{
            ReseauAutobus resTemp = (ReseauAutobus) ObjectCloner.deepCopy(this.getReseau());
            //this.getReseau().getListCircuits().stream().forEach((circ) -> {circ.getListBus().clear();});
            //this.getReseau().getListItineraires().stream().forEach((iti) -> {iti.getListPass().clear();});
            
            listSimulation.add(Factory.CreerSim(resTemp, hrDep, hrArr, nom));
        }
        catch(SimulExcept ex){
            throw new uiExcept(ex.getMessage());
        }
        catch(InterruptedException ex2){
            throw new uiExcept(ex2.getMessage());
        }
        catch(Exception e){
            throw new uiExcept(e.getMessage());
        }
        
    }
    
    /**
     * Méthode permettant de récupérer les coordonnées d'un bus a un moment precis
     * @param bus Le bus
     * @param moment Le moment 
     * @return Coordonnees representant l'emplacemnet (pixels) du bus
     * @throws SimulExcept 
     */
    public Coordonnee getCoordBus(Bus bus, Date moment, String nomSim) throws uiExcept{
        try{
            return getSimParNom(nomSim).getEmplacement(bus, moment);
        }
        catch(SimulExcept ex){
            throw new uiExcept(ex.getMessage());
        }
        catch(Exception e){
            throw new uiExcept(e.getMessage());
        }
        
    }
    
    /**
     * Méthode permettant de récupérer la liste totale de bus
     * @return ArrayList<Bus> contenant la liste totale des bus
     */
    public ArrayList<Bus> getListeTotaleBus(String nomSim) throws uiExcept{
        try{
            return getSimParNom(nomSim).getListeTotaleBus();
        }
        catch(Exception e){
            throw new uiExcept(e.getMessage());
        }
    }
    
    /**
     * Méthode permettant de récupérer la liste des circuits du circuit
     * @param circ Le circuit
     * @return ArrayList<Segment> contenant la liste des segments du circuit
     */
    public ArrayList<Segment> getListSegmentCirc(Circuit circ) throws uiExcept{
        try{
            return reseau.getListSegmentCirc(circ);
        }
        catch(Exception e){
            throw new uiExcept(e.getMessage());
        }
    }
    
    public ArrayList<Point> getNbPointsGraphe(){
        return reseau.getCarte().getListPoints();
    }
    
    public ReseauAutobus getReseau(){
        return this.reseau;
    }
    
    public void CreerItineraire(ArrayList<Point> listPts, ArrayList<Circuit> listCirc, ArrayList<Point> listPivot, String nom, double tempsArrPass1, double min, double mode, double max) throws uiExcept{
        try{
            this.getReseau().CreerIti(listPts, listCirc, listPivot,nom, tempsArrPass1, min, mode, max);
        }
        catch(SimulExcept ex){
            throw new uiExcept(ex.getMessage());
        }
        catch(Exception ex){
            throw new uiExcept(ex.getMessage());
        }
    }
    
    public Point getProchPtPossibleIti(Point pt, Circuit circ){
        
        return this.getReseau().getProchPtPossibleIti(pt, circ);
        
    }
    
    public ArrayList<Circuit> getCircDunPoint(Point Pt){
        return this.getReseau().getCircDunPoint(Pt);
    }
    
    
    public void majListsPassagers(Date hr, String nomSim) throws uiExcept{
        try{
            getSimParNom(nomSim).majListesPass(hr);
        }
        catch(SimulExcept ex){
            throw new uiExcept(ex.getMessage());
        }
        
        catch(Exception ex){
            throw new uiExcept(ex.getMessage());
        }
    }
    
    public void flushListSim(){
        this.listSimulation.clear();
    }
    
    public ArrayList<Simulation> getListSim(){
        return this.listSimulation;
    }
    
    
    public Simulation getSimParNom(String nom){
        for(Simulation sim:this.getListSim()){
            if(sim.getNomSim().equals(nom)){
                return sim;
            }
        }
        
        return null;
    }
    
    public void modifPositionPoint (Point pt, double x, double y) throws uiExcept{
        try{
            this.getReseau().getCarte().modifPositionPoint(pt, new Coordonnee(x,y));
        }
        catch(SimulExcept ex){
            throw new uiExcept(ex.getMessage());
        }
    }
    
    public void supprItineraire(Itineraire iti) throws uiExcept{
        try{
            reseau.supprItineraire(iti);
        }
        catch(Exception e){
            throw new uiExcept(e.getMessage());
        }
    }
    
    public Itineraire getIti(String nom) throws uiExcept{
        try{
            return reseau.getIti(nom);
        }
        catch(Exception e){
            throw new uiExcept(e.getMessage());
        }
    }
    
    public ArrayList<Segment> getListSegmentItineraire(Itineraire iti){
        
        return reseau.getListSegmentIti(iti);
        
    }
    
    public Point changerTypeArretVersIntersection(Point pt) throws uiExcept{
        try{
            /*double x = pt.getCoordPixels().getLat();
            double y = pt.getCoordPixels().getLon();
            String nom = pt.getNom();
            ArrayList<Segment> listeSeg = new ArrayList();
            listeSeg = pt.getListSegment();
            ArrayList<Point> listePointDepart = new ArrayList();
            ArrayList<Double> listeMin = new ArrayList();
            ArrayList<Double> listeMode = new ArrayList();
            ArrayList<Double> listeMax = new ArrayList();
            //Loop sur chaque points du graphe
            for(Point pointsCourants : this.getListePoints()){
                //Pour chaque points, on récupère chaque segments un a un
                for(Segment segmentsPointsCourants : pointsCourants.getListSegment()){
                    //On vérifie si le points d'arrivée du segment est le point courant
                    if(segmentsPointsCourants.getArrivee()==pt){
                        listePointDepart.add(segmentsPointsCourants.getDepart());
                        listeMin.add(segmentsPointsCourants.getTempsMin());
                        listeMode.add(segmentsPointsCourants.getTempsMode());
                        listeMax.add(segmentsPointsCourants.getTempsMax());
                    }
                }
            }            
            supprPoint(pt);
            ajouterPoint(x,y,false,nom);
            Point temp = getPoint(x,y);
            temp.setListSegment(listeSeg);
            int iter=0;
            for(Point ptDepart : listePointDepart){
                 ajouterSegment(ptDepart, temp, listeMin.get(iter), listeMode.get(iter), listeMax.get(iter));
                 iter++;
            }*/
            return this.getReseau().getCarte().changerTypeArretVersIntersection(pt);
        }catch(SimulExcept ex){
            throw new uiExcept(ex.getMessage());
        }
    }
    
    public Point changerTypeIntersectionVersArret(Point pt) throws uiExcept{
        try{
            /*double x = pt.getCoordPixels().getLat();
            double y = pt.getCoordPixels().getLon();
            String nom = pt.getNom();
            ArrayList<Segment> listeSeg = new ArrayList();
            listeSeg = pt.getListSegment();
            ArrayList<Point> listePointDepart = new ArrayList();
            ArrayList<Double> listeMin = new ArrayList();
            ArrayList<Double> listeMode = new ArrayList();
            ArrayList<Double> listeMax = new ArrayList();
            //Loop sur chaque points du graphe
            for(Point pointsCourants : this.getListePoints()){
                //Pour chaque points, on récupère chaque segments un a un
                for(Segment segmentsPointsCourants : pointsCourants.getListSegment()){
                    //On vérifie si le points d'arrivée du segment est le point courant
                    if(segmentsPointsCourants.getArrivee()==pt){
                        listePointDepart.add(segmentsPointsCourants.getDepart());
                        listeMin.add(segmentsPointsCourants.getTempsMin());
                        listeMode.add(segmentsPointsCourants.getTempsMode());
                        listeMax.add(segmentsPointsCourants.getTempsMax());
                    }
                }
            }           
            supprPoint(pt);
            ajouterPoint(x,y,true,nom);
            Point temp = getPoint(x,y);
            temp.setListSegment(listeSeg);
            int iter=0;
            for(Point ptDepart : listePointDepart){
                 ajouterSegment(ptDepart, temp, listeMin.get(iter), listeMode.get(iter), listeMax.get(iter));
                 iter++;
            }*/
            return this.getReseau().getCarte().changerTypeIntersectionVersArret(pt);
        }catch(SimulExcept ex){
            throw new uiExcept(ex.getMessage());
        }
    }
    
    
    public ArrayList<Statistique> getStatsIti(Simulation sim){
        return sim.getStatsIti();
    }
    
    public void saveSim(String path, Simulation sim) throws uiExcept{
        try{
            Utils.saveSim(path, sim);
        }
        catch(FileNotFoundException e){
            
            throw new uiExcept("Le path est introuvable");
            
        }
        
        catch(Exception e){
            
            throw new uiExcept(e.getMessage());
            
        }
    }
    
    public void loadSim(String path) throws uiExcept{
        try{
            Simulation simLoade = Utils.loadSim(path);
            this.reseau=simLoade.getReseau();
            this.listSimulation.clear();
            this.listSimulation.add(simLoade);
        }
        catch(Exception ex){
            throw new uiExcept(ex.getMessage());
        }
        
        
                
    }
    
    
    public boolean arretAtteignable(Point pt) throws uiExcept{
        try{
            return this.getReseau().getCarte().arretAtteignable(pt);
        }
        catch(Exception ex){
            throw new uiExcept(ex.getMessage());
        }
    }
}
