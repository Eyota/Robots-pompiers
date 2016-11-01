package elements;

import java.util.List;

public class DonneesSimulation {

    private Carte carte;
    private List<Incendie> incendies;
    private List<Robot> robots;

    /*public DonneesSimulation(Carte map, List<Incendie> listeFeux, List<Robot> listeRobots){
     this.carte = map;
     this.incendies = listeFeux;
     this.robots = listeRobots;
     }*/
    
    
    public void setCarte(Carte carte) {
        this.carte = carte;
    }

    public void setIncendies(List<Incendie> incendies) {
        this.incendies = incendies;
    }

    public void setRobots(List<Robot> robots) {
        this.robots = robots;
    }

    public Carte getCarte() {
        return carte;
    }

}
