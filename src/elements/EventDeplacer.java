
package elements;

public class EventDeplacer extends Evenement{
    private Case position;
    private Robot robot;
    
    public EventDeplacer (int date, Robot robot, Case newPos){
        super(date);
        this.robot = robot;
        this.position = newPos;
    }
    
    public void execute(){
        this.robot.setPosition(this.position);
    }
}
