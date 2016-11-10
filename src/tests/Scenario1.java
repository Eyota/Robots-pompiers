/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import Simulateur.Simulateur;
import elements.Carte;
import elements.Direction;
import elements.DonneesSimulation;
import elements.Drone;
import elements.Evenement;
import elements.EventDeplacer;
import elements.EventEteindre;
import elements.EventRemplir;
import elements.Incendie;
import elements.PlusCourtChemin;
import elements.Robot;
import elements.RobotARoues;
import elements.VoisinsDijsktra;
import io.LecteurDonnees;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.zip.DataFormatException;

/**
 *
 * @author Agathe
 */

/*
Déplacer le 2ième robot (à roues) vers le nord, en (5,5).
Le faire intervenir sur la case où il se trouve.
Déplacer le robot deux fois vers l’ouest.
Remplir le robot.
Déplacer le robot deux fois vers l’est.
Le faire intervenir sur la case où il se trouve.
Le feu de la case en question doit alors être éteint.

*/
public class Scenario1 {
    public static void main(String[] args) throws FileNotFoundException, DataFormatException{
        testScenario1();
    }
    private static void testScenario1() throws FileNotFoundException, DataFormatException{
        /*
        On lit le fichier carteSujet.map
        */
        Simulateur simulation= new Simulateur("C:\\Users\\Sylvain\\Documents\\_ISSC\\Java\\Robots-pompiers\\cartes\\carteSujet.map");
        simulation.getGui().setSimulable(simulation);
        int date=1;
        Robot WallE = simulation.getData().getRobots().get(2);
        Incendie petitFeu = simulation.getData().getIncendies().get(4);
        Carte Mappy = simulation.getData().getCarte();
        //création des évènements
        /*Evenement Monter= new EventDeplacer(date, WallE, Direction.NORD);
        Evenement Babord= new EventDeplacer(date, WallE,  Direction.OUEST);
        Evenement Tribord= new EventDeplacer(date, WallE,  Direction.EST);
        Evenement Glouglou = new EventRemplir(date, WallE);
        Evenement Splash = new EventEteindre(date, WallE, petitFeu);*/
        
        //test + court chemin
        System.out.println("Calcul chemin");
        PlusCourtChemin way = new PlusCourtChemin(WallE, petitFeu.getPosition());
        LinkedList<VoisinsDijsktra> chemin = way.getChemin();
        System.out.println(chemin.toString());
        
        
        //simulation robot à roues
        /*simulation.ajouteEvenement(new EventDeplacer(date, WallE, Direction.EST));
        date++;
        simulation.ajouteEvenement(new EventDeplacer(date, WallE,  Direction.NORD));
        date++;
        simulation.ajouteEvenement(new EventDeplacer(date, WallE,  Direction.NORD));
        date++;
        simulation.ajouteEvenement(new EventRemplir(date, WallE));
        date++;
        simulation.ajouteEvenement(new EventDeplacer(date, WallE,  Direction.OUEST));
        date++;
        simulation.ajouteEvenement(new EventDeplacer(date, WallE,  Direction.SUD));
        date++;
        simulation.ajouteEvenement(new EventEteindre(date, WallE, petitFeu));
        date*/
        
        //simulation robot à pattes
        /*simulation.ajouteEvenement(new EventDeplacer(date, WallE, Direction.OUEST));
        date++;
        simulation.ajouteEvenement(new EventDeplacer(date, WallE,  Direction.OUEST));
        date++;
        simulation.ajouteEvenement(new EventDeplacer(date, WallE,  Direction.SUD));
        date++;
        simulation.ajouteEvenement(new EventEteindre(date, WallE, petitFeu));
        date++;
        simulation.ajouteEvenement(new EventDeplacer(date, WallE,  Direction.OUEST));
        date++;
        simulation.ajouteEvenement(new EventDeplacer(date, WallE,  Direction.SUD));
        date++;*/
    }
}
    
