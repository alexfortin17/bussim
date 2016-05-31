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
 *
 * @author alfo
 */
public class Passager implements Serializable {

    /**
     * @return the compteurPassTotal
     */
    public static int getCompteurPassTotal() {
        return compteurPassTotal;
    }

    /**
     * @param aCompteurPassTotal the compteurPassTotal to set
     */
    public static void setCompteurPassTotal(int aCompteurPassTotal) {
        compteurPassTotal = aCompteurPassTotal;
    }
    
    //Attributs
    private int id;
    private static int compteurPassTotal = 0;
    private Itineraire itineraire;
    private Date hrDepart;
    private Date hrArrivee;
     private ArrayList<SnapshotPassager> listSnapShots = new ArrayList();
    //private Arret ArretCourant = null;
    //private Bus BusCourant = null;
    
 
    public Passager(Itineraire iti){
        compteurPassTotal++;
        this.id = compteurPassTotal;
        this.itineraire = iti;
                
    }
    
    public int getPassTotal(){
        return this.getCompteurPassTotal();
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the itineraire
     */
    public Itineraire getItineraire() {
        return itineraire;
    }

    /**
     * @param itineraire the itineraire to set
     */
    public void setItineraire(Itineraire itineraire) {
        this.itineraire = itineraire;
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
    
    public ArrayList<SnapshotPassager> getListSnapPass(){
        return this.listSnapShots;
    }

   
}
