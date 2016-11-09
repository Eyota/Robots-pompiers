
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
            for (Case voisin : map.ListeVoisins(super.position)) {
                //Si la case est voisine de sa position
                if (C.equals(voisin)) {
                    super.position = C;
                }
            } 
            if(!super.position.equals(C))
                throw new UnreachableCaseException();
            System.out.println("Drone moves to :" + C.toString());
        }
        catch (UnreachableCaseException e){
            System.out.println("Drone : cette case ne peut pas être atteinte");
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
    public String getImage() {
        return "C:\\Users\\Sylvain\\Documents\\_ISSC\\Java\\Robots-pompiers\\cartes\\drone.png";
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