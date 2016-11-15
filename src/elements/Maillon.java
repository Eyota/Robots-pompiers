/*
Numéro de groupe : 50
*/
package elements;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Structure permettant d'exécuter l'algorithme de Dijsktra
 */
public class Maillon {

    private Case courant;
    private Case pere;
    private int dureechemin;
    private Boolean visite;
    private LinkedList<VoisinsDijsktra> listVoisins = new LinkedList<>();

    /**
     * Initialise un maillon de Dijsktra : le maillon est de cout maximal et
     * n'as pas été visité
     */
    public Maillon(Case c) {
        this.courant = c;
        this.dureechemin = Integer.MAX_VALUE;
        this.visite = false;
    }

    /**
     * ajoute un VoisinsDijsktra à la liste des voisins dans un Maillon
     *
     * @param l la liste des voisins
     * @param c la case voisine
     * @param cout la distance entre la case courante et la case voisine
     */
    public void ajouteVoisin(List<VoisinsDijsktra> l, Case c, int cout) {
        VoisinsDijsktra m = new VoisinsDijsktra(c, cout);
        l.add(m);
    }

    /**
     * accède à la case courante du maillon
     *
     * @return la case courante
     */
    public Case getCourant() {
        return this.courant;
    }

    /**
     * accède à la case père du maillon
     *
     * @return la case père
     */
    public Case getPere() {
        return this.pere;
    }

    /**
     * accède à la durée du chemin
     *
     * @return la durée
     */
    public int getDuree() {
        return this.dureechemin;
    }

    /**
     * retourne true si le maillon a été visité
     */
    public Boolean getVisite() {
        return this.visite;
    }

    /**
     * accède à la liste des voisins de la case courante du maillon
     *
     * @return la liste
     */
    public LinkedList<VoisinsDijsktra> getListVoisins() {
        return this.listVoisins;
    }

    public void setPere(Case per) {
        this.pere = per;
    }

    /**
     * met la duree du maillon à la valeur passée en paramètre
     *
     * @param d la duree
     */
    public void setDuree(int d) {
        this.dureechemin = d;
    }

    /**
     * met visite à la valeur passée en paramètre
     *
     * @param b
     */
    public void setVisite(Boolean b) {
        this.visite = b;
    }

    /**
     * la liste de voisins du maillon devient celle passée en paramètre
     *
     * @param baba la liste des voisins
     */
    public void setListVoisins(List<VoisinsDijsktra> baba) {
        this.listVoisins = (LinkedList<VoisinsDijsktra>) baba;
    }
}
