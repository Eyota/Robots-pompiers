package elements.robots;

import Simulateur.Simulateur;
import elements.Carte;
import elements.Case;
import elements.CaseWithoutWaterException;
import elements.Direction;
import elements.NatureTerrain;
import elements.PlusCourtChemin;
import elements.UnreachableCaseException;
import elements.VoisinsDijsktra;
import elements.events.EventDeplacer;
import elements.events.EventDeplacerCase;
import java.util.ArrayList;

public abstract class Robot {

    protected Case position;
    protected Carte map;
    protected int volumeEau; //en litres
    private int capacite; //en litres
    private int tempsRemplissage; //en s
    private double vitesseIntervention; // en L/s
    private double vitesse; // en km/h. 
    private boolean disponible = true;
    private ArrayList<Case> ptEau;
    private PlusCourtChemin chemin;

    protected Robot(Carte carte, int cap, int tpsRemplissage, double vtIntervention, double vit) {
        this.map = carte;
        this.volumeEau = cap;
        this.capacite = cap;
        this.tempsRemplissage = tpsRemplissage;
        this.vitesseIntervention = vtIntervention;
        this.vitesse = vit;
        this.ptEau = cherchePointsDEau();
    }   
        
    public Case getPosition() {
        return this.position;
    }

    public double getVolumeEau() {
        return this.volumeEau;
    }

    public int getCapacite() {
        return capacite;
    }

    public int getTempsRemplissage() {
        return this.tempsRemplissage;
    }

    public Carte getMap() {
        return map;
    }

    public int gettempsIntervention(int Volume) {
        return (int) (Volume / vitesseIntervention);
    }

    abstract public double getVitesse(NatureTerrain T);

    abstract public String getImage();

    public boolean estDisponible() {
        return this.disponible;
    }

    public ArrayList<Case> getPtEau() {
        return ptEau;
    }

    abstract public void setPosition(Case C);

    abstract public void setPositionInit(Case C);

    public void setVitesse(int speed) {
        this.vitesse = speed;
    }

    public void setDisponible(boolean dispo) {
        this.disponible = dispo;
    }

    public abstract boolean peutRemplir(Case C);

    abstract public boolean estAccessible(Case C);

    public void deverserEau(int Volume) /*throws EmptyTankException */ {
        if (Volume <= this.volumeEau) { //si le robot contient assez d'eau
            this.volumeEau = this.volumeEau - Volume; //On diminue la quantit� d'eau dans le r�servoir
        } else {
            this.volumeEau = 0;
            //throw new EmptyTankException();
        }
    }

    public void remplirReservoir(){ //Au moment où on appelle cette fonction, on a déjà vérifié que le robot pouvait se remplir
        
        this.volumeEau = capacite; //remplissage

    }

    public ArrayList<Case> cherchePointsDEau() {
        int i, j;
        ArrayList<Case> liste = new ArrayList<Case>();
        for (i = 0; i < map.getNbLignes(); i++) {
            for (j = 0; j < map.getNbColonnes(); j++) {
                if (this.peutRemplir(map.getCase(i, j))) {
                    liste.add(map.getCase(i, j));
                }
            }
        }
        return liste;
    }

    public void deplacerRobot(Simulateur simu, Case dst) {   //Pas très optimisé car le chef le calcule déjà...
            //on cherche le plus court chemin
            this.chemin = new PlusCourtChemin(this, dst);
            //on initialise le temps de deplacement a 0
            int coutPrecedent = 0;

            for (VoisinsDijsktra a : this.chemin.getChemin()) {
                //la date de l'evenement est la date du simulateur ajoutee a la date du maillon precedent
                //(date de fin de l'evenement precedent)
                simu.ajouteEvenement(new EventDeplacerCase(simu.getDate() + coutPrecedent, this, a.getDestinationV()));
                //le cout contenu dans un maillon est le temps de fin de deplacement : on le garde pour l'evenement suivant
                coutPrecedent = a.getCoutV();
            }
        
    }

}
