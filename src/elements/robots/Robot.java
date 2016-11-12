package elements.robots;

import elements.Carte;
import elements.Case;
import elements.CaseWithoutWaterException;
import elements.NatureTerrain;
import java.util.ArrayList;

public abstract class Robot {

    protected Case position;
    protected Carte map;
    protected int volumeEau; //en litres
    private int capacite; //en litres
    private int tempsRemplissage; //en s
    private double vitesseIntervention; // en L/s
    private int vitesse; // en km/h. 
    private boolean disponible = true;
    private ArrayList<Case> ptEau;

    protected Robot(Carte carte, int cap, int tpsRemplissage, double vtIntervention, int vit) {
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

    abstract public int getVitesse(NatureTerrain T);

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
    
    public abstract boolean peutRemplir();
    
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

    public void remplirReservoir() throws CaseWithoutWaterException {
        for (Case Voisin : map.ListeVoisins(position)) { //Si la case est voisine de sa position

            if (Voisin.getNature() == NatureTerrain.EAU) { //et qu'elle est compos�e d'eau

                this.volumeEau = capacite; //remplissage
                break;

            } else {

                throw new CaseWithoutWaterException();

            }
        }
    }

    public ArrayList<Case> cherchePointsDEau (){
        int i,j;
        ArrayList<Case> liste = new ArrayList<Case>();
        for (i=0; i<map.getNbLignes(); i++){
            for (j=0; j<map.getNbColonnes(); j++){
                if (this.peutRemplir(map.getCase(i, j))){
                    liste.add(map.getCase(i, j));
                }
            }
        }
        return liste;
    }
     
}
