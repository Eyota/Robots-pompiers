package elements.events;

import elements.Direction;
import elements.robots.Robot;

public class EventDisponible extends Evenement {

    private Robot robot;

    /**
     * Crée l'évènement qui indique la disponibilité du robot
     *
     * @param date date de l'évènement
     * @param robot robot en question
     */
    public EventDisponible(int date, Robot robot) {
        super(date);
        this.robot = robot;
    }

    public void execute() {
        robot.setDisponible(true);
    }
}
