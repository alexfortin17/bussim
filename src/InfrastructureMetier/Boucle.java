/*
 * Ce code est la propriete de l'empereur Alexandre Fortin
 * Each line should be prefixed with  * 
 */
package InfrastructureMetier;

import Fondation.SimulExcept;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author alfo
 */
public class Boucle extends Circuit implements Serializable{
    
    //Variables
    ArrayList<Bus> listBus = new ArrayList();
    
    /**
     * Constructeur de boucle
     * @param nom Nom du circuit
     * @param listPoints Liste de points du circuit
     * @param min Temps minimum
     * @param mode Temps modal
     * @param max Temps maximal
     * @throws SimulExcept 
     */
    public Boucle(String nom, ArrayList<Point> listPoints, double min, double mode, double max, Point src) throws SimulExcept{
        super(nom, listPoints, min, mode, max, src);
        this.setLoop(true);
    }
    
    /**
     * Constructeur de boucle avec capaciteMax d'autobus
     * @param nom Nom du circuit
     * @param listPoints Liste de points du circuit
     * @param min temps minimal
     * @param mode temps modal
     * @param max temps maximal
     * @param capaciteMaxCircuit capacite maximale du circuit
     * @throws SimulExcept 
     */
    public Boucle(String nom, ArrayList<Point> listPoints, double min, double mode, double max, int capaciteMaxCircuit, Point src) throws SimulExcept{
        super(nom, listPoints, min, mode, max, capaciteMaxCircuit, src);
        this.setLoop(true);
    }
    
    /**
     * Permet d'ajouter un bus au circuit
     * @param bus
     * @throws SimulExcept 
     */
    public void AddBus(Bus bus) throws SimulExcept{
        if(bus == null){
            throw new SimulExcept(1, "Dereferencement d'un objet null AddBus", "AddBus");
        }
        
        listBus.add(bus);
        
           
    }   
    
    public ArrayList<Bus> getListBus(){
        return listBus;
    }
    
     public ArrayList<Point> getCircdepuisSource(){
         ArrayList<Point> newList = new ArrayList();
         
         int indexSrc = this.getListPointsCircuit().indexOf(this.getSource());
         
         for(int i  = indexSrc; i < getListPointsCircuit().size() - 1; i++){//on doit ignorer le dernier element qui est le meme que le 1er
             newList.add(getListPointsCircuit().get(i));
         }
         
         for(int j = 0; j <= indexSrc; j++){//on doit mettre le point src en double (debut et fin) pour boucler la boucle
             newList.add(getListPointsCircuit().get(j));
         }
         
         return newList;
     }
    
 
}
