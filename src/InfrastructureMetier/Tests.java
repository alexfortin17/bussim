/*
 * Ce code est la propriete de l'empereur Alexandre Fortin
 * Each line should be prefixed with  * 
 */
package InfrastructureMetier;

import Domaine.Graphe;
import Presentation.InterfacePrincipale;

/**
 *
 * @author Admin
 */
public class Tests {
    Graphe graphe1 = new Graphe();
    
    public Tests(){
        
        
        
        
        
        
        
        
        
        /*
         *SECTION POUR CRÉATION D'INSTANCES 
         */
        /*
        try{
            graphe1.ajoutPoint(200, 200, "Intersection1", false);//Ajout point de type intersection
            graphe1.ajoutPoint(300, 300, "Arrêt1", true);
            graphe1.ajoutPoint(400, 400, "Arrêt2", true);
            int idDepartSegment=0;
            int idArriveSegment=0;
            for(int i=0;i<graphe1.ListPoint.size();i++){
                if(graphe1.ListPoint.get(i).coord.lat == 300 && graphe1.ListPoint.get(i).coord.lon == 300){
                    idDepartSegment = graphe1.ListPoint.get(i).id;
                }
                if(graphe1.ListPoint.get(i).coord.lat == 400 && graphe1.ListPoint.get(i).coord.lon == 400){
                    idArriveSegment = graphe1.ListPoint.get(i).id;
                }
                
            }
            graphe1.ajoutSegment(idDepartSegment, idArriveSegment, 80, 2, 10, 30);
        }
        catch (Exception e){
            
        }
        
        
        
        
        
        */
        
        
        
           
           /*
        SECTION POUR AFFICHAGE DE DONNÉES
        */
        //On vérifie si le graphe est vide
        if(graphe1.ListPoint.isEmpty()){
            
        }
        //Sinon on peut afficher 
        else{
            System.out.println("Listes des points actuellement créés : ");
            for(int i=0;i<graphe1.ListPoint.size();i++){
                //Affiche coordonné 
                System.out.println("");
                System.out.println("Information du point : ");
                System.out.println("ID : " + graphe1.ListPoint.get(i).getId());
                System.out.println("LAT : " + graphe1.ListPoint.get(i).getCoord().lat);
                System.out.println("LON : " + graphe1.ListPoint.get(i).getCoord().lon);
                System.out.println("NOM : " + graphe1.ListPoint.get(i).getNom());
                System.out.println("Est le point de début/d'arrivée du/des segments suivants : ");
                
                //On vérifie s'il y a des segments de rattachés à ce point
                if(graphe1.ListPoint.get(i).getListSegment().isEmpty()){
                    //À VÉRIFIER - Quand on veut aller dans un Array vide on reçoit une erreur
                    
                }
                //Si oui on les affiches
                else{
                    for(int j=0;j<graphe1.ListPoint.get(i).getListSegment().size();j++){
                        System.out.println("Point de début du segment suivant : ");
                        System.out.println("LAT DEPART : " + graphe1.ListPoint.get(i).getListSegment().get(j).getDepart().getCoord().lat);
                        System.out.println("LAT ARRIVEE : " + graphe1.ListPoint.get(i).getListSegment().get(j).getArrivee().getCoord().lat);
                        System.out.println("LON DEPART : " + graphe1.ListPoint.get(i).getListSegment().get(j).getDepart().getCoord().lon);
                        System.out.println("LON ARRIVEE : " + graphe1.ListPoint.get(i).getListSegment().get(j).getArrivee().getCoord().lon);
                        System.out.println();
                    }
                }
                
                //On vérifie si ce point est le point d'arrivée d'un segment
                int idCourant = graphe1.ListPoint.get(i).getId();
                for(int j=0;j<graphe1.ListPoint.size();j++){
                    if(graphe1.ListPoint.get(j).getListSegment().size()<=0){
                        
                    }
                    else{
                        for(int k=0;k<graphe1.ListPoint.get(j).getListSegment().size();k++){
                            if(graphe1.ListPoint.get(j).getListSegment().get(k).getArrivee().getId()==idCourant){
                                System.out.println("Point d'arrivée du segment suivant : ");
                                System.out.println("LAT DEPART : " + graphe1.ListPoint.get(j).getListSegment().get(k).getDepart().getCoord().lat);
                                System.out.println("LAT ARRIVEE : " + graphe1.ListPoint.get(j).getListSegment().get(k).getArrivee().getCoord().lat);
                                System.out.println("LON DEPART : " + graphe1.ListPoint.get(j).getListSegment().get(k).getDepart().getCoord().lon);
                                System.out.println("LON ARRIVEE : " + graphe1.ListPoint.get(j).getListSegment().get(k).getArrivee().getCoord().lon);
                                System.out.println("DISTANCE : " + graphe1.ListPoint.get(j).getListSegment().get(k).getDistance());
                                System.out.println("MIN : " + graphe1.ListPoint.get(j).getListSegment().get(k).getTempsMin());
                                System.out.println("MOD : " + graphe1.ListPoint.get(j).getListSegment().get(k).getTempsMode());
                                System.out.println("MAX : " + graphe1.ListPoint.get(j).getListSegment().get(k).getTempsMax());
                                System.out.println();
                            }
                            else{
                                
                            }
                        }
                    }
                }
            }
            
           
           
       
        
        
        
        
            
            
            
            
        /*
         *ON DÉPLACE UN POINT ET RAFFICHE LES COORDONNÉES DU SEGMENT Y ÉTANT RATTACHÉ 
         */
        System.out.println();    
        System.out.println();    
        System.out.println();    
        System.out.println("On déplace l'arrêt1 aux coordonnées LAT et LON 200");
        try{
            graphe1.deplacerPoint(2,200,200);
        }catch(Exception e){
            
        }
        
        /*
        *AFFICHAGE DES NOUVELLES INFORMATIONS
        */
        System.out.println("Nouvelles informations de l'arrêt1");
        System.out.println("ID : " + graphe1.ListPoint.get(1).getId());
        System.out.println("LAT : " + graphe1.ListPoint.get(1).getCoord().lat);
        System.out.println("LON : " + graphe1.ListPoint.get(1).getCoord().lon);
        System.out.println("LON : " + graphe1.ListPoint.get(1).getNom());
        System.out.println("Information du segment qui débute à cet arrêt : ");
        if( graphe1.ListPoint.get(1).getListSegment().isEmpty()){
            //À VÉRIFIER - Quand on veut aller dans un Array vide on reçoit une erreur
            System.out.println("Aucun");
        }
        else{
            for(int j=0;j<graphe1.ListPoint.get(1).getListSegment().size();j++){
                System.out.println("LAT DEPART: " + graphe1.ListPoint.get(1).getListSegment().get(j).getDepart().getCoord().lat);
                System.out.println("LON DEPART : " + graphe1.ListPoint.get(1).getListSegment().get(j).getDepart().getCoord().lon);
                System.out.println("LAT ARRIVEE: " + graphe1.ListPoint.get(1).getListSegment().get(j).getArrivee().getCoord().lat);
                System.out.println("LON ARRIVEE : " + graphe1.ListPoint.get(1).getListSegment().get(j).getArrivee().getCoord().lon);
                System.out.println("DISTANCE : " + graphe1.ListPoint.get(1).getListSegment().get(j).getDistance());
                System.out.println("MIN : " + graphe1.ListPoint.get(1).getListSegment().get(j).getTempsMin());
                System.out.println("MOD : " + graphe1.ListPoint.get(1).getListSegment().get(j).getTempsMode());
                System.out.println("MAX : " + graphe1.ListPoint.get(1).getListSegment().get(j).getTempsMax());
            }
        }
        System.out.println();
        
        
        
        
        
        
        
        
        
        
        
        
        /*
         *ON DÉPLACE UN POINT QUI EST LE POINT D'ARRIVÉE D'UN SEGMENT ET RAFFICHE LES COORDONNÉES DU SEGMENT POUR S'ASSURER QUE LA LONGUEUR A ÉTÉ MODIFIÉE
         */
        System.out.println();    
        System.out.println();    
        System.out.println();    
        System.out.println("On déplace l'arrêt2 aux coordonnées LAT et LON 600");
        try{
            graphe1.deplacerPoint(3,600,600);
        }catch(Exception e){
            
        }
        
        //On affiche les informations 
        if( graphe1.ListPoint.get(1).getListSegment().isEmpty()){
            System.out.println("Aucun");
        }
        else{
            for(int iter=0;iter<graphe1.ListPoint.get(1).getListSegment().size();iter++){
            }
        }
        System.out.println();
        
        
        
    }
 }   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //EXÉCUTABLE
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InterfacePrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfacePrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfacePrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfacePrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Tests();
        });
    }
}
