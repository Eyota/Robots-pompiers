package tests;

import Simulateur.Simulateur;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

public class TestIA {

    public static void main(String[] args) throws FileNotFoundException, DataFormatException {
        testIA();
    }

    private static void testIA() throws FileNotFoundException, DataFormatException {
        Simulateur simulation = new Simulateur("C:\\Users\\Sylvain\\Documents\\_ISSC\\Java\\Robots-pompiers\\cartes\\carteSujet.map");
        simulation.getGui().setSimulable(simulation);
        simulation.setTaille(80);

    }

}
