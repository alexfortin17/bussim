/*
 * Ce code est la propriete de l'empereur Alexandre Fortin
 * Each line should be prefixed with  * 
 */
package Domaine;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class Statistique implements Serializable{
    
    String nomIti;
    double min;
    double moy;
    double max;

    public Statistique(String nom, double min, double moy, double max) {
        this.nomIti = nom;
        this.min = min;
        this.moy = moy;
        this.max = max;
    }
    
    public String getNomIti(){
        return this.nomIti;     
    }
    
    public double getMin(){
        return this.min;
    }
    
    public double getMoyenne(){
        return this.moy;
    }
    
    public double getMax(){
        return this.max;
    }
    
    
}
