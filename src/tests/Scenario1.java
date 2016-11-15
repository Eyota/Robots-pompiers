/*
Numéro de groupe : 50
*/
package tests;

import Simulateur.Simulateur;
import elements.Carte;
import elements.Direction;
import elements.Incendie;
import elements.PlusCourtChemin;
import elements.VoisinsDijsktra;
import elements.events.EventDeplacer;
import elements.events.EventEteindre;
import elements.events.EventRemplir;
import elements.robots.Robot;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.zip.DataFormatException;

/*
 Déplacer le 2ième robot (à roues) vers le nord, en (5,5).
 Le faire intervenir sur la case où il se trouve.
 Déplacer le robot deux fois vers l’ouest.
 Remplir le robot.
 Déplacer le robot deux fois vers l’est.
 Le faire intervenir sur la case où il se trouve.
 Le feu de la case en question doit alors être éteint.

 */
public class Scenario1 {    //Une fois le chef pompier implémenté, ce test ne fonctionne plus...

    public static void main(String[] args) throws FileNotFoundException, DataFormatException {
        testScenario1();
    }

    private static void testScenario1() throws FileNotFoundException, DataFormatException {
        /*
         On lit le fichier carteSujet.map
         */
        Simulateur simulation = new Simulateur("." + File.separator + "cartes" + File.separator + "carteSujet.map", 80);
        simulation.getGui().setSimulable(simulation);
        int date = 1;
        Robot WallE = simulation.getData().getRobots().get(1);
        Incendie petitFeu = simulation.getData().getIncendies().get(2);
        Carte Mappy = simulation.getData().getCarte();

        simulation.ajouteEvenement(new EventDeplacer(date, WallE, Direction.NORD));
        date++;
        simulation.ajouteEvenement(new EventEteindre(date, WallE, petitFeu));
        date++;
        simulation.ajouteEvenement(new EventDeplacer(date, WallE, Direction.OUEST));
        date++;
        simulation.ajouteEvenement(new EventDeplacer(date, WallE, Direction.OUEST));
        date++;
        simulation.ajouteEvenement(new EventRemplir(date, WallE));
        date++;
        simulation.ajouteEvenement(new EventDeplacer(date, WallE, Direction.EST));
        date++;
        simulation.ajouteEvenement(new EventDeplacer(date, WallE, Direction.EST));
        date++;
        simulation.ajouteEvenement(new EventEteindre(date, WallE, petitFeu));

    }
}
