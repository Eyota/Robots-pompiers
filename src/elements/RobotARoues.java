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
		if ((C.getNature()==NatureTerrain.EAU)||(C.getNature()==NatureTerrain.ROCHE)||(C.getNature()==NatureTerrain.FORET)){
			System.out.println("Le robot ne peut pas se d�placer ici.");
		}
		else{
			for (Case Voisin : map.ListeVoisins(super.position)){ //Si case voisine de sa position
					if (C==Voisin) super.position=C;
			}
                        super.position=C;
		}	
		
		
	}
	
	@Override
	public double getVitesse(NatureTerrain T){
			if (T==(NatureTerrain.HABITAT)||(T==NatureTerrain.TERRAIN_LIBRE)) 
				return vitesse;
			else
				return 0;
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

