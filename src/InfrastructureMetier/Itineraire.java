/*
 * Ce code est la propriete de l'empereur Alexandre Fortin
 * Each line should be prefixed with  * 
 */
package InfrastructureMetier;

import Fondation.SimulExcept;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author alfo
 */
public class Itineraire implements Serializable{
    
    //variables
    
    String nom;
    Point depart;
    Point arrivee;
    double min;
    double mode;
    double max;
    double tempsArrpass1;
    ArrayList<Passager> listPassager = new ArrayList();
    ArrayList<Point> listPoint;
    ArrayList<Circuit> listCircuit;
    ArrayList<Point> listPivot;
    
    double statTmpMin;
    double statTmpMoy;
    double statTmpMax;
    
    
   
    
    /**
     * Constructeur avec nom d'itineraire
     * @param dep : Point de depart
     * @param arr : Point d'arrivée
     * @param nom : Nom de l'itinéraire
     */
    public Itineraire(Arret dep, Arret arr, String nom, double tempsArrpass1, double min, double mode, double max){
        this.depart = dep;
        this.arrivee = arr;
        this.nom = nom;
        this.min = min;
        this.mode = mode;
        this.max = max;
        this.tempsArrpass1 = tempsArrpass1;
        //this.genererTrajet();
    }
    
    
    public Itineraire(ArrayList<Point> listPoint, ArrayList<Circuit> listCirc, ArrayList<Point> listPivot, String nom, double tempsArrpass1, double min, double mode, double max) throws SimulExcept{
         
        this.depart = listPoint.get(0);
        this.arrivee = listPoint.get(listPoint.size() - 1);
        this.nom = nom;
        this.listPoint = new ArrayList(listPoint);
        this.listCircuit = new ArrayList(listCirc);
        this.listPivot = new ArrayList(listPivot);
        this.min = min;
        this.mode = mode;
        this.max = max;
        this.tempsArrpass1 = tempsArrpass1;
    }
    
    public void genererTrajet(){
        //TODO
    }
    
    public ArrayList<Point> getListPts(){
        return this.listPoint;
    }
    
    public ArrayList<Circuit> getListCirc(){
        return this.listCircuit;
    }
    
    public ArrayList<Point> getListPivot(){
        return this.listPivot;
    }
    
    public ArrayList<Passager> getListPass(){
        return this.listPassager;
    }
    
    /**
     * Méthode permettant d'obtenir le temps minimal
     * @return Le temps min
     */
    public double getMin() {
        return this.min;
    }

    /**
     * Méthode permettant d'obtenir le temps modal
     * @return Le temps modal
     */
    public double getMode() {
        return this.mode;
    }

    /**
     * Méthode permettant d'obtenir le temps maximal
     * @return Le temps maximal
     */
    public double getMax() {
        return this.max;
    }
    
    public void addPassager(Passager pass){
        this.listPassager.add(pass);
    }
    
    public void setStatMin(double min){
        this.statTmpMin = min;
    }
    
    public void setStatMoy(double moy){
        this.statTmpMoy = moy;
    }
    
    public void setStatMax(double max){
        this.statTmpMax = max;
    }
    
    public double getStatMin(){
        return this.statTmpMin;
    }
    
    public double getStatMoy(){
        return this.statTmpMoy;
    }
    
    public double getStatMax(){
        return this.statTmpMax;
    }
    
    public String getNomIti(){
        return this.nom;
    }
    
    public double getTempsArrPass1(){
        return this.tempsArrpass1;
    }
    
    public ArrayList<Segment> getListSegmentIti(){
        ArrayList<Segment> listSeg = new ArrayList();
        ArrayList<Point> listPt = getListPts();
        
        
        for(int i =0; i<getListPts().size() - 1; i++){
            for(Segment seg: listPt.get(i).getListSegment()){
                if(seg.getArrivee() == listPt.get(i+1)){
                    listSeg.add(seg);
                }
                    
            }
            
        }
        
        return listSeg;
            
    }
    
    
    
}
