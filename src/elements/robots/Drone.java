
package elements.robots;

import elements.Carte;
import elements.Case;
import elements.NatureTerrain;
import elements.UnreachableCaseException;
import elements.robots.Robot;

public class Drone extends Robot{
	private final static int capacite=10000;
	private final static int vitesse=100;
	private final static int tempsRemplissage=30*60;
	private final static int vitesseIntervention=100;
	
	
    public Drone(Carte carte) {

        super(carte, capacite, tempsRemplissage, vitesseIntervention, vitesse);

    }
    
    @Override
    public String toString() {
        return "drone";
    }

    @Override
    public void setPosition(Case C) {
       try {
            if(C.equals(this.getPosition())) return;
            for (Case voisin : map.ListeVoisins(super.position)) {
                //Si la case est voisine de sa position
                if (C.equals(voisin)) {
                    super.position = C;
                }
            } 
            if(!super.position.equals(C))
                throw new UnreachableCaseException();
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
        return vitesse*0.28;
    }
    
    public int gettempsIntervention(int Volume) {
        return 30;
    }
    
    @Override
    public String getImage() {
        return "C:\\Users\\Sylvain\\Documents\\_ISSC\\Java\\Robots-pompiers\\cartes\\drone.png";
        //return "C:\\Users\\Agathe\\Documents\\2A\\POO\\Robots-pompiers-master\\cartes\\drone.png";
    }

    @Override
    public void deverserEau(int volume){
        this.volumeEau=0;
    }
    
    @Override
    public void remplirReservoir() {
        if (position.getNature() == NatureTerrain.EAU) { //S'il est plac� sur une case d'eau
            super.volumeEau = capacite;
        }
    }  
       
    @Override
    public boolean estAccessible(Case C){
       return true;
    }

    @Override
    public boolean peutRemplir(Case C) {
        if (C.getNature().equals(NatureTerrain.EAU))
            return true;
        else
            return false;
    }   
    

   

}