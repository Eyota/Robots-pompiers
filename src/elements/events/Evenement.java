/*
Numéro de groupe : 50
*/
package elements.events;

public abstract class Evenement implements Comparable<Evenement> {

    protected double date;

    /**
     * Crée l'objet Evenement, en prenant en compte sa date
     *
     * @param date indique l'ordre de priorité de l'évènement
     */
    public Evenement(long date) {
        this.date = date;
    }

    /**
     * @return date de l'évènement
     */
    public double getDate() {
        return this.date;
    }

    public abstract void execute();

    /**
     * Compare l'ordre de priorité de deux évènements en fonction de leurs dates
     *
     * @param e Evenement à comparer
     * @return 1 si e est prioritaire sur l'objet
     */
    @Override
    public int compareTo(Evenement e) {
        if (this.date - e.getDate() >= 0) {
            return 1;
        } else {
            return -1;
        }
    }
}
