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
        Simulateur simulation = new Simulateur("." + File.separator + "cartes" + File.separator + "carteSujet.map");
    }

}
