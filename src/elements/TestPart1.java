
package elements;

import gui.GUISimulator;
import io.LecteurDonnees;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

public class TestPart1 {
    public static void main(String args[]) throws FileNotFoundException, DataFormatException{
        DonneesSimulation donnees = LecteurDonnees.lire("C:\\Users\\Sylvain\\Documents\\_ISSC\\Java\\Robots-pompiers\\cartes\\carteSujet.map");
        Carte map = donnees.getCarte();
        int taille = map.getTailleCases();
        GUISimulator ui = new GUISimulator(map.getNbColonnes()*taille, map.getNbLignes()*taille, Color.white);   //Paramètres : Hauteur de la fenêtre, largeur de la fenêtre, couleur de fond
        
        
    }
    
    private void drawMap(DonneesSimulation data, GUISimulator gui){
        int i;
        Carte map = data.getCarte();
        int taille = map.getTailleCases();
        
        //Dessiner la map
        
        for (i=1; i<map.getNbColonnes(); i++){
            
        }
    
    }
    
}
