
package Simulateur;

import IA.ChefPompier;
import elements.Carte;
import elements.DonneesSimulation;
import elements.events.Evenement;
import elements.Incendie;
import elements.NatureTerrain;
import elements.robots.Robot;
import static tests.TestPart1.drawFire;
import static tests.TestPart1.drawRobots;
import gui.GUISimulator;
import gui.ImageElement;
import gui.Rectangle;
import gui.Simulable;
import io.LecteurDonnees;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.DataFormatException;

public class Simulateur implements Simulable{
    private GUISimulator gui; 
    private DonneesSimulation data;
    private Carte map;
    private String chemin;
    private List liste = new ArrayList<Evenement>();
    private int date;
    private int taille = 80;
    private ChefPompier master;
    
    public Simulateur (String path){
        try {
        this.chemin=path;
        this.date=0;
        this.data = LecteurDonnees.lire(path);
        this.map = this.data.getCarte();
        this.master = new ChefPompier(this.data);
        this.gui = new GUISimulator(map.getNbColonnes()*taille+80, map.getNbLignes()*taille+80, Color.white);   //Paramètres : Hauteur de la fenêtre, largeur de la fenêtre, couleur de fond
        Simulateur.drawMap(gui, map);
        Simulateur.drawFire(gui, this.data.getIncendies());
        Simulateur.drawRobots(gui, this.data.getRobots());   
        }
        catch (FileNotFoundException e){
            System.out.println("Le fichier spécifié n'existe pas");
            //exit(1);
        }
        catch (DataFormatException d){
            System.out.println("Le fichier spécifié n'a pas le bon format");
        }
    }

    public DonneesSimulation getData() {
        return data;
    }

    public int getDate() {
        return date;
    }    

    public GUISimulator getGui() {
        return gui;
    }  
        
    @Override
    public void next() {
        master.strategieElementaire(this);
        Evenement enCours;        
        this.incrementeDate();
        if (!this.simulationTerminee()){
            enCours = (Evenement) this.liste.get(0); 
            while (enCours.getDate()<= this.date){
                enCours.execute();
                this.liste.remove(0);
                if (!this.simulationTerminee()){
                    enCours = (Evenement) this.liste.get(0);
                }
                else break;
            }
            drawMap(this.gui, this.map);
            drawFire(this.gui, this.data.getIncendies());
            drawRobots(this.gui, this.data.getRobots());
        }
        else{
            System.out.println("Simulation terminée");
            //throw new Exception rattrapée par ??
        }
    }

    @Override
    public void restart() {
        try {
        this.data = LecteurDonnees.lire(this.chemin); 
        this.map = this.data.getCarte();
        this.date=0;      
        this.liste= new ArrayList<Evenement>();
        this.master = new ChefPompier(this.data);
        drawMap(gui, map);
        drawFire(gui, this.data.getIncendies());
        drawRobots(gui, this.data.getRobots());  
        }
        catch (FileNotFoundException e){
            System.out.println("Le fichier spécifié n'existe pas");
            //exit(1);
        }
        catch (DataFormatException d){
            System.out.println("Le fichier spécifié n'a pas le bon format");
        }        
    }
    
    public void ajouteEvenement(Evenement e){
        this.liste.add(e);
        Collections.sort(this.liste);
    }
    
    public void incrementeDate(){
        this.date=this.date +100;
    }
    
    public boolean simulationTerminee(){
        if (this.liste.isEmpty()){
            return true;
        }
        return false;
    }  
    
    private static void drawMap(GUISimulator gui, Carte map){       //Ou faire une classe Draw ??
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
                gui.addGraphicalElement(new Rectangle((j+1)*taille, (i+1)*taille, Color.black, couleurCase, taille));
                
            }
        }
    
    }
    
    public static void drawFire(GUISimulator gui, ArrayList<Incendie> ListeIncendies){
        int i, x, y;
        int taille = 80;
        for(Incendie incendie : ListeIncendies){
            if (incendie.getIntensite()>0){
                x=incendie.getPosition().getLigne();
                y=incendie.getPosition().getColonne();
                gui.addGraphicalElement(new Rectangle((y+1)*taille, (x+1)*taille, Color.red , Color.red , 50));
            }
        }    
    }
    
    public static void drawRobots(GUISimulator gui, ArrayList<Robot> ListeRobots){
        int  x, y;
        int taille = 80;
        for(Robot robot : ListeRobots){
            x=robot.getPosition().getLigne();
            y=robot.getPosition().getColonne();
            gui.addGraphicalElement(new ImageElement((y+1)*taille-30, (x+1)*taille-30, robot.getImage() , 60 , 60, null));
        }    
    }
}
