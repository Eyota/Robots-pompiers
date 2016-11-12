/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import elements.robots.RobotAChenilles;
import static elements.NatureTerrain.EAU;
import static elements.NatureTerrain.FORET;
import static elements.NatureTerrain.HABITAT;
import static elements.NatureTerrain.ROCHE;
import static elements.NatureTerrain.TERRAIN_LIBRE;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;


/**
 *
 * @author Camille Gardelle
 */
public class TestDi2 {
     public static void main(String args[]) {
        Carte map = new Carte(2,2,1);
        Case c;
        RobotAChenilles Almo = new RobotAChenilles(map);
        PlusCourtChemin pcc;
        
        //igne0
        c = new Case(0,0,TERRAIN_LIBRE);
        map.setCase(0, 0, c);
        c = new Case(0,1,TERRAIN_LIBRE);
        map.setCase(0, 1, c);
        //ligne 1
        c = new Case(1,0,TERRAIN_LIBRE);
        map.setCase(1, 0, c);
        c = new Case(1,1,TERRAIN_LIBRE);
        map.setCase(1, 1, c);
     
        Almo.setPositionInit(map.getCase(0, 0));
        System.out.println("Almo: " + Almo.getPosition().getLigne() + "  " + Almo.getPosition().getColonne() );
        pcc = new PlusCourtChemin(Almo,map.getCase(1, 1));
                
        System.out.println("longueur chemin: " + pcc.getDuree());
        if(pcc.getChemin() != null){
            for (VoisinsDijsktra a : pcc.getChemin() ){
                System.out.println(a.getDestinationV().getLigne());
                System.out.println(a.getDestinationV().getColonne());
                System.out.println(a.getCoutV());
                System.out.println();
                

            }
        }
    }
}
