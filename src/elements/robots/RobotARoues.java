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
    private final static int tempsRemplissage = 10*60;
    private final static double vitesseIntervention = 25;

    public RobotARoues(Carte carte) {
        super(carte, capacite, tempsRemplissage, vitesseIntervention, vitesse);
    }
    
    @Override
    public String toString() {
        return "robot a roues";
    }

    @Override
    public void setPosition(Case C) {
        try {
            if(C.equals(this.getPosition())) return;
            if (!estAccessible(C)) {
                throw new WrongCaseNatureException();
            } else {
                for (Case Voisin : map.ListeVoisins(super.position)) {
                    if (C.equals(Voisin)) {
                        super.position = C;
                    }
                }
                if (!super.position.equals(C)) {
                    throw new UnreachableCaseException();
                }
            }

        } catch (UnreachableCaseException e) {
            System.out.println("Roues : cette case ne peut pas être atteinte");
        } catch (WrongCaseNatureException e) {
            System.out.println("Roues : cette case n'a pas la bonne nature");
        }

    }

    @Override
    public void setPositionInit(Case C) {
        try {
            if (!estAccessible(C)) {
                throw new WrongCaseNatureException();
            } else {
                super.position = C;
            }
        } catch (WrongCaseNatureException e) {
            System.out.println("Cette case n'a pas la bonne nature");
        }

    }

    @Override
    public double getVitesse(NatureTerrain T
    ) {
        if (T == (NatureTerrain.HABITAT) || (T == NatureTerrain.TERRAIN_LIBRE)) {
            return vitesse;
        } else {
            return 0;
        }
    }
    
    @Override
    public String getImage() {
        return "C:\\Users\\Sylvain\\Documents\\_ISSC\\Java\\Robots-pompiers\\cartes\\roues.png";
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
public boolean estAccessible(Case C){

    if ((C.getNature() == NatureTerrain.EAU) || (C.getNature() == NatureTerrain.ROCHE) || (C.getNature() == NatureTerrain.FORET)) {
        //terrain inaccessible pour ce type de robot
        return false;
    } else {
        return true;
    }
}

}
