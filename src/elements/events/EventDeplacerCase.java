
package elements.events;

import elements.Case;
import elements.Direction;
import elements.UnreachableCaseException;
import elements.robots.Robot;

public class EventDeplacerCase extends Evenement {
    private Case destination;
    private Robot robot;

    public EventDeplacerCase(int date, Robot robot, Case dest) {
        super(date);
        this.robot = robot;
        this.destination = dest;
    }

    public void execute() {
        try {
            if (Case.sontVoisines(robot.getPosition(), destination)){
                this.robot.setPosition(this.destination);
            }
            else throw new UnreachableCaseException();

        } catch (UnreachableCaseException e) {
            System.out.println("Ces cases ne sont pas voisines (eventDeplacerCase)");
        }
    }

}
