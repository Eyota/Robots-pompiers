package elements;

public class RobotAPattes extends Robot {

    private final static int capacite = Integer.MAX_VALUE;
    private final static double vitesse = 30;
    private final static double vitesseIntervention = 10;

    public RobotAPattes(Carte carte) {
        super(carte, capacite, 0, vitesseIntervention, vitesse);
    }

    @Override
    public void setPosition(Case C) {
        try {
            if (C.getNature() == NatureTerrain.EAU) {
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
            if (C.getNature() == NatureTerrain.EAU) {
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
            return 10;
        } else {
            return vitesse;
        }
    }

    @Override
    public void deverserEau(double Volume) { //le robot a de la poudre dans le r�servoir, et donc un r�servoir infini
    }

    @Override
    public void remplirReservoir() { //le robot a de la poudre dans le r�servoir, et donc un r�servoir infini
    }

    @Override
    public boolean peutRemplir() {
        return false;
    }

}
