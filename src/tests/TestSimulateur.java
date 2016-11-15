/*
Numéro de groupe : 50
*/
package tests;

import Simulateur.Simulateur;
import java.io.File;

public class TestSimulateur {
    public static void main(String args[]){
        Simulateur simu = new Simulateur("." + File.separator + "cartes" + File.separator + "carteSujet.map", 80);
    }
}
