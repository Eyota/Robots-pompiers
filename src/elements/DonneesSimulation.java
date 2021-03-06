/*
Numéro de groupe : 50
*/
package elements;

import elements.robots.Robot;
import java.util.ArrayList;
import java.util.List;

public class DonneesSimulation {

    private Carte carte;
    private ArrayList<Incendie> incendies;
    private ArrayList<Robot> robots;
    
    public void setCarte(Carte carte) {
        this.carte = carte;
    }

    public void setIncendies(ArrayList<Incendie> incendies) {
        this.incendies = incendies;
    }

    public void setRobots(ArrayList<Robot> robots) {
        this.robots = robots;
    }

    public Carte getCarte() {
        return carte;
    }

    public ArrayList<Incendie> getIncendies() {
        return incendies;
    }

    public ArrayList<Robot> getRobots() {
        return robots;
    }
    
}
