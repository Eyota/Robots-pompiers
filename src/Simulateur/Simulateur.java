
package Simulateur;

import elements.Evenement;
import gui.Simulable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Simulateur implements Simulable{
    private List liste = new ArrayList();
    private int date;

    @Override
    public void next() {
    }

    @Override
    public void restart() {
    }
    
    public void ajouteEventement(Evenement e){
        this.liste.add(e);
        Collections.sort(this.liste);
    }
    
    public void incrementeDate(){
        this.date=this.date +1;
    }
    
    public boolean simulationTerminee(){
        if (this.liste.isEmpty()){
            return true;
        }
        return false;
    }
    
}
