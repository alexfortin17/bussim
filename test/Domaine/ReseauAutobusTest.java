/*
 * Ce code est la propriete de l'empereur Alexandre Fortin
 * Each line should be prefixed with  * 
 */
package Domaine;

import Fondation.SimulExcept;
import InfrastructureMetier.Bus;
import InfrastructureMetier.Circuit;
import InfrastructureMetier.Coordonnee;
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
public class ReseauAutobusTest {
    //Carte en genre de x
    Graphe carte = new Graphe();
    ArrayList<Point> line = new ArrayList<Point>();
    ArrayList<Point> line2 = new ArrayList<Point>();
    ArrayList<Point> loopo = new ArrayList<Point>();
    
    public ReseauAutobusTest() throws SimulExcept{
        
    }
    
    @BeforeClass
    public static void setUpClass() throws SimulExcept{
        
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws SimulExcept{
        
        //Graphe carte = new Graphe();
        carte.ajoutArret(new Coordonnee(1.0, 1.0), "0");
        carte.ajoutArret(new Coordonnee(2.0, 2.0), "1");
        carte.ajoutArret(new Coordonnee(3.0, 3.0), "2");
        carte.ajoutArret(new Coordonnee(4.0, 4.0), "3");
        carte.ajoutArret(new Coordonnee(5.0, 5.0), "4");
        carte.ajoutArret(new Coordonnee(6.0, 6.0), "5");
        carte.ajoutArret(new Coordonnee(1.0, 6.0), "6");
        carte.ajoutArret(new Coordonnee(6.0, 1.0), "7");
        ArrayList<Point> ListPt = carte.getListPoints();
        carte.ajoutSegment(ListPt.get(0), ListPt.get(1), 2, 2, 2);
        carte.ajoutSegment(ListPt.get(1), ListPt.get(2), 2, 2, 2);
        carte.ajoutSegment(ListPt.get(2), ListPt.get(3), 2, 2, 2);
        carte.ajoutSegment(ListPt.get(3), ListPt.get(4), 2, 2, 2);
        carte.ajoutSegment(ListPt.get(4), ListPt.get(5), 2, 2, 2);
        carte.ajoutSegment(ListPt.get(5), ListPt.get(6), 2, 2, 2);
        carte.ajoutSegment(ListPt.get(7), ListPt.get(5), 2, 2, 2);
        carte.ajoutSegment(ListPt.get(6), ListPt.get(3), 2, 2, 2);
        carte.ajoutSegment(ListPt.get(6), ListPt.get(0), 2, 2, 2);
        carte.ajoutSegment(ListPt.get(7), ListPt.get(2), 2, 2, 2);
        carte.ajoutSegment(ListPt.get(0), ListPt.get(7), 2, 2, 2);
        line.add(ListPt.get(0));
        line.add(ListPt.get(1));
        line.add(ListPt.get(2));
        line.add(ListPt.get(3));
        line.add(ListPt.get(4));
        line.add(ListPt.get(5));
        loopo.add(ListPt.get(6));
        loopo.add(ListPt.get(0));
        loopo.add(ListPt.get(7));
        loopo.add(ListPt.get(5));
        loopo.add(ListPt.get(6));
        line2.add(ListPt.get(0));
        line2.add(ListPt.get(7));
        line2.add(ListPt.get(5));
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of creerCircuit method, of class ReseauAutobus.
     */
    @Test
    public void testCreerCircuit_7args() throws Exception {
        System.out.println("creerCircuit");
        String nom = "w";
        boolean loop = false;
        ArrayList<Point> listPoints = line;
        ArrayList<Point> listPoints2 = loopo;
        double min = 0.0;
        double mode = 4.0;
        double max = 7.0;
        Point src = line.get(0);
        Point src2 = loopo.get(0);
        ReseauAutobus instance = new ReseauAutobus(carte);
        //instance.creerCircuit(nom, loop, listPoints, min, mode, max);
       // instance.creerCircuit("p", true, listPoints2, min, mode, max);
        
        assertEquals(instance.getListCircuits().size(), 2);
    }

    /**
     * Test of creerCircuit method, of class ReseauAutobus.
     */
    @Test
    public void testCreerCircuit_8args() throws Exception {
        System.out.println("creerCircuit");
        String nom = "46";
        boolean loop = false;
        ArrayList<Point> listPoints = line;
        ArrayList<Point> listPoints2 = loopo;
        double min = 0.0;
        double mode = 4.0;
        double max = 7.0;
        int nbBusMax = 5;
        ReseauAutobus instance = new ReseauAutobus(carte);
        //instance.creerCircuit(nom, loop, listPoints, min, mode, max, nbBusMax);
        //instance.creerCircuit("p", true, listPoints2, min, mode, max, nbBusMax);
        //instance.creerCircuit("rfb", false, line2, min, mode, max, nbBusMax);
        
        assertEquals(instance.getListCircuits().size(), 3);
        
    }

    /**
     * Test of modifierNomCircuit method, of class ReseauAutobus.
     */
    @Test
    public void testModifierNomCircuit() throws Exception {
        System.out.println("modifierNomCircuit");
        String nom = "46";
        boolean loop = false;
        ArrayList<Point> listPoints = line;
        ArrayList<Point> listPoints2 = loopo;
        double min = 0.0;
        double mode = 4.0;
        double max = 7.0;
        Point src = line.get(0);
        Point src2 = loopo.get(0);
        Point src3 = line2.get(0);
        int nbBusMax = 5;
        ReseauAutobus instance = new ReseauAutobus(carte);
        //instance.creerCircuit(nom, loop, listPoints, min, mode, max);
        //instance.creerCircuit("p", true, listPoints2, min, mode, max);
        //instance.creerCircuit("rfb", false, line2, min, mode, max);
        Circuit circuit = null;
        String newnom = "vomi";
        instance.modifierNomCircuit(instance.getListCircuits().get(0), newnom);
        
        assertEquals(instance.getListCircuits().get(0).getNomCircuit(), "vomi");
    }

    /**
     * Test of modifierCircuit method, of class ReseauAutobus.
     */
    @Test
    public void testModifierCircuit() throws Exception {
        System.out.println("modifierCircuit");
        Circuit circuit = null;
        ArrayList<Point> listPts = null;
        ReseauAutobus instance = null;
        instance.modifierCircuit(circuit, listPts);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of supprCircuit method, of class ReseauAutobus.
     */
    @Test
    public void testSupprCircuit() {
        System.out.println("supprCircuit");
        Circuit circuit = null;
        ReseauAutobus instance = null;
        instance.supprCircuit(circuit);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ValidationAllerSimple method, of class ReseauAutobus.
     */
    @Test
    public void testValidationAllerSimple() {
        System.out.println("ValidationAllerSimple");
        ArrayList<Point> listPoints = null;
        ReseauAutobus instance = null;
        boolean expResult = false;
        boolean result = instance.ValidationAllerSimple(listPoints);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ValidationLoop method, of class ReseauAutobus.
     */
    @Test
    public void testValidationLoop() {
        System.out.println("ValidationLoop");
        ArrayList<Point> listPoints = null;
        ReseauAutobus instance = null;
        boolean expResult = false;
        boolean result = instance.ValidationLoop(listPoints);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

  

    /**
     * Test of genererListeTotalePassagers method, of class ReseauAutobus.
     */
    @Test
    public void testGenererListeTotalePassagers() {
        System.out.println("genererListeTotalePassagers");
        Date hrDebSim = null;
        Date hrFinSim = null;
        ReseauAutobus instance = null;
        instance.genererListeTotalePassagers(hrDebSim, hrFinSim);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
}
