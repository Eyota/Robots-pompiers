/*
Numéro de groupe : 50
*/
package elements;

public class Incendie {

    private Case position;
    private double intensite;

    public Incendie(Case pos, int eau) {
        this.position = pos;
        this.intensite = eau;
    }
    
    public Case getPosition(){
        return this.position;
    }
    
    public double getIntensite(){
        return this.intensite;
    }

    public void setIntensite(double n){
        this.intensite = n;
    }
    
}
