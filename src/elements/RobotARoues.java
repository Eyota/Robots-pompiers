/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

public class RobotARoues extends Robot{
	
	private final static int capacite=5000;
	private final static double vitesse=80;
	private final static int tempsRemplissage=10;
	private final static double vitesseIntervention=25;
	
	public RobotARoues(Carte carte){
		super(carte, capacite, tempsRemplissage, vitesseIntervention, vitesse);
	}
	
	
    @Override
    public void setPosition(Case C) {
        try{
        if ((C.getNature() == NatureTerrain.EAU) || (C.getNature() == NatureTerrain.ROCHE) || (C.getNature() == NatureTerrain.FORET)) {
            throw new WrongCaseNatureException();
        } else {
            while (C != super.position) {
                for (Case Voisin : map.ListeVoisins(super.position)) {
                    if (C.equals(Voisin)) {
                        System.out.println("Test");
                        super.position = C;
                    }
                }
                throw new UnreachableCaseException();
            }

        }
        }
        catch (UnreachableCaseException e){
            System.out.println("Le robot ne peut pas atteindre cette case car elle n'existe pas");
        }
        catch (WrongCaseNatureException e){
            System.out.println("Cette case n'a pas la bonne nature");
        }
        
    }

    @Override
    public void setPositionInit(Case C) {
        try{
        if ((C.getNature() == NatureTerrain.EAU) || (C.getNature() == NatureTerrain.ROCHE) || (C.getNature() == NatureTerrain.FORET)) {
            throw new WrongCaseNatureException();
        } else {
            super.position = C;
        }
        }
                
        catch (WrongCaseNatureException e){
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
        public boolean peutRemplir(){  
            for (Case Voisin : this.map.ListeVoisins(this.position)){ //Si la case est voisine de sa position
                if (Voisin.getNature()==NatureTerrain.EAU){ //et qu'elle est composee d'eau
                    return true;
                }
            }
            return false;
        }

}

