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

    public EventDeplacerCase(int date, Robot robot, Case dest) {
        super(date);
        this.robot = robot;
        this.destination = dest;
    }

    public void execute() {
        this.robot.setPosition(this.destination);
    }

}
