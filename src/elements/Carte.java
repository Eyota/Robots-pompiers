package elements;

import java.util.ArrayList;
import java.util.List;

public class Carte {

    private int tailleCases;
    protected Case[][] matrice;

    /**
     * Crée l'objet Carte à partir de plusieurs paramètres
     *
     * @param nbLignes nombre de lignes
     * @param nbColonnes nombre de colonnes
     * @param taille taille d'une case
     */
    public Carte(int nbLignes, int nbColonnes, int taille) { //Encore une fois, faut-il faire des constructeurs ici ou non ?
        //Faut il empêcher que lignes et colonne soient négatives ?
        this.matrice = new Case[nbLignes][nbColonnes];
        this.tailleCases = taille;
    }

    /**
     * @return nombre de lignes de la carte
     */
    public int getNbLignes() {
        return this.matrice.length;
    }

    /**
     * @return nombre de colonnes de la carte
     */
    public int getNbColonnes() {
        return this.matrice[0].length;
    }

    /**
     * @return taille des cases de la carte
     */
    public int getTailleCases() {
        return this.tailleCases;
    }

    /**
     * renvoie une case de la carte à partir de ses coordonnées
     *
     * @param lig ligne de la case
     * @param col colonne de la case
     * @return case demandée
     */
    public Case getCase(int lig, int col) {
        return this.matrice[lig][col];
    }

    /**
     * définir le type d'une case de la carte
     *
     * @param lig ligne de la case
     * @param col colonne de la case
     * @param cases case à ajouter
     */
    public void setCase(int lig, int col, Case cases) {
        //if (lig<0 || lig>this.getNbLignes() || col<0 || col>this.getNbColonnes()){
        //throw new CaseDoesntExistExecption;
        //}
        this.matrice[lig][col] = cases;
    }

    /**
     * renvoie un booléen pour savoir si une case voisine existe dans la
     * direction demandée
     *
     * @param src Case
     * @param dir direction
     * @return true si une case voisine existe dans la direction demandée
     */
    public boolean voisinExiste(Case src, Direction dir) {
        switch (dir) {
            case NORD:
                if (src.getLigne() > 0) {
                    return true;
                } else {
                    return false;
                }
            case SUD:
                if (src.getLigne() < this.getNbLignes() - 1) {
                    return true;
                } else {
                    return false;
                }
            case EST:
                if (src.getColonne() < this.getNbColonnes() - 1) {
                    return true;
                } else {
                    return false;
                }
            case OUEST:
                if (src.getColonne() > 0) {
                    return true;
                } else {
                    return false;
                }
            default:
                return false;
        }
    }

    /**
     * renvoie la case voisine de la case src dans la direction dir si elle
     * existe
     *
     * @param src Case
     * @param dir direction
     * @return Case voisine de la Case src dans la direction dir
     * @throws UnreachableCaseException La case n'est pas accessible
     */
    public Case getVoisin(Case src, Direction dir) throws UnreachableCaseException {
        if (!this.voisinExiste(src, dir)) {
            throw new UnreachableCaseException();
        }
        switch (dir) {
            case NORD:
                return this.matrice[src.getLigne() - 1][src.getColonne()];
            case SUD:
                return this.matrice[src.getLigne() + 1][src.getColonne()];
            case EST:
                return this.matrice[src.getLigne()][src.getColonne() + 1];
            case OUEST:
                return this.matrice[src.getLigne()][src.getColonne() - 1];
            default:
                return null;
        }
    }

    /**
     * @param src Case
     * @return renvoie la liste des voisins associés à la Case src
     */
    public List<Case> listeVoisins(Case src) {
        ArrayList<Case> List = new ArrayList<Case>();
        try {
            for (Direction dir : Direction.values()) {
                if (this.voisinExiste(src, dir)) {
                    List.add(getVoisin(src, dir));
                }
            }
        } catch (UnreachableCaseException e) {
            System.out.println("On ne devrait pas arriver ici");
        }
        return List;
    }

}
