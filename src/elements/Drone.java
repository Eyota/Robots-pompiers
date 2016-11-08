
package elements;

public class Drone extends Robot{
	private final static int capacite=10000;
	private final static int vitesse=100;
	private final static int tempsRemplissage=30;
	private final static double vitesseIntervention=10000/30;
	
	
	public Drone(Carte carte) {

        super(carte, capacite, tempsRemplissage, vitesseIntervention, vitesse);

    }

    @Override
    public void setPosition(Case C) {
       try {
        while (super.position != C) {
            for (Case voisin : map.ListeVoisins(super.position)) {
                //Si la case est voisine de sa position
                if (C == voisin) {
                    super.position = C;
                }
            } 
        throw new UnreachableCaseException();
        }
        }
        catch (UnreachableCaseException e){
            System.out.println("Cette case ne peut pas être atteinte");
        }
    }
    
    public void setPositionInit(Case C) {
        super.position = C;
    }

    @Override
    public double getVitesse(NatureTerrain T) {
        return vitesse;
    }

    @Override
    public void remplirReservoir() {
        if (position.getNature() == NatureTerrain.EAU) { //S'il est plac� sur une case d'eau
            super.volumeEau = capacite;
        }
    }
        
    @Override
    public boolean peutRemplir(){
        if (position.getNature() == NatureTerrain.EAU)
            return true;
        else
            return false;
        }

}