/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domaine;
import Fondation.Factory;
import InfrastructureMetier.Segment;
import InfrastructureMetier.Coordonnee;
import InfrastructureMetier.Circuit;
import InfrastructureMetier.Point;
import InfrastructureMetier.Bus;
import Fondation.SimulExcept;
import Fondation.Utils;
import InfrastructureMetier.AllerSimple;
import InfrastructureMetier.Arret;
import InfrastructureMetier.Itineraire;
import InfrastructureMetier.Passager;
import InfrastructureMetier.SnapShotPassArret;
import InfrastructureMetier.SnapShotPassBus;
import InfrastructureMetier.SnapshotBus;
import InfrastructureMetier.SnapshotPassager;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
//import sun.awt.X11.XConstants;

/**
 *
 * @author alfo
 */
public class Simulation implements Serializable{
    //Variables
    ReseauAutobus reseau;
    String Nom;
    Date debut;
    Date fin;
    ArrayList<Bus> listTotalBus = new ArrayList();
    ArrayList<Passager> listTotalPass = new ArrayList();
    
    
    public Simulation(ReseauAutobus reseau, Date deb, Date fin, String nom) throws SimulExcept, InterruptedException{
        this.reseau = reseau;
        this.debut = deb;
        this.fin = fin;
        this.Nom = nom;
        reseau.getCarte().CalculTempsTransitSegments();
        
        genererListeTotaleBus(deb, fin);
        
        for(Circuit circ:getReseau().getListCircuits()){
                this.listTotalBus.addAll(circ.getListBus());
        }
        
        genererListeTotalePass(deb, fin);
        
        for(Itineraire itin:getReseau().getListItineraires()){
                this.listTotalPass.addAll(itin.getListPass());
        }
        
        genererStatsIti();
    }
    
    public ReseauAutobus getReseau(){
        return reseau;
    }
    
    public Date getTempsDebSim(){
        return debut;
    }
    
    public Date getTempsFinSim(){
        return fin;
    }
    
    public String getNomSim(){
        return this.Nom;
    }
    
    
    /*public final void EvalTempsTransitSegmentSim(){
         try{
            for(Point pt : reseau.getCarte().ListPoint){
                for(Segment seg : pt.getListSegment()){
                     seg.setTempsTransit(Utils.genererTempsTriangulaire(seg.getTempsMin(), seg.getTempsMode(), seg.getTempsMax()));
             }  
            } 
         }
         catch(Exception e){
             //do somethin
         }
         

  }*/
    
    public final void genererListeTotaleBus(Date hrDebSim, Date hrFinSim) throws SimulExcept, InterruptedException{
        for(Circuit circ: this.getReseau().getListCircuits()){
            genererListeBus(hrDebSim, hrFinSim, circ);
        }
        
    }
    
    public long getTempsTraverserAS(Circuit circ) throws SimulExcept{
        ArrayList<Point> listPts = circ.getCircdepuisSource();
        long tempsTotal = 0;
        
        for(int i = 0; i < listPts.size() - 1; i++){
            Segment seg = getReseau().getCarte().GetSegment(listPts.get(i), listPts.get(i+1));
            tempsTotal = Math.round(60000*seg.getTempsTransitSimulation()) + tempsTotal;
        }
        
        return tempsTotal;
        
    }
    
    public void genererListeTotalePass(Date debSim, Date finSim) throws SimulExcept{
        for(Itineraire itin: this.getReseau().getListItineraires()){
            genererListePassagers(debSim, finSim, itin);
        }
    }
    
    public void genererListePassagers(Date debSim, Date finSim, Itineraire iti) throws SimulExcept{
        
        Date tempsTemp = new Date((long)(debSim.getTime() + iti.getTempsArrPass1()*60000));
        
        while (tempsTemp.before(finSim)) {
            
            Passager pass = new Passager(iti);
            pass.setHrDepart(tempsTemp);
            pass.getListSnapPass().add(new SnapShotPassArret(tempsTemp, ((Arret)iti.getListPts().get(0))));//premier snapshot a l'apparition du passager
            Date tempsIntermed = tempsTemp;    
            
            loopMain:
            for(int i = 0; i < iti.getListCirc().size(); i++){
                int ptDepSurCirc;
                
                if(i==0){//si c le depart de l'itineraire
                    ptDepSurCirc = iti.getListCirc().get(i).getCircdepuisSource().indexOf(iti.getListPts().get(0));
                    
                }
                else{
                    ptDepSurCirc = iti.getListCirc().get(i).getCircdepuisSource().indexOf(iti.getListPivot().get(i-1));
                }
                
                if(iti.getListCirc().get(i) instanceof AllerSimple){
                    boolean value=false;//oli
                    boolean value2=false;//oli
                    construcListSnap1Passager:
                    for(Bus bus:iti.getListCirc().get(i).getListBus()){//determine quel bus il va prendre
                        if(bus.getListSnapBus().get(ptDepSurCirc).getTimeStamp().after(tempsIntermed) || bus.getListSnapBus().get(ptDepSurCirc).getTimeStamp().equals(tempsIntermed)){
                            value=true;
                            if(pass.getListSnapPass().get(pass.getListSnapPass().size()-1) instanceof SnapShotPassArret){//OLI
                                pass.getListSnapPass().add(new SnapShotPassBus(bus.getListSnapBus().get(ptDepSurCirc).getTimeStamp(), bus));//ajoute un snapshot au moment ou un passager entre ds bus
                            }
                            if(i == iti.getListCirc().size() - 1){//si je suis sur le dernier circuit de l'itineraire
                                Point ptTerminus = iti.getListPts().get(iti.getListPts().size()-1);
                                for(SnapshotBus snap:bus.getListSnapBus()){
                                    if(snap.getPtCourant() == ptTerminus && snap.getTimeStamp().after(pass.getListSnapPass().get(pass.getListSnapPass().size() -1).getTimeStamp())){
                                        pass.getListSnapPass().add(new SnapShotPassArret(snap.getTimeStamp(), (Arret)snap.getPtCourant()));
                                        tempsIntermed = snap.getTimeStamp();
                                        value2=true;//oli
                                        break construcListSnap1Passager;
                                    }
                                }
                            }
                            
                            else{//si je suis sur un pivot
                                Point pivot = iti.getListPivot().get(i);
                                for(SnapshotBus snap:bus.getListSnapBus()){
                                    if(snap.getPtCourant() == pivot && snap.getTimeStamp().after(pass.getListSnapPass().get(pass.getListSnapPass().size() -1).getTimeStamp())){
                                        pass.getListSnapPass().add(new SnapShotPassArret(snap.getTimeStamp(), (Arret)snap.getPtCourant()));
                                        tempsIntermed = snap.getTimeStamp();
                                        value2=true;//oli
                                        break construcListSnap1Passager;
                                    }
                                }
                            }
                        }
                    }
                    if(value==false && value2==false){//oli
                        break;
                    }
                    
                    
                }
                    
                else{//si c'est une loop
                    
                    
                    ArrayList<SnapshotBus> listTimeStampPossible = new ArrayList();
                    construcListSnap1Passager:
                    for(Bus bus:iti.getListCirc().get(i).getListBus()){//determine quel bus il va prendre
                        //listTimeStampPossible.clear();
                        for(int k = 0; k < bus.getListSnapBus().size(); k++){
                            if(k % (iti.getListCirc().get(i).getCircdepuisSource().size() - 1) == ptDepSurCirc){//si
                                if(bus.getListSnapBus().get(k).getTimeStamp().after(tempsIntermed) || bus.getListSnapBus().get(k).getTimeStamp().equals(tempsIntermed)){
                                    listTimeStampPossible.add(bus.getListSnapBus().get(k));//on add toutes les possibilites ds une liste temporaire
                                }
                            }
                        }
                    }
                    
                    boolean value1=false;
                    boolean value2=false;
                    if(listTimeStampPossible.isEmpty() == false){
                        final Comparator<SnapshotBus> comp = (b1, b2) -> Long.compare(b1.getTimeStamp().getTime(), b2.getTimeStamp().getTime());
                        SnapshotBus snapMin = listTimeStampPossible.stream().min(comp).get();//va chercher le snapshot de la liste ayant le plus petit timestamp
                        
                        
                        Bus busUtil = null;
                        for(Bus bus:iti.getListCirc().get(i).getListBus()){
                            if(bus.getListSnapBus().contains(snapMin)){
                                busUtil = bus;
                                value1=true;//oli
                                pass.getListSnapPass().add(new SnapShotPassBus(snapMin.getTimeStamp(), busUtil));//oli - déplacé cet ligne de code qui était à l'extérieur de la boucle (ça ne change rien qu'elle soit dedans) ajoute un snapshot au moment ou un passager entre ds bus
                                break;
                            }
                        }

                        

                        if(i == iti.getListCirc().size() - 1){//si je suis sur le dernier circuit de l'itineraire
                            Point ptTerminus = iti.getListPts().get(iti.getListPts().size()-1);
                            for(SnapshotBus snap:busUtil.getListSnapBus()){
                                if(snap.getPtCourant() == ptTerminus && snap.getTimeStamp().after(pass.getListSnapPass().get(pass.getListSnapPass().size() -1).getTimeStamp())){//verif que le timestamp du bus a ce pt est apres le dernier snap du passager
                                    pass.getListSnapPass().add(new SnapShotPassArret(snap.getTimeStamp(), (Arret)snap.getPtCourant()));
                                    tempsIntermed = snap.getTimeStamp();
                                    value2=true;//oli
                                    break;//changement pour accomoder loop
                                    
                                }
                            }
                        }

                        else{//si je suis sur un pivot
                            Point pivot = iti.getListPivot().get(i);
                            for(SnapshotBus snap:busUtil.getListSnapBus()){
                                if(snap.getPtCourant() == pivot && snap.getTimeStamp().after(pass.getListSnapPass().get(pass.getListSnapPass().size() -1).getTimeStamp())){
                                    pass.getListSnapPass().add(new SnapShotPassArret(snap.getTimeStamp(), (Arret)snap.getPtCourant()));
                                    tempsIntermed = snap.getTimeStamp();
                                    value2=true;//oli
                                    break;
                                }
                            }
                            
                        }
                    }
                    else{//oli
                        break;
                    }
                    
                    if(value1==false && value2==false){//oli
                        break;
                    }
                    
                }
                
                
            }
            
           
            pass.setHrArrivee(pass.getListSnapPass().get(pass.getListSnapPass().size()-1 ).getTimeStamp());
            iti.addPassager(pass);//on add le passager a la liste de son itineraire
            tempsTemp = NouvTempsDep(tempsTemp, iti);//on calcul un nouveau temps d'apparition pour le prochain passager
        }
    }
    
    
    public void genererListeBus(Date debSim, Date finSim, Circuit circ) throws SimulExcept, InterruptedException{
        
        Date tempsTemp = debSim;

        //On calcul d'abord le temps de debut et de fin pour ch bus et on determine en meme temps combien de bus seront cree
        while (tempsTemp.before(finSim)) {
            
            Date TempsArr = null;
            
        
            
            if(!circ.isLoop()){
                long TempsTrav = getTempsTraverserAS(circ);
                long tempsArrMilli = tempsTemp.getTime() + TempsTrav;
                TempsArr = new Date(tempsArrMilli);
                
            }

            
            //si pas de capacite maximum
            if(circ.getCapaciteMaxCircuit() == -1){
                circ.AddBus(new Bus(tempsTemp, TempsArr, circ));
            }
            else{
                 if(getNbBusEnRoute(circ, tempsTemp) < circ.getCapaciteMaxCircuit()){
                     circ.AddBus(new Bus(tempsTemp, TempsArr, circ));
                }
                
            }
            tempsTemp = NouvTempsDep(tempsTemp, circ);
   
        }
        
        ArrayList<Point> ListPtsCircuit = circ.getCircdepuisSource();
        
        
        if(!circ.isLoop()){
            
            for(Bus bus: circ.getListBus()){
                long hrDebUtil = bus.getHrDep().getTime();
                Segment segUtil = null;
                bus.getListSnapBus().add(new SnapshotBus(bus.getHrDep(), ListPtsCircuit.get(0)));//premier snapshot au moment de l'apparition du bus sur le circuit(depart)
                for (int i = 0; i < ListPtsCircuit.size()-1; i++){
                    segUtil = reseau.getCarte().GetSegment(ListPtsCircuit.get(i), ListPtsCircuit.get(i+1));
                    Date tempsArrNextPoint = new Date((long) (60000*segUtil.getTempsTransitSimulation()) + hrDebUtil);
                    /*if(tempsArrNextPoint.equals(this.getTempsFinSim())){//cas limite ou cest agale au millieme de sec pres
                        bus.getListSnapBus().add(new SnapshotBus(tempsArrNextPoint, ListPtsCircuit.get(i+1)));//snapshot de l'arrivee au point suivant
                        break;
                    }
                    /*if(bus.getHrArr() == null){
                        if (tempsArrNextPoint.after(this.getTempsFinSim())){
                            break;
                        }
                    }*/
                   
                    if(tempsArrNextPoint.after(bus.getHrArrivee())){
                        break;
                    }
                    
                    
                    
                    
                    bus.getListSnapBus().add(new SnapshotBus(tempsArrNextPoint, ListPtsCircuit.get(i+1)));//snapshot de l'arrivee au point suivant
                    hrDebUtil = hrDebUtil + (long) (60000*segUtil.getTempsTransitSimulation());
                
                }
            }
           
          
            
            
            
        }
        
        else{
            
            for(Bus bus: circ.getListBus()){
                long hrDebUtil = bus.getHrDep().getTime();
                Segment segUtil = null;
                boolean brise = false;
                bus.getListSnapBus().add(new SnapshotBus(bus.getHrDep(), ListPtsCircuit.get(0)));//premier snapshot au moment de l'apparition du bus sur le circuit(depart)

                while(brise == false){
                    for (int i = 0; i < ListPtsCircuit.size() - 1; i++){
                        if(i == ListPtsCircuit.size() - 2 ){
                            segUtil = reseau.getCarte().GetSegment(ListPtsCircuit.get(i), ListPtsCircuit.get(0));
                        }
                        else{
                            segUtil = reseau.getCarte().GetSegment(ListPtsCircuit.get(i), ListPtsCircuit.get((i+1)));
                        }

                        Date tempsArrNextPoint = new Date((long) (60000*segUtil.getTempsTransitSimulation()) + hrDebUtil);

                        /*if (tempsArrNextPoint.after(new Date(this.getTempsFinSim().getTime() + (long)(segUtil.getTempsTransitSimulation() * 60000)))){//pour s'assure davoir un dernier snapshot au moins egale au temps de finde sim
                            brise = true;
                            break;
                        }
                                */
                        if (bus.getListSnapBus().get(bus.getListSnapBus().size() - 1).getTimeStamp().after(this.getTempsFinSim()) || 
                                bus.getListSnapBus().get(bus.getListSnapBus().size() - 1).getTimeStamp().equals(this.getTempsFinSim())){//pour s'assure davoir un dernier snapshot au moins egale au temps de finde sim
                            brise = true;
                            break;
                        }
                        bus.getListSnapBus().add(new SnapshotBus(tempsArrNextPoint, ListPtsCircuit.get(i+1)));//snapshot de l'arrivee au point suivant
                        hrDebUtil = hrDebUtil + (long) (60000*segUtil.getTempsTransitSimulation());
                    }


                }
            }
        
    }
        
    }
    
    
    public void majListesPass(Date hr) throws SimulExcept{
        if(hr.before(debut) || hr.after(fin)){
            throw new SimulExcept(21, "La date specifie est hors du range de la simulation", "majListesPass");
        }
        
        for(Point pt:this.getReseau().getCarte().getListPoints()){//purge la liste de passager des arrets
            if(pt instanceof Arret){
                ((Arret)pt).getListPassagers().clear();
            }
        }
        
        for(Bus b:getListeTotaleBus()){//purge la liste de passager des bus
            b.getListePassagers().clear();
        }
        
        
        for(Itineraire it:this.getReseau().getListItineraires()){//pour ch itineraire
            for(Passager pas:it.getListPass()){//pour chaque passager de cet itineraire
                if((pas.getListSnapPass().get(0).getTimeStamp().before(hr) || pas.getListSnapPass().get(0).getTimeStamp().equals(hr)) &&
                        (pas.getListSnapPass().get(pas.getListSnapPass().size()-1).getTimeStamp().after(hr) || pas.getListSnapPass().size() < 3 + 2*(pas.getItineraire().getListPivot().size()))){
                   
                
                
                    ArrayList<SnapshotPassager> listSnap = new ArrayList();
                    listSnap = pas.getListSnapPass();
                    
                    if(pas.getListSnapPass().size() < 3 + 2*(pas.getItineraire().getListPivot().size())){
                        for(int i = 0; i < listSnap.size();i++){//on ne considere pas le dernier snap car le passager sera arrivee
                            if(i == listSnap.size()-1){
                                 SnapshotPassager snapUtil = listSnap.get(i);
                                if(snapUtil instanceof SnapShotPassArret){
                                    ((SnapShotPassArret)snapUtil).getArret().getListPassagers().add(pas);

                                }
                                else{
                                   ((SnapShotPassBus)snapUtil).getBus().getListePassagers().add(pas);
                                }

                                break;
                            }
                            if(listSnap.get(i+1).getTimeStamp().after(hr)){
                                SnapshotPassager snapUtil = listSnap.get(i);
                                if(snapUtil instanceof SnapShotPassArret){
                                    ((SnapShotPassArret)snapUtil).getArret().getListPassagers().add(pas);

                                }
                                else{
                                   ((SnapShotPassBus)snapUtil).getBus().getListePassagers().add(pas);
                                }

                                break;
                            }
                        }
                    }

                    else{
                        for(int i = 0; i < listSnap.size() - 1;i++){//on ne considere pas le dernier snap car le passager sera arrivee
                            if(listSnap.get(i+1).getTimeStamp().after(hr)){
                                SnapshotPassager snapUtil = listSnap.get(i);
                                if(snapUtil instanceof SnapShotPassArret){
                                    ((SnapShotPassArret)snapUtil).getArret().getListPassagers().add(pas);

                                }
                                else{
                                   ((SnapShotPassBus)snapUtil).getBus().getListePassagers().add(pas);
                                }

                                break;
                            }
                    }
                    }
                    

                }
            }
        }
        
        
    }
    
    
    
    
    
    
    
    
    public Coordonnee getEmplacement(Bus bus, Date hr) throws SimulExcept{
        if(hr.before(debut) || hr.after(fin)){
            throw new SimulExcept(21, "La date specifie est hors du range de la simulation", "getEmplacement");
        }
        
        Circuit circuitCourant = bus.getCircuit();
        Date hrDepBus = bus.getHrDep();
        
        ArrayList<Point> ListPtsCircuit = circuitCourant.getCircdepuisSource();
        
        
        
        if(hr.before(hrDepBus)){
            return ListPtsCircuit.get(0).getCoordPixels();
        }
        
        
        if (!circuitCourant.isLoop()){
            if(bus.getHrArr().before(this.getTempsFinSim()) && hr.after(bus.getHrArr())){
                return ListPtsCircuit.get(ListPtsCircuit.size()-1).getCoordPixels();
            }
        }
        
        Segment segCourant = null;
        long hrDebutSegCourant = 0;
        
        
            for(int i = 0; i < bus.getListSnapBus().size()-1; i++){
                if(bus.getListSnapBus().get(i+1).getTimeStamp().after(hr)||bus.getListSnapBus().get(i+1).getTimeStamp().equals(hr)){//si le snapshot au prochain poit  est apres l'hr d'interet
                    segCourant = this.getReseau().getCarte().GetSegment(bus.getListSnapBus().get(i).getPtCourant(), bus.getListSnapBus().get(i+1).getPtCourant());
                    hrDebutSegCourant = bus.getListSnapBus().get(i).getTimeStamp().getTime();
                    break;
                }                
            }
            return CalculCoordonneeBus(segCourant, hrDebutSegCourant, hr);
        
    }
    
    
        public ArrayList<Bus> getListeTotaleBus(){
            
            return this.listTotalBus;      
        }
        
        
        
        
        public int getNbBusEnRoute(Circuit circ, Date temps){
            int compteur = 0 ;
            
            if(circ.isLoop()){
                for(Bus bus:circ.getListBus()){
                    if(bus.getHrDep().before(temps)){
                        compteur++;
                    }
                }
            }
            else{
                for(Bus bus:circ.getListBus()){
                    if(bus.getHrArrivee() == null){
                        compteur++;//veut dire que le bus actuel n'a pas le temps d'arrivee donc est sur le circuit
                    }
                    else if(bus.getHrDep().before(temps) && bus.getHrArrivee().after(temps)){
                        compteur++;
                    }
                }
            }
            
            return compteur;
        }
        
        public void genererStatsIti() throws SimulExcept{
            for(Itineraire iti:this.getReseau().getListItineraires()){
                if(iti.getListPass().isEmpty()){
                    throw new SimulExcept(34, "La liste de passager doit avoir ete genere pour generer des statistiques", "genererStats");
                }
                
                ArrayList<Double> listTempsPourTrav = new ArrayList();
                for(Passager pass: iti.getListPass()){
                    if((pass.getHrArrivee().before(this.getTempsFinSim()) || pass.getHrArrivee().equals(this.getTempsFinSim())) 
                            && pass.getListSnapPass().size() == 3 + 2*(pass.getItineraire().getListPivot().size())){
                        listTempsPourTrav.add((double)(pass.getHrArrivee().getTime()) - (double)(pass.getHrDepart().getTime()));
                    }
                }
                if(listTempsPourTrav.isEmpty()){
                    iti.setStatMin(0);
                    iti.setStatMoy(0);
                    iti.setStatMax(0);
                }
                else{
                     double mini = Collections.min(listTempsPourTrav);// /60000 car elles sont en millisec et ont les veux en minutes
                    iti.setStatMin(round(mini/60000, 2));
                    double maxi = Collections.max(listTempsPourTrav);
                    iti.setStatMax(round(maxi/60000, 2));// /60000 car elles sont en millisec et ont les veux en minutes
                
                
                
                    //Calcul de la moyenne 
                    double sum = 0;
                    for(double d:listTempsPourTrav){
                        sum = sum + d;
                    }         
                    //on met sum en minutes
                    iti.setStatMoy(round((sum/(listTempsPourTrav.size()))/60000, 2));// /60000 car elles sont en millisec et ont les veux en minutes
                }
               
                
                
            }      
    }
        
    public ArrayList<Statistique> getStatsIti(){
        
           ArrayList<Statistique> listRetour = new ArrayList();
           
           for(Itineraire iti: this.getReseau().ListItineraire){
               listRetour.add(Factory.creerStatistique(iti.getNomIti(), iti.getStatMin(), iti.getStatMoy(), iti.getStatMax()));
           }
           
           return listRetour;

    }
    
        
        
    
    private Coordonnee CalculCoordonneeBus(Segment segCourant, long hrDebSeg, Date hrBus) throws SimulExcept{
        if (segCourant == null){
                throw new SimulExcept(1, "Dereferencement d'un objet null getEmplacement", "getEmplacement");
            }
            double DistSegment= Utils.MesurerDistanceCoordonnees(segCourant.getDepart().getCoordPixels().lat, segCourant.getDepart().getCoordPixels().lon,
                    segCourant.getArrivee().getCoordPixels().lat, segCourant.getArrivee().getCoordPixels().lon);

            long hrArrSeg = hrDebSeg + (long) (60000*segCourant.getTempsTransitSimulation());
            long TempsTotal = hrArrSeg - hrDebSeg;
            long TempsDebutAPointChercher = hrBus.getTime() - hrDebSeg;
            double RatioChercheTotal = (double)TempsDebutAPointChercher/(double)TempsTotal;

            double DistDebAChercher = RatioChercheTotal * DistSegment;

            double ratioDistSurDistTotale = DistDebAChercher/DistSegment;

            Coordonnee coordChercher = Utils.TrouverCoord(segCourant.getDepart().getCoordPixels(), segCourant.getArrivee().getCoordPixels(), ratioDistSurDistTotale);

            return coordChercher;
    }


    private Date NouvTempsDep(Date tempsX, Circuit circ) throws SimulExcept{
    Double decalageMinInit = Utils.genererTempsTriangulaire(circ.getMin(), circ.getMode(), circ.getMax());
    long decalMillisecInit = (Math.round(decalageMinInit * 60000));
    Date nouvTempsDep = new Date(tempsX.getTime() + decalMillisecInit);

    return nouvTempsDep;
    }

    private Date NouvTempsDep(Date tempsX, Itineraire iti) throws SimulExcept{
    Double decalageMinInit = Utils.genererTempsTriangulaire(iti.getMin(), iti.getMode(), iti.getMax());
    long decalMillisecInit = (Math.round(decalageMinInit * 60000));
    Date nouvTempsDep = new Date(tempsX.getTime() + decalMillisecInit);

    return nouvTempsDep;
    }
    
    
    private static double round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();

    BigDecimal bd = new BigDecimal(value);
    bd = bd.setScale(places, RoundingMode.HALF_UP);
    return bd.doubleValue();
}



}
        

      
    

