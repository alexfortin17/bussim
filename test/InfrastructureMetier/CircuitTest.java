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
public class CircuitTest {
    
    ArrayList<Point> line = new ArrayList<Point>();
    ArrayList<Point> line2 = new ArrayList<Point>();
    ArrayList<Point> line3 = new ArrayList<Point>();
    ArrayList<Point> line4 = new ArrayList<Point>();
    
    public CircuitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws SimulExcept{
        Arret arret1 = new Arret(new Coordonnee(1.0, 1.0));
        Arret arret2 = new Arret(new Coordonnee(2.0, 2.0));
        Arret arret3 = new Arret(new Coordonnee(3.0, 3.0));
        Arret arret4 = new Arret(new Coordonnee(4.0, 4.0));
        Intersection intersection = new Intersection(new Coordonnee(1.0, 1.0));
        line.add(arret1);
        line.add(arret2);
        line.add(arret3);
        line.add(arret4);
        line2.add(arret1);
        line3.add(intersection);
        line3.add(arret2);
        line3.add(arret3);
        line3.add(arret4);
        line4.add(arret2);
        line4.add(arret3);
        line4.add(arret4);
    }
    
    @After
    public void tearDown() {
    }
   /* 
    @Test(expected=SimulExcept.class)
    public void ConstrucCircuitNomTest() throws SimulExcept{
        System.out.println("ConstrucCircuitNomTest");
        double min = 10;
        double max = 20;
        double mod = 15;
        String nom = "";
        AllerSimple circuit = new AllerSimple(nom, line, min, mod, max);
    }
    @Test(expected=SimulExcept.class)
    public void ConstrucCircuitCapacityTest() throws SimulExcept{
        System.out.println("ConstrucCircuitCapacityTest");
        double min = 10;
        double max = 20;
        double mod = 15;
        int maxCapacityBus = 0;
        String nom = "allo";
        AllerSimple circuit = new AllerSimple(nom, line, min, mod, max, maxCapacityBus);
    }
    
    @Test(expected=SimulExcept.class)
    public void setCircuitTestNegatif() throws SimulExcept{
        System.out.println("setCircuitTestNegatif");
        double min = 10;
        double max = 20;
        double mod = 15;
        int maxCapacityBus = 10;
        String nom = "allo";
        AllerSimple circuit = new AllerSimple(nom, line2, min, mod, max, maxCapacityBus);
        circuit.setCircuit(line2);
    }
    
    @Test
    public void setCircuitTestPositif() throws SimulExcept{
        System.out.println("setCircuitTestPositif");
        double min = 10;
        double max = 20;
        double mod = 15;
        int maxCapacityBus = 10;
        String nom = "allo";
        AllerSimple circuit = new AllerSimple(nom, line, min, mod, max, maxCapacityBus);
        circuit.setCircuit(line);
    }
    
    @Test
    public void setCapaciteMaxCircuitPositif() throws SimulExcept{
        System.out.println("setCapaciteMaxCircuitPositif");
        double min = 10;
        double max = 20;
        double mod = 15;
        int maxCapacityBus = 10;
        String nom = "allo";
        AllerSimple circuit = new AllerSimple(nom, line, min, mod, max);
        circuit.setCapaciteMaxCircuit(maxCapacityBus);
        
    }
    
    @Test(expected=SimulExcept.class)
    public void setCapaciteMaxCircuitNegatif() throws SimulExcept{
        System.out.println("setCapaciteMaxCircuitNegatif");
        double min = 10;
        double max = 20;
        double mod = 15;
        int maxCapacityBus = 0;
        String nom = "allo";
        AllerSimple circuit = new AllerSimple(nom, line, min, mod, max);
        circuit.setCapaciteMaxCircuit(maxCapacityBus);
    }
    
    @Test(expected=SimulExcept.class)
    public void setMinNegatif() throws SimulExcept{
        System.out.println("setMinNegatif");
        double min = 10;
        double max = 20;
        double mod = 15;
        String nom = "allo";
        AllerSimple circuit = new AllerSimple(nom, line, min, mod, max);
        circuit.setMin(0);
    }
    
    @Test(expected=SimulExcept.class)
    public void setModNegatif() throws SimulExcept{
        System.out.println("setModNegatif");
        double min = 10;
        double max = 20;
        double mod = 15;
        String nom = "allo";
        AllerSimple circuit = new AllerSimple(nom, line, min, mod, max);
        circuit.setMode(0);
    }
    
    @Test(expected=SimulExcept.class)
    public void setMaxNegatif() throws SimulExcept{
        System.out.println("setMaxNegatif");
        double min = 10;
        double max = 20;
        double mod = 15;
        String nom = "allo";
        AllerSimple circuit = new AllerSimple(nom, line, min, mod, max);
        circuit.setMax(0);
    }
    
    @Test(expected=SimulExcept.class)
    public void setSourceNegatif() throws SimulExcept{
        System.out.println("setMaxNegatif");
        double min = 10;
        double max = 20;
        double mod = 15;
        String nom = "allo";
        AllerSimple circuit = new AllerSimple(nom, line, min, mod, max);
        Intersection intersection = new Intersection(new Coordonnee(1.0, 1.0));
        circuit.setSource(intersection);
    }
    
    @Test(expected=SimulExcept.class)
    public void modifCircuitNegatif() throws SimulExcept{
        System.out.println("modifCircuitNegatif");
        double min = 10;
        double max = 20;
        double mod = 15;
        String nom = "allo";
        AllerSimple circuit = new AllerSimple(nom, line, min, mod, max);
        circuit.modifCircuit("", line, 11, 16, 21);
    }
    
    @Test(expected=SimulExcept.class)
    public void modifCircuitNegatif2() throws SimulExcept{
        System.out.println("modifCircuitNegatif2");
        double min = 10;
        double max = 20;
        double mod = 15;
        String nom = "allo";
        AllerSimple circuit = new AllerSimple(nom, line, min, mod, max);
        circuit.modifCircuit("bonjour", line2, 11, 16, 21);
    }
    
    @Test(expected=SimulExcept.class)
    public void modifCircuitNegatif3() throws SimulExcept{
        System.out.println("modifCircuitNegatif3");
        double min = 10;
        double max = 20;
        double mod = 15;
        String nom = "allo";
        AllerSimple circuit = new AllerSimple(nom, line, min, mod, max);
        circuit.modifCircuit("bonjour", line, 0, 16, 21);
    }
    
    @Test(expected=SimulExcept.class)
    public void modifCircuitNegatif4() throws SimulExcept{
        System.out.println("modifCircuitNegatif4");
        double min = 10;
        double max = 20;
        double mod = 15;
        String nom = "allo";
        AllerSimple circuit = new AllerSimple(nom, line, min, mod, max);
        circuit.modifCircuit("bonjour", line, 11, 0, 21);
    }
    
    @Test(expected=SimulExcept.class)
    public void modifCircuitNegatif5() throws SimulExcept{
        System.out.println("modifCircuitNegatif5");
        double min = 10;
        double max = 20;
        double mod = 15;
        String nom = "allo";
        AllerSimple circuit = new AllerSimple(nom, line, min, mod, max);
        circuit.modifCircuit("bonjour", line, 11, 16, 0);
    }
   
    @Test (expected=SimulExcept.class)
    public void modifCircuitNegatif6() throws SimulExcept{
        System.out.println("modifCircuitNegatif6");
        double min = 10;
        double max = 20;
        double mod = 15;
        String nom = "allo";
        AllerSimple circuit = new AllerSimple(nom, line, min, mod, max);
        circuit.modifCircuit("bonjour", line3, 11, 16, 21);
    }
        
    
    @Test
    public void modifCircuitPositif() throws SimulExcept{
        System.out.println("modifCircuitPositif");
        double min = 10;
        double max = 20;
        double mod = 15;
        String nom = "allo";
        AllerSimple circuit = new AllerSimple(nom, line, min, mod, max);
        circuit.modifCircuit("Bonjour", line4, 11, 16, 21);
        
    }
    
    @Test
    public void getListSegmentCircTest() throws SimulExcept{
        System.out.println("getListSegmentCircTest");
        double min = 10;
        double max = 20;
        double mod = 15;
        String nom = "allo";
        AllerSimple circuit = new AllerSimple(nom, line, min, mod, max);
        circuit.getListSegmentCirc();
    }
*/
}
