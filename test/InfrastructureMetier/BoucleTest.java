/*
 * Ce code est la propriete de l'empereur Alexandre Fortin
 * Each line should be prefixed with  * 
 */
package InfrastructureMetier;

import Fondation.SimulExcept;
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
 * @author Gabriel
 */
public class BoucleTest {
    
    public BoucleTest() {
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
    public void ConstrucBoucleTest() throws SimulExcept{
        System.out.println("ConstrucAllerSimpleTest");
        String nom = "boucle";
        Arret arret1 = new Arret(new Coordonnee(1.0, 1.0));
        Arret arret2 = new Arret(new Coordonnee(2.0, 2.0));
        Arret arret3 = new Arret(new Coordonnee(3.0, 3.0));
        Arret arret4 = new Arret(new Coordonnee(4.0, 4.0));
        ArrayList<Point> ListPoints = new ArrayList();
        ListPoints.add(arret1);
        ListPoints.add(arret2);
        ListPoints.add(arret3);
        ListPoints.add(arret4);
        double min = 10;
        double max = 20;
        double mod = 15;
        Boucle boucle = new Boucle(nom, ListPoints, min, mod, max);
        
        int listSize = ListPoints.size();
        int nombreTest = 4;
        double minTest = boucle.getMin();
        double maxTest = boucle.getMax();
        double modTest = boucle.getMode();
        String nomTest = boucle.getNomCircuit();
        boolean loop = true;
        
        assertEquals(boucle.isLoop(), loop);
        
        assertEquals(nomTest, nom);
        assertEquals(min, minTest, 0);
        assertEquals(max, maxTest, 0);
        assertEquals(mod, modTest, 0);
        assertEquals(listSize, nombreTest);
    }
    @Test
    public void ConstrucBoucleMAXCAPACITYTest() throws SimulExcept{
        System.out.println("ConstrucAllerSimpleMAXCAPACITYTest");
        String nom = "boucle";
        Arret arret1 = new Arret(new Coordonnee(1.0, 1.0));
        Arret arret2 = new Arret(new Coordonnee(2.0, 2.0));
        Arret arret3 = new Arret(new Coordonnee(3.0, 3.0));
        Arret arret4 = new Arret(new Coordonnee(4.0, 4.0));
        ArrayList<Point> ListPoints = new ArrayList();
        ListPoints.add(arret1);
        ListPoints.add(arret2);
        ListPoints.add(arret3);
        ListPoints.add(arret4);
        double min = 10;
        double max = 20;
        double mod = 15;
        int maxCapacityBus = 10;
        Boucle boucle = new Boucle(nom, ListPoints, min, mod, max, maxCapacityBus);
        int capacityTest = boucle.getCapaciteMaxCircuit();
        assertEquals(capacityTest, maxCapacityBus);
        
        
    }
    
    @Test
    public void AddBusBouclePositif() throws SimulExcept, InterruptedException{
        System.out.println("AddBusBouclePositif");
        
        String nom = "boucle";
        Arret arret1 = new Arret(new Coordonnee(1.0, 1.0));
        Arret arret2 = new Arret(new Coordonnee(2.0, 2.0));
        Arret arret3 = new Arret(new Coordonnee(3.0, 3.0));
        Arret arret4 = new Arret(new Coordonnee(4.0, 4.0));
        ArrayList<Point> ListPoints = new ArrayList();
        ListPoints.add(arret1);
        ListPoints.add(arret2);
        ListPoints.add(arret3);
        ListPoints.add(arret4);
        double min = 10;
        double mod = 15;
        double max = 20;
        int maxCapacityBus = 2;
        Date hrdepart = new Date(10);
        Date hrarrive = new Date(11);
        Boucle boucle = new Boucle(nom, ListPoints, min, mod, max, maxCapacityBus);
        Bus autobus = new Bus(hrdepart, hrarrive, boucle, new Coordonnee(1.0, 1.0));
        
        boucle.AddBus(autobus);
        
  
    }
    
    @Test(expected=SimulExcept.class)
    public void AddBusBoucleNegatif() throws SimulExcept, InterruptedException{
        System.out.println("AddBusBoucleNegatif");
        
        String nom = "boucle";
        Arret arret1 = new Arret(new Coordonnee(1.0, 1.0));
        Arret arret2 = new Arret(new Coordonnee(2.0, 2.0));
        Arret arret3 = new Arret(new Coordonnee(3.0, 3.0));
        Arret arret4 = new Arret(new Coordonnee(4.0, 4.0));
        ArrayList<Point> ListPoints = new ArrayList();
        ListPoints.add(arret1);
        ListPoints.add(arret2);
        ListPoints.add(arret3);
        ListPoints.add(arret4);
        double min = 10;
        double mod = 15;
        double max = 20;
        int maxCapacityBus = 1;
        Date hrdepart = new Date(10);
        Date hrarrive = new Date(11);
        Boucle boucle = new Boucle(nom, ListPoints, min, mod, max, maxCapacityBus);
        Bus autobus = new Bus(hrdepart, hrarrive, boucle, new Coordonnee(1.0, 1.0));
        Bus autobus2 = new Bus(hrdepart, hrarrive, boucle, new Coordonnee(2.0, 2.0));
        
        boucle.AddBus(autobus);
        int size = boucle.compteurBus;
        System.out.println(size);
        boucle.AddBus(autobus2);
        
    }
    
    @Test
    public void AddBusBoucleSansCapaMaxPositif() throws SimulExcept, InterruptedException{
        System.out.println("AddBusBoucleSansCapaMaxPositif");
        
        String nom = "boucle";
        Arret arret1 = new Arret(new Coordonnee(1.0, 1.0));
        Arret arret2 = new Arret(new Coordonnee(2.0, 2.0));
        Arret arret3 = new Arret(new Coordonnee(3.0, 3.0));
        Arret arret4 = new Arret(new Coordonnee(4.0, 4.0));
        ArrayList<Point> ListPoints = new ArrayList();
        ListPoints.add(arret1);
        ListPoints.add(arret2);
        ListPoints.add(arret3);
        ListPoints.add(arret4);
        double min = 10;
        double mod = 15;
        double max = 20;
        Date hrdepart = new Date(10);
        Date hrarrive = new Date(11);
        Boucle boucle = new Boucle(nom, ListPoints, min, mod, max);
        Bus autobus = new Bus(hrdepart, hrarrive, boucle, new Coordonnee(1.0, 1.0));
        
        boucle.AddBus(autobus);
        int nbBus = boucle.compteurBus;
        System.out.println(nbBus);
    }
  */  
}
