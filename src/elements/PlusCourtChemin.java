
package elements;

import elements.robots.Robot;
import java.util.LinkedList;

public class PlusCourtChemin {

    private LinkedList<VoisinsDijsktra> chemin;
    private int duree;

    public PlusCourtChemin(Robot wizz, Case arrivee) {
        try {
            if (arrivee.equals(wizz.getPosition())) {
                this.duree = 0;
                this.chemin = null;
            } else {
                this.chemin = new LinkedList<>();
                this.ImplementerChemin(dijsktra(structureDepart(wizz)), arrivee, wizz);
            }
        } catch (UnreachableCaseException u) {
            System.out.println("Case inaccessible (plus court chemin)");
        }
    }
    
    public void setDuree(int duree){
        this.duree=duree;
    }

    private void ImplementerChemin(LinkedList<Maillon> tableau, Case dst, Robot wizz) {
        //dans le cas ou le tableau n'existe pas
        Case precedente = new Case(dst.getLigne(), dst.getColonne(), dst.getNature());
        for (Maillon m : tableau) {
            if (m.getCourant().equals(precedente)) { //on se met sur le maillon de la case a atteindre
                this.duree = m.getDuree(); //on met la duree totale du chemin
            }
        }
        int i;

        while (precedente != null && !precedente.equals(wizz.getPosition()) )//tant que la case precedente n'est pas celle du robot
        {
            i = 0;
            for (Maillon m : tableau) {
                if (i < 2 && m.getCourant().equals(precedente)) { //on se met sur le maillon de la case a atteindre
                    i++;
                    this.ajouteChemin(m.getCourant(), m.getDuree());
                    precedente = m.getPere();
                }
            }
        }
    }

    public LinkedList<VoisinsDijsktra> getChemin() {
        return this.chemin;
    }

    public int getDuree() {
        return this.duree;
    }

    private void ajouteChemin(Case c, int cout) {
        VoisinsDijsktra m = new VoisinsDijsktra(c, cout);
        this.chemin.addFirst(m);//ajout en tete
    }

    private void ajouteMaillon(LinkedList<Maillon> l, Case c) {
        Maillon m = new Maillon(c);
        l.add(m);
    }

    private LinkedList<Maillon> dijsktra(LinkedList<Maillon> tableau) {
        //jusqu'a ce que tous les Maillons aient ete traveses
        if (tableau.equals(null)) {
            return null;
        }
        Boolean vide = true;
        for (Maillon r : tableau) {
            vide = vide && r.getVisite();
        }
        VoisinsDijsktra minimum = new VoisinsDijsktra(tableau.get(0).getCourant());
        while (!vide) {
            //on met le premier maillon en minimum, avec le cout max
            minimum.setCoutV(2000000000);
            //on parcours la liste des maillons (le tableau) pour trouver le minimum en cout
            for (Maillon m : tableau) {
                if (!m.getVisite()) {
                    if (m.getDuree() < minimum.getCoutV()) {
                        minimum.setCoutV(m.getDuree());
                        minimum.setDestination(m.getCourant());
                    }
                }
            }
            //on prend la liste des voisins du maillon de cout minimum

            for (Maillon m : tableau) {
                if (m.getCourant().equals(minimum.getDestinationV())) {
                    //pour chaque voisin
                    if (m.getListVoisins() != null) {
                        for (VoisinsDijsktra voisin : m.getListVoisins()) {
                            //rechercher le maillon destination
                            for (Maillon dst : tableau) {
                                if (voisin.getDestinationV().equals(dst.getCourant())) {
                                    //si celui_ci n'est pas sortie du tableau
                                    if (!dst.getVisite()) {
                                        //donc le maillon courant est m et le maillon destination est dst
                                        //regarder si duree(courant) + distance < duree(destination)
                                        if ((m.getDuree() + voisin.getCoutV()) < dst.getDuree()) {
                                            //dans ce cas changer
                                            dst.setDuree(m.getDuree() + voisin.getCoutV());
                                            dst.setPere(m.getCourant());
                                        }
                                    }

                                }
                            }
                        }
                    }
                }
            }

            //retirer courant du tableau
            for (Maillon m : tableau) {
                if (m.getCourant().equals(minimum.getDestinationV())) {
                    //pour chaque voisin
                    m.setVisite(true);
                }
            }

            vide = true;
            for (Maillon r : tableau) {
                vide = vide && r.getVisite();
            }
        }
        return tableau;
    }

    private LinkedList<VoisinsDijsktra> ListeVoisinsD(Maillon a, Robot wizz) throws UnreachableCaseException {
        //obtenir la liste des voisins avec leur cout
        LinkedList<VoisinsDijsktra> voisins = new LinkedList<>();

        for (Direction dir : Direction.values()) {
            if (wizz.getMap().voisinExiste(a.getCourant(), dir)) {
                if (wizz.estAccessible(wizz.getMap().getVoisin(a.getCourant(), dir))) {
                    a.ajouteVoisin(voisins, wizz.getMap().getVoisin(a.getCourant(), dir),(int) (wizz.getMap().getTailleCases() / wizz.getVitesse(a.getCourant().getNature())));
                }
            }
        }
        return voisins;
    }

    private LinkedList<Maillon> structureDepart(Robot wizz) throws UnreachableCaseException {
        //initialisation de dijsktra : on met les sommets et la liste des voisins 
        LinkedList<Maillon> tableauD = new LinkedList<>();
        if (wizz.getMap() != null) {
            //on cree une liste de Maillons initialises
            for (int i = 0; i < wizz.getMap().getNbLignes(); i++) {
                for (int j = 0; j < wizz.getMap().getNbColonnes(); j++) {
                    if (wizz.estAccessible(wizz.getMap().getCase(i, j))) {
                        ajouteMaillon(tableauD, wizz.getMap().getCase(i, j));
                    }
                }
            }
            //on cree la liste des voisin pour chaque maillon
            for (Maillon a : tableauD) {
                a.setListVoisins(ListeVoisinsD(a, wizz));
                //on initialise le maillon src a 0
                if (a.getCourant().equals(wizz.getPosition())) {
                    a.setDuree((int) 0);
                }
            }
        }
        return tableauD;
    }

}
