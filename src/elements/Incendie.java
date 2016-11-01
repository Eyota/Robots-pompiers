package elements;

public class Incendie {

    private Case position;
    private int intensite;

    public Incendie(Case pos, int eau) {
        this.position = pos;
        this.intensite = eau;
    }
    
    public Case getPosition(){
        return this.position;
    }
    
}
