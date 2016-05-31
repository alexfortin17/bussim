/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domaine;
import InfrastructureMetier.Segment;
import InfrastructureMetier.Circuit;
import InfrastructureMetier.AllerSimple;
import InfrastructureMetier.Point;
import InfrastructureMetier.Boucle;
import InfrastructureMetier.Itineraire;
import Fondation.SimulExcept;
import InfrastructureMetier.Arret;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author alfo
 */
public class ReseauAutobus implements Serializable{
    
    
    //Variables
    private Graphe carte;
    ArrayList<Circuit> ListCircuits = new ArrayList();
    ArrayList<Itineraire> ListItineraire = new ArrayList();
    
    /**
     * Constructeur de ReseauAutobus
     * @param carte 
     */
    public ReseauAutobus(Graphe carte){
        this.carte = carte;  
    }
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------
    /**
     * Permet de créer un circuit
     * @param nom: Nom du circuit
     * @param loop
     * @param listPoints: Liste contenant tous les Points du circuit
     * @throws SimulExcept 
     */ 
    public void creerCircuit(String nom, boolean loop, ArrayList<Point> listPoints, double min, double mode, double max, Point src) throws SimulExcept{
        //On s'assurer que le nom du circuit n'est pas vide
        if(nom.isEmpty() || nom==null || nom.equals("")){
            throw new SimulExcept(13, "Le nom d'un circuit ne peut être vide", "creerCircuit");
        }
        
        if(listPoints.contains(src) == false){
            throw new SimulExcept(26, "Le point source ne fait pas partie du circuit", "creerCircuit");
        }
        //On vérifie si ce nom de circuit est déjà utilisé
        if(listPoints.size()>=0){
            for(int i=0;i<ListCircuits.size();i++){
                if(ListCircuits.get(i).getNomCircuit().equals(nom) && nom.isEmpty()!=true && !nom.equals("")){
                    throw new SimulExcept(17,"Le nom du circuit existe déjà", "creerCircuit");
                }
            }
        }
        //On s'assure que le circuit passé en paramètre est valide
        if(listPoints.size()<=0){
            throw new SimulExcept(14,"Le circuit n'est pas valide", "creerCircuit");
        }
        
        
        if(loop == false){
                //On verifie si les points dans l'ordre ou il sont pas sont contigus
            if(ValidationAllerSimple(listPoints) == false){
                throw new SimulExcept(16, "Les points dans l'ordre specifie pour le circuit ne sont pas contigus", "Constructeur Circuit");
            }




            //On crée le circuit
            Circuit circuit = new AllerSimple(nom, listPoints, min, mode, max, src);
            ListCircuits.add(circuit);
            }
        
        else{//si c'est une loop
            if(ValidationLoop(listPoints) == false){
            throw new SimulExcept(16, "Les points dans l'ordre specifie pour le circuit ne sont pas contigus", "Constructeur Circuit");
            }  
            
            Circuit cicuit = new Boucle(nom, listPoints, min, mode, max, src);
            ListCircuits.add(cicuit);            
        }
        
    }
    
    
    public void creerCircuit(String nom, boolean loop, ArrayList<Point> listPoints, double min, double mode, double max, int nbBusMax, Point src) throws SimulExcept{
        
        if(nbBusMax <1){
            throw new SimulExcept(20, "Le nombre max de bus sur un circuit ne peut etre plus petit que 1", "creerCircuit");
        }
        
        if(listPoints.contains(src) == false){
            throw new SimulExcept(26, "Le point source ne fait pas partie du circuit", "creerCircuit");
        }
        //On s'assurer que le nom du circuit n'est pas vide
        if(nom.isEmpty() || nom==null || nom.equals("")){
            throw new SimulExcept(13, "Le nom d'un circuit ne peut être vide", "creerCircuit");
        }
        //On vérifie si ce nom de circuit est déjà utilisé
        if(listPoints.size()>=0){
            for(int i=0;i<ListCircuits.size();i++){
                if(ListCircuits.get(i).getNomCircuit().equals(nom) && nom.isEmpty()!=true && !nom.equals("")){
                    throw new SimulExcept(17,"Le nom du circuit existe déjà", "creerCircuit");
                }
            }
        }
        //On s'assure que le circuit passé en paramètre est valide
        if(listPoints.size()<=0){
            throw new SimulExcept(14,"Le circuit n'est pas valide", "creerCircuit");
        }
        
        
        if(loop == false){
            //On verifie si les points dans l'ordre ou il sont passe sont contigus
           
            if(ValidationAllerSimple(listPoints) == false){
                throw new SimulExcept(16, "Les points dans l'ordre specifie pour le circuit ne sont pas contigus", "Constructeur Circuit");
            }




            //On crée le circuit
            Circuit circuit = new AllerSimple(nom, listPoints, min, mode, max, nbBusMax, src);
            ListCircuits.add(circuit);
            }
        
        else{//si c'est une loop
            if(ValidationLoop(listPoints) == false){
            throw new SimulExcept(16, "Les points dans l'ordre specifie pour le circuit ne sont pas contigus", "Constructeur Circuit");
            }  
            
            Circuit cicuit = new Boucle(nom, listPoints, min, mode, max, nbBusMax, src);
            ListCircuits.add(cicuit);            
        }
        
    }
    
    public void ajoutCircuit(Circuit circ){
        this.getListCircuits().add(circ);
    }
    
    public ArrayList<Circuit> getListCircuits(){
        return ListCircuits;
    }
    
    public ArrayList<Itineraire> getListItineraires(){
        return ListItineraire;
    }
    
    
    /**
     * Permet de modifier un circuit
     * @param circuit
     * @param nom: Nom du circuit à modifier 
     * @throws my.SimulatHEURE.SimulExcept 
     */
    public void modifierNomCircuit(Circuit circuit, String nom) throws SimulExcept{
        //On s'assurer que le nom du circuit n'est pas vide
        if(nom.isEmpty() || nom==null || nom.equals("")){
            throw new SimulExcept(13, "Le nom d'un circuit ne peut être vide", "modifierNomCircuit");
        }
        //On vérifie si ce nom de circuit est déjà utilisé
        if(this.ListCircuits.size()>=0){
            for(int i=0;i<ListCircuits.size();i++){
                if(ListCircuits.get(i).getNomCircuit().equals(nom) && nom.isEmpty()!=true && nom.equals("")){
                    throw new SimulExcept(17,"Le nom du circuit existe déjà", "modifierNomCircuit");
                }
            }
        }
        
        circuit.setNomCircuit(nom);
    }
    
    public void modifierCircuit(Circuit circuit, ArrayList<Point> listPts) throws SimulExcept{
        if(circuit.isLoop() == false){
            if(ValidationAllerSimple(listPts) == false){
            throw new SimulExcept(16, "Les points dans l'ordre specifie pour le circuit ne sont pas contigus", "modifierCircuit");
        }
        
        circuit.setCircuit(listPts);
        }
        else{
            if(ValidationLoop(listPts) == false){
            throw new SimulExcept(16, "Les points dans l'ordre specifie pour le circuit ne sont pas contigus", "modifierCircuit");
        }
            
        circuit.setCircuit(listPts);
        }
        
        
      
    }
    
    public void modifParamCircuit(Circuit circ, double min, double mode, double max, int capaciteMax) throws SimulExcept{
        
         if(min > mode || min > max || mode > max){
            throw new SimulExcept(5, "Les donnees de calcul de la fonction de distri triangulaire sont invalides", "modifParamCircuit");
       }
         circ.setMin(min);
         circ.setMode(mode);
         circ.setMax(max);
         circ.setCapaciteMaxCircuit(capaciteMax);
    }
    
    
    public void modifPtSource(Circuit circ, Point src) throws SimulExcept{
        
        if(circ instanceof AllerSimple && src == circ.getListPointsCircuit().get(circ.getListPointsCircuit().size() - 1)){
            
            throw new SimulExcept(33, "Le point source ne peut pas etre le dernier point d'un aller simple", "modifPtSource");

        }
        
        if(circ.getListPointsCircuit().contains(src) == false){
            throw new SimulExcept(32, "Le point a mettre comme source ne fait pas partie du circuit", "modifPtSource");
        }
        circ.setSource(src);
    }
    
    
    /**
     * Permet de supprimer un circuit
     * @param circuit: circuit à supprimer 
     */
    public ArrayList<Itineraire> supprCircuit(Circuit circuit){
        
        ArrayList<Itineraire> itiPassantparCirc = new ArrayList();
        
        for(Itineraire iti: this.getListItineraires()){
            if(iti.getListCirc().contains(circuit)){
                itiPassantparCirc.add(iti);          
            }
        }
        
        if(itiPassantparCirc.isEmpty()){
            ListCircuits.remove(circuit);
        }
        
        return itiPassantparCirc;
        
    }
    
    
    public void supprItineraire(Itineraire iti){
        this.getListItineraires().remove(iti);
    }
    
    
    
    
    public boolean ValidationAllerSimple(ArrayList<Point> listPoints){
        
        //On verifie si les points dans l'odre ou il sont passe sont contigus
        int sizeListPts = listPoints.size();
        for(int i = 0; i < listPoints.size()-1; i++){//on ne verifie pas le dernier element car rien apres
            ArrayList<Point> PtsContigus = new ArrayList();
            for (Segment seg:listPoints.get(i).getListSegment()){
                PtsContigus.add(seg.getArrivee());
            }
            
            if(PtsContigus.isEmpty() || PtsContigus.contains(listPoints.get(i+1)) == false){
                return false;
            }
        }
        
        return true;
    }
    
    public boolean ValidationLoop(ArrayList<Point> listPoints){
         //On verifie si les points dans l'odre ou il sont passe sont contigus
        int sizeListPts = listPoints.size();
        
        if(listPoints.get(0) != listPoints.get(sizeListPts - 1)){
            return false;
        }
        
        for(int i = 0; i < listPoints.size()-1; i++){//on ne verifie pas le dernier element car rien apres
            ArrayList<Point> PtsContigus = new ArrayList();
            for (Segment seg:listPoints.get(i).getListSegment()){
                PtsContigus.add(seg.getArrivee());
            }
            
            if(PtsContigus.isEmpty() || PtsContigus.contains(listPoints.get(i+1)) == false){
                return false;
            }
        }
        
        return true;
    }

    
    
    public void genererListeTotalePassagers(Date hrDebSim, Date hrFinSim){
        
    }
    
    
    
    public Circuit getCircuit(String nom){
        for(int i=0;i<this.getListCircuits().size();i++){
            if(getListCircuits().get(i).getNomCircuit().equals(nom)){
                return getListCircuits().get(i);
            }
        }
        return null;
    }
    
    public Itineraire getIti(String nom){
        for(int i=0;i<this.getListItineraires().size();i++){
            if(getListItineraires().get(i).getNomIti().equals(nom)){
                return getListItineraires().get(i);
            }
        }
        return null;
    }
    
    
    
    public Graphe getCarte(){
        return carte;       
    }
    
    public ArrayList<Segment> getListSegmentCirc(Circuit circ){
        return circ.getListSegmentCirc();
    }
    
    public ArrayList<Segment> getListSegmentIti(Itineraire iti){
        return iti.getListSegmentIti();
    }
    
    public ArrayList<Circuit> supprSegmentReseau(Segment seg) throws SimulExcept{
        ArrayList<Circuit> circPassantparSeg = new ArrayList();
        
        for(Circuit circ:this.getListCircuits()){
            ptloop:
            for(Point pt:circ.getListPointsCircuit()){
                for(Segment segment:pt.getListSegment()){
                    
                    if(seg == segment){
                        if(circ.getListPointsCircuit().contains(seg.getArrivee())){
                            circPassantparSeg.add(circ);
                            break ptloop;
                        }
                    }
                }
            }
        }
        
        if(circPassantparSeg.isEmpty()){
            getCarte().supprSegment(seg.getDepart(), seg.getArrivee());
        }
           
        return circPassantparSeg;//retourne une liste vide si la suppression est permise et une liste des circuit a supprimer si des circ passe par ce segmetn
        
    }
    
    public ArrayList<Circuit> supprPtReseau(Point pt) throws SimulExcept{
        ArrayList<Circuit> circPassantparPt = new ArrayList();
        
        for(Circuit circ:this.getListCircuits()){
            for(Point point:circ.getListPointsCircuit()){
                if(point == pt){
                    circPassantparPt.add(circ);
                    break;
                }
            }
        
            
        }
        
        if(circPassantparPt.isEmpty()){
            getCarte().supprPoint(pt);
        }
           
        return circPassantparPt;//retourne une liste vide si la suppression est permise et une liste des circuit a supprimer si des circ passe par ce segmetn
        
    }
    
    public void CreerIti(ArrayList<Point> listPts, ArrayList<Circuit> listCirc, ArrayList<Point> listPivot, String nom, double tempsArrPass1, double min, double mode, double max) throws SimulExcept{
        if(listPts.isEmpty() || listPts.size() == 1){
            throw new SimulExcept(27, "La liste de pts doit contenir au moins 2 elements", "validationListPtIti");//verifie si au  moins 2 pts
        }
        
        if(listPts.get(0) instanceof Arret == false){
            throw new SimulExcept(27, "Un itineraire doit commencer et se terminer par un arret", "validationListPtIti");
        }
        
        if(listPts.get(listPts.size() - 1) instanceof Arret == false){
            throw new SimulExcept(27, "Un itineraire doit commencer et se terminer par un arret", "validationListPtIti");
        }

        
        for(Point pt:listPts){//verifie que tous les pts se trouvent sur un circuit
            boolean flag = false;
            for(Circuit cir: this.getListCircuits()){
                if(cir.getListPointsCircuit().contains(pt)){
                    flag = true;
                    break;
                }
            }
            if(flag == false){
                throw new SimulExcept(30, "Un des points demandes ne ce trouve sur aucun circuit", "CreerIti");
            }
        }
        
        for(int i = 0; i < listPts.size() - 2; i++){//verifie que tous les pts sont contigu
            if(listPts.get(i).estContigu(listPts.get(i+1)) == false){
                throw new SimulExcept(29, "Tous les points de la liste doivent etre contigus", "validationListPtIti");
            }
        }
        /*
        //verification du nombre de circuit emprunte et si le changement de circuit se fait sur un arret
        ArrayList<Point> listPivot = new ArrayList();
        Circuit circCourant = listCirc.get(0);
        int compteurCirc = 1;
        for(int j = 0; j < listPts.size(); j++){
            if(circCourant.getListPointsCircuit().contains(listPts.get(j))){
                //pass
            }
            else{
                if(listCirc.size() <= compteurCirc){
                    throw new SimulExcept(31, "La liste de circuit et la liste de points ne concordent pas", "CreerIti");
                }
                if(listCirc.get(compteurCirc).getListPointsCircuit().contains(listPts.get(j)) == false){
                    throw new SimulExcept(31, "La liste de circuit et la liste de points ne concordent pas", "CreerIti");
                }
                if(listPts.get(j-1) instanceof Arret == false){
                    throw new SimulExcept(32, "Les pivots d'un itineraire doivent etre des arrets", "CreerIti");
                }
                
                listPivot.add(listPts.get(j-1));
                circCourant = listCirc.get(compteurCirc);
                compteurCirc++;
                
            }
        }
        */
        this.getListItineraires().add(new Itineraire(listPts, listCirc, listPivot, nom, tempsArrPass1, min, mode, max));//ajout de l'itineraire
    }
    
    public void ajoutItineraire(Itineraire iti){
        this.getListItineraires().add(iti);
    }
    
    
    public Point getProchPtPossibleIti(Point pt, Circuit circ){

        int indexPtCourant = circ.getCircdepuisSource().indexOf(pt);
        if(indexPtCourant < circ.getCircdepuisSource().size()-1){
            return circ.getCircdepuisSource().get(indexPtCourant+1);
        }
        else{
            return null;
        }
    }
    
    public ArrayList<Circuit> getCircDunPoint(Point Pt){
        ArrayList<Circuit> listCircDuPt = new ArrayList();
        
        for(Circuit circ:this.getListCircuits()){
            if(circ.getListPointsCircuit().contains(Pt)){
                listCircDuPt.add(circ);
            }
        }
        
        return listCircDuPt;
    }
}
