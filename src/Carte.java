
public class Carte {
	private int tailleCases;
	private Case[][] matrice;

	public Carte (int nbLignes, int nbColonnes, int taille){ //Encore une fois, faut-il faire des constructeurs ici ou non ?
	//Faut il empêcher que lignes et colonne soient négatives ?
		this.matrice = new Case [nbLignes][nbColonnes];
		this.tailleCases = taille;
	}
  
	//Evenement ???
  
	public int getNbLignes(){
		return this.matrice.length;
	}
  
	public int getNbColonnes(){
		return this.matrice[0].length;
	}
  
	public int getTailleCases(){
		return this.tailleCases;
	}
  
	public Case getCase(int lig, int col){
		return this.matrice[lig][col];
	}
	
	public void setCase(int lig, int col, Case cases){
		this.matrice[lig][col] = cases;
	}
  
	public boolean voisinExiste(Case src, Direction dir){
		switch(dir){
			case NORD :
				if (src.getLigne > 0) return true;
				else return false;
				break;
			case SUD :
				if (src.getLigne < this.getNbLignes) return true;
				else return false;
				break;
			case EST :
				if (src.getColonne < this.getNbColonnes) return true;
				else return false;
				break;
			case OUEST :
				if (src.getColonne > 0) return true;
				else return false;
				break;
		}
	}
	
	public Case getVoisin(Case src, Direction dir){
		if (!this.voisinExiste(src, dir)){
			//raise execption
		}
		switch(dir){
			case NORD :
				return this.matrice[src.getLigne-1][src.getColonne];
				break;	//break nécessaire ?
			case SUD :
				return this.matrice[src.getLigne+1][src.getColonne];
				break;
			case EST :
				return this.matrice[src.getLigne][src.getColonne+1];
				break;
			case OUEST :
				return this.matrice[src.getLigne][src.getColonne-1];
				break;
		}
	}  
  
}
