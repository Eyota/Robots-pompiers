package Simulateur;

import IA.ChefPompier;
import elements.Carte;
import elements.DonneesSimulation;
import elements.events.Evenement;
import elements.Incendie;
import elements.NatureTerrain;
import elements.robots.Robot;
import gui.GUISimulator;
import gui.ImageElement;
import gui.Rectangle;
import gui.Simulable;
import io.LecteurDonnees;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.DataFormatException;

public class Simulateur implements Simulable {

    private GUISimulator gui;
    private DonneesSimulation data;
    private Carte map;
    private String chemin;
    private List listeEvent = new ArrayList<Evenement>();
    private int date;
    private int taille = 80;
    private ChefPompier master;

    /**
     * Construit l'objet Simulateur et prépare la simulation en lisant les
     * données du fichier dont les fichiers est pris en paramètre et en créant 
     * le GUISimulator qui gère l'affichage en fonction de la taille indiquée
     * @param path
     * @param taille
     */
    public Simulateur(String path, int taille) {
        try {
            this.chemin = path;
            this.date = 0;
            this.taille=taille;
            this.data = LecteurDonnees.lire(path);
            this.map = this.data.getCarte();
            this.master = new ChefPompier(this.data);
            this.gui = new GUISimulator((map.getNbColonnes()+1) * taille, (map.getNbLignes()+1) * taille, Color.white);   //Paramètres : Hauteur de la fenêtre, largeur de la fenêtre, couleur de fond
            this.gui.setSimulable(this);
            Simulateur.drawMap(gui, map, taille);
            Simulateur.drawFire(gui, this.data.getIncendies(), taille);
            Simulateur.drawRobots(gui, this.data.getRobots(), taille);
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier spécifié n'existe pas");
            //exit(1);
        } catch (DataFormatException d) {
            System.out.println("Le fichier spécifié n'a pas le bon format");
        }
    }

    /**
     * @return les données de la simulation (carte, incendies et robots)
     */
    public DonneesSimulation getData() {
        return data;
    }

    /**
     * @return la date courante de la simulation
     */
    public int getDate() {
        return date;
    }

    /**
     * @return GUISimulator (gestionnaire d'affichage graphique)
     */
    public GUISimulator getGui() {
        return gui;
    }

    /**
     * incrémente la date courante puis exécute dans l’ordre tous les événements 
     * non encore exécutés jusqu’à cette date tant que la liste des événements 
     * n'est pas vide
     */
    @Override
    public void next() {
        master.strategie2(this);
        Evenement enCours;
        this.incrementeDate();
        if (!this.simulationTerminee()) {
            enCours = (Evenement) this.listeEvent.get(0);
            while (enCours.getDate() <= this.date) {
                enCours.execute();
                this.listeEvent.remove(0);
                if (!this.simulationTerminee()) {
                    enCours = (Evenement) this.listeEvent.get(0);
                } else {
                    break;
                }

            }
            drawMap(this.gui, this.map, taille);
            drawFire(this.gui, this.data.getIncendies(), taille);
            drawRobots(this.gui, this.data.getRobots(), taille);
        } else {
            System.out.println("Simulation terminée");
        }
    }

    /**
     * réinitialise la simulation en relisant les données du fichier source et 
     * en redessinant la carte
     */
    @Override
    public void restart() {
        try {
            this.data = LecteurDonnees.lire(this.chemin);
            this.map = this.data.getCarte();
            this.date = 0;
            this.listeEvent = new ArrayList<Evenement>();
            this.master = new ChefPompier(this.data);
            drawMap(gui, map, taille);
            drawFire(gui, this.data.getIncendies(), taille);
            drawRobots(gui, this.data.getRobots(), taille);
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier spécifié n'existe pas");
        } catch (DataFormatException d) {
            System.out.println("Le fichier spécifié n'a pas le bon format");
        }
    }

    /**
     * ajoute un événement à la liste et la retrie par ordre chronologique
     * @param e l'événement à ajouter
     */
    public void ajouteEvenement(Evenement e) {
        this.listeEvent.add(e);
        Collections.sort(this.listeEvent);
    }

    /**
     * incrémente la date d'un pas de 100 pour que la vitesse du programme soit 
     * raisonnable
     */
    public void incrementeDate() {
        this.date = this.date + 100;
    }

    /**
     * Si la liste des événements est vide, la simulation est terminée
     */
    public boolean simulationTerminee() {
        if (this.listeEvent.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * Parcourt la matrice représantant la carte et  pour chaque case dessine un 
     * carré de la couleur correspondante à son type
     * @param gui
     * @param map
     * @param taille 
     */
    private static void drawMap(GUISimulator gui, Carte map, int taille) {
        int i, j;
        NatureTerrain type;
        Color couleurCase;
        //Dessiner la carte

        for (i = 0; i < map.getNbLignes(); i++) {
            for (j = 0; j < map.getNbColonnes(); j++) {
                type = map.getCase(i, j).getNature();
                switch (type) {
                    case EAU:
                        couleurCase = Color.BLUE;
                        break;
                    case FORET:
                        couleurCase = Color.GREEN;
                        break;
                    case ROCHE:
                        couleurCase = Color.LIGHT_GRAY;
                        break;
                    case TERRAIN_LIBRE:
                        couleurCase = Color.white;
                        break;
                    case HABITAT:
                        couleurCase = Color.yellow;
                        break;
                    default:
                        couleurCase = Color.white;
                        break;
                }
                gui.addGraphicalElement(new Rectangle((j + 1) * taille, (i + 1) * taille, Color.black, couleurCase, taille));

            }
        }

    }

    /**
     * Dessine les incendies aux coordonnées correspondantes sur la carte
     * @param gui
     * @param ListeIncendies
     * @param taille 
     */
    public static void drawFire(GUISimulator gui, ArrayList<Incendie> ListeIncendies, int taille) {
        int i, x, y;
        for (Incendie incendie : ListeIncendies) {
            if (incendie.getIntensite() > 0) {
                x = incendie.getPosition().getLigne();
                y = incendie.getPosition().getColonne();
                gui.addGraphicalElement(new ImageElement((y + 1) * taille - (int)(taille*0.3), (x + 1) * taille - (int)(taille*0.3), "." + File.separator + "pictures" + File.separator + "fire.png", (int)(taille*0.6), (int)(taille*0.6), null));
            }
        }
    }

    /**
     * Dessine les robots aux coordonnées correspondantes sur la carte
     * @param gui
     * @param ListeRobots
     * @param taille 
     */
    public static void drawRobots(GUISimulator gui, ArrayList<Robot> ListeRobots, int taille) {
        int x, y;
        for (Robot robot : ListeRobots) {
            x = robot.getPosition().getLigne();
            y = robot.getPosition().getColonne();
            gui.addGraphicalElement(new ImageElement((y + 1) * taille - (int)(taille*0.3), (x + 1) * taille - (int)(taille*0.3), robot.getImage(), (int)(taille*0.6), (int)(taille*0.6), null));
        }
    }
}
