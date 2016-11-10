/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Camille Gardelle
 */
package elements;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Maillon {

    private Case courant;
    private Case pere;
    private Double dureechemin;
    private Boolean visite;
    private LinkedList<VoisinsDijsktra> listVoisins = new LinkedList<>();

    
    public Maillon(Case c){
        this.courant = c;
        this.dureechemin = 200000.0;
        this.visite = false;
    }
    
    
    
   public void ajouteVoisin(List<VoisinsDijsktra> l, Case c, Double cout){
       VoisinsDijsktra m = new VoisinsDijsktra(c, cout);
       l.add(m);
   }

            
    public Case getCourant() {
        return this.courant;
    }

    public Case getPere() {
        return this.pere;
    }

    public Double getDuree() {
        return this.dureechemin;
    }
    
    public Boolean getVisite(){
        return this.visite;
    }
    
    public LinkedList<VoisinsDijsktra> getListVoisins(){
        return this.listVoisins;
    }
    
    public void setCourant(Case c) {
        this.courant = c;
    }

    public void setPere(Case per) {
        this.pere = per;
    }

    public void setDuree(Double d) {
        this.dureechemin = d;
    }

    public void setVisite(Boolean b) {
        this.visite = b;
    }

    public void setListVoisins(List<VoisinsDijsktra> baba) {
        this.listVoisins = (LinkedList<VoisinsDijsktra>) baba;
    }
}
