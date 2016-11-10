package elements;

public class RobotAChenilles extends Robot {

    private final static int capacite = 2000;
    private final static double vitesse = 60;
    private final static int tempsRemplissage = 50;
    private final static double vitesseIntervention = 12.5;

    public RobotAChenilles(Carte carte) {
        super(carte, capacite, tempsRemplissage, vitesseIntervention, vitesse);
    }

    @Override
    public void setPosition(Case C) {
        try {
            if ((C.getNature() == NatureTerrain.EAU) || (C.getNature() == NatureTerrain.ROCHE)) {
                //terrain inaccessible pour ce type de robot
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
            System.out.println("Chenille : cette case ne peut pas être atteinte");
        } catch (WrongCaseNatureException e) {
            System.out.println("Chenille : cette case n'a pas la bonne nature");
        }
    }

    @Override
    public void setPositionInit(Case C) {
        try {
            if (!estAccessible(C)) {
                //terrain inaccessible pour ce type de robot
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
        if ((T == NatureTerrain.EAU) || (T == NatureTerrain.ROCHE)) {
            return 0;
        }
        if (T == NatureTerrain.FORET) {
            return vitesse / 2;
        } else {
            return vitesse;
        }

    }
    
    @Override
    public String getImage() {
        return "C:\\Users\\Sylvain\\Documents\\_ISSC\\Java\\Robots-pompiers\\cartes\\wall-e.png";
    }

    @Override
    public boolean peutRemplir() {
        for (Case Voisin : this.map.ListeVoisins(this.position)) { //Si la case est voisine de sa position
            if (Voisin.getNature() == NatureTerrain.EAU) { //et qu'elle est composee d'eau
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean estAccessible(Case C){

        if ((C.getNature() == NatureTerrain.EAU) || (C.getNature() == NatureTerrain.ROCHE)) {
            //terrain inaccessible pour ce type de robot
            return false;
        } else {
            return true;
        }
    }

}
