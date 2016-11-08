
package elements;

public class RobotAChenilles extends Robot{
	
	private final static int capacite=2000;
	private final static double vitesse=60;
	private final static int tempsRemplissage=50;
	private final static double vitesseIntervention=12.5;
	
	public RobotAChenilles(Carte carte){
		super(carte, capacite, tempsRemplissage, vitesseIntervention, vitesse);
	}
	
	
	@Override
	public void setPosition(Case C) {

		if ((C.getNature()==NatureTerrain.EAU) || (C.getNature()==NatureTerrain.ROCHE)){ 
			//terrain inaccessible pour ce type de robot
			System.out.println("Le robot ne peut pas se déplacer ici.");       //Execption
		}
		
		else {
			for (Case Voisin : map.ListeVoisins(super.position)){
					if (C==Voisin) super.position=C;
			}
                        super.position=C;
		}
		
	}
		
	@Override
	public double getVitesse(NatureTerrain T){
		if ((T==NatureTerrain.EAU)||(T==NatureTerrain.ROCHE)) return 0;
		if (T==NatureTerrain.FORET) return vitesse/2;
		else return vitesse;
		
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