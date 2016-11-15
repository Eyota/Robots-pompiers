/*
Numéro de groupe : 50
*/
package elements.events;

import elements.Case;
import elements.Direction;
import elements.UnreachableCaseException;
import elements.robots.Robot;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventDeplacerCase extends Evenement {

    private Case destination;
    private Robot robot;

    /**
     * Créer l'évènement qui déplace le robot d'une case.
     *
     * @param date date de l'évènement
     * @param robot robot à déplacer
     * @param dest case sur laquelle déplacer le robot
     */
    public EventDeplacerCase(int date, Robot robot, Case dest) {
        super(date);
        this.robot = robot;
        this.destination = dest;
    }

    public void execute() {
        //System.out.println(robot.toString()+ " va en " + destination);
        this.robot.setPosition(this.destination);
        
    }

}
