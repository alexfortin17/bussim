/*
 * Ce code est la propriete de l'empereur Alexandre Fortin
 * Each line should be prefixed with  * 
 */
package Fondation;


import java.util.Date;

import Application.Application;
import Domaine.Simulation;
import Domaine.ReseauAutobus;
import Domaine.Graphe;
import Domaine.Statistique;
import Domaine.uiExcept;
import InfrastructureMetier.Circuit;
/**
 *
 * @author Admin
 */
public class Factory {
    /**
     * Créateur d'objet simulation
     * @param unReseau : Réseau d'autobus
     * @param debut : heure de début
     * @param fin : heure de fin
     * @return une nouvelle instance d'objet simulation
     */
    public static Simulation CreerSim(ReseauAutobus unReseau, Date debut, Date fin, String nom) throws SimulExcept, InterruptedException{
        
        Simulation uneSim = new Simulation(unReseau, debut, fin, nom);
        return uneSim;
    }
    
    /**
     * Créateur d'objet RéseauAutobus
     * @param carte : La carte
     * @return une nouvelle instance de reseauAutobus
     */
    public static ReseauAutobus CreerReseau(Graphe carte){
        ReseauAutobus unReseau = new ReseauAutobus(carte);
        return unReseau;
    }
    
    /**
     * Créateur d'objet de type graphe
     * @return une nouvelle instance de graphe
     */
    public static Graphe CreerGraphe(){
        return new Graphe();
    }
    
    /**
     * Création d'objet de type application
     * @return une nouvelle instance d'objet application
     */
    public static Application creerApplication(){
        return new Application();
    }
    
    public static Statistique creerStatistique(String nom, double min, double moy, double max){
        
        return new Statistique(nom, min, moy, max);
        
    }
}