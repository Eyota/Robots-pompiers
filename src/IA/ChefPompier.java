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
import java.util.ArrayList;
import java.util.LinkedList;

public class ChefPompier {

    ArrayList<Robot> robots = new ArrayList<Robot>();
    ArrayList<Incendie> incendies = new ArrayList<Incendie>();
    Carte map;

    public ChefPompier(DonneesSimulation data) {
        this.robots = data.getRobots();
        this.incendies = data.getIncendies();
        this.map = data.getCarte();
    }
    
    /**
     * Implémente la stratégie élémentaire du chef pompier : le premier robot disponible va éteindre le premier feu trouvé
     * @param simulateur 
     */
    public void strategieElementaire(Simulateur simulateur) {
        PlusCourtChemin courant;
        //Pour chaque robot, s'il est vide on l'envoie chercher de l'eau
        for (Robot robot : robots) {
            //Si le robot est libre et vide, on l'envoie se remplir sur n'importe quelle case où il peut le faire
            if (robot.estDisponible() && robot.getVolumeEau() < robot.getCapacite()) {
                Case eau = robot.getPtEau().get(0);
                System.out.println("Le robot : " + robot.toString() + " va se remplir en : " + eau.toString());
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
                    //le robot redevient disponible à la fin de son intervention (nb : pour caculer le temps d'intervention on prend le volume d'eau porté par le robot, sauf pour le robot à pattes dont le réservoir est infini)
                    if (robot.toString().equals("robot a pattes")) {
                        simulateur.ajouteEvenement(new EventDisponible(simulateur.getDate() + courant.getDuree() + robot.gettempsIntervention((int) inc.getIntensite()), robot));
                    } else {
                        simulateur.ajouteEvenement(new EventDisponible(simulateur.getDate() + courant.getDuree() + robot.gettempsIntervention((int) robot.getVolumeEau()), robot));
                    }
                    break;  //Si on trouve un robot libre, on ne demande pas aux autres
                }
            }
        }

    }

    /*public void StrategieEvoluee (Simulateur simulateur){
     double duree = 1000000;
     LinkedList<VoisinsDijsktra> cheminMin;
     PlusCourtChemin courant;
     while(){
     //Pour chaque robot, s'il est vide on l'envoie chercher de l'eau
     for (Robot robot : robots){
     if(robot.estDisponible() && ){
                        
     }
     }
     // Pour chaque incendie, on cherche le robot libre le plus proche
     for (Incendie inc : incendies){ 
     for (Robot robot : robots){
     if(robot.estDisponible()){
     courant = new PlusCourtChemin(robot, inc.getPosition());
     if (courant.getDuree()<duree){
     cheminMin=courant.getChemin();
     duree=courant.getDuree();
     }
     simulateur.ajouteEvenement(null);       //remplacer par l'évènement de parcourt du chemin
     }
     }
     }
     }
     }*/
}
