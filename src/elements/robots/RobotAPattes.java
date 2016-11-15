/*
Numéro de groupe : 50
*/
package elements.robots;

import elements.Carte;
import elements.Case;
import elements.NatureTerrain;
import elements.UnreachableCaseException;
import elements.WrongCaseNatureException;
import java.io.File;

public class RobotAPattes extends Robot {

    private final static int capacite = Integer.MAX_VALUE;
    private final static double vitesse = 30;
    private final static double vitesseIntervention = 10;

    public RobotAPattes(Carte carte) {
        super(carte, capacite, 0, vitesseIntervention, vitesse);
    }

    @Override
    public String toString() {
        return "robot a pattes";
    }

    @Override
    public double getVitesse(NatureTerrain T) {
        if (T == NatureTerrain.EAU) {
            return 0;
        }
        if (T == NatureTerrain.ROCHE) {
            return 10 * 0.28;
        } else {
            return vitesse * 0.28;
        }
    }

    @Override
    public String getImage() {
        return super.pictureFolder + "pattes.png";
    }

    @Override
    public void deverserEau(int Volume) { //le robot a de la poudre dans le réservoir, et donc un réservoir infini
    }

    @Override
    public void remplirReservoir() { //le robot a de la poudre dans le réservoir, et donc un réservoir infini
    }

    @Override
    public boolean estAccessible(Case C) {
        if (C.getNature() == NatureTerrain.EAU) {
            //terrain inaccessible pour ce type de robot
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean peutRemplir(Case C) {
        return false;
    }

}
