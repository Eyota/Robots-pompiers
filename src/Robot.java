abstract class Robot{
	
	protected Case position;
	protected Carte map;
	protected double volumeEau; //en litres
	private int capacite; //en litres
	private int tempsRemplissage; //en s
	private double vitesseIntervention; // en L/s
	@SuppressWarnings("unused") //Utilisée dans les classes héritées de robot.
	private double vitesse; // en km/h. 
	
	protected Robot(Carte carte, int cap, int tpsRemplissage, double vtIntervention, double vit){
		this.map=carte;
		this.volumeEau=cap;
		this.capacite=cap;
		this.tempsRemplissage=tpsRemplissage;
		this.vitesseIntervention=vtIntervention;
		this.vitesse=vit;
	}
	public Case getPosition(){
		
		return this.position;
	}
	
	public double getVolumeEau(){
		
		return this.volumeEau;
	}
	
	public int getTempsRemplissage(){
		
		return this.tempsRemplissage;
		
	}
	
	abstract public void setPosition(Case C);
	
	abstract public double getVitesse(NatureTerrain T); //OK mais voir implémentation lecture fichier
	
	public double gettempsIntervention(int Volume){
		return Volume/vitesseIntervention ;
	}
	
	public void deverserEau(int Volume){
		if (Volume <= this.volumeEau){ //si le robot contient assez d'eau
			this.volumeEau = this.volumeEau-Volume; //On diminue la quantité d'eau dans le réservoir
			}
	}
	
	public void remplirReservoir(){
		for (Case Voisin : map.ListeVoisins(position)){ //Si la case est voisine de sa position
			if (Voisin.getNature()==NatureTerrain.EAU){ //et qu'elle est composée d'eau
				this.volumeEau=capacite; //remplissage
			}
		}
	}
	
	
}
/**************************************************************************/
	
class Drone extends Robot{
	private final static int capacite=10000;
	private final static int vitesse=100;
	private final static int tempsRemplissage=30;
	private final static double vitesseIntervention=10000/30;
	
	
	public Drone(Carte carte){
		super(carte, capacite, tempsRemplissage, vitesseIntervention, vitesse);
	}
	
	@Override
	public void setPosition(Case C) {
		for (Case Voisin : map.ListeVoisins(super.position)){ //Si la case est voisine de sa position
				if (C==Voisin) super.position=C;
		}
		
	}
	
	@Override
	public double getVitesse(NatureTerrain T){
		return vitesse;
	}
	
	@Override
	public void remplirReservoir(){
		if (position.getNature()==NatureTerrain.EAU){ //S'il est placé sur une case d'eau
		super.volumeEau=capacite;
		}
	}

}
/***************************************************************************/


class RobotARoues extends Robot{
	
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
			System.out.println("Le robot ne peut pas se déplacer ici.");
		}
		else{
			for (Case Voisin : map.ListeVoisins(super.position)){ //Si case voisine de sa position
					if (C==Voisin) super.position=C;
			}
		}	
		
		
	}
	
	@Override
	public double getVitesse(NatureTerrain T){
			if (T==(NatureTerrain.HABITAT)||(T==NatureTerrain.TERRAIN_LIBRE)) 
				return vitesse;
			else
				return 0;
	}

}

/***************************************************************************/
class RobotAChenilles extends Robot{
	
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
			System.out.println("Le robot ne peut pas se déplacer ici.");
		}
		
		else {
			for (Case Voisin : map.ListeVoisins(super.position)){
					if (C==Voisin) super.position=C;
			}
		}
		
	}
		
	@Override
	public double getVitesse(NatureTerrain T){
		if ((T==NatureTerrain.EAU)||(T==NatureTerrain.ROCHE)) return 0;
		if (T==NatureTerrain.FORET) return vitesse/2;
		else return vitesse;
		
	}
}

/***************************************************************************/
class RobotAPattes extends Robot{
	private final static int capacite = Integer.MAX_VALUE;
	private final static double vitesse = 30;
	private final static double vitesseIntervention=10;
	
	public RobotAPattes(Carte carte){
		super(carte, capacite, 0, vitesseIntervention, vitesse);
	}
	
	@Override
	public void setPosition(Case C) {
		if (C.getNature()==NatureTerrain.EAU){
			System.out.println("Le robot ne peut pas se déplacer ici.");
		}
		else{
			for (Case Voisin : map.ListeVoisins(super.position)){
					if (C==Voisin) super.position=C;
			}
		}
		
	}
	
	@Override
	public double getVitesse(NatureTerrain T){
		if (T==NatureTerrain.EAU) return 0;
		if (T==NatureTerrain.ROCHE) return 10;
		else return vitesse;
	}

	@Override
	public void deverserEau(int Volume) { //le robot a de la poudre dans le réservoir, et donc un réservoir infini
	}

	@Override
	public void remplirReservoir() { //le robot a de la poudre dans le réservoir, et donc un réservoir infini
	}
	
}