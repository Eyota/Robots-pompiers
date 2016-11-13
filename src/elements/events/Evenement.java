
package elements.events;

public abstract class Evenement implements Comparable<Evenement> {
    protected double date;
    
    public Evenement(long date){
        this.date = date;
    }
    
    public double getDate(){
        return this.date;
    }
    
    public abstract void execute();
    
    @Override
    public int compareTo(Evenement e) {
        if (this.date-e.getDate()>=0){
            return 1;
        }
        else return -1;
    }
}