/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domaine;

import InfrastructureMetier.*;
import Fondation.SimulExcept;
import Fondation.Utils;
import java.util.ArrayList;
import java.io.Serializable;

/**
 *
 * @author alfo
 */
public class Graphe implements Serializable{
    
    //Variables
    public ArrayList<Point> ListPoint;
    public int nbPoints;
    public int nbSegments;
        
//------------------------------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------------------------------
//$$$$$$$$4$$$$$$$$$$$$$$$$$$$$$$----Methodes Publiques-----$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$    
    
    
//--------------------------------------------------------------------------------------------------------------------------------------------------    
    /**
     * Constructeur de Graphe (vide)
     */
    public Graphe(){
        this.nbPoints=0;      
        this.nbSegments=0;
        this.ListPoint = new ArrayList();
    }
    
    /**
     * Constructeur de copie de graphe
     * @param grapheACopier: Graphe à copier 
     */
    public Graphe(Graphe grapheACopier){
        this.ListPoint = grapheACopier.ListPoint;
        this.nbPoints = grapheACopier.nbPoints;
        this.nbSegments = grapheACopier.nbSegments;
    }

    
        
//--------------------------------------------------------------------------------------------------------------------------------------------------
    /**
     * Permet d'ajouter un point au graphe
     * @param lat: Latitude du point
     * @param lon Longitude du point
     * @param nom: Nom du point (peut être vide)
     * @param arret: Indique si le point est un arrêt (true) ou une intersection (false)
     * @throws SimulExcept 
     */
    public void ajoutArret(Coordonnee coord, String nom) throws SimulExcept{
        
       
        //***********************Verifications**************************************//
        if(coord.lon < 0 && coord.lat < 0 ){
               throw new SimulExcept(6, "Une coordonnee ne peut etre neg", "ajoutPoint");
            }
        if (NomPointExiste(nom)){
               throw new SimulExcept(2, "Ce nom existe deja", "ajoutPoint");
        }
        
      
        //******************Ajout du point a la liste de points************************//
        
        
            Arret NouvArret = new Arret(new Coordonnee(coord.lat, coord.lon), nom);
            ListPoint.add(NouvArret);//ajout du nouvel arret a la liste de points
            nbPoints++;
      }
    
    public void ajoutIntersec(Coordonnee coord, String nom) throws SimulExcept{
        //***********************Verifications**************************************//
        if(coord.lon < 0 && coord.lat < 0 ){
               throw new SimulExcept(6, "Une coordonnee ne peut etre neg", "ajoutPoint");
            }
        if (NomPointExiste(nom)){
               throw new SimulExcept(2, "Ce nom existe deja", "ajoutPoint");
        }
 
        
        
        Intersection NouvInter = new Intersection(new Coordonnee(coord.lat, coord.lon), nom);
        ListPoint.add(NouvInter);//ajout du nouvel intersec a la liste de points
        nbPoints++;
    }
    
    public void ajoutPoint(Point pt){
     
        this.getListPoints().add(pt);
        nbPoints++;
    }
    
    
  
    
  //--------------------------------------------------------------------------------------------------------------------------------------------------
    /**
     * Permet de modifier le nom d'un point dont le ID est passé en paramètre
     * @param idPoint: ID du point à modifier
     * @param Nom: Nouveau nom du point
     * @throws SimulExcept 
     */
     public void modifNomPoint(int idPoint, String Nom) throws SimulExcept{
         
         //****************************Verifications******************************************//
        
        if(NomPointExiste(Nom)){//si nom existe deja, exception
            throw new SimulExcept(2, "Nom de point deja existant", "modifPoint");
        }
        //***********************Modification du point**********************************
        getPointDeListe(idPoint).setNom(Nom);
     }
     
     
     //--------------------------------------------------------------------------------------------------------------------------------------------------
     /**
      * Permet de modifier le nom d'un point passé en paramètre
      * @param point: Point à modifier
      * @param Nom: Nouveau nom de ce point
      * @throws SimulExcept 
      */
     public void modifNomPoint(Point point, String Nom) throws SimulExcept{
         
         //****************************Verifications******************************************//
            
        if(NomPointExiste(Nom)){//si nom existe deja, exception
            throw new SimulExcept(2, "Nom de point deja existant", "modifPoint");
        }
        //***********************Modification du point**********************************
        point.setNom(Nom);
     }

     //------------------------------------------------------------------------------------------------------------------------
     /**
      * Permet de déplacer un point dans le graphe
      * @param idPt: ID du point à déplacer
      * @param lat: Nouvelle coordonnée de latitude
      * @param lon: Nouvelle coordonnée de longitude
      * @throws SimulExcept 
      */
     public void deplacerPoint(int idPt, double lat, double lon)throws SimulExcept{
         Point PtDeplacer = getPointDeListe(idPt);
         
         PtDeplacer.getCoord().lat = lat;
         PtDeplacer.getCoord().lon = lon;
         
         //Modifie la longeurs des segments sortants
        PtDeplacer.getListSegment().stream().parallel().forEach(seg -> seg.setDistance(Utils.MesurerDistanceCoordonnees(PtDeplacer.getCoord().lat,
                PtDeplacer.getCoord().lon, seg.getArrivee().getCoord().lat, seg.getArrivee().getCoord().lon)));
         
         /** @TODO  recalculer les segments entrants (multithreading?)*/
        //On parcours la liste de points, pour chaque point, on vérifie chaque segment qui débute par ce point.  Ensuite, on vérifie si le point d,arrivée
        //de ce segment est le point qui est déplacé.  Si oui, on recalcul la longueur du segment.
         for (Point ListPoint1 : ListPoint) {
             for (Segment ListSegment : ListPoint1.getListSegment()) {
                 if (ListSegment.getArrivee().getId() == idPt) {
                     ListSegment.setDistance(Utils.MesurerDistanceCoordonnees(ListSegment.getDepart().getCoord().lat, ListSegment.getDepart().getCoord().lon, lat, lon));
                 }
             }
        }
        
     }
     
     
     
     
     //------------------------------------------------------------------------------------------------------------------------
     /**
      * Permet de déplacer un point passé en paramètre
      * @param point: Point à déplacer
      * @param lat: Nouvelle coordonnée de latitude
      * @param lon: Nouvelle coordonnée de longitude
      * @throws SimulExcept 
      */
     public void deplacerPoint(Point point, Coordonnee coord)throws SimulExcept{
         Point PtDeplacer = point;
         
         PtDeplacer.getCoord().lat = coord.lat;
         PtDeplacer.getCoord().lon = coord.lon;
         
         //Modifie la longeurs des segments sortants
        PtDeplacer.getListSegment().stream().parallel().forEach(seg -> seg.setDistance(Utils.MesurerDistanceCoordonnees(PtDeplacer.getCoord().lat,
                PtDeplacer.getCoord().lon, seg.getArrivee().getCoord().lat, seg.getArrivee().getCoord().lon)));
         
         /** @TODO  recalculer les segments entrants (multithreading?)*/
        //On parcours la liste de points, pour chaque point, on vérifie chaque segment qui débute par ce point.  Ensuite, on vérifie si le point d,arrivée
        //de ce segment est le point qui est déplacé.  Si oui, on recalcul la longueur du segment.
         for (Point ListPoint1 : ListPoint) {
             for (Segment ListSegment : ListPoint1.getListSegment()) {
                 if (ListSegment.getArrivee() == point) {
                     ListSegment.setDistance(Utils.MesurerDistanceCoordonnees(ListSegment.getDepart().getCoord().lat, ListSegment.getDepart().getCoord().lon, coord.lat, coord.lon));
                 }
             }
        }
        
     }
     
     
     
     //--------------------------------------------------------------------------------------------------------------------------------------------------
     /**
      * Permet de supprimer un point dont le ID est passé en paramètre
      * @param idPoint: ID du point à supprimer
      * @throws SimulExcept 
      */
     public void supprPoint(int idPoint) throws SimulExcept{
         //****************************Verifications******************************************//
         ListPoint.remove(getPointDeListe(idPoint));
         nbPoints--;
 
     }
     
     
     public void ajouterSegment(Segment seg){
         
         seg.getDepart().getListSegment().add(seg);
         nbSegments++;
         
     }
     
     
     //--------------------------------------------------------------------------------------------------------------------------------------------------
     /**
      * Permet de supprimer un point passé en paramètre
      * @param point: Point à supprimer
      * @throws SimulExcept 
      */
     public void supprPoint(Point point) throws SimulExcept{
         ArrayList<Segment> temp = new ArrayList();
         for (Point pt: ListPoint){
             for (Segment seg:pt.getListSegment()){
                 if (seg.getArrivee() == point){
                     temp.add(seg);
                 }   
             }
             pt.getListSegment().removeAll(temp);
         }
         ListPoint.remove(point);
         nbPoints--;
 
     }
    
     
     /**
      * Permet de vérifier si un point existe dans un range de -15/+15 de l'emplacement passé en paramètre
      * @param lat: Latitude de l'emplacement à vérifier
      * @param lon: Longitude de l'emplacement à vérifier
      * @return: True si un point existe dans un range de -15/+15 de la latitude passé en paramètre 
      */
     public boolean pointExisteDansRange(Coordonnee coord){
         boolean returnValue = false;
         for(int i=0;i<ListPoint.size();i++){
             if(ListPoint.get(i).getCoord().lat-5<=coord.lat && ListPoint.get(i).getCoord().lat+5>=coord.lat && 
                     ListPoint.get(i).getCoord().lon-5<=coord.lon && ListPoint.get(i).getCoord().lon+5>=coord.lon){
                 returnValue=true;
             }
         }
         return returnValue;
     }
     
     
     
    
     //--------------------------------------------------------------------------------------------------------------------------------------------------
     /**
      * Permet d'ajouter un segment au graphe
      * @param idDepart: ID du point de départ du segment
      * @param idArrivee: ID du point d'arrivée du segment
      * @param distance: Distance entre point départ et d'arrivée
      * @param min: Temps minimal pour traverser ce segment
      * @param mode: Temps modal pour traverser ce segment
      * @param max: Temps maximal pour traverser ce segment
      * @throws SimulExcept 
      */
     public void ajoutSegment(int idDepart, int idArrivee, double distance,
             double min, double mode, double max) throws SimulExcept {
         
         //*******************Verifications*******************************//
    
        if(min > mode || min > max || mode > max){
        throw new SimulExcept(5, "Les donnees de calcul de la fonction de distri triangulaire sont invalides", "ajoutSegment");
       }
        
        //verifie si segment loop sur lui meme
        if(idDepart == idArrivee){
            throw new SimulExcept(8, "Un segment ne peut etre une boucle", "ajoutSegment");
        }
        
        
        //Verifie si les 2 points existent
         if(PointExiste(idDepart) == false || PointExiste(idArrivee) == false){//si le point n'existe pas, exception
            throw new SimulExcept(12, "Le point specifie n'existe pas", "modifPoint");
         }

        //Verifie si segment existe deja
      
       if(SegmentExiste(idDepart, idDepart) == true){
           throw new SimulExcept(7, "Le segment a ajoute existe deja", "ajoutSegment");
       }
       
       

       
       
       //********************************Creation du segment*******************************************//

       //Si tout passe, creation du segment
         getPointDeListe(idDepart).getListSegment().add(new Segment(getPointDeListe(idDepart), getPointDeListe(idArrivee), min, mode, max));
         nbSegments++;
     }
     
     
     
     
     
     //--------------------------------------------------------------------------------------------------------------------------------------------------
     /**
      * Permet d'ajouter un segment au graphe à l'aide du point de départ et d'arrivée
      * @param depart: Point de départ du segment
      * @param arrivee: Point d'arrivée du segment
      * @param distance: Distance entre les deux points
      * @param min: Temps minimal pour franchir ce segment
      * @param mode: Temps modal pour franchir ce segment
      * @param max: Temps maximal pour franchir ce segment
      * @throws SimulExcept 
      */
     public void ajoutSegment(Point depart, Point arrivee,
             double min, double mode, double max) throws SimulExcept {
         
         //*******************Verifications*******************************//
    
        if(min > mode || min > max || mode > max){
        throw new SimulExcept(5, "Les donnees de calcul de la fonction de distri triangulaire sont invalides", "ajoutSegment");
       }
        
        //verifie si segment loop sur lui meme
        if(depart == arrivee){
            throw new SimulExcept(8, "Un segment ne peut etre une boucle", "ajoutSegment");
        }
        
        
        //Verifie si les 2 points existent
         if(PointExiste(depart) == false || PointExiste(arrivee) == false){//si le point n'existe pas, exception
            throw new SimulExcept(12, "Le point specifie n'existe pas", "modifPoint");
         }

        //Verifie si segment existe deja
      
       if(SegmentExiste(depart, arrivee) == true){
           throw new SimulExcept(7, "Le segment a ajoute existe deja", "ajoutSegment");
       }
       
       

       
       
       //********************************Creation du segment*******************************************//

       //Si tout passe, creation du segment
         depart.creerSegment(arrivee, min, mode, max);
         nbSegments++;
     }
     
     
     
     
     
     
     //--------------------------------------------------------------------------------------------------------------------------------------------------
     /**
      * Permet de modifier les informations d'un segment
      * @param idDep: Id du point de départ de ce segment
      * @param idArr: ID du point d'arrivée de ce segment
      * @param distance: Nouvelle distance entre les deux points
      * @param min: Nouveau temps minimal pour franchir ce segment
      * @param mode: Nouveau mode pour franchir ce segment
      * @param max: Nouveau temps maximal pour franchir ce segment
      * @throws SimulExcept 
      */
     public void modifSegment(int idDep, int idArr, double distance, double min, double mode, double max) throws SimulExcept{
         
        Segment segModif = GetSegment(idDep, idArr);
        int posPointDepart=-1;
        int posPointArrivee=-1;
        //*************************Reafectation des parametres************************public ArrayList<Point> getListePtArrPotentielsNouveauSegment(Point pt)**********//
        
        segModif.setDistance(distance);
        segModif.setTempsMin(min);
        segModif.setTempsMode(mode);
        segModif.setTempsMax(max);      
            
     }
     
     
     
     //--------------------------------------------------------------------------------------------------------------------------------------------------
     /**
      * Permet de modifier un segment à l'aide du point de départ et d'arrivée
      * @param dep: Point de départ du segment à modifier
      * @param arr: Point d'arrivée du segment à modifier
      * @param min: Nouveau temps minimal pour franchir ce segment
      * @param mode: Nouveau mode pour franchir ce segment
      * @param max: Nouveau temps maximal pour franchir ce segment
      * @throws SimulExcept 
      */
     public void modifSegment(Point dep, Point arr, double min, double mode, double max) throws SimulExcept{
         
         dep.modifSegment(arr, min, mode, max);
        
     }
     
     
     
     
     
     
     //--------------------------------------------------------------------------------------------------------------------------------------------------
     /**
      * Permet de supprimer un segment dont l'ID du point de départ et l'ID du point d'arrivée est passé en paramètre
      * @param idDep: ID du point de départ
      * @param idArr: ID du point d'arrivée
      * @throws SimulExcept 
      */
     public void supprSegment(int idDep, int idArr) throws SimulExcept{
         
     Point refDep = getPointDeListe(idDep);
     Segment refSeg = GetSegment(idDep, idArr);
         
       
        
    ///****************************suppression du segment*************************//

    //on enleve le segment de la liste de segment du pt de depart
    refDep.getListSegment().remove(refSeg);
    nbSegments--;
        
     }
     
     
     //--------------------------------------------------------------------------------------------------------------------------------------------------
     /**
      * Permet de supprimer un segment dont le point de départ et d'arrivée est passé en paramètre
      * @param dep: Point de départ
      * @param arr: Point d'arrivée
      * @throws SimulExcept 
      */
     public void supprSegment(Point dep, Point arr) throws SimulExcept{
         
     dep.SupprSegment(arr);
     nbSegments--;
        
     }
     
     public ArrayList<Point> getListePtArrPotentielsNouveauSegment(Point pt){
         ArrayList<Point> ListPtArriveeExistant = pt.getListPtsArrivee();
         ArrayList<Point> listPtsArr = new ArrayList();
         listPtsArr.addAll(getListPoints());
         listPtsArr.removeAll(ListPtArriveeExistant);
         listPtsArr.remove(pt);
         
         return listPtsArr;
     }
     
     
     public ArrayList<Point> getListePtArr(Point pt){
        return pt.getListPtsArrivee();
    }
    
    public ArrayList<Segment> getListSegContigu(Point pt){
        return pt.getListSegment();
    }
    
    public boolean arretAtteignable(Point pt) throws SimulExcept, Exception{
        ArrayList<Point> list = new ArrayList();
        list = ptAtteignable(pt);
        
        for(Point point: list){
            if(point instanceof Arret){
                return true;
            }
        }
        
        return false;
    }
     
     
    public ArrayList<Point> ptAtteignable(Point pt) throws SimulExcept, Exception{
        Graphe graphFerme = FermetureGraphe();
        
        for(Point ptFerme:graphFerme.getListPoints()){
            if(ptFerme.getId() == pt.getId()){
                return ptFerme.getListPtsArrivee();
            }
        }
        
        throw new SimulExcept(37, "Une erreur est survenu dans la fermeture de graphe", "ptAtteignable");
    }
     
     
     
     
         
    //--------------------------------------------------------------------------------------------------------------------------------------------------
     public Graphe FermetureGraphe() throws SimulExcept, Exception {
         
         Graphe grapheFerme = (Graphe) ObjectCloner.deepCopy(this);
         
         for(Point pt2:grapheFerme.ListPoint){
             for(Point pt:grapheFerme.ListPoint){
                 for(Point pt3:grapheFerme.ListPoint){
                     
                     if(grapheFerme.SegmentExiste(pt, pt2) && grapheFerme.SegmentExiste(pt2, pt3) 
                             && grapheFerme.SegmentExiste(pt, pt3) ==  false && pt != pt3){
                         
                         grapheFerme.ajoutSegment(pt, pt3, 0, 0, 0);
                     
                 }
                 }
             }
         }
         
         return grapheFerme;
     }
     
     
     
     
     public void CalculTempsTransitSegments() throws SimulExcept{
       
            for(Point pt : this.getListPoints()){
                for(Segment seg : pt.getListSegment()){
                     seg.setTempsTransit(Utils.genererTempsTriangulaire(seg.getTempsMin(), seg.getTempsMode(), seg.getTempsMax()));
                }  
            } 
         
        
     }
     
     public ArrayList<Point> getListPoints(){
         return ListPoint;
     }
     
     public Point getPoint(double x, double y) throws SimulExcept{
         for(Point pt:ListPoint){
             if(pt.getCoordPixels().getLat()==x && pt.getCoordPixels().getLon()==y){
                 return pt;
             }
         }
         return null;
     }
     
     public void modifPositionPoint (Point pt, Coordonnee coordPix) throws SimulExcept{
        pt.modifPositionPoint(coordPix);
    }
         
   
    

     
     
     
     
     
     
//------------------------------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------------------------------
//$$$$$$$$4$$$$$$$$$$$$$$$$$$$$$$----Methodes Privees-----$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
     
     
     
 //------------------------------------------------------------------------------------------------------------------------------------------- 
     /**
      * Vérifie si un point existe à l'aide du ID de ce point
      * @param idPoint: ID du point à vérifier
      * @return True si le point existe, false autrement
      */
     private boolean PointExiste(int idPoint){
         //Java 8 a des Lambdas expressions!
         return ListPoint.stream().anyMatch((pt) -> (pt.getId() == idPoint));
     
     }
     
     //------------------------------------------------------------------------------------------------------------------------------------------- 
     /**
      * Vérifie si un point existe
      * @param point: Point dont nous voulons vérifier l'existance
      * @return True si le point existe, false autrement
      */
     private boolean PointExiste(Point point){
         //Java 8 a des Lambdas expressions!
         return ListPoint.stream().anyMatch((pt) -> (pt == point));
     
     }
     
     
 //-------------------------------------------------------------------------------------------------------------------------------------------
     /**
      * Permet de récupérer un point dans la liste de points créés
      * @param idPoint: ID du point à récupére
      * @return Le point dont le ID est passé en paramètre
      * @throws SimulExcept 
      */
     private Point getPointDeListe(int idPoint) throws SimulExcept{
         if(PointExiste(idPoint) == false){
             throw new SimulExcept(12, "Le point specifie n'existe pas", "modifPoint");
         }
         
        return ListPoint.stream().filter(pt -> pt.getId() == idPoint).findAny().get();
     }
     

    

 //-------------------------------------------------------------------------------------------------------------------------------------------
    /**
     * Vérifie si le nom d'un point est déjà existant
     * @param nom: Nom du point à vérifier
     * @return True si le nom du point existe déjà
     */
     private boolean NomPointExiste(String nom){
        return ListPoint.stream().anyMatch((pt) -> (pt.getNom().isEmpty()==false && pt.getNom().equals(nom)));
     }
     
 //-------------------------------------------------------------------------------------------------------------------------------------------    
     /**
      * Vérifie si un segment existe déjà
      * @param idPtDep: ID du Point de départ du segment
      * @param idPtArr: ID du Point d'arrivée du segment
      * @return
      * @throws SimulExcept 
      */
     private boolean SegmentExiste(int idPtDep, int idPtArr) throws SimulExcept{
         //Verifie si les 2 points existent
         if(PointExiste(idPtDep) == false || PointExiste(idPtArr) == false){//si le point n'existe pas, exception
            throw new SimulExcept(12, "Le point specifie n'existe pas", "modifPoint");
         }
         return getPointDeListe(idPtDep).getListSegment().stream().anyMatch(seg -> seg.getArrivee().getId() == idPtArr);
     }
     
     //-------------------------------------------------------------------------------------------------------------------------------------------   
     /**
      * Vérifie si un segment existe déjà 
      * @param PtDep: Point de départ du segment
      * @param PtArr: Point d'arrivée du segment
      * @return True si le segment existe déjà, false autrement
      * @throws SimulExcept 
      */
     private boolean SegmentExiste(Point PtDep, Point PtArr) throws SimulExcept{
         //Verifie si les 2 points existent
         if(PointExiste(PtDep) == false || PointExiste(PtArr) == false){//si le point n'existe pas, exception
            throw new SimulExcept(12, "Le point specifie n'existe pas", "modifPoint");
         }
         return PtDep.getListSegment().stream().anyMatch(seg -> seg.getArrivee() == PtArr);
     }
     
     
 //-------------------------------------------------------------------------------------------------------------------------------------------
     /**
      * Permet de récupérer un segment à l'aide du ID du point de départ et d'arrivée
      * @param idDep: ID du point de départ du segment
      * @param idArr: ID du point d'arrivée du segment
      * @return Segment demandé
      * @throws SimulExcept 
      */
     private Segment GetSegment(int idDep, int idArr) throws SimulExcept{
         //verifie si points existe
         if(PointExiste(idDep) == false || PointExiste(idArr) == false){//si le point n'existe pas, exception
            throw new SimulExcept(12, "Le point specifie n'existe pas", "modifPoint");
         }
         
         //Verifie si le segment existe deja
        if(SegmentExiste(idDep, idArr) == false){
           throw new SimulExcept(9, "Le segment a modifier n'existe pas", "modifSegment");
       }
         
         return getPointDeListe(idDep).getListSegment().stream().filter(seg -> seg.getArrivee().getId() == idArr).findAny().get();
         
     }
     
     
     //-------------------------------------------------------------------------------------------------------------------------------------------
     /**
      * PErmet de récupérer un segment à l'aide de points
      * @param PtDep: Point de départ du segment
      * @param PtArr: Point d'arrivée du segment
      * @return Segment demandé
      * @throws SimulExcept 
      */
     public Segment GetSegment(Point PtDep, Point PtArr) throws SimulExcept{
         //verifie si points existe
         if(PointExiste(PtDep) == false || PointExiste(PtArr) == false){//si le point n'existe pas, exception
            throw new SimulExcept(12, "Le point specifie n'existe pas", "GetSegment");
         }
         
         //Verifie si le segment existe deja
        if(SegmentExiste(PtDep, PtArr) == false){
           throw new SimulExcept(9, "Le segment a modifier n'existe pas", "GetSegment");
       }
         
         return PtDep.getListSegment().stream().filter(seg -> seg.getArrivee() == PtArr).findAny().get();
         
     }
     
     public Point verifPointRange(double x, double y) throws SimulExcept{
         ArrayList<Point> listPtDsRange = new ArrayList();
         Point ptARenvoyer;
         for(Point pt: ListPoint){
             if (pt.GetPointDsRangePixel(x, y)!= null){
                 listPtDsRange.add(pt);
             }      
         }
         if(listPtDsRange.isEmpty()){
            return null; 
         }
         if(listPtDsRange.size() == 1){
             return listPtDsRange.get(0);
         }
         else{
             ptARenvoyer = listPtDsRange.get(0);
             double distCentre = Utils.MesurerDistanceCoordonnees(ptARenvoyer.getCoordPixels().lat, ptARenvoyer.getCoordPixels().lon, x, y);
             for(int i = 1; i < listPtDsRange.size(); i++){
                 if(Utils.MesurerDistanceCoordonnees(listPtDsRange.get(i).getCoord().getLat(), listPtDsRange.get(i).getCoord().getLon(), x, y) < distCentre){
                     distCentre = Utils.MesurerDistanceCoordonnees(listPtDsRange.get(i).getCoord().getLat(), listPtDsRange.get(i).getCoord().getLon(), x, y);
                     ptARenvoyer = listPtDsRange.get(i);
                 }
             }
             return ptARenvoyer;
         }
         
     }
     
     public ArrayList<Segment> verifSegDsRange(double x, double y){
         
         ArrayList<Segment> listSeg = new ArrayList();
         
         for(Point ptDep:this.getListPoints()){
             for (Segment seg: ptDep.getListSegment()){
                 Point ptArr = seg.getArrivee();
                 
                 
                 double ax = ptDep.getCoordPixels().getLat();
                 double ay = ptDep.getCoordPixels().getLon();
                 double cx = x;
                 double cy = y;
                 double bx = ptArr.getCoordPixels().getLat();
                 double by = ptArr.getCoordPixels().getLon();
                 
                 double crossproduct = (cy - ay) * (bx - ax) - (cx - ax) * (by - ay);
                 double dotproduct = (cx - ax) * (bx - ax) + (cy - ay)*(by - ay);
                 double squaredlength = (bx - ax)*(bx - ax) + (by - ay)*(by - ay);
                 
                 double epsilon = 2000;
                 if(Math.abs(crossproduct) < epsilon && dotproduct >= 0 && dotproduct <= squaredlength){//1-si la cross ref est plus petit que epsilon, on sait que le point b est sur la meme pente que a et c 
                                                                                                        //2 et 3 -verifier si b est ENTRE a et c
                     listSeg.add(seg);
                          
                 }
                 
                 
             }
         }
         return listSeg;//si on trouve rien on retourne null
     }
     
     
     public Point changerTypeArretVersIntersection(Point pt) throws SimulExcept{
         
         if(pt instanceof Intersection){
             throw new SimulExcept(35, "Le point est deja une intersection", "changerTypeIntersectionVersArret");
         }
         
            double x = pt.getCoordPixels().getLat();
            double y = pt.getCoordPixels().getLon();
            String nom = pt.getNom();
            ArrayList<Segment> listeSeg = new ArrayList();
            listeSeg = pt.getListSegment();
            ArrayList<Point> listePointDepart = new ArrayList();
            ArrayList<Double> listeMin = new ArrayList();
            ArrayList<Double> listeMode = new ArrayList();
            ArrayList<Double> listeMax = new ArrayList();
            //Loop sur chaque points du graphe
            for(Point pointsCourants : this.getListPoints()){
                //Pour chaque points, on récupère chaque segments un a un
                for(Segment segmentsPointsCourants : pointsCourants.getListSegment()){
                    //On vérifie si le points d'arrivée du segment est le point courant
                    if(segmentsPointsCourants.getArrivee()==pt){
                        listePointDepart.add(segmentsPointsCourants.getDepart());
                        listeMin.add(segmentsPointsCourants.getTempsMin());
                        listeMode.add(segmentsPointsCourants.getTempsMode());
                        listeMax.add(segmentsPointsCourants.getTempsMax());
                    }
                }
            }            
            supprPoint(pt);
            Coordonnee coord = new Coordonnee(x, y);
            ajoutIntersec(coord,nom);
            Point temp = getPoint(x,y);
            temp.setListSegment(listeSeg);
            int iter=0;
            for(Point ptDepart : listePointDepart){
                 ajoutSegment(ptDepart, temp, listeMin.get(iter), listeMode.get(iter), listeMax.get(iter));
                 iter++;
            }
            return temp;
       
    }
     
     public Point changerTypeIntersectionVersArret(Point pt) throws SimulExcept{
         
         if(pt instanceof Arret){
             throw new SimulExcept(36, "Le point est deja un arret", "changerTypeIntersectionVersArret");
         }
         
            double x = pt.getCoordPixels().getLat();
            double y = pt.getCoordPixels().getLon();
            String nom = pt.getNom();
            ArrayList<Segment> listeSeg = new ArrayList();
            listeSeg = pt.getListSegment();
            ArrayList<Point> listePointDepart = new ArrayList();
            ArrayList<Double> listeMin = new ArrayList();
            ArrayList<Double> listeMode = new ArrayList();
            ArrayList<Double> listeMax = new ArrayList();
            //Loop sur chaque points du graphe
            for(Point pointsCourants : this.getListPoints()){
                //Pour chaque points, on récupère chaque segments un a un
                for(Segment segmentsPointsCourants : pointsCourants.getListSegment()){
                    //On vérifie si le points d'arrivée du segment est le point courant
                    if(segmentsPointsCourants.getArrivee()==pt){
                        listePointDepart.add(segmentsPointsCourants.getDepart());
                        listeMin.add(segmentsPointsCourants.getTempsMin());
                        listeMode.add(segmentsPointsCourants.getTempsMode());
                        listeMax.add(segmentsPointsCourants.getTempsMax());
                    }
                }
            }            
            supprPoint(pt);
            Coordonnee coord = new Coordonnee(x, y);
            ajoutArret(coord,nom);
            Point temp = getPoint(x,y);
            temp.setListSegment(listeSeg);
            int iter=0;
            for(Point ptDepart : listePointDepart){
                 ajoutSegment(ptDepart, temp, listeMin.get(iter), listeMode.get(iter), listeMax.get(iter));
                 iter++;
            }
            return temp;
       
    }
}
            
