package Presentation;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;
import Application.Application;
import Domaine.uiExcept;
import Fondation.Factory;
import InfrastructureMetier.Arret;
import InfrastructureMetier.Bus;
import InfrastructureMetier.Circuit;
import InfrastructureMetier.Coordonnee;
import InfrastructureMetier.Intersection;
import InfrastructureMetier.Itineraire;
import InfrastructureMetier.Point;
import InfrastructureMetier.Segment;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
/**
 *
 * @author Admin
 */
public class Dessinateur extends JPanel {
    
    Application application = Factory.creerApplication();
    boolean mettreSurbrillancePoint=false;
    boolean mettreSurbrillanceSegment=false;
    boolean mettreSurbrillancePointReseau=false;
    boolean mettreSurbrillanceReseauEntier=false;
    boolean mettreSurbrillancePointItineraire=false;
    boolean mettreSurbrillanceItineraire=false;
    boolean afficherArretBus = false;
    boolean afficherBus=false;
    int indexSegment=-1;
    String nomSimulation=null;
    Point pointSource = null;
    Segment segmentSurb = null;
    Circuit circuitSurbrilItineraire = null;
    String circuitSurbrillance = null;
    Itineraire itineraireSurbrillance = null;
    Date date1 = new Date();
    Date date2 = new Date();
    Date dateCourante = new Date();
    private double zoomFactor = 1.0;  
    private boolean zoomer = false;
    Dimension sizePanel = new Dimension(2000,1000);
    Coordonnee coord = null;
    AffineTransform at = new AffineTransform();
    private final double phi = Math.toRadians(30);
    private final int barb = 20;
    private BufferedImage bg=null;    
    
    
    /**
     * Override de la fonction paintComponent - Permet de dessiner dans l'interface
     * @param g: Paramètres graphiques de l'interface ou dessiner 
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if(zoomer==true){    
            at = new AffineTransform();
            at.scale(zoomFactor,zoomFactor);
            zoomer=false;
        }
        g2.transform(at);
        try{
            if(bg!=null){
                g2.drawImage(bg, 0,0, this);
            }
            BufferedImage busImage = ImageIO.read(getClass().getResourceAsStream("/Presentation/images/movingBus.png"));
            
            //--------------
            //AFFICHAGE DES POINTS ET SEGMENTS DU GRAPHE
            //--------------
            ArrayList<Point> listePoints = application.getListePoints();
            for(Point pt : listePoints){
            g2.setColor(Color.BLACK);
            if(pt instanceof Intersection){
                g2.setColor(Color.GRAY);
                g2.fillOval((int)pt.getCoordPixels().getLat()-8, (int)sizePanel.height-(int)pt.getCoordPixels().getLon()-4, 8,8);   
            }
            else{
                g2.setColor(Color.BLACK);
                g2.fillOval((int)pt.getCoordPixels().getLat()-8, (int)sizePanel.height-(int)pt.getCoordPixels().getLon()-4, 8,8);   
            }
            g2.setColor(Color.BLACK);
            if(pt instanceof Arret && afficherBus == true){
                if(application.getListSim().size()>0){
                    ArrayList<Point> listePoints2 = application.getListePoints(nomSimulation);
                    for(Point pt2:listePoints2){
                        if(pt2 instanceof Arret){
                            g2.drawString("Passagers : " + String.valueOf(((Arret)pt2).getListPassagers().size()),(int)pt2.getCoordPixels().getLat()-30, (int)sizePanel.height-(int)pt2.getCoordPixels().getLon()-15);
                        }
                    }
                }
                else{
                    g2.drawString("Passagers : " + String.valueOf(((Arret)pt).getListPassagers().size()),(int)pt.getCoordPixels().getLat()-30, (int)sizePanel.height-(int)pt.getCoordPixels().getLon()-15);
                }
            }
            if(pt.getListSegment().size()>0){
                ArrayList<Segment> listeSegmentsPt = pt.getListSegment();
                for(Segment seg : listeSegmentsPt){
                    g2.drawLine((int)seg.getDepart().getCoordPixels().getLat(),
                        (int)sizePanel.height-(int)seg.getDepart().getCoordPixels().getLon(),
                        (int)seg.getArrivee().getCoordPixels().getLat(),
                        (int)sizePanel.height-(int)seg.getArrivee().getCoordPixels().getLon());
                    
                        //AFFICHAGE ORIENTATION SEGMENT
                        double dy = ((int)sizePanel.height-(int)seg.getDepart().getCoordPixels().getLon()) - ((int)sizePanel.height-(int)seg.getArrivee().getCoordPixels().getLon());
                        double dx = seg.getDepart().getCoordPixels().getLat() - seg.getArrivee().getCoordPixels().getLat();
                        double theta = Math.atan2(dy, dx);
                        double x, y, rho = theta + phi;
                        for(int j = 0; j < 2; j++)
                        {
                            x = seg.getArrivee().getCoordPixels().getLat() + barb * Math.cos(rho);
                            y = (int)sizePanel.height-(int)seg.getArrivee().getCoordPixels().getLon() + barb * Math.sin(rho);
                            g2.draw(new Line2D.Double(seg.getArrivee().getCoordPixels().getLat(), (int)sizePanel.height-(int)seg.getArrivee().getCoordPixels().getLon(), x, y));
                            rho = theta - phi;
                        }
                }
            }
            }
            
        //---------------
        //AFFICHAGE POINTS POTENTIELS CRÉATION SEGMENTS
        if(mettreSurbrillancePoint==true){
            ArrayList<Point> pointArrivePotentiel = application.getListePtArrPotentielsNouveauSegment(pointSource);
            for(Point pt : pointArrivePotentiel){
                g2.setColor(Color.RED);
                g2.fillOval((int)pt.getCoordPixels().getLat()-8, (int)sizePanel.height-(int)pt.getCoordPixels().getLon()-4, 8,8);
            }
            mettreSurbrillancePoint = false;
        }
        
        //AFFICHAGE D'UN SEGMENT EN SURBRILLANCE
        if(mettreSurbrillanceSegment==true){
            g2.setColor(Color.RED);
            g2.drawLine((int)segmentSurb.getDepart().getCoordPixels().getLat(),
                (int)sizePanel.height-(int)segmentSurb.getDepart().getCoordPixels().getLon(),
                (int)segmentSurb.getArrivee().getCoordPixels().getLat(),
                (int)sizePanel.height-(int)segmentSurb.getArrivee().getCoordPixels().getLon());
            //AFFICHAGE ORIENTATION SEGMENT
            double dy = ((int)sizePanel.height-(int)segmentSurb.getDepart().getCoordPixels().getLon()) - ((int)sizePanel.height-(int)segmentSurb.getArrivee().getCoordPixels().getLon());
            double dx = segmentSurb.getDepart().getCoordPixels().getLat() - segmentSurb.getArrivee().getCoordPixels().getLat();
            double theta = Math.atan2(dy, dx);
            double x, y, rho = theta + phi;
            for(int j = 0; j < 2; j++)
            {
                x = segmentSurb.getArrivee().getCoordPixels().getLat() + barb * Math.cos(rho);
                y = (int)sizePanel.height-(int)segmentSurb.getArrivee().getCoordPixels().getLon() + barb * Math.sin(rho);
                g2.draw(new Line2D.Double(segmentSurb.getArrivee().getCoordPixels().getLat(), (int)sizePanel.height-(int)segmentSurb.getArrivee().getCoordPixels().getLon(), x, y));
                rho = theta - phi;
            }
            mettreSurbrillanceSegment=false;
        }
        
        //AFFICHAGE POINTS POSSIBLE CRÉATION CIRCUITS
        if(mettreSurbrillancePointReseau==true){
            g2.setColor(Color.RED);
            ArrayList<Point> listePointsPossible=application.getListePtArr(pointSource);
            for(Point pt : listePointsPossible){    
                g2.fillOval((int)pt.getCoordPixels().lat-8, (int)sizePanel.height-(int)pt.getCoordPixels().lon-4, 8,8);
            }
            mettreSurbrillancePointReseau = false;
        }
        
        //AFFICHAGE D'UN CIRCUIT SPECIFIQUE EN COULEUR
        if(mettreSurbrillanceReseauEntier==true){
            g2.setColor(Color.RED);
            ArrayList<Circuit> listeCircuit = application.getListCircuit();
            ArrayList<Point> listePointsCircuit;
            ArrayList<Segment> listeSegmentsCircuit;
            for(Circuit circ : listeCircuit){
                if(circ.getNomCircuit().equals(circuitSurbrillance)){
                    listePointsCircuit = circ.getListPointsCircuit();
                    listeSegmentsCircuit = application.getListSegmentCirc(circ);
                    for(Point pt : listePointsCircuit){
                        g2.fillOval((int)pt.getCoordPixels().lat-8,(int)sizePanel.height-(int)pt.getCoordPixels().lon-4,8,8);
                    }               
                    for(Segment sg : listeSegmentsCircuit){
                        g2.drawLine((int)sg.getDepart().getCoordPixels().getLat(), (int)sizePanel.height-(int)sg.getDepart().getCoordPixels().getLon(), 
                                (int)sg.getArrivee().getCoordPixels().getLat(), (int)sizePanel.height-(int)sg.getArrivee().getCoordPixels().getLon());
                        //AFFICHAGE ORIENTATION SEGMENT
                        double dy = ((int)sizePanel.height-(int)sg.getDepart().getCoordPixels().getLon()) - ((int)sizePanel.height-(int)sg.getArrivee().getCoordPixels().getLon());
                        double dx = sg.getDepart().getCoordPixels().getLat() - sg.getArrivee().getCoordPixels().getLat();
                        double theta = Math.atan2(dy, dx);
                        double x, y, rho = theta + phi;
                        for(int j = 0; j < 2; j++){
                            x = sg.getArrivee().getCoordPixels().getLat() + barb * Math.cos(rho);
                            y = (int)sizePanel.height-(int)sg.getArrivee().getCoordPixels().getLon() + barb * Math.sin(rho);
                            g2.draw(new Line2D.Double(sg.getArrivee().getCoordPixels().getLat(), (int)sizePanel.height-(int)sg.getArrivee().getCoordPixels().getLon(), x, y));
                            rho = theta - phi;
                        }
                    }
                }
            } 
        }
        
        //AFFICHAGE DES BUS SUR LA CARTE
        if(afficherBus==true){
            ArrayList<Bus> listeBus = new ArrayList(application.getListeTotaleBus(nomSimulation));
            for(Bus bus : listeBus){
                Coordonnee coord1 = application.getCoordBus(bus,dateCourante,nomSimulation);
                Coordonnee coordSourceBus = bus.getCircuit().getSource().getCoordPixels();
                Coordonnee coordArrBus = bus.getCircuit().getListPointsCircuit().get(bus.getCircuit().getListPointsCircuit().size()-1).getCoordPixels();
                if(coord1 == coordSourceBus || coord1 == coordArrBus){}
                else{
                    g2.setColor(Color.BLACK);
                    g2.drawString(" Circuit: " + bus.getCircuit().getNomCircuit() + " Pass : " + bus.getListePassagers().size(), (int)coord1.getLat()-30, (int)sizePanel.height-(int)coord1.getLon()-15);
                    g2.drawImage(busImage,(int)coord1.getLat()-15,(int)sizePanel.height-(int)coord1.getLon()-15,null);
                    if(dateCourante.equals(date2)){
                        afficherBus=false;
                    }
                }
            }
        }

        //AFFICHAGE DES POINTS POSSIBLE LORS CRÉATION ITINÉRAIRES
        if(mettreSurbrillancePointItineraire==true){
            g2.setColor(Color.RED);
            int indexPt = 0;
            for(int i=0;i<circuitSurbrilItineraire.getCircdepuisSource().size()-1;i++){
                if(circuitSurbrilItineraire.getCircdepuisSource().get(i)==pointSource){
                    if(i==circuitSurbrilItineraire.getCircdepuisSource().size()-1){}
                    else{
                        indexPt=i;
                    }
                    
                }
            }
            Point pt = circuitSurbrilItineraire.getCircdepuisSource().get(indexPt+1);
            g2.fillOval((int)pt.getCoordPixels().lat-8,(int)sizePanel.height-(int)pt.getCoordPixels().lon-4,8,8);
            mettreSurbrillancePointItineraire=false;
        }
        
        //AFFICHAGE DE L'ITINERAIRE COMPLET
        if(mettreSurbrillanceItineraire==true){
            g2.setColor(Color.RED);
            for(Point pt : itineraireSurbrillance.getListPts()){
                g2.fillOval((int)pt.getCoordPixels().getLat()-8, (int)sizePanel.height-(int)pt.getCoordPixels().getLon()-4, 8,8);   
                for(Segment seg : itineraireSurbrillance.getListSegmentIti()){
                    g2.drawLine((int)seg.getDepart().getCoordPixels().getLat(),
                        (int)sizePanel.height-(int)seg.getDepart().getCoordPixels().getLon(),
                        (int)seg.getArrivee().getCoordPixels().getLat(),
                        (int)sizePanel.height-(int)seg.getArrivee().getCoordPixels().getLon());
                    //AFFICHAGE ORIENTATION SEGMENT
                    double dy = ((int)sizePanel.height-(int)seg.getDepart().getCoordPixels().getLon()) - ((int)sizePanel.height-(int)seg.getArrivee().getCoordPixels().getLon());
                    double dx = seg.getDepart().getCoordPixels().getLat() - seg.getArrivee().getCoordPixels().getLat();
                    double theta = Math.atan2(dy, dx);
                    double x, y, rho = theta + phi;
                    for(int j = 0; j < 2; j++){
                        x = seg.getArrivee().getCoordPixels().getLat() + barb * Math.cos(rho);
                        y = (int)sizePanel.height-(int)seg.getArrivee().getCoordPixels().getLon() + barb * Math.sin(rho);
                        g2.draw(new Line2D.Double(seg.getArrivee().getCoordPixels().getLat(), (int)sizePanel.height-(int)seg.getArrivee().getCoordPixels().getLon(), x, y));
                        rho = theta - phi;
                    }
                }
            }
            mettreSurbrillanceItineraire=false;
        }
        
        
        
        }catch(uiExcept ex){
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(Dessinateur.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    /**
     * Permet de mettre à jour la variable application en fonction de celle du UI
     * @param app Nouvelle valeur de la variable Application
     */
    public void updateApplication(Application app){
        this.application = app;
    }
    
    /**
     * Permet de définir la variable mettreSurbrillancePoint à 1 afin de dessiner les points d'arrivés potentiel lors de la création d'un segment
     * @param pt Le point source
     */
    public void mettreSurbrillancePoint(Point pt){
        mettreSurbrillancePoint=true;
        pointSource = pt;
    }
    
    /**
     * Permet de définir à 1 la variable surbrillancePointReseau qui indique au dessinateur d'afficher tous les points possible lors de la création d'un circuit
     * @param pt Dernier point ayant été ajouté au circuit
     */
    public void mettreSurbrillancePointReseau(Point pt){
        mettreSurbrillancePointReseau=true;
        pointSource = pt;
    }
    
    /**
     * Met à jour la variable mettreSurbrillanceReseauEntier afin d'afficher un circuit entier sur la carte 
     * @param circuit Le circuit à mettre en surbrillance
     */
    public void mettreSurbrillanceCircuit(String circuit){
        mettreSurbrillanceReseauEntier=true;
        circuitSurbrillance = circuit;
    }
    
    /**
     * Met à jour la variable mettreSurbrillanceSegment qui permet de mettre en bleu un segment lors du clic sur celui-ci
     * @param seg Le segment à mettre en surbrillance
     */
    public void mettreSurbrillanceSegment(Segment seg){
        segmentSurb = seg;
        mettreSurbrillanceSegment=true;
    }
    
    /**
     * Met en surbirllance les points possible lors création itinéraire
     * @param pt Le point courant
     * @param circ Le circuit courant
     */
    public void mettreSurbrillancePointItineraire(Point pt, Circuit circ){
        mettreSurbrillancePointItineraire=true;
        pointSource = pt;
        circuitSurbrilItineraire = circ;
    }
    
    /**
     * Met en surbrillance l'itinéraire passé en paramètre
     * @param iti L'itinéraire
     */
    public void mettreSurbrillanceItineraire(Itineraire iti){
        mettreSurbrillanceItineraire=true;
        itineraireSurbrillance = null;
        itineraireSurbrillance = iti;
    }
    
    /**
     * Defini la variable afficherBus à 1 ce qui permet d'afficher les bus dans la simulation
     * @param nomSim Nom de la simulation à afficher
     */
    public void setAfficherBus(String nomSim){
        afficherBus=true;
        nomSimulation = nomSim;
    }
    
    public void setAfficherArretBus(Coordonnee coord){
        afficherArretBus=true;
        this.coord=coord;
    }
    
    /**
     * Defini la date courante en fonction de celle du UI
     * @param date Nouvelle date courante 
     */
    public void setDateCourante(Date date){
        dateCourante = (Date)date.clone();
    }
    
    /**
     * Permet d'indiquer au dessinateur quel est la taille de la carte 
     * @param dim Dimension de la carte
     */
    public void setSizePanel(Dimension dim){
        sizePanel = dim;
    }
    
    /**
     * Permet de définir la date de début et de fin de la simulation
     * @param dateDeb Date de debut
     * @param dateFin Date de fin
     */
    public void setDateDebutFin(Date dateDeb, Date dateFin){
        date1 = dateDeb;
        date2 = dateFin;
    }
    
    /**
     * Permet de réinitialiser tous les parametres de surbrillance
     */
    public void resetAllSurbrillance(){
        resetSurbrillancePoint();
        resetSurbrillanceSegment();
        resetSurbrillancePointReseau();
        resetSurbrillanceReseauEntier();
        resetAfficherBus();
    }
    
    /**
     * Permet de réinitialiser la variable de surbrillance des points
     */
    public void resetSurbrillancePoint(){
        mettreSurbrillancePoint=false;
    }
    
    /**
     * Permet de réinitialiser la variable de surbrillance des segments
     */
    public void resetSurbrillanceSegment(){
        mettreSurbrillanceSegment=false;
    }
    
    /**
     * Permet de réinitialiser la variable de surbrillance des points lors de création réeau
     */
    public void resetSurbrillancePointReseau(){
        mettreSurbrillancePointReseau=false;
    }
    
    /**
     * Permet de réinitialiser la variable de surbrillance du reseau entier
     */
    public void resetSurbrillanceReseauEntier(){
        mettreSurbrillanceReseauEntier=false;
    }
    
    /**
     * Permet de réinitialiser la variable d'afficher des bus dans la simulation
     */
    public void resetAfficherBus(){
        afficherBus=false;
    }
    
    /**
     * Permet de réinitialiser la variable d'affichage des points possible lors création itineraire
     */
    public void resetSurbrillancePointItineraire(){
        mettreSurbrillancePointItineraire=false;
    }
    
    public double getZoomFactor(){
        return this.zoomFactor;
    }
    
    public void setZoomFactor(double factor){        
        if(factor<this.zoomFactor){
            this.zoomFactor=this.zoomFactor/1.1;
        }
        else{
            this.zoomFactor=factor;
        }
        this.zoomer=true;
    }
    
    public AffineTransform getAffine(){
        return this.at;
    }
    
    public void setBackgroundType(int indexSelect){
        try{
        if(indexSelect==0){
            bg=null;
            bg = ImageIO.read(getClass().getResourceAsStream("/Presentation/images/backgroundSim.png"));
        }
        if(indexSelect==1){
            
            bg = ImageIO.read(getClass().getResourceAsStream("/Presentation/images/backgroundReel.png"));
        }
        if(indexSelect!=0 && indexSelect!=1){
            bg = null;
        }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
}
