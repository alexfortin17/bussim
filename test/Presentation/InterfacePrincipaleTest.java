/*
 * Ce code est la propriete de l'empereur Alexandre Fortin
 * Each line should be prefixed with  * 
 */
package Presentation;

import Application.Application;
import Domaine.uiExcept;
import Fondation.Factory;
import Fondation.SimulExcept;
import InfrastructureMetier.AllerSimple;
import InfrastructureMetier.Arret;
import InfrastructureMetier.Bus;
import InfrastructureMetier.Circuit;
import InfrastructureMetier.Coordonnee;
import InfrastructureMetier.Point;
import InfrastructureMetier.Segment;
import java.awt.event.ActionEvent;
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
 * @author Admin
 */
public class InterfacePrincipaleTest {
    
    public InterfacePrincipaleTest() {
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
     * Test of main method, of class InterfacePrincipale.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        InterfacePrincipale.main(args);
    }
    
    /**
     * Test recuperer emplacement d'un bus
     */
    @Test
    public void testEmplacementBus(){
        System.out.println("Emplacement Bus lancer sim");
        ArrayList<Bus> listeTotaleBus = new ArrayList();
        try{
            Application app = Factory.creerApplication();
            app.ajouterPoint(100, 100, true, "point1");
            app.ajouterPoint(200, 200, true, "point2");
            app.ajouterPoint(300, 300, true, "point3");
            app.ajouterPoint(400, 400, true, "point4");
            Coordonnee coord1 = new Coordonnee(100,100);
            Coordonnee coord2 = new Coordonnee(200,200);
            Coordonnee coord3 = new Coordonnee(300,300);
            Coordonnee coord4 = new Coordonnee(400,400);
            Arret pt1 = new Arret(coord1,"point1");
            Arret pt2 = new Arret(coord2,"point2");
            Arret pt3 = new Arret(coord3,"point3");
            Arret pt4 = new Arret(coord4,"point4");
            app.ajouterSegment(pt1, pt2, 2, 2, 2);
            app.ajouterSegment(pt2, pt3, 2, 2, 2);
            app.ajouterSegment(pt3, pt4, 2, 2, 2);
            ArrayList<Point> listePointsCirc1 = new ArrayList();
            ArrayList<Point> listePointsCirc2 = new ArrayList();
            listePointsCirc1.add(pt1);
            listePointsCirc1.add(pt2);
            listePointsCirc2.add(pt3);
            listePointsCirc2.add(pt3);
            //Circuit 1 a un nb max de 5 bus
            //app.creerCircuit("circuit1", listePointsCirc1, 2, 2, 2,5);
            //app.creerCircuit("circuit2", listePointsCirc2, 2, 2, 2,5);
            Date dep = new Date();
            Date fin = new Date();
            fin.setTime(fin.getTime()+1600000);
            //app.LancerSim(dep, fin);
            //listeTotaleBus.addAll(app.getListeTotaleBus());
        }catch(Exception ex){
            
        }
        assertEquals(9,listeTotaleBus.size(),0);
    }

    /**
     * Test of actionPerformed method, of class InterfacePrincipale.
     */
    @Test
    public void testActionPerformed() {
        System.out.println("actionPerformed");
        ActionEvent e = null;
        InterfacePrincipale instance = new InterfacePrincipale();
        instance.actionPerformed(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
}
