package elements.events;

import elements.CaseWithoutWaterException;
import elements.robots.Robot;

public class EventRemplir extends Evenement {

    private Robot robot;

    /**
     * Creation de l'évènement qui remplit le réservoir du robot
     *
     * @param date date de l'évènement
     * @param robot robot dont le réservoir doit être rempli
     */
    public EventRemplir(int date, Robot robot) {
        super(date);
        this.robot = robot;
    }

    public void execute() {
        try {
            //si le robot ne peut pas remplir
            if (!this.robot.peutRemplir(this.robot.getPosition())) {
                throw new CaseWithoutWaterException();
            } else {
                //on considere que le reservoir est toujours vide
                System.out.println("Remplissage du reservoir");
                this.robot.remplirReservoir();
            }
        } catch (CaseWithoutWaterException c) {
            System.out.println("Le robot ne peut pas se remplir ici");
        }
    }

}
