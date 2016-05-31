/*
 * Ce code est la propriete de l'empereur Alexandre Fortin
 * Each line should be prefixed with  * 
 */
package InfrastructureMetier;


import Fondation.SimulExcept;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author alfo
 */
public class AllerSimple extends Circuit implements Serializable{
    
    //Variables
    
    ArrayList<Bus> fileBus;
    
    /**
     * Constructeur sans capacité maximal de bus
     * @param nom : Nom du circuit
     * @param listPoints Liste de points du circuit
     * @param min temps minimal
     * @param mode temps modal
     * @param max temps maximal
     * @throws SimulExcept 
     */
    public AllerSimple(String nom, ArrayList<Point> listPoints, double min, double mode, double max, Point src) throws SimulExcept{
        super(nom, listPoints, min, mode, max, src);
        this.setLoop(false);
        fileBus = new ArrayList();
    }
    
    /**
     * Constructeur avec capacite maximum de bus
     * @param nom nom du circuit
     * @param listPoints liste de points du circuit
     * @param min temps minimal du circuit
     * @param mode temps modal du circuit
     * @param max temps maximal du circuit
     * @param src point source du circuit
     * @param capaciteMaxCircuit
     * @throws SimulExcept 
     */
    public AllerSimple(String nom, ArrayList<Point> listPoints, double min, double mode, double max, int capaciteMaxCircuit, Point src) throws SimulExcept{
        super(nom, listPoints, min, mode, max, capaciteMaxCircuit, src);
        this.setLoop(false);
        fileBus = new ArrayList();
    }
    
    /**
     * Permet d'ajouter un autobus au circuit
     * @param bus le bus à ajouter
     * @throws SimulExcept
     * @throws InterruptedException 
     */
    public void AddBus(Bus bus) throws SimulExcept, InterruptedException{
        if(bus == null){
            throw new SimulExcept(1, "Dereferencement d'un objet null AddBus", "AddBus");
        }
       
        fileBus.add(bus);
       
    }
    
   /*
    public void delBus() throws SimulExcept{
        if(fileBus.size() == 0){
            throw new SimulExcept(19, "Il n'y a pas de bus en enlever sur le circuit", "delBus");
        }
        fileBus.poll();
    }
    */
    
    public ArrayList<Bus> getListBus(){
        return this.fileBus;
    }
    
    public ArrayList<Point> getCircdepuisSource(){
       ArrayList<Point> newList = new ArrayList();
       
       if(this.getListPointsCircuit().get(0) == this.getListPointsCircuit().get(this.getListPointsCircuit().size()-1)){//si c'est une oop en aller simple
        int indexSrc = this.getListPointsCircuit().indexOf(this.getSource());
         
         for(int i  = indexSrc; i < getListPointsCircuit().size() - 1; i++){//on doit ignorer le dernier element qui est le meme que le 1er
             newList.add(getListPointsCircuit().get(i));
         }
         
         for(int j = 0; j <= indexSrc; j++){//on doit mettre le point src en double (debut et fin) pour boucler la boucle
             newList.add(getListPointsCircuit().get(j));
         }
       }
       
       else{//si c'est une allee simple standard
            int i =0;
       while(getListPointsCircuit().get(i) != this.getSource()){
           i++;
       }
       for(int j = i; j < this.getListPointsCircuit().size(); j++){
           newList.add(this.getListPointsCircuit().get(j));
       }
       }
       
      
       
       return newList;
               
    }
    
    
}
