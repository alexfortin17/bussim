/*
 * Ce code est la propriete de l'empereur Alexandre Fortin
 * Each line should be prefixed with  * 
 */
package Domaine;

import Fondation.SimulExcept;
import static Fondation.Utils.loadSim;
import static Fondation.Utils.saveSim;
import InfrastructureMetier.Bus;
import InfrastructureMetier.Circuit;
import InfrastructureMetier.Coordonnee;
import InfrastructureMetier.Itineraire;
import InfrastructureMetier.Point;
import java.util.ArrayList;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alfo
 */
public class SimulationTest {
    
    Graphe carte = new Graphe();
    ReseauAutobus reseau;
    Date deb = new Date();
    Date fin = new Date(deb.getTime() + 900000);//15 min plus tard
    ArrayList<Point> blue = new ArrayList();
    ArrayList<Point> orange = new ArrayList();
    ArrayList<Point> purple = new ArrayList();
    ArrayList<Point> smallGreen = new ArrayList();
    ArrayList<Point> bigGreen = new ArrayList();
    ArrayList<Point> loop = new ArrayList();
    
    ArrayList<Point> trajetVomi = new ArrayList();
    ArrayList<Point> trajetPuke = new ArrayList();
    ArrayList<Point> trajetLoop = new ArrayList();
    
    ArrayList<Circuit> circVomi = new ArrayList();
    ArrayList<Circuit> circPuke = new ArrayList();
    ArrayList<Circuit> circLoop = new ArrayList();

    
    public SimulationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws SimulExcept{
        
        carte.ajoutArret(new Coordonnee(0.0, 0.0), "0");
        carte.ajoutArret(new Coordonnee(0.0, 5.0), "1");
        carte.ajoutIntersec(new Coordonnee(0.0, 10.0), "2");
        carte.ajoutArret(new Coordonnee(5.0, 0.0), "3");
        carte.ajoutArret(new Coordonnee(5.0, 5.0), "4");
        carte.ajoutArret(new Coordonnee(5.0, 10.0), "5");
        carte.ajoutIntersec(new Coordonnee(10.0, 0.0), "6");
        carte.ajoutArret(new Coordonnee(10.0, 5.0), "7");
        carte.ajoutArret(new Coordonnee(10.0, 10.0), "8");
        ArrayList<Point> ListPt = carte.getListPoints();
        carte.ajoutSegment(ListPt.get(0), ListPt.get(1), 1, 2, 3);
        carte.ajoutSegment(ListPt.get(0), ListPt.get(3), 1, 2, 3);
        carte.ajoutSegment(ListPt.get(1), ListPt.get(2), 1, 2, 3);
        carte.ajoutSegment(ListPt.get(1), ListPt.get(4), 1, 2, 3);
        carte.ajoutSegment(ListPt.get(2), ListPt.get(5), 1, 2, 3);
        carte.ajoutSegment(ListPt.get(3), ListPt.get(4), 1, 2, 3);
        carte.ajoutSegment(ListPt.get(3), ListPt.get(6), 1, 2, 3);
        carte.ajoutSegment(ListPt.get(4), ListPt.get(5), 1, 2, 3);
        carte.ajoutSegment(ListPt.get(4), ListPt.get(7), 1, 2, 3);
        carte.ajoutSegment(ListPt.get(5), ListPt.get(8), 1, 2, 3);
        carte.ajoutSegment(ListPt.get(6), ListPt.get(7), 1, 2, 3);
        carte.ajoutSegment(ListPt.get(7), ListPt.get(8), 1, 2, 3);
        
        carte.ajoutSegment(ListPt.get(4), ListPt.get(3), 1, 2, 3);
        carte.ajoutSegment(ListPt.get(3), ListPt.get(0), 1, 2, 3);
        
        blue.add(ListPt.get(0));
        blue.add(ListPt.get(1));
        blue.add(ListPt.get(2));
        blue.add(ListPt.get(5));
        blue.add(ListPt.get(8));
        
        orange.add(ListPt.get(1));
        orange.add(ListPt.get(4));
        orange.add(ListPt.get(7));
        
       purple.add(ListPt.get(0));
       purple.add(ListPt.get(3));
       purple.add(ListPt.get(4));
       
       smallGreen.add(ListPt.get(4));
       smallGreen.add(ListPt.get(5));
       
       bigGreen.add(ListPt.get(3));
       bigGreen.add(ListPt.get(6));
       bigGreen.add(ListPt.get(7));
       bigGreen.add(ListPt.get(8));
       
       loop.add(ListPt.get(0));
       loop.add(ListPt.get(1));
       loop.add(ListPt.get(4));
       loop.add(ListPt.get(3));
       loop.add(ListPt.get(0));
       
       trajetVomi.add(ListPt.get(0));
       trajetVomi.add(ListPt.get(3));
       trajetVomi.add(ListPt.get(4));
       trajetVomi.add(ListPt.get(5));
       trajetVomi.add(ListPt.get(8));
       
       trajetPuke.add(ListPt.get(1));
       trajetPuke.add(ListPt.get(4));
       trajetPuke.add(ListPt.get(7));
       trajetPuke.add(ListPt.get(8));
       
       trajetLoop.add(ListPt.get(0));
       trajetLoop.add(ListPt.get(1));
       trajetLoop.add(ListPt.get(4));
       trajetLoop.add(ListPt.get(3));
       
        
        System.out.println("creerCircuit");
 
        double min = 1.0;
        double mode = 2.0;
        double max = 3.0;
        reseau = new ReseauAutobus(carte);
        reseau.creerCircuit("blue", false, blue, min, mode, max, blue.get(0));//0
        reseau.creerCircuit("orange", false, orange, min, mode, max, orange.get(0));//1
        reseau.creerCircuit("purple", false, purple, min, mode, max, purple.get(0));//2
        reseau.creerCircuit("smallGreen", false, smallGreen, min, mode, max, smallGreen.get(0));//3
        reseau.creerCircuit("bigGreen", false, bigGreen, min, mode, max, bigGreen.get(0));//4
        reseau.creerCircuit("loop", true, loop, min, mode, max, loop.get(0));//5
        
        
        circVomi.add(reseau.getListCircuits().get(2));
        circVomi.add(reseau.getListCircuits().get(3));
        circVomi.add(reseau.getListCircuits().get(0));
        
        circPuke.add(reseau.getListCircuits().get(1));
        circPuke.add(reseau.getListCircuits().get(4));
        
        circLoop.add(reseau.getListCircuits().get(0));
        circLoop.add(reseau.getListCircuits().get(5));
        
        
        
        reseau.CreerIti(trajetVomi, circVomi, "trajetVomi", 1, min, mode, max);
        reseau.CreerIti(trajetPuke, circPuke, "trajetPuke", 1, min, mode, max);
        reseau.CreerIti(trajetLoop, circLoop, "trajetLoop", 1, min, mode, max);
        

        
    }
    
    @After
    public void tearDown() {
    }

/*
    @Test
    public void testGetReseau() throws SimulExcept, InterruptedException{
        System.out.println("getReseau");
        Simulation instance = new Simulation(reseau, deb, fin);
        ReseauAutobus expResult = reseau;
        ReseauAutobus result = instance.getReseau();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetTempsDebSim() throws SimulExcept, InterruptedException{
        System.out.println("getTempsDebSim");
        Simulation instance = new Simulation(reseau, deb, fin);
        Date expResult = deb;
        Date result = instance.getTempsDebSim();
        assertEquals(expResult, result);
    }

   
    @Test
    public void testGetTempsFinSim() throws SimulExcept, InterruptedException{
        System.out.println("getTempsFinSim");
        Simulation instance = new Simulation(reseau, deb, fin);
        Date expResult = fin;
        Date result = instance.getTempsFinSim();
        assertEquals(expResult, result);
    }

 
    @Test
    public void testEvalTempsTransitSegmentSim() throws SimulExcept, InterruptedException{
        System.out.println("EvalTempsTransitSegmentSim");
        Simulation instance = new Simulation(reseau, deb, fin);
        instance.EvalTempsTransitSegmentSim();
        assertTrue(instance.getReseau().getCarte().getListPoints().get(0).getListSegment().get(0).getTempsTransitSimulation() <= 3);
        assertTrue(instance.getReseau().getCarte().getListPoints().get(0).getListSegment().get(0).getTempsTransitSimulation() >= 1);
    }


    @Test
    public void testGenererListeTotaleBus() throws Exception {
        System.out.println("genererListeTotaleBus");
        Date hrDebSim = null;
        Date hrFinSim = null;
        Simulation instance = null;
        instance.genererListeTotaleBus(hrDebSim, hrFinSim);
        // TODO review the generated test code and remove the default call to fail.
        assertTrue(true);
    }

    @Test
    public void testGetTempsTraverserAS() throws Exception {
        System.out.println("getTempsTraverserAS");
        Circuit circ = null;
        Simulation instance = null;
        long expResult = 0L;
        long result = instance.getTempsTraverserAS(circ);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

 
    @Test
    public void testGenererListeBus() throws Exception {
        System.out.println("genererListeBus");
        Date debSim = null;
        Date finSim = null;
        Circuit circ = null;
        Simulation instance = null;
        instance.genererListeBus(debSim, finSim, circ);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
    @Test
    public void testGetEmplacement() throws Exception {
        System.out.println("getEmplacement");
        Simulation instance = new Simulation(reseau, deb, fin);
        Bus bus = instance.getReseau().getListCircuits().get(0).getListBus().get(0);//premier bus du premier circuit
        Date hr = deb;
        Coordonnee expResult = instance.getReseau().getListCircuits().get(0).getListPointsCircuit().get(0).getCoordPixels();
        Coordonnee result = instance.getEmplacement(bus, hr);
        assertEquals(expResult, result);
    }

    @Test
    public void testloopGetEmplacement() throws Exception {
        System.out.println("getEmplacement");
        Simulation instance = new Simulation(reseau, deb, fin);
        Bus bus = instance.getReseau().getListCircuits().get(1).getListBus().get(0);//premier bus du 2e circuit
        Date hr = deb;
        Coordonnee expResult = instance.getReseau().getListCircuits().get(1).getListPointsCircuit().get(0).getCoordPixels();
        Coordonnee result = instance.getEmplacement(bus, hr);
        assertEquals(expResult, result);
    }
    

    @Test
    public void testRandomGetEmplacement() throws Exception {
        System.out.println("getEmplacement");
        Simulation instance = new Simulation(reseau, deb, fin);
        Bus bus = instance.getReseau().getListCircuits().get(0).getListBus().get(0);//premier bus du 1e circuit
        Date hr = new Date(deb.getTime() + 150000);
        Coordonnee expResult = new Coordonnee(1.25, 1.25);
        Coordonnee result = instance.getEmplacement(bus, hr);
        assertEquals(expResult.getLat(), result.getLat(), 0);
        assertEquals(expResult.getLon(), result.getLon(), 0);
    }
    

    @Test
    public void testRandomGetEmplacementloop() throws Exception {
        System.out.println("getEmplacementloop");
        Simulation instance = new Simulation(reseau, deb, fin);
        Bus bus = instance.getReseau().getListCircuits().get(1).getListBus().get(0);//premier bus du 1e circuit
        Date hr = new Date(deb.getTime() + 600000);
        Coordonnee expResult = new Coordonnee(1.25, 1.25);
        Coordonnee result = instance.getEmplacement(bus, hr);
        assertEquals(expResult.getLat(), result.getLat(), 0);
        assertEquals(expResult.getLon(), result.getLon(), 0);
    }

 
    @Test
    public void testGetListeTotaleBus()throws InterruptedException, SimulExcept{
        System.out.println("getListeTotaleBus");
        Simulation instance = new Simulation(reseau, deb, fin);
        ArrayList<Bus> expResult = null;
        ArrayList<Bus> result = instance.getListeTotaleBus();
        assertEquals(expResult, result);
        
    }

  
    @Test
    public void testGenererListeTotalePass() throws Exception {
        System.out.println("genererListeTotalePass");
        Date debSim = null;
        Date finSim = null;
        Simulation instance = null;
        instance.genererListeTotalePass(debSim, finSim);
        // TODO review the generated test code and remove the default call to fail.
        assertTrue(true);
    }
*/
    @Test
    public void testGenererListePassagers() throws Exception {
        System.out.println("genererListePassagers");
        Simulation instance = new Simulation(reseau, deb, fin, "simutets");
        //Itineraire iti = reseau.getListItineraires().get(0);
        //instance.genererListePassagers(debSim, finSim, iti);
        //Itineraire iti2 = reseau.getListItineraires().get(1);
        //instance.genererListePassagers(debSim, finSim, iti2);
        ArrayList<Statistique> arSta = instance.getStatsIti();
        saveSim("allo.sim", instance);
        Simulation simLoade = loadSim("allo.sim");
        
        assertTrue(true);
    }
/*
    @Test
    public void testMajListesPass() throws Exception {
        System.out.println("majListesPass");
        Date hr = null;
        Simulation instance = null;
        instance.majListesPass(hr);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    @Test
    public void testGetNbBusEnRoute() {
        System.out.println("getNbBusEnRoute");
        Circuit circ = null;
        Date temps = null;
        Simulation instance = null;
        int expResult = 0;
        int result = instance.getNbBusEnRoute(circ, temps);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
*/
}
