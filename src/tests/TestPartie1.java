/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import Simulateur.Simulateur;
import elements.*;

/**
 *
 * @author Agathe
 */
public class TestPartie1 {
    public static void main(String args[]){
        //Test sur la carte et les cases
        
        Carte Map = new Carte(8,8,1);
        System.out.println(Map.getNbLignes());
        System.out.println(Map.getNbColonnes());
        System.out.println(Map.getTailleCases());
        Case Case22 = new Case(2,2,NatureTerrain.EAU);
        Case Case21 = new Case(2,1,NatureTerrain.FORET);
        Case Case12 = new Case(1,2,NatureTerrain.ROCHE);
        Case Case24 = new Case(2,4, NatureTerrain.HABITAT);
        
        Map.setCase(2, 2, Case22);
        Map.setCase(2, 1, Case21);
        Map.setCase(1, 2, Case12);
        Map.setCase(2, 4, Case24);
        System.out.println(Map.voisinExiste(Case22, Direction.NORD));
        System.out.println(Map.listeVoisins(Case22).toString());
        
        //Test sur le lecteur de données
        
        
        //Test sur le simulateur
        Simulateur sim = new Simulateur("C:\\Users\\Agathe\\Documents\\2A\\POO\\Robots-pompiers-master\\cartes\\carteSujet.map", 80);
        
        
        
        
        
    }
}
