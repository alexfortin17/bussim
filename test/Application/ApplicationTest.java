/*
 * Ce code est la propriete de l'empereur Alexandre Fortin
 * Each line should be prefixed with  * 
 */
package Application;

import InfrastructureMetier.Bus;
import InfrastructureMetier.Circuit;
import InfrastructureMetier.Coordonnee;
import InfrastructureMetier.Point;
import InfrastructureMetier.Segment;
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
public class ApplicationTest {
    
    public ApplicationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of verifClicPoint method, of class Application.
     */
    @Test
    public void testVerifClicPoint() throws Exception {
        System.out.println("verifClicPoint");
        double x = 0.0;
        double y = 0.0;
        Application instance = new Application();
        Point expResult = null;
        Point result = instance.verifClicPoint(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ajouterPoint method, of class Application.
     */
    @Test
    public void testAjouterPoint() throws Exception {
        System.out.println("ajouterPoint");
        double x = 0.0;
        double y = 0.0;
        boolean arret = false;
        String nom = "";
        Application instance = new Application();
        instance.ajouterPoint(x, y, arret, nom);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getListePoints method, of class Application.
     */
    @Test
    public void testGetListePoints() throws Exception {
        System.out.println("getListePoints");
        Application instance = new Application();
        ArrayList<Point> expResult = null;
        ArrayList<Point> result = instance.getListePoints();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPoint method, of class Application.
     */
    @Test
    public void testGetPoint() throws Exception {
        System.out.println("getPoint");
        double x = 0.0;
        double y = 0.0;
        Application instance = new Application();
        Point expResult = null;
        Point result = instance.getPoint(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ajouterSegment method, of class Application.
     */
    @Test
    public void testAjouterSegment() throws Exception {
        System.out.println("ajouterSegment");
        Point dep = null;
        Point arr = null;
        double min = 0.0;
        double mode = 0.0;
        double max = 0.0;
        Application instance = new Application();
        instance.ajouterSegment(dep, arr, min, mode, max);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getListePtArrPotentielsNouveauSegment method, of class Application.
     */
    @Test
    public void testGetListePtArrPotentielsNouveauSegment() throws Exception {
        System.out.println("getListePtArrPotentielsNouveauSegment");
        Point pt = null;
        Application instance = new Application();
        ArrayList<Point> expResult = null;
        ArrayList<Point> result = instance.getListePtArrPotentielsNouveauSegment(pt);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getListePtArr method, of class Application.
     */
    @Test
    public void testGetListePtArr() throws Exception {
        System.out.println("getListePtArr");
        Point pt = null;
        Application instance = new Application();
        ArrayList<Point> expResult = null;
        ArrayList<Point> result = instance.getListePtArr(pt);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getListSegContigu method, of class Application.
     */
    @Test
    public void testGetListSegContigu() throws Exception {
        System.out.println("getListSegContigu");
        Point pt = null;
        Application instance = new Application();
        ArrayList<Segment> expResult = null;
        ArrayList<Segment> result = instance.getListSegContigu(pt);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modifSeg method, of class Application.
     */
    @Test
    public void testModifSeg() throws Exception {
        System.out.println("modifSeg");
        Point ptDep = null;
        Point ptArr = null;
        double min = 0.0;
        double mode = 0.0;
        double max = 0.0;
        Application instance = new Application();
        instance.modifSeg(ptDep, ptArr, min, mode, max);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of supprSeg method, of class Application.
     */
    @Test
    public void testSupprSeg() throws Exception {
        System.out.println("supprSeg");
        Point ptDep = null;
        Point ptArr = null;
        Application instance = new Application();
        //instance.supprSeg(ptDep, ptArr);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of supprPoint method, of class Application.
     */
    @Test
    public void testSupprPoint() throws Exception {
        System.out.println("supprPoint");
        Point pt = null;
        Application instance = new Application();
        instance.supprPoint(pt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modifPoint method, of class Application.
     */
    @Test
    public void testModifPoint() throws Exception {
        System.out.println("modifPoint");
        Point pt = null;
        String nom = "";
        Application instance = new Application();
        instance.modifPoint(pt, nom);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of passageModeReseau method, of class Application.
     */
    @Test
    public void testPassageModeReseau() throws Exception {
        System.out.println("passageModeReseau");
        Application instance = new Application();
        //instance.passageModeReseau();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of creerCircuit method, of class Application.
     */
    @Test
    public void testCreerCircuit_5args() throws Exception {
        System.out.println("creerCircuit");
        String nom = "";
        ArrayList<Point> ListPtsCircuit = null;
        double min = 0.0;
        double mode = 0.0;
        double max = 0.0;
        Application instance = new Application();
        //instance.creerCircuit(nom, ListPtsCircuit, min, mode, max);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of creerCircuit method, of class Application.
     */
    @Test
    public void testCreerCircuit_6args() throws Exception {
        System.out.println("creerCircuit");
        String nom = "";
        ArrayList<Point> ListPtsCircuit = null;
        double min = 0.0;
        double mode = 0.0;
        double max = 0.0;
        int nbMaxBus = 0;
        Application instance = new Application();
        //instance.creerCircuit(nom, ListPtsCircuit, min, mode, max, nbMaxBus);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modifNomCircuit method, of class Application.
     */
    @Test
    public void testModifNomCircuit() throws Exception {
        System.out.println("modifNomCircuit");
        String nom = "";
        Circuit circ = null;
        Application instance = new Application();
        instance.modifNomCircuit(nom, circ);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modifParamCircuit method, of class Application.
     */
    @Test
    public void testModifParamCircuit() throws Exception {
        System.out.println("modifParamCircuit");
        Circuit circ = null;
        double min = 0.0;
        double mode = 0.0;
        double max = 0.0;
        Application instance = new Application();
        //instance.modifParamCircuit(circ, min, mode, max);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of supprCircuit method, of class Application.
     */
    @Test
    public void testSupprCircuit() throws Exception {
        System.out.println("supprCircuit");
        Circuit circ = null;
        Application instance = new Application();
        instance.supprCircuit(circ);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getListCircuit method, of class Application.
     */
    @Test
    public void testGetListCircuit() throws Exception {
        System.out.println("getListCircuit");
        Application instance = new Application();
        ArrayList<Circuit> expResult = null;
        ArrayList<Circuit> result = instance.getListCircuit();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getListTotaleBus method, of class Application.
     */
    @Test
    public void testGetListTotaleBus() throws Exception {
        System.out.println("getListTotaleBus");
        Application instance = new Application();
        ArrayList<Bus> expResult = null;
        //ArrayList<Bus> result = instance.getListTotaleBus();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCircuit method, of class Application.
     */
    @Test
    public void testGetCircuit() throws Exception {
        System.out.println("getCircuit");
        String nom = "";
        Application instance = new Application();
        Circuit expResult = null;
        Circuit result = instance.getCircuit(nom);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of LancerSim method, of class Application.
     */
    @Test
    public void testLancerSim() throws Exception {
        System.out.println("LancerSim");
        Date hrDep = null;
        Date hrArr = null;
        Application instance = new Application();
        //instance.LancerSim(hrDep, hrArr);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCoordBus method, of class Application.
     */
    @Test
    public void testGetCoordBus() throws Exception {
        System.out.println("getCoordBus");
        Bus bus = null;
        Date moment = null;
        Application instance = new Application();
        Coordonnee expResult = null;
        //Coordonnee result = instance.getCoordBus(bus, moment);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getListeTotaleBus method, of class Application.
     */
    @Test
    public void testGetListeTotaleBus() throws Exception {
        System.out.println("getListeTotaleBus");
        Application instance = new Application();
        ArrayList<Bus> expResult = null;
        //ArrayList<Bus> result = instance.getListeTotaleBus();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getListSegmentCirc method, of class Application.
     */
    @Test
    public void testGetListSegmentCirc() throws Exception {
        System.out.println("getListSegmentCirc");
        Circuit circ = null;
        Application instance = new Application();
        ArrayList<Segment> expResult = null;
        ArrayList<Segment> result = instance.getListSegmentCirc(circ);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
