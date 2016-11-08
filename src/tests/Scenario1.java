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
import elements.Robot;
import elements.RobotARoues;
import io.LecteurDonnees;
import java.io.FileNotFoundException;
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
        Simulateur simulation= new Simulateur();
        int date;
        date=0;
        DonneesSimulation donnees = LecteurDonnees.lire("C:\\Users\\Agathe\\Documents\\2A\\POO\\Robots-pompiers-master\\cartes\\carteSujet.map");
        Carte Mappy = donnees.getCarte();
        
        //initialisation du robot et de sa position
        Robot WallE = new RobotARoues(Mappy);
        WallE.setPosition(Mappy.getCase(4, 5));
        
        //création des évènements
        Evenement Monter= new EventDeplacer(date, WallE, Mappy.getVoisin(WallE.getPosition(), Direction.NORD));
        Evenement Babord= new EventDeplacer(date, WallE, Mappy.getVoisin(WallE.getPosition(), Direction.OUEST));
        Evenement Tribord= new EventDeplacer(date, WallE, Mappy.getVoisin(WallE.getPosition(), Direction.EST));
        Evenement Glouglou = new EventRemplir();
        Evenement Splash = new EventEteindre();
        
        //simulation
        simulation.ajouteEvenement(Monter);
        date++;
        simulation.ajouteEvenement(Splash);
        date++;
        simulation.ajouteEvenement(Babord);
        date++;
        simulation.ajouteEvenement(Babord);
        date++;
        simulation.ajouteEvenement(Glouglou);
        date++;
        simulation.ajouteEvenement(Tribord);
        date++;
        simulation.ajouteEvenement(Tribord);
        date++;
        simulation.ajouteEvenement(Splash);
        date++;
        
        //normalement: incendie maîtrisé
    }
}
    
