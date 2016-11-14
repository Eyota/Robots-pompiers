package io;

import elements.robots.Drone;
import elements.DonneesSimulation;
import elements.robots.Robot;
import elements.Carte;
import elements.Incendie;
import elements.Case;
import elements.NatureTerrain;
import elements.robots.RobotAChenilles;
import elements.robots.RobotAPattes;
import elements.robots.RobotARoues;
import elements.UnreachableCaseException;
import elements.WrongCaseNatureException;
import java.io.*;
import static java.lang.System.exit;
import java.util.*;
import java.util.zip.DataFormatException;

/**
 * Lecteur de cartes au format spectifié dans le sujet. Les données sur les
 * cases, robots puis incendies sont lues dans le fichier, puis simplement
 * affichées. A noter: pas de vérification sémantique sur les valeurs numériques
 * lues.
 */
public class LecteurDonnees {

    /**
     * Lit et affiche le contenu d'un fichier de donnees (cases, robots et
     * incendies). Ceci est méthode de classe; utilisation:
     * LecteurDonnees.lire(fichierDonnees)
     *
     * @param fichierDonnees nom du fichier à lire
     */
    public static DonneesSimulation lire(String fichierDonnees)
            throws FileNotFoundException, DataFormatException {
        System.out.println("\n == Lecture du fichier" + fichierDonnees);
        LecteurDonnees lecteur = new LecteurDonnees(fichierDonnees);
        DonneesSimulation donnees = new DonneesSimulation();
        Carte map = lireCarte();
        donnees.setCarte(map);
        donnees.setIncendies(lireIncendies(map));
        donnees.setRobots(lireRobots(map));
        return donnees;
    }

    // Tout le reste de la classe est prive!
    private static Scanner scanner;

    /**
     * Constructeur prive; impossible d'instancier la classe depuis l'exterieur
     *
     * @param fichierDonnees nom du fichier a lire
     */
    private LecteurDonnees(String fichierDonnees)
            throws FileNotFoundException {
        scanner = new Scanner(new File(fichierDonnees));
        scanner.useLocale(Locale.US);
    }

    /**
     * Lit et affiche les donnees de la carte.
     *
     * @throws ExceptionFormatDonnees
     */
    private static Carte lireCarte() throws DataFormatException {
        ignorerCommentaires();
        Carte carte;
        try {
            int nbLignes = scanner.nextInt();
            int nbColonnes = scanner.nextInt();
            int tailleCases = scanner.nextInt();	// en m

            carte = new Carte(nbLignes, nbColonnes, tailleCases);

            for (int lig = 0; lig < nbLignes; lig++) {
                for (int col = 0; col < nbColonnes; col++) {
                    carte.setCase(lig, col, lireCase(lig, col));
                }
            }

        } catch (NoSuchElementException e) {
            throw new DataFormatException("Format invalide. "
                    + "Attendu: nbLignes nbColonnes tailleCases");
        }
        // une ExceptionFormat levee depuis lireCase est remontee telle quelle
        return carte;
    }

    /**
     * Lit et affiche les donnees d'une case.
     */
    private static Case lireCase(int lig, int col) throws DataFormatException {
        ignorerCommentaires();
        String chaineNature = new String();
        NatureTerrain nature;
        Case cases;

        try {
            chaineNature = scanner.next();
            nature = NatureTerrain.valueOf(chaineNature);

            verifieLigneTerminee();

            cases = new Case(lig, col, nature);

        } catch (NoSuchElementException e) {
            throw new DataFormatException("format de case invalide. "
                    + "Attendu: nature altitude [valeur_specifique]");
        }

        return cases;
    }

    /**
     * Lit et affiche les donnees des incendies.
     */
    private static ArrayList<Incendie> lireIncendies(Carte map) throws DataFormatException {
        ignorerCommentaires();
        ArrayList<Incendie> liste = new ArrayList();
        try {
            int nbIncendies = scanner.nextInt();
            //System.out.println("Nb d'incendies = " + nbIncendies);
            for (int i = 0; i < nbIncendies; i++) {
                liste.add(lireIncendie(i, map));
            }

        } catch (NoSuchElementException e) {
            throw new DataFormatException("Format invalide. "
                    + "Attendu: nbIncendies");
        }
        return liste;
    }

    /**
     * Lit et affiche les donnees du i-eme incendie.
     *
     * @param i
     */
    private static Incendie lireIncendie(int i, Carte map) throws DataFormatException {
        ignorerCommentaires();
        //System.out.print("Incendie " + i + ": ");
        Incendie incendie;
        try {
            int lig = scanner.nextInt();
            int col = scanner.nextInt();
            int intensite = scanner.nextInt();
            Case pos = map.getCase(lig, col);
            incendie = new Incendie(pos, intensite);
            if (intensite <= 0) {
                throw new DataFormatException("incendie " + i
                        + "nb litres pour eteindre doit etre > 0");
            }
            verifieLigneTerminee();

        } catch (NoSuchElementException e) {
            throw new DataFormatException("format d'incendie invalide. "
                    + "Attendu: ligne colonne intensite");
        }
        return incendie;
    }

    /**
     * Lit et affiche les donnees des robots.
     */
    private static ArrayList<Robot> lireRobots(Carte map) throws DataFormatException {
        ignorerCommentaires();
        ArrayList<Robot> listeRobots = new ArrayList();
        try {
            int nbRobots = scanner.nextInt();
            //System.out.println("Nb de robots = " + nbRobots);
            for (int i = 0; i < nbRobots; i++) {
                listeRobots.add(lireRobot(i, map));
            }

        } catch (NoSuchElementException e) {
            throw new DataFormatException("Format invalide. "
                    + "Attendu: nbRobots");
        }
        return listeRobots;
    }

    /**
     * Lit et affiche les donnees du i-eme robot.
     *
     * @param i
     */
    private static Robot lireRobot(int i, Carte map) throws DataFormatException {
        ignorerCommentaires();
        //System.out.print("Robot " + i + ": ");
        Robot monRobot;

        try {
            int lig = scanner.nextInt();
            int col = scanner.nextInt();
            Case cases = map.getCase(lig, col);
            String type = scanner.next();

            switch (type) {
                case "DRONE":
                    monRobot = new Drone(map);
                    break;
                case "ROUES":
                    monRobot = new RobotARoues(map);
                    break;
                case "PATTES":
                    monRobot = new RobotAPattes(map);
                    break;
                case "CHENILLES":
                    monRobot = new RobotAChenilles(map);
                    break;
                default:
                    monRobot = new Drone(map);
                    break;
            }

            monRobot.setPositionInit(cases);

            // lecture eventuelle d'une vitesse du robot (entier)
            //System.out.print(" vitesse = ");
            String s = scanner.findInLine("(\\d+)");	// 1 or more digit(s) ?
            // pour lire un flottant:    ("(\\d+(\\.\\d+)?)");

            if (s != null) {
                int vitesse = Integer.parseInt(s);
                if (type == "DRONE" && vitesse > 150) {
                    monRobot.setVitesse(150);
                } else if (type == "CHENILLES" && vitesse > 80) {
                    monRobot.setVitesse(80);
                } else {
                    monRobot.setVitesse(vitesse);
                }
            }
            verifieLigneTerminee();

        } catch (NoSuchElementException e) {
            throw new DataFormatException("format de robot invalide. "
                    + "Attendu: ligne colonne type [valeur_specifique]");
        }

        return monRobot;
    }

    /**
     * Ignore toute (fin de) ligne commencant par '#'
     */
    private static void ignorerCommentaires() {
        while (scanner.hasNext("#.*")) {
            scanner.nextLine();
        }
    }

    /**
     * Verifie qu'il n'y a plus rien a lire sur cette ligne (int ou float).
     *
     * @throws ExceptionFormatDonnees
     */
    private static void verifieLigneTerminee() throws DataFormatException {
        if (scanner.findInLine("(\\d+)") != null) {
            throw new DataFormatException("format invalide, donnees en trop.");
        }
    }
}
