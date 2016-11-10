/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.util.LinkedList;

/**
 *
 * @author Camille Gardelle
 */
public class PlusCourtChemin {
    private LinkedList<VoisinsDijsktra> chemin;
    private Double duree;
    
    public PlusCourtChemin(Robot wizz, Case arrivee){
        try{
            System.out.println("Constructeur");
            this.ImplementerChemin(dijsktra(structureDepart(wizz)), arrivee, wizz);
            System.out.println("Chemin construit");
        }
        catch(UnreachableCaseException u){
            System.out.println("Case inaccessible (plus court chemin)");
        }
    }
    
    private void ImplementerChemin(LinkedList<Maillon> tableau, Case dst, Robot wizz){
       System.out.println("Début impémenter chemin");
       Case precedente = new Case(dst.getLigne(), dst.getColonne(), dst.getNature());
       for (Maillon m : tableau){
           if (m.getCourant().equals(precedente)){ //on se met sur le maillon de la case a atteindre
               this.duree = m.getDuree(); //on met la duree totale du chemin
            }
        }   
        while (!precedente.equals(wizz.position))//tant que la case precedente n'est pas celle du robot
        {
            for (Maillon m : tableau){
                if (m.getCourant().equals(precedente)){ //on se met sur le maillon de la case a atteindre
                    this.ajouteChemin(m.getCourant(), m.getDuree());
                    precedente = m.getPere();
                }
            }
        }
        System.out.println("Fin impémenter chemin");
    }
    
    public LinkedList<VoisinsDijsktra> getChemin(){
        return this.chemin;
    }
    
    public Double getDuree(){
        return this.duree;
    }
    
    
    private void ajouteChemin(Case c, Double cout){
        System.out.println("Ajoute chemin");
       VoisinsDijsktra m = new VoisinsDijsktra(c, cout);
       this.chemin.addFirst(m);//ajout en tete
   }
    
   
    
    private void ajouteMaillon(LinkedList<Maillon> l, Case c){
       Maillon m = new Maillon(c);
       l.add(m);
    }
           
    
    private LinkedList<Maillon> dijsktra(LinkedList<Maillon> tableau){
        //jusqu'a ce que tous les Maillons aient ete traveses
        System.out.println("Début dijkstra");
        Boolean vide = true;
        int i=0;
        for (Maillon r : tableau){
            vide = vide && r.getVisite();
        }
        while (!vide)
        {
            //on met le premier maillon en minimum, avec le cout max
            VoisinsDijsktra minimum = new VoisinsDijsktra(tableau.get(0).getCourant());
            //on parcours la liste des maillons (le tableau) pour trouver le minimum en cout
            for (Maillon m : tableau){
                if (m.getDuree()<minimum.getCoutV()){
                    minimum.setCoutV(m.getDuree());
                    minimum.setDestination(m.getCourant());
                }
            }
            //System.out.println("Fin premier for");
            //on prend la liste des voisins du maillon de cout minimum
            for (Maillon m : tableau){
                if (m.getCourant().equals(minimum.getDestinationV())){
                    //pour chaque voisin
                    for (VoisinsDijsktra voisin : m.getListVoisins()){
                        //rechercher le maillon destination
                        for (Maillon dst : tableau){
                            if (voisin.getDestinationV().equals(dst.getCourant())){
                                //si celui_ci n'est pas sortie du tableau
                                if(!dst.getVisite()){
                                    //donc le maillon courant est m et le maillon destination est dst
                                    //regarder si duree(courant) + distance < duree(destination)
                                     if ((m.getDuree() + voisin.getCoutV()) < dst.getDuree()){
                                    //dans ce cas changer
                                    dst.setDuree(m.getDuree() + voisin.getCoutV());
                                    dst.setPere(m.getCourant());
                                    }
                                }
                            
                                
                            }
                        }
                    }
                //retirer courant du tableau
                m.setVisite(true);
                }
            }
            i=0;
            //System.out.println("Vide = true");
            vide = true;
            for (Maillon r : tableau){
                vide = vide && r.getVisite();
            }
        }
        return tableau;
    }
            
            
    private LinkedList<VoisinsDijsktra> ListeVoisinsD(Maillon a, Robot wizz) throws UnreachableCaseException{
        //obtenir la liste des voisins avec leur cout
        LinkedList<VoisinsDijsktra> voisins = new LinkedList<>();
        
        for (Direction dir : Direction.values()){
            if(wizz.getMap().voisinExiste(a.getCourant(), dir)){
                if (wizz.estAccessible(wizz.getMap().getVoisin(a.getCourant(), dir))){
                    a.ajouteVoisin(voisins, wizz.getMap().getVoisin(a.getCourant(), dir), wizz.getMap().getTailleCases()/wizz.getVitesse(a.getCourant().getNature()));
                }
            }
        }
        return voisins;
    }
    
    
    
    
    private LinkedList<Maillon> structureDepart(Robot wizz) throws UnreachableCaseException{
        //initialisation de dijsktra : on met les sommets et la liste des voisins 
        LinkedList<Maillon> tableauD = new LinkedList<>();

        //on cree une liste de Maillon initialises
        for (int i = 0; i <= wizz.map.getNbLignes()-1; i++){
            for (int j = 0; j <= wizz.map.getNbColonnes()-1; j++){
                if (wizz.estAccessible(wizz.map.getCase(i, j))){
                    ajouteMaillon(tableauD,wizz.map.getCase(i, j));
                }
            }
        }
        //on cree la liste des voisin pour chaque maillon
        for (Maillon a : tableauD ) {
            a.setListVoisins(ListeVoisinsD(a,wizz));
            //on initialise le maillon src a 0
            if (a.getCourant().equals(wizz.getPosition())){
                a.setDuree((double)0);
            }
        }
        System.out.println(tableauD.size());
        return tableauD; 
    }
    
}