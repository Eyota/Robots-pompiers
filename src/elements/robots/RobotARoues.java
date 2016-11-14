/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements.robots;

import elements.Carte;
import elements.Case;
import elements.NatureTerrain;
import elements.UnreachableCaseException;
import elements.WrongCaseNatureException;

public class RobotARoues extends Robot {

    private final static int capacite = 5000;
    private final static double vitesse = 80;
    private final static int tempsRemplissage = 10 * 60;
    private final static double vitesseIntervention = 25;

    public RobotARoues(Carte carte) {
        super(carte, capacite, tempsRemplissage, vitesseIntervention, vitesse);
    }

    @Override
    public String toString() {
        return "robot a roues";
    }

    @Override
    public double getVitesse(NatureTerrain T
    ) {
        if (T == (NatureTerrain.HABITAT) || (T == NatureTerrain.TERRAIN_LIBRE)) {
            return vitesse * 0.28;
        } else {
            return 0;
        }
    }

    @Override
    public String getImage() {
        return super.pictureFolder + "roues.png";
    }

    @Override
    public boolean peutRemplir(Case C) {
        for (Case Voisin : this.map.ListeVoisins(C)) { //Si la case est voisine de sa position
            //System.out.println(Voisin);
            if (Voisin.getNature().equals(NatureTerrain.EAU)) { //et qu'elle est composee d'eau
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean estAccessible(Case C) {

        if ((C.getNature() == NatureTerrain.EAU) || (C.getNature() == NatureTerrain.ROCHE) || (C.getNature() == NatureTerrain.FORET)) {
            //terrain inaccessible pour ce type de robot
            return false;
        } else {
            return true;
        }
    }

}
