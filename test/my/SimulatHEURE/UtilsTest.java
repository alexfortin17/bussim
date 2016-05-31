/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.SimulatHEURE;


import Fondation.SimulExcept;
import Fondation.Utils;
import java.lang.Math;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

/**
 *
 * @author alfo
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
     * Test of genererTempsParcours method, of class Utils.
     * Prouve avec R sur 100000 cas que la distro triangulaire est respecte
     */
    @Test
    public void testGenererTempsParcours() throws SimulExcept {
        System.out.println("genererTempsParcours");
        double min = 10.0;
        double mode = 18.0;
        double max = 20.0;
        
        //creation de 1000 000 cas
        double[] ListTest = new double[1000000];
        for(int x = 0; x < 1000000; x++){
            ListTest[x] = Utils.genererTempsTriangulaire(min, mode, max);
        }
        
        //Met l'array en ordre
        Arrays.sort(ListTest);
        
        //Calcul de la moyenne (theorique et observe)
        double sumOfArray = 0.0;
        for(int y = 0; y<1000000;y++){
            sumOfArray = sumOfArray + ListTest[y];
        }
        double actualMean = sumOfArray/1000000;
        System.out.println("Actual Mean: " + actualMean);
        double expectedMean = (max+min+mode)/3;
        System.out.println("expected Mean: " + expectedMean);
        assertEquals("Devrait etre egale avec une tolerance de "+ (max-min)/1000, expectedMean, actualMean, (max-min)/1000);
        
        
        //calcul de l'ecart-type(theorique et observe)
        
        //Selon le modele de la variation triangulaire, la variance doit etre:
        //(min^2+max^2+mode^2-min*max-min*mode-max*mode)/18
        //dans le cas ou max = 20, min = 10 et mode = 14, cela fait 4.22222
        //l'ecart type est donc 2.054263 (racine carre de 4.22222)
        
        double sumSquaredVariations = 0.0;
        for(int y = 0; y<1000000;y++){
            double ssx = Math.pow((ListTest[y] - actualMean), 2.0);
            sumSquaredVariations = sumSquaredVariations + ssx;
        }
        double expectedVariance = (Math.pow(min,2.0)+Math.pow(max, 2.0)+Math.pow(mode, 2.0)-max*min-min*mode-max*mode)/18;
        double actualVariance = sumSquaredVariations/1000000;
        double expectedSD = Math.sqrt(expectedVariance);
        double actualSD = Math.sqrt(actualVariance);
        System.out.println("Actual SD: " + actualSD);
        System.out.println("Expected SD: " + expectedSD);
        assertEquals("Devrait etre egale avec une tolerance de " + (max-min)/1000, expectedSD, actualSD, (max-min)/1000);
        
        
        
        
        assertTrue(ListTest[0] >= 10);
        System.out.println("Min Val: " +ListTest[0]);
        assertTrue(ListTest[999999] <= 20);
        System.out.println("Max Val: " + ListTest[999999]);
       
        //Exception  
        
    }
    
    @Test(expected=SimulExcept.class)
    public void testGenererTempsParcoursExceptions() throws SimulExcept{
        System.out.println("genererTempsParcours");
        double min = 10.0;
        double mode = 18.0;
        double max = 9.0;
        //Utils instance = new Utils();
        //max < min
        double x = Utils.genererTempsTriangulaire(min, mode, max);
        
        min = 10;
        max = 20;
        mode = 166;
        
        //mode hors du range de min et max
        double y = Utils.genererTempsTriangulaire(min, mode, max);
    }
    
    
}
