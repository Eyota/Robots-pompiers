package elements.events;

import elements.Case;
import elements.Direction;
import elements.UnreachableCaseException;
import elements.robots.Robot;

public class EventDeplacer extends Evenement {
    private Case destination;
    private Direction direction;
    private Robot robot;

    public EventDeplacer(int date, Robot robot, Direction dir) {
        super(date);
        this.robot = robot;
        this.direction = dir;
    }

    public void execute() {
        try {
            this.destination = robot.getMap().getVoisin(robot.getPosition(), this.direction);
            this.robot.setPosition(this.destination);

        } catch (UnreachableCaseException e) {
            //System.out.println("Cette case n'existe pas");
        }
    }

}
