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
import java.util.Date;

/**
 *
 * @author Gabriel
 */
public class AllerSimpleTest {
    
    public AllerSimpleTest() {
        ArrayList<Point> ListPoints = new ArrayList();
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
    public void ConstrucAllerSimpleTest() throws SimulExcept{
        System.out.println("ConstrucAllerSimpleTest");
        String nom = "allerSimple";
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
        AllerSimple allsimple = new AllerSimple(nom, ListPoints, min, mod, max);
        
        int listSize = ListPoints.size();
        int nombreTest = 4;
        double minTest = allsimple.getMin();
        double maxTest = allsimple.getMax();
        double modTest = allsimple.getMode();
        String nomTest = allsimple.getNomCircuit();
        boolean loop = false;
        
        assertEquals(allsimple.isLoop(), loop);
        assertEquals(nomTest, nom);
        assertEquals(min, minTest, 0);
        assertEquals(max, maxTest, 0);
        assertEquals(mod, modTest, 0);
        assertEquals(listSize, nombreTest);
    }
    @Test
    public void ConstrucAllerSimpleMAXCAPACITYTest() throws SimulExcept{
        System.out.println("ConstrucAllerSimpleMAXCAPACITYTest");
        String nom = "allerSimple";
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
        AllerSimple allsimple = new AllerSimple(nom, ListPoints, min, mod, max, maxCapacityBus);
        int capacityTest = allsimple.getCapaciteMaxCircuit();
        assertEquals(capacityTest, maxCapacityBus);
        
        
    }
    
    @Test
    public void AddBusPositif() throws SimulExcept, InterruptedException{
        System.out.println("AddBusPositif");
        
        String nom = "allerSimple";
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
        AllerSimple allsimple = new AllerSimple(nom, ListPoints, min, mod, max, maxCapacityBus);
        Bus autobus = new Bus(hrdepart, hrarrive, allsimple, new Coordonnee(1.0, 1.0));
        
        allsimple.AddBus(autobus);
        
  
    }
    
    @Test(expected=SimulExcept.class)
    public void AddBusNegatif() throws SimulExcept, InterruptedException{
        System.out.println("AddBusNegatif");
        
        String nom = "allerSimple";
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
        AllerSimple allsimple = new AllerSimple(nom, ListPoints, min, mod, max, maxCapacityBus);
        Bus autobus = new Bus(hrdepart, hrarrive, allsimple, new Coordonnee(1.0, 1.0));
        Bus autobus2 = new Bus(hrdepart, hrarrive, allsimple, new Coordonnee(2.0, 2.0));
        
        allsimple.AddBus(autobus);
        int size = allsimple.compteurBus;
        System.out.println(size);
        allsimple.AddBus(autobus2);
        
    }
    
    @Test
    public void AddBusAllerSimpleSansCapaMaxPositif() throws SimulExcept, InterruptedException{
        System.out.println("AddBusAllerSimpleSansCapaMaxPositif");
        
        String nom = "allerSimple";
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
        AllerSimple allsimple = new AllerSimple(nom, ListPoints, min, mod, max);
        Bus autobus = new Bus(hrdepart, hrarrive, allsimple, new Coordonnee(1.0, 1.0));
        
        allsimple.AddBus(autobus);
        int size = allsimple.compteurBus;
        System.out.println(size);
    }
    
    
    @Test(expected=SimulExcept.class)
    public void DelBusNegatif() throws SimulExcept{
        System.out.println("DelBusNegatif");
        
        String nom = "allerSimple";
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
        AllerSimple allsimple = new AllerSimple(nom, ListPoints, min, mod, max, maxCapacityBus);
        Bus autobus = new Bus(hrdepart, hrarrive, allsimple, new Coordonnee(1.0, 1.0));
        
        allsimple.delBus();
        
  
    }
    
    @Test
    public void DelBusPositif() throws SimulExcept, InterruptedException{
        System.out.println("DelBusPositif");
        
        String nom = "allerSimple";
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
        AllerSimple allsimple = new AllerSimple(nom, ListPoints, min, mod, max, maxCapacityBus);
        Bus autobus = new Bus(hrdepart, hrarrive, allsimple, new Coordonnee(1.0, 1.0));
        
        allsimple.AddBus(autobus);
        allsimple.delBus();
    }
   */ 
}
