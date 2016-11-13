package elements;

public class Case {

    private int ligne;
    private int colonne;
    private NatureTerrain nature;

    /**
     * Crée l'objet Case à partir de ses coordonnées et de la nature de son
     * terrain (voir NatureTerrain.java)
     *
     * @param lig ligne de la case
     * @param col colonne de la case
     * @param nat nature de la case
     */
    public Case(int lig, int col, NatureTerrain nat) {
        this.ligne = lig;
        this.colonne = col;
        this.nature = nat;
    }

    /**
     * @return ligne de la case
     */
    public int getLigne() {
        return this.ligne;
    }

    /**
     * @return colonne de la case
     */
    public int getColonne() {
        return this.colonne;
    }

    /**
     * @return nature du terrain
     */
    public NatureTerrain getNature() {
        return this.nature;
    }

    /**
     * méthode toString
     *
     * @return ligne, colonne et nature de la case
     */
    @Override
    public String toString() {
        return "Case : " + ligne + ", " + colonne + ", " + nature;
    }

    /**
     * méthode equals
     *
     * @param obj Case à comparer
     * @return true si les deux cases sont égales
     */
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
