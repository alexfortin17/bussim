/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InfrastructureMetier;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Classe representant les autobus
 * @author alfo
 */
public class Bus implements Serializable{

    /**
     * @return the compteurPrimaire
     */
    public static int getCompteurPrimaire() {
        return compteurPrimaire;
    }

    /**
     * @param aCompteurPrimaire the compteurPrimaire to set
     */
    public static void setCompteurPrimaire(int aCompteurPrimaire) {
        compteurPrimaire = aCompteurPrimaire;
    }
    
    
    //Attributs
    private int id;
    private int compteurPassagers;
    private static int compteurPrimaire = 0;
    private Coordonnee coord;
    private Coordonnee coordPixel;
    private ArrayList<Passager> listePassagers = new ArrayList();
    private Date hrDepart;
    private Date hrArrivee;
    private Circuit circuit;
    private boolean actif;
    private ArrayList<SnapshotBus> listSnapShots = new ArrayList();
    
    
   //TODO Liste de passager a mettre a jour 
    public Bus(Date hrDepart, Date hrArrivee, Circuit circuit){
        compteurPrimaire++;
        this.id = compteurPrimaire;
        this.compteurPassagers = 0;
        this.hrDepart = hrDepart;
        this.hrArrivee = hrArrivee;
        this.circuit = circuit;         
    }
    
    public Bus(Date hrDepart, Date hrArrivee, Circuit circuit, Coordonnee coord){
        compteurPrimaire++;
        this.id = compteurPrimaire;
        this.compteurPassagers = 0;
        this.hrDepart = hrDepart;
        this.hrArrivee = hrArrivee;
        this.circuit = circuit;
       
    }
    
    /**
     * Fonction get pour l'identificateur d'autobus
     * @return identificateur de l'autobus(int)
     */
    public int getId(){
        return this.id;
    }
    
    /**
     * Fonction set pour l'identificateur d'autobus
     * @param nouvId: int representant le nouvel id de la bus
     */
    public void setId(int nouvId){
        id = nouvId;
    }
    
    
    /**
     * Fonction get pour la le compteur de passagers
     * @return le compte courant du nombre de passagers (int)
     */
    public int getCompteur(){
        return this.getCompteurPassagers();
    }
    
    /**
     * Fonction set pour la le compteur de passagers
     * @param nouvCompteur : nouveau compte total de passagers
     */
    public void setCompteur(int nouvCompteur){
        setCompteurPassagers(nouvCompteur);
    }
    
    public Circuit getCircuit(){
        return this.circuit;
    }
    
    public void setCircuit(Circuit circuit){
        this.circuit = circuit;
    }
    
    public Date getHrDep(){
        return this.getHrDepart();
    }
    
    public void setHrDep(Date hr){
        this.setHrDepart(hr);
    }
    
    public Date getHrArr(){
        return this.getHrArrivee();
    }
    
    public void setHrArr(Date hr){
        this.setHrArrivee(hr);
    }
    
    public void addPassagers(Passager pass){
        
        getListePassagers().add(pass);
        setCompteurPassagers(getCompteurPassagers() + 1);
    }
    
    public void delPassagers(Passager pass){
        //Precondition
        getListePassagers().remove(pass);
        setCompteurPassagers(getCompteurPassagers() - 1);
    }

    /**
     * @return the compteurPassagers
     */
    public int getCompteurPassagers() {
        return compteurPassagers;
    }

    /**
     * @param compteurPassagers the compteurPassagers to set
     */
    public void setCompteurPassagers(int compteurPassagers) {
        this.compteurPassagers = compteurPassagers;
    }

    /**
     * @return the coord
     */
    public Coordonnee getCoord() {
        return coord;
    }

    /**
     * @param coord the coord to set
     */
    public void setCoord(Coordonnee coord) {
        this.coord = coord;
    }

    /**
     * @return the coordPixel
     */
    public Coordonnee getCoordPixel() {
        return coordPixel;
    }

    /**
     * @param coordPixel the coordPixel to set
     */
    public void setCoordPixel(Coordonnee coordPixel) {
        this.coordPixel = coordPixel;
    }

    /**
     * @return the listePassagers
     */
    public ArrayList<Passager> getListePassagers() {
        return listePassagers;
    }

    /**
     * @param listePassagers the listePassagers to set
     */
    public void setListePassagers(ArrayList<Passager> listePassagers) {
        this.listePassagers = listePassagers;
    }

    /**
     * @return the hrDepart
     */
    public Date getHrDepart() {
        return hrDepart;
    }

    /**
     * @param hrDepart the hrDepart to set
     */
    public void setHrDepart(Date hrDepart) {
        this.hrDepart = hrDepart;
    }

    /**
     * @return the hrArrivee
     */
    public Date getHrArrivee() {
        return hrArrivee;
    }

    /**
     * @param hrArrivee the hrArrivee to set
     */
    public void setHrArrivee(Date hrArrivee) {
        this.hrArrivee = hrArrivee;
    }

    /**
     * @return the actif
     */
    public boolean isActif() {
        return actif;
    }

    /**
     * @param actif the actif to set
     */
    public void setActif(boolean actif) {
        this.actif = actif;
    }
    
    public ArrayList<SnapshotBus> getListSnapBus(){
        return this.listSnapShots;
    }
    
    
    
    
}
