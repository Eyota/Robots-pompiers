/*
Numéro de groupe : 50
*/
package IA;

import Simulateur.Simulateur;
import elements.Carte;
import elements.Case;
import elements.DonneesSimulation;
import elements.Incendie;
import elements.PlusCourtChemin;
import elements.VoisinsDijsktra;
import elements.events.EventDisponible;
import elements.events.EventEteindre;
import elements.events.EventRemplir;
import elements.robots.Robot;
import static java.lang.Math.min;
import java.util.ArrayList;
import java.util.LinkedList;

public class ChefPompier {

    ArrayList<Robot> robots = new ArrayList<Robot>();
    ArrayList<Incendie> incendies = new ArrayList<Incendie>();
    Carte map;

    /**
     * Construit l'objet ChefPompier qui va gérer la stratégie des robots 
     * pompiers
     * @param data 
     */
    public ChefPompier(DonneesSimulation data) {
        this.robots = data.getRobots();
        this.incendies = data.getIncendies();
        this.map = data.getCarte();
    }

    /**
     * Implémente la stratégie élémentaire du chef pompier : le premier robot
     * disponible va éteindre le premier feu trouvé
     *
     * @param simulateur
     */
    public void strategieElementaire(Simulateur simulateur) {
        PlusCourtChemin courant;
        //Pour chaque robot, s'il est vide on l'envoie chercher de l'eau
        for (Robot robot : robots) {
            //Si le robot est libre et non plein, on l'envoie se remplir sur n'importe quelle case où il peut le faire
            if (robot.estDisponible() && robot.getVolumeEau() < robot.getCapacite()) {
                Case eau = robot.getPtEau().get(0);
                System.out.println("Le " + robot.toString() + " va se remplir en : " + eau.toString());
                courant = new PlusCourtChemin(robot, eau);
                //Le robot devient indisponible
                robot.setDisponible(false);
                //envoyer le robot suivre le chemin calculé
                robot.deplacerRobot(simulateur, courant);
                //ajouter evenement eteindre à t + duree
                simulateur.ajouteEvenement(new EventRemplir(simulateur.getDate() + courant.getDuree(), robot));
                //rendre le robot diponible après son remplissage
                simulateur.ajouteEvenement(new EventDisponible(simulateur.getDate() + courant.getDuree() + robot.getTempsRemplissage(), robot));
            }
        }
        // Pour chaque incendie, on cherche le robot libre le plus proche
        for (Incendie inc : incendies) {
            //Si l'incendie est eteint, on le retire de la liste
            if (inc.getIntensite() == 0) {
                incendies.remove(inc);
                break;
            }
            //sinon : on prend le premier robot libre et qui peut se rendre sur la case de l'incendie
            for (Robot robot : robots) {
                if (robot.estDisponible() && robot.estAccessible(inc.getPosition())) {
                    System.out.println("Le " + robot.toString() + " va eteindre en : " + inc.getPosition().toString());
                    //le robot est indisponible
                    robot.setDisponible(false);
                    courant = new PlusCourtChemin(robot, inc.getPosition());
                    //envoyer le robot suivre le chemin calculé
                    robot.deplacerRobot(simulateur, courant);
                    //ajouter evenement eteindre à t + duree deplacement
                    simulateur.ajouteEvenement(new EventEteindre(simulateur.getDate() + courant.getDuree(), robot, inc));
                    //le robot redevient disponible à la fin de son intervention
                    simulateur.ajouteEvenement(new EventDisponible(simulateur.getDate() + courant.getDuree() + robot.gettempsIntervention((int) min(inc.getIntensite(), robot.getVolumeEau())), robot));

                    break;  //Si on trouve un robot libre, on ne demande pas aux autres
                }
            }
        }

    }

    public void strategie2(Simulateur simulateur) {
        double duree = Integer.MAX_VALUE;
        double dureeRemplissage = Integer.MAX_VALUE;
        PlusCourtChemin cheminMin;
        PlusCourtChemin courant;
        for (Robot robot : robots) {
            dureeRemplissage = Integer.MAX_VALUE;
            //Si le robot est libre et vide, on l'envoie se remplir vers la case la plus proche
            if (robot.estDisponible() && robot.getVolumeEau() == 0) {
                //il peut aller le remplir à la case la plus proche
                PlusCourtChemin eauOptimal = null;                
                for (Case eau : robot.getPtEau()) {
                    courant = new PlusCourtChemin(robot, eau);
                    if (courant.getDuree() <= dureeRemplissage) {
                        dureeRemplissage = courant.getDuree();
                        eauOptimal = courant;
                    }
                }
                robot.setDisponible(false);
                //le robot se déplace vers la case d'eau le plus proche
                System.out.println("Le " + robot.toString() + " va se remplir en "
                        + eauOptimal.getChemin().getLast().getDestinationV());
                robot.deplacerRobot(simulateur, eauOptimal);
                //il se remplit à cette case
                simulateur.ajouteEvenement(new EventRemplir(simulateur.getDate() + eauOptimal.getDuree(), robot));
                simulateur.ajouteEvenement(new EventDisponible(simulateur.getDate() + eauOptimal.getDuree() + robot.getTempsRemplissage(), robot));

            }
        }

        // Pour chaque incendie, on cherche le robot libre le plus proche
        for (Incendie inc : incendies) {
            Robot robotOptimal = null;
            duree = Integer.MAX_VALUE;
            cheminMin = null;

            for (Robot robot : robots) {

                if (robot.estDisponible()) { 
                    courant = new PlusCourtChemin(robot, inc.getPosition());
                    if (courant.getDuree() <= duree) {
                        cheminMin = courant;
                        duree = courant.getDuree();
                        robotOptimal = robot;
                    }

                }
            }
            if (robotOptimal != null) {
                robotOptimal.setDisponible(false);
                System.out.println("Le " + robotOptimal.toString() + " va eteindre en : " + inc.getPosition().toString());
                if (inc.getIntensite() == 0) {
                    simulateur.ajouteEvenement(new EventDisponible(simulateur.getDate(), robotOptimal));
                    incendies.remove(inc);
                    break;
                } else { // si l'incendie n'est pas éteint quand il est arrivé*/
                    robotOptimal.deplacerRobot(simulateur, cheminMin);
                    simulateur.ajouteEvenement(new EventEteindre(simulateur.getDate() + cheminMin.getDuree(), robotOptimal, inc));
                    simulateur.ajouteEvenement(new EventDisponible(simulateur.getDate() + cheminMin.getDuree() + robotOptimal.gettempsIntervention((int) min(inc.getIntensite(), robotOptimal.getVolumeEau())), robotOptimal));
                }
            }
        }
    }
    
    
    
    
}
