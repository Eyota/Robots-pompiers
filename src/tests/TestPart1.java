
package tests;

import elements.Carte;
import elements.Carte;
import elements.DonneesSimulation;
import elements.DonneesSimulation;
import elements.Incendie;
import elements.Incendie;
import elements.NatureTerrain;
import elements.NatureTerrain;
import elements.robots.Robot;
import gui.GUISimulator;
import gui.ImageElement;
import gui.Rectangle;
import io.LecteurDonnees;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

public class TestPart1 {
    public static void main(String args[]) throws FileNotFoundException, DataFormatException{
        DonneesSimulation donnees = LecteurDonnees.lire("." + File.separator + "cartes" + File.separator + "carteSujet.map");
        Carte map = donnees.getCarte();
        int taille = 80;               //map.getTailleCases();
        GUISimulator ui = new GUISimulator(map.getNbColonnes()*taille+80, map.getNbLignes()*taille+80, Color.white);   //Paramètres : Hauteur de la fenêtre, largeur de la fenêtre, couleur de fond
        
        drawMap(ui, map);
        drawFire(ui, donnees.getIncendies());
        drawRobots(ui, donnees.getRobots());
    }
    
    private static void drawMap(GUISimulator gui, Carte map){
        int i,j;
        int taille = 80;        //map.getTailleCases();
        NatureTerrain type;
        Color couleurCase;
        //Dessiner la map
        
        for (i=0; i<map.getNbLignes(); i++){
            for (j=0; j<map.getNbColonnes(); j++){
                type = map.getCase(i, j).getNature();
                switch (type){
                    case EAU :
                       couleurCase = Color.BLUE;
                       break;
                    case FORET :
                       couleurCase = Color.GREEN;
                       break;
                    case ROCHE :
                       couleurCase = Color.LIGHT_GRAY;
                       break;
                    case TERRAIN_LIBRE :
                       couleurCase = Color.white;
                       break;
                    case HABITAT :
                       couleurCase = Color.yellow;
                       break;
                    default :
                       couleurCase = Color.white;
                       break;
                }
                gui.addGraphicalElement(new Rectangle((i+1)*taille, (j+1)*taille, Color.black, couleurCase, taille));
                
            }
        }
    
    }
    
    public static void drawFire(GUISimulator gui, ArrayList<Incendie> ListeIncendies){
        int i, x, y;
        int taille = 80;
        for(Incendie incendie : ListeIncendies){
            x=incendie.getPosition().getLigne();
            y=incendie.getPosition().getColonne();
            gui.addGraphicalElement(new Rectangle((y+1)*taille, (x+1)*taille, Color.red , Color.red , 50));
        }    
    }
    
    public static void drawRobots(GUISimulator gui, ArrayList<Robot> ListeRobots){
        int  x, y;
        
        int taille = 80;
        for(Robot robot : ListeRobots){
            x=robot.getPosition().getLigne();
            y=robot.getPosition().getColonne();
            String type = robot.getClass().getName();
            System.out.println(type);
            String path;
            switch (type){
                case "elements.Drone" :
                    path = "C:\\Users\\Sylvain\\Documents\\_ISSC\\Java\\Robots-pompiers\\cartes\\drone.png";
                    break;
                case "elements.RobotAChenilles" :
                    path = "C:\\Users\\Sylvain\\Documents\\_ISSC\\Java\\Robots-pompiers\\cartes\\wall-e.png";
                    break;
                case "elements.RobotAPattes" :
                    path = "C:\\Users\\Sylvain\\Documents\\_ISSC\\Java\\Robots-pompiers\\cartes\\pattes.png";
                    break;               
                case "elements.RobotARoues" :
                    path = "C:\\Users\\Sylvain\\Documents\\_ISSC\\Java\\Robots-pompiers\\cartes\\roues.png";
                    break; 
                default :
                    path = "C:\\Users\\Sylvain\\Documents\\_ISSC\\Java\\Robots-pompiers\\cartes\\roues.png";
                    break; 
            }                   
            //gui.addGraphicalElement(new Rectangle((y+1)*taille, (x+1)*taille, Color.black , Color.DARK_GRAY , 50));
            gui.addGraphicalElement(new ImageElement((y+1)*taille-30, (x+1)*taille-30, path , 60 , 60, null));
        }    
    }
}
