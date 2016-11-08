package elements;

public abstract class Robot {

    protected Case position;
    protected Carte map;
    protected double volumeEau; //en litres
    private int capacite; //en litres
    private double tempsRemplissage; //en s
    private double vitesseIntervention; // en L/s
    private double vitesse; // en km/h. 

    protected Robot(Carte carte, int cap, int tpsRemplissage, double vtIntervention, double vit) {
        this.map = carte;
        this.volumeEau = cap;
        this.capacite = cap;
        this.tempsRemplissage = tpsRemplissage;
        this.vitesseIntervention = vtIntervention;
        this.vitesse = vit;
    }

     public Case getPosition() {

        return this.position;
    }

    public double getVolumeEau() {

        return this.volumeEau;
    }

    public double getTempsRemplissage() {

        return this.tempsRemplissage;

    }

    public Carte getMap() {
        return map;
    }
    
    
    public double gettempsIntervention(double Volume) {
        return Volume / vitesseIntervention;
    }

    abstract public double getVitesse(NatureTerrain T);

    abstract public void setPosition(Case C);
    
    abstract public void setPositionInit(Case C);

    public void setVitesse(double speed) {
        this.vitesse = speed;
    }

    public void deverserEau(double Volume) throws EmptyTankException {
        if (Volume <= this.volumeEau) { //si le robot contient assez d'eau
            this.volumeEau = this.volumeEau - Volume; //On diminue la quantit� d'eau dans le r�servoir
        } else {
            this.volumeEau = 0;
            throw new EmptyTankException();
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

    public abstract boolean peutRemplir();
}
