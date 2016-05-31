/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InfrastructureMetier;
import Fondation.SimulExcept;
import Fondation.Utils;
import java.io.Serializable;

/**
 *
 * @author alfo
 */
public class Segment implements Serializable{
    private Point depart;
    private Point arrivee;
    private double distance;
    private double tempsMin;
    private double tempsMode;
    private double tempsMax;
    private double tempsTransitSimulation = -1;
  
  /**
   * Constructeur de segmetn
   * @param dep : Point de départ
   * @param arr : Point d'arrivée
   * @param tempsMin : Temps minimal
   * @param tempsMode : Temps modal
   * @param tempsMax  : Temps maximal
   */
  public Segment(Point dep, Point arr, double tempsMin,
          double tempsMode, double tempsMax){
      this.depart = dep;
      this.arrivee = arr;
      this.distance = Utils.MesurerDistanceCoordonnees(dep.getCoordPixels().lat, dep.getCoordPixels().lon, arr.getCoordPixels().lat, arr.getCoordPixels().lon); 
      this.tempsMin = tempsMin;
      this.tempsMode = tempsMode;
      this.tempsMax = tempsMax;
  }
  
  /**
   * Constructeur copie de Segment
   * @param seg 
   */
  public Segment(Segment seg){
      this.arrivee = seg.arrivee;
      this.depart = seg.depart;
      this.distance = seg.distance;
      this.tempsMin = seg.tempsMin;
      this.tempsMode = seg.tempsMode;
      this.tempsMax = seg.tempsMax;
      this.tempsTransitSimulation = seg.tempsTransitSimulation;
  }

  /**
   * Accesseur point de depart
   * @return Point de depart
   */
  public Point getDepart(){
      return this.depart;
  }
  
  /**
   * Accesseur point d'arrivée
   * @return : Point d'arrivée
   */
  public Point getArrivee(){
      return this.arrivee;
  }
  
  /**
   * Accesseur distance segment
   * @return : Distance du segment
   */
  public double getDistance(){
      return this.distance;
  }
  
  /**
   * Accesseur temps max
   * @return Double représentant le temps maximal pour franchir ce segment
   */
  public double getTempsMax(){
      return this.tempsMax;
  }
  
  /**
   * Accesseur temps modal
   * @return Double représentant le temps modal pour franchir ce segment
   */
  public double getTempsMode(){
      return this.tempsMode;
  }
  
  /**
   * Accesseur temps minimal
   * @return : Double représentant le temps minimal
   */
  public double getTempsMin(){
      return this.tempsMin;
  }
  
  /**
   * Permet de d'acceder au temps de transit du segment pour la simulation
   * @return double représentant le temps de transit
   * @throws SimulExcept 
   */
  public double getTempsTransitSimulation() throws SimulExcept{
      if(this.tempsTransitSimulation == -1){
          throw new SimulExcept(24, "Le temps de transit simuler n'a pas ete calcule", "getTempsTransitSimulation");
      }
      return this.tempsTransitSimulation;
  }
  
  /**
   * Méthode qui permet de definir le point de depart du segment
   * @param depart : Point de depart
   */
  public void setDepart(Point depart){
      this.depart = depart;
  }
  
  /**
   * Méthode qui permet de définir le point d'arrivée du segment
   * @param arrivee : Point d'arrivée
   */
  public void setArrivee(Point arrivee){
      this.arrivee = arrivee;
  }
  
  /**
   * Méthode qui permet de définir la distance du segment
   * @param dist : double représentant la distance du segment
   */
  public void setDistance(double dist){
      this.distance = dist;
  }
  
  /**
   * Méthode qui permet de définir le temps minimal
   * @param min : Temps minimal à définir
   */
  public void setTempsMin(double min){
      this.tempsMin = min;
  }
  
  /**
   * Méthode qui permet de définir le temps modal 
   * @param mode : Temps modal à définir
   */
  public void setTempsMode(double mode){
      this.tempsMode = mode;
  }
  
  /**
   * Méthode qui permet de définir le temps maximal
   * @param max : Temps maximal à définir
   */
  public void setTempsMax(double max){
      this.tempsMax = max;
  }
  
  /**
   * Méthode qui permet de définir le temps transit
   * @param temps : Temps transit à définir
   */
  public void setTempsTransit(double temps){
      this.tempsTransitSimulation = temps;
  }
  
  /**
   * Calcul le temps de transit 
   * @throws SimulExcept 
   */
  public void CalculTempsTransit()throws SimulExcept{
      this.tempsTransitSimulation = Utils.genererTempsTriangulaire(tempsMin, tempsMode, tempsMax);
  }
  
}
