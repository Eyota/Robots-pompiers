package elements;


import java.util.ArrayList;
import java.util.List;


public class Carte {
	private int tailleCases;
	protected Case[][] matrice;

	public Carte (int nbLignes, int nbColonnes, int taille){ //Encore une fois, faut-il faire des constructeurs ici ou non ?
	//Faut il empêcher que lignes et colonne soient négatives ?
		this.matrice = new Case [nbLignes][nbColonnes];
		this.tailleCases = taille;
	}
  
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
            //if (lig<0 || lig>this.getNbLignes() || col<0 || col>this.getNbColonnes()){
		//throw new CaseDoesntExistExecption;
            //}
            this.matrice[lig][col] = cases;  
	}
  
	public boolean voisinExiste(Case src, Direction dir){
		switch(dir){
			case NORD :
				if (src.getLigne() > 0) return true;
				else return false;
			case SUD :
				if (src.getLigne() < this.getNbLignes()-1) return true;
				else return false;
			case EST :
				if (src.getColonne() < this.getNbColonnes()-1) return true;
				else return false;
			case OUEST :
				if (src.getColonne() > 0) return true;
				else return false;
                        default :
                            return false;
		}
	}
	
	public Case getVoisin(Case src, Direction dir) throws UnreachableCaseException{
		if (!this.voisinExiste(src, dir)){
			throw new UnreachableCaseException();
		}
		switch(dir){
			case NORD :
				return this.matrice[src.getLigne()-1][src.getColonne()];
			case SUD :
				return this.matrice[src.getLigne()+1][src.getColonne()];
			case EST :
				return this.matrice[src.getLigne()][src.getColonne()+1];
			case OUEST :
				return this.matrice[src.getLigne()][src.getColonne()-1];
                        default :
                                return null;
		}
	} 
	
	public List<Case> ListeVoisins(Case src){
		ArrayList<Case> List = new ArrayList<Case>();
		try{
		for (Direction dir : Direction.values()){
                        if (this.voisinExiste(src, dir)){
                            List.add(getVoisin(src,dir));
                        }
		}
                }
                catch (UnreachableCaseException e){
                    System.out.println("On ne devrait pas arriver ici");
                }
		return List;
	}
  
}
