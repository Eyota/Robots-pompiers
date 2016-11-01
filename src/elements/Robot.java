package elements;

public abstract class Robot{
	
	protected Case position;
	protected Carte map;
	protected double volumeEau; //en litres
	private int capacite; //en litres
	private int tempsRemplissage; //en s
	private double vitesseIntervention; // en L/s
	@SuppressWarnings("unused") //Utilis�e dans les classes h�rit�es de robot.
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
	
	abstract public double getVitesse(NatureTerrain T); //OK mais voir impl�mentation lecture fichier
        
        public void setVitesse(double speed){
            this.vitesse = speed;
        }
	
	public double gettempsIntervention(int Volume){
		return Volume/vitesseIntervention ;
	}
	
	public void deverserEau(int Volume){
		if (Volume <= this.volumeEau){ //si le robot contient assez d'eau
			this.volumeEau = this.volumeEau-Volume; //On diminue la quantit� d'eau dans le r�servoir
			}
	}
	
	public void remplirReservoir(){
		for (Case Voisin : map.ListeVoisins(position)){ //Si la case est voisine de sa position
			if (Voisin.getNature()==NatureTerrain.EAU){ //et qu'elle est compos�e d'eau
				this.volumeEau=capacite; //remplissage
			}
		}
	}
	
	
}


