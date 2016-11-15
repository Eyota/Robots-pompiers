/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import Simulateur.Simulateur;
import elements.Carte;
import elements.Case;
import elements.Incendie;
import static elements.NatureTerrain.EAU;
import static elements.NatureTerrain.FORET;
import static elements.NatureTerrain.HABITAT;
import static elements.NatureTerrain.ROCHE;
import static elements.NatureTerrain.TERRAIN_LIBRE;
import elements.PlusCourtChemin;
import elements.robots.Robot;
import elements.robots.RobotAChenilles;
import elements.VoisinsDijsktra;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

/**
 *
 * @author Camille Gardelle
 */
public class TestDi {
    public static void main(String args[]) {
        
        Simulateur simulation= new Simulateur("." + File.separator + "cartes" + File.separator + "carteTestDi.map", 80);
        simulation.getGui().setSimulable(simulation);
        Robot Almo = simulation.getData().getRobots().get(0);
        Incendie petitFeu = simulation.getData().getIncendies().get(0);
        Carte map = simulation.getData().getCarte();
        /*Carte map = new Carte(4,3,1);
        Case c;
        RobotAChenilles Almo = new RobotAChenilles(map);*/
        PlusCourtChemin pcc;
        
        /*//igne0
        c = new Case(0,0,EAU);
        map.setCase(0, 0, c);
        c = new Case(0,1,TERRAIN_LIBRE);
        map.setCase(0, 1, c);
        c = new Case(0,2,TERRAIN_LIBRE);
        map.setCase(0, 2, c);
        System.out.println("T1");
        //ligne 1
        c = new Case(1,0,FORET);
        map.setCase(1, 0, c);
        c = new Case(1,1,EAU);
        map.setCase(1, 1, c);
        c = new Case(1,2,TERRAIN_LIBRE);
        map.setCase(1, 2, c);
        //ligne 2
        c = new Case(2,0,TERRAIN_LIBRE);
        map.setCase(2, 0, c);
        c = new Case(2,1,ROCHE);
        map.setCase(2, 1, c);
        c = new Case(2,2,TERRAIN_LIBRE);
        map.setCase(2, 2, c);
        //ligne 3
        c = new Case(3,0,HABITAT);
        map.setCase(3, 0, c);
        c = new Case(3,1,TERRAIN_LIBRE);
        map.setCase(3, 1, c);
        c = new Case(3,2,TERRAIN_LIBRE);
        map.setCase(3, 2, c);

        //chemin de 32 a 12
        Almo.setPositionInit(map.getCase(3, 2));
        System.out.println("Almo :" + Almo.getPosition().getLigne() + "  " + Almo.getPosition().getColonne());
        */
        pcc = new PlusCourtChemin(Almo,petitFeu.getPosition());
        System.out.println(pcc.getDuree());
        for (VoisinsDijsktra a : pcc.getChemin() ){
            System.out.println("case suivante: " + a.getDestinationV().getLigne() + "  " + a.getDestinationV().getColonne() + "  " + a.getCoutV());
        }

       /* //chemin de 30 a 01
        Almo.setPositionInit(map.getCase(3, 0));
        System.out.println("Almo :" + Almo.getPosition().getLigne() + "  " + Almo.getPosition().getColonne());
        
        pcc = new PlusCourtChemin(Almo,map.getCase(0, 1));
        System.out.println("Almo :" + Almo.getPosition().getLigne() + "  " + Almo.getPosition().getColonne());
        for (VoisinsDijsktra a : pcc.getChemin() ){
            System.out.println("case suivante: " + a.getDestinationV().getLigne() + "  " + a.getDestinationV().getColonne() + "  " + a.getCoutV());
        }*/
    }
}
