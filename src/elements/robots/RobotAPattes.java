package elements.robots;

import elements.Carte;
import elements.Case;
import elements.NatureTerrain;
import elements.UnreachableCaseException;
import elements.WrongCaseNatureException;

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
            System.out.println("Pattes : cette case ne peut pas être atteinte");
        } catch (WrongCaseNatureException e) {
            System.out.println("Pattes : cette case n'a pas la bonne nature");
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
    public double getVitesse(NatureTerrain T) {
        if (T == NatureTerrain.EAU) {
            return 0;
        }
        if (T == NatureTerrain.ROCHE) {
            return 10*0.28;
        } else {
            return vitesse*0.28;
        }
    }
    
    @Override
    public String getImage() {
        //return "C:\\Users\\Sylvain\\Documents\\_ISSC\\Java\\Robots-pompiers\\cartes\\pattes.png";
        return "C:\\Users\\Agathe\\Documents\\2A\\POO\\Robots-pompiers-master\\cartes\\pattes.png";
    }

    @Override
    public void deverserEau(int Volume) { //le robot a de la poudre dans le r�servoir, et donc un r�servoir infini
    }

    @Override
    public void remplirReservoir() { //le robot a de la poudre dans le r�servoir, et donc un r�servoir infini
    }

    @Override
  public boolean estAccessible(Case C){
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
