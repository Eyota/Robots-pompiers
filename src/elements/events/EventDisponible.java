
package elements.events;

import elements.Direction;
import elements.robots.Robot;


public class EventDisponible extends Evenement{
    private Robot robot;

    public EventDisponible(int date, Robot robot) {
        super(date);
        this.robot = robot;
    }

    public void execute() {
        robot.setDisponible(true);
    }
}
