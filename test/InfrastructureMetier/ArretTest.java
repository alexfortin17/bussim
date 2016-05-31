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
public class ArretTest {
    
    public ArretTest() {
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
    public void ConstrucArretCoord() throws SimulExcept{
        System.out.println("ConstrucArretCoord");
        double lat = 10.0;
        double lon = 18.0;
        Coordonnee x = new Coordonnee(lat, lon);
        Arret arret = new Arret(x);
        assertEquals(lat,arret.getCoordPixels().getLat(),0);
        assertEquals(lon,arret.getCoordPixels().getLon(),0);
    }
    
    @Test
    public void ConstrucArretCompteurPassager0() throws SimulExcept{
        System.out.println("ConstrucArretCompteurPassager0");
        double lat = 10.0;
        double lon = 18.0;
        Coordonnee x = new Coordonnee(lat, lon);
        Arret arret = new Arret(x);
        int nombrePassager = 0;
        int arretPassager = arret.getCompteurPassagers();
        assertEquals(arretPassager, nombrePassager);
    }
    
    @Test
    public void ConstrucArretSetArret() throws SimulExcept{
        System.out.println("ConstrucArretSetArret");
        double lat = 10.0;
        double lon = 18.0;
        Coordonnee x = new Coordonnee(lat, lon);
        Arret arret = new Arret(x);
        boolean pourTest = true;
        boolean arretTEST = arret.getArret();
        assertEquals(arretTEST, pourTest);
    }
    
    @Test
    public void ConstrucArretAvecNom() throws SimulExcept{
        System.out.println("ConstrucArretAvecNom");
        double lat = 10.0;
        double lon = 18.0;
        String nom = "bonjour";
        Coordonnee x = new Coordonnee(lat, lon);
        Arret arret = new Arret(x, nom);
        String nomTest = arret.getNom();
        assertEquals(nomTest, nom);
    }
    
    @Test
    public void ConstrucCopieArret() throws SimulExcept{
        System.out.println("ConstrucCopieArret");
        double lat = 10.0;
        double lon = 18.0;
        String nom = "bonjour";
        Coordonnee x = new Coordonnee(lat, lon);
        Arret arret = new Arret(x, nom);
        Arret arretCopie = new Arret(arret);
        
        double latTest = arretCopie.getCoordPixels().getLat();
        double lonTest = arretCopie.getCoordPixels().getLon();
        String nomTest = arretCopie.getNom();
        int id = arret.getId();
        int idTest = arretCopie.getId();
        boolean arretactuel = arret.getArret();
        boolean arretCopieTest = arretCopie.getArret();
        
        
        assertEquals(latTest, lat, 0);
        assertEquals(lonTest, lon, 0);
        assertEquals(nomTest, nom);
        assertEquals(idTest, id, 0);
        assertEquals(arretCopieTest, arretactuel);
    }
    
    @Test
    public void AddPassagerArret() throws SimulExcept{
        System.out.println("AddPassagerArret");
        double lat1 = 10.0;
        double lon1 = 18.0;
        Coordonnee coord1 = new Coordonnee(lat1, lon1);
        Arret arret1 = new Arret(coord1);
        double lat2 = 20.00;
        double lon2 = 36.00;
        Coordonnee coord2 = new Coordonnee(lat2, lon2);
        Arret arret2 = new Arret(coord2);
        Itineraire iti = new Itineraire(arret1, arret2);
        Passager pass = new Passager(iti);
        arret1.addPassagers(pass);
        int nbpassager = 1;
        int nbpassTest = arret1.getCompteurPassagers();
        assertEquals(nbpassTest, nbpassager);
        
    }
    
    @Test
    public void DelPassagerArret() throws SimulExcept{
        System.out.println("DelPassagerArret");
        double lat1 = 10.0;
        double lon1 = 18.0;
        Coordonnee coord1 = new Coordonnee(lat1, lon1);
        Arret arret1 = new Arret(coord1);
        double lat2 = 20.00;
        double lon2 = 36.00;
        Coordonnee coord2 = new Coordonnee(lat2, lon2);
        Arret arret2 = new Arret(coord2);
        Itineraire iti = new Itineraire(arret1, arret2);
        Passager pass1 = new Passager(iti);
        Passager pass2 = new Passager(iti);
        Passager pass3 = new Passager(iti);
        arret1.addPassagers(pass1);
        arret1.addPassagers(pass2);
        arret1.addPassagers(pass3);
        arret1.delPassagers(pass2);
        int nbpassager = 2;
        int nbpassTest = arret1.getCompteurPassagers();
        assertEquals(nbpassTest, nbpassager, 0);
        
    }
    
    */
}
    
    
