
public class Case {
	private int ligne;
	private int colonne;
	private NatureTerrain nature;
  
	public Case (int lig, int col, NatureTerrain nat){
		this.ligne=lig;
		this.colonne=col;
		this.nature=nat;
	}

	public int getLigne(){
		return this.ligne;
	}

	public int getColonne(){
		return this.colonne;
	}

	public NatureTerrain getNature(){
		return this.nature;
	}

}
