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

    /**
     * Construit l'objet Robot à partir de plusieurs paramètres
     *
     * @param carte carte dans laquelle le robot évolue
     * @param cap capacité du réservoir maximale
     * @param tpsRemplissage temps de remplissage
     * @param vtIntervention vitesse d'intervention
     * @param vit vitesse de trajet
     */
    protected Robot(Carte carte, int cap, int tpsRemplissage, double vtIntervention, double vit) {
        this.map = carte;
        this.volumeEau = cap;
        this.capacite = cap;
        this.tempsRemplissage = tpsRemplissage;
        this.vitesseIntervention = vtIntervention;
        this.vitesse = vit;
        this.ptEau = cherchePointsDEau();
    }

    /**
     * @return case sur laquelle est le robot.
     */
    public Case getPosition() {
        return this.position;
    }

    /**
     * @return volume d'eau contenu dans le réservoir
     */
    public double getVolumeEau() {
        return this.volumeEau;
    }

    /**
     * @return capacité maximale du réservoir
     */
    public int getCapacite() {
        return capacite;
    }

    /**
     * @return temps de remplissage du réservoir
     */
    public int getTempsRemplissage() {
        return this.tempsRemplissage;
    }

    /**
     * @return carte associée au robot
     */
    public Carte getMap() {
        return map;
    }

    /**
     * @param Volume volume actuel du réservoir
     * @return temps d'intervention
     */
    public int gettempsIntervention(int Volume) {
        return (int) (Volume / vitesseIntervention);
    }

    /**
     * @param T nature du terrain
     * @return vitesse du robot
     */
    abstract public double getVitesse(NatureTerrain T);

    /**
     * @return image du robot pour la simulation
     */
    abstract public String getImage();

    /**
     * @return état du robot: occupé ou non pour faire une action
     */
    public boolean estDisponible() {
        return this.disponible;
    }

    /**
     * @return liste chaînée contenant les cases d'eau voisines de la position
     * du robot
     */
    public ArrayList<Case> getPtEau() {
        return ptEau;
    }

    /**
     * définir la position du robot, si la case est voisine et de terrain
     * compatible
     *
     * @param C case à atteindre
     */
    abstract public void setPosition(Case C);

    /**
     * définir la position initiale du robot
     *
     * @param C case : position initiale
     */
    abstract public void setPositionInit(Case C);

    /**
     * Définir la vitesse: utilisé par le lecteur de données
     *
     * @param speed vitesse à définir.
     */
    public void setVitesse(int speed) {
        this.vitesse = speed;
    }

    /**
     *
     * @param dispo
     */
    public void setDisponible(boolean dispo) {
        this.disponible = dispo;
    }

    /**
     * @param C case d'eau
     * @return true si le robot peut se remplir
     */
    public abstract boolean peutRemplir(Case C);

    /**
     * @param C case à atteindre
     * @return true si la case est accessible
     */
    abstract public boolean estAccessible(Case C);

    /**
     * diminue le volume d'eau dans le réservoir
     *
     * @param Volume volume d'eau à déverser
     */
    public void deverserEau(int Volume) /*throws EmptyTankException */ {
        if (Volume <= this.volumeEau) { //si le robot contient assez d'eau
            this.volumeEau = this.volumeEau - Volume; //On diminue la quantit� d'eau dans le r�servoir
        } else {
            this.volumeEau = 0;
            //throw new EmptyTankException();
        }
    }

    /**
     * remplit le réservoir : le volume d'eau est égal à la capacité max du
     * réservoir
     */
    public void remplirReservoir() { //Au moment où on appelle cette fonction, on a déjà vérifié que le robot pouvait se remplir

        this.volumeEau = capacite; //remplissage

    }

    /**
     * @return liste chaînée contenant les cases d'eau voisines de la position
     * du robot
     */
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

    /**
     *
     * @param simu
     * @param dst
     */
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
