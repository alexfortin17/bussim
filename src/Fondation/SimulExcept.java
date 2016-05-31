/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fondation;

import java.io.Serializable;

/**
 *
 * @author alfo
 */
public class SimulExcept extends Exception implements Serializable{
    
    int codError;
    String method;
    
    public SimulExcept(int CodErr, String mess, String method){
        super(mess);
        this.codError = CodErr;
    }
}

/*Liste des codes d'erreurs A METTRE A JOUR!!!
 * 1--Dereferencement d'un objet null
 * 2--Nom de point deja existant
 * 3--Point deja existant a l'endroit ou on souhaite creer un point
 * 4--Lat/lon invalide (<= 0)
 * 5--Les donnees de calcul de la fonction de distri triangulaire sont invalides
 * 6--Une coordonnee ne peut etre neg
 * 7--Le segment a ajoute existe deja
 * 8--Un segment ne peut etre une boucle
 * 9--Le segment a modifier n'existe pas
 * 10--Le segment a supprimer n'existe pas
 * 11--Au moins un des deux points specifies n'existe pas
 * 12--Le point specifie n'existe pas
 * 13--Le nom d'un circuit ne peut être vide
 * 14--Le circuit n'est pas valide
 * 15--Problème lors de la suppression du dernier point
 * 16--Les points dans l'ordre specifie pour le circuit ne sont pas contigus
 * 17--Le nom du circuit existe deja
 * 18--Le nom du circuit ne peut etre vide
 * 19--Il n'y a pas de bus en enlever sur le circuit
 * 20--Le nombre max de bus sur un circuit ne peut etre plus petit que 1
 * 21--La date specifie est hors du range de la simulation
 * 22--Le temps minimal/maximal/modal du circuit doit être plus grand que 1
 * 23--Le point de départ d'un circuit doit être un arrêt
 * 24--Le temps de transit simuler n'a pas ete calcule
 * 25--La carte actuel n'est pas valide
 * 26--Le point source ne fait pas partie du circuit
 * 27--La list de points n'est pas un itineraire valide
 * 28--Un itineraire doit commencer et se terminer par un arret
 * 29--Tous les points de la liste doivent etre contigus
 * 31--La liste de circuit et la liste de points ne concordent pas
 * 32--Le point a mettre comme source ne fait pas partie du circuit
 * 33--Le point source ne peut pas etre le dernier point d'un aller simple
 * 34--La liste de passager doit avoir ete genere pour generer des statistiques
 * 35--Le point est deja une intersection
 * 36--Le point est deja une arret
 * 37--Une erreur est survenu dans la fermeture de graphe
*/

