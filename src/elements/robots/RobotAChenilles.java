/*
Numéro de groupe : 50
*/
package elements.robots;

import elements.Carte;
import elements.Case;
import elements.NatureTerrain;
import elements.UnreachableCaseException;
import elements.WrongCaseNatureException;

public class RobotAChenilles extends Robot {

    private final static int capacite = 2000;
    private final static double vitesse = 60;
    private final static int tempsRemplissage = 5 * 60;
    private final static double vitesseIntervention = 12.5;

    public RobotAChenilles(Carte carte) {
        super(carte, capacite, tempsRemplissage, vitesseIntervention, vitesse);
    }

    @Override
    public String toString() {
        return "robot a chenilles";
    }

    @Override
    public double getVitesse(NatureTerrain T) {
        if ((T == NatureTerrain.EAU) || (T == NatureTerrain.ROCHE)) {
            return 0;
        }
        if (T == NatureTerrain.FORET) {
            return (vitesse / 2) * 0.28;
        } else {
            return vitesse * 0.28;
        }

    }

    @Override
    public String getImage() {
        return super.pictureFolder + "wall-e.png";
    }

    @Override
    public boolean peutRemplir(Case C) {
        for (Case Voisin : this.map.listeVoisins(C)) { //Si la case est voisine de sa position
            if (Voisin.getNature().equals(NatureTerrain.EAU)) { //et qu'elle est composee d'eau
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean estAccessible(Case C) {

        if ((C.getNature() == NatureTerrain.EAU) || (C.getNature() == NatureTerrain.ROCHE)) {
            //terrain inaccessible pour ce type de robot
            return false;
        } else {
            return true;
        }
    }

}
