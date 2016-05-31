/*
 * Ce code est la propriete de l'empereur Alexandre Fortin
 * Each line should be prefixed with  * 
 */
package InfrastructureMetier;

import Fondation.SimulExcept;
import java.util.ArrayList;
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
public class PointTest {
    
    public PointTest() {
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
    public void setArretTest() throws SimulExcept{
        System.out.println("setArretTest");
        double lat = 10.0;
        double lon = 18.0;
        Coordonnee x = new Coordonnee(lat, lon);
        Arret arret = new Arret(x);
        arret.setArret(true);
        boolean arretTest = arret.getArret();
        assertTrue(arretTest == true);
    }
    @Test
    public void creerSegmentTest() throws SimulExcept{
        System.out.println("creerSegmentTest");
        double lat = 10.0;
        double lon = 10.0;
        Coordonnee x = new Coordonnee(lat, lon);
        Arret arret = new Arret(x);
        double lat2 = 20.0;
        double lon2 = 20.0;
        Coordonnee y = new Coordonnee(lat2, lon2);
        Arret arretA = new Arret(y);
        arret.creerSegment(arretA, 10, 12, 15);
        int sizeTest = arret.getListSegment().size();
        assertTrue(sizeTest == 1);
    }
    
    @Test
    public void modifierSegmentTest() throws SimulExcept{
        System.out.println("modifierSegmentTest");
        Arret arretD = new Arret(new Coordonnee(10.0, 10.0));
        Arret arretA = new Arret(new Coordonnee(20.0, 20.0));
        double min = 10.0;
        double mod = 12.0;
        double max = 15.0;
        
        arretD.creerSegment(arretA, min, mod, max);
        arretD.modifSegment(arretA, 12, 15, 20);
        Segment test = arretD.getListSegment().get(0);
        double min1 = test.getTempsMin();
        double max1 = test.getTempsMax();
        double mod1 = test.getTempsMode();
        
        assertEquals(min1,12,0);
        assertEquals(mod1,15,0);
        assertEquals(max1,20,0);
        
    }
    @Test
    public void supprimerSegmentTest() throws SimulExcept{
        System.out.println("supprimerSegmentTest");
        Arret arretD = new Arret(new Coordonnee(10.0, 10.0));
        Arret arretA = new Arret(new Coordonnee(20.0, 20.0));
        Arret arretA2 = new Arret(new Coordonnee(15.0, 25.0));
        double min = 10.0;
        double mod = 12.0;
        double max = 15.0;
        
        arretD.creerSegment(arretA, min, mod, max);
        arretD.creerSegment(arretA2, min, mod, max);
        arretD.SupprSegment(arretA);
        int size = arretD.getListSegment().size();
        
        assertEquals(1,size,0);
        
    }
    @Test
    public void GetPointDsRangePixelTrueTest() throws SimulExcept{
        System.out.println("GetPointDsRangePixelTrueTest");
        double lat = 10.0;
        double lon = 10.0;
        Coordonnee x = new Coordonnee(lat, lon);
        Arret arret = new Arret(x);
        Point result = arret.GetPointDsRangePixel(8, 8);
        assertTrue(result.getCoordPixels().getLat()-5 <= 8 && result.getCoordPixels().getLat()+5 >= 8);
        assertTrue(result.getCoordPixels().getLon()-5 <= 8 && result.getCoordPixels().getLon()+5 >= 8);
        
    } 
    
    
    @Test
    public void GetPointDsRangePixelTrue2Test() throws SimulExcept{
        System.out.println("GetPointDsRangePixelTrue2Test");
        double lat = 10.0;
        double lon = 10.0;
        Coordonnee x = new Coordonnee(lat, lon);
        Arret arret = new Arret(x);
        Point result = arret.GetPointDsRangePixel(12, 12);
        assertTrue(result.getCoordPixels().getLat()-5 <= 12 && result.getCoordPixels().getLat()+5 >= 12);
        assertTrue(result.getCoordPixels().getLon()-5 <= 12 && result.getCoordPixels().getLon()+5 >= 12);
        
    } 
    
    @Test
    public void GetPointDsRangePixelTrue3Test() throws SimulExcept{
        System.out.println("GetPointDsRangePixelTrue3Test");
        double lat = 10.0;
        double lon = 10.0;
        Coordonnee x = new Coordonnee(lat, lon);
        Arret arret = new Arret(x);
        Point result = arret.GetPointDsRangePixel(8, 12);
        assertTrue(result.getCoordPixels().getLat()-5 <= 8 && result.getCoordPixels().getLat()+5 >= 8);
        assertTrue(result.getCoordPixels().getLon()-5 <= 12 && result.getCoordPixels().getLon()+5 >= 12);
        
    } 
    
    @Test
    public void GetPointDsRangePixelTrue4Test() throws SimulExcept{
        System.out.println("GetPointDsRangePixelTrue4Test");
        double lat = 10.0;
        double lon = 10.0;
        Coordonnee x = new Coordonnee(lat, lon);
        Arret arret = new Arret(x);
        Point result = arret.GetPointDsRangePixel(12, 8);
        assertTrue(result.getCoordPixels().getLat()-5 <= 12 && result.getCoordPixels().getLat()+5 >= 12);
        assertTrue(result.getCoordPixels().getLon()-5 <= 8 && result.getCoordPixels().getLon()+5 >= 8);
        
    } 
    
    @Test
    public void GetPointDsRangePixelTrue5Test() throws SimulExcept{
        System.out.println("GetPointDsRangePixelTrue5Test");
        double lat = 10.0;
        double lon = 50.0;
        Coordonnee x = new Coordonnee(lat, lon);
        Arret arret = new Arret(x);
        Point result = arret.GetPointDsRangePixel(8, 47);
        assertTrue(result.getCoordPixels().getLat()-5 <= 8 && result.getCoordPixels().getLat()+5 >= 8);
        assertTrue(result.getCoordPixels().getLon()-5 <= 47 && result.getCoordPixels().getLon()+5 >= 47);
        
    } 
    
    @Test
    public void GetPointDsRangePixelFalseTest() throws SimulExcept{
        System.out.println("GetPointDsRangePixelFalseTest");
        double lat = 10.0;
        double lon = 10.0;
        Coordonnee x = new Coordonnee(lat, lon);
        Arret arret = new Arret(x);
        Point result = arret.GetPointDsRangePixel(4, 4);
        
        
    } 
    
    @Test
    public void GetPointDsRangePixelFalse1Test() throws SimulExcept{
        System.out.println("GetPointDsRangePixelFalse1Test");
        double lat = 10.0;
        double lon = 10.0;
        Coordonnee x = new Coordonnee(lat, lon);
        Arret arret = new Arret(x);
        Point result = arret.GetPointDsRangePixel(17, 17);
       
        
    } 
    
    @Test
    public void GetPointDsRangePixelFalse2Test() throws SimulExcept{
        System.out.println("GetPointDsRangePixelFalse2Test");
        double lat = 10.0;
        double lon = 10.0;
        Coordonnee x = new Coordonnee(lat, lon);
        Arret arret = new Arret(x);
        Point result = arret.GetPointDsRangePixel(4, 17);
       
    } 
    
    @Test
    public void GetPointDsRangePixelFalse3Test() throws SimulExcept{
        System.out.println("GetPointDsRangePixelFalse3Test");
        double lat = 10.0;
        double lon = 10.0;
        Coordonnee x = new Coordonnee(lat, lon);
        Arret arret = new Arret(x);
        Point result = arret.GetPointDsRangePixel(17, 4);
       
        
    } 
    
    @Test
    public void GetPointDsRangePixelFalse4Test() throws SimulExcept{
        System.out.println("GetPointDsRangePixelFalse4Test");
        double lat = 10.0;
        double lon = 50.0;
        Coordonnee x = new Coordonnee(lat, lon);
        Arret arret = new Arret(x);
        Point result = arret.GetPointDsRangePixel(17, 44);
     
    } 
    
    @Test
    public void GetPointDsRangePixelFalTruTest() throws SimulExcept{
        System.out.println("GetPointDsRangePixelFalTruTest");
        double lat = 10.0;
        double lon = 50.0;
        Coordonnee x = new Coordonnee(lat, lon);
        Arret arret = new Arret(x);
        Point result = arret.GetPointDsRangePixel(14, 44);
    } 
    
    
    @Test
    public void PointExisteDansRangePixelTest() throws SimulExcept{
        System.out.println("PointExisteDansRangePixelTest");
        double lat = 10.0;
        double lon = 10.0;
        Coordonnee x = new Coordonnee(lat, lon);
        Arret arret = new Arret(x);
        boolean result = arret.PointExisteDansRangePixel(6, 6);
        assertEquals(result, true);
        
        
    }
    
    @Test
    public void PointExisteDansRangePixelFalseTest() throws SimulExcept{
        System.out.println("PointExisteDansRangePixelFalseTest");
        double lat = 10.0;
        double lon = 10.0;
        Coordonnee x = new Coordonnee(lat, lon);
        Arret arret = new Arret(x);
        boolean result = arret.PointExisteDansRangePixel(3, 3);
        assertEquals(result, false);
        
        
    }
   */ 
}