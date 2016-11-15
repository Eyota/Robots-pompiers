/*
Numéro de groupe : 50
*/
package tests;

import Simulateur.Simulateur;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

public class TestIA {

    public static void main(String[] args) throws FileNotFoundException, DataFormatException {
        testIA();
    }

    private static void testIA() throws FileNotFoundException, DataFormatException {
        Simulateur simulation = new Simulateur("." + File.separator + "cartes" + File.separator + "carteSujet.map", 80);
        //Simulateur simulation = new Simulateur("." + File.separator + "cartes" + File.separator + "desertOfDeath-20x20.map", 40);
        //Simulateur simulation = new Simulateur("." + File.separator + "cartes" + File.separator + "mushroomOfHell-20x20.map", 40);
        //Simulateur simulation = new Simulateur("." + File.separator + "cartes" + File.separator + "spiralOfMadness-50x50.map", 40);
    }

}
