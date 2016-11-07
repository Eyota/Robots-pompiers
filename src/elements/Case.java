package elements;


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
        
        public boolean sontVoisines(Case src, Case news){
            double difCol;
            double difLig;

            difLig = src.getLigne() - news.getLigne();
            difCol = src.getColonne() - news.getColonne();

            if(difCol==-1 || difCol==0 || difCol==1){
                if(difLig==-1 || difLig==0 || difLig==1)
                    return true;
            }
            return false;
        }

}
