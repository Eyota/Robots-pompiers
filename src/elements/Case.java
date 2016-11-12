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
        
        public static boolean sontVoisines(Case src, Case news){         //??????
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

    @Override
    public String toString() {
        return "Case : " + ligne + ", " + colonne + ", " + nature;
    }       

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Case other = (Case) obj;
        if (this.ligne != other.ligne) {
            return false;
        }
        if (this.colonne != other.colonne) {
            return false;
        }
        if (this.nature != other.nature) {
            return false;
        }
        return true;
    }
    
    
}
