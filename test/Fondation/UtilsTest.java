/*
 * Ce code est la propriete de l'empereur Alexandre Fortin
 * Each line should be prefixed with  * 
 */
package Fondation;

import InfrastructureMetier.Coordonnee;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class UtilsTest {
    
    public UtilsTest() {
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
     * Test of genererTempsTriangulaire method, of class Utils.
     */
    @Test
    public void testGenererTempsTriangulaire() throws Exception {
        System.out.println("genererTempsTriangulaire");
        double min = 0.0;
        double mode = 0.0;
        double max = 0.0;
        double expResult = 0.0;
        double result = Utils.genererTempsTriangulaire(min, mode, max);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of MesurerDistanceCoordonnees method, of class Utils.
     */
    @Test
    public void testMesurerDistanceCoordonnees() {
        System.out.println("MesurerDistanceCoordonnees");
        double lat1 = 0.0;
        double lon1 = 0.0;
        double lat2 = 0.0;
        double lon2 = 0.0;
        double expResult = 0.0;
        double result = Utils.MesurerDistanceCoordonnees(lat1, lon1, lat2, lon2);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of TrouverCoord method, of class Utils.
     */
    @Test
    public void testTrouverCoord() throws Exception {
        System.out.println("TrouverCoord");
        Coordonnee p1 = null;
        Coordonnee p2 = null;
        double ratioDistSurDistTotale = 0.0;
        Coordonnee expResult = null;
        Coordonnee result = Utils.TrouverCoord(p1, p2, ratioDistSurDistTotale);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of convertPixelToCoord method, of class Utils.
     * @throws java.lang.Exception
     */
    @Test
    public void testConvertPixelToCoord() throws Exception {
        System.out.println("convertPixelToCoord");
        double x = 800;
        double y = 500;
        double lat = -71.320920;
        double lon = 46.759400;
        Utils instance = new Utils();
        Coordonnee result = instance.convertPixelToCoord(x, y);
        assertEquals("test latitude",lat,result.getLat(),0);
        assertEquals("test longitude",lon,result.getLon(),0);
    }

    /**
     * Test of convertCoordToPixel method, of class Utils.
     */
    @Test
    public void testConvertCoordToPixel() throws Exception {
        System.out.println("convertCoordToPixel");
        double x = 800;
        double y = 500;
        double lat = -71.320920;
        double lon = 46.759400;
        Utils instance = new Utils();
        Coordonnee coord = new Coordonnee(-71.320920,46.759400);
        assertEquals("test latitude",x,instance.convertCoordToPixel(coord).getLat(),0);
        assertEquals("test longitude",y,instance.convertCoordToPixel(coord).getLon(),0);
    }
    
}
