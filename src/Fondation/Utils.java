//TODO : Valider conversion pixels/coordonnées et inversement
package Fondation;

import Domaine.Simulation;
import java.util.Random;
import InfrastructureMetier.Coordonnee;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import static java.lang.Math.pow;

/**
 *
 * @author alfo
 */
public final class Utils implements Serializable {

    Utils(){    
    }
    
    /**
     * En Millisecondes
     * @param min Temps minimal observe pour le parcours (en minutes)
     * @param mode Temps observe le plus souvent (en minutes)
     * @param max Temps maximal observe pour le parcours (en minutes)
     * @return un float representant un nombre aleatoire en minutes pour le parcours
     * @throws Fondation.SimulExcept
     */
    public static double genererTempsTriangulaire(double min, double mode, double max) throws SimulExcept{
        if (min > max || mode < min || mode > max){
            throw new SimulExcept(5, "Les donnees de calcul de la fonction de distri triangulaire sont invalides", "genererTempsTriangulaire");
        }
        
        Random randomGenerator = new Random();
        double randomSeed = randomGenerator.nextDouble();
        
        if (randomSeed < (mode-min)/(max-min)){
            return ((min + (double)Math.sqrt((randomSeed*(max-min)*(mode-min)))));
        }
        else{
             return ((max - (double) Math.sqrt((1-randomSeed)*(max-min)*(max-mode))));
        }
       
    }
    
    public static double MesurerDistanceCoordonnees(double lat1, double lon1, double lat2, double lon2){
        return ((double)Math.sqrt(pow(lat1 - lat2, 2)+pow(lon1-lon2, 2)));
    }
    
    public static Coordonnee TrouverCoord (Coordonnee p1, Coordonnee p2, double ratioDistSurDistTotale) throws SimulExcept{
        double lat = (1-ratioDistSurDistTotale) * p1.lat + ratioDistSurDistTotale * p2.lat;
        double lon = (1-ratioDistSurDistTotale) * p1.lon + ratioDistSurDistTotale * p2.lon;
        
        return new Coordonnee(lat, lon);
    }
    
    /**
     * Converti des coordonnées pixels en coordonnées géographique
     * @param x : Coordonnées X (pixel)
     * @param y : Coordonnées Y (pixel)
     * @return une nouvelle instance de coordonnées représentant des coordonnées géographiques
     * @throws SimulExcept 
     */
    public static Coordonnee convertPixelToCoord(double x, double y) throws SimulExcept{   
        //On défini le range couvert par l'application sur la carte
        double xGauche = -71.446200;
        double xDroit = -71.133000;
        double yHaut = 46.834900;
        double yBas = 46.683900;
        
        //Récupère les totaux en x et y (coordonnées géographiques
        double totalXCoord = (double)Math.round((xDroit - xGauche)*1000000d)/1000000d;
        double totalYCoord = (double)Math.round((yHaut-yBas)*1000000d)/1000000d;
        
        //Trouve le ratio en pixels
        double ratioXSurCarte = x/2000;//2000Pixels en X (dans UI)
        double ratioYSurCarte = y/1000;//1000Pixels en Y (dans UI)
        
        //Applique ce ratio aux coordonnées géographiques
        double coordLat = (double)Math.round((xGauche + (ratioXSurCarte*totalXCoord))*1000000d)/1000000d;
        double coordLon = (double)Math.round((yHaut-(ratioYSurCarte*totalYCoord))*1000000d)/1000000d;
        
        Coordonnee coordConvertie = new Coordonnee(coordLat,coordLon);
        //retourne la coordonnées en coordonnées géographique
        return coordConvertie;
    }
    
    /**
     * Converti des coordonnées géographiques en pixels
     * @param coord : Coordonnées géographiques à convertir
     * @return Instance de coordonnées en pixels
     * @throws SimulExcept 
     */
    public static Coordonnee convertCoordToPixel (Coordonnee coord) throws SimulExcept{
        //On défini le range couvert par l'application sur la carte
        double xGauche = -71.446200;
        double xDroit = -71.133000;
        double yHaut = 46.834900;
        double yBas = 46.683900;
        double xRangePixel = 2000;
        double yRangePixel = 1000;
        
        //Récupère les valeurs
        double xCoord = coord.getLat();
        double yCoord = coord.getLon();
        
        //Récupère le total en X et Y (coordonnées géographique)
        double totalXCoord =  (double)Math.round((xDroit - xGauche)*1000000d)/1000000d;
        double totalYCoord = (double)Math.round((yHaut-yBas)*1000000d)/1000000d;
        
        //On trouve le ratio de la coordonnées sur le total
        double ratioXCoord = -((double)Math.round(((xGauche - xCoord)/totalXCoord)*1000000d)/1000000d);
        double ratioYCoord = (double)Math.round(((yHaut-yCoord)/totalYCoord)*1000000d)/1000000d;
        
        //Récupère la valeur selon le range en x et y et le ratio obtenu
        double coordX = xRangePixel*ratioXCoord;
        double coordY = yRangePixel*ratioYCoord;
        return new Coordonnee(coordX, coordY);
    }
    
    public static void saveSim(String path, Simulation sim) throws FileNotFoundException, IOException{
        
        //File file = new File(path);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
        
        oos.writeObject(sim);
        oos.flush();
        oos.close();
        
    }
    
    public static Simulation loadSim(String path) throws ClassNotFoundException, IOException{
        
        //File file = new File(path);
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
        
        Simulation simLoad = (Simulation) ois.readObject();
        
        return simLoad;
        
    }
}