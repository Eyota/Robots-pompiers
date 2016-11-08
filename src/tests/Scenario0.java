/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import Simulateur.Simulateur;
import elements.Carte;
import elements.Case;
import elements.Direction;
import elements.DonneesSimulation;
import elements.Drone;
import elements.Evenement;
import elements.EventDeplacer;
import elements.Robot;
import static elements.TestPart1.drawFire;
import static elements.TestPart1.drawRobots;
import gui.GUISimulator;
import io.LecteurDonnees;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

/**
 *
 * @author Agathe
 */

/*
Déplacer le 1er robot (drone) vers le nord, quatre fois de suite.
Erreur : le robot est sorti de la carte.
*/
public class Scenario0{
    public static void main(String[] args) throws FileNotFoundException, DataFormatException{
        testScenario0();
    }
    private static void testScenario0() throws FileNotFoundException, DataFormatException{
        /*
        On lit le fichier carteSujet.map
        */
        Simulateur simulation = new Simulateur("C:\\Users\\Agathe\\Documents\\2A\\POO\\Robots-pompiers-master\\cartes\\carteSujet.map");
        /*
        On déplace le robot au nord 4 fois de suite.
        */
        Robot Buzz = simulation.getData().getRobots().get(1);
        Carte Mappy = simulation.getData().getCarte();
        for (int i=0;i<4;i++){
            Evenement Monter= new EventDeplacer(i, Buzz, Direction.NORD);
            simulation.ajouteEvenement(Monter);
        }
        
        //tant que la liste n'est pas vide, on incrémente & on redessine la carte
        while (!(simulation.simulationTerminee())){
            
        }
        //normalement: throws exception sur setPosition
        
        
}
}