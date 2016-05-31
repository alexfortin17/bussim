/*
 * Ce code est la propriete de l'empereur Alexandre Fortin
 * Each line should be prefixed with  * 
 */
package InfrastructureMetier;

import Fondation.SimulExcept;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jacinthe
 */
public class IntersectionTest {
    
    public IntersectionTest() {
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
/*
    @Test
    public void ConstrucIntCoord() throws SimulExcept{
        System.out.println("ConstrucIntCoord");
        double lat = 10.0;
        double lon = 18.0;
        Coordonnee x = new Coordonnee(lat, lon);
        Intersection inter = new Intersection(x);
        assertEquals(lat,inter.getCoordPixels().getLat(),0);
        assertEquals(lon,inter.getCoordPixels().getLon(),0);
    }
   
    
    @Test
    public void ConstrucInterAvecNom() throws SimulExcept{
        System.out.println("ConstrucInterAvecNom");
        double lat = 10.0;
        double lon = 18.0;
        String nom = "bonjour";
        Coordonnee x = new Coordonnee(lat, lon);
        Intersection inter = new Intersection(x, nom);
        String nomTest = inter.getNom();
        assertEquals(nomTest, nom);
    }
    
    @Test
    public void ConstrucCopieInter() throws SimulExcept{
        System.out.println("ConstrucCopieInter");
        double lat = 10.0;
        double lon = 18.0;
        String nom = "bonjour";
        Coordonnee x = new Coordonnee(lat, lon);
        Intersection inter = new Intersection(x, nom);
        Intersection interCopie = new Intersection(inter);
        
        //Coordonnee coordTest1 = inter.getCoordPixels();
        //Coordonnee coordTest2 = interCopie.getCoordPixels();
        
        //assertEquals(coordTest1, coordTest2);
        
        double latTest = interCopie.getCoordPixels().getLat();
        double lonTest = interCopie.getCoordPixels().getLon();
        String nomTest = interCopie.getNom();
        int id = inter.getId();
        int idTest = interCopie.getId();
        boolean interactuel = inter.getArret();
        boolean interCopieTest = interCopie.getArret();
        
        
        assertEquals(latTest, lat, 0);
        assertEquals(lonTest, lon, 0);
        assertEquals(nomTest, nom);
        assertEquals(idTest, id, 0);
        assertEquals(interCopieTest, interactuel);
    }
   */ 
}
