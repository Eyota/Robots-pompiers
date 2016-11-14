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
import elements.robots.Drone;
import elements.events.Evenement;
import elements.events.EventDeplacer;   //A été remplacé par un évènement qui prend une case en paramètre pour les besoins de l'IA
import elements.robots.Robot;
import static tests.TestPart1.drawFire;
import static tests.TestPart1.drawRobots;
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
        Simulateur simulation = new Simulateur("C:\\Users\\Sylvain\\Documents\\_ISSC\\Java\\Robots-pompiers\\cartes\\carteSujet.map");
        simulation.getGui().setSimulable(simulation);
        /*
        On déplace le robot au nord 4 fois de suite.
        */
        Robot Buzz = simulation.getData().getRobots().get(0);
        Carte Mappy = simulation.getData().getCarte();
        for (int i=1;i<5;i++){
            Evenement Monter= new EventDeplacer(i, Buzz, Direction.NORD);
            simulation.ajouteEvenement(Monter);
        }
        
        //tant que la liste n'est pas vide, on incrémente & on redessine la carte
       
        //normalement: throws exception sur setPosition
        
        
}
}
