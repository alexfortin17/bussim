/*
 * Ce code est la propriete de l'empereur Alexandre Fortin
 * Each line should be prefixed with  * 
 */
package my.SimulatHEURE;

import InfrastructureMetier.Coordonnee;
import Fondation.SimulExcept;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gabriel
 */
public class CoordonneeTest {
    
    
    public CoordonneeTest() {
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
    
    @Test
    public void ConstrucLonNeg() throws SimulExcept{
        System.out.println("ConstrucLonNegatifs");
        double lat = 10.0;
        double lon = -18.0;
        Coordonnee x = new Coordonnee(lat, lon);
        assertTrue(lat >= 0);
        assertFalse(lon >= 0);
    }
    @Test
    public void ConstrucLatNeg() throws SimulExcept{
        System.out.println("ConstrucLatNegatifs");
        double lat = -10.0;
        double lon = 18.0;
        Coordonnee x = new Coordonnee(lat, lon);
        assertFalse(lat >= 0);
        assertTrue(lon >= 0);
    }
    @Test
    public void ConstrucNeg() throws SimulExcept{
        System.out.println("ConstructNegatifs");
        double lat = -10.0;
        double lon = -18.0;
   
  
        Coordonnee x = new Coordonnee(lat, lon);
        assertFalse(lat >= 0);
        assertFalse(lon >= 0);
    }
    @Test
    public void ConstrucPos() throws SimulExcept{
        System.out.println("ConstructPositif");
        double lat = 10.0;
        double lon = 18.0;
   
  
        Coordonnee x = new Coordonnee(lat, lon);
        assertTrue(lat >= 0);
        assertTrue(lon >= 0);
    }
    
}
