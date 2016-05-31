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
public class SegmentTest {
    
    public SegmentTest() {
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
    public void ConstrucPointArrSegment() throws SimulExcept{
        System.out.println("ConstrucPointArrSegment");
        double tempsMin = 10;
        double tempsMode = 12;
        double tempsMax = 15;
        
        double latD = 10.0;
        double lonD = 10.0;
        Coordonnee x = new Coordonnee(latD, lonD);
        Arret pointD = new Arret(x);
        
        double latA = 18.0;
        double lonA = 18.0;
        Coordonnee y = new Coordonnee(latA, lonA);
        Arret pointA = new Arret(y);
        
        Segment seg = new Segment(pointD,pointA,tempsMin,tempsMode,tempsMax);
        
        double minTest = seg.getTempsMin();
        double maxTest = seg.getTempsMax();
        double modTest = seg.getTempsMode();
        Point pointDepart = seg.getDepart();
        Point pointArrivee = seg.getArrivee();
        pointDepart.setArret(true);
        pointArrivee.setArret(true);
        
        assertTrue(pointDepart == pointD);
        assertTrue(pointArrivee == pointA);
        assertTrue(minTest == tempsMin);
        assertTrue(modTest == tempsMode);
        assertTrue(maxTest == tempsMax);
      
    }
    
    @Test//(expected=SimulExcept.class)
    public void ConstrucCopieSegment() throws SimulExcept{
        System.out.println("ConstrucCopieSegment");
        double tempsMin = 10;
        double tempsMode = 12;
        double tempsMax = 15;
        
        double latD = 10.0;
        double lonD = 10.0;
        Coordonnee x = new Coordonnee(latD, lonD);
        Arret pointD = new Arret(x);
        
        double latA = 18.0;
        double lonA = 18.0;
        Coordonnee y = new Coordonnee(latA, lonA);
        Arret pointA = new Arret(y);
        
        Segment seg = new Segment(pointD,pointA,tempsMin,tempsMode,tempsMax);
        
        Segment segCopie = new Segment(seg);
        
        
        Point pointCopieDepart = segCopie.getDepart();
        Point pointCopieArrivee = segCopie.getArrivee();
        pointCopieDepart.setArret(true);
        pointCopieArrivee.setArret(true);
        
        double minTempsCopie = segCopie.getTempsMin();
        double modTempsCopie = segCopie.getTempsMode();
        double maxTempsCopie = segCopie.getTempsMax();
        
        //double temps = segCopie.getTempsTransitSimulation();

        assertEquals(pointCopieDepart, pointD);
        assertEquals(pointCopieArrivee, pointA);
        assertEquals(minTempsCopie, tempsMin, 0);
        assertEquals(maxTempsCopie, tempsMax, 0);
        assertEquals(modTempsCopie, tempsMode, 0);
        //assertTrue(temps == seg.getTempsTransitSimulation());
    }
    
    @Test(expected=SimulExcept.class)
    public void getTempsTransitSimulationTestNegatif() throws SimulExcept{
        System.out.println("getTempsTransitSimulationTestNegatif");
        double tempsMin = 10;
        double tempsMode = 12;
        double tempsMax = 15;
        
        double latD = 10.0;
        double lonD = 10.0;
        Coordonnee x = new Coordonnee(latD, lonD);
        Arret pointD = new Arret(x);
        
        double latA = 18.0;
        double lonA = 18.0;
        Coordonnee y = new Coordonnee(latA, lonA);
        Arret pointA = new Arret(y);
        
        Segment seg = new Segment(pointD,pointA,tempsMin,tempsMode,tempsMax);
        seg.setTempsTransit(-1);
        double temps = seg.getTempsTransitSimulation();
    }
    
    @Test
    public void getTempsTransitSimulationTestPositif() throws SimulExcept{
        System.out.println("getTempsTransitSimulationTestPositif");
        double tempsMin = 10;
        double tempsMode = 12;
        double tempsMax = 15;
        
        double latD = 10.0;
        double lonD = 10.0;
        Coordonnee x = new Coordonnee(latD, lonD);
        Arret pointD = new Arret(x);
        
        double latA = 18.0;
        double lonA = 18.0;
        Coordonnee y = new Coordonnee(latA, lonA);
        Arret pointA = new Arret(y);
        
        Segment seg = new Segment(pointD,pointA,tempsMin,tempsMode,tempsMax);
        seg.setTempsTransit(10);
        double temps = seg.getTempsTransitSimulation();
    }
    
    
    @Test
    public void CalculTempsTransitTestPositif() throws SimulExcept{
        System.out.println("CalculTempsTransitTestPositif");
        double tempsMin = 10;
        double tempsMode = 12;
        double tempsMax = 15;
        
        double latD = 10.0;
        double lonD = 10.0;
        Coordonnee x = new Coordonnee(latD, lonD);
        Arret pointD = new Arret(x);
        
        double latA = 18.0;
        double lonA = 18.0;
        Coordonnee y = new Coordonnee(latA, lonA);
        Arret pointA = new Arret(y);
        
        Segment seg = new Segment(pointD,pointA,tempsMin,tempsMode,tempsMax);
        seg.CalculTempsTransit();
        double temps = seg.getTempsTransitSimulation();
        String tempsString = String.valueOf(temps);
        System.out.println(tempsString);
    }
   */     
}
